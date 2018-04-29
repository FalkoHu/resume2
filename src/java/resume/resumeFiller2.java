package resume;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static javax.servlet.SessionTrackingMode.URL;
import org.apache.pdfbox.cos.COSDictionary;
import static org.apache.pdfbox.cos.COSName.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class resumeFiller2 {

    private static PDDocument _pdfDocument;

    public static ByteArrayOutputStream create(String type, int id) throws SQLException, ClassNotFoundException {

        //System.out.println("Working Directory = " +
        //System.getProperty("user.dir"));
        String originalPdf = null;
        if (type.equals("Modern")) {
            originalPdf = "http://localhost:8080/Resume-v4/assets/resumes/modern.pdf";
        } else if (type.equals("Traditional")) {

        }

        //String targetPdf = "G:\\Desktop\\resume22.pdf";
        try {
            return populateAndCopy(originalPdf, type, id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ByteArrayOutputStream populateAndCopy(String originalPdf, String type, int id) throws IOException, SQLException, ClassNotFoundException {

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        _pdfDocument = PDDocument.load(new URL(originalPdf).openStream());
        //InputStream input = new URL("http://www.somewebsite.com/a.txt").openStream();

        _pdfDocument.getNumberOfPages();

        if (type.equals("Modern")) {

            Statement stmt = null;
            String fname = null;
            String lname = null;
            String profession = null;
            String objective = null;
            String jobTitle1 = null;
            String jobEmployer1 = null;
            Date jobStart1 = null;
            Date jobEnd1 = null;
            String jobDescription1 = null;
            String degree = null;
            String schoolName = null;
            String schoolLoc = null;
            String gradYear = null;
            String gpa = null;
            String major = null;
            
            String query;
            if (id > 0){
                query = "select * from createdResumes WHERE id = " + id + " order by id desc limit 1";
            } else {
                query = "select * from createdResumes order by id desc limit 1";
            }

            //String query = "select * from createdResumes order by id desc limit 1";
            try {
                stmt = sqlconnect.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    fname = rs.getString("fname");
                    lname = rs.getString("lname");
                    profession = rs.getString("major") + " | other data can go here";
                    objective = rs.getString("objective");
                    jobTitle1 = rs.getString("jobTitle1");
                    jobEmployer1 = rs.getString("jobEmployer1");
                    jobStart1 = rs.getDate("jobStart1");
                    jobEnd1 = rs.getDate("jobEnd1");
                    jobDescription1 = rs.getString("jobDescription1");
                    degree = rs.getString("degree");
                    schoolName = rs.getString("schoolName");
                    schoolLoc = rs.getString("schoolLoc");
                    gradYear = rs.getString("gradYear");
                    gpa = String.valueOf(rs.getDouble("gpa"));
                    major = rs.getString("major");
                }
            } catch (SQLException e) {
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }

            setField("Name", fname + " " + lname);
            setField("Profession", profession);
            setField("Objective", objective);
            setField("Experience", jobTitle1.toUpperCase() + " \u2022 " + jobEmployer1.toUpperCase() + " \u2022 " + jobStart1 + " - " + jobEnd1 + "\n" + jobDescription1);
            setField("Skills", "This is my skills! Aint it the neatest? I sure think so. I want to make sure multi-line works fine so i'm going to type a whole bunch of stuff here. ");
            setField("Education", schoolName.toUpperCase() + ", " + schoolLoc.toUpperCase() + " (GPA " + gpa + "/4)\n" + major.toUpperCase() + ", " + gradYear);
            setField("Extra Section Header", "New Header");
            setField("Email", "mreddin@ilstu.edu");
            setField("Phone", "8474128415");
            setField("LinkedIn", "Mark Reddington");
            flatten();
            _pdfDocument.save(output);
            _pdfDocument.close();
            return output;

        } else if (type.equals("Traditional")) {

        } else {
            return null;
        }
        return null;
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

}
