package cloudPayAdmin.admin.dbapp.entity.hicatcard.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.BaseEntity;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

@Entity
@Table(name = "t_service")
public class TService extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SERVICE_ID")
	private Long serviceId;

	@Column(name = "SERVICE_NAME")
	private String serviceName;

	@Column(name = "STATUS")
	private String status;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	@Column(name = "UPDATE_TIME")
	private String updateTIme;
	
	@Column(name = "ENABLE")
	private boolean enable = Boolean.TRUE;;

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTIme() {
		return updateTIme;
	}

	public void setUpdateTIme(String updateTIme) {
		this.updateTIme = updateTIme;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	/*public static final String STATUS_IN_USE = "1";
	public static final String STATUS_IN_USE_MSG = "在用";
	public static final String STATUS_NOT_USE = "0";
	public static final String STATUS_NOT_USE_MSG = "停用";

	// 状态
	public static Map<String, String> initStatusMap() {
		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put(STATUS_IN_USE, STATUS_IN_USE_MSG);
		statusMap.put(STATUS_NOT_USE, STATUS_NOT_USE_MSG);
		return statusMap;
	}*/
}
