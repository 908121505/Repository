<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
	/*样式初始化*/
	ul,ol{list-style: none}
	button::-moz-focus-inner, input[type="reset"]::-moz-focus-inner, input[type="button"]::-moz-focus-inner, input[type="submit"]::-moz-focus-inner, input[type="file"] > input[type="button"]::-moz-focus-inner {border:none;padding:0;}
	.clearfix{zoom: 1;}
	.clearfix:after {content:" ";clear: both;display: block;height: 0;visibility: hidden;}
	.icon{background: url("resources/css/images/icon.png") no-repeat;}

	/*select 下拉多选样式*/
	.region-sel-box{position:relative;float: left;cursor: pointer;}
	.region-box{border:1px solid #d6d6d6;line-height: 33px;}
	.region-com{float: left;background: #fff;width:221px;min-height: 33px;max-height: 85px;overflow-y: auto;}
	#J_terminal_pop .region-com{width:343px;}
	.region-com ul{padding-bottom: 4px;}
	.region-com li{float:left;margin-top:4px;margin-left: 4px;padding:0 6px;line-height: 23px;background: #ff4200;border-radius: 25px;color:#fff;font-size: 13px;}
	.region-com li i{display: inline-block;margin-left: 3px;width: 9px;height: 9px;background-position: -240px -35px;cursor: pointer;}
	.region-right{float: left;background: #fff;width:27px;}
	.region-right.disabled{background: #eee;}
	.region-right i{display: inline-block;margin-top:13px;margin-left:8px;width: 10px;height: 5px;background-position: -200px -35px;}
	.region-list{position: absolute;width:248px;background: #fff;border-left:1px solid #d6d6d6;border-right:1px solid #d6d6d6;border-bottom:1px solid #d6d6d6;display: none;z-index: 200;max-height: 210px;overflow-y: auto;}
	.region-list li{line-height: 30px}
	.region-list li:hover{background: #eeeeee;}
	.region-list li .text{float: left;padding-left:8px;}
	.region-list li.selected .text{color:#ff4200;}
	.region-list li.selected .check{background-position: -160px -35px;border:none;}
	.region-list .check{float: right;width:17px;height: 17px;margin-top:6px;margin-right: 9px;cursor: pointer;background-position: -280px -35px;}


</style>
<script src="resources/js/select_checkbox_base.js" />
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }发帖信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="articlepostForm" name="articlepostForm" enctype="multipart/form-data"
				  action="articlePost/save${empty entity?'Insert':'Update' }.htm"
				  role="form">
				<c:if test="${empty entity==false}">
					<div class="form-group">
						<label class="col-sm-4 control-label">发帖id</label>

						<div class="col-sm-8">
							<input type="text" id="articlePostId" readonly="readonly" class="form-control" name="articlePostId"
								   value="${entity.articlePostId}">
						</div>

					</div>
				</c:if>
				<div class="form-group">
					<label class="col-sm-4 control-label">发帖标题</label>
					<div class="col-sm-8">
						<c:if test="${empty entity==false}">
							<%-- <input type="hidden" value="${entity.createTime }" id="createTime" name="createTime" /> --%>
							<input type="hidden" value="${entity.createMan }" id="createMan" name="createMan" />
							<input type="hidden" value="${entity.modifyTime }" id="modifyTime" name="modifyTime" />
							<input type="hidden" value="${entity.modifyMan }" id="modifyMan" name="modifyMan" />
							<input type="hidden" value="${entity.accountId }" id="accountId" name="accountId" />
							<input type="hidden" value="${entity.accountAvatar }" id="accountAvatar" name="accountAvatar" />
							<input type="hidden" value="${entity.accountNickName }" id="accountNickName" name="accountNickName" />
						</c:if>
						<input type="text" class="form-control" id="article_post_title" name="articlePostTitle" value="${entity.articlePostTitle}">
					</div>
				</div>

				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">发帖内容<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<div>
								<textarea  id="article_post_content" name="articlePostContent"  cols="45" rows="20" style="overflow:auto">
									${entity.articlePostContent}
								</textarea>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">发帖图片</label>
					<div class="col-sm-8">
						<div class="col-sm-6" id="imgShow">
							<input type="hidden" class="form-control" name="articlePostImg"
								   id="articlePostImg" value="${entity.articlePostImg }">
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-4 control-label">阅读数</label>
					<div class="col-sm-8">
						<input type="text" id="readNum"  class="form-control" name="readNum"
							   value="${entity.readNum}" >
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">点赞数</label>
					<div class="col-sm-8">
						<input type="text" id="praiseNumber"  class="form-control" name="praiseNumber"
							   value="${entity.praiseNumber}" >
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">评论数</label>
					<div class="col-sm-8">
						<input type="text" id="numberComment" readonly="readonly" class="form-control" name="numberComment"
							   value="${entity.numberComment}" >
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-4 control-label">帖子类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<select class="form-control" id="is_essence" name="isEssence" onchange="topOrderonchange(this);">
							<option value="1" ${entity.isEssence!='1'?'selected':'' }>普通</option>
							<option value="2" ${entity.isEssence=='2'?'selected':'' }>精华</option>
							<option value="3" ${entity.isEssence=='3'?'selected':'' }>热门</option>
							<option value="4" ${entity.isEssence=='4'?'selected':'' }>IOS审核专区</option>
							<option value="5" ${entity.isEssence=='5'?'selected':'' }>置顶</option>
						</select>
					</div>
				</div>
				<div class="form-group" id="topOrderDiv">
					<label class="col-sm-4 control-label">置顶顺序</label>
					<div class="col-sm-8">
						<input type="text" id="topOrder"  class="form-control" name="topOrder"
							   value="${entity.topOrder}" >
					</div>
				</div>
				<div class="form-group" id="positionDiv">
					<label class="col-sm-4 control-label">帖子位置</label>
					<div class="col-sm-8">
						<input onkeyup="this.value=this.value.replace(/[^\d]/g,'');" type="text" id="articlePosition"  class="form-control" name="articlePosition"
							   value="${entity.articlePosition}" >
					</div>
				</div>
				<div class="form-group" id="positionDiv">
					<label class="col-sm-4 control-label">帖子标签</label>
					<div class="col-sm-8">
						<c:forEach var="lableInfo" items="${articlePostLableList }">
								<input type="checkbox"   name="articlePostLable"
									   value="${lableInfo.id}" >&nbsp;${lableInfo.lableName}
								<br/>
						</c:forEach>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">帖子专区<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<select class="form-control" id="prefectureId" name="prefectureId">
							<c:forEach var="info" items="${zoneProductList }">
								<option value="${info.zoneId }">${info.zoneName }</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">状态 <font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<select class="form-control"  id="is_state" name="state">
							<option value="1" ${entity.state =='1'?'selected':'' }>有效</option>
							<option value="2" ${entity.state=='2'?'selected':'' }>无效</option>
							<option value="3" ${entity.state=='3'?'selected':'' }>待审核</option>
							<option value="4" ${entity.state=='4'?'selected':'' }>已驳回</option>
							<option value="5" ${entity.state=='5'?'selected':'' }>违禁</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">链接地址</label>
					<div class="col-sm-8">
						<input type="text" id="linkUrl"  class="form-control" name="linkUrl"
							   value="${entity.linkUrl}">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">图片上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if>
					</label>

					<div class="col-sm-8">
						<div class="input-group">
							<input type="file" class="form-control" id="appversionFile"
								   name="appversionFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadBanner">上传图片</button>
							</span>
						</div>
					</div>
				</div>

				<input type="hidden"
					   class="form-control" name="labelBackgroundImg" id="bannerFile_input"
					   value="${entity.labelBackgroundImg }">

				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">精华贴配图</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<img src="${entity.labelBackgroundImg }" alt="暂无图片，点击上传！" class="img-rounded"
								 style="max-width: 100%; max-height: 300px;" id="bannerFile_img">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">缩略内容</label>
					<div class="col-sm-8">
						<div>
							<textarea  id="breviary_content" onchange="check_bre_content(this);"  name="breviaryContent"  cols="45" rows="4" style="overflow:auto">
								${entity.breviaryContent}
							</textarea>
						</div>
					</div>
				</div>




				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">相关产品</label>

					<div class="page-content clearfix" >
						<div class="region-sel-box">
							<div class="region-box clearfix" style="width: 356px;">
								<div class="region-com" style="width: 320px;">
									<ul class="clearfix">
									<c:forEach var="info" items="${products }">

										<c:set var="isBool" value="se"></c:set>
										<c:set var="tempArr" value="${not empty entity?fn:split(entity.productIds,','):''}"></c:set>
										<c:forEach items="${tempArr}" var="tp">
											<c:if test="${tp eq info.productId}">
												<c:set var="isBool" value="te"></c:set>
												<li><span class="text">${info.productName}</span><i class="icon"></i><input hidden="hidden" name="productIds" value="${info.productId}">  </li>
											</c:if>
										</c:forEach>
									</c:forEach>
									</ul>
								</div>
								<div class="region-right"><i class="icon"></i></div>
							</div>
							<div class="region-list" style="width: 356px;">
									<div style="height: 45px;">
										<input type="text" id="searchProductId"  class="form-control" name="searchProductId"
											   placeholder="请输入产品名称" style="width: 76%;float: left;margin-left: 10px;"/>

										<input type="button" onclick="searchProduct()" style="width: 17%;margin: 4px;" value="查询"/>

									</div>


									<ul id="productIdList">
										<c:forEach var="info" items="${products }">
											<li class="clearfix">
												<span class="text">${info.productName}</span>
												<span class="check icon" value="${info.productId}"></span>

											</li>



										</c:forEach>
									</ul>

							</div>
						</div>
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
    function searchProduct() {
        $.ajax({
            type:"POST",
            url: "sms/queryProduct.htm",
            data:{"proName":$("#searchProductId").val(),"num":1,"productCategoryId":"a9e64a034ddc4f62bd6360b58a5f6417"},
            scriptCharset: 'utf-8' ,
            success: function(data){
                if(data !='' && data !='undefine' && $.parseJSON(data).length > 0 ){
                    debugger;
                    $("#productIdList").empty();
                    var productLists = "";
                    for(var i=0;i< $.parseJSON(data).length;i++){
                        productLists += "<li class='clearfix'>";
						productLists += "<span class='text'>"+ $.parseJSON(data)[i].productName +"</span>";
                        productLists += "<span class=\"check icon\" value=\""+ $.parseJSON(data)[i].productId +"\"></span> ";
                        productLists += "</li>";
                    }
                    console.log(productLists);
                    $("#productIdList").append(productLists);
				}else{
                    console.log("无数据");
				}
				console.log("执行成功...");
            }
        });
    }

    var i = 0;
    function clickNum(obj){
        console.log(obj);
        if($("input[name='productIds']:checked").length > 3)
        {
            alert("只能选择三个产品");
            obj.checked = false;
            return false;
        }
    }

    function check_bre_content(obj){
        if($.trim(obj.value).length>20){
            alert("缩略内容不能超过20个字符");
            obj.value="";
        }
        //if($.trim(obj.value))
    }


    $(function (){

    	if('${entity.isEssence}'=='5'){
    		$('#topOrderDiv').show();
    	}else{
    		$('#topOrderDiv').hide();
    	}
        if('${entity.isEssence}'=='3'){
            $('#positionDiv').show();
        }else{
            $('#positionDiv').hide();
        }


        $("input[name='articlePostLable']").each(function(){
            <c:forEach var="lableInfo" items="${lableMap }">
                var lableId = "${lableInfo.relationArticlePostLableId}";
                if ( lableId == this.value) {
                    this.checked = true;
                }
			</c:forEach>
        });

        var articlePostImg =  $("#articlePostImg").val();
        if(articlePostImg=="" || articlePostImg ==null){
            return;
        }
        var $imgArry = $(articlePostImg.split(","));
        $imgArry.each(function(index,element){
            var img = "<img src="+element+" class='img-rounded' style='width: 120px; height: 180px;padding:5px;'/>"
            $("#imgShow").append(img);
        });

    });

    //添加ckeditor编辑器；
    var editor ;
    if(!CKEDITOR.instances.article_post_content){  //判定content是否存在
        editor= CKEDITOR.replace("article_post_content");
    }else{
        addCkeditor("article_post_content");
    }

    function addCkeditor(id){
        var editor2 = CKEDITOR.instances[id];
        if(editor2) editor2.destroy(true);//销毁编辑器 content,然后新增一个
        editor = CKEDITOR.replace(id);
    }

    if('${entity}'!=''){
        $("#prefectureId").val('${entity.prefectureId}');

    }
    function delsmallLaelImg( imgsrc){
        $("#"+imgsrc+"src").attr("src","");
        $("#"+imgsrc).val("");
    }

    function check_fun(){
        $("#tip").html("");
        var b = true;

        var editor_data = CKEDITOR.instances.article_post_content.getData();

        if($("input[name='articlePostLable']:checked").length > 3)
        {
            $("#tip").html("只能选择三个标签");
            b = false
            return b;
        }
        $("#article_post_content").val(editor_data);
        var articlePostTitle = $("#article_post_title").val();

        if(editor_data == null || editor_data.trim() == ''){
            $("#tip").html("请输入帖子内容");
            b = false;
        }

        if($('#is_essence').val()==2){
            if($('#bannerFile_input').val()==null||$('#bannerFile_input').val().trim()==''){
                $("#tip").html("请上传精华贴配图");
                b = false;
            }
        }

        return b;
    }


     function topOrderonchange(obj){
        if($('#is_essence').val()==5){
        	$('#topOrderDiv').show();
        }else{
        	$('#topOrderDiv').hide();
        }
		 if($('#is_essence').val() == 3){
			 $('#positionDiv').show();
		 }else{
			 $('#positionDiv').hide();
		 }

    }



    $(function() {
        $('#uploadBanner').click(function() {
            var file = document.articlepostForm.appversionFile.value;
            if(file == ""){
                $("#tip").html("请选择图片");
                return false;
            }
            $.ajaxFileUpload({
                type : "post",
                dataType : "json",
                fileElementId : 'appversionFile',
                url : "upload/appversion.htm",
                data : {
                    "id" : "${entity.articlePostId }"
                },
                error : function(data) {
                    alert("错误！");
                },
                success : function(data) {
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