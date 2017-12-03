/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import hiber.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Bilal
 */
public class DBBeanImpl {

    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex.getLocalizedMessage());
        }

        DBBeanImpl d = new DBBeanImpl();
        // d.listCustomers();
        // d.GetCourses(1);
        //d.GetPeople(1);
        // d.CreateAssignment(1, "JSF3", "create a LMS using JSF", 1, 0, 400);
        //d.GetAssignments(1, 1);
        // d.CreateAssignmentType(1, "quizes");
        //d.GetAssignmentTypes(1);
    }

    public static void setFactory(SessionFactory factory) {
        DBBeanImpl.factory = factory;
    }

    /**
     * method to log people in
     *
     * @param email for user
     * @param pass password of user
     * @return -1 if invalid username or pass word else returns TUser object
     * containing role and userID
     */
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
     * When a user succefully logs in the controller will retrive which courses
     * they are enrolled in
     *
     * @param id userid
     * @return arraylist of courses
     */
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

    /**
     * Method return arraylist of assignments for a course
     *
     * @param CourseID id of course
     * @return arraylist of assignments of given courseId
     */
    public ArrayList<Assignment> GetAssignments(int CourseID) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList<Assignment> ret = new ArrayList<Assignment>();

        try {
            tx = session.beginTransaction();
            //List assignments = session.createQuery("FROM Assignment where CourseId =:code").list();

            Query query = session.createQuery("FROM Assignment where courseId =:code");
            query.setParameter("code", CourseID);
            List assignment = query.list();

            for (int i = 0; i < assignment.size(); i++) {
                ret.add((Assignment) assignment.get(i));
                System.out.println(ret.get(i).getAssignmentName());
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
     * Will create an assignment for a professor. Must be given all fields
     *
     * @param courseId the course id
     * @param assignmentName what the assignment name should be
     * @param assignmentDescription description
     * @param assignmentTypeId is classwork, homework, etc
     * @param isGraded
     * @param potentialScore out of 100, 20?
     * @return 1 if successful or -1 if not
     */
    public int CreateAssignment(int courseId, String assignmentName, String assignmentDescription,
            int assignmentTypeId, int isGraded, int potentialScore) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();//temp.getassignmentid will = assignment id
            Assignment temp = new Assignment(0, courseId, assignmentName, assignmentDescription, assignmentTypeId, isGraded, potentialScore);
            int num = (int) session.save(temp);// dah = blah
            //System.out.println("num from create assignment is "+ num+ "temp value is "+ temp.getAssignmentId());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
            return 1;
        }
    }

    /**
     * Method to get assignment types created by professor
     * @param courseId the courseid needed to return items
     * @return arraylist of the object assignmnet type
     */
    public ArrayList<AssignmentType> GetAssignmentTypes(int courseId) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList<AssignmentType> ret = new ArrayList<AssignmentType>();

        try {
            tx = session.beginTransaction();
            //List assignments = session.createQuery("FROM Assignment where CourseId =:code").list();

            Query query = session.createQuery("FROM AssignmentType where courseId =:code");
            query.setParameter("code", courseId);
            List assignmentType = query.list();

            for (int i = 0; i < assignmentType.size(); i++) {
                ret.add((AssignmentType) assignmentType.get(i));
                System.out.println(ret.get(i).getAssignmentTypeName());
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
     * professors can create categories for assignments and put them in there
     *
     * @param CourseId course id
     * @param type string of the name ex. classwork, quiz...
     * @return if successful 1 if not -1 
     */

    public int CreateAssignmentType(int CourseId, String type) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            AssignmentType temp = new AssignmentType(0, CourseId, type);
            session.save(temp);// dah = blah
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
            return 1;
        }
    }

    /**
     * Method for a user to submit assignment
     * @param assignmentId
     * @param userId
     * @param textSub answer or solution to assignment
     * @param score for professor grading
     * @param isSubmitted 1 for yes 0 for no
     * @param isLate
     * @return 1 if successful -1 if not
     */
    public int SubmitAssignment(int assignmentId, int userId, String textSub, int score, int isSubmitted, int isLate) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            UserAssignment temp = new UserAssignment(new UserAssignmentId(assignmentId, userId), textSub, score, isSubmitted, isLate);
            session.save(temp);// dah = blah could be  customerID = (Integer) session.save(customer);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
            return 1;
        }
    }
    
    /**
     * method for updating and to grade a student assignment
     * @param ua userassignment object needs to be passed in
     * @return 1 if sucessful -1 if not
     */
    public int GradeAssignment(UserAssignment ua) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.update(ua);// dah = blah could be  customerID = (Integer) session.save(customer);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
            return 1;
        }
    }

    /**
     * used to get grades for A STUDENT
     * will need to sort in controller or in a seperate class
     *
     * @param userID
     * @param courseID
     * @return arraylist of userassignments for A student
     */
    public ArrayList<UserAssignment> GetGrades(int userID, int courseID, ArrayList<Assignment> at) {
        ArrayList<UserAssignment> ret = new ArrayList<UserAssignment>();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from UserAssignment where UserId = :code ");
            query.setParameter("code", userID);
            List userAssignments = query.list();
            for (int i = 0; i < userAssignments.size(); i++) {
                
                UserAssignment ua = (UserAssignment) userAssignments.get(i);
                for(int j =0; j < at.size(); j++){
                    if(at.get(j).getCourseId()== courseID){
                        ret.add(ua);
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
     * for a professor to get grades for all student
     *
     * @param CourseID
     * @return array list of userAssignments
     */
    public ArrayList<UserAssignment> GetGrades(int CourseID) {
        ArrayList<UserAssignment> ret = new ArrayList<>();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            List userAssignments = session.createQuery("FROM UserAssignment").list();
           Query query = session.createQuery("from Assignment where CoursetId = :code ");
            query.setParameter("code", CourseID);
            List assignments = query.list();
            for (int i = 0; i < userAssignments.size(); i++) {
                UserAssignment ua = (UserAssignment) userAssignments.get(i);
                for(int j = 0; j< assignments.size(); j++){
                    Assignment ta = (Assignment) assignments.get(j);
                    if(ta.getAssignmentId() == ua.getId().getAssignmentId()){
                        ret.add(ua);
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
