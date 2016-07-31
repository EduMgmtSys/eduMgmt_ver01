package com.americanexpress.pricingengine.interceptor;
/**
 * <BR> This class is an interceptor class<BR>
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
 * @author	Janeesh
 *
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.americanexpress.pricingengine.common.constants.IPECommonConstants;

public class PEHomeInterceptor extends PEInterceptor {
	private static final Logger AUDIT_LOGGER = LoggerFactory.getLogger(IPECommonConstants.AUDIT_LOGGER);
	@Override
	public boolean preHandling(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("111111111111");
		AUDIT_LOGGER.info("preHandling");
		return true;
	}

	@Override
	public boolean preHandlingCompletion(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("44444444444444");
		AUDIT_LOGGER.info("preHandlingCompletion");
		return true;

	}

}