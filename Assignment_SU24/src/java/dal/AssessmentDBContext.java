 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Assessment;
import model.Exam;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Subject;

/**
 *
 * @author Nitro
 */
public class AssessmentDBContext extends DBContext<Assessment>{
    public ArrayList<Exam> getRelatedExams(int cid) {
        PreparedStatement stm = null;
        ArrayList<Exam> exams = new ArrayList<>();
        try {
            String sql = "SELECT a.aid, a.aname, a.weight,su.subid, su.subname,e.eid, e.[from], e.duration FROM exams e\n"
                    + "INNER JOIN assesments a ON e.aid = a.aid\n"
                    + "INNER JOIN subjects su ON a.subid = su.subid\n"
                    + "INNER JOIN courses c ON su.subid = c.subid\n"
                    + "WHERE c.cid = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Assessment a = new Assessment();
                a.setId(rs.getInt("aid"));
                a.setName(rs.getString("aname"));
                a.setWeight(rs.getFloat("weight"));
                
                Subject sub = new Subject();
                sub.setId(rs.getInt("subid"));
                sub.setName(rs.getString("subname"));
                a.setSubject(sub);
                
                Exam e = new Exam();
                e.setId(rs.getInt("eid"));
                e.setDate(rs.getDate("from"));
                e.setDuration(rs.getInt("duration"));
                e.setAssessment(a);
                
                exams.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssessmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssessmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exams;
    }
}
