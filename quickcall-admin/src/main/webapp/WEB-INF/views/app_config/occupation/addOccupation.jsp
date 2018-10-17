<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog" style="width: 30%;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }职业</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post"
                  action="occupation/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">职业名称<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control required" id="occupationName" name="name" value="${entity.name }">
                        <input type="hidden" class="form-control" name="id"  value="${entity.id }">
                    </div>
                </div>
                <div>
                    <label class="col-sm-4 control-label">职业描述<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control required" id="occuDescribe" name="occuDescribe" value="${entity.occuDescribe }">
                    </div>
                </div>

                <span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
            </form>
        </div>

        <div class="modal-footer">
            <button class="btn btn-default" data-dismiss="modal"
                    aria-hidden="true">取消</button>
            <button class="btn btn-primary" data-dismiss="modal">保存</button>
        </div>


    </div>
</div>
<script type="text/javascript">
    function check_fun(){
        $("#tip").html("");
        var b = true;
        var occupationName = $("#occupationName").val();
        if(occupationName == null || occupationName.trim() == ''){
            $("#tip").html("请输入职业名称");
            b = false;
        }

        return b;
    }

    $(function() {

    });
</script>