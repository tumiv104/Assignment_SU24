/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecturer;
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
            String sql = "SELECT u.username,u.displayname,l.lid,l.lname\n"
                    + "FROM users u LEFT JOIN users_lecturers ul ON ul.username = u.username AND ul.active = 1\n"
                    + "						LEFT JOIN lecturers l ON ul.lid = l.lid\n"
                    + "						WHERE u.username = ? AND u.[password] = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if(rs.next())
            {
                user = new User();
                user.setDisplayname(rs.getString("displayname"));
                user.setUsername(username);
                int lid = rs.getInt("lid");
                if(lid!=0)
                {
                   Lecturer lecturer = new Lecturer();
                   lecturer.setId(lid);
                   lecturer.setName(rs.getString("lname"));
                   user.setLecturer(lecturer);
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
