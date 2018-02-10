//自定义校验规则文件

// 以下为jquery validate 自定义检验规则
$(function() {
	
	// 手机号码验证
	jQuery.validator.addMethod("mobile",function(value, element) {
		var length = value.length;
		var mobile = /^(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/;
		return this.optional(element)|| (length == 11 && mobile.test(value));
	}, "请正确填写您的手机号码");

	// qq号码检验
	jQuery.validator.addMethod("QQ", function(value, element) {
		var tel = /^[1-9]\d{4,9}$/;
		return this.optional(element) || (tel.test(value));
	}, "请出入正确的qq号码");

	// 中文检验，只能输入中文
	jQuery.validator.addMethod("chinese", function(value, element) {
		var chinese = /^[\u4e00-\u9fa5]+$/;
		return this.optional(element) || (chinese.test(value));
	}, "只能输入中文");
	
	// 判断是否羊城通卡号
	jQuery.validator.addMethod("yctCardNum", function(value, element) {
		var flag = false;
		if(value.length == 16 || value.length == 10 || value.length == 8) {
			flag = true;
		}
		return this.optional(element) || flag;
	}, "请输入8位或10位或16位羊城通卡号");
	
	// 判断两个input元素的value是否相等, param为jquery选择器的字符串
	jQuery.validator.addMethod("eqAnoInput", function(value, element, param) {
        var anoValue = $(param).val();
		var flag = false;
		if(value == anoValue) {
			flag = true;
		}
		return this.optional(element) || flag;
	}, "两个值不相等");


});