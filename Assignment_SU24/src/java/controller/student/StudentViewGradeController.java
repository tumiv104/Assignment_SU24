/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.student;

import controller.auth.BaseRequiredStudentAuthenticationController;
import dal.AssessmentDBContext;
import dal.GradeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Exam;
import model.Grade;
import model.Student;
import model.User;

/**
 *
 * @author Nitro
 */
public class StudentViewGradeController extends BaseRequiredStudentAuthenticationController {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user, Student student)
    throws ServletException, IOException {
        AssessmentDBContext adb = new AssessmentDBContext();
        int cid = Integer.parseInt(request.getParameter("cid"));
        ArrayList<Exam> exams = adb.getRelatedExams(cid);
        int[] eids = new int[exams.size()];
        int i = 0;
        for (Exam exam : exams) {
            eids[i] = exam.getId();
            i++;
        }
        GradeDBContext gdb = new GradeDBContext();
        ArrayList<Grade> grades = gdb.getGradesByEidsSid(eids, student.getId());
        String status = "";
        if (grades.size() < exams.size()) {
            status = "Studying";
        }
        if (!status.equals("Studying")) {
            status = "Failed";
            boolean check = true;
            for (int j = 0; j < grades.size(); j++) {
                Grade get = grades.get(j);
                if (get.getScore() == 0) {
                    check = false;
                    break;
                }
                if (get.getExam().getAssessment().getName().equals("Final Exam")) {
                    if (get.getScore() < 4) {
                        check = false;
                        break;
                    }
                }
            }
            float avg = 0;
            for (int j = 0; j < grades.size(); j++) {
                Grade get = grades.get(j);
                avg += get.getScore() * (get.getExam().getAssessment().getWeight());
            }
            if (avg >= 5 && check) {
                status = "Passed";
            }
            request.setAttribute("avg", avg);
        }
        request.setAttribute("status", status);
        request.setAttribute("exams", exams);
        request.setAttribute("grades", grades);
        request.getRequestDispatcher("view/student/grade.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user, Student student)
    throws ServletException, IOException {
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
