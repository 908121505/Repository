<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" href="resources/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<script type="text/javascript" language="javascript" src="resources/bootstrap/js/bootstrap-datetimepicker.min.js" charset="utf-8"></script>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">X
            </button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }弹窗配置</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
                  action="layLevel/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">图片<c:if test="${entity eq null }"><font
                            color="red">&nbsp;*</font></c:if>
                    </label>

                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="file" class="form-control" id="appversionFile"
                                   name="appversionFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadBanner">上传图片</button>
							</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">背景图片</label>
                    <div class="col-sm-10">
                        <div class="col-sm-6">
                            <img src="${entity.layImg }" alt="暂无图片，点击上传！" class="img-rounded"
                                 style="max-width: 100%; max-height: 300px;" id="bannerFile_img">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">链接地址：<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control required" id="lay_url" name="layUrl"
                               value="${entity.layUrl }"/>
                        <input type="hidden"
                               class="form-control" name="layImg" id="bannerFile_input"
                               value="${entity.layImg }"/>
                        <input type="hidden"
                               class="form-control" name="layLevelId" id="layLevelId"
                               value="${entity.layLevelId }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">标题：<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control required" id="lay_topic" name="layTopic"
                               value="${entity.layTopic }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">设备类型：<font color="red">&nbsp;*</font></label>
                    <div class="input-group">
                        <select class="form-control" id="deviceType" name="deviceType">
                            <option value="0" ${entity.deviceType=='0'? 'selected':'' }>所有设备</option>
                            <option value="1" ${entity.deviceType=='1'? 'selected':'' }>iOS</option>
                            <option value="2" ${entity.deviceType=='2'? 'selected':'' }>Android</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">版本号：<font color="red">&nbsp;*</font></label>
                    <div class="input-group">
                        <select class="form-control" id="versionRule" name = "versionRule">
                            <option value="0" ${entity.versionRule=='0'? 'selected':'' }>所有版本</option>
                            <option value="1" ${entity.versionRule=='1'? 'selected':'' }>大于</option>
                            <option value="2" ${entity.versionRule=='2'? 'selected':'' }>小于</option>
                            <option value="3" ${entity.versionRule=='3'? 'selected':'' }>等于</option>
                            <option value="4" ${entity.versionRule=='4'? 'selected':'' }>大于等于</option>
                            <option value="5" ${entity.versionRule=='5'? 'selected':'' }>小于等于</option>
                        </select>
                        <input type="text" class="form-control required" id="version" name="version"
                               value="${entity.version }"/> 格式：4.0.0
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">当前状态：<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <label class="checkbox-inline"> <input type="radio" name="state" value="1" ${entity.state=='1'?'checked':'' }> 开启 </label>
                        <label class="checkbox-inline"> <input type="radio" name="state" value="2" ${entity.state=='2'?'checked':'' }> 关闭 </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">展示时间：<font color="red">&nbsp;*</font></label>
                    <div class="input-group date">
                        <input class="form-control" type="text" id="startTime" name ="startTime" value = "${entity.startTime}">
                        <div class="input-group-addon">到</div>
                        <input class="form-control" type="text" id="endTime" name = "endTime" value = "${entity.endTime}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">触发时间：<font color="red">&nbsp;*</font></label>
                    <div class="input-group">
                        <select class="form-control" id="startType" name="startType">
                            <option value="1" ${entity.startType=='1'? 'selected':'' }>每日首次打开</option>
                            <option value="2" ${entity.startType=='2'? 'selected':'' }>每次打开</option>
                            <option value="3" ${entity.startType=='3'? 'selected':'' }>首次打开</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">排序值：<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control required" id="sort_new" name="sort"
                               value="${entity.sort }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">弹出位置：<font color="red">&nbsp;*</font></label>
                    <div class="input-group">
                        <select class="form-control" id="lay_location" name="layLocation">
                            <option value="1" ${entity.layLocation=='1'? 'selected':'' }>首页</option>
                            <option value="2" ${entity.layLocation=='2'? 'selected':'' }>贷款大全</option>
                            <option value="3" ${entity.layLocation=='3'? 'selected':'' }>活动</option>
                            <option value="4" ${entity.layLocation=='4'? 'selected':'' }>社区</option>
                            <option value="5" ${entity.layLocation=='5'? 'selected':'' }>工具</option>
                        </select>
                    </div>
                </div>
                <span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
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

        var filepath = $("#bannerFile_input").val();
        var layUrl = $("#lay_url").val();
        var layTopic = $("#lay_topic").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var sortNew = $("#sort_new").val();
        var state = $('input[name="state"]:checked').val();

        var versionRule = $("#versionRule").val();
        var version = $("#version").val();
        if (versionRule != 0) {
            if(version == null || version.trim() == ''){
                $("#tip").html("请输入版本号，格式：4.0.0");
                return false;
            }
        }
        if(layUrl == null || layUrl.trim() == ''){
            $("#tip").html("请输入链接");
            return false;
        }
        if(layTopic == null || layTopic.trim() == ''){
            $("#tip").html("请输入标题");
            return false;
        }
        if(startTime == null || startTime.trim() == ''){
            $("#tip").html("请选择开始时间");
            return false;
        }
        if(endTime == null || endTime.trim() == ''){
            $("#tip").html("请选择结束时间");
            return false;
        }
        if(sortNew == null || sortNew.trim() == ''){
            $("#tip").html("请输入排序");
            return false;
        }
        if (state == undefined) {
            $("#tip").html("请选择状态");
            return false;
        }

        if (filepath == null || filepath == '') {
            $("#tip").html("请上传图片");
            return false;
        }


        return true;
    }
    $(function () {
        $('#startTime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:ss'
        });
        $('#endTime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:ss'
        });
        $('#uploadBanner').click(function () {
            var file = document.bannerForm.appversionFile.value;
            if (file == "") {
                $("#tip").html("请选择图片");
                return false;
            }
            $.ajaxFileUpload({
                type: "post",
                dataType: "json",
                fileElementId: 'appversionFile',
                url: "upload/appversion.htm",
                data: {
                    "id": "${entity.layLevelId }"
                },
                error: function (data) {
                    alert("错误！");
                },
                success: function (data) {
                    if (data.result = 'success') {
                        console.info(data);
                        alert("上传图片成功！");
                        $('#bannerFile_input').val(data.imgUrl);
                        $('#bannerFile_img').attr("src", data.imgUrl);
                        $("#tip").html("");
                    } else {
                        alert(data.msg);
                    }
                }
            });
        });
    });
</script>