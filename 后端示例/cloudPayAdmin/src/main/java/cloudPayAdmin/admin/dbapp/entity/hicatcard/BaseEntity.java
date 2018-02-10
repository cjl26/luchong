package cloudPayAdmin.admin.dbapp.entity.hicatcard;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseEntity {
	
	public static final String STATUS_IN_USE = "1";
	public static final String STATUS_IN_USE_MSG = "在用";
	public static final String STATUS_NOT_USE = "0";
	public static final String STATUS_NOT_USE_MSG = "停用";
	
	public static final Boolean ENABLE = true;
	public static final Boolean DISABLE = false;


	// 状态
	public static Map<String, String> initStatusMap() {
		Map<String, String> statusMap = new LinkedHashMap<String, String>();
		statusMap.put(STATUS_IN_USE, STATUS_IN_USE_MSG);	
		statusMap.put(STATUS_NOT_USE, STATUS_NOT_USE_MSG);
		return statusMap;
	}

}
