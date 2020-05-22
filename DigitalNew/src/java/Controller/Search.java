/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DB.DB;
import Model.Digital;
import java.io.IOException;
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
@WebServlet(name = "Search", urlPatterns = {"/search"})
public class Search extends HttpServlet {

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
            //pagesize is number post in paging 
            int pagesize = 3;
            
            String error = null;
            DB db = new DB();
            String title = request.getParameter("title");
            // check search have any post// title don't need to check null 
            //because title have been send to parameter 
            if (title == null || title.equals("")) {
                error = "page not found";
            }
            // count post by title
            int count = db.pageCount(title);
            if (count == 0) {
                error = "page not found";
            }
            // count page (if number post search by title devide for pagesize  == 0 don't need add 1 page )
            int pagecount = (count % pagesize == 0) ? count / pagesize : (count / pagesize) + 1;
            //check pageNumber null -> pageNumber is first page
            String pageNumber = request.getParameter("page");
            // pageindex is page now
            
            int padeindex = 1;
            try {
                //check pageNumber null -> pageNumber is first page
                if (pageNumber == null) {
                    pageNumber = "1";
                }
                
                padeindex = Integer.parseInt(pageNumber);
                // if page now > number of all page  => page not found
                if (padeindex > pagecount) {
                    error = "page not found";
                }
            } catch (Exception e) {
                error = "page not found";
            }
            // get All post have information of title , pageindex , page size
            ArrayList<Digital> digitalListPaging = db.getAllPost(title, padeindex, pagesize);
            request.setAttribute("digitalListPaging", digitalListPaging);
            // get newest post to take short des
            Digital digitalNew = db.getPost();
            request.setAttribute("digitalNew", digitalNew);
            // get top 5 newpost different newest post
            ArrayList<Digital> digitalList = db.getTop5Post();
            request.setAttribute("digitalList", digitalList);
            request.setAttribute("pageindex", padeindex);
            request.setAttribute("pagecount", pagecount);
            request.setAttribute("title", title);
            request.setAttribute("error", error);
            request.getRequestDispatcher("view/Search.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("view/Error.jsp");
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
