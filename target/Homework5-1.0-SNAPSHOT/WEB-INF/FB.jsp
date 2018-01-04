<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/servlet/FB" method="get">

    <div>
        <a href="https://graph.facebook.com/me?fields=name,email&access_token=EAACEdEose0cBAG9purEqaDhDdZB7zwiSRIKnwMSQ6Oj4TpbUan37SNC5Kr1KKEA7Kx0Oiunq3RbYvcSU7tO7S9NLNuviZAOYh4rzxCZAJAX6W3O8ShVAMJ9by5g0XhUZC7oMdinNlp6YOZCWqD881E43mOq2aPpncrEs2zuZBCv1BbhnfF0kcFlmXwTMKETsgZAZAVyaVqnEYogINklywVAVxRDULZCUd54YZD">facebook</a>
    </div>
    <div class="fb">
        <div class="loginwithFB">
            <a href="https://graph.facebook.com/me?
    fields=name,email">
                <img
                        style="margin-top: 138px;" src="./img/facebookloginbutton.png"/>
            </a>
        </div>
    </div>

</form>
<form action="/servlet/FB" method="post">
    <a href="https://graph.facebook.com/me?fields=name,email&access_token=EAACEdEose0cBAG9purEqaDhDdZB7zwiSRIKnwMSQ6Oj4TpbUan37SNC5Kr1KKEA7Kx0Oiunq3RbYvcSU7tO7S9NLNuviZAOYh4rzxCZAJAX6W3O8ShVAMJ9by5g0XhUZC7oMdinNlp6YOZCWqD881E43mOq2aPpncrEs2zuZBCv1BbhnfF0kcFlmXwTMKETsgZAZAVyaVqnEYogINklywVAVxRDULZCUd54YZD">facebook
        (GET)</a>
    <a href="https://www.facebook.com/dialog/oauth?
    client_id=218978205312420&
    redirect_uri=http://localhost:8080/servlet/profile&
    scope=email">test with redirect</a>
</form>
</body>
</html>
