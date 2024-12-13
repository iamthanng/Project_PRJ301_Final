<%-- 
    Document   : review
    Created on : Jul 18, 2024, 8:39:07 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Review</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!--css icon-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
                width: 70%;
                margin: auto;
                margin-top: 20px;
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

            .container {
                margin-top: 20px;
            }
            .table th, .table td {
                vertical-align: middle;
            }
            .action-buttons .btn {
                margin-right: 5px;
            }
            .title{
                width: 550px;
            }
        </style>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <!--end navbar-->
        <div style="display: flex;">
            <!--sidebar-->
            <%@include file="sidebar.jsp" %>
            <div class="container">
                <h2>Review</h2>
                <table class="table table-hover">
                    <thead >
                        <tr class="thead">
                            <th class="title" scope="col">Topic Name</th>
                            <th scope="col">Instructor</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <c:forEach items="${requestScope.list}" var="l">
                            <tr>
                                <td class="title">${l.title}</td>
                                <td>${instructorMap[l.teacherId]}</td>
                                <td class="action-buttons">
                                    <button class="btn btn-warning review-details"
                                            data-score="${l.score}"
                                            data-status="${statusMap[l.status_id]}"
                                            > 
                                        Review
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <!--Review Modal-->
        <div class="modal fade" id="reviewModal" tabindex="-1" role="dialog" aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="detailsModalLabel">Details of Topic</h5>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="row">
                                <div class="form-group col">
                                    <label for="modalInstructor">Total Score: </label>
                                    <input type="text" class="form-control" id="totalScore" readonly>
                                </div>
                                <div class="form-group col">
                                    <label for="modalStudentName">Status:</label>
                                    <input type="text" class="form-control" id="status" readonly>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="close btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $(document).ready(function () {
                $('.review-details').click(function () {
                    // Get data attributes from the button
                    var score = $(this).data('score');
                    var status = $(this).data('status');

                    // Set the data into the modal
                    $('#totalScore').val(score);
                    $('#status').val(status);

                    // Show the modal
                    $('#reviewModal').modal('show');
                });

                $('.close').click(function () {
                    $('#reviewModal').modal('hide');
                });
            });
        </script>
    </body>
</html>
