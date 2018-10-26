<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link type="text/css"  href="resources/bootstrap/css/bootstrap-select.min.css"  rel="stylesheet" />
<script type="text/javascript" language="javascript" src="resources/bootstrap/js/bootstrap-select.min.js"  charset="utf-8"></script>

<div class="modal-dialog" style="width: 50%;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }弹窗广告</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="sysUserForm" name="advertisementForm"
                  action="advertisement/save${empty entity?'Insert':'Update' }.htm"
                  role="form">

                <%--<input  class="form-control" name="id"  value="${entity.id }">
                <input  class="form-control" name="imageUrl" id="advertisementImgFile_input" value="${entity.imageUrl }">--%>

                <input type="hidden" class="form-control" name="id" value="${entity.id }">
                <input type="hidden" class="form-control" name="imageUrl" id="advertisementImgFile_input"
                       value="${entity.imageUrl }">
                <div class="form-group">
                    <label class="col-sm-2 control-label">广告标题<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control required" id="title" name="title"
                               value="${entity.title }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">广告名称<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control required" id="name" name="name" value="${entity.name }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">广告描述<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control required" id="adDescrible" name="adDescrible"
                               value="${entity.adDescrible }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">生效时间<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" id="banner_startTime" class="form-control"
                               onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01',maxDate:'#F{$dp.$D(\'banner_endTime\')}'})"
                               value="${entity.startTime }" name="startTime" autocomplete="off"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">结束时间<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" id="banner_endTime" class="form-control"
                               onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'banner_startTime\')}',maxDate:'2050-12-31'})"
                               value="${entity.endTime }" name="endTime" autocomplete="off"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="imageUrl" class="col-sm-2 control-label">图片</label>
                    <div class="col-sm-10">
                        <div class="col-sm-6">
                            <img src="${entity.imageUrl }" alt="暂无图片" class="img-rounded"
                                 style="max-width: 100%; max-height: 300px;" id="imageUrl">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">图片上传<c:if test="${entity eq null }"><font
                            color="red">&nbsp;*</font></c:if>
                    </label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="file" class="form-control" id="advertisementImgFile"
                                   name="advertisementImgFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadBanner">上传图片</button>
							</span>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">选择生效版本<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <select  class="form-control selectpicker" id="appVersionSelect" name="appVersionIdList" multiple>
                            <%--<option value="0">请选择</option>
                            <option value="1">1.0</option>
                            <option value="2">2.0</option>--%>
                        </select>

                    </div>
                </div>
                 <c:if test="${not empty entity}">
                 <div class="form-group">
                    <label class="col-sm-2 control-label">当前生效版本<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control required" id="appVersion" name="appVersion" value="${entity.appVersion }"/>
                    </div>
                </div>
                </c:if>

                <div class="form-group">
                    <label class="col-sm-2 control-label">跳转地址<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control required" id="url" name="url" value="${entity.url }">
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

<script type="text/javascript">
    function check_fun() {
        <%--$("#tip").html("");--%>

        <%--var title = $("#banner_title").val();--%>
        <%--if(title == null || title.trim() == ''){--%>
        <%--$("#tip").html("请输入标题");--%>
        <%--return false;--%>
        <%--}--%>
        <%--if(title&&title.length>150){--%>
        <%--$("#tip").html("标题不能超过150个字");--%>
        <%--return false;--%>
        <%--}--%>

        <%--var url = $("#banner_url").val();--%>
        <%--if(url == null || url.trim() == ''){--%>
        <%--if($("#clickType").val() == 1){--%>
        <%--$("#tip").html("请输入HTML链接");--%>
        <%--return false;--%>
        <%--}--%>
        <%--if($("#clickType").val() == 2){--%>
        <%--$("#tip").html("请输入个人主页的主页ID");--%>
        <%--return false;--%>
        <%--}--%>
        <%--if($("#clickType").val() == 3){--%>
        <%--$("#tip").html("请输入分类页的类别ID");--%>
        <%--return false;--%>
        <%--}--%>
        <%--}--%>

        <%--var sort = $("#banner_sort").val();--%>
        <%--if(sort == null || sort.trim() == ''){--%>
        <%--$("#tip").html("请输入序号");--%>
        <%--return false;--%>
        <%--}--%>
        <%--if(sort&&sort.length>9){--%>
        <%--$("#tip").html("序号不能超过9个字");--%>
        <%--return false;--%>
        <%--}--%>

        <%--var startTime = $("#banner_startTime").val();--%>
        <%--var endTime = $("#banner_endTime").val();--%>
        <%--if(startTime == null || startTime.trim() == ''){--%>
        <%--$("#tip").html("请输入开始时间");--%>
        <%--return false;--%>
        <%--}--%>
        <%--if(endTime == null || endTime.trim() == ''){--%>
        <%--$("#tip").html("请输入结束时间");--%>
        <%--return false;--%>
        <%--}--%>
        <%--if(endTime <= startTime){--%>
        <%--$("#tip").html("开始时间必须大于结束时间");--%>
        <%--return false;--%>
        <%--}--%>

        <%--var versionRule = $("#appVersionRule").val();--%>
        <%--var version = $("#appVersion").val();--%>
        <%--if (versionRule != 0) {--%>
        <%--if(version == null || version.trim() == ''){--%>
        <%--$("#tip").html("请输入版本号，格式：4.0.0");--%>
        <%--return false;--%>
        <%--}--%>
        <%--}--%>

        <%--if('${entity}' == ''){--%>
        <%--var filepath = $("#bannerFile_input").val();--%>
        <%--if( filepath == null || filepath == ''){--%>
        <%--$("#tip").html("请上传图片");--%>
        <%--return false;--%>
        <%--}--%>

        <%--}--%>

        <%--var remark = $('input[name="remark"]').val();--%>
        <%--if(remark&&remark.length>250){--%>
        <%--$("#tip").html("备注不能超过250个字");--%>
        <%--return false;--%>
        <%--}--%>

        <%--var temp = /^[0-9]*$/;--%>
        <%--if(!temp.test(sort)){--%>
        <%--$("#tip").html("序号必须全部是数字");--%>
        <%--return false;--%>
        <%--}--%>

        return true;
    }

    $(function () {
        $('#uploadBanner').click(function () {
            var file = document.advertisementForm.advertisementImgFile.value;
            if (file == "") {
                $("#tip").html("请选择图片");
                return false;
            }
            $.ajaxFileUpload({
                type: "post",
                dataType: "json",
                fileElementId: 'advertisementImgFile',
                url: "upload/advertisement.htm",
                error: function (data) {
                    alert("错误！");
                },
                success: function (data) {
                    if (data.result = 'success') {
                        alert("上传图片成功！");
                        $('#advertisementImgFile_input').val(data.imgUrl);
                        $('#imageUrl').attr("src", data.imgUrl);
                        $("#tip").html("上传图片成功。");
                    } else {
                        alert(data.msg);
                    }
                }
            });
        });
    });

    if (${empty entity}) {
    }
    $(document).ready(function () {

        $.ajax({
            type: "POST",
            url: "advertisement/getAppVersionList",
            dataType: "json",
            async: false,
            success: function (data) {

                if(data.length > 0){
//                    $('#appVersionSelect').append("<option value=\"\">请选择</option>")
                    var i;
                    for (i = 0; i < data.length; i++) {
                        $('#appVersionSelect').append("<option value="+data[i].id+">"+data[i].appVersion+"</option>")
                    }
                }

                $('#appVersionSelect').selectpicker();
            },
            error: function () {
                $("#tip").html("未知错误");
                b = false;
                return
            }
        });


    });


</script>

<script type="text/javascript" src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

