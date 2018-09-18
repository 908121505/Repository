<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>

<style type="text/css">


	/**标题相关*/
   .lable_1{
  	 width: 18%;
  	 cursor: pointer;
  	 text-align: center;
  	 padding-left:0px;
  	 padding-right:0px;
   }
   .p_selected{
   	height: 30px;
   	border-radius: 15px; 
   	padding: 0 5px; 
   	background-color: rgba(255, 153, 0, 1); 
   	display: inline-block;
   	color: white;
   	margin:0;
   }
   
   .p_common{
   	height: 30px;
   	border-radius: 15px; 
   	/* padding: 0 15px; */
   	background-color: white;
   	display: inline-block;
   	line-height: 30px;
   	margin:0;
   	width:100%;
   	text-align: center;
   	}
   	
   	.span_1{
   	  font-size: 14px;
   	  padding-top: 5px;
   	  vertical-align: middle;
   	}
   	.span_2{
   	  font-size: 14px;
   	  padding-top: 5px;
   	  vertical-align: middle;
   	}
   	
   	
   	
   	
   	/**内容相关*/
   	.div_two{
   	 	height:100%;
   	 	width:48%;
   	 	display: inline-block;
   	 	cursor: pointer;
   	}
   	.div_two span{
   	    display: inline-block;
		font-size: 14px;
		padding: 6px 20px;
		line-height: 14px;
		margin: 10px 0; 
   	}
    .div_two_span_1{
        border: solid 1px rgba(204, 204, 204, 1);
    }
    #col-sm-10-extra{
    	height:108px;
    	float:right;
    	width:66%;
    }
    .col-sm-10-sub-one{
	    height:100%;
	    width:100%;
	    display: inline-block;
	    padding-top: 10px;
    }
    
    .col-sm-10-sub-two{
	    height:50%;
	    width:100%;
	    display: inline-block;
    }
    
    
    

</style>




</head>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }产品信息</h3>
		</div>
		<div class="modal-body" style="max-height:600px;overflow-y:auto;">
			<form class="form-horizontal" method="post" id="productForm" name="productForm"
				action="bjProduct/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				 <c:if test="${empty entity==false}">
					<div class="form-group">
						<label class="col-sm-4 control-label">产品id</label>
							<div class="col-sm-8">
								<input type="text" id="id" readonly="readonly" class="form-control" name="id"
									value="${entity.id}">
							</div>
						
					</div>
				</c:if>
				<div class="form-group">
					<label class="col-sm-4 control-label">商户位置<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="sort"  class="form-control" name="sort"
							value="${entity.sort}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">产品名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
					  
						<input type="text" class="form-control" id="product_name" name="productName" value="${entity.productName}">
					</div>
				</div>
				
				
				
				
				<div class="form-group">
					<label class="col-sm-4 control-label">产品图片上传</label>
					<div class="col-sm-8">
						<div class="input-group">
							<input type="file" class="form-control" id="productImgFile"
								name="productImgFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button"
									id="uploadproductImg">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">产品logo</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<img src="${entity.productLogo }"
								class="img-rounded" style="max-width: 100px; max-height: 80px;"
								id="productImgsrc"/>
							<input type="hidden" class="form-control" name="productLogo"
							id="productImg" value="${entity.productLogo }">
						</div>
					</div>
				</div>
				
			
				
				<div class="form-group">
					<label class="col-sm-4 control-label">金额<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="amount"  class="form-control" name="amount"
							value="${entity.amount}">
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
		
		
		var productName = $("#product_name").val();
		var sort = $("#sort").val();
		var productLogo = $("#productImg").val(); 
		var amount = $("#amount").val();
		if(sort == null || sort.trim() == ''){
			$("#tip").html("请选择商户位置");
			b = false;
		}
		else if(productName == null || productName.trim() == ''){
			$("#tip").html("请输入产品名称");
			b = false;
		}else if(productLogo == null || productLogo.trim() == ''){
			$("#tip").html("请上传产品logo");
			b = false;
		}else if(amount == null || amount.trim() == ''){
			$("#tip").html("请输入金额范围");
			b = false;
		}
		
		
		return b;
	}
	
	$(function() {
		$('#uploadproductImg').click(function() {
			var file = document.productForm.productImgFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'productImgFile',
				url : "upload/product.htm",
				data : {
					"id" : "${entity.id }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#productImg').val(data.imgUrl);
						$('#productImgsrc').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
		
		$('#uploadsmallLabelImg').click(function() {
			var file = document.productForm.smallLabelImgFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'smallLabelImgFile',
				url : "upload/smalLabelImg.htm",
				data : {
					"id" : "${entity.id }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#smallLabelImg').val(data.imgUrl);
						$('#smallLabelImgsrc').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
		

		$('#applyProcessImg').click(function() {
			var file = document.productForm.applyProcessFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'applyProcessFile',
				url : "upload/applyprocessImg.htm",
				data : {
					"id" : "${entity.id }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图标成功！");
						$('#applyProcess').insertContent(data.imgUrl);
						
					} else {
						alert(data.msg);
					}
				}
			});
		});
		
		/* 在textarea处插入文本 */
		(function($) {
			 $.fn.extend({
			   insertContent : function(myValue, t) {
			   var $t = $(this)[0];
			   if (document.selection) { // ie
			    this.focus();
			    var sel = document.selection.createRange();
			    sel.text = myValue;
			    this.focus();
			    sel.moveStart('character', -l);
			    var wee = sel.text.length;
			    if (arguments.length == 2) {
			    var l = $t.value.length;
			    sel.moveEnd("character", wee + t);
			    t <= 0 ? sel.moveStart("character", wee - 2 * t - myValue.length) : sel.moveStart( "character", wee - t - myValue.length);
			    sel.select();
			    }
			   } else if ($t.selectionStart
			    || $t.selectionStart == '0') {
			    var startPos = $t.selectionStart;
			    var endPos = $t.selectionEnd;
			    var scrollTop = $t.scrollTop;
			    $t.value = $t.value.substring(0, startPos)
			     + myValue
			     + $t.value.substring(endPos,$t.value.length);
			    this.focus();
			    $t.selectionStart = startPos + myValue.length;
			    $t.selectionEnd = startPos + myValue.length;
			    $t.scrollTop = scrollTop;
			    if (arguments.length == 2) {
			    $t.setSelectionRange(startPos - t,
			     $t.selectionEnd + t);
			    this.focus();
			    }
			   } else {
			    this.value += myValue;
			    this.focus();
			   }
			   }
			  })
			 })(jQuery);
		
	});
	

</script>