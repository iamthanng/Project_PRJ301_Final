<%@page contentType="text/html" pageEncoding="UTF-8"%>

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

        </style>
    </head>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
            <div class="main-content">
                <div class="sidebar">
                <jsp:include page="sidebar.jsp"></jsp:include>
            </div>
        </div>
    </div>
</body>
</html>
