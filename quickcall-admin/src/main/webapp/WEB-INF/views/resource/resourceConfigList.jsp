<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="resourceConfig:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">资源管理后台</h1>
			<ul class="breadcrumb">
				<li>资源管理后台</li>
				<li class="active">首页6帧资源位配置</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
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
				sAjaxSource:"resourceConfig/initTable.htm",
				aoColumns: [
		            { 
		              "data": "configNum",
		              "sTitle":"序号",
		              'sClass':"text-center"
		            },
		            { 
		               "data": "strategy",
		               "sTitle":"当前策略",
		               'sClass':"text-center",
		               "mRender": function(data, type, full) {
                     		if(data==1){
                     			return "自然推荐";
                     		}else if(data==2){
                     			return "运营推荐";
                     		}else{
                     			return data;
                     		}
                     	}
		            },
		            { 
		                "data": "skillName",
		                "sTitle":"品类",
		                'sClass':"text-center"
		            },
                   
                    {
                        "data": "resourceName",
                        "sTitle":"启用资源池",
                        'sClass':"text-center"
                    },
                    {
						"data" : "totalCusNum",
						"sTitle" : "声优总量",
						'sClass' : "text-center"
					},
                    {
                        "data": "receiptCusNum",
                        "sTitle":"已接单声优",
                        'sClass':"text-center"
                    },
                    {
						"data" : "surplusCusNum",
						"sTitle" : "剩余声优",
						'sClass' : "text-center"
					},
           
					{
						"data" : "resourceConfigId",
						"sTitle" : "操作",
						'sClass' : "text-center"
					}
		         ],
		         fnServerParams: function (aoData) {  //查询条件
                    } ,
                 aoColumnDefs : [ {
					"aTargets" : 7,
					"mRender" : function(data,type, row) {
						var detail = "";
						<shiro:hasPermission name="resourceConfig:update">
						detail = "<a href='#' onclick='addAndUpdateRow(\""+ row.resourceConfigId+ "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>编辑</a>";
						</shiro:hasPermission>
						return  detail ;
					}
			} ] 
	            
			});
			
			 $('#query').click(function(){
				$('#example').dataTable().fnDraw();
			});
		
		});
		
		
		//增加或者修改受影响的行数
		function addAndUpdateRow(id){
			$('#insertAndUpdate').addAndUpdateRow("resourceConfig/addAndUpdateHome.htm?id="+id);
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
