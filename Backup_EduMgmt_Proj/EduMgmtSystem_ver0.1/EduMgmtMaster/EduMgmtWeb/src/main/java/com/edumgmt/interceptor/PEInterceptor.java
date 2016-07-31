package com.americanexpress.pricingengine.interceptor;
/**
 * <BR> This class is an interceptor class <BR>
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
 * @author	Murali
 *
 */
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class PEInterceptor extends PEBaseInterceptor {
	
	/**
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param Object handler
	 * @return boolean - This value bean contain
	 *         presentation specific data.
	 * @throws Exception
	 */
	 public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {
		       preHandling(request,response);
		       super.preHandle(request, response, handler);
		       preHandlingCompletion(request,response);
		     return true;
	    }
	 
	 /**
		 * @param HttpServletRequest request
		 * @param HttpServletResponse response
		 * @return boolean - This value bean contain
		 *         presentation specific data.
		 * @throws IOException
		 */
	 public abstract boolean preHandling(HttpServletRequest request,HttpServletResponse response) throws IOException;
	 /**
		 * @param HttpServletRequest request
		 * @param HttpServletResponse response
		 * @return boolean - This value bean contain
		 *         presentation specific data.
		 * @throws 
		 */
	 public abstract boolean preHandlingCompletion(HttpServletRequest request,HttpServletResponse response);

}
