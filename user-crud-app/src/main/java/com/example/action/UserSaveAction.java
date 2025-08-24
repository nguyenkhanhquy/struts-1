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
import java.util.Date;

public class UserSaveAction extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        UserForm userForm = (UserForm) form;
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servlet.getServletContext());
        UserService userService = (UserService) context.getBean("userService");
        
        try {
            User user;
            
            if (userForm.getId() != null && !userForm.getId().trim().isEmpty()) {
                // Cập nhật user hiện có
                user = userService.getUserById(Long.parseLong(userForm.getId()));
                if (user == null) {
                    ActionMessages errors = new ActionMessages();
                    errors.add("error", new ActionMessage("error.user.notfound"));
                    saveErrors(request, errors);
                    request.setAttribute("mode", "edit");
                    return mapping.findForward("failure");
                }
                
                user.setUsername(userForm.getUsername());
                user.setEmail(userForm.getEmail());
                user.setFullName(userForm.getFullName());
                
                // Chỉ cập nhật password nếu có nhập mới
                if (userForm.getPassword() != null && !userForm.getPassword().trim().isEmpty()) {
                    user.setPassword(userForm.getPassword());
                }
                
                userService.updateUser(user);
                
                ActionMessages messages = new ActionMessages();
                messages.add("success", new ActionMessage("message.user.updated"));
                saveMessages(request, messages);
                
            } else {
                // Tạo user mới
                user = new User();
                user.setUsername(userForm.getUsername());
                user.setEmail(userForm.getEmail());
                user.setFullName(userForm.getFullName());
                user.setPassword(userForm.getPassword());
                user.setCreatedDate(new Date());
                
                userService.saveUser(user);
                
                ActionMessages messages = new ActionMessages();
                messages.add("success", new ActionMessage("message.user.created"));
                saveMessages(request, messages);
            }
            
            return mapping.findForward("success");
            
        } catch (Exception e) {
            ActionMessages errors = new ActionMessages();
            errors.add("error", new ActionMessage("error.user.save", e.getMessage()));
            saveErrors(request, errors);
            
            // Giữ lại mode để hiển thị form đúng
            String mode = (userForm.getId() != null && !userForm.getId().trim().isEmpty()) ? "edit" : "create";
            request.setAttribute("mode", mode);
            
            return mapping.findForward("failure");
        }
    }
}
