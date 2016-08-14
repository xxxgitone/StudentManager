package cn.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


public class TestAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		
		System.out.println(name);
		request.setAttribute("username", "Myth");
		//HttpSession pu = request.getSession(true);
		System.out.println("test execute!");
		return "test";
	}

}
