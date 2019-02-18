<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 11835
  Date: 2018/9/9
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="//cdn.jsdelivr.net/sockjs/1.0.0/sockjs.min.js"></script>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="<s:url value="/resources/stomp.js"/>"></script>
<input id="name">
<button id="btn">开启</button>
<div id="message"></div>
<script>
    $("#btn").click(function () {
        var url='http://'+window.location.host+'/marcopolo';
        var socket=new SockJS(url);
        console.info(socket.url);
        var stomp=Stomp.over(socket);
        var payload=JSON.stringify({'message':'Marco!'});
        var id=$("#name").val();
        stomp.connect(
            {
                name: id // 携带客户端信息
            },
            function connectCallback(frame) {
                // 连接成功时（服务器响应 CONNECTED 帧）的回调方法
                setMessageInnerHTML("连接成功");
                stomp.send("/app/marco",{},payload);
                stomp.subscribe("/topic/marco",handleOneTime);
                stomp.subscribe("/user/queue/notifications",handleMessage);
            },
            function errorCallBack(error) {
                // 连接失败时（服务器响应 ERROR 帧）的回调方法
                setMessageInnerHTML("连接失败");
            }
        );
        // stomp.connect({name:id},'guest','guest',function (frame) {
        //     stomp.send("/app/marco",{},payload);
        //     stomp.subscribe("/topic/marco",handleOneTime);
        //     stomp.subscribe("/user/queue/notifications",handleMessage);
        // });
        function handleOneTime(message) {
            console.log('Received: ', message);
            stomp.send("/app/message", {},
                JSON.stringify({'from': 1,'to': 2,'content':"12121"}));
            // sayMarco();
        }
        function handleMessage(message) {
            console.log('Received: ', message.body);
            setMessageInnerHTML(message.body);
        }
        function handlePolo(message) {
            console.log('Received: ', message);
            if (JSON.parse(message.body).message === 'Polo!') {
                setTimeout(function(){sayMarco()}, 2000);
            }
        }
        function sayMarco() {
            console.log('Sending Marco!');
            stomp.send("/app/marco", {},
                JSON.stringify({ 'message': 'Marco!' }));
        }
        function setMessageInnerHTML(innerHTML) {
            document.getElementById('message').innerHTML += innerHTML + '<br/>';
        }
    });
</script>