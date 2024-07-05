/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Course;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecturer;
import model.Semester;
import model.Subject;

/**
 *
 * @author Nitro
 */
public class CourseDBContext extends DBContext<Course>{
    public ArrayList<Course> filterByLecturerID(int lid) {
        ArrayList<Course> courses = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT c.cid, c.cname, c.lid, c.subid, sub.subname, sem.semid FROM courses c\n"
                    + "INNER JOIN semester sem ON c.semid = sem.semid\n"
                    + "INNER JOIN subjects sub ON c.subid = sub.subid\n"
                    + "WHERE sem.active = 1 AND c.lid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Course c = new Course();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                
                Lecturer l = new Lecturer();
                l.setId(rs.getInt("lid"));
                c.setLecturer(l);
                
                Subject s = new Subject();
                s.setId(rs.getInt("subid"));
                s.setName(rs.getString("subname"));
                c.setSubject(s);
                
                Semester sem = new Semester();
                sem.setId(rs.getInt("semid"));
                c.setSemester(sem);
                
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses;
    }
}
