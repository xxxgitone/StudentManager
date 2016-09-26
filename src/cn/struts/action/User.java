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
		System.out.println("进入销毁时间");
		//只是在Tomcat停掉的时候才会执行一次
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String url = request.getRequestURI();
//		System.out.println("发起的URL请求："+url);
		String ss[] = url.split("/");// 以/分段
		ss = ss[ss.length-1].split("\\.");//将最后一部分 以 . 分段
		ss = ss[ss.length-1].split("\\?");//将最后一部分 以 ? 分段
		String suffix = ss[0];//得到后缀
		if("action".equals(suffix)||"jsp".equals(suffix)||"html".equals(suffix)){
			System.out.println(" 被拦截的URL : __"+url+"__");
			
			if("/SMS/login/LoginAction.action".equals(url)){
				System.out.println("发起登录请求:"+"/SMS/login/LoginAction.action".equals(url));
			}else{
				System.out.println("非发起登录请求");
//				System.out.println("!--"+url+"--");
				HttpSession sessions = request.getSession(false);
				if(sessions==null) sessions = request.getSession(true);
				//true 是因为当浏览器没有Session，然后进登录页面的时候空指针异常
				String user = null;
				user = (String)sessions.getAttribute("user");
	//			System.out.println(user==null);
				if(!"/SMS/Logins.jsp".equals(url)){//不是进入登录页面的请求就会被拦截和重定向
					System.out.println("!login--"+url+"--");
					if(user==null || "".equals(user)){
						sessions.setAttribute("param", "outtime");
	//					System.out.println(response);
						//如果是一个会报404错误的URL，实例化的response是不能执行重定向的，但是却也没有异常抛出
						try {
							//使用转发而不是重定向是因为，回登录页面重定向会加上命名空间在链接前，所以js和css就会404
							System.out.println("重定向回的登录页面");
							response.sendRedirect("/SMS/Logins.jsp");
//							System.out.println("转发回的登录页面");
	//						request.getRequestDispatcher("/Logins.jsp?u=9").forward(request,response);
	//						 RequestDispatcher rd = request.getRequestDispatcher("/Logins.jsp?u=9");
	//				           rd.forward(request, response);  
						} catch (Exception e) {
							System.out.println("重定向有异常抛出");
						}
						System.out.println("不是登录页面，而且Session中没有属性");
					}
				}
			}
		}
		
//		System.out.println("=="+request+"=="+response+"==");
//		arg2.doFilter(request, response);
		//拦截器的下一个链
		try {
			System.out.println("发起了一个资源请求，进入自定义监听器");
			arg2.doFilter(request, response);
		} catch (Exception e) {
			System.out.println("路径进入有异常");
			//这是在用户没有登录，没有Session存在的状态下发起的
			//一个不存在的URL时抛出异常的处理，如果这个浏览器有用户登录(有Session存在，就不会有异常)
			//浏览器之间是不会有干扰的
			HttpSession sessions = request.getSession(false);
			if(sessions!=null){
				String user = (String)sessions.getAttribute("user");
//				System.out.println("user状态："+user==null);
				if(!"/SMS/Logins.jsp".equals(url)&&(user==null || "".equals(user))){
					sessions.setAttribute("param", "outtime");
					System.out.println(response);
					request.getRequestDispatcher("/SMS/Logins.jsp");
//					response.sendRedirect("/SMS/Logins.jsp");
					System.out.println("因为输入的地址是非法的，所以使用重定向是有异常的，所以需要使用转发方式");
				}else{
					request.getRequestDispatcher("/SMS/Logins.jsp");
				}
			}else{
				request.getRequestDispatcher("/SMS/Logins.jsp");
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Tomcat服务器启动的时候执行这个方法");
	}

}
