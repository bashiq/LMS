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
import orm.UserAssignment;

/**
 *
 * @author Damien
 */
public class GradeDaoImpl implements GradeDao {
    private SessionFactory factory;
    
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
    
    /**
     * method for updating and to grade a student assignment
     * @param ua userassignment object needs to be passed in
     * @return 1 if sucessful -1 if not
     */
    @Override
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
     * @param at
     * @return arraylist of userassignments for A student
     */
    @Override
    public ArrayList<UserAssignment> GetGrades(int userID, int courseID, 
            ArrayList<Assignment> at) {
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
    @Override
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
