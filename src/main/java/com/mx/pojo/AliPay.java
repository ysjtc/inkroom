package com.mx.pojo;

import java.io.Serializable;

public class AliPay implements Serializable {

    /*商户订单号，必填*/
    private String out_trade_no;

    /*订单名称，必填*/
    private String subject;

    /*付款金额，必填*/
    private StringBuffer total_amount;

    /*商品描述，可空*/
    private String body;

    /*支付宝交易号，必填*/
    private String tradeNo;

    /*退款金额，必填*/
    private String refundAmount;

    /*退款原因，可空*/
    private String refundReason;

    /*标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传*/
    private String out_request_no;



    /*超时时间参数*/
    private String timeout_express="5m";

    private String product_code="FAST_INSTANT_TRADE_PAY";


    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public StringBuffer getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(StringBuffer total_amount) {
        this.total_amount = total_amount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }

    @Override
    public String toString() {
        return "AliPay{" +
                "out_trade_no='" + out_trade_no + '\'' +
                ", subject='" + subject + '\'' +
                ", total_amount=" + total_amount +
                ", body='" + body + '\'' +
                '}';
    }
}
