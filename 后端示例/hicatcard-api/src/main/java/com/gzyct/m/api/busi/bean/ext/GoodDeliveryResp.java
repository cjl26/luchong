package com.gzyct.m.api.busi.bean.ext;

import com.project.m.api.common.biz.resp.BizResp;

/**
 * 发货返回
 * @author Administrator
 *
 */
public class GoodDeliveryResp extends BizResp {
	private String result_code; // 返回码
	private String err_msg; // 错误描述
	private String order_id; // 商品订单号
	private String ship_status; // 发货状态
	private String yct_extend; // 羊城通扩展字段

	public GoodDeliveryResp() {
		result_code = "";
		err_msg = "";
		order_id = "";
		ship_status = "";
		yct_extend = "";
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_msg() {
		return err_msg;
	}

	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getShip_status() {
		return ship_status;
	}

	public void setShip_status(String ship_status) {
		this.ship_status = ship_status;
	}

	public String getYct_extend() {
		return yct_extend;
	}

	public void setYct_extend(String yct_extend) {
		this.yct_extend = yct_extend;
	}

}
