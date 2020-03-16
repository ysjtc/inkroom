package com.mx.controller.backMange;

import com.alipay.api.AlipayApiException;
import com.mx.pojo.AliPay;
import com.mx.service.AliPayService;
import com.mx.utils.Pay.AliPay.AlipayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Pay")
public class PayController {


    //支付宝支付，微信支付写后面.
    @Autowired
    private AliPayService aliPayService;

    /**
     * 支付宝支付接口
     *
     * @param out_trade_no  订单编号
     * @param subject 订单名称
     * @param total_amount 订单金额
     * @param body 备注
     * @return
     * @throws AlipayApiException
     */
    @ResponseBody
    @RequestMapping(value = {"/AliPay"},method = RequestMethod.POST)
    public Map alipay(String out_trade_no, String subject, String total_amount, String body) throws AlipayApiException {
//        System.out.println(out_trade_no+subject+total_amount+body);
        AliPay pay=new AliPay();
        pay.setOut_trade_no(out_trade_no);
        pay.setSubject(subject);
        pay.setTotal_amount(new StringBuffer().append(total_amount));
        pay.setBody(body);
        System.out.println(pay.toString());
        String form=aliPayService.aliPay(pay);
        System.out.println(form);
        Map map=new HashMap();
        map.put("form",form);
        map.put("result",true);
        return map;
    }

    /**
     * 支付宝支付成功后.会回调该接口
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */

    @ResponseBody
    @RequestMapping(value = {"/notify_url"},method = RequestMethod.POST)
    public String notify_url(HttpServletRequest request) throws UnsupportedEncodingException {
        boolean signVerified = AlipayUtils.checkSign(request);
        if (signVerified){
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }
            return "success";
        }else {
            return "failure";
        }
    }


//    @GetMapping("/return_url")
//    @ResponseBody
//    public String return_url(HttpServletRequest request) throws UnsupportedEncodingException {
//        boolean signVerified =AlipayUtils.checkSign(request);
//        if (signVerified){
//            //商户订单号
//            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//            //支付宝交易号
//            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
//            //交易状态
//            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
//            if(trade_status.equals("TRADE_FINISHED")){
//                //判断该笔订单是否在商户网站中已经做过处理
//                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                //如果有做过处理，不执行商户的业务程序
//                //注意：
//                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
//            }else if (trade_status.equals("TRADE_SUCCESS")){
//                //判断该笔订单是否在商户网站中已经做过处理
//                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                //如果有做过处理，不执行商户的业务程序
//                //注意：
//                //付款完成后，支付宝系统发送该交易状态通知
//            }
//            return "success";
//        }else {
//            return "failure";
//        }
//    }
}
