package cloudPayAdmin.admin.dbapp.vo.hicatcard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "v_merchant_service")
public class VMerchantService {
	
	@Id
	@Column(name = "MERCHANT_SERVICE_ID")
	private Long merchantServiceId;
	
	@Column(name = "MERCHANT_ID")
	private Long merchantId;
	
	@Column(name = "SERVICE_ID")
	private Long serviceId;
	
	@Column(name = "FEE")
	private Integer fee;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "MERCHANT_NUMBER")
	private String merchantNumber;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "SERVICE_NAME")
	private String serviceName;
	
	@Column(name = "ENABLE")
	private boolean enable;
	
	@Column(name = "STATUS")
	private String status;
	
	
	
	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	
}
