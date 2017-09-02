/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ae2017u1t07.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Enumeration;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis Garnica
 */
@WebServlet(name = "Encabezados", urlPatterns = {"/Encabezados"})
public class Encabezados extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                
                JsonObjectBuilder builder = Json.createObjectBuilder();
                JsonObjectBuilder headers = Json.createObjectBuilder();
                JsonObjectBuilder parameters = Json.createObjectBuilder();
                     
                Enumeration<String> cabeceras = request.getHeaderNames();

                if (cabeceras != null) {
                    while (cabeceras.hasMoreElements()) {
                        String nombreCabecera = cabeceras.nextElement();
                        headers.add(nombreCabecera, request.getHeader(nombreCabecera));
                    }
                }   
                        
                Enumeration<String> parametros = request.getParameterNames();
                if(parametros != null){
                    while(parametros.hasMoreElements()){
                        String nombreParametro = parametros.nextElement();
                        parameters.add(nombreParametro, request.getParameter(nombreParametro));
                    }
                }
                builder.add("Method", request.getMethod());
                builder.add("Headers", headers);
                builder.add("Parameters", parameters);
                
                JsonObject peticion = builder.build();
                
                out.print(peticion);
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
