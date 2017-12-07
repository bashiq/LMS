/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import orm.Assignment;
import orm.AssignmentType;

/**
 *
 * @author Damien
 */
public interface AssignmentDao {
    public ArrayList<Assignment> GetAssignments(int CourseID);
    public int CreateAssignment(int courseId, String assignmentName, String assignmentDescription,
            int assignmentTypeId, int isGraded, int potentialScore);
    public ArrayList<AssignmentType> GetAssignmentTypes(int courseId);
    public int CreateAssignmentType(int CourseId, String type);
    public int SubmitAssignment(int assignmentId, int userId, String textSub, 
            int score, int isSubmitted, int isLate);
      public int UpdateAssignment(Assignment at);
}
