<%-- 
    Document   : committee
    Created on : Jul 6, 2024, 3:32:04 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Grade</title>
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
            .container {
                margin-top: 20px;
            }
            .table th, .table td {
                text-align: center;
            }
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

            .container h2 {
                display: flex;
                justify-content: center;
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
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                font-size: 16px;
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
            <div class="container">
                <h2>Grading List</h2>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Title</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.thesisList}" var="thesis">
                            <tr>
                                <td>${thesis.title}</td>
                                <td>
                                    <c:forEach items="${requestScope.committeeList}" var="committee">
                                        <c:if test="${(thesis.thesisId == committee.thesis_id)}">
                                            <c:forEach items="${requestScope.cdList}" var="cd">
                                                <c:if test="${(cd.committee_id == committee.committee_id) and (cd.teacher_id == teacher.teacherId) and (cd.score != 0)}">
                                                    <button class="btn btn-primary disabled"
                                                            >Grading</button>
                                                </c:if>
                                                <c:if test="${(cd.committee_id == committee.committee_id) and (cd.teacher_id == teacher.teacherId) and (cd.score == 0)}">
                                                    <button class="btn btn-primary view-grading" data-bs-toggle="modal" data-bs-target="#assignTopicsModal" 
                                                            data-id="${thesis.thesisId}" 
                                                            data-title="${thesis.title}" 
                                                            data-instructor="${instructorMap[thesis.teacherId]}"
                                                            data-student-name="${studentMap[thesis.studentId]}" 
                                                            data-file-name="${thesis.fileName}" 
                                                            data-file-path="${thesis.filePath}" 
                                                            >Grading</button>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>

                                    <button class="btn btn-warning view-details" data-bs-toggle="modal" data-bs-target="#detailsModal"
                                            data-id="${thesis.thesisId}" 
                                            data-title="${thesis.title}" 
                                            data-instructor="${instructorMap[thesis.teacherId]}"
                                            data-student-name="${studentMap[thesis.studentId]}" 
                                            data-file-name="${thesis.fileName}" 
                                            data-file-path="${thesis.filePath}" 
                                            <c:forEach items="${requestScope.committeeList}" var="committee">
                                                <c:if test="${(thesis.thesisId == committee.thesis_id)}">
                                                    <c:forEach items="${requestScope.cdList}" var="cd">
                                                        <c:if test="${(cd.committee_id == committee.committee_id) and (cd.teacher_id == teacher.teacherId)}">

                                                            data-score="${cd.score}" 
                                                            data-comment="${cd.comment}" 
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </c:forEach>
                                            >Detail</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!--Grading thesis Modal--> 
            <div class="modal fade" id="assignTopicsModal" tabindex="-1" aria-labelledby="assignTopicsModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="assignTopicsModalLabel">Grading Thesis</h5>
                            <div class="alert" id="success-alert">
                                <strong>Grading Thesis Successfully!</strong>
                            </div>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body row">
                            <form id="assignTopicsForm" action="gradingThesis" method="post">
                                <div class="container" style="display: flex;">
                                    <div class="col" style="border-right: 2px solid rgba(86, 61, 124, .2); padding-right: 20px;">
                                        <input type="hidden" name="thesis_id" id="modalId">
                                        <input type="hidden" name="teacher_id" value="${teacher.teacherId}">
                                        <input type="hidden" name="action" value="gradeThesis">
                                        <div class="form-group">
                                            <label for="modalTitle">Title</label>
                                            <input type="text" class="form-control" id="modalTitle" readonly>
                                            <input type="hidden" class="form-control" id="modalId" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="modalTitle">Student:</label>
                                            <input type="text" class="form-control" id="modalStudentName" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="modalInstructor">Instructor:</label>
                                            <input type="text" class="form-control" id="modalInstructor" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="modalFileName">File:</label>
                                            <a id="fileDownloadLink" href=""><input type="text" class="form-control" name="fileName" id="modalFileName" readonly></a>
                                        </div>
                                    </div>
                                    <div class="col" style="padding-left: 20px;">
                                        <div class="form-group">
                                            <label for="modalTitle">Score</label>
                                            <input type="text" class="form-control" id="modalScore" name="score" required>
                                            <input type="hidden" class="form-control" id="modalId" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="modalComment">Comment:</label>
                                            <textarea name="comment" style="width: 100%; height: 200px;" required></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary" id="submitGrading">Grade</button>
                                    <button type="button" class="close btn btn-secondary">Cancel</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--Detail Modal-->
        <div class="modal fade" id="detailsModal" tabindex="-1" role="dialog" aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="assignTopicsModalLabel">Grading Thesis</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body row">
                        <form id="detailForm">
                            <div class="container" style="display: flex;">
                                <div class="col" style="border-right: 2px solid rgba(86, 61, 124, .2); padding-right: 20px;"
                                     <input type="hidden" name="thesis_id" id="modalId">
                                    <input type="hidden" name="teacher_id" value="${teacher.teacherId}">
                                    <div class="form-group">
                                        <label for="modalTitle">Title</label>
                                        <input type="text" class="form-control" id="titleDetail" readonly>
                                        <!--<input type="hidden" class="form-control" id="modalId" readonly>-->
                                    </div>
                                    <div class="form-group">
                                        <label for="modalTitle">Student:</label>
                                        <input type="text" class="form-control" id="studentDetail" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="modalInstructor">Instructor:</label>
                                        <input type="text" class="form-control" id="instructorDetail" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="modalFileName">File:</label>
                                        <a id="fileDownload" href=""><input type="text" class="form-control" name="fileName" id="fileDetail" readonly></a>
                                    </div>
                                </div>
                                <div class="col" style="padding-left: 20px;">
                                    <div class="form-group">
                                        <label for="modalTitle">Score</label>
                                        <input type="text" class="form-control" id="scoreDetail" name="score" required>
                                        <input type="hidden" class="form-control" id="modalId" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="modalComment">Comment:</label>
                                        <textarea name="comment" style="width: 100%; height: 200px;" id="commentDetail"></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="close btn btn-secondary">Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $('.assignThesis').click(function () {
                // Get data attributes from the button
                var id = $(this).data('committee-id');
                // Set the data into the modal
                $('.committee_id').val(id);
            });

            $('.close').click(function () {
                $('#assignTopicsModal').modal('hide');
                $('#detailsModal').modal('hide');
            });

            $(document).ready(function () {
                $('#submitGrading').click(function (event) {
                    event.preventDefault(); // Prevent the default form submission
                    var form = $('#assignTopicsForm')[0];
                    if (form.checkValidity()) {
                        $('#success-alert').fadeIn().delay(500).fadeOut(function () {
                            form.submit(); // Submit the form after fading out the alert
                        });
                    } else {
                        form.reportValidity(); // Display validation messages
                    }
                });
            });

            $('.view-grading').click(function () {
                // Get data attributes from the button
                var id = $(this).data('id');
                var title = $(this).data('title');
                var instructor = $(this).data('instructor');
                var studentName = $(this).data('student-name');
                var fileName = $(this).data('file-name');
                // Set the data into the modal
                $('#modalId').val(id);
                $('#modalTitle').val(title);
                $('#modalInstructor').val(instructor);
                $('#modalStudentName').val(studentName);
                $('#modalFileName').val(fileName);
                $('#fileDownloadLink').attr('href', 'downloadFile?fileName=' + fileName);
            });

            $('.view-details').click(function () {
                // Get data attributes from the button
                var title = $(this).data('title');
                var instructor = $(this).data('instructor');
                var studentName = $(this).data('student-name');
                var fileName = $(this).data('file-name');
                var score = $(this).data('score');
                var comment = $(this).data('comment');
                // Set the data into the modal
//                $('#modalId').val(id);
                $('#titleDetail').val(title);
                $('#instructorDetail').val(instructor);
                $('#studentDetail').val(studentName);
                $('#fileDetail').val(fileName);
                $('#fileDownload').attr('href', 'downloadFile?fileName=' + fileName);
                $('#scoreDetail').val(score);
                $('#commentDetail').val(comment);
            });
        </script>
    </body>
</html>
