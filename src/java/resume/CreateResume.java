package resume;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

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
    String username;
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        username = session.getAttribute("userid").toString();
        response.setContentType("application/pdf");
        if (request.getParameter("view") != null) {
            //View is true
            try {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                output = create(request.getParameter("type"), Integer.parseInt(request.getParameter("id")));
                if (request.getParameter("view").equals("download")) {
                    response.addHeader("Content-Type", "application/force-download");
                    response.addHeader("Content-Disposition", "attachment; filename=\"ITResume4UResume.pdf\"");
                }

                response.getOutputStream().write(output.toByteArray());
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CreateResume.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            try {
                
                byte[] bytes;
                
                String type = request.getParameter("type");
                String fname = request.getParameter("FIRSTNAME");
                String lname = request.getParameter("LASTNAME");
                String address = request.getParameter("ADDRESS");
                String city = request.getParameter("CITY");
                String zip = request.getParameter("ZIP");
                String email = request.getParameter("EMAIL");
                String phone = request.getParameter("PHONE");
                String objective = request.getParameter("OBJECTIVE");
                bytes = objective.getBytes(StandardCharsets.ISO_8859_1);
                objective = new String(bytes, StandardCharsets.UTF_8);
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
                bytes = jobDescription.getBytes(StandardCharsets.ISO_8859_1);
                jobDescription = new String(bytes, StandardCharsets.UTF_8);
                
                String jobTitle1 = request.getParameter("JOBTITLE1");
                String employer1 = request.getParameter("EMPLOYER1");
                String jobCity1 = request.getParameter("JOBCITY1");
                String jobState1 = request.getParameter("JOBSTATE1");
                String jobStart1 = request.getParameter("STARTDATE1");
                String jobEnd1 = request.getParameter("ENDDATE1");
                String jobDescription1 = request.getParameter("DESCRIPTION1");
                if (jobDescription1 != null) {
                    bytes = jobDescription1.getBytes(StandardCharsets.ISO_8859_1);
                    jobDescription1 = new String(bytes, StandardCharsets.UTF_8);
                }
                
                
                String jobTitle2 = request.getParameter("JOBTITLE1");
                String employer2 = request.getParameter("EMPLOYER1");
                String jobCity2 = request.getParameter("JOBCITY1");
                String jobState2 = request.getParameter("JOBSTATE1");
                String jobStart2 = request.getParameter("STARTDATE1");
                String jobEnd2 = request.getParameter("ENDDATE1");
                String jobDescription2 = request.getParameter("DESCRIPTION1");
                if (jobDescription2 != null) {
                    bytes = jobDescription2.getBytes(StandardCharsets.ISO_8859_1);
                    jobDescription2 = new String(bytes, StandardCharsets.UTF_8);
                }
                
                String customHeader = request.getParameter("CUSTOMHEADER");
                String customContent = request.getParameter("CUSTOMCONTENT");
                bytes = customContent.getBytes(StandardCharsets.ISO_8859_1);
                customContent = new String(bytes, StandardCharsets.UTF_8);

                Connection con = sqlconnect.getConnection();
                Statement st = con.createStatement();
                //ResultSet rs;

                PreparedStatement stmt = con.prepareStatement("insert into createdResumes("
                        + "fname, username, lname, address, city, zip, email, phone, " //8
                        + "schoolName, schoolLoc, degree, gradDate, gradYear, gpa, type, major, gradeLevel, " //9
                        + "objective, jobTitle1, jobEmployer1, jobCity1, jobState1, jobStart1, jobEnd1, jobDescription1, " //8
                        + "jobTitle2, jobEmployer2, jobCity2, jobState2, jobStart2, jobEnd2, jobDescription2, " //7
                        + "jobTitle3, jobEmployer3, jobCity3, jobState3, jobStart3, jobEnd3, jobDescription3, " //7
                        + " customHeader, customContent) " //2
                        + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); //41
                
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
                    stmt.setString(26, jobTitle1);
                    stmt.setString(27, employer1);
                    stmt.setString(28, jobCity1);
                    stmt.setString(29, jobState1);
                    stmt.setString(30, jobStart1);
                    stmt.setString(31, jobEnd1);
                    stmt.setString(32, jobDescription1);
                    stmt.setString(33, jobTitle2);
                    stmt.setString(34, employer2);
                    stmt.setString(35, jobCity2);
                    stmt.setString(36, jobState2);
                    stmt.setString(37, jobStart2);
                    stmt.setString(38, jobEnd2);
                    stmt.setString(39, jobDescription2);
                    stmt.setString(40, customHeader);
                    stmt.setString(41, customContent);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                stmt.execute();

                ByteArrayOutputStream output = new ByteArrayOutputStream();
                output = create(type, 0);

                response.addHeader("Content-Type", "application/force-download");
                response.addHeader("Content-Disposition", "attachment; filename=\"yourFile.pdf\"");
                response.getOutputStream().write(output.toByteArray());

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    private static PDDocument _pdfDocument;

    public ByteArrayOutputStream create(String type, int id) throws SQLException, ClassNotFoundException {

        //System.out.println("Working Directory = " +
        //System.getProperty("user.dir"));
        String originalPdf = null;
        if (type.equals("Modern")) {
            originalPdf = "http://localhost:8080/Resume-v4/assets/resumes/modern.pdf";
        } else if (type.equals("Traditional")) {
            originalPdf = "http://localhost:8080/Resume-v4/assets/resumes/traditional.pdf";
        }

        //String targetPdf = "G:\\Desktop\\resume22.pdf";
        try {
            return populateAndCopy(originalPdf, type, id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ByteArrayOutputStream populateAndCopy(String originalPdf, String type, int id) throws IOException, SQLException, ClassNotFoundException {

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        _pdfDocument = PDDocument.load(new URL(originalPdf).openStream());
        //InputStream input = new URL("http://www.somewebsite.com/a.txt").openStream();

        _pdfDocument.getNumberOfPages();
            Statement stmt = null;
            String fname = null;
            String lname = null;
            String address = null;
            String city = null;
            String state = null;
            int zip = 0;
            String email = null;
            String phone = null;
            String profession = null;
            String objective = null;
            
            String jobTitle1 = null;
            String jobEmployer1 = null;
            String jobCity1 = null;
            String jobState1 = null;
            Date jobStart1 = null;
            Date jobEnd1 = null;
            String jobDescription1 = null;
            
            String jobTitle2 = null;
            String jobEmployer2 = null;
            String jobCity2 = null;
            String jobState2 = null;
            Date jobStart2 = null;
            Date jobEnd2 = null;
            String jobDescription2 = null;
            
            String jobTitle3 = null;
            String jobEmployer3 = null;
            String jobCity3 = null;
            String jobState3 = null;
            Date jobStart3 = null;
            Date jobEnd3 = null;
            String jobDescription3 = null;
            
            String degree = null;
            String schoolName = null;
            String schoolLoc = null;
            String gradDate = null;
            String gradYear = null;
            String gpa = null;
            String major = null;
            String customHeader = null;
            String customContent = null;
            String query;
            if (id > 0) {
                System.out.println(" query 1!");
                query = "select * from createdResumes WHERE id = " + id + " AND username = '" + username + "' order by id desc limit 1";
                System.out.println(query);
            } else {
                System.out.println(" query 2!");
                query = "select * from createdResumes WHERE username = '" + username + "'  order by id desc limit 1";
            }

        if (type.equals("Modern")) {

            try {
                stmt = sqlconnect.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    fname = rs.getString("fname");
                    lname = rs.getString("lname");
                    address = rs.getString("address");
                    city = rs.getString("city");
                    //state = rs.getString("state");
                    zip = rs.getInt("zip");
                    email = rs.getString("email");
                    phone = rs.getString("phone");
                    profession = rs.getString("major") + " | other data can go here";
                    objective = rs.getString("objective");
                    jobTitle1 = rs.getString("jobTitle1");
                    jobEmployer1 = rs.getString("jobEmployer1");
                    jobCity1 = rs.getString("jobCity1");
                    jobState1 = rs.getString("jobState1");
                    jobStart1 = rs.getDate("jobStart1");
                    jobEnd1 = rs.getDate("jobEnd1");
                    jobDescription1 = rs.getString("jobDescription1");
                    degree = rs.getString("degree");
                    schoolName = rs.getString("schoolName");
                    schoolLoc = rs.getString("schoolLoc");
                    gradYear = rs.getString("gradYear");
                    gpa = String.valueOf(rs.getDouble("gpa"));
                    major = rs.getString("major");
                    customHeader = rs.getString("customHeader");
                    customContent = rs.getString("customContent");
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }

            setField("Name", fname + " " + lname);
            phone = phone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
            setField("Profession", profession + "\n" + address + ", " + city + " - " + phone);
            setField("Objective", objective);
            setField("Experience", jobTitle1.toUpperCase() + " \u2022 " + jobEmployer1.toUpperCase() + " \u2022 " + jobStart1 + " - " + jobEnd1 + "\n" + jobDescription1);
            setField("Skills", "This is my skills! Aint it the neatest? I sure think so. I want to make sure multi-line works fine so i'm going to type a whole bunch of stuff here. ");
            setField("Education", schoolName.toUpperCase() + ", " + schoolLoc.toUpperCase() + " (GPA " + gpa + "/4)\n" + major.toUpperCase() + ", " + gradYear);
            setField("Extra Section Header", customHeader);
            setField("Extra Section", customContent);
            setField("Email", email);
            setField("Phone", phone);
            setField("LinkedIn", "Mark Reddington");
            flatten();
            _pdfDocument.save(output);
            _pdfDocument.close();
            return output;

        } else if (type.equals("Traditional")) {
            
            try {
                stmt = sqlconnect.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    fname = rs.getString("fname");
                    lname = rs.getString("lname");
                    address = rs.getString("address");
                    city = rs.getString("city");
                    //state = rs.getString("state");
                    zip = rs.getInt("zip");
                    email = rs.getString("email");
                    phone = rs.getString("phone");
                    profession = rs.getString("major") + " | other data can go here";
                    objective = rs.getString("objective");
                    jobTitle1 = rs.getString("jobTitle1");
                    jobEmployer1 = rs.getString("jobEmployer1");
                    jobCity1 = rs.getString("jobCity1");
                    jobState1 = rs.getString("jobState1");
                    jobStart1 = rs.getDate("jobStart1");
                    jobEnd1 = rs.getDate("jobEnd1");
                    jobDescription1 = rs.getString("jobDescription1");
                    
                    jobTitle2 = rs.getString("jobTitle2");
                    jobEmployer2 = rs.getString("jobEmployer2");
                    jobCity2 = rs.getString("jobCity2");
                    jobState2 = rs.getString("jobState2");
                    jobStart2 = rs.getDate("jobStart2");
                    jobEnd2 = rs.getDate("jobEnd2");
                    jobDescription2 = rs.getString("jobDescription2");
                    
                    jobTitle3 = rs.getString("jobTitle3");
                    jobEmployer3 = rs.getString("jobEmployer3");
                    jobCity3 = rs.getString("jobCity3");
                    jobState3 = rs.getString("jobState3");
                    jobStart3 = rs.getDate("jobStart3");
                    jobEnd3 = rs.getDate("jobEnd3");
                    jobDescription3 = rs.getString("jobDescription3");
                    
                    degree = rs.getString("degree");
                    schoolName = rs.getString("schoolName");
                    schoolLoc = rs.getString("schoolLoc");
                    gradDate = rs.getString("gradDate");
                    gradYear = rs.getString("gradYear");
                    gpa = String.valueOf(rs.getDouble("gpa"));
                    major = rs.getString("major");
                    customHeader = rs.getString("customHeader");
                    customContent = rs.getString("customContent");
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }

            setField("Name", fname + " " + lname);
            phone = phone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
            setField("Address", address + " | " + city + ", " + state + " " + zip + " | " + phone + " | " + email);
            setField("School", schoolName + ", " + schoolLoc);
            setField("Degree", major + "\n" + "sequence");
            setField("Minor", "Minor: ");
            setField("GPA", "Cumulative GPA: " + gpa + "/4.0");
            setField("Graduation Date", gradDate + " " + gradYear);
            setField("Skills Info", "\u2022      Cobol\n\u2022      Java\n\u2022      C++");
            setField("Experience Info", jobTitle1 + "\n" + jobEmployer1 + ", " + jobState1);
            setField("Experience Details", jobDescription1);
            setField("Experience Date", jobStart1 + " - " + jobEnd1);
            if (jobTitle2 != null) {
                setField("Experience Info 2", jobTitle2 + "\n" + jobEmployer2 + ", " + jobState2);
                setField("Experience Details 2", jobDescription2);
                setField("Experience Date 2", jobStart2 + " - " + jobEnd2);
            }
            if (jobTitle3 != null) {
                setField("Experience Info 3", jobTitle3 + "\n" + jobEmployer3 + ", " + jobState3);
                setField("Experience Details 3", jobDescription3);
                setField("Experience Date 3", jobStart3 + " - " + jobEnd3);
            }
            setField("Activities Details", "\u2022      Details\n\u2022      Details\n\u2022      Details");
            setField("Volunteer", customHeader);
            setField("Volunteer Info", "Company, Town, State");
            setField("Volunteer Details", "\u2022      Details\n\u2022      Details\n\u2022      Details");
            setField("Volunteer Date", "02/02/2018 - 03/03/2018");
            setField("Volunteer Info 2", "Company, Town, State");
            setField("Volunteer Details 2", "\u2022      Details\n\u2022      Details\n\u2022      Details");
            setField("Volunteer Date 2", "02/02/2018 - 03/03/2018");
            
            flatten();
            _pdfDocument.save(output);
            _pdfDocument.close();
            return output;
        } else {
            return null;
        }
    }

    public static void setField(String name, String value) throws IOException {
        PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        PDField field = acroForm.getField(name);

        COSDictionary dict = ((PDField) field).getCOSObject();

        if (field != null) {
            if (name.equals("Objective")) {
                //dict.setString(COSName.DA, "/Montserrat-Bold 11 Tf 0g");
            }
            field.setValue(value);

        } else {
            System.err.println("No field found with name:" + name);
        }
    }

    public static void flatten() throws IOException {
        PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        acroForm.flatten();
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
