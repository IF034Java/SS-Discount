<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>

<div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <%--&lt;%&ndash;--%><span class="icon-bar"></span><%--&ndash;%&gt;--%>
            </button>
            <a class="navbar-brand" href="/">SoftServe Discount Program</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">

            <security:authorize access="not isAuthenticated()">
                <li><a href="/login">Login</a></li>
                <li><a href="/registration">Registration</a></li>
            </security:authorize>

            <security:authorize access="isAuthenticated()">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/logout">Logout</a></li>
                </ul>
            </security:authorize>

            <security:authorize access="isAuthenticated()">
                <ul class="nav navbar-text navbar-right">
                    <li>Logged in as <strong><security:authentication property="principal.mail"/></strong></li>
                </ul>
            </security:authorize>

            <security:authorize access="not isAuthenticated()">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <script src="//loginza.ru/js/widget.js" type="text/javascript"></script>
                        <a href="https://loginza.ru/api/widget?token_url=http://localhost:8080/login/openid"
                           class="loginza">Log in via OpenID</a></li>
                </ul>
            </security:authorize>

            <ul class="nav navbar-nav navbar-right">
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="/view">Management</a></li>
            </security:authorize>
            </ul>

            </ul>
        </div>
</div>

<div class="page-header"></div>