package cloudPayAdmin.admin.entity.excel;

import cloudPayAdmin.util.annotation.ExcelColumn;

public class EImportUser {
	
	@ExcelColumn("序号")
	private String index;
	
	@ExcelColumn("卡号")
	private String cardNum;
	
	@ExcelColumn("手机号码")
	private String phone;

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
	
	
}
