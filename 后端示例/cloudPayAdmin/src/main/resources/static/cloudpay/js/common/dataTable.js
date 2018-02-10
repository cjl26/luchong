//设置 adminlte 表格渲染提示方式
if($.fn.dataTable != null && $.fn.dataTable != undefined) {
	if($.fn.dataTable.ext != null && $.fn.dataTable.ext != undefined) {		
			$.fn.dataTable.ext.errMode = 'none';
	}
}

$(function() {
	// 初始化下拉条
	var select = $('.select2');
	if (select != null && select != undefined) {
		select.select2()
	}
	
	var datepicker = $('.datepicker');
	if (datepicker != null && datepicker != undefined) {
		// 初始化日期选择
		// Date picker
		datepicker.datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true,
			language : 'zh-CN'
		})
	}
	
	var daterange = $('.daterange');
	if(daterange != null && daterange != undefined) {
		// Date range picker
		daterange.daterangepicker({
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
					}/*,
					"startDate":"2017-12-12",
					"endDate":"2018-12-12"*/
				})

		daterange.val("");
	}
	
	 var dataTable = $('#dataTable tbody');
  	if( dataTable != null && dataTable != undefined) {  		
  		dataTable.on('click',':checkbox', function() {
  			//如果选中
  			if($(this).prop("checked")) {
  				 selected.push($(this).val()); 				
  			} else {   //如果是取消选择
  				var id = $(this).val();
  				var index = $.inArray(id, selected);
  				selected.splice( index, 1 ); 				
  			} 
  		});
  	}	  
	
})