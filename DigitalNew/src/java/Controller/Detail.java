package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DB.DB;
import Model.Digital;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thang
 */
@WebServlet(urlPatterns = {"/detail"})
public class Detail extends HttpServlet {

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
        try {
            DB db = new DB();

            Digital digital = db.getPost();
            String mess = null;

            String idS = request.getParameter("id");
            try {
                if (idS == null) {
                    mess = "No articles found";
                } else { 
                    // check id must be number
                    int id = Integer.parseInt(idS);
                    //countPostById to count post hvae id if count == 0 no post to search
                    // if count == 1 have 1 post 
                    int countPostById = db.countPostById(id);
                    if (countPostById != 0) {
                        digital = db.getPost(id);
                    } else {
                        mess = "No articles found";
                    }
                }
            } catch (Exception e) {
                    mess = "No articles found";
            }
            // send mess to check error search
            request.setAttribute("mess", mess);

            request.setAttribute("digital", digital);
            //get newset post for take short des 
            Digital digitalNew = db.getPost();
            request.setAttribute("digitalNew", digitalNew);
            //get top 5 new post different post newset
            ArrayList<Digital> digitalList = db.getTop5Post();
            request.setAttribute("digitalList", digitalList);
            request.getRequestDispatcher("view/Detail.jsp").forward(request, response);
        } catch (Exception ex) {
            request.getRequestDispatcher("view/Error.jsp").forward(request, response);
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
