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
public class WorldAction extends DispatchAction{

    public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("country", "usa");
        request.setAttribute("city", "nyc");
        return mapping.findForward("worldRequest");
    }
}
