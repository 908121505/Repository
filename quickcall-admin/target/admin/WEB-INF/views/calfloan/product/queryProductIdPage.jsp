<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<div class="content1">
    <div class="header">
        <ul class="breadcrumb">
            <li>产品ID查询</li>
            <li class="active">产品ID查询</li>
        </ul>
        <div class="row">
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">产品名称</div>
                        <input class="form-control" type="text" id="pname">
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-primary btn-small btn-block"
                        id="query">
                    <i class="glyphicon glyphicon-search"></i> 查询
                </button>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">产品ID</div>
                        <input class="form-control" style = "width:530px" type="text" id="pid" value="">
                    </div>
                </div>
            </div>
        </div>

    </div>

    <script>
        $('#query').click(function () {
            $.ajax({
                url: '/monitorInfo/queryProductId',
                type: 'POST', //GET
                async: true,    //或false,是否异步
                data: {
                    productName: $('#pname').val()
                },
                timeout: 5000,    //超时时间
                dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                success: function (data, textStatus, jqXHR) {
                    $('#pid').val(data);
                },
                error: function (xhr, textStatus) {
                },
                complete: function () {
                }
            });
        });
    </script>
</div>
