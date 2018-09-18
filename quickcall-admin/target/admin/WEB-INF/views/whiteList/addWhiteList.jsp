<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">
            </button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }白名单配置</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="bizAppParam"
                  name="appParamForm"
                  action="whiteList/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
               
                <div class="form-group">
                    <label class="col-sm-2 control-label">手机号<font color="red">&nbsp;*</font></label>
                        <input type="hidden" name="id" value="${entity.id }" >
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="phoneNum" name="phoneNum" value=${entity.phoneNum }>
                    </div>
                </div>
                
                 <div class="form-group">
                    <label class="col-sm-2 control-label">姓名<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="nickName" name="nickName" value=${entity.nickName }>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">类型<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                          <select class="form-control" name="type">
                            <option value="1" ${entity.type==1?"selected":'' } >登录白名单</option>
                            <option value="2" ${entity.type==2?"selected":'' }>产品白名单</option>
                            </select>  
                            <%-- <input type="radio"  name="type" value="1" ${entity.type==1?"checked":'' }/>登录白名单
                            <input type="radio"  name="type" value="2" ${entity.type==2?"checked":''}/>产品白名单 --%>
                    </div>
                </div>
                
                
                 
				<span id="tip"
                      style="color: red; font-size: 14px; margin-left: 20px;"></span>
            </form>
        </div>
        <div class="modal-footer">
            <button class="btn btn-default" data-dismiss="modal"
                    aria-hidden="true">取消
            </button>
            <button class="btn btn-primary" data-dismiss="modal">保存</button>
        </div>
    </div>
</div>
<script type="text/javascript"
        src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">
    function check_fun() {
        $("#tip").html("");
        var b = true;
        if ($("#nickName").val() == null || $("#nickName").val() == '') {
            $("#tip").html("请输入姓名");
            b = false;
        }
        if(isNaN(Number($("#phoneNum").val()))){
        	$("#tip").html("手机号只能为数字");
            b = false;	
        }
        if ($("#phoneNum").val() == null || $("#phoneNum").val() == '') {
            $("#tip").html("请输入手机号");
            b = false;
        }
        
        
       
        return b;
    }
</script>
