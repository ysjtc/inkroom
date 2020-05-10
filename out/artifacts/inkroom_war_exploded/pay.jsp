<%--
  Created by IntelliJ IDEA.
  User: 陈
  Date: 2019/7/24
  Time: 0:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="static/js/jquery-3.3.1.js"></script>
    <%@include file="/WEB-INF/jsp/common/basePath.jsp"%>
</head>
<body>
       *商户订单 :<br>
       <input type="text" name="out_trade_no" id="out_trade_no"><br>
       *订单名称 :<br>
       <input type="text" name="subject" id="subject"><br>
       *付款金额 :<br>
       <input type="text" name="total_amount" id="total_amount"><br>
        商品描述 :<br>
       <input type="text" name="body" id="body"><br>
       <input type="submit" id="b2" value="支付宝支付">
    <br>

    <div id="formdiv">
        <h2> 此处执行返回的form表单</h2>

    </div>
<script>
    $("#b2").click(function () {
        var out_trade_no=$("input[name='out_trade_no']").val();;
        var subject=$("input[name='subject']").val();;
        var total_amount=$("input[name='total_amount']").val();;
        var body=$("input[name='body']").val();;
         // alert(subject);
        $.ajax({
            url:'Pay/AliPay',
            dataType:'html',
            type:'post',
            async:false,
            // contentType:'application/json',
            // data:JSON.stringify(obj),
            data:{
                'out_trade_no':out_trade_no,
                'subject':subject,
                'total_amount':total_amount,
                'body':body
            },
            success:function (data){
                alert("成功"+data);
                 $('#formdiv').html(data);

            },error:function (data) {
                alert("失败"+data);
            }
        })
    })



</script>

</body>
</html>
