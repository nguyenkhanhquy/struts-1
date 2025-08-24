package com.example.action;

import com.example.form.LoginForm;
import com.example.model.User;
import com.example.model.UserSession;
import com.example.service.UserService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction extends Action {
    private UserService userService;
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        LoginForm loginForm = (LoginForm) form;
        
        try {
            // Kiểm tra thông tin đăng nhập
            boolean isValid = userService.validateUser(loginForm.getUsername(), loginForm.getPassword());
            
            if (isValid) {
                // Đăng nhập thành công
                User user = userService.getUserByUsername(loginForm.getUsername());
                
                // Tạo session
                HttpSession session = request.getSession();
                UserSession userSession = new UserSession(user);
                session.setAttribute("userSession", userSession);
                
                // Remember me functionality
                if ("on".equals(loginForm.getRememberMe()) || "true".equals(loginForm.getRememberMe())) {
                    Cookie usernameCookie = new Cookie("rememberedUsername", loginForm.getUsername());
                    usernameCookie.setMaxAge(30 * 24 * 60 * 60); // 30 ngày
                    usernameCookie.setPath(request.getContextPath());
                    response.addCookie(usernameCookie);
                } else {
                    // Xóa cookie nếu không check remember me
                    Cookie usernameCookie = new Cookie("rememberedUsername", "");
                    usernameCookie.setMaxAge(0);
                    usernameCookie.setPath(request.getContextPath());
                    response.addCookie(usernameCookie);
                }
                
                ActionMessages messages = new ActionMessages();
                messages.add("success", new ActionMessage("message.login.success", user.getFullName()));
                saveMessages(request, messages);
                
                // Redirect đến trang được yêu cầu hoặc trang chủ
                String returnUrl = (String) session.getAttribute("returnUrl");
                if (returnUrl != null && !returnUrl.trim().isEmpty()) {
                    session.removeAttribute("returnUrl");
                    return new ActionForward(returnUrl, true);
                } else {
                    return mapping.findForward("success");
                }
                
            } else {
                // Đăng nhập thất bại
                ActionMessages errors = new ActionMessages();
                errors.add("error", new ActionMessage("error.login.invalid"));
                saveErrors(request, errors);
                return mapping.findForward("failure");
            }
            
        } catch (Exception e) {
            ActionMessages errors = new ActionMessages();
            errors.add("error", new ActionMessage("error.login.system", e.getMessage()));
            saveErrors(request, errors);
            return mapping.findForward("failure");
        }
    }
}
