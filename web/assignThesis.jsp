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
        <title>Committee</title>
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
                <h2>Assign Thesis</h2>
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
                                    <c:if test="${c.thesis_id == 0}">
                                        <button class="btn btn-primary assignThesis" data-bs-toggle="modal" data-bs-target="#assignTopicsModal" data-committee-id="${c.committee_id}">Assign</button>
                                    </c:if>
                                    <c:if test="${c.thesis_id != 0}">
                                        <button class="btn btn-primary assignThesis disabled" data-bs-toggle="modal" data-bs-target="#assignTopicsModal" data-committee-id="${c.committee_id}">Assign</button>
                                    </c:if>

                                    <button class="btn btn-success view-details" 
                                            data-bs-toggle="modal" 
                                            data-bs-target="#detailCommitteeModal"
                                            data-thesis-title="${thesisMap[c.thesis_id]}"
                                            <c:forEach var="member" items="${memberCommitteeMap[c.committee_id]}" varStatus="status">
                                                data-member-${status.index + 1}="${member.name}"
                                            </c:forEach>>
                                        Detail
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!--Assign Topics Modal--> 
            <div class="modal fade" id="assignTopicsModal" tabindex="-1" aria-labelledby="assignTopicsModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="assignTopicsModalLabel">Assign Topic</h5>
                            <div class="alert" id="success-alert">
                                <strong>Registered topic successfully!</strong>
                            </div>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="assignTopicsForm" action="assignThesis" method="post">
                                <input type="hidden" class="committee_id" name="committee_id">
                                <input type="hidden" name="action" value="chooseThesis">
                                <div class="mb-3">
                                    <label for="topics" class="form-label">Choose Thesis</label>
                                    <select class="form-select" name="topics" required>
                                        <c:forEach items="${thesisList}" var="thesis">
                                            <c:if test="${thesis.status_id == 1}">
                                                <option value="${thesis.thesisId}">${thesis.title}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary" id="submitAssign">Assign</button>
                                <button type="button" class="close btn btn-secondary">Cancel</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            
            <!--Committee Detail Modal-->
            <div class="modal fade" id="detailCommitteeModal" tabindex="-1" aria-labelledby="detailModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="detailModalLabel">Committee Details</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form>
                                <label for="modalTitle">Tittle Thesis:</label>
                                <input type="hidden" id="modalId" name="committee_id">
                                <input type="text" class="form-control" id="modalTitle">
                                <div class="form-group mb-3">
                                    <label for="modalTitle">Teacher 1:</label>
                                    <input type="text" class="form-control" id="modalMember1">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="modalTitle">Teacher 2:</label>
                                    <input type="text" class="form-control" id="modalMember2">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="modalTitle">Teacher 3:</label>
                                    <input type="text" class="form-control" id="modalMember3">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="close btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
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
                $('#detailCommitteeModal').modal('hide');
            });

            $(document).ready(function () {
                $('#submitAssign').click(function (event) {
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
            $(document).ready(function () {
                $('.view-details').click(function () {
                    // Get data attributes from the button
                    var member1 = $(this).data('member-1');
                    var member2 = $(this).data('member-2');
                    var member3 = $(this).data('member-3');
                    var title = $(this).data('thesis-title');
                    // Set the data into the modal
                    $('#modalMember1').val(member1);
                    $('#modalMember2').val(member2);
                    $('#modalMember3').val(member3);
                    $('#modalTitle').val(title);
                });
            });
        </script>
    </body>
</html>
