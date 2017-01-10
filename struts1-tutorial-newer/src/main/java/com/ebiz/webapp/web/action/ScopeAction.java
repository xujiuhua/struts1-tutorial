package com.ebiz.webapp.web.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xujiuhua
 * @date 2017-01-04
 */
public class ScopeAction extends DispatchAction{

    public ActionForward req(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("country", "China");
        request.setAttribute("city", "HeFei");
        return mapping.findForward("req");
    }
}
