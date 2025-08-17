package com.learn.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.learn.struts.model.User;

public class UserDispatchAction extends DispatchAction {
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return viewUser(mapping, form, request, response);
	}
	
	public ActionForward addUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user = (User) form;
		user.setName("Hello Struts - Add");
		user.setAge(10);
		
		return mapping.findForward("addUser");
	}
	
	public ActionForward viewUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user = (User) form;
		user.setName("Hello Struts - View");
		user.setAge(10);
		
		return mapping.findForward("viewUser");
	}
	
	public ActionForward updateUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user = (User) form;
		user.setName("Hello Struts - Update");
		user.setAge(10);
		
		return mapping.findForward("updateUser");
	}
	
	public ActionForward deleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user = (User) form;
		user.setName("Hello Struts - Delete");
		user.setAge(10);
		
		return mapping.findForward("deleteUser");
	}
}
