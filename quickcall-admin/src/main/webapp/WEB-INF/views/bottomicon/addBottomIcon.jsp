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
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }底部icon</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
                  action="bottomIcon/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-3 control-label">之前的名称：</label>
                    <div class="col-sm-9">
                        <select class="form-control" id="preIconName" name = "preIconName" disabled = "disabled">
                            <option value="1" ${entity.preIconName=='1'? 'selected':'' }>首页</option>
                            <option value="2" ${entity.preIconName=='2'? 'selected':'' }>贷款大全</option>
                            <option value="3" ${entity.preIconName=='3'? 'selected':'' }>活动</option>
                            <option value="4" ${entity.preIconName=='4'? 'selected':'' }>社区</option>
                            <option value="5" ${entity.preIconName=='5'? 'selected':'' }>工具</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">icon位置：<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-9">
                        <input type="text" maxlength="1" class="form-control required" id="iconPosition" name="iconPosition"
                               value="${entity.iconPosition }"/>

                        <input type="hidden"
                               class="form-control" name="preIconImg" id="preIconImg_input"
                               value="${entity.preIconImg }"/>
                        <input type="hidden"
                               class="form-control" name="aftIconImg" id="aftIconImg_input"
                               value="${entity.aftIconImg }"/>
                        <input type="hidden"
                               class="form-control" name="id" id="id"
                               value="${entity.id }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">icon名称：<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control required" id="iconName" name="iconName"
                               value="${entity.iconName }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">点击前icon图片<c:if test="${entity eq null }"><font
                            color="red">&nbsp;*</font></c:if>
                    </label>

                    <div class="col-sm-9">
                        <div class="input-group">
                            <input type="file" class="form-control" id="appversionFile"
                                   name="appversionFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadPreIcon">上传图片</button>
							</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">背景图片</label>
                    <div class="col-sm-10">
                        <div class="col-sm-6">
                            <img src="${entity.preIconImg }" alt="暂无图片，点击上传！" class="img-rounded"
                                 style="max-width: 100%; max-height: 300px;" id="bannerFile_img">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">点击后icon图片<c:if test="${entity eq null }"><font
                            color="red">&nbsp;*</font></c:if>
                    </label>

                    <div class="col-sm-9">
                        <div class="input-group">
                            <input type="file" class="form-control" id="appversionFileSecond"
                                   name="appversionFileSecond"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadAftIcon">上传图片</button>
							</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">背景图片</label>
                    <div class="col-sm-10">
                        <div class="col-sm-6">
                            <img src="${entity.aftIconImg }" alt="暂无图片，点击上传！" class="img-rounded"
                                 style="max-width: 100%; max-height: 300px;" id="bannerFile_img_second">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">当前状态：<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-9">
                        <label class="checkbox-inline"> <input type="radio" name="state" value="1" ${entity.state=='1'?'checked':'' }> 有效 </label>
                        <label class="checkbox-inline"> <input type="radio" name="state" value="2" ${entity.state=='2'?'checked':'' }> 无效 </label>
                    </div>
                </div>
                <div id = "forActivity">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">链接：<font color="red">&nbsp;*</font></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control required" id="linkUrl" name="linkUrl"
                                   value="${entity.linkUrl }"/>
                        </div>
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

        var filepath = $("#preIconImg_input").val();
        var filepath2 = $("#aftIconImg_input").val();
        var iconPosition = $("#iconPosition").val();
        var iconName = $("#iconName").val();
        var linkUrl = $("#linkUrl").val();

        var versionRule = $("#versionRule").val();
        var version = $("#version").val();
        if(iconPosition == null || iconPosition.trim() == ''){
            $("#tip").html("请输入icon位置");
            return false;
        } else {
            var reg = /^[1-5]*$/;
            if (!reg.test(iconPosition)) {
                $("#tip").html("icon位置请输入1至5之间的数字");
                return false;
            }

        }
        if(iconName == null || iconName.trim() == ''){
            $("#tip").html("请输入icon名称");
            return false;
        }
        var preIconName = "${entity.preIconName}";
        if (preIconName == 3) {
            if(linkUrl == null || linkUrl.trim() == ''){
                $("#tip").html("请输入链接");
                return false;
            }
        }

        if (filepath == null || filepath == '') {
            $("#tip").html("请上传icon点击前图片");
            return false;
        }
        if (filepath2 == null || filepath2 == '') {
            $("#tip").html("请上传icon点击后图片");
            return false;
        }


        return true;
    }
    $(function () {
        var preIconName = "${entity.preIconName}";
        if (preIconName == '3') {
            $("#forActivity").show();
        } else {
            $("#forActivity").hide();
        }
        $('#uploadPreIcon').click(function () {
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
                    "id": "${entity.id }"
                },
                error: function (data) {
                    alert("错误！");
                },
                success: function (data) {
                    if (data.result = 'success') {
                        console.info(data);
                        alert("上传图片成功！");
                        $('#preIconImg_input').val(data.imgUrl);
                        $('#bannerFile_img').attr("src", data.imgUrl);
                        $("#tip").html("");
                    } else {
                        alert(data.msg);
                    }
                }
            });
        });
        $('#uploadAftIcon').click(function () {
            var file = document.bannerForm.appversionFileSecond.value;
            if (file == "") {
                $("#tip").html("请选择图片");
                return false;
            }
            $.ajaxFileUpload({
                type: "post",
                dataType: "json",
                fileElementId: 'appversionFileSecond',
                url: "upload/appversionSecond.htm",
                data: {
                    "id": "${entity.id }"
                },
                error: function (data) {
                    alert("错误！");
                },
                success: function (data) {
                    if (data.result = 'success') {
                        console.info(data);
                        alert("上传图片成功！");
                        $('#aftIconImg_input').val(data.imgUrl);
                        $('#bannerFile_img_second').attr("src", data.imgUrl);
                        $("#tip").html("");
                    } else {
                        alert(data.msg);
                    }
                }
            });
        });
    });
</script>