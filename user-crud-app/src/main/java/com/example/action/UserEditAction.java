package com.example.action;

import com.example.form.UserForm;
import com.example.model.User;
import com.example.service.UserService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserEditAction extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String idParam = request.getParameter("id");
        
        if (idParam == null || idParam.trim().isEmpty()) {
            ActionMessages errors = new ActionMessages();
            errors.add("error", new ActionMessage("error.user.id.required"));
            saveErrors(request, errors);
            return new ActionForward("/userList.do", true);
        }
        
        try {
            Long id = Long.parseLong(idParam);
            
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servlet.getServletContext());
            UserService userService = (UserService) context.getBean("userService");
            
            User user = userService.getUserById(id);
            if (user == null) {
                ActionMessages errors = new ActionMessages();
                errors.add("error", new ActionMessage("error.user.notfound"));
                saveErrors(request, errors);
                return new ActionForward("/userList.do", true);
            }
            
            // Populate form với dữ liệu user
            UserForm userForm = (UserForm) form;
            userForm.setId(user.getId().toString());
            userForm.setUsername(user.getUsername());
            userForm.setEmail(user.getEmail());
            userForm.setFullName(user.getFullName());
            // Không set password vì lý do bảo mật
            
            // Set mode để JSP biết đây là chỉnh sửa
            request.setAttribute("mode", "edit");
            request.setAttribute("pageTitle", "Chỉnh Sửa User");
            
            return mapping.findForward("success");
            
        } catch (NumberFormatException e) {
            ActionMessages errors = new ActionMessages();
            errors.add("error", new ActionMessage("error.user.id.invalid"));
            saveErrors(request, errors);
            return new ActionForward("/userList.do", true);
        }
    }
}
