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
import model.Grade;
import model.Student;

/**
 *
 * @author Nitro
 */
public class GradeDBContext extends DBContext<Grade>{
    
    public ArrayList<Grade> getGradesByEids(int[] eids) {
        ArrayList<Grade> grades = new ArrayList<>();
        String sql = """
                     SELECT eid,sid,score FROM grades WHERE (1 > 2)
                     """;
        for (int eid : eids) {
            sql += " OR eid = ? ";
        }
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            for (int i = 0; i < eids.length; i++) {
                stm.setInt((i + 1), eids[i]);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Exam exam = new Exam();
                exam.setId(rs.getInt("eid"));

                Student s = new Student();
                s.setId(rs.getInt("sid"));

                Grade g = new Grade();
                g.setExam(exam);
                g.setStudent(s);
                g.setScore(rs.getFloat("score"));

                grades.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return grades;
    }
    
    public void saveGradesByCourse(int cid, ArrayList<Grade> grades) {
        PreparedStatement stm_delete = null;
        ArrayList<PreparedStatement> stm_insests = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            
            String sql_remove_records = "DELETE FROM grades WHERE [sid] IN (SELECT [sid] FROM students_courses WHERE cid=?)";
            String sql_insert_grades = "INSERT INTO [grades]\n"
                    + "           ([eid]\n"
                    + "           ,[sid]\n"
                    + "           ,[score])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            
            stm_delete = connection.prepareStatement(sql_remove_records);
            stm_delete.setInt(1, cid);
            stm_delete.executeUpdate();
            
            for (Grade grade : grades) {
                PreparedStatement stm_insert = connection.prepareStatement(sql_insert_grades);
                stm_insert.setInt(1, grade.getExam().getId());
                stm_insert.setInt(2, grade.getStudent().getId());
                stm_insert.setFloat(3, grade.getScore());
                stm_insert.executeUpdate();
                stm_insests.add(stm_insert);
            }
            
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                connection.setAutoCommit(true);
                stm_delete.close();
                for (PreparedStatement stm_insest : stm_insests) {
                    stm_insest.close();
                }
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public ArrayList<Grade> getGradesByEidsSid(int[] eids, int sid) {
        ArrayList<Grade> grades = new ArrayList<>();
        String sql = """
                     SELECT g.eid, g.sid,score, a.aname, a.weight FROM grades g
                     LEFT JOIN exams e ON g.eid = e.eid
                     LEFT JOIN assesments a ON e.aid = a.aid
                     WHERE g.sid = ? AND ((1 > 2)
                     """;
        for (int eid : eids) {
            sql += " OR g.eid = ? ";
        }
        sql +=")";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            for (int i = 0; i < eids.length; i++) {
                stm.setInt((i + 2), eids[i]);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Assessment a = new Assessment();
                a.setName(rs.getString("aname"));
                a.setWeight(rs.getFloat("weight"));
                
                Exam exam = new Exam();
                exam.setId(rs.getInt("eid"));
                exam.setAssessment(a);
                

                Student s = new Student();
                s.setId(rs.getInt("sid"));

                Grade g = new Grade();
                g.setExam(exam);
                g.setStudent(s);
                g.setScore(rs.getFloat("score"));

                grades.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return grades;
    }
}
