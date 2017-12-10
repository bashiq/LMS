/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import orm.Assignment;
import orm.UserAssignment;

/**
 *
 * @author Damien
 */
public interface GradeDao {
    public int GradeAssignment(UserAssignment ua);
    public UserAssignment GetGrade(int userID, int courseID, int assignmentID);
    public ArrayList<UserAssignment> GetGrades(int CourseID);
}
