<jsp:include page="../../template/header.jsp" />
<div class="container">
    <div class="sixteen columns">
        <h1 class="remove-bottom" style="margin-top: 40px">Simple page</h1>
        <hr />
    </div>
    <div class="sixteen columns">
        <p>maybe you should try the <a href="delorean">restricted delorean page</a></p>
        <p>you can also try the <a href="admin">restricted admin page</a></p>
        <p><a href="login">Login</a></p>
        <p><a href="register">Register</a></p>
    </div>
    <div class="sixteen columns">
        <form action="cars" method="post">
            <ul>
                <li><label>Color:</label><input autocomplete="off" type="text" name="car.color"/></li>
                <li><label>Brand:</label><input autocomplete="off" type="text" name="car.brand"/></li>
                <li><input type="submit"/></li>
            </ul>
        </form>
    </div>
    <div class="sixteen columns">
        <p>maybe you should try out the general <a href="throwException">error handling</a></p>
    </div>

</div>
<jsp:include page="../../template/footer.jsp" />