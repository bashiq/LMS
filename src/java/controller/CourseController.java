/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
//import org.springframework.stereotype.Controller;

/**
 *
 * @author Bilal
 */
//@Controller
//@RequestMapping(value = "/yellow.htm", method = RequestMethod.GET)
public class CourseController implements Controller{

    private UserDao ud;
    private AssignmentDao ad;
    private GradeDao gd;

    public void setUserDao1(UserDao UserDao) {
        this.ud = UserDao;
    }

    public void setAssignmentDao1(AssignmentDao d) {
        ad = d;
    }

    public void setGradeDao1(GradeDao g) {
        gd = g;
    }
    
    //@RequestMapping(value = "/people", method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("in courseController");
        int cid = Integer.parseInt(request.getParameter("action"));
        return new ModelAndView("coursePeople", "people", ud.GetPeople(1));
    }
}
