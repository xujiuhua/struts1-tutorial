package com.ebiz.webapp.web.bean;

import org.apache.struts.action.ActionForm;

/**
 * @author xujiuhua
 * @date 2017-01-03
 */
public class LoginForm extends ActionForm{
    private String userName;
    private String password;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
