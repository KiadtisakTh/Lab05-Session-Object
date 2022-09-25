/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BikeInfo;

/**
 *
 * @author Lab247
 */
@WebServlet(name = "AddNewBike_1", urlPatterns = {"/AddNewBike_1"})
public class AddNewBike extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            BikeInfo Info = (BikeInfo) session.getAttribute("Info");
            if (Info == null) {
                String bike = request.getParameter("bike");
                String brand = request.getParameter("brand");
                int weight = Integer.parseInt(request.getParameter("weight"));
                int price = Integer.parseInt(request.getParameter("price"));
                Info = new BikeInfo();
                Info.setBike(bike);
                Info.setBrand(brand);
                Info.setWeight(weight);
                Info.setPrice(price);
                
                session.setAttribute("Info", Info);
                out.println("<html><body>");
                out.println("<form action='AddNewBike'>");
                out.println("ประเภท:  "+Info.getBike() + "<br/>" + "ยี่ห้อ:    "+ Info.getBrand() +  "<br/>" +  " น้ำหนัก:   " +Info.getWeight() + "<br/>" + "ราคา:  "+ Info.getPrice() +"<br/>");
                out.println("<input type='submit' value='แก้ไข'>"+ "<br/>");
                out.println("</form></body></html>");
            } // รับข้อมูลครั้งต่อมาจาก AddNewBike
            else {
                // แสดงข้อมูล Bike เดิม
                out.println("<form action='AddNewBike'>");
                out.println("ประเภท: <input type='text' name='bike' value='"
                        + Info.getBike() + "'>" +  "<br/>");
                out.println("ยี่ห้อ: <input type='text' name='brand' value='"
                        + Info.getBrand() + "'>" +  "<br/>");
                out.println("น้ำหนัก: <input type='text' name='weight' value='"
                        + Info.getWeight() + "'>" +  "<br/>");
                out.println("ราคา: <input type='text' name='price' value='"
                        + Info.getPrice() + "'>" +  "<br/>");
                out.println("<input type='submit' value='ตกลง'>"+ "<br/>");
                out.println("</form></body></html>");

                session.removeAttribute("Info");    
            }

        } catch (Exception e) {
            response.sendRedirect("error.html");
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
