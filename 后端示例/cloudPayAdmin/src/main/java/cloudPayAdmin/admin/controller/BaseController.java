package cloudPayAdmin.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;

public class BaseController {
		
	 protected static ThreadLocal<List<String>> errorMsgLocal = new ThreadLocal<List<String>>(); 
	 /**
	  * 增加错误信息到页面显示
	  * @param request
	  * @param errorMsg
	  */
	 protected void addErrorMsg(HttpServletRequest request,String errorMsg) {
		 
		 Object object =  request.getAttribute("errorMsgList");
		 if(object == null) {
			 errorMsgLocal.remove();
			 List<String> errorMsgList = new ArrayList<String>();
			 errorMsgList.add(errorMsg);
			 request.setAttribute("errorMsgList", errorMsgList);
			 errorMsgLocal.set(errorMsgList);
		 } else {
			 List<String> errorMsgList = errorMsgLocal.get();
			 if(!CollectionUtils.isEmpty(errorMsgList)) {
				 errorMsgList.add(errorMsg);
				 request.setAttribute("errorMsgList", errorMsgList);
				 errorMsgLocal.set(errorMsgList);
			 }
		 }
	}
}
