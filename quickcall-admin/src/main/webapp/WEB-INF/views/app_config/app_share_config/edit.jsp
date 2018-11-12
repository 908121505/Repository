<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">
            </button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }app分享配置</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="appShareConfigForm"
                  name="appShareConfigForm"
                  action="appShareConfig/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">分享标题<font
                            color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="hidden" class="form-control" name="id" value="${entity.id }">
                        <input type="text" class="form-control" name="title" value="${entity.title }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">分享内容<font color="red">&nbsp;*</font></label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="content" value=${entity.content }>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">分享链接<font color="red">&nbsp;*</font></label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="linkUrl" value=${entity.linkUrl }>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">分享类型<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <select class="form-control" id="type" name="type">
                            <option value="1" ${entity.type=='1'? 'selected':'' }>个人主页分享</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">备注</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="remark" value="${entity.remark }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">图片上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if>
                    </label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="hidden" class="form-control" name="iconUrl" id="iconUrl_input" value="${entity.iconUrl }">
                            <input type="file" class="form-control" id="appversionFile" name="appversionFile">
                            <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadBanner">上传图片</button>
							</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="fadeUser_head_img" class="col-sm-2 control-label">图片</label>
                    <div class="col-sm-10">
                        <div class="col-sm-6">
                            <img src="${empty entity.iconUrl ? DEFAULT_IMG : entity.iconUrl }" alt="暂无图片，点击上传！" class="img-rounded" style="max-width: 100%; max-height: 300px;" id="icon_img">
                        </div>
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
        var title = $('input[name="title"]').val();
        if (title == null || title.trim() == '') {
            $("#tip").html("请输入分享标题");
            b = false;
        }
        return b;
    }

    $(function() {
        $('#uploadBanner').click(function() {
            var file = document.appShareConfigForm.appversionFile.value;
            if(file == ""){
                $("#tip").html("请选择图片");
                return false;
            }
            $.ajaxFileUpload({
                type : "post",
                dataType : "json",
                fileElementId : 'appversionFile',
                url : "upload/fadeCustomerHeadImg.htm",
                data : {},
                error : function(data) {
                    alert("错误！");
                },
                success : function(data) {
                    if (data.result = 'success') {
                        console.info(data);
                        alert("上传图片成功！");
                        $('#iconUrl_input').val(data.imgUrl);
                        $('#icon_img').attr("src", data.imgUrl);
                        $("#tip").html("");
                    } else {
                        alert(data.msg);
                    }
                }
            });
        });
    });
</script>
