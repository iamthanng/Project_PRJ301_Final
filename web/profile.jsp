<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="profile">
    <div class="container">
        <h2>Information</h2>
        <form action="profile" method="post">
            <div class="form-group">
                <label for="fullName">Full Name</label>
                <c:if test="${account.roleId == 1}">
                    <input type="text" id="fullName" name="fullName" value="${student.name}" readonly>
                </c:if>
                <c:if test="${account.roleId == 2}">
                    <input type="text" id="fullName" name="fullName" value="${teacher.name}" readonly>
                </c:if>
                <c:if test="${account.roleId == 3}">
                    <input type="text" id="fullName" name="fullName" value="${taskmaster.name}" readonly>
                </c:if>
                <c:if test="${account.roleId == 4}">
                    <input type="text" id="fullName" name="fullName" value="${admin.name}" readonly>
                </c:if>
            </div>
            <div class="form-group">
                <c:choose>
                    <c:when test="${sessionScope.account.getRoleId() == 1}">
                        <label for="studentId">Student Code</label>
                        <input type="text" id="studentId" name="studentCode" value="${student.student_code}" readonly>
                    </c:when>
                    <c:when test="${sessionScope.account.getRoleId() == 2}">
                        <label for="studentId">Teacher Code</label>
                        <input type="text" id="studentId" name="studentId" value="${teacher.teacher_code}" readonly>
                    </c:when>
                </c:choose>
            </div>
            <div class="form-group">
                <label for="dob">Birth Date:</label>
                <c:choose>
                    <c:when test="${sessionScope.account.getRoleId() == 1}">
                        <input type="date" id="dob" name="dob" value="${student.birthdate}">
                    </c:when>
                    <c:when test="${sessionScope.account.getRoleId() == 2}">
                        <input type="date" id="dob" name="dob" value="${teacher.birthdate}">
                    </c:when>
                    <c:when test="${sessionScope.account.getRoleId() == 3}">
                        <input type="date" id="dob" name="dob" value="${taskmaster.birthdate}">
                    </c:when>
                    <c:when test="${sessionScope.account.getRoleId() == 4}">
                        <input type="date" id="dob" name="dob" value="${admin.birthdate}">
                    </c:when>
                </c:choose>
            </div>
            <div class="form-group">
                <label for="phone">Phone Number</label>
                <c:choose>
                    <c:when test="${sessionScope.account.getRoleId() == 1}">
                        <input type="text" id="phone" name="phone" value="${student.phone}">
                    </c:when>
                    <c:when test="${sessionScope.account.getRoleId() == 2}">
                        <input type="text" id="phone" name="phone" value="${teacher.phone}">
                    </c:when>
                    <c:when test="${sessionScope.account.getRoleId() == 3}">
                        <input type="text" id="phone" name="phone" value="${taskmaster.phone}">
                    </c:when>
                    <c:when test="${sessionScope.account.getRoleId() == 4}">
                        <input type="text" id="phone" name="phone" value="${admin.phone}">
                    </c:when>
                </c:choose>
            </div>

            <c:if test="${account.getRoleId() == 1 or account.getRoleId() == 2}">
                <div class="form-row">
                    <div class="form-group">
                        <label for="departmentCode">Department:</label>
                        <input type="text" id="departmentCode" name="department_id" value="${department.name}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="departmentCode">Major: </label>
                        <input type="text" id="departmentCode" name="departmentCode" value="${major.name}" readonly>
                    </div>
                </div>
            </c:if>
            <div class="form-group submit">
                <input type="submit" value="Save Information">
            </div>
        </form>
    </div>
</div>