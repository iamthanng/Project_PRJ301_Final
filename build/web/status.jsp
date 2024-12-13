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
        <!--navbar-->
        <%@include file="navbar.jsp" %>
        <!--end navbar-->
        <div style="display: flex;">
            <!--sidebar-->
            <%@include file="sidebar.jsp" %>
            <!--end sidebar-->
            <div class="container">
                <h2>Topic Status</h2>
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
                                    <button class="btn btn-warning view-details"
                                            data-id="${l.thesisId}" 
                                            data-title="${l.title}" 
                                            data-instructor="${instructorMap[l.teacherId]}"
                                            data-student-name="${student.name}" 
                                            data-student-code="${student.student_code}" 
                                            data-file-name="${l.fileName}" 
                                            data-file-path="${l.filePath}"
                                            data-registerdate="${l.register_date}" 
                                            data-status="${statusMap[l.status_id]}"> 
                                        Details
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Bootstrap Modal -->
        <!--Details-->
        <div class="modal fade" id="detailsModal" tabindex="-1" role="dialog" aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="detailsModalLabel">Details of Topic</h5>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <label for="modalTitle">Tittle:</label>
                                <input type="hidden" class="form-control" id="modalId" readonly>
                                <input type="text" class="form-control" id="modalTitle" readonly>
                            </div>
                            <div class="form-group">
                                <label for="modalInstructor">Instructor:</label>
                                <input type="text" class="form-control" id="modalInstructor" readonly>
                            </div>
                            <div class="row">
                                <div class="form-group col">
                                    <label for="modalStudentName">Student Name:</label>
                                    <input type="text" class="form-control" id="modalStudentName" readonly>
                                </div>
                                <div class="form-group col">
                                    <label for="studentCode">Student Code:</label>
                                    <input type="text" class="form-control" id="modalStudentCode" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="modalFileName">File:</label>
                                <a id="fileDownloadLink" href=""><input type="text" class="form-control" name="fileName" id="modalFileName" readonly></a>
                            </div>
                            <div class="row">
                                <div class="form-group col">
                                    <label for="modalDate">Registered Date:</label>
                                    <input type="text" class="form-control" id="modalDate" readonly>
                                </div>
                                <div class="form-group col">
                                    <label for="modalStatus">Status</label>
                                    <input type="text" class="form-control" id="modalStatus" readonly>
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
                $('.view-details').click(function () {
                    // Get data attributes from the button
                    var id = $(this).data('id');
                    var title = $(this).data('title');
                    var instructor = $(this).data('instructor');
                    var studentName = $(this).data('student-name');
                    var studentCode = $(this).data('student-code');
                    var fileName = $(this).data('file-name');
                    var registerDate = $(this).data('registerdate');
                    var status = $(this).data('status');

                    // Set the data into the modal
                    $('#modalId').val(id);
                    $('#modalTitle').val(title);
                    $('#modalInstructor').val(instructor);
                    $('#modalStudentName').val(studentName);
                    $('#modalStudentCode').val(studentCode);
                    $('#modalFileName').val(fileName);
                    $('#modalStatus').val(status);
                    $('#modalDate').val(registerDate);
                    $('#fileDownloadLink').attr('href', 'downloadFile?fileName=' + fileName);

                    // Show the modal
                    $('#detailsModal').modal('show');
                });

                $('.close').click(function () {
                    $('#detailsModal').modal('hide');
                    $('#editModal').modal('hide');
                });
            });
        </script>
    </body>
</html>
