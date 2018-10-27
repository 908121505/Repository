<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="content1">
    <div class="header">
        <h1 class="page-title">系统主页</h1>
        <ul class="breadcrumb">
            <li>系统主页</li>
            <li class="active">平台数据预览</li>
        </ul>
    </div>
    <table>
        <tr height="500px">
            <td width="5%"></td>
            <td width="20%" align="center" style="font-size: 28px">
                <div style="background-color:  #cccccc;">
                    <lable id=lable1></lable>
                    <br>总用户数
                </div>
            </td>
            <td width="5%"></td>
            <td width="20%" align="center" style="font-size: 28px">
                <div style="background-color:  #cccccc;">
                    <lable id=lable2></lable>
                    <br>今日新用户
                </div>
            </td>
            <td width="5%"></td>
            <td width="20%" align="center" style="font-size: 28px">
                <div style="background-color:  #cccccc;">
                    <lable id=lable3></lable>
                    <br>总营收金额
                </div>
            </td>
            <td width="5%"></td>
            <td width="20%" align="center" style="font-size: 28px">
                <div style="background-color:  #cccccc;">
                    <lable id=lable4></lable>
                    <br>昨日营收金额
                </div>
            </td>
        </tr>
    </table>
    <div class="modal fade" id="insertAndUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
    </div>
</div>
<script type="text/javascript">
    /*$(function () {
        $.ajax({
            url: "account/platfromTJ.htm",
            async: false,
            dataType: "JSON",
            type: 'post',
            success: function (data) {
                $('#lable1').html(data.accountTotal);
                $('#lable2').html(data.accountTodayTotal);
                $('#lable3').html(data.ysAmtTotal != null ? data.ysAmtTotal : 0);
                $('#lable4').html(data.todayysAmtTotal != null ? data.todayysAmtTotal : 0);
            }
        });
    });*/
</script>