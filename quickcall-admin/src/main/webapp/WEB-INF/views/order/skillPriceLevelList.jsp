<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="skillPriceLevel:select">
<div class="content1">
    <div class="header">
        <h1 class="page-title">价格阶梯管理</h1>
        <ul class="breadcrumb">
            <li>订单管理</li>
            <li class="active">价格阶梯管理</li>
        </ul>
    </div>

    <div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">技能名称</div>
							<input class="form-control" type="text" id="skillItemNameQuery">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">等级</div>
							<!-- <select class="form-control" id="priceLevelQuery">
								<option value="">--请选择--</option>
								<option value="1">一等级</option>
								<option value="2">二等级</option>
								<option value="3">三等级</option>
							</select> -->
							<input class="form-control" type="number" min="1" id="priceLevelQuery">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" id="levelStatusQuery">
								<option value="">--请选择--</option>
								<option value="0">关闭</option>
								<option value="1">显示次数</option>
								<option value="2">显示一小时</option>
								<option value="4">显示半小时</option>
								<option value="6">同时显示半/一小时</option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="col-md-2">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div>
				<shiro:hasPermission name="skillPriceLevel:add">
				<div class="col-md-2">
					<button type="button" class="btn btn-info btn-small btn-block"
						onclick="addAndUpdateRow(0)">
						<i class="glyphicon glyphicon-plus"></i> 增加
					</button>
				</div>
				</shiro:hasPermission>
			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
		
		
        function Format(now,mask)
		{
		    var d = now;
		    var zeroize = function (value, length)
		    {
		        if (!length) length = 2;
		        value = String(value);
		        for (var i = 0, zeros = ''; i < (length - value.length); i++)
		        {
		            zeros += '0';
		        }
		        return zeros + value;
		    };
		 
		    return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0)
		    {
		        switch ($0)
		        {
		            case 'd': return d.getDate();
		            case 'dd': return zeroize(d.getDate());
		            case 'ddd': return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];
		            case 'dddd': return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];
		            case 'M': return d.getMonth() + 1;
		            case 'MM': return zeroize(d.getMonth() + 1);
		            case 'MMM': return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];
		            case 'MMMM': return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];
		            case 'yy': return String(d.getFullYear()).substr(2);
		            case 'yyyy': return d.getFullYear();
		            case 'h': return d.getHours() % 12 || 12;
		            case 'hh': return zeroize(d.getHours() % 12 || 12);
		            case 'H': return d.getHours();
		            case 'HH': return zeroize(d.getHours());
		            case 'm': return d.getMinutes();
		            case 'mm': return zeroize(d.getMinutes());
		            case 's': return d.getSeconds();
		            case 'ss': return zeroize(d.getSeconds());
		            case 'l': return zeroize(d.getMilliseconds(), 3);
		            case 'L': var m = d.getMilliseconds();
		                if (m > 99) m = Math.round(m / 10);
		                return zeroize(m);
		            case 'tt': return d.getHours() < 12 ? 'am' : 'pm';
		            case 'TT': return d.getHours() < 12 ? 'AM' : 'PM';
		            case 'Z': return d.toUTCString().match(/[A-Z]+$/);
		            // Return quoted strings with the surrounding quotes removed
		            default: return $0.substr(1, $0.length - 2);
		        }
		    });
		}; 
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"skillPriceLevel/initTable.htm",
					aoColumns: [
			            { 
			              "data": "skillItemName",
			              "sTitle":"技能名称",
			              'sClass':"text-center"
			            },
			            { 
			               "data": "priceLevel",
			               "sTitle":"价格等级",
			               'sClass':"text-center"/* ,
			           	"mRender": function(data, type, full) {
	                    		if(data == 1){
	                               return "<font color='green'>一等级</font>";
	                           } else if(data == 2) {
	                               return "<font color='green'>二等级</font>";
	                           }else if(data == 3) {
	                               return "<font color='green'>三等级</font>";
	                           }else{
	                        	   return data;
	                           } 
                      	 	} */
			            },
						{
							"data": "halfPrice",
							"sTitle": "半小时价格",
                            'sClass':"text-center"
						},
						{
							"data": "onePrice",
							"sTitle": "一小时价格",
                            'sClass':"text-center"
						},
						{
							"data": "timePrice",
							"sTitle": "一次价格",
                            'sClass':"text-center"
						},
						{
							"data": "levelThreshold",
							"sTitle": "评分阀值",
                            'sClass':"text-center"
						},
						{
							"data": "levelStatus",
							"sTitle": "状态",
                            'sClass':"text-center",
                           	"mRender": function(data, type, full) {
                            		if(data == 0){
                                       return "<font color='red'>关闭</font>";
                                   } else if(data == 1) {
                                       return "<font color='green'>显示次数</font>";
                                   }else if(data == 2) {
                                       return "<font color='green'>显示一小时</font>";
                                   }else if(data == 4) {
                                       return "<font color='green'>显示半小时</font>";
                                   }else if(data == 6) {
                                       return "<font color='green'>同时显示半/一小时</font>";
                                   }else{
                                	   return data;
                                   }
                               }
						},
						{
							"data" : "skillItemId",
							"sTitle" : "操作",
							'sClass' : "text-center"
						}
			         ],
			         fnServerParams: function (aoData) {  //查询条件
	                       aoData.push({ "name": "skillItemName", "value": $("#skillItemNameQuery").val().replace(new RegExp(" ","g"),"") } );
		                   aoData.push({"name": "priceLevel", "value": $("#priceLevelQuery").val()});
		                   aoData.push({"name": "levelStatus", "value": $("#levelStatusQuery").val()});
	                    },
	                    aoColumnDefs : [ {
							"aTargets" : 7,
							"mRender" : function(data,type, row) {
								var detail = "",del = "";
								<shiro:hasPermission name="skillPriceLevel:update">
									detail = "<a href='#' onclick='addAndUpdateRow(\""+ row.skillItemId+"\",\""+row.priceLevel+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>修改</a>";
								</shiro:hasPermission>
								<shiro:hasPermission name="skillPriceLevel:delete">
                                	del = "<a href='#' onclick='deleteRow(\""+ row.skillItemId+"\",\""+row.priceLevel+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>删除</a>";
                               	</shiro:hasPermission>
								return  detail+"&nbsp;"+del;
							}
						} ]
		            
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
				 
			  	var ipt = $('#priceLevelQuery');
		        	
			    ipt.on('keyup',function(){
		
			        if(! /^\d+$/.test(this.value)){            
		
			        this.value='';
		
			        }
		
			    })
			
			});
			//增加或者修改受影响的行数
			function addAndUpdateRow(skillItemId,priceLevel){
				$('#insertAndUpdate').addAndUpdateRow("skillPriceLevel/addAndUpdateOther.htm?skillItemId="+skillItemId+"&priceLevel="+priceLevel);
			}

        function deleteRow(skillItemId,priceLevel){
            $('#myModal').deleteRow("skillPriceLevel/deleteRow.htm?skillItemId="+skillItemId+"&priceLevel="+priceLevel);
        }
	</script>
		<!---dialog选项-->
		<div>
			<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />
			<jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp" />

			<div class="modal fade" id="insertAndUpdate" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			</div>
		</div>
	</div>
</shiro:hasPermission>