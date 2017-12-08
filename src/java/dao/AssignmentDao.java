/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import orm.Assignment;
import orm.AssignmentType;
import orm.UserAssignment;

/**
 *
 * @author Damien
 */
public interface AssignmentDao {
    public ArrayList<Assignment> GetAssignments(int CourseID);
    public int CreateAssignment(Assignment at);
    public ArrayList<AssignmentType> GetAssignmentTypes(int courseId);
    public int CreateAssignmentType(int CourseId, String type);
    public int SubmitAssignment(UserAssignment ua);
    public int UpdateAssignment(Assignment at);
    public int CreateUserAssignments(UserAssignment ua);
}
