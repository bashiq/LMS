package hiber;
// Generated Dec 2, 2017 12:13:47 AM by Hibernate Tools 4.3.1



/**
 * UserRole generated by hbm2java
 */
public class UserRole  implements java.io.Serializable {


     private int userRoleId;
     private String userRoleName;

    public UserRole() {
    }

    public UserRole(int userRoleId, String userRoleName) {
       this.userRoleId = userRoleId;
       this.userRoleName = userRoleName;
    }
   
    public int getUserRoleId() {
        return this.userRoleId;
    }
    
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }
    public String getUserRoleName() {
        return this.userRoleName;
    }
    
    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }




}

