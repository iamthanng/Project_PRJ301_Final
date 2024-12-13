/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CommitteeDAO;
import dal.CommitteeDetailDAO;
import dal.DepartmentDAO;
import dal.StudentDAO;
import dal.TaskmasterDAO;
import dal.TeacherDAO;
import dal.ThesisDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.Committee;
import model.Department;
import model.Taskmaster;
import model.Teacher;
import model.Thesis;

/**
 *
 * @author ADMIN
 */
public class AssignThesisServlet extends HttpServlet {

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
        ThesisDAO thesisDao = new ThesisDAO();

        TaskmasterDAO taskDao = new TaskmasterDAO();
        Taskmaster taskmaster = taskDao.getTaskMasterByID(a.getAccountId());
        request.setAttribute("taskmaster", taskmaster);

        List<Thesis> thesisList = thesisDao.getAllThesis();
        request.setAttribute("thesisList", thesisList);

        //List Teacher
        List<Teacher> listTeacher = tDao.getAll();
        request.setAttribute("listTeacher", listTeacher);

        //Department List
        DepartmentDAO dpmDao = new DepartmentDAO();
        List<Department> departmentList = dpmDao.getAll();

        //DepartmentMap
        Map<Integer, String> deparmentMap = new HashMap<>();
        for (Department department : departmentList) {
            deparmentMap.put(department.getDepartmentId(), department.getName());
        }
        request.setAttribute("deparmentMap", deparmentMap);
        request.setAttribute("departmentList", departmentList);

        //List committee
        CommitteeDAO c = new CommitteeDAO();
        List<Committee> committeeList = c.getAll();
        request.setAttribute("committeeList", committeeList);
        
        //ThesisMap
        Map<Integer, String> thesisMap = new HashMap();
        for (Thesis thesis : thesisList) {
            for(Committee committee : committeeList){
                if(thesis.getThesisId() == committee.getThesis_id()){
                    thesisMap.put(committee.getThesis_id(), thesis.getTitle());
                }
            }
        }
        request.setAttribute("thesisMap", thesisMap);

        Map<Integer, List<Teacher>> memberCommitteeMap = new HashMap<>();
        for(Committee committee: committeeList){
            List<Teacher> listMember = tDao.getTeacherByCommittee(committee.getCommittee_id());
            memberCommitteeMap.put(committee.getCommittee_id(), listMember);
        }
        request.setAttribute("memberCommitteeMap", memberCommitteeMap);
        
        request.getRequestDispatcher("assignThesis.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        CommitteeDAO cDAO = new CommitteeDAO();
        CommitteeDetailDAO cdDAO = new CommitteeDetailDAO();
        ThesisDAO tDAO = new ThesisDAO();
        switch (action) {
            case "chooseThesis":
                int thesisId = Integer.parseInt(request.getParameter("topics"));
                int committee_id = Integer.parseInt(request.getParameter("committee_id"));
                cDAO.assignThesis(committee_id, thesisId);
                tDAO.updateStatusThesis(thesisId, 2);
                break;
            case "addMember": {
                int teacher1_id = Integer.parseInt(request.getParameter("teacher1"));
                int teacher2_id = Integer.parseInt(request.getParameter("teacher2"));
                int teacher3_id = Integer.parseInt(request.getParameter("teacher3"));
                int committeeId = Integer.parseInt(request.getParameter("committeeId"));
                cdDAO.addMember(committeeId, teacher1_id);
                cdDAO.addMember(committeeId, teacher2_id);
                cdDAO.addMember(committeeId, teacher3_id);
                break;
            }
            case "removeCommittee": {
                int committeeId = Integer.parseInt(request.getParameter("committeeId"));
                cDAO.removeCommittee(committeeId);
                break;
            }
            default:
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
