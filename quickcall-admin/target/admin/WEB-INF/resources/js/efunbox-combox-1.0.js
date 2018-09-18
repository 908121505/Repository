
/**
 * 服务器获取数据，comboxbox下拉自动生成
 * @param url
 * @param obj
 * @param value
 */
function ajaxComboxAutocomplete(url,obj,value){
	obj.append("<option value=''>--请选择一项--</option>");
	$.post(url,function(data){
		$.each(data,function(){
			obj.append("<option value='"+this.codes+"'>"+this.names+"</option>");
		});
		obj.combobox();
		obj.combobox("defaultAutocomplete",value);
	},"json");
}

/**
 * 服务器获取数据，下来列表显示
 * @param url
 * @param obj
 * @param value
 */
function ajaxCombobox(url,obj,value,datas){
	obj.append("<option value=''>--请选择一项--</option>");
	$.post(url,datas,function(data){
		$.each(data,function(){
			if(value == this.codes){
				obj.append("<option selected='selected' value='"+this.codes+"'>"+this.names+"</option>");
			}else {
				obj.append("<option value='"+this.codes+"'>"+this.names+"</option>");
			}
		});
	},"json");
}