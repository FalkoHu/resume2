package resume;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import resume.resumeFiller2;
import resume.sqlconnect;

/**
 *
 * @author markm
 */
@WebServlet(urlPatterns = {"/CreateResume"})
public class CreateResume extends HttpServlet {

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
        response.setContentType("application/pdf");
        if (request.getParameter("view") != null){
            //View is true
            try {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                output = resumeFiller2.create(request.getParameter("type"), Integer.parseInt(request.getParameter("id")));
                if (request.getParameter("view").equals("download")) {
                    response.addHeader("Content-Type", "application/force-download");
                    response.addHeader("Content-Disposition", "attachment; filename=\"ITResume4UResume.pdf\"");
                }
               
                response.getOutputStream().write(output.toByteArray());
            }  catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CreateResume.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
            try {
                HttpSession session = request.getSession();
                session.getAttribute("userid").toString();
            String username = request.getParameter("username");
            String type = request.getParameter("type");
            String fname = request.getParameter("FIRSTNAME");
            String lname = request.getParameter("LASTNAME");
            String address = request.getParameter("ADDRESS");
            String city = request.getParameter("CITY");
            String zip = request.getParameter("ZIP");
            String email = request.getParameter("EMAIL");
            String phone = request.getParameter("PHONE");
            String objective = request.getParameter("OBJECTIVE");
            String schoolName = request.getParameter("SCHOOLNAME");
            String schoolLoc = request.getParameter("SCHOOLLOC");
            String degree = request.getParameter("DEGREE");
            String major = request.getParameter("MAJOR");
            String gradeLevel = request.getParameter("YEAR");
            String gradDate = request.getParameter("GRADUATIONDATE");
            String gradYear = request.getParameter("GRADUATIONYEAR");
            String gpa = request.getParameter("GPA");

            String course168 = request.getParameter("168");
            String course261 = request.getParameter("261");
            String course262 = request.getParameter("262");
            String courseCobol = request.getParameter("cobol");
            String course363 = request.getParameter("363");
            String course378 = request.getParameter("378");
            String courseAcc260 = request.getParameter("ACC260");
            String courseAcc261 = request.getParameter("ACC261");
            String courseAcc362 = request.getParameter("ACC362");
            String courseAcc366 = request.getParameter("366");
            String course368 = request.getParameter("ACC368");

            String javaSkill = request.getParameter("javaSkill");
            String sqlSkill = request.getParameter("sqlSkill");
            String frontEndSkill = request.getParameter("frontEndSkill");
            String cobolSkill = request.getParameter("cobolSkill");
            String umlSkill = request.getParameter("umlSkill");
            String projectManagementSkill = request.getParameter("projectManagementSkill");

            String visualBasicSkill = request.getParameter("visualBasicSkill");
            String sdlcSkill = request.getParameter("sdlcSkill");
            String agileSkill = request.getParameter("agileSkill");

            String jobTitle = request.getParameter("JOBTITLE");
            String employer = request.getParameter("EMPLOYER");
            String jobCity = request.getParameter("JOBCITY");
            String jobState = request.getParameter("JOBSTATE");
            String jobStart = request.getParameter("STARTDATE");
            String jobEnd = request.getParameter("ENDDATE");
            String jobDescription = request.getParameter("DESCRIPTION");

            Connection con = sqlconnect.getConnection();
            Statement st = con.createStatement();
            //ResultSet rs;

            PreparedStatement stmt = con.prepareStatement("insert into createdResumes(fname, username, lname, address, city, zip, email, phone, schoolName, schoolLoc, degree, gradDate, gradYear, gpa, type, major, gradeLevel, objective, jobTitle1, jobEmployer1, jobCity1, jobState1, jobStart1, jobEnd1, jobDescription1) "
                    + "                                                        values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            try {
                stmt.setString(1, fname);//1 specifies the first parameter in the query  
                stmt.setString(2, username);//1 specifies the first parameter in the query  
                stmt.setString(3, lname);
                stmt.setString(4, address);
                stmt.setString(5, city);
                stmt.setInt(6, Integer.parseInt(zip));
                stmt.setString(7, email);
                stmt.setString(8, phone);
                stmt.setString(9, schoolName);
                stmt.setString(10, schoolLoc);
                stmt.setString(11, degree);
                stmt.setString(12, gradDate);
                stmt.setInt(13, Integer.parseInt(gradYear));
                stmt.setDouble(14, Double.parseDouble(gpa));
                stmt.setString(15, type);
                stmt.setString(16, major);
                stmt.setString(17, gradeLevel);
                stmt.setString(18, objective);
                stmt.setString(19, jobTitle);
                stmt.setString(20, employer);
                stmt.setString(21, jobCity);
                stmt.setString(22, jobState);
                stmt.setString(23, jobStart);
                stmt.setString(24, jobEnd);
                stmt.setString(25, jobDescription);
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            stmt.execute();

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            output = resumeFiller2.create(type,0);

            response.addHeader("Content-Type", "application/force-download");
            response.addHeader("Content-Disposition", "attachment; filename=\"yourFile.pdf\"");
            response.getOutputStream().write(output.toByteArray());
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
            
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
