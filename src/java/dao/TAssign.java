/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import orm.TUser;
import orm.UserAssignment;

/**
 *
 * @author Bilal
 */
public class TAssign {
    private TUser tUser;
    private UserAssignment userAssign;

    public TAssign(){
        
    }
    public TAssign(TUser tUser, UserAssignment userAssign) {
        this.tUser = tUser;
        this.userAssign = userAssign;
    }
    
    

    public TUser gettUser() {
        return tUser;
    }

    public void settUser(TUser tUser) {
        this.tUser = tUser;
    }

    public UserAssignment getUserAssign() {
        return userAssign;
    }

    public void setUserAssign(UserAssignment userAssign) {
        this.userAssign = userAssign;
    }
    
    
}
