package com.example.action;

import com.example.form.UserForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateAction extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // Reset form để tạo mới
        UserForm userForm = (UserForm) form;
        userForm.reset(mapping, request);
        
        // Set mode để JSP biết đây là tạo mới
        request.setAttribute("mode", "create");
        request.setAttribute("pageTitle", "Thêm User Mới");
        
        return mapping.findForward("success");
    }
}
