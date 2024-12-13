<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Login Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <!--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">-->

        <style>
            .login-form {
                width: 340px;
                margin: 100px auto;
                font-size: 15px;
                border-radius: 5px;
                margin-bottom: 15px;
                background: #f7f7f7;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                padding: 30px;
            }
            .login-form h2 {
                margin: 0 0 15px;
            }
            .form-control, .btn {
                min-height: 38px;
                border-radius: 5px;
            }
            .login-form button {
                font-size: 15px;
                font-weight: bold;
            }
            .logo_navbar{
                width:80px;
                height:60px;
                margin-top: 20px;
                margin-left: 20px;
            }
        </style>
    </head>
    <body>
        <!-- Logo -->
        
        <a href="" ><img class="logo_navbar" src="image/logo_navbar.png" alt="logo"/></a>
        <div class="login-form">
            <form action="login" method="post">
                <h2 class="text-center">LOG IN</h2>   
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Username" name = "username" required="required">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="Password" name = "password" required="required">
                </div>
                <p class="text-danger">${mess}</p>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">LOGIN</button>
                </div>
            </form>
        </div>
    </body>
</html>