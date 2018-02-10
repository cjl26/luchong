function getYearByDateTime(dateTime) {
	var year = '';
	if (dateTime.length >= 4) {
		year = dateTime.substr(0, 4);
	}
	return year;
}

function getMonthByDateTime(dateTime) {
	var month = '';
	if (dateTime.length >= 6) {
		month = dateTime.substr(4, 2);
	}
	return month;
}

function getDayByDateTime(dateTime) {
	var date = '';
	if (dateTime.length >= 8) {
		date = dateTime.substr(6, 2)
	}
	return date;
}

function getHourByDateTime(dateTime) {
	var hour = '';
	if (dateTime.length >= 10) {
		hour = dateTime.substr(8, 2);
	}
	return hour;
}

function getMinuteByDateTime(dateTime) {
	var minute = '';
	if (dateTime.length >= 12) {
		minute = dateTime.substr(10, 2);
	}
	return minute;
}

function getSecondByDateTime(dateTime) {
	var second = '';
	if (dateTime.length >= 14) {
		second = dateTime.substr(12, 2);
	}
	return second;
}

function getDateByDateTime(dateTime, pattern) {

	var year = getYearByDateTime(dateTime);

	var month = isEmpty(getMonthByDateTime(dateTime)) ? '' : pattern
			+ getMonthByDateTime(dateTime);

	var day = isEmpty(getDayByDateTime(dateTime)) ? '' : pattern
			+ getDayByDateTime(dateTime);

	var date = year + month + day;

	return date;
}

function getChineseDateByDateTime(dateTime) {

	var year = isEmpty(getYearByDateTime(dateTime)) ? ''
			: getYearByDateTime(dateTime) + "年";

	var month = isEmpty(getMonthByDateTime(dateTime)) ? ''
			: getMonthByDateTime(dateTime) + "月";

	var day = isEmpty(getDayByDateTime(dateTime)) ? ''
			: getDayByDateTime(dateTime) + "日";

	var date = year + month + day;

	return date;
}

function getTimeByDateTime(dateTime, pattern) {

	var hour = getHourByDateTime(dateTime);

	var minute = isEmpty(getMinuteByDateTime(dateTime)) ? '' : pattern
			+ getMinuteByDateTime(dateTime);

	var second = isEmpty(getSecondByDateTime(dateTime)) ? '' : pattern
			+ getSecondByDateTime(dateTime);

	var time = hour + minute + second;

	return time;
}

function getMinTimeByDateTime(dateTime, pattern) {

	var hour = getHourByDateTime(dateTime);

	var minute = isEmpty(getMinuteByDateTime(dateTime)) ? '' : pattern
			+ getMinuteByDateTime(dateTime);

	var time = hour + minute;

	return time;
}

function getChineseTimeByDateTime(dateTime) {

	var hour = isEmpty(getHourByDateTime(dateTime)) ? ''
			: getHourByDateTime(dateTime) + "时";

	var minute = isEmpty(getMinuteByDateTime(dateTime)) ? ''
			: getMinuteByDateTime(dateTime) + "分";

	var second = isEmpty(getSecondByDateTime(dateTime)) ? ''
			: getSecondByDateTime(dateTime) + "秒";

	var time = hour + minute + second;

	return time;
}

// 时间格式 : yyyyMMddHHmmss -- > yyyy-MM-dd
function dateTime2Date(dateTime) {
	if (isEmpty(dateTime)) {
		return "";
	}
	return dateTime.substring(0, 4) + "-" + dateTime.substring(4, 6) + "-"
			+ dateTime.substring(6, 8);
}
// 某日期与当前日期相隔的天数
function getDateDiff(oneDate) {
	oneDate = new Date(oneDate.replace(/-/g, "/"));
	var nowDate = new Date();
	var days = Math.abs(oneDate.getTime() - nowDate.getTime());
	var dates = parseInt(days / (1000 * 60 * 60 * 24));

	// 如果等于0，计算小时数
	if (dates == 0) {

		dates = (days % (1000 * 60 * 60 * 24));
		var newdate = parseInt(dates / (1000 * 60 * 60));
		return newdate;
	}

	return dates;
}

// yyyyMMddHHmmss获取yyyy/MM/dd HH:mm:ss 格式时间
function getLocalFormatDateTime(dateTime) {
	return getDateByDateTime(dateTime, '/') + " "
			+ getTimeByDateTime(dateTime, ":");
}

// yyyyMMddHHmmss获取yyyy-MM-dd HH:mm:ss 格式时间
function getStandardFormatDateTime(dateTime) {
	return getDateByDateTime(dateTime, '-') + " "
			+ getTimeByDateTime(dateTime, ":");
}

//yyyyMMddHHmmss获取yyyy-MM-dd HH:mm 格式时间
function getMinFormatDateTime(dateTime) {
	return getDateByDateTime(dateTime, '-') + " "
			+ getMinTimeByDateTime(dateTime, ":");
}

// yyyyMMddHHmmss转换为Date
function dateTimeToDate(dateTime) {
	return new Date(getLocalFormatDateTime(dateTime));
}

/**
 * 时间戳转为yyyy-MM-dd HH:mm:ss的字符串
 * @param timeStamp
 * @returns {String}
 */
function timeStampToDateString(timeStamp) {
	   alert(timeStamp);
	   var d =new Date(timeStamp);  
	   var year=d.getFullYear();     
       var month=d.getMonth()+1;
       var date=d.getDate();     
       var hour=d.getHours();     
       var minute=d.getMinutes();     
       var second=d.getSeconds();     
       return  year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;     
}

// 获取标准当前时间
function getStandardFormatNowDate() {
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1
			+ strDate + " " + date.getHours() + seperator2 + date.getMinutes()
			+ seperator2 + date.getSeconds();
	return currentdate;
}

// 获取当前datetime
function getFormatNowDateTime() {
	var date = new Date();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = date.getFullYear() + month + strDate + date.getHours()
			+ date.getMinutes() + date.getSeconds();
	return currentdate;
}

//获取datetime1, datetime2的间隔天数
function getDateDiffByDatetime(datetime1, datetime2) { 
	//sDate1和sDate2是2002-12-18格式  
	var sDate1 = getDateByDateTime(datetime1, '-');
	var sDate2 = getDateByDateTime(datetime2, '-');
	var aDate, oDate1, oDate2, iDays;
	aDate = sDate1.split("-");
	oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]); //转换为12-18-2002格式  
	aDate = sDate2.split("-");
	oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
	iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); //把相差的毫秒数转换为天数  
	return iDays;
}

// 获取与当前时间的时间区间
function getIntervalDownTime(dateTime) {
	var dt = dateTimeToDate(dateTime);
	// 1.获取倒计时
	var intervalMsec = Date.now() - dt; // 当前时间减去目的时间，获取两者相差的毫秒数
	// 计算出相差天数
	var days = Math.floor(intervalMsec / (24 * 3600 * 1000))
	// 计算天数后剩余的毫秒数
	var leave1 = intervalMsec % (24 * 3600 * 1000)
	// 计算出剩余的毫秒数小时数
	var hours = Math.floor(leave1 / (3600 * 1000))
	// 计算小时数后剩余的毫秒数
	var leave2 = leave1 % (3600 * 1000)
	// 计算相差分钟数
	var minutes = Math.floor(leave2 / (60 * 1000))
	// 计算相差秒数
	var leave3 = leave2 % (60 * 1000) // 计算分钟数后剩余的毫秒数
	var seconds = Math.round(leave3 / 1000)

	var rs = days > 30 ? getStandardFormatDateTime(dateTime) : "" + days > 0
			&& days <= 30 ? days + "天前 " : "" + hours > 0 ? hours + "小时前 " : ""
			+ minutes > 0 ? minutes + "分钟前" : "" + seconds > 0 ? seconds + "秒前"
			: "刚刚"
	return rs;
}
