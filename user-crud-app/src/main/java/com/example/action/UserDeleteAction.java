package com.example.action;

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

public class UserDeleteAction extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String idParam = request.getParameter("id");
        
        if (idParam == null || idParam.trim().isEmpty()) {
            ActionMessages errors = new ActionMessages();
            errors.add("error", new ActionMessage("error.user.id.required"));
            saveErrors(request, errors);
            return mapping.findForward("success");
        }
        
        try {
            Long id = Long.parseLong(idParam);
            
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servlet.getServletContext());
            UserService userService = (UserService) context.getBean("userService");
            
            userService.deleteUser(id);
            
            ActionMessages messages = new ActionMessages();
            messages.add("success", new ActionMessage("message.user.deleted"));
            saveMessages(request, messages);
            
        } catch (NumberFormatException e) {
            ActionMessages errors = new ActionMessages();
            errors.add("error", new ActionMessage("error.user.id.invalid"));
            saveErrors(request, errors);
        } catch (Exception e) {
            ActionMessages errors = new ActionMessages();
            errors.add("error", new ActionMessage("error.user.delete", e.getMessage()));
            saveErrors(request, errors);
        }
        
        return mapping.findForward("success");
    }
}
