package cn.struts.action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class User implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("进入销毁时间");
		//只是在Tomcat停掉的时候才会执行一次
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String url = request.getRequestURI();
		System.out.println(" 获取到的请求URL --"+url+"--是否登陆请求："+"/SMS/login/LoginAction.action".equals(url));
		if("/SMS/login/LoginAction.action".equals(url)){
			System.out.println("发起登录请求:"+"/SMS/login/LoginAction.action".equals(url));
		}else{
			System.out.println("非发起登录请求");
//			System.out.println("--"+url+"--");
			HttpSession sessions = request.getSession(false);
			if(sessions==null) sessions = request.getSession(true);
			//true 是因为当浏览器没有Session，然后进登录页面的时候空指针异常
			String user = null;
			user = (String)sessions.getAttribute("user");
//			System.out.println(user==null);
			if(!"/SMS/Logins.jsp".equals(url)){//不是进入登录页面的请求就会被拦截和重定向
				if(user==null || "".equals(user)){
					sessions.setAttribute("param", "outtime");
//					System.out.println(response);
					//如果是一个会报404错误的URL，实例化的response是不能执行重定向的，但是却也没有异常抛出
					try {
//						System.out.println("重定向回的登录页面");
//						response.sendRedirect("/SMS/Logins.jsp");
						System.out.println("转发回的登录页面");
						request.getRequestDispatcher("/SMS/Logins.jsp");
					} catch (Exception e) {
						System.out.println("重定向有异常抛出");
					}
					System.out.println("不是登录页面，而且Session中没有属性");
				}
			}
		}
//		System.out.println("=="+request+"=="+response+"==");
//		arg2.doFilter(request, response);
		//拦截器的下一个链
		try {
			arg2.doFilter(request, response);
		} catch (Exception e) {
			HttpSession sessions = request.getSession(false);
			String user = null;
			user = (String)sessions.getAttribute("user");
			System.out.println(user==null);
			if(!"/SMS/Logins.jsp".equals(url)){
				if(user==null || "".equals(user)){
					sessions.setAttribute("param", "outtime");
					System.out.println(response);
					request.getRequestDispatcher("/SMS/Logins.jsp");
//					response.sendRedirect("/SMS/Logins.jsp");
					System.out.println("因为输入的地址是非法的，所以使用重定向是有异常的，所以需要使用转发方式");
				}
			}
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Tomcat服务器启动的时候执行这个方法");
	}

}
