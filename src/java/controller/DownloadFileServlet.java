/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 *
 * @author ADMIN
 */
public class DownloadFileServlet extends HttpServlet {

    public static int BUFFER_SIZE = 1024 * 100;
    public static final String UPLOAD_DIR = "resources";
    public static String fileName = null;

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DownloadFileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DownloadFileServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        fileName = request.getParameter("fileName");
        if (fileName == null || fileName.equals("")) {
            /**
             * *** Set Response Content Type ****
             */
            response.setContentType("text/html");

            /**
             * *** Print The Response ****
             */
            response.getWriter().println("<h3>File " + fileName + " Is Not Present .....!</h3>");
        } else {
            String applicationPath = getServletContext().getRealPath("");
            String downloadPath = applicationPath + File.separator + UPLOAD_DIR;
            String filePath = downloadPath + File.separator + fileName;
            System.out.println(fileName);
            System.out.println(filePath);
            System.out.println("fileName:" + fileName);
            System.out.println("filePath :" + filePath);
            File file = new File(filePath);
            OutputStream outStream = null;
            FileInputStream inputStream = null;

            if (file.exists()) {

                /**
                 * ** Setting The Content Attributes For The Response Object
                 * ***
                 */
                String mimeType = "application/octet-stream";
                response.setContentType(mimeType);

                /**
                 * ** Setting The Headers For The Response Object ***
                 */
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
                response.setHeader(headerKey, headerValue);

                try {

                    /**
                     * ** Get The Output Stream Of The Response ***
                     */
                    outStream = response.getOutputStream();
                    inputStream = new FileInputStream(file);
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead = -1;

                    /**
                     * ** Write Each Byte Of Data Read From The Input Stream
                     * Write Each Byte Of Data Read From The Input Stream Into
                     * The Output Stream ***
                     */
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }
                } catch (IOException ioExObj) {
                    System.out.println("Exception While Performing The I/O Operation?= " + ioExObj.getMessage());
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }

                    outStream.flush();
                    if (outStream != null) {
                        outStream.close();
                    }
                }
            } else {

                /**
                 * *** Set Response Content Type ****
                 */
                response.setContentType("text/html");

                /**
                 * *** Print The Response ****
                 */
                response.getWriter().println("<h3>File " + fileName + " Is Not Present .....!</h3>");
            }

        }

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
