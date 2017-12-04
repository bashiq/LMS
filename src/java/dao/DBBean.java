/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import orm.Assignment;
import orm.Course;
import orm.TUser;
import orm.AssignmentType;
import orm.UserAssignment;
import java.util.ArrayList;

/**
 *
 * @author Damien
 */
public interface DBBean {
    public TUser Login(String username, String pass);
    public ArrayList<Course> GetCourses(int id);
    public ArrayList<People> GetPeople(int courseId);
    public ArrayList<Assignment> GetAssignments(int CourseID);
    public int CreateAssignment(int courseId, String assignmentName, String assignmentDescription,
            int assignmentTypeId, int isGraded, int potentialScore);
    public ArrayList<AssignmentType> GetAssignmentTypes(int courseId);
    public int CreateAssignmentType(int CourseId, String type);
    public int SubmitAssignment(int assignmentId, int userId, String textSub, 
            int score, int isSubmitted, int isLate);
    public int GradeAssignment(UserAssignment ua);
    public ArrayList<UserAssignment> GetGrades(int userID, int courseID, 
            ArrayList<Assignment> at);
    public ArrayList<UserAssignment> GetGrades(int CourseID);
}
