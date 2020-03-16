package com.mx.service;

import com.alipay.api.AlipayApiException;
import com.mx.pojo.AliPay;

public interface AliPayService {

    /*支付宝支付*/
    String aliPay(AliPay aliPay) throws AlipayApiException;

    /*支付宝退款*/
    String aliPayRefound(AliPay aliPay) throws AlipayApiException;
}
