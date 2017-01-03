package com.ebiz.webapp.web.action;

import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author xujiuhua
 * @date 2017-01-03
 */
public class DynAddStudentAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dynaActionForm = (DynaActionForm) form;
        // 获得信息
        String sname = (String) dynaActionForm.get("sname");
        Date birthday = (Date) dynaActionForm.get("birthday");
        String major = (String) dynaActionForm.get("major");
        Float score = (Float) dynaActionForm.get("score");
        System.out.println("sname=" + sname + ",birthday=" + birthday + ",score="
                + score);
        if("admin".equals(sname)) {
            return mapping.findForward("addSuccess");
        }else {
            return mapping.findForward("addFail");
        }
    }
}
