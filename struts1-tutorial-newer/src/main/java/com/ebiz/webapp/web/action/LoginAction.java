package com.ebiz.webapp.web.action;

import com.ebiz.webapp.web.bean.LoginForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xujiuhua
 * @date 2017-01-03
 */
public class LoginAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 转化成loginForm
        LoginForm loginForm = (LoginForm) form;
        if ("admin".equals(loginForm.getUserName())) {
            return mapping.findForward("loginSuccess");
        } else {
            return mapping.findForward("loginFail");
        }
    }
}
