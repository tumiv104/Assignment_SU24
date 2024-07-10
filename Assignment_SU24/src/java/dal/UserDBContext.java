/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecturer;
import model.Student;
import model.User;

/**
 *
 * @author Nitro
 */
public class UserDBContext extends DBContext<User>{
    
    public User getUserByUsernamePassword(String username, String password) {
        PreparedStatement stm =null;
        User user = null;
        try {
            String sql = "SELECT u.username,l.lid,l.lname, s.sid, s.sname FROM users u \n"
                    + "LEFT JOIN users_lecturers ul ON ul.username = u.username AND ul.active = 1\n"
                    + "LEFT JOIN users_students us ON us.username = u.username AND us.active = 1\n"
                    + "LEFT JOIN lecturers l ON ul.lid = l.lid\n"
                    + "LEFT JOIN students s ON us.sid = s.sid\n"
                    + "WHERE u.username = ? AND u.[password] = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if(rs.next())
            {
                user = new User();
                user.setUsername(username);
                int lid = rs.getInt("lid");
                if(lid!=0)
                {
                   Lecturer lecturer = new Lecturer();
                   lecturer.setId(lid);
                   lecturer.setName(rs.getString("lname"));
                   user.setLecturer(lecturer);
                }
                int sid = rs.getInt("sid");
                if (sid!=0) {
                    Student student = new Student();
                    student.setId(sid);
                    student.setName(rs.getString("sname"));
                    user.setStudent(student);
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }
}
