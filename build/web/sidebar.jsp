<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="sidebar-height flex-container p-3 bg-white" style="width: 280px; border-right: 1px solid #f7f7f7; box-shadow: 1px 2px 2px rgba(0, 0, 0, 0.3);">
    <ul class="list-unstyled ps-0 side-bar">
        <li class="mb-1">
            <a href = "home">
                <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="false">
                    <i class="bi bi-house"></i> Home
                </button>
            </a>
        </li>
        <!-- Student's view-->
        <c:if test="${sessionScope.account.getRoleId() == 1}">
            <li class="mb-1">
                <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#outline-collapse" aria-expanded="false">
                    <i class="bi bi-paperclip"></i> Thesis
                </button>
                <div class="collapse" id="outline-collapse">
                    <ul class="a btn-toggle-nav list-unstyled fw-normal pb-1 small">
                        <li><a href="registration" class="link-dark rounded">Register</a></li>
                        <li><a href="status" class="link-dark rounded">Status</a></li>
                    </ul>
                </div>
            </li>

            <li class="mb-1">
                <a href = "review">
                    <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="false">
                        <i class="bi bi-graph-down"></i> Reviews
                    </button>
                </a>
            </li>

        </c:if>

        <!-- Teacher's view-->
        <c:if test="${sessionScope.account.getRoleId() == 2}">
            <li class="mb-1">
                <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#outline-collapse" aria-expanded="false">
                    <i class="bi bi-bar-chart"></i> Grading
                </button>

                <div class="collapse" id="outline-collapse">
                    <ul class="a btn-toggle-nav list-unstyled fw-normal pb-1 small">
                        <li><a href="gradingThesis" class="link-dark rounded">Grading thesis</a></li>
                    </ul>
                </div>
            </li>
        </c:if>

        <c:if test="${sessionScope.account.getRoleId() == 3}">
            <li class="mb-1">
                <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#thesis-collapse" aria-expanded="false">
                    <i class="bi bi-bar-chart"></i> Assign
                </button>

                <div class="collapse" id="thesis-collapse">
                    <ul class="a btn-toggle-nav list-unstyled fw-normal pb-1 small">
                        <li><a href="assignThesis" class="link-dark rounded">Assign Thesis</a></li>
                        <li><a href="assignCommittee" class="link-dark rounded">Assign Committee</a></li>
                    </ul>
                </div>
            </li>
        </c:if>
        <li class="border-top my-3"></li>
    </ul>
</div>
<script>
    window.onload = function () {
        var divHeight = window.innerHeight; // Lấy chiều cao của cửa sổ trình duyệt
        var sidebar = document.querySelector('.sidebar-height');
        sidebar.style.height = divHeight + 'px'; // Đặt chiều cao của sidebar bằng chiều cao của trang
    };
</script>