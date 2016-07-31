package com.americanexpress.pricingengine.interceptor;
/**
 * <BR> This class is a base interceptor class<BR>
 *
 * <B>Created</B>	July 15, 2014
 * <BR><BR>
 *
 * <B>Revision History - 1.0</B>
 * <p><TABLE BORDER=1>
 *   <TR><TH>  Version </TH><TH> Modified By </TH><TH> Modified on </TH><TH> Trigger </TH><TH> Description </TH></TR>
 *   </TABLE>
 *
 * @version	1.0
 *
 * @author	Harish
 *
 */
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.americanexpress.pricingengine.common.constants.IPECommonConstants;
import com.americanexpress.pricingengine.peweb.valuebean.PEUserBean;
import com.americanexpress.pricingengine.util.PESessionManager;

public class PEBaseInterceptor implements HandlerInterceptor{
	private static HashMap propertiesMap = new HashMap();
	private static final Logger AUDIT_LOGGER = LoggerFactory.getLogger(IPECommonConstants.AUDIT_LOGGER);
	
	/**
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param Object handler
	 * @return boolean
	 * @throws Exception
	 */
	 public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {
		 String uri = request.getRequestURI();
		 	 
		 if(!uri.endsWith("loginError.do") && !uri.endsWith("logout.do") )
		  {
			 PESessionManager sessionManager = null;
			 HttpSession session = null;
			
		 Enumeration reqParams = request.getParameterNames();
			while (reqParams.hasMoreElements()) {
				Object reqParamObj = reqParams.nextElement();
				String reqParamName = (String) reqParamObj;
				String reqParamValue = request.getParameter(reqParamName);
				AUDIT_LOGGER.info("reqParamName"+reqParamName);
				AUDIT_LOGGER.info("reqParamValue"+reqParamValue);
			}
			Enumeration reqParams1 = request.getHeaderNames();
			
			
			while (reqParams1.hasMoreElements()) {
				Object reqParamObj = reqParams1.nextElement();
				String reqParamName = (String) reqParamObj;
				String reqParamValue = request.getHeader(reqParamName);
}
			
			//commented by satish
			//String env=PEPropertyReader.getProperty("pe", "env");
			//String env= PropertyLoader.getValue(PEConstants.PE_ENV);
			
			String env=System.getProperty("spring.profiles.active");
			AUDIT_LOGGER.info("@@@env"+env);
			if(null!=env && !"E0".equalsIgnoreCase(env))
			{
				AUDIT_LOGGER.info("yes");
				String guid=(String)request.getHeader("GUID");
				String empid=(String)request.getHeader("employeeid");
				 AUDIT_LOGGER.info("@@@guid"+guid);
				 AUDIT_LOGGER.info("@@@empid"+empid);
				
			if(null==(String)request.getHeader("GUID")|| null==(String)request.getHeader("employeeid")) 
			{
				 AUDIT_LOGGER.error("GUID Not Found Error");
				 response.sendRedirect("loginError.do");
				 return false;	
			}
			else{
				sessionManager = new PESessionManager(request,true);	
				session  = sessionManager.getWrappedSession();
				PEUserBean userBean= new PEUserBean();
				userBean.setUserGUID((String)request.getHeader("GUID"));
				 AUDIT_LOGGER.info("33333");
				userBean.setEmployeeID((String)request.getHeader("employeeid"));
//				userBean.setCtryCodeLst(ctryCodeLst);
				sessionManager.setAttribute(IPECommonConstants.USER_BEAN,userBean);
			}
		  }
			else{
				 AUDIT_LOGGER.info("@@@4444");
				sessionManager = new PESessionManager(request,true);	
				session  = sessionManager.getWrappedSession();
				PEUserBean userBean= new PEUserBean();
				userBean.setUserGUID("demoUser");
				userBean.setEmployeeID("demoUser");
				session.setAttribute(IPECommonConstants.USER_BEAN,userBean);
				
			}
		  }
		 
		     return true;
	    }
	     
	 /**
		 * @param HttpServletRequest request
		 * @param HttpServletResponse response
		 * @param Object handler
		 * @param ModelAndView modelAndView
		 * @return 
		 * @throws Exception
		 */
	    public void postHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler,
	            ModelAndView modelAndView) throws Exception {
	     }
	     
	    /**
		 * @param HttpServletRequest request
		 * @param HttpServletResponse response
		 * @param Object handler
		 * @param Exception ex
		 * @return 
		 * @throws Exception
		 */
	    public void afterCompletion(HttpServletRequest request,
	            HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {
	       
	    }
	    

}