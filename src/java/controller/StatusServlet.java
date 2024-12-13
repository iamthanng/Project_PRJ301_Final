/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CommitteeDetailDAO;
import dal.StatusDAO;
import dal.StudentDAO;
import dal.TeacherDAO;
import dal.ThesisDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.CommitteeDetail;
import model.Status;
import model.Student;
import model.Teacher;
import model.Thesis;

/**
 *
 * @author ADMIN
 */
public class StatusServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
        ThesisDAO thesisDao = new ThesisDAO();
        StatusDAO statusDao = new StatusDAO();
        CommitteeDetailDAO cdDao = new CommitteeDetailDAO();
        //List thesis
        List<Thesis> list = thesisDao.getAllThesisById(sDao.getStudentByID(a.getAccountId()).getStudentId());
        for (Thesis thesis : list) {
            if (thesis.getScore() == 0) {
                int count = 0;
                double totalScore = 0;
                List<CommitteeDetail> listC = cdDao.getByThesisId(thesis.getThesisId());
                for (CommitteeDetail committeeDetail : listC) {
                    if (committeeDetail.getScore() != 0) {
                        count++;
                    }
                }
                if (count == 3) {
                    for (CommitteeDetail committeeDetail : listC) {
                        totalScore += committeeDetail.getScore();
                    }
                    thesisDao.totalScore(thesis.getThesisId(), totalScore*1/3);
                }
            } else if(thesis.getScore() >= 5){
                thesisDao.updateStatusThesis(thesis.getThesisId(), 3);
            } else if(thesis.getScore() < 5 && thesis.getScore() > 0){
                thesisDao.updateStatusThesis(thesis.getThesisId(), 4);
            }
        }
        List<Thesis> listNew = thesisDao.getAllThesisById(sDao.getStudentByID(a.getAccountId()).getStudentId());
        request.setAttribute("list", listNew);
        
        //Get Student 
        Student student = sDao.getStudentByID(a.getAccountId());
        request.setAttribute("student", student);
        
        //List Teacher
        List<Teacher> listTeacher = tDao.getAll();
        Map<Integer, String> instructorMap = new HashMap<>();
        for (Teacher teacher : listTeacher) {
            instructorMap.put(teacher.getTeacherId(), teacher.getName());
        }
        request.setAttribute("instructorMap", instructorMap);
        
        //List status
        List<Status> listStatus = statusDao.getAll();
        Map<Integer, String> statusMap = new HashMap<>();
        for (Status status : listStatus) {
            statusMap.put(status.getStatusId(), status.getStatusName());
        }
        request.setAttribute("statusMap", statusMap);
        
        request.getRequestDispatcher("status.jsp").forward(request, response);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
