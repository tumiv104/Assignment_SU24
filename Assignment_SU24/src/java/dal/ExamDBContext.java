/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assessment;
import model.Exam;

/**
 *
 * @author Nitro
 */
public class ExamDBContext extends DBContext<Exam>{
    
    public ArrayList<Exam> getExamsByEids(int[] eids) {
        ArrayList<Exam> exams = new ArrayList<>();
        String sql = """
                     SELECT e.eid,e.[from],e.duration,a.aid,a.aname,a.weight FROM exams e INNER JOIN assesments a ON a.aid = e.aid
                     WHERE (1 > 2) """;
        for (int eid : eids) {
            sql+= " OR eid = ? ";
        }
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            for (int i = 0; i < eids.length; i++) {
                stm.setInt((i+1), eids[i]);
            }
            
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Assessment a = new Assessment();
                a.setId(rs.getInt("aid"));
                a.setName(rs.getString("aname"));
                a.setWeight(rs.getFloat("weight"));
                
                Exam exam = new Exam();
                exam.setId(rs.getInt("eid"));
                exam.setDate(rs.getTimestamp("from"));
                exam.setDuration(rs.getInt("duration"));
                exam.setAssessment(a);
                
                exams.add(exam);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exams;
        
    }
}
