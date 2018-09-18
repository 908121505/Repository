<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">
            </button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }app信息</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="sysUserForm"
                  name="appversionForm"
                  action="appversion/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                		  <input type="hidden" value="${entity.appVersionId }"
                               name="appVersionId"/> 	
                
                    <label class="col-sm-2 control-label">APP类型<font
                            color="red">&nbsp;*</font></label>

                    <div class="col-sm-10">
	                 	<select class="form-control" id="appTypeSelect" name="appType">
										
						</select> 
						<input type="hidden" id="appTypeHidden" value="${entity.appType}">  
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label">文件上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if></label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="file" class="form-control" id="appversionFile"
                                   name="appversionFile"> <span class="input-group-btn">
                        <button class="btn btn-success" type="button" id="uploadappversion">上传文件</button>
                    </span>
                        </div>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label">更新地址<font
                            color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="downloadUrl"
                               id="appversionFile_input" value="${entity.downloadUrl }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">版本类型<font
                            color="red">&nbsp;*</font></label>

                    <div class="col-sm-10">
                        <label class="checkbox-inline">
                            <input type="radio" name="tag" value="1" ${entity.tag=='1'?'checked':'' }> 生产
                        </label>
                        <label class="checkbox-inline">
                            <input type="radio" name="tag" value="2" ${entity.tag=='2'?'checked':'' }> 预发布
                        </label>
                        <label class="checkbox-inline">
                            <input type="radio"  name="tag" value="3" ${entity.tag=='3'?'checked':'' }>测试
                        </label>
                        <label class="checkbox-inline">
                            <input type="radio"  name="tag" value="4" ${entity.tag=='4'?'checked':'' }>补丁
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>

                    <div class="col-sm-10">
                        <label class="checkbox-inline"> <input type="radio"
                                                               name="state"
                                                               value="1" ${entity.state=='1'?'checked':'' }>
                            开启
                        </label> <label class="checkbox-inline"> <input type="radio"
                                                                        name="state"
                                                                        value="2" ${entity.state=='2'?'checked':'' }>
                        关闭
                    </label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">是否弹窗<font color="red">&nbsp;*</font></label>

                    <div class="col-sm-10">
                        <label class="checkbox-inline"> <input type="radio"
                                                               name="popup"
                                                               value="1" ${entity.popup=='1'?'checked':'' }>
                            弹窗
                        </label> <label class="checkbox-inline"> <input type="radio"
                                                                        name="popup"
                                                                        value="2" ${entity.popup=='2'?'checked':'' }>
                        不弹窗
                    </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">版本号<font color="red">&nbsp;*</font></label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="versionNumber"
                               id="appversion_versionNumber" value="${entity.versionNumber }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">更新属性<font color="red">&nbsp;*</font></label>

                    <div class="col-sm-10">
                        <label class="checkbox-inline"> <input type="radio"
                                                               name="changeProperties" id="appversion_changeProperties"
                                                               value="1" ${entity.changeProperties=='1'?'checked':'' }>
                            强制更新
                        </label> <label class="checkbox-inline"> <input type="radio"
                                                                        name="changeProperties" value="0"
                    ${entity.changeProperties=='0'?'checked':'' }> 提示更新
                    </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">更新描述<font
                            color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="changeDesc"
                               id="appversion_changeDesc" value="${entity.changeDesc }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">更新日志</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="changeLog"
                               id="appversion_changeLog" value="${entity.changeLog }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">开始时间<font color="red">&nbsp;*</font></label>

                    <div class="col-sm-10">
                        <input type="text" id="appversion_startTime" class="form-control"
                               onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01',maxDate:'#F{$dp.$D(\'appversion_endTime\')}'})"
                               value="${entity.beginTime }" name="beginTime"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">结束时间<font color="red">&nbsp;*</font></label>

                    <div class="col-sm-10">
                        <input type="text" id="appversion_endTime" class="form-control"
                               onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'appversion_beginTime\')}',maxDate:'2050-12-31'})"
                               value="${entity.endTime }" name="endTime"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">versionCode/patchVersion(补丁)</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="remark"
                               id="appversion_remark" value="${entity.remark }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">md5</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="encryption"
                               id="appversion_encryption" value="${entity.encryption }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">渠道参数</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="channel"
                               id="appversion_channel" value="${entity.channel }">
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
        var endTime = $("#appversion_endTime").val();
        var beginTime = $("#appversion_beginTime").val();
        var changeProperties = $('input[name="changeProperties"]:checked')
                .val();
        var state = $('input[name="state"]:checked').val();
        var popup = $('input[name="popup"]:checked').val();
        var versionNumber = $("#appversion_versionNumber").val();
        var downloadUrl = $("#appversionFile_input").val();
        var apptype = $('#appTypeSelect').val();
        var tag = $('input[name="tag"]:checked').val();
        var changeDesc = $("#appversion_changeDesc").val();
        debugger
        if (changeProperties == undefined) {
            $("#tip").html("请选择更新属性");
            b = false;
        }
        if (versionNumber == null || versionNumber.trim() == '') {
            $("#tip").html("请输入版本号");
            b = false;
        }
        if (popup == null || popup.trim() == '') {
            $("#tip").html("请选择是否弹窗");
            b = false;
        }
        if (state == undefined) {
            $("#tip").html("请选择状态");
            b = false;
        }
        if (apptype == undefined) {
            $("#tip").html("请选择app类型");
            b = false;
        }
        if (tag == undefined) {
            $("#tip").html("请选择版本类型");
            b = false;
        }
        if (changeDesc == null || changeDesc.trim() == '') {
            $("#tip").html("更新描述不能为空");
            b = false;
        }
        if (downloadUrl == null || downloadUrl.trim() == '') {
            $("#tip").html("更新地址不能为空");
            b = false;
        }
        return b;
    }

    $(function () {

    	$.ajax({
			url: "apptype/getAllAppType",
			method: "post",
			data: {
				
			},
			success:function(obj){
				$.each($.parseJSON(obj), function(k, v){
					var apptype = $("#appTypeHidden").val();
					debugger
					if(v.appTypeCode == apptype ){
						$("#appTypeSelect").append("<option selected=selected value='"+v.appTypeCode+"'>"+v.appTypeName+"</option>");
					}else{
						$("#appTypeSelect").append("<option  value='"+v.appTypeCode+"'>"+v.appTypeName+"</option>");
					} 
				});
			},
			error: function(){
			}
		});
    	
        $('#uploadappversion').click(function () {
            var file = document.appversionForm.appversionFile.value;
            if (file == "") {
                $("#tip").html("请选择文件");
                return false;
            }
            $.ajaxFileUpload({
                type: "post",
                dataType: "json",
                fileElementId: 'appversionFile',
                url: "upload/appversion.htm",
                data: {},
                error: function (data) {
                    alert("错误！");
                },
                success: function (data) {
                    if (data.result = 'success') {
                        alert("上传文件成功！");
                        $('#appversionFile_input').val(data.imgUrl);
                        $('#appversion_encryption').val(data.md5Str);
                        $("#tip").html("");
                    } else {
                        alert(data.msg);
                    }
                }
            });
        });
    });
</script>
