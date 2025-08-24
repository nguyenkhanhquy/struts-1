package com.example.action;

import com.example.form.LoginForm;
import com.example.model.UserSession;
import com.example.service.UserService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginShowAction extends Action {
    private UserService userService;
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        
        // Nếu đã đăng nhập rồi thì redirect về trang chủ
        if (userSession != null && userSession.isLoggedIn()) {
            return new ActionForward("/userList.do", true);
        }
        
        // Reset form
        LoginForm loginForm = (LoginForm) form;
        if (loginForm != null) {
            loginForm.reset(mapping, request);
        }
        
        return mapping.findForward("success");
    }
}
