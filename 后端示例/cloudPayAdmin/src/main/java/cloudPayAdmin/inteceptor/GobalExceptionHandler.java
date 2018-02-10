package cloudPayAdmin.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一异常处理
 * @author hyj
 *
 */
@ControllerAdvice
public class GobalExceptionHandler {
	
	private final Logger logger = Logger.getLogger(getClass());

	@ExceptionHandler(value = Exception.class)
	public void defaultErrorHandler(HttpServletRequest req,HttpServletResponse resp, Exception e) throws Exception {
		
		if(e != null) {
			logger.error(e.getMessage(),e);
			resp.sendRedirect("/cloudpay/system/toErrorPage");
		}
	}
}
