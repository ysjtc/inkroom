package com.mx.utils.Pay.AliPay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.mx.pojo.AliPay;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlipayUtils {


    public static List alipayutil(AliPay aliPay) throws AlipayApiException {
        AlipayPropertiesConfig apc=new AlipayPropertiesConfig();
//            System.out.println( "-----"+apc.getKey("gatewayUrl"));
        //1、获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                apc.getKey("gatewayUrl"),//支付宝网关
                apc.getKey("app_id"),//appid
                apc.getKey("merchant_private_key"),//商户私钥
                "json",
                apc.getKey("charset"),//字符编码格式
                apc.getKey("alipay_public_key"),//支付宝公钥
                apc.getKey("sign_type")//签名方式
        );
        //2、设置请求参数
        AlipayTradePagePayRequest aliRequest = new AlipayTradePagePayRequest();
        //页面跳转同步通知页面路径
        aliRequest.setReturnUrl(apc.getKey("return_url"));
        // 服务器异步通知页面路径
        aliRequest.setNotifyUrl(apc.getKey("notify_url"));
        //封装参数
        aliRequest.setBizContent(JSON.toJSONString(aliPay));
        //将AlipayClient和AlipayTradePagePayRequest加入到list中
        List ali=new ArrayList();
        ali.add(alipayClient);
        ali.add(aliRequest);
        return  ali;
    }

    /**
     *
     * outTradeNo  商户订单号，商户网站订单系统中唯一订单号，必填   对应缴费记录的orderNo
     * totalAmount  付款金额，必填
     * subject 主题
     * body 商品描述，可空
     *
     */
        public static String alipayConnect(AliPay aliPay) throws AlipayApiException {
            //3、请求支付宝进行付款，并获取支付结果
            List aliList=AlipayUtils.alipayutil(aliPay);
            AlipayClient alipayClient= (AlipayClient)aliList.get(0);
            AlipayTradePagePayRequest aliRequest=(AlipayTradePagePayRequest)aliList.get(1);
            String result = alipayClient.pageExecute(aliRequest).getBody();
//            System.out.println("result"+result);
            //返回付款信息
            return  result;
        }



    /**
     * 支付宝退款接口
     * outTradeNo
     * tradeNo
     * refundAmount
     * refundReason
     * out_request_no  标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     *
     */
        public static String alipayReFound(String out_trade_no,String tradeNo,String refundAmount,String refundReason,String out_request_no) {
            AliPay aliPay=new AliPay();
            aliPay.setOut_trade_no(out_trade_no);
            aliPay.setTradeNo(tradeNo);
            aliPay.setRefundAmount(refundAmount);
            aliPay.setRefundReason(refundReason);
            aliPay.setOut_request_no(out_request_no);
            try {
                //3、请求支付宝进行退款，并获取结果
                List aliList=AlipayUtils.alipayutil(aliPay);
                AlipayClient alipayClient= (AlipayClient)aliList.get(0);
                AlipayTradePagePayRequest aliRequest=(AlipayTradePagePayRequest)aliList.get(1);
                String result = alipayClient.execute(aliRequest).getBody();
                //返回退款信息
                return result;
            }catch (AlipayApiException e){
                e.printStackTrace();
                return null;
            }

        }

    /**
     * 支付宝的验签方法
     * @param request
     * @return
     */
    public static boolean checkSign(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, String> paramsMap = new HashMap<>();
        requestMap.forEach((key, values) -> {
            String strs = "";
            for(String value : values) {
                strs = strs + value;
            }
            System.out.println(("key值为"+key+"value为："+strs));
            paramsMap.put(key, strs);
        });

        //调用SDK验证签名
        try {

            AlipayPropertiesConfig apc=new AlipayPropertiesConfig();
            return  AlipaySignature.rsaCheckV1(paramsMap, apc.getKey("alipay_public_key"), apc.getKey("charset"), apc.getKey("sign_type"));
        } catch (AlipayApiException e) {
            e.printStackTrace();
            System.out.println("验签失败");
            return false;
        }
    }

}
