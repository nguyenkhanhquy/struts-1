package com.example.action;

import com.example.service.UserService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserListAction extends Action {
	private UserService userService;
    
    // Setter để Spring inject UserService
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		/*
		 * WebApplicationContext context =
		 * WebApplicationContextUtils.getWebApplicationContext(servlet.getServletContext
		 * ()); UserService userService = (UserService) context.getBean("userService");
		 */
        
        try {
            request.setAttribute("userList", userService.getAllUsers());
            request.setAttribute("totalUsers", userService.getAllUsers().size());
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi tải danh sách user: " + e.getMessage());
        }
        
        return mapping.findForward("success");
    }
}
