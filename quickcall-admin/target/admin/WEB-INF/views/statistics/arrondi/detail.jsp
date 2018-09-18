<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true"></button>
            <h3 id="myModalLabel"><span id="pName"></span></h3>
        </div>
        <div>
            <c:if test="${empty list}">
                <p style="text-align: center">暂无数据</p>
            </c:if>
            <c:if test="${not empty list}">
                <table class="table dataTable no-footer" role="grid" aria-describedby="example_info">
                    <thead>
                        <tr role="row" class="odd">
                            <th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">用户手机号码
                            </th>
                            <th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">点击次数
                            </th>
                            <th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">点击时间
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="info" items="${list}">
                        <tr role="row" class="odd">
                            <td class="text-center">${info.phoneNum}</td>
                            <td class="text-center">${info.count}</td>
                            <td class="text-center">${info.date}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
</div>

<script>
    $(function() {
        var name = localStorage.getItem("arrondi");
        $("#pName").html('<font color="red">'+ name + '</font>' + "专区产品详情数据列表");
    });
</script>

<script type="text/javascript"
        src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8">
</script>