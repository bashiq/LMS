/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import orm.Course;
import orm.TUser;
import orm.UserCourse;
import orm.UserCourseId;

/**
 *
 * @author Damien
 */
public class UserDaoImpl implements UserDao{
    private SessionFactory factory;
    
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
    
    /**
     * method to log people in
     *
     * @param username
     * @param email for user 
     * @param pass password of user
     * @return -1 if invalid username or pass word else returns TUser object
     * containing role and userID
     */
    @Override
    public TUser Login(String username, String pass) {
        Session session = factory.openSession();
        Transaction tx = null;
        TUser returnUser = new TUser(), tempuser = new TUser();
        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM TUser").list();
            for (Iterator iterator = users.iterator(); iterator.hasNext();) {
                tempuser = (TUser) iterator.next();

                if (tempuser.getUserName().equals(username) && tempuser.getPassword().equals(pass)) {
                    returnUser = tempuser;
                } else {
                    returnUser.setUserId(-1);
                    returnUser.setRoleId(-1);
                }
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            return returnUser;
        }
    }

    /**
     * When a user successfully logs in the controller will retrieve which courses
     * they are enrolled in
     *
     * @param id userid
     * @return arraylist of courses
     */
    @Override
    public ArrayList<Course> GetCourses(int id) {
        ArrayList<Course> retCourse = new ArrayList<Course>();
        Session session = factory.openSession();
        Transaction tx = null;
        Course tcourse = new Course();

        try {
            tx = session.beginTransaction();
            List courses = session.createQuery("FROM Course").list();
            List userCourses = session.createQuery("FROM UserCourse").list();
            //Query query = session.createQuery("from UserCourse where UserId = :code ");
            //query.setParameter("code", 1);
            //List userCourses = query.list();
            for (int i = 0; i < userCourses.size(); i++) {
                UserCourse tuc = (UserCourse) userCourses.get(i);
                UserCourseId tuci = tuc.getId();
                System.out.println("hello");
                for (Iterator iterator2 = courses.iterator(); iterator2.hasNext();) {
                    Course tc = (Course) iterator2.next();
                    if (id == tuci.getUserId()) {
                        System.out.println(tc.getCourseName());
                        retCourse.add(tc);
                    }
                }
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            return retCourse;
        }

    }

    /**
     * Returns all of the people enrolled in the course
     *
     * @param courseId course id of what your looking for
     * @return array list of class people
     */
    @Override
    public ArrayList<People> GetPeople(int courseId) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList<Integer> pid = new ArrayList<Integer>();
        String rank = "Student";
        ArrayList<People> ret = new ArrayList<People>();

        try {
            tx = session.beginTransaction();
            List people = session.createQuery("FROM UserCourse").list();

            for (int i = 0; i < people.size(); i++) {
                UserCourse uc = (UserCourse) people.get(i);
                if (uc.getId().getUserId() == courseId) {
                    pid.add(uc.getId().getUserId());
                }
            }

            List tUsers = session.createQuery("FROM TUser").list();
            for (int i = 0; i < tUsers.size(); i++) {
                TUser temp = (TUser) tUsers.get(i);

                for (int j = 0; j < pid.size(); j++) {
                    if (temp.getUserId() == pid.get(j)) {
                        if (temp.getRoleId() == 2) {
                            rank = "Professor";
                        }
                        ret.add(new People(temp.getUserName(), rank));
                    }
                }
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            return ret;
        }
    }
}
