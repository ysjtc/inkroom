package com.mx.service.Impl;

import com.alipay.api.AlipayApiException;
import com.mx.pojo.AliPay;
import com.mx.service.AliPayService;
import com.mx.utils.Pay.AliPay.AlipayUtils;
import org.springframework.stereotype.Service;

@Service
public class AliPayServiceImpl implements AliPayService {

    @Override
    public String aliPay(AliPay aliPay) throws AlipayApiException {
        return AlipayUtils.alipayConnect(aliPay);
    }

    @Override
    public String aliPayRefound(AliPay aliPay)  {
//        return AlipayUtils.alipayReFound(aliPay);
        return null;
    }
}
