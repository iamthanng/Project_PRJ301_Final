/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CommitteeDAO;
import dal.CommitteeDetailDAO;
import dal.DepartmentDAO;
import dal.StudentDAO;
import dal.TeacherDAO;
import dal.ThesisDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.Committee;
import model.CommitteeDetail;
import model.Department;
import model.Student;
import model.Taskmaster;
import model.Teacher;
import model.Thesis;

/**
 *
 * @author ADMIN
 */
public class GradingThesisServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        StudentDAO sDao = new StudentDAO();
        TeacherDAO tDao = new TeacherDAO();
        ThesisDAO thesisDao = new ThesisDAO();

        //Get teacher, get thesis
        Teacher teacher = tDao.getTeacherByID(a.getAccountId());
        request.setAttribute("teacher", teacher);

        List<Thesis> thesisList = thesisDao.getThesisGrading(teacher.getTeacherId());
        request.setAttribute("thesisList", thesisList);

        //StudentMap
        List<Student> studentList = sDao.getAll();
        Map<Integer, String> studentMap = new HashMap<>();
        for (Student student : studentList) {
            studentMap.put(student.getStudentId(), student.getName());
        }
        request.setAttribute("studentMap", studentMap);

        //List Teacher
        List<Teacher> listTeacher = tDao.getAll();
        Map<Integer, String> instructorMap = new HashMap<>();
        for (Teacher teacherMap : listTeacher) {
            instructorMap.put(teacherMap.getTeacherId(), teacherMap.getName());
        }
        request.setAttribute("instructorMap", instructorMap);

        //Department List
        DepartmentDAO dpmDao = new DepartmentDAO();
        List<Department> departmentList = dpmDao.getAll();
        request.setAttribute("departmentList", departmentList);
        //DepartmentMap
        Map<Integer, String> deparmentMap = new HashMap<>();
        for (Department department : departmentList) {
            deparmentMap.put(department.getDepartmentId(), department.getName());
        }
        request.setAttribute("deparmentMap", deparmentMap);

        CommitteeDAO c = new CommitteeDAO();
        List<Committee> committeeList = c.getAll();
        request.setAttribute("committeeList", committeeList);

        //Detail score and comment
        CommitteeDetailDAO cd = new CommitteeDetailDAO();
        List<CommitteeDetail> cdList = cd.getAll();
        request.setAttribute("cdList", cdList);
        request.getRequestDispatcher("gradingThesis.jsp").forward(request, response);
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
        int thesis_id = Integer.parseInt(request.getParameter("thesis_id"));
        double score = Double.parseDouble(request.getParameter("score"));
        String comment = request.getParameter("comment");
        int teacher_id = Integer.parseInt(request.getParameter("teacher_id"));
        CommitteeDetailDAO cd = new CommitteeDetailDAO();
        CommitteeDetail committeeDetail = cd.get(thesis_id, teacher_id);
        cd.grading(committeeDetail, score, comment);
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
