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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import orm.Course;
import orm.CourseInstructor;
import orm.CourseInstructorId;
import orm.TUser;
import orm.UserCourse;
import orm.UserCourseId;

/**
 *
 * @author Damien
 */
public class UserDaoImpl implements UserDao {

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
        boolean flag = true;
        Session session = factory.openSession();
        Transaction tx = null;
        TUser returnUser = new TUser(), tempuser = new TUser();
        try {
            tx = session.beginTransaction();
            //  List users = session.createQuery("FROM TUser").list();

            Query query = session.createQuery("FROM TUser where userName =:code1");
            query.setParameter("code1", username);
            List user = query.list();
            for (Iterator iterator = user.iterator(); iterator.hasNext();) {
                tempuser = (TUser) iterator.next();
                // System.out.println("temppass "+ tempuser.getPassword());
                if (tempuser.getUserId() == 0) {
                    flag = false;
                }
                if (tempuser.getPassword().equals(pass)) {
                    returnUser = tempuser;
                } else {
                    flag = false;
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
            if (flag == false) {
                returnUser.setUserId(-1);
                returnUser.setRoleId(-1);
            }
            // System.out.println("user id is "+ returnUser.getUserId());
            return returnUser;
        }
    }

    /**
     * When a user successfully logs in the controller will retrieve which
     * courses they are enrolled in
     *
     * @param id userid
     * @return arraylist of courses
     */
    @Override
    public ArrayList<Course> GetCourses(int id, int rank) {
        ArrayList<Course> retCourse = new ArrayList<Course>();
        Session session = factory.openSession();
        Transaction tx = null;
        Course tcourse = new Course();

        try {
            tx = session.beginTransaction();
            List courses = session.createQuery("FROM Course").list();
            if (rank == 1) {
                List userCourses = session.createQuery("FROM UserCourse").list();
                for (int i = 0; i < userCourses.size(); i++) {
                    UserCourse tuc = (UserCourse) userCourses.get(i);
                    UserCourseId tuci = tuc.getId();
                    // System.out.println("hello");
                    for (Iterator iterator2 = courses.iterator(); iterator2.hasNext();) {
                        Course tc = (Course) iterator2.next();
                        if (tc.getCourseId() == tuci.getCourseId()) {
                            System.out.println(tc.getCourseName());
                            retCourse.add(tc);
                        }
                    }
                }
            } else {
                List courseInstructor = session.createQuery("FROM CourseInstructor").list();
                for (int i = 0; i < courseInstructor.size(); i++) {
                    CourseInstructor tuc = (CourseInstructor) courseInstructor.get(i);
                    CourseInstructorId tuci = tuc.getId();
                    // System.out.println("hello");
                    for (Iterator iterator2 = courses.iterator(); iterator2.hasNext();) {
                        Course tc = (Course) iterator2.next();
                        if (tc.getCourseId() == tuci.getCourseId()) {
                            System.out.println(tc.getCourseName());
                            retCourse.add(tc);
                        }
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
    public ArrayList<People> GetPeople(int courseId
    ) {
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
                if (uc.getId().getCourseId() == courseId) {
                    pid.add(uc.getId().getUserId());
                }
            }

            List prof = session.createQuery("FROM CourseInstructor").list();
            for (int i = 0; i < prof.size(); i++) {
                CourseInstructor uc = (CourseInstructor) prof.get(i);
                if (uc.getId().getCourseId() == courseId) {
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
                        System.out.println("getPeople " + temp.getUserName());
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

    /**
     * Returns all of the people enrolled in the course
     *
     * @param courseId course id of what your looking for
     * @return array list of class people
     */
    @Override
    public ArrayList<TUser> GetPeople2(int courseId
    ) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList<Integer> pid = new ArrayList<Integer>();
        ArrayList<TUser> ret = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            List people = session.createQuery("FROM UserCourse").list();

            for (int i = 0; i < people.size(); i++) {
                UserCourse uc = (UserCourse) people.get(i);
                if (uc.getId().getCourseId() == courseId) {
                    pid.add(uc.getId().getUserId());
                }
            }

            List tUsers = session.createQuery("FROM TUser").list();
            for (int i = 0; i < tUsers.size(); i++) {
                TUser temp = (TUser) tUsers.get(i);

                for (int j = 0; j < pid.size(); j++) {
                    if (temp.getUserId() == pid.get(j)) {
                        ret.add(temp);
                        //System.out.println("getPeople " + temp.getUserName());
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
