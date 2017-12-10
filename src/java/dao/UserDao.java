/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import orm.Course;
import orm.TUser;

/**
 *
 * @author Damien
 */
public interface UserDao {
    public TUser Login(String username, String pass);
    public ArrayList<Course> GetCourses(int id);
    public ArrayList<People> GetPeople(int courseId);
    public ArrayList<TUser> GetPeople2(int courseId);
}
