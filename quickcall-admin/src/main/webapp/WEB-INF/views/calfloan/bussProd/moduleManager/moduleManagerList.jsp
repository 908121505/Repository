<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="moduleManager:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">模块管理</h1>
            <ul class="breadcrumb">
                <li>商务后台</li>
                <li class="active">模块管理</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">模块类型</div>
                            <select class="form-control" id="modelNo1">
                             <option value="">全部 </option>
                            <option value="GSXZ">公司性质</option>
                               <option value="HZMS">合作模式</option>
                                 <option value="JSZQ">结算周期</option>
                                    <option value="TGLX">活动类型</option>
                            
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">模块内容</div>
                            <input class="form-control" type="text"  id="modelContent1"  >
                        </div>
                    </div>
                </div>
                
                 
                 
                <div class="col-md-1">
                    <button type="button" class="btn btn-primary btn-small btn-block"
                            id="query">
                         查询
                    </button>
                </div>
                 <div class="col-md-2">
                    <shiro:hasPermission name="moduleManager:add">
                        <button type="button" class="btn btn-info btn-small btn-block"
                                onclick="addAndUpdateRow(0)">
                            <i class="glyphicon glyphicon-plus"></i> 增加
                        </button>
                    </shiro:hasPermission>
                </div>
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
                    sAjaxSource:"moduleManager/initTable.htm",
                    aoColumns: [
                    	{
                            sTitle: '序号',
                            data: "no",
                            className: 'text-center whiteSpace',
                            render:function(data,type,row,meta) {
                            	if(data==null||data==''){
                                return meta.row + 1 ;
                            	}else{
                            		return data;
                            	}
                            }
                        },
                    	
                       
                        {
                            "data": "modelType",
                            "sTitle":"模块类型",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "modelContent",
                            "sTitle":"模块内容",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "weight",
                            "sTitle":"权重",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "modifyMan",
                            "sTitle":"操作人",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "modifyTime",
                            "sTitle":"操作时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        }
                        <shiro:hasPermission name="moduleManager:update">
                        ,{"data": "id","sTitle":"操作",'sClass':"text-center"}
                        </shiro:hasPermission> 
                       
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({ "name": "modelContent", "value": $("#modelContent1").val() } );
                        aoData.push({ "name": "modelNo", "value": $("#modelNo1").val() } );
                       
                    }
                ,
                aoColumnDefs: [
                 
                    {"aTargets" :6,"mRender" : function(data, type, row){
						var edit="",del="";
					
							del ="<a href='#' onclick='addAndUpdateRow(\""+row.id+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑 </a>";
					if(row.id==null||row.id==''){
						return '--';
					}else{
					
				    return edit+"&nbsp;"+del;
                    }
				}
				}
                    
                ]
               
                });

                $('#query').click(function(){
                    $('#example').dataTable().fnDraw();
                });

            });
           
            
          //增加或者修改受影响的行数
            function addAndUpdateRow(id) {
                $('#insertAndUpdate').addAndUpdateRow("moduleManager/addAndUpdateHome.htm?id=" + id);
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