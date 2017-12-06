/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import orm.Assignment;
import orm.AssignmentType;
import orm.UserAssignment;
import orm.UserAssignmentId;

/**
 *
 * @author Damien
 */
public class AssignmentDaoImpl implements AssignmentDao {
    
    private SessionFactory factory;//if error occurs may need to be static
    
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
    
    /**
     * Method return arraylist of assignments for a course
     *
     * @param CourseID id of course
     * @return arraylist of assignments of given courseId
     */
    @Override
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
    @Override
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
    @Override
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

    @Override
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
    @Override
    public int SubmitAssignment(int assignmentId, int userId, String textSub, 
            int score, int isSubmitted, int isLate) {
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
}
