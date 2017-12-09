/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import java.lang.annotation.Annotation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import orm.TUser;

/**
 *
 * @author Damien
 */
public class LoginController implements Controller {// is extends AbstractControlller or this?------------------------
    
    private UserDao ud;
    
    public void setUserDao1(UserDao UserDao){
        this.ud = UserDao;
    }

 //    @RequestMapping( method = RequestMethod.POST)
//    @RequestMapping(value = "/login")
//    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
//        ModelAndView rmav = new ModelAndView();
//        System.out.println("Username " + request.getParameter("username"));
        //return rmav;
//    }
    
    
   // @RequestMapping(value = "/login")
//   public ModelAndView loggs() {
//        System.out.println("here1");
//      return new ModelAndView("login", "blank", new Object());
//   }
   
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {
            //System.out.println(request.getParameter("username"));
            String username = request.getParameter("username");
            String pass = request.getParameter("password")+"";
            System.out.println("pass is |" + pass +"| end");
            
             HttpSession session = request.getSession();
//            Object objBean = session.getAttribute("UserDao");
//            UserDao ud = (UserDao) objBean; 
            TUser tuser = ud.Login(username, pass);
            System.out.println("userid is "+ tuser.getUserId());
            if(tuser.getUserId() ==0 || tuser.getUserId() ==-1){
                if(pass.equals("null")){
                    System.out.println("in here");
                    return new ModelAndView("performLogin", "invalidLogin", "");
                }
            return new ModelAndView("performLogin", "invalidLogin", "Invalid Username or password. Try Again");
                }
            
            //need to save tuser in a session var. Would that be done initialized in appContext?-------------------
            session.setAttribute("tUser", tuser);
            return new ModelAndView("viewCourses", "courses", ud.GetCourses(tuser.getUserId()));
            
        }
}
