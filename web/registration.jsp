<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đăng Ký Đề Tài</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!--css icon-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <style>
            body {
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
            /*register*/
            .container {
                position: relative;
                width: 60%;
                margin: auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 10px;
                box-shadow: 2px 2px 12px rgba(0,0,0,0.2);
            }
            .container h2 {
                display: flex;
                justify-content: center;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                font-weight: bold;
            }
            .form-group input, .form-group textarea, .form-group select {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .btn-submit {
                background-color: #28a745;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .btn-submit:hover {
                /*color: black;*/
                background-color: #0e8529;
            }

            /*alert*/
            .alert {
                width: 275px;
                padding: 15px;
                background-color: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
                border-left: 5px solid;
                border-radius: 5px;
                display: none;  /*Hidden by default */
                position: fixed;
                top: 65px;
                right: 30px;
                /*z-index: 9999;*/
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                font-size: 16px;
            }

            .step-hide {
                display: none;
            }
        </style>
    </head>
    <body>
        navbar
        <%@include file="navbar.jsp" %>
        end navbar
        <div style="display: flex;">
            sidebar
            <%@include file="sidebar.jsp" %>
            end sidebar
            <div class="container">
                <h2>Register Topic</h2>

                <div class="alert" id="success-alert">
                    <strong>Registered topic successfully!</strong>
                </div>

                <form id="registration" action="registration" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="title">Topic Name</label>
                        <input type="text" id="title" name="title" required>
                    </div>
                    <div class="form-group">
                        <label for="studentId">Student Code</label>
                        <input type="hidden" id="studentId" name="studentId" value="${student.studentId}" readonly>
                        <input type="text" id="studentCode" name="studentCode" value="${student.student_code}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="teacher">Instructor</label>
                        <select id="teacherId" name="teacherId">
                            <c:forEach items="${requestScope.list}" var="t">
                                <option value="${t.teacherId}">${t.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="file">File of Outline</label>
                        <input type="file" name="file" required/>
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea id="description" name="description" rows="4"></textarea>
                    </div>
                    <div class="form-group" style="display: flex; justify-content: center">
                        <button type="submit" class="btn-submit" id="submitAdd">Submit</button>
                    </div>
                </form>

            </div>
        </div>
    </body>
    <script>
        $(document).ready(function () {
            $('#submitAdd').click(function (event) {
                event.preventDefault(); // Prevent the default form submission
                var form = $('#registration')[0];
                if (form.checkValidity()) {
                    $('#success-alert').fadeIn().delay(500).fadeOut(function () {
                        form.submit(); // Submit the form after fading out the alert
                    });
                } else {
                    form.reportValidity(); // Display validation messages
                }
            });
        });

        $(document).on(click, '.js-step', function (e) {
            e.stopPropagation();
            var attrStep = $(this).attr('data-show');
            $(this).parents('.js-steps-content').hide();
            $(attrStep).show();
        });
    </script>
</html>
