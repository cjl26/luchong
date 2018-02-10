$(function(){
	var llList = $("#lList");
	var llList = document.getElementById("lList");
	var rList = $("#rList");
	var items = $(".data-list li");
	for(var i = 0;i<items.length;i++){
		items[i].onclick = itemsclick;
		items[i].ondblclick = itemsdblclick;
	}
	function itemsdblclick(){
		if (this.parentNode === llList) {
			rList.append(this);
		}else{
			lList.append(this);
		}
	}
	function itemsclick(){
		var classname = this.className;
		if(classname === "selected"){
			this.className = "";
		}else{
			this.className="selected";
		}
	}
	function itemsMove(){
		var items = $(".data-list li.selected");
		for(var i = 0;i<items.length;i++){
			if(this.id === "add"){
				rList.append(items[i]);
			}else{
				lList.append(items[i]);
			}
		}
		
		notSelected.splice(0,notSelected.length);
		hasSelected.splice(0,hasSelected.length);
		var lListChild = $("#lList").children();
		var rListChild = $("#rList").children();
		lListChild.each(function(i,n) {
			notSelected.push($(this).attr("id"));
		});
		
		rListChild.each(function(i,n) {
			hasSelected.push($(this).attr("id"));
		});
	}
	$("#add").on("click",itemsMove);
	$("#remove").on("click",itemsMove);
});