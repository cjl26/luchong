package cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant;

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
@Table(name = "t_merchant_service")
public class TMerchantService extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MERCHANT_SERVICE_ID")
	private Long merchantServiceId;

	@Column(name = "MERCHANT_ID")
	private Long merchantId;

	@Column(name = "SERVICE_ID")
	private Long serviceId;

	@Column(name = "FEE")
	private Integer fee;
	
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	@Column(name = "CREATE_TIME")
	private String createTime;

	@Column(name = "UPDATE_TIME")
	private String updateTime;

	@Column(name = "ENABLE")
	private boolean enable = Boolean.TRUE;;

	@Column(name = "STATUS")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getMerchantServiceId() {
		return merchantServiceId;
	}

	public void setMerchantServiceId(Long merchantServiceId) {
		this.merchantServiceId = merchantServiceId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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
