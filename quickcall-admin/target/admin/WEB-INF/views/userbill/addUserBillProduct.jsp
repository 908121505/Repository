<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">X
            </button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }记账产品信息</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
                  action="userBillProduct/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">产品名称<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control required" id="product_name" name="productName"
                               value="${entity.productName }"/>
                        <input type="hidden"
                               class="form-control" name="productImg" id="bannerFile_input"
                               value="${entity.productImg }"/>
                        <input type="hidden"
                               class="form-control" name="billProductId" id="billProductId"
                               value="${entity.billProductId }"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-2 control-label">是否默认显示<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <label class="checkbox-inline"> <input type="radio"
                                                               name="isRecommand"
                                                               value="1" ${entity.isRecommand=='1'?'checked':'' }>
                            是
                        </label> <label class="checkbox-inline"> <input type="radio"
                                                                        name="isRecommand"
                                                                        value="0" ${entity.isRecommand=='0'?'checked':'' }>
                        否
                    </label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">图片上传<c:if test="${entity eq null }"><font
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
                            <img src="${entity.productImg }" alt="暂无图片，点击上传！" class="img-rounded"
                                 style="max-width: 100%; max-height: 300px;" id="bannerFile_img">
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

        var state = $('input[name="isRecommand"]:checked').val();
        var filepath = $("#bannerFile_input").val();


        var product_name = $("#product_name").val();
        if (state == undefined) {
            $("#tip").html("请选择是否显示");
            return false;
        }

        if (product_name.length == 0) {
            $("#tip").html("请输入产品名称");
            return false;
        }

        if (filepath == null || filepath == '') {
            $("#tip").html("请上传图片");
            return false;
        }


        return true;
    }
    $(function () {
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
                    "id": "${entity.billProductId }"
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