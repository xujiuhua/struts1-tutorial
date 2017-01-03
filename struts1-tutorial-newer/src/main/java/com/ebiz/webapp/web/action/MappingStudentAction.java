package com.ebiz.webapp.web.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author xujiuhua
 * @date 2017-01-03
 */
public class MappingStudentAction extends MappingDispatchAction{
    public ActionForward addStudent(ActionMapping arg0, ActionForm form,
                                    HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
        PrintWriter out=arg3.getWriter();
        out.println("addStudent");
        return null;
    }
    public ActionForward deleteStudent(ActionMapping arg0, ActionForm arg1,
                                       HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
        PrintWriter out=arg3.getWriter();
        out.println("deleteStudent");
        return null;
    }
    public ActionForward updateStudent(ActionMapping arg0, ActionForm arg1,
                                       HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
        PrintWriter out=arg3.getWriter();
        out.println("updateStudent");
        return null;
    }
}
