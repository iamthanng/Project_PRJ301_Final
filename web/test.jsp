<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!--css icon-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>Home Page</title>
        <style>
            body{
                font-family: Arial, sans-serif;
            }
            /*navbar css*/
            .navbar-custom{
                border-bottom: 1px solid #f7f7f7;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            }
            .logo_navbar{
                padding-left: 50px;
            }
            .profile-container {
                display: flex;
                align-items: center;
            }
            .logo_profile {
                margin-left: 10px;
                cursor: pointer;
            }
            .dropdown-menu {
                min-width: 100px; /* Adjust the minimum width as needed */
            }
            /*sidebar css*/
            .side-bar button:hover:not(.active), .side-bar div a:hover:not(.active){
                background-color: #f7f7f7;
            }

            .side-bar button {
                margin-left: 30px;
            }
            .side-bar div a{
                text-decoration: none;
            }

            ul.a {
                text-decoration: none;
                font-size: 15px;
                list-style-type: square;
                margin-left: 70px;
            }

            /*profile css*/
            .profile {
                background: #FF8E36;
                display: flex;
                justify-content: center;
                border-radius: 10px;
                padding: 20px 150px;
                margin: 0px auto;
            }
            .container {
                background: white;
                padding: 50px 200px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                max-width: 800px;
                /*margin: 0;*/
            }
            .container h2 {
                text-align: center;
                margin-bottom: 20px;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
            }
            .form-group input {
                width: 100%;
                padding: 8px;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            .form-group input[type="submit"] {
                width: 200px;
                background: #6200ea;
                color: white;
                border: none;
                cursor: pointer;
                transition: background 0.3s;
            }
            .form-group input[type="submit"]:hover {
                background: #3700b3;
            }
            .submit{
                display: flex;
                justify-content: center;
            }
            .form-row {
                display: flex;
                justify-content: space-between;
            }
            .form-row .form-group {
                flex: 1;
                margin-right: 10px;
            }
            .form-row .form-group:last-child {
                margin-right: 0;
            }
        </style>
    </head>
    <body>
        <!--navbar-->
        <%@include file="navbar.jsp" %>
        <!--end navbar-->
        <div style="display: flex;">
            <!--sidebar-->
            <%@include file="sidebar.jsp" %>
            <!--end sidebar-->
            <%@include file="profile.jsp" %>
            <!--profile-->
        </div>
        <!--end profile-->
    </body>
</html>
