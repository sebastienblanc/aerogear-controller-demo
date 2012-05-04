<%@page pageEncoding="UTF-8" %>
<html>
<body>
<p>hello, Security!</p>
<p>maybe you should try the <a href="delorean">delorean page</a></p>

<p>
<form action="welcome" method="post">
    <ul>
        <li><label>Username:</label><input type="text" name="userLogin.username"/></li>
        <li><label>Password:</label><input type="text" name="userLogin.password"/></li>
        <li><input type="submit"/></li>
    </ul>
</form>
</p>
</body>
</html>