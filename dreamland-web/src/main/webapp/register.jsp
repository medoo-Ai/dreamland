<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <meta name="keywords" content="囚徒网">
    <meta name="content" content="囚徒网">
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link type="text/css" rel="stylesheet" href="${ctx}/css/dreamland.css">
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
</head>

<body class="login_bj" style="background-color: grey">

<div class="zhuce_body">
    <div class="zhuce_kong" id="dre_div">
        <div class="zc">
            <div class="bj_bai" style="height: 408px">
                <h3>欢迎注册</h3>
                <form action="${ctx}/doRegister" method="post" id="registerForm">
                    <span id="reg_span"></span>
                    <input id="phone" name="phone" type="text" class="kuang_txt phone" placeholder="手机号"
                           onblur="checkPhone();">
                    <span id="phone_ok"></span>
                    <br/>
                    <span id="phone_span" style="color: red"></span>
                    <input id="email" name="email" type="text" class="kuang_txt email" placeholder="邮箱"
                           onblur="checkEmail();">
                    <br/>
                    <span id="email_span" style="color: red"></span>
                    <input id="password" name="password" type="password" class="kuang_txt possword" placeholder="密码"
                           onKeyUp="CheckIntensity(this.value)" onblur="checkPassword();">
                    <br/>
                    <span id="password_span"></span>
                    <input id="nickName" name="nickName" type="text" class="kuang_txt possword" placeholder="昵称"
                           onblur="checkNickName();">
                    <br/>
                    <span id="nickName_span" style="color: red"></span>
                    <%-- 集成验证码--%>
                    <input id="code" type="text" name="code" class="kuang_txt yanzm" placeholder="验证码"
                           onblur="checkCode()">
                    <div>
                        <img id="captchaImg" style="CURSOR: pointer" onclick="changeCaptcha()"
                             title="点击刷新验证码" align="absmiddle" src="${ctx}/captchaServlet" height="18" width="55">
                        <a href="javascript:;" onclick="changeCaptcha()" style="color: #666">看不清楚</a>
                        <span id="code_span" style="color: #e53d1c"></span>
                    </div>
                    <div>
                        <input id="protocol" type="checkbox" onclick="checkProtocol();"><span>已阅读并同意<a href="#"
                                                                                                       target="_blank"><span
                            class="lan">《囚徒网用户协议》</span></a></span>
                        <br/>
                        <span id="protocol_span"></span>
                    </div>
                    <input name="注册" type="button" class="btn_zhuce" id="to_register" value="注册">
                    <br/>
                    <span style="color: red">${error}</span>

                </form>
            </div>
            <div class="bj_right" style="height: 408px">
                <p>使用以下账号直接登录</p>
                <a href="#" class="zhuce_qq">QQ注册</a>
                <a href="#" class="zhuce_wb">微博注册</a>
                <a href="#" class="zhuce_wx">微信注册</a>
                <p>已有账号？<a href="login.html">立即登录</a></p>

            </div>
        </div>
    </div>
</div>

<div style="text-align:center;">
</div>
</body>

<script type="text/javascript">


    var phoneWidth = parseInt(window.screen.width);
    var phoneScale = phoneWidth / 640;
    var ua = navigator.userAgent;
    if (/Android (\d+\.\d+)/.test(ua)) {
        var version = parseFloat(RegExp.$1);
        if (version > 2.3) {
            document.write('<meta name="viewport" content="width=640, minimum-scale = ‘+phoneScale+‘, maximum-scale = ‘+phoneScale+‘, target-densitydpi=device-dpi">');
        } else {
            document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
        }
    } else {
        document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
    }

    //获取验证码
    function changeCaptcha() {
        // 使用attr(a,b)
        $("#captchaImg").attr('src', '${ctx}/captchaServlet?t=' + new Date().getTime());
    }

    //校验手机号


    var v = 0;
    var flag = false;

    function checkPhone() {
        var phone = $("#phone").val();
        // 校验手机号是否为空
        // 校验手机号的规则

        //用|把不同的规则分隔开,去掉前后空格
        phone = phone.replace(/^\s+|\s+$/g, "");
        if (phone == "") {
            $("#phone_span").text("请输入手机号");
            $("#phone_ok").text("");
            var hight = $("#regist-left").height();
            if (v == 0) {
                $("#regist-right").height(hight + 30);
                $("#regist-left").height(hight + 30);
                v++;
            }
            flag = false;
        }
        //
        if (!(/^1[3|4|5|8|7][0-9]\d{8}$/.test(phone))) {
            $("#phone_span").text("请重新输入手机号");
            $("#phone_ok").text("");
            var hight = $("#regist-left").height();
            if (v == 0) {
                $("#regist-right").height(hight + 30);
                $("#regist-left").height(hight + 30);
                v++;
            }
            flag = false;
        } else {
            //手机校验
            $.ajax({
                data: {"phone": phone},
                type: 'post',
                url: '/register/checkPhone',
                dataType: 'json',
                success: function (data) {
                    var val = data['message'];
                    if (val == "success") {
                        $("#phone_span").text("");
                        $("#reg_span").text();
                        $("#phone_ok").text("√").css("color", "green");
                        var context = $("#phone_ok").text();

                        if (context == "√") {
                            var hight = $("#regist-left").height();
                            if (v == 1) {
                                $("#regist-right").height(hight - 30);
                                $("#regist-left").height(hight - 30);
                                v = 0;
                            }
                            flag = true;
                        }
                    } else {
                        // 该手机已经被注册


                        $("#phone_span").text("该手机号已经被注册").css("color", "red");
                        $("#phone_ok").text("");
                        var hight = $("#regist-left").height();
                        if (v == 0) {
                            $("#regist-right").height(hight + 30);
                            $("#regist-left").height(hight + 30);
                            v++;
                        }
                        flag = false;
                    }
                }
            });
        }
        return flag;
    }

    // 添加高度
    function increaseHeight() {
        var height = $("#regist_left").height();
        $("#regist_left").height(height + 30);
        $("#regist_right").height(height + 30);
        //
    }

    function reduceHeight() {
        var height = $("#regist_left").height();
        $("#regist_left").height(height - 30);
        $("#regist_right").height(height - 30);
        //
    }


    //验证码校验
    var flag_code = false;

    function checkCode() {
        var code = $("#code").val();
        // 去除首尾空格
        code = code.replace(/^\s+|\s+$/g, "");
        // 判断验证码是否为空
        if (code == "") {
            $("#code").text("请输入验证码").css("color", "red");
            flag_code = false;
        } else {
            $.ajax({
                data: {"code": code},
                type: 'post',
                url: '/register/checkCode',
                dataType: 'json',
                success: function (data) {
                    var val = data['message'];
                    if (val == "success") {
                        $("#reg_span").text("");
                        $("#code_span").text("√").css("color", "green");
                        flag_code = true;
                    } else {
                        //
                        $("#phone_span").text("验证码错误").css("color", "red");
                        flag_code = false;
                    }
                }
            });
        }
        return flag_code;
    }

    // 勾选用户协议
    function checkProtocol() {
        // 判断用户是否勾选了协议
        if ($("#protocol").prop("checked")) {
            $("#reg_span").text("");
            // 勾选可提交  ，否则不能够提交
            return true;
        } else {
            return false;
        }
    }


</script>
</html>