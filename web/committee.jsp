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
        <title>List of Committee</title>
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

            .form-row {
                display: flex;
                justify-content: space-around;
            }
            .form-group {
                width: 45%;
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
                <h2>List of Committee</h2>
                <form action="assignCommittee" method="post">
                    <input type="hidden" name="action" value="addCommittee">
                    <button type="submit" class="btn btn-success mb-3">
                        <i class="bi bi-person-plus-fill"></i> Add Committee
                    </button>
                </form>

                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Committee</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.committeeList}" var="c">
                            <tr>
                                <td>${c.name}</td>
                                <td>
                                    <button class="btn btn-primary add-member" data-bs-toggle="modal" data-bs-target="#addCouncilModal" data-committee-id="${c.committee_id}">Assign
                                    </button>
                                    <button class="btn btn-warning edit-member" 
                                            data-bs-toggle="modal" 
                                            data-bs-target="#editCouncilModal"
                                            data-committee-id="${c.committee_id}"
                                            <c:forEach var="member" items="${memberCommitteeMap[c.committee_id]}" varStatus="status">
                                                data-member-${status.index + 1}="${member.name}"
                                                data-teacher-id-${status.index + 1}="${member.teacherId}"
                                            </c:forEach>>
                                        Edit
                                    </button>
                                    <button class="btn btn-danger remove-committee" data-bs-toggle="modal" data-bs-target="#removeCommittee" data-committee-id="${c.committee_id}">Remove</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!--Thêm hội đồng Modal--> 
            <div class="modal fade" id="addCouncilModal" tabindex="-1" aria-labelledby="addCouncilModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addCouncilModalLabel">Add Member</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="addMember" action="assignCommittee" method="post">
                                <!--action add member-->
                                <input type="hidden" name="action" value="addMember">
                                <!-- committee Id-->
                                <input type="hidden" class="committeeId" name="committeeId">
                                <div class="mb-3">
                                    <label class="form-label">Teacher 1:</label>
                                    <select class="form-select" id="addTeacher1" name="teacher1" required>
                                        <c:forEach items="${listTeacher}" var="teacher">
                                            <option value="${teacher.teacherId}">${teacher.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Teacher 2:</label>
                                    <select class="form-select" id="addTeacher2" name="teacher2" required>
                                        <c:forEach items="${listTeacher}" var="teacher">
                                            <option value="${teacher.teacherId}">${teacher.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Teacher 3:</label>
                                    <select class="form-select" id="addTeacher3" name="teacher3" required>
                                        <c:forEach items="${listTeacher}" var="teacher">
                                            <option value="${teacher.teacherId}">${teacher.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div id="add-error-message" class="text-danger" style="display:none;">Teachers must be different from each other.</div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">Add</button>
                                    <button type="button" class="close btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!--Chỉnh sửa hội đồng-->
            <div class="modal fade" id="editCouncilModal" tabindex="-1" aria-labelledby="editCouncilModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addCouncilModalLabel">Edit Member</h5>
                            <div class="alert" id="success-alert">
                                <strong>Edit committee successfully!</strong>
                            </div>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="editMemberForm" action="assignCommittee" method="post">
                                <!--action add member-->
                                <input type="hidden" name="action" value="editCommittee">
                                <!-- committee Id-->
                                <input type="hidden" id="edit-committeeId" name="committeeId">
                                <div class="form-row">
                                    <div class="form-group mb-3" style="width: 45%">
                                        <label class="form-label">Teacher 1:</label>
                                        <input type="hidden" name="teacherOld1" id="teacherOld1">
                                        <input type="text" class="form-control" id="modalMember1" name="teacherOld1">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label class="form-label">Teacher 1:</label>
                                        <select class="form-select" id="editTeacher1" name="teacher1" required>
                                            <c:forEach items="${listTeacher}" var="teacher">
                                                <option value="${teacher.teacherId}">${teacher.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group mb-3" style="width: 45%">
                                        <label class="form-label">Teacher 2:</label>
                                        <input type="hidden" name="teacherOld2" id="teacherOld2">
                                        <input type="text" class="form-control" id="modalMember2" name="teacherOld2">
                                    </div>
                                    <div class="form-group mb-3" style="width: 45%">
                                        <label class="form-label">Teacher 2:</label>
                                        <select class="form-select" id="editTeacher2" name="teacher2" required>
                                            <c:forEach items="${listTeacher}" var="teacher">
                                                <option value="${teacher.teacherId}">${teacher.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group mb-3" >
                                        <label class="form-label">Teacher 3:</label>
                                        <input type="hidden" name="teacherOld3" id="teacherOld3">
                                        <input type="text" class="form-control" id="modalMember3" name="teacherOld3">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label class="form-label">Teacher 3:</label>
                                        <select class="form-select" id="editTeacher3" name="teacher3" required>
                                            <c:forEach items="${listTeacher}" var="teacher">
                                                <option value="${teacher.teacherId}">${teacher.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div id="edit-error-message" class="text-danger" style="display:none;">Teachers must be different from each other.</div>
                                <div class="modal-footer">
                                    <button type="submit" id="submitEdit" class="btn btn-primary">Change</button>
                                    <button type="button" class="close btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!--Hủy hội đồng-->
            <div class="modal fade" id="removeCommittee" tabindex="-1" aria-labelledby="addCouncilModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editCouncilModalLabel">Edit Committee</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="assignCommittee" method="post">
                                <!--action add member-->
                                <input type="hidden" name="action" value="removeCommittee">
                                <!-- committee Id-->
                                <input type="hidden" class="committeeId" name="committeeId">
                                <p>Are you sure to remove this committee? </p>
                                <button type="submit" class="btn btn-danger">Remove</button>
                                <button type="button" class="close btn btn-secondary" data-dismiss="modal">Close</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <script>
            document.getElementById('addMember').addEventListener('submit', function (event) {
                var teacher1 = document.getElementById('addTeacher1').value;
                var teacher2 = document.getElementById('addTeacher2').value;
                var teacher3 = document.getElementById('addTeacher3').value;

                if (teacher1 === teacher2 || teacher1 === teacher3 || teacher2 === teacher3) {
                    event.preventDefault();
                    document.getElementById('add-error-message').style.display = 'block';
                } else {
                    document.getElementById('add-error-message').style.display = 'none';
                }
            });
            document.getElementById('editMemberForm').addEventListener('submit', function (event) {
                var teacher1 = document.getElementById('editTeacher1').value;
                var teacher2 = document.getElementById('editTeacher2').value;
                var teacher3 = document.getElementById('editTeacher3').value;
                if (teacher1 === teacher2 || teacher1 === teacher3 || teacher2 === teacher3) {
                    event.preventDefault();
                    document.getElementById('edit-error-message').style.display = 'block';
                } else {
                    $('#success-alert').fadeIn().delay(1000);
                    document.getElementById('edit-error-message').style.display = 'none';
                }
            });
            $(document).ready(function () {
                $('.add-member').click(function () {
                    // Get data attributes from the button
                    var id = $(this).data('committee-id');
                    // Set the data into the modal
                    $('.committeeId').val(id);
                });
                $('.edit-member').click(function () {
                    // Get data attributes from the button
                    var member1 = $(this).data('member-1');
                    var member2 = $(this).data('member-2');
                    var member3 = $(this).data('member-3');
                    var member_id1 = $(this).data('teacher-id-1');
                    var member_id2 = $(this).data('teacher-id-2');
                    var member_id3 = $(this).data('teacher-id-3');
                    var id = $(this).data('committee-id');
                    // Set the data into the modal
                    $('#modalMember1').val(member1);
                    $('#modalMember2').val(member2);
                    $('#modalMember3').val(member3);
                    $('#teacherOld1').val(member_id1);
                    $('#teacherOld2').val(member_id2);
                    $('#teacherOld3').val(member_id3);
                    $('#edit-committeeId').val(id);
                });
                $('.remove-committee').click(function () {
                    // Get data attributes from the button
                    var id = $(this).data('committee-id');
                    // Set the data into the modal
                    $('.committeeId').val(id);
                });
            });
            $('.close').click(function () {
                $('#removeCommittee').modal('hide');
                $('#editCouncilModal').modal('hide');
            });
        </script>
    </body>
</html>
