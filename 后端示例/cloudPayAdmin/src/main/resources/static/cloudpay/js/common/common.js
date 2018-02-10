var successRespCode="0";

function isEmpty(str){
	return str == "" || str == "null" || str == undefined || str == null;
}

/**
 * 获得url的参数
 * @param name
 * @returns
 */
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}


/**
 * 分转为元,参数为要转换的 dom元素的Id
 */
function transferFenToYuan(elementId) {	
	var elem = $("#" + elementId);
	if(!isEmpty(elem.val())) {
		elem.val(new BigDecimal(elem.val()+"").multiply(new BigDecimal("0.01")).setScale(2, MathContext.ROUND_HALF_UP).toString());
	}
	
}

/**
 * 将form里面的内容序列化成json
 * 相同的checkbox用分号拼接起来
 * @param {dom} 指定的选择器
 * @param {obj} 需要拼接在后面的json对象
 * @method serializeJson
 * */
$.fn.serializeJson=function(otherString){
    var serializeObj={},
        array=this.serializeArray();
    $(array).each(function(){
        if(serializeObj[this.name]){
            serializeObj[this.name]+=','+this.value;
        }else{
            serializeObj[this.name]=this.value;
        }
    });

    if(otherString!=undefined){
        var otherArray = otherString.split(';');
        $(otherArray).each(function(){
            var otherSplitArray = this.split(':');
            serializeObj[otherSplitArray[0]]=otherSplitArray[1];
        });
    }
    return serializeObj;
};

//单独渲染时间范围插件
function doDaterangepicker(elementId,startDate,endData) {
	if(!isEmpty(startDate) && !isEmpty(endData)) {
		$("#" + elementId).daterangepicker({
			"opens" : "center",
			"locale" : {
				"format" : "YYYY-MM-DD",
				"separator" : " - ",
				"applyLabel" : "确定",
				"cancelLabel" : "取消",
				"fromLabel" : "From",
				"toLabel" : "To",
				"customRangeLabel" : "Custom",
				"weekLabel" : "W",
				"daysOfWeek" : [ "日", "一", "二", "三", "四", "五", "六" ],
				"monthNames" : [ "1月", "2月", "3月", "4月", "5月", "6月", "7月",
						"8月", "9月", "10月", "11月", "12月" ],
				"firstDay" : 1						
			},
			"startDate":startDate,
			"endDate":endData
		})
	} else {
		$("#" + elementId).daterangepicker({
			"opens" : "center",
			"locale" : {
				"format" : "YYYY-MM-DD",
				"separator" : " - ",
				"applyLabel" : "确定",
				"cancelLabel" : "取消",
				"fromLabel" : "From",
				"toLabel" : "To",
				"customRangeLabel" : "Custom",
				"weekLabel" : "W",
				"daysOfWeek" : [ "日", "一", "二", "三", "四", "五", "六" ],
				"monthNames" : [ "1月", "2月", "3月", "4月", "5月", "6月", "7月",
						"8月", "9月", "10月", "11月", "12月" ],
				"firstDay" : 1						
			}
		})
		
		$("#" + elementId).val("");
	}
}

