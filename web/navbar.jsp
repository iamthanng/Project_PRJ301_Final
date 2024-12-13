<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-light bg-light navbar-custom">
    <a class="logo_navbar" href="home"> 
        <!-- Logo -->
        <img src="image/logo_navbar.png" width="80px" height="60px" alt="Logo"/>
    </a>
    <c:choose>
        <c:when test="${not empty sessionScope.account}">
            <div class="profile-container">
                <c:choose>
                    <c:when test="${account.roleId == 1}">
                        <span>${student.name}</span>
                    </c:when>
                    <c:when test="${account.roleId == 2}">
                        <span>${teacher.name}</span>
                    </c:when>
                    <c:when test="${account.roleId == 3}">
                        <span>${taskmaster.name}</span>
                    </c:when>
                    <c:when test="${account.roleId == 4}">
                        <span>${admin.name}</span>
                    </c:when>
                </c:choose>
                <div class="dropdown show" style="width: 50px;  margin-right: 65px">
                    <a class="" href="#" role="button" data-bs-toggle="dropdown">
                        <div style="display: flex; align-items: center; color: black;">
                            <img class="logo_profile" src="image/logo_profile.png" height="45px" width="45px">
                            <i style="margin-left: 5px" class="bi bi-three-dots"></i>
                        </div>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="profile"><i class="bi bi-person-fill"></i></i> Profile</a></li>
                        <li><a class="dropdown-item" href="logout"><i class="bi bi-box-arrow-left"></i> Logout</a></li>
                    </ul>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <a href="login">Login</a>
        </c:otherwise>
    </c:choose>
</nav>