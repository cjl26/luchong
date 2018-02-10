package cloudPayAdmin.admin.dbapp.entity.hicatcard.user;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.*;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.BaseEntity;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.annotation.StringDateFormat;

@Entity
@Table(name = "t_user")
public class TUser extends BaseEntity {

	public static Integer GENDER_MALE = 1;//男性
	public static String  GENDER_MALE_MSG = "男";
	public static Integer GENDER_FEMALE = 2;//女性
	public static String  GENDER_FEMALE_MSG = "女";
	public static Integer GENDER_UNKNOWN = 3;//未知
	public static String  GENDER_UNKNOWN_MSG = "未知";
	
	public static Map<String,String> initGenderMap() {
		Map<String,String> genderMap = new LinkedHashMap<String,String>();
		genderMap.put(GENDER_MALE+"", GENDER_MALE_MSG);
		genderMap.put(GENDER_FEMALE+"", GENDER_FEMALE_MSG);
		genderMap.put(GENDER_UNKNOWN+"", GENDER_UNKNOWN_MSG);
		return genderMap;
	}

	public static Boolean genderValid(String genderInput) {
		try {
			Integer gender = Integer.valueOf(genderInput);
			if (gender == GENDER_MALE || gender == GENDER_FEMALE || gender == GENDER_UNKNOWN) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "PHONE")
	private String phone;
	@Column(name = "NICKNAME")
	private String nickname;
	@Column(name = "AVATER_URL")
	private String avaterUrl;
	@Column(name = "GENDER")
	private Integer gender;
	@Column(name = "COUNTRY")
	private String country;
	@Column(name = "PROVINCE")
	private String province;
	@Column(name = "CITY")
	private String city;
	@Column(name = "LANGUAGE")
	private String language;
	@Column(name = "OPENID")	
	private String openid;
	@Column(name = "UNIONID")
	private String unionid;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "CREATE_TIME")
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	private String createTime;
	@Column(name = "UPDATE_TIME")
	@StringDateFormat(oriFormat=TimeUtil.DATE_PATTERN_NOSEPARTOR,destFormat=TimeUtil.DEFAULT_DATE_PATTERN)
	private String updateTime;
	@Column(name = "ENABLE")
	private Boolean enable;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
	public String getAvaterUrl() {
		return avaterUrl;
	}

	public void setAvaterUrl(String avaterUrl) {
		this.avaterUrl = avaterUrl;
	}


	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
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

	
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	
	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
