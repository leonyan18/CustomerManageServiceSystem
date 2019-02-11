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
<script>
    var url='http://'+window.location.host+'/marcopolo';
    var socket=new SockJS(url);
    console.info(socket.url);
    var stomp=Stomp.over(socket);
    var payload=JSON.stringify({'message':'Marco!'});
    stomp.connect('guest','guest',function (frame) {
        stomp.send("/app/marco",{},payload);
        stomp.subscribe("/topic/marco",handleOneTime)
    });
    function handleOneTime(message) {
        console.log('Received: ', message);
        sayMarco();
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
</script>
<form method="post" action="http://upload.qiniup.com/"
      enctype="multipart/form-data">
    <input name="key" type="hidden" value="1160299279/第一次试验/">
    <input name="token" type="hidden" value="0ygyCNdhOs4z9SOcS5uBYMo5sv80imPMeb3LhsPQ:nmR0ClGdPvSaXu3XcxEuLIZRKSA=:eyJzY29wZSI6ImpvYi1tYW5hZ2VtZW50LXN5c3RlbS1iYXNlZC1vbi1vcGVuLWNsb3VkLXN0b3JhZ2UiLCJkZWFkbGluZSI6MTU0MjAyMTI0N30=">
    <input name="file" type="file" />
    <input type="submit" value="上传文件" />
</form>