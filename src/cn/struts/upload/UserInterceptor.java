package cn.struts.upload;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class UserInterceptor implements Interceptor{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("UserInterceptor +++++++++++++++++ destroy");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("UserInterceptor +++++++++++++++++ init");
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("UserInterceptor +++++++++++++++++ intercept");
		//只是获取了Session中的数据，没有取到实例
		//ServletActionContext.getRequest().getSession() 这才是获取了Session实例，才能添加属性
		Map sessionMap = ServletActionContext.getContext().getSession();
		Object obj = sessionMap.get("user");
		
		if(obj==null||obj.equals("")){
			ServletActionContext.getRequest().getSession().setAttribute("param", "outtime");
			return "Login";
		}else{
			//既然不需要对action方法修改什么就直接返回null就会跳去执行action
//			UploadAction d = (UploadAction) invocation.getAction();
//			return d.saveFile();
			
			//return null;
			
			//返回null就会继续执行action，如果在前面就调出来了action实例，并且执行了方法
				//那么之后的action就不会再重复执行
			
			//检查是否还有拦截器待执行，有就去执行，没有就会得到null，同样的继续执行action
			String result = invocation.invoke();
			return result;
		}
//		return null;
	}

}
