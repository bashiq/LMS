/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import orm.Assignment;
import orm.TUser;
import orm.UserAssignment;
import orm.UserAssignmentId;

/**
 * Class that combines Assignment TUser and UserAssignment that can be used in
 * grades
 *
 * @author Bilal
 */
public class TotalAssignment {

//    private int userId;
//    private String username;
//
//    private UserAssignmentId id;
//    private String userAssignmentSubmission;
//    private Integer studentScore;
//    private Integer hasUserSubmitted;
//
//    private int assignmentId;
//    private int courseId;
//    private String assignmentName;
//    private Integer potentialScore;
//    
    private double studentOverallGrade;

    private ArrayList<Assignment> assignment;
    private ArrayList<TAssign> tassign;

    /**
     * Do not use this Constuctor
     */
    public TotalAssignment() {
    }

    /**
     * Use this constructor for best results
     *
     * @param at assignment obj
     * @param tu tUser obj
     * @param ua userassignment obj
     */
    public TotalAssignment(ArrayList<Assignment> at, double studentOverallGrade) {
        assignment = at;
        this.studentOverallGrade = studentOverallGrade;
        // setEverything();
    }

    public void setStudentOverallGrade(double studentOverallGrade) {
        this.studentOverallGrade = studentOverallGrade;
    }

    public void setAssignment(ArrayList<Assignment> assignment) {
        this.assignment = assignment;
    }

    public void setTassign(ArrayList<TAssign> tassign) {
        this.tassign = tassign;
    }
    
    

//    private void setEverything() {
//        userId = tUser.getUserId();
//        username = tUser.getUserName();
//
//        assignmentId = assignment.getAssignmentId();
//        courseId = assignment.getCourseId();
//        assignmentName = assignment.getAssignmentName();
//        potentialScore = assignment.getPotentialScore();
//
//        id = userAssign.getId();
//        userAssignmentSubmission = userAssign.getUserAssignmentSubmission();
//        studentScore = userAssign.getScore();
//        hasUserSubmitted = userAssign.getIsSubmitted();
//    }
    public double getStudentOverallGrade() {
        return studentOverallGrade;
    }

    public ArrayList<Assignment> getAssignment() {
        return assignment;
    }

    public ArrayList<TAssign> getTassign() {
        return tassign;
    }


    

}
