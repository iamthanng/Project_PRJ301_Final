/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DepartmentDAO;
import dal.MajorDAO;
import dal.StudentDAO;
import dal.TeacherDAO;
import dal.ThesisDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import model.Account;
import model.Student;
import model.Teacher;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
public class RegistrationServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        StudentDAO sDao = new StudentDAO();
        TeacherDAO tDao = new TeacherDAO();
        List<Teacher> list = tDao.getAll();
        Student student = sDao.getStudentByID(a.getAccountId());
        request.setAttribute("student", student);
        request.setAttribute("list", list);
        request.getRequestDispatcher("registration.jsp").forward(request, response);
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
        ThesisDAO tDAO = new ThesisDAO();
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int student_id = Integer.parseInt(request.getParameter("studentId"));
        int teacher_id = Integer.parseInt(request.getParameter("teacherId"));
        //File and path
        String folderName = "resources";
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + folderName;
        File dir = new File(uploadPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        Part filePart = request.getPart("file"); //Name file
        String fileName = filePart.getSubmittedFileName(); 
        String filePath = folderName + File.separator + fileName;
        InputStream is = filePart.getInputStream();
        Files.copy(is, Paths.get(uploadPath + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
        
        Timestamp register_date = Timestamp.from(Instant.now());
        
        tDAO.addThesis(title, description, student_id, teacher_id, fileName, filePath, register_date);
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
