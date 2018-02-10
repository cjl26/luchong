//导出excel,后台方法待实现
function exportExcel() {
	//需要导出excel的列的列标，从0开始
	var excelIndex = [];
	//excel头的列名
	var header = [];
	//excel的数据
	var excelData = [];

	var ths = $("#thead1").children().eq(0).children();
	
	ths.each(function(i,n) {
		var $n = $(n);
		if("true" == $n.attr("excel")) {			
			excelIndex.push(i);
		}
	});
	
	$.each(excelIndex,function(i,n) {
		header.push($(datatable.column(n).header()).html());
	});
	
	datatable.cells().every( function ( rowIndex,columnIndex, tableLoop, cellLoop ) {
		
	    if($.inArray(columnIndex,excelIndex) >= 0) {
	    	var $node = $(this.node());
	    	excelData.push($node.html());
	    }
	});
	
	var form=$("<form>");//定义一个form表单
	form.attr("style","display:none");
	form.attr("target","_blank");
	form.attr("method","post");
	form.attr("action","/cloudpay/system/exportExcelData");
	
	var input1=$("<input>");
	input1.attr("type","hidden");
	input1.attr("name","header");
	input1.attr("value",header.join("|"));
	//alert(header.join("|"));
	var input2=$("<input>");
	input2.attr("type","hidden");
	input2.attr("name","excelData");
	input2.attr("value",excelData.join("|"));
	//alert(excelData.join("|"));
	$("body").append(form);//将表单放置在web中
	form.append(input1);
	form.append(input2);
	form.submit();//表单提交 
	
	/*alert(excelIndex.toString());
	alert(header.toString());
	alert(excelData.toString());*/
}