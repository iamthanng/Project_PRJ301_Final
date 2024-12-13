/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AdminDAO;
import dal.DepartmentDAO;
import dal.MajorDAO;
import dal.StudentDAO;
import dal.TaskmasterDAO;
import dal.TeacherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Admin;
import model.Department;
import model.Major;
import model.Student;
import model.Taskmaster;
import model.Teacher;

/**
 *
 * @author ADMIN
 */
public class ProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            StudentDAO sDao = new StudentDAO();
            TeacherDAO tDao = new TeacherDAO();
            TaskmasterDAO taskDao = new TaskmasterDAO();
            AdminDAO aDao = new AdminDAO();
            DepartmentDAO dDao = new DepartmentDAO();
            MajorDAO mDao = new MajorDAO();
            switch (a.getRoleId()) {
                case 1:
                    Student student = sDao.getStudentByID(a.getAccountId());
                    request.setAttribute("student", student);
                    Department department = dDao.getDepartmentByAccount(a);
                    Major major = mDao.getMajor(a);
                    request.setAttribute("department", department);
                    request.setAttribute("major", major);
                    break;
                case 2:
                    Teacher teacher = tDao.getTeacherByID(a.getAccountId());
                    request.setAttribute("teacher", teacher);
                    Department department1 = dDao.getDepartmentByAccount(a);
                    Major major1 = mDao.getMajor(a);
                    request.setAttribute("department", department1);
                    request.setAttribute("major", major1);
                    break;
                case 3:
                    Taskmaster taskmaster = taskDao.getTaskMasterByID(a.getAccountId());
                    request.setAttribute("taskmaster", taskmaster);
                    break;
                case 4:
                    Admin admin = aDao.getAdminByID(a.getAccountId());
                    request.setAttribute("admin", admin);
                    break;
            }

            request.getRequestDispatcher("test.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        StudentDAO sDao = new StudentDAO();
        TeacherDAO tDao = new TeacherDAO();
        TaskmasterDAO taskDao = new TaskmasterDAO();
        AdminDAO aDao = new AdminDAO();
        Account a = (Account) session.getAttribute("account");
        String dobInput = request.getParameter("dob");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        LocalDate dob = LocalDate.parse(dobInput);
        String phone = request.getParameter("phone");
        switch (a.getRoleId()) {
            case 1:
                sDao.updateProfileStudent(phone, dob, sDao.getStudentByID(a.getAccountId()).getStudentId());
                break;
            case 2:
                tDao.updateProfileTeacher(phone, dob, tDao.getTeacherByID(a.getAccountId()).getTeacherId());
                break;
            case 3:
                taskDao.updateProfileTask(phone, dob, taskDao.getTaskMasterByID(a.getAccountId()).getTaskmaster_id());
                break;
            case 4:
                aDao.updateProfileAdmin(phone, dob, aDao.getAdminByID(a.getAccountId()).getAdmin_id());
                break;
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
