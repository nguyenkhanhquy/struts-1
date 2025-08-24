package com.example.action;

import com.example.model.UserSession;
import com.example.service.UserService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction extends Action {
    private UserService userService;
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            UserSession userSession = (UserSession) session.getAttribute("userSession");
            
            if (userSession != null && userSession.isLoggedIn()) {
                String username = userSession.getUsername();
                
                // Logout
                userSession.logout();
                session.removeAttribute("userSession");
                session.invalidate();
                
                ActionMessages messages = new ActionMessages();
                messages.add("success", new ActionMessage("message.logout.success", username));
                saveMessages(request, messages);
            }
        }
        
        return mapping.findForward("success");
    }
}
