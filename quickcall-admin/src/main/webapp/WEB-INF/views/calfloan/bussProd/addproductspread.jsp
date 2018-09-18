<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link type="text/css"
	href="resources/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" />
<script type="text/javascript" language="javascript"
	src="resources/bootstrap/js/bootstrap-datetimepicker.min.js"
	charset="utf-8"></script>

<head>

<style type="text/css">

/**标题相关*/
.lable_1 {
	width: 18%;
	cursor: pointer;
	text-align: center;
	padding-left: 0px;
	padding-right: 0px;
}

.p_selected {
	height: 30px;
	border-radius: 15px;
	padding: 0 5px;
	background-color: rgba(255, 153, 0, 1);
	display: inline-block;
	color: white;
	margin: 0;
}

.p_common {
	height: 30px;
	border-radius: 15px;
	/* padding: 0 15px; */
	background-color: white;
	display: inline-block;
	line-height: 30px;
	margin: 0;
	width: 100%;
	text-align: center;
}

.span_1 {
	font-size: 14px;
	padding-top: 5px;
	vertical-align: middle;
}

.span_2 {
	font-size: 14px;
	padding-top: 5px;
	vertical-align: middle;
}

/**内容相关*/
.div_two {
	height: 100%;
	width: 48%;
	display: inline-block;
	cursor: pointer;
}

.div_two span {
	display: inline-block;
	font-size: 14px;
	padding: 6px 20px;
	line-height: 14px;
	margin: 10px 0;
}

.div_two_span_1 {
	border: solid 1px rgba(204, 204, 204, 1);
}

#col-sm-10-extra {
	height: 108px;
	float: right;
	width: 66%;
}

.col-sm-10-sub-one {
	height: 100%;
	width: 100%;
	display: inline-block;
	padding-top: 10px;
}

.col-sm-10-sub-two {
	height: 50%;
	width: 100%;
	display: inline-block;
}
</style>




</head>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增商户上线推广':'活动详情修改' }</h3>
		</div>
		<div class="modal-body" style="max-height: 700px; overflow-y: auto;"  id = "editBody">
			<form class="form-horizontal" method="post" id="productForm"
				name="productForm"
				action="spread/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<c:if test="${empty entity==false}">
					<div class="form-group"  id = "product_company_id">
						<label class="col-sm-4 control-label">商户id</label>
						<div class="col-sm-8">
							<input type="text" id="product_id" readonly="readonly"
								class="form-control" name="productId"
								value="${entity.productId}">
						</div>

					</div>
				</c:if>
				<!-- 蒙层开始 -->
				<div   style=""  id = "hideDev">
					<div class="form-group">
						<label class="col-sm-4 control-label">公司名称</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="companyName"
								value="${companyName}" readonly="readonly">
							<c:if test="${empty entity==false}">
								<input type="hidden" value="${entity.createTime }"
									id="createTime" name="createTime" />
								<input type="hidden" value="${entity.createMan }" id="createMan"
									name="createMan" />
								<input type="hidden" value="${entity.modifyTime }"
									id="modifyTime" name="modifyTime" />
								<input type="hidden" value="${entity.modifyMan }" id="modifyMan"
									name="modifyMan" />
								<input type="hidden" value="${orderTimeStr }"
									id="oldOrderTimeStr" name="oldOrderTimeStr" />
								<input type="hidden" value="${entity.state }" id="oldState"
									name="oldState" />
								<input type="hidden" value="" id="submitType" name="submitType" />
							</c:if>


						</div>


					</div>


					<div class="form-group" id="productNameShow">
						<label class="col-sm-4 control-label">商户名称<font
							color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text" class="form-control"
								value="${ProductIdNameInfo.productName}" id="nameShow">
							<input type="hidden" class="form-control" id="productNameHide"
								name="productName" value="${ProductIdNameInfo.productId}">
						</div>
					</div>


					<div class="form-group" id="productNameSelect">
						<label class="col-sm-4 control-label">商户名称<font
							color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="hidden" id="productName" class="form-control"
								name="productName" value="${ProductIdNameInfo.productId}">
							<select class="form-control"
								style="width: 100%; float: right; display: inline-block;"
								onclick="setInputValue()" id="productSelect"></select> <input
								class="form-control"
								style="width: 95%; float: left; display: inline-block; position: relative; bottom: 34px;"
								id="input" name="input" onkeyup="getProductValue(this.value)"
								value="--全部--"> <input class="form-control"
								id="inputProductId" name="inputProductId" type="hidden" value="">
						</div>
					</div>



					<%-- <div class="form-group">
					<label class="col-sm-4 control-label">副标题</label>
					<div class="col-sm-8">
						<c:if test="${empty entity==false}">
							<input type="hidden" value="${entity.createTime }"
								id="createTime" name="createTime" />
							<input type="hidden" value="${entity.createMan }" id="createMan"
								name="createMan" />
							<input type="hidden" value="${entity.modifyTime }"
								id="modifyTime" name="modifyTime" />
							<input type="hidden" value="${entity.modifyMan }" id="modifyMan"
								name="modifyMan" />
							<input type="hidden" value="${orderTimeStr }"
								id="oldOrderTimeStr" name="oldOrderTimeStr" />
							<input type="hidden" value="${entity.state }" id="oldState"
								name="oldState" />
						</c:if>
					
					
						<input type="text" id="viceName" class="form-control"
							name="viceName" value="${entity.viceName}">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">分享标题</label>
					<div class="col-sm-8">
						<input type="text" id="shareTitle" class="form-control"
							name="shareTitle" value="${entity.shareTitle}">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">分享内容</label>
					<div class="col-sm-8">
						<input type="text" id="shareContent" class="form-control"
							name="shareContent" value="${entity.shareContent}">
					</div>
				</div> --%>

					<div class="form-group" id="productCategoryDev">
						<label class="col-sm-4 control-label">商户类型<font
							color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<select class="form-control" id="product_category_id"
								name="productCategoryId">
								<c:forEach var="info" items="${productCategorys }">
									<option value="${info.productCategoryId }">${info.categoryName }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<!-- <script type="text/javascript">
				    $("#productCategoryDev").hide();
				</script> -->





					<%-- <div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">上榜理由</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<textarea id="productJoice" name="productJoice" cols="45"
								rows="5" style="overflow: auto">${entity.productJoice}</textarea>
						</div>
					</div>
				</div> --%>





					<div class="form-group" id="supplierDiv">
						<label class="col-sm-4 control-label">供应商<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<select class="form-control" id="supplierId" name="supplierId">
								<c:forEach var="info" items="${suppliers }">
									<option value="${info.supplierId }">${info.supplierName }</option>
								</c:forEach>


							</select>
						</div>
					</div>

					<!-- <script type="text/javascript">
				    $("#supplierDiv").hide();
				</script> -->








					<%-- <div class="form-group">
					<label class="col-sm-4 control-label">申请完成链接地址</label>
					<div class="col-sm-8">
						<input type="text" id="linkUrlH" class="form-control"
							name="linkUrlH" value="${entity.linkUrlH}">
					</div>
				</div> --%>

					<div class="form-group">
						<label class="col-sm-4 control-label">商户图片上传<font
							color="red">&nbsp;*</font></label>
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
						<label for="inputEmail3" class="col-sm-4 control-label">商户图片</label>
						<div class="col-sm-8">
							<div class="col-sm-6">
								<img src="${entity.productImg }" class="img-rounded"
									style="max-width: 100px; max-height: 80px;" id="productImgsrc" />
								<input type="hidden" class="form-control" name="productImg"
									id="productImg" value="${entity.productImg }">
							</div>
						</div>
					</div>



					<%-- <div class="form-group"  id =  "remarkDev">
					<label class="col-sm-4 control-label">新手指导</label>
					<div class="col-sm-8">
						<textarea id="remark" name="remark" cols="45" rows="5"
							style="overflow: auto">${entity.remark}</textarea>
					</div>
				</div> --%>

					<%-- 		<div class="form-group">
					<label class="col-sm-4 control-label">商户小标签文字</label>
					<div class="col-sm-8">
						<input type="text" id="smallLabelFont" class="form-control"
							name="smallLabelFont" value="${entity.smallLabelFont}">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">商户小标签图片上传</label>
					<div class="col-sm-8">
						<div class="input-group">
							<input type="file" class="form-control" id="smallLabelImgFile"
								name="smallLabelImgFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button"
									id="uploadsmallLabelImg">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">商户小标签图片</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<img src="${entity.smallLabelImg }" class="img-rounded"
								style="max-width: 100px; max-height: 80px;"
								id="smallLabelImgsrc" /> <input type="hidden"
								class="form-control" name="smallLabelImg" id="smallLabelImg"
								value="${entity.smallLabelImg }">
							<button class="btn btn-success" type="button" id="delImg"
								onclick="delsmallLaelImg()">清空图片</button>
						</div>
					</div>
				</div> --%>

					<div class="form-group">
						<label class="col-sm-4 control-label">商户标签</label>
						<div class="col-sm-8">
							<c:forEach var="parentLabel" items="${parentLabels}">
								<label><b>${parentLabel.labelName }</b></label>
								<br>
								<c:forEach var="plabel" items="${productLabels}">
									<c:if test="${plabel.parentId == parentLabel.productLabelId}">
										<label><input type="checkbox" name="productLabel"
											checked="checked" value="${plabel.productLabelId }" />${plabel.labelName }</label>
									</c:if>
								</c:forEach>
								<hr>
							</c:forEach>
						</div>
					</div>

					<%-- <div class="form-group" id = "auditDescDev">
					<label for="inputEmail3" class="col-sm-4 control-label">借款审核细节</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<textarea id="auditDesc" name="auditDesc" cols="45" rows="5"
								style="overflow: auto">${entity.auditDesc}</textarea>
						</div>
					</div>
				</div>

				<div class="form-group" id = "applyConDev">
					<label for="inputEmail3" class="col-sm-4 control-label">申请条件(多个条件用','号分割)</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<textarea id="applyCon" name="applyCon" cols="45" rows="5"
								style="overflow: auto">${entity.applyCon}</textarea>
						</div>
					</div>
				</div> --%>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-4 control-label">申请流程(格式：图标|内容，多流程用','号分割)
							<font color="red">&nbsp;*</font>
						</label>
						<div class="col-sm-8">
							<div class="col-sm-6">
								<textarea id="applyProcess" name="applyProcess" cols="45"
									rows="10" style="overflow: auto">
								<c:if test="${empty entity==false}">
								      ${entity.applyProcess}
								</c:if>
								<c:if test="${empty entity==true}">
								      http://wdgj.oss-cn-shanghai.aliyuncs.com/loan/product/applyprocessImg/68347dea049e4a4ca0fc08d67e01c8bb.jpg|身份认证,http://wdgj.oss-cn-shanghai.aliyuncs.com/loan/product/applyprocessImg/476fba7fb32d4944b5fce0198674b639.jpg|资料上传,http://wdgj.oss-cn-shanghai.aliyuncs.com/loan/product/applyprocessImg/a54151341daf4a77a5ef0dcd7e720117.jpg|运营商认证,http://wdgj.oss-cn-shanghai.aliyuncs.com/loan/product/applyprocessImg/0e6f9b1650ee494b84e391a80e2b257f.jpg|审核放款
								</c:if>
						    </textarea>
							</div>
						</div>
					</div>
					<%-- 
				<div class="form-group">
					<label class="col-sm-4 control-label">申请流程图标上传</label>
					<div class="col-sm-8">
						<div class="input-group">
							<input type="file" class="form-control" id="applyProcessFile"
								name="applyProcessFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button"
									id="applyProcessImg">上传图标</button>
							</span>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-4 control-label">申请数量</label>
					<div class="col-sm-8">
						<input type="number" id="applyNum" class="form-control"
							name="applyNum" value="${entity.applyNum}">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">下款率 <font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="number" id="theRateOf" class="form-control"
							name="theRateOf" value="${entity.theRateOf}">
					</div>
				</div> --%>


					<div class="form-group">
						<label class="col-sm-4 control-label">最小额度 <font
							color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text" id="loanRangeMin" class="form-control"
								name="loanRangeMin" value="${entity.loanRangeMin}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-4 control-label">最大额度 <font
							color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text" id="loanRangeMax" class="form-control"
								name="loanRangeMax" value="${entity.loanRangeMax}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-4 control-label">最小期限<font
							color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text" id="timeLimitMint" class="form-control"
								name="timeLimitMint" value="${entity.timeLimitMint}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-4 control-label">最大期限<font
							color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text" id="timeLimitMax" class="form-control"
								name="timeLimitMax" value="${entity.timeLimitMax}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-4 control-label">期限类型</label>
						<div class="col-sm-8">
							<select class="form-control" id="timeLimitType"
								name="timeLimitType">
								<option value="1">天</option>
								<option value="2">月</option>
							</select>
						</div>
					</div>


					<div class="form-group">
						<label class="col-sm-4 control-label">放款时间（小时）<font
							color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text" id="loanTime" class="form-control"
								name="loanTime" value="${entity.loanTime}">
						</div>
					</div>

					<%-- <div class="form-group">
					<label class="col-sm-4 control-label">成功率(如0.1=10%)</label>
					<div class="col-sm-8">
						<input type="text" id="successRate" class="form-control"
							name="successRate" value="${entity.successRate}">
					</div>
				</div> --%>

					<div class="form-group">
						<label class="col-sm-4 control-label">利率(如0.1=10%)</label>
						<div class="col-sm-8">
							<input type="text" id="dayRate" class="form-control"
								name="dayRate" value="${entity.strdayRate}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-4 control-label">利率类型</label>
						<div class="col-sm-8">
							<select class="form-control" id="rateType" name="rateType">
								<option value="1">日利率</option>
								<option value="2">月利率</option>
								<option value="3">年利率</option>
							</select>
						</div>
					</div>

					<%-- <div class="form-group">
					<label class="col-sm-4 control-label">服务费率(如0.1=10%)</label>
					<div class="col-sm-8">
						<input type="text" id="serviceRate" class="form-control"
							name="serviceRate" value="${entity.serviceRate}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">推荐个数</label>
					<div class="col-sm-8">
						<input type="text" id="recommendNum" class="form-control"
							name="recommendNum" value="${entity.recommendNum}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">不推荐个数</label>
					<div class="col-sm-8">
						<input type="text" id="noRecommendNum" class="form-control"
							name="noRecommendNum" value="${entity.noRecommendNum}">
					</div>
				</div>  --%>

					<div class="form-group">
						<label class="col-sm-4 control-label">活动类型</label>
						<div class="col-sm-5">
							<select class="form-control" id="spreadType" name="spreadType">
								<c:forEach var="item" items="${spreadTypeList }">
									<option value="${item.id }"
										<c:if test="${item.id ==  prodExt.spreadType}"> selected="selected" </c:if>>${item.modelContent }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<!-- 客服电话-->
					<div class="form-group">
						<label class="col-sm-4 control-label">客服电话</label>
						<div class="col-sm-8">
							<input type="text" id="servicePhone" class="form-control"
								name="servicePhone" value="${prodExt.servicePhone }"
								placeholder="请输入客服电话" maxlength="13" />
						</div>
					</div>
					<!-- 官网地址 -->
					<%-- <div class="form-group">
					<label class="col-sm-4 control-label">官网地址</label>
					<div class="col-sm-8">
						<input type="text" id="officialWebset" class="form-control"
							name="officialWebset" value="${prodExt.officialWebset }"
							placeholder="请输入官网地址">
					</div>
				</div> --%>

					<!-- 展示原始数据区域 -->
					<%-- 	<c:if test="${empty entity == false }">
					<div class="form-group" style="border: solid 2px red;">
						<div class="form-group"  style = "margin-top: 15px;">
							<label class="col-sm-7 control-label" style="font-size: 26px;color: red;font-weight: bold;">历史数据</label>
							<div class="col-sm-5"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">上线时间</label>
							<div class="col-sm-8">
								<input type="text"  class="form-control"  value="${prodExt.shangxianTimeStr}" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">下线时间</label>
							<div class="col-sm-8">
								<input type="text"  class="form-control" value="${prodExt.xiaxianTimeStr}" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">预约上线时间</label>
							<div class="col-sm-8">
							<c:if test="${prodExt.productStatus == 3  ||  prodExt.productStatus == 30 }">
								<input type="text"  class="form-control"  value="${orderTimeStr}"  readonly="readonly">
						    </c:if>
							<c:if test="${prodExt.productStatus != 3  &&  prodExt.productStatus != 30 }">
								<input type="text"  class=" form-control"  value=""  readonly="readonly">
						    </c:if>
								
							</div>
						</div>
						
						<div class="form-group" >
							<label class="col-sm-4 control-label">下线理由</label>
							<div class="col-sm-8">
								<textarea  cols="45" rows="5" style="overflow: auto" readonly="readonly">${prodExt.offlineReason}</textarea>
							</div>
						</div> 
					</div>
				</c:if> --%>





					<div class="form-group">
						<label class="col-sm-4 control-label">商户状态</label>
						<div class="col-sm-8">
							<input type="text" id="state" class="form-control"
								readonly="readonly"
								<c:if test="${prodExt.productStatus == 1}">
									value="已上线"
							</c:if>
								<c:if test="${prodExt.productStatus == 2}">
									value="已下线"
							</c:if>
								<c:if test="${prodExt.productStatus == 30}">
									value="待预约上线"
							</c:if>
								<c:if test="${prodExt.productStatus == 3}">
									value="已预约上线"
							</c:if>
								<c:if test="${prodExt.productStatus == 4}">
									value="待上线"
							</c:if>
								<c:if test="${prodExt.productStatus == 5}">
									value="待下线"
							</c:if>>
						</div>
					</div>



					<div class="form-group">
						<label class="col-sm-4 control-label">是否有后台</label>
						<div class="col-sm-8">
							<select class="form-control" id="backFlag" name="backFlag">
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</div>
					</div>



					<div class="form-group" id="backUrlDev">
						<label class="col-sm-4 control-label">对方后台地址：</label>
						<div class="col-sm-8">
							<input type="text" id="backUrl" class="form-control"
								name="backUrl" value="${prodExt.backUrl}">
						</div>
					</div>

					<div class="form-group" id="backAccountDev">
						<label class="col-sm-4 control-label">对方后台账号：</label>
						<div class="col-sm-8">
							<input type="text" id="backAccount" class="form-control"
								name="backAccount" value="${prodExt.backAccount}">
						</div>
					</div>
					<div class="form-group" id="backPwdDev">
						<label class="col-sm-4 control-label">对方后台密码：</label>
						<div class="col-sm-8">
							<input type="text" id="backPwd" class="form-control"
								name="backPwd" value="${prodExt.backPwd}">
						</div>
					</div>

				</div>
				 
				<!-- 蒙层结束 -->
				 
				<div class="form-group">
					<label class="col-sm-4 control-label">商户链接地址<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="productUrl" class="form-control"
							name="productUrl" value="${entity.productUrl}"  maxlength="500">
					</div>
				</div>
				<div class="form-group"  id = "backupProductUrlDev">
					<label class="col-sm-4 control-label">备用链接(市场推广用,多个以#分隔)</label>
					<div class="col-sm-8">
						<input type="text" id="backupProductUrl" class="form-control"
							name="backupProductUrl" value="${entity.backupProductUrl}">
					</div>
				</div>
				
				
				
					<!-- 只修改不审核区域开始 -->		
				<div class="form-group" style="border: dashed 4px red;"  id = "borderDev">
					<div class="form-group"  style = "margin-top: 15px;" id ="borderDevSub">
						<label class="col-sm-11 control-label" style="font-size: 26px;color: red;font-weight: bold;">只修改不审核数据区域，无视其它</label>
						<div class="col-sm-1"></div>
					</div>
					
					
					
				<div class="form-group" id = "zhuanquDev">
					<label class="col-sm-4 control-label">所属专区<!-- <font color="red">&nbsp;*</font> --></label>
					<div class="col-sm-8"  id= "zhuanqu">
						<c:forEach var="info" items="${zones}">
							<label><input type="checkbox" name="zoneId"
								value="${info.zoneId}" />${info.zoneName }</label>
						</c:forEach>

					</div>
				</div>
				
				
			
				
					
					
					<%-- <div class="form-group"  id = "newSortDev">
						<label class="col-sm-4 control-label">最新口子权重值</label>
						<div class="col-sm-8">
							<input type="number" id="newSort" class="form-control" name="sort"
								value="${prodExt.newSort}">
						</div>
					</div> --%>
				
				<div class="form-group" id = "recommendReasonDev">
					<label  class="col-sm-4 control-label">推荐理由<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<textarea id="recommendReason" name="recommendReason" cols="45"
								rows="5" style="overflow: auto">${entity.recommendReason}</textarea>
						</div>
					</div>
				</div>
				<div class="form-group" id = "colorDev">
					<label class="col-sm-4 control-label">常用十二种颜色</label>
					<div class="col-sm-8">
						<div class="col-sm-12">
							    <font color="#8cc540">1. 浅绿 #8cc540</font>
							    <font color="#009f5d">2. 深绿 #009f5d</font>
							    <font color="#019fa0">3. 暗蓝 #019fa0</font>
							    <font color="#019fde">4. 蓝色 #019fde</font>
							    <font color="#007cdc">5. 深蓝 #007cdc</font>
							    <font color="#887ddd">6. 深紫 #887ddd</font>
							    <font color="#cd7bdd">7. 浅紫 #cd7bdd</font>
							    <font color="#ff5675">8. 粉色 #ff5675</font>
							    <font color="#ff1244">9. 红色 #ff1244</font>
							    <font color="#ff8345">10. 橙色 #ff8345</font>
							    <font color="#f8bd0b">11. 黄色 #f8bd0b</font>
							    <font color="#d1d2d4">12. 灰色 #d1d2d4</font>
						</div>
					</div>
				</div>
				
				<div class="form-group" id = "recommendReasonColorDev">
					<label for="recommendReasonColor" class="col-sm-4 control-label">推荐理由（带颜色）</label>
					<div class="col-sm-8">
						<div class="col-sm-12">
							<textarea id="recommendReasonColor" name="recommendReasonColor" cols="45"
								rows="5" style="overflow: auto">${entity.recommendReasonColor}</textarea>
							<br>
							<font color = "red">
							参考格式<xmp><font color="#ff1244">无视</font><font color="#019fde">  秒下款</font></xmp>
							</font>
							效果展示：<font color="#ff1244">无视</font><font color="#019fde">  秒下款</font>
						</div>
					</div>
				</div>
				
				<div class="form-group" id = "showFlagDev">
					<label class="col-sm-4 control-label">是否在贷款大全显示</label>
					<div class="col-sm-8">
						<select class="form-control" id="showFlag" name="showFlag">
							<option value="2">是</option>
							<option value="1">否</option>
						</select>
					</div>
				</div>
				<div class="form-group" id = "backupSortDev">
					<label class="col-sm-4 control-label">备用权重值(市场推广用,多个以#分隔)</label>
					<div class="col-sm-8">
						<input type="text" id="backupSort" class="form-control"
							name="backupSort" value="${entity.backupSort}">
					</div>
				</div>
		
				<!-- 特殊权限控制区： ----------------------------------------------------->


				<div class="form-group">
					<label class="col-sm-2 control-label" style="width: 33.33%;">操作<font
						color="red">&nbsp;*</font></label>
					<div class="col-sm-10" style="width: 66.66%;">
						<c:if test="${operateType ==1}">
							<label class="checkbox-inline" onclick="showTimeInfo('1')"  style = "padding-left:0px;">
								<input type="radio" name="operateType" value="1"/> 上线
							</label> 
							<label class="checkbox-inline" onclick="showTimeInfo('3')"   style = "padding-left:0px;">
								<input type="radio" name="operateType" value="3"/> 预约上线
							</label> 
						</c:if>
						<c:if test="${operateType ==2}">
							<label class="checkbox-inline" onclick="showTimeInfo('2')"   style = "padding-left:0px;">
								<input type="radio" name="operateType" value="2"/> 下线
							</label>  
						</c:if>
						<c:if test="${empty entity == false}">
								<label class="checkbox-inline" onclick="showTimeInfo('4')"   style = "padding-left:0px;" id = "onlyUpdate">
										<input type="radio" name="operateType" value="4"/> 只修改不审核
								</label>
							<%-- <shiro:hasPermission name = "spread:upload">
		    				</shiro:hasPermission> --%>
	    				</c:if>
						<input type="hidden" value="999" id="myState" />
					</div>
				</div>


				<div class="form-group" style="height: 170px;" id="onlineInfo">
					<div class="col-sm-10"
						style="padding-right: 0; padding-left: 0; float: right; width: 66%;">

						<label class="col-sm-4 control-label lable_1" style="width: 25%;"
							id="label_1" onclick="changebackground('1')">
							<p class="p_common"
								<c:if test= "${prodInfo.afterEightFlag == 0}">  
						     		style = "background:rgba(255, 153, 0, 1);color:rgba(255, 255, 255, 255);" 
						     </c:if>>
								<span class="span_1">今日${prodInfo.firstStr}</span>
							</p>
						</label> <label class="col-sm-4 control-label lable_1" id="label_2"
							onclick="changebackground('2')">
							<p class="p_common"
								<c:if test= "${prodInfo.afterEightFlag == 1}">  
						     		style = "background:rgba(255, 153, 0, 1);color:rgba(255, 255, 255, 255);" 
						     </c:if>>
								<span class="span_1"> ${prodInfo.secondStr} </span>
							</p>
						</label> <label class="col-sm-4 control-label lable_1" id="label_3"
							onclick="changebackground('3')">
							<p class="p_common">
								<span class="span_2"> ${prodInfo.thirdStr} </span>
							</p>
						</label> <label class="col-sm-4 control-label lable_1" id="label_4"
							onclick="changebackground('4')">
							<p class="p_common">
								<span class="span_2"> ${prodInfo.fourthStr} </span>
							</p>
						</label> <label class="col-sm-4 control-label lable_1" id="label_5"
							onclick="changebackground('5')">
							<p class="p_common">
								<span class="span_2"> ${prodInfo.fifthStr} </span>
							</p>
						</label>
						<!-- type="hidden" -->
						<!-- 隐藏数据，以便于区分选中的哪一天 -->
						<input type="hidden" value="${prodInfo.myDateIndex} "
							id="myDateIndex" name="myDateIndex" /> <input type="hidden"
							value="" id="hourStr" name="hourStr" />
					</div>
					<div class="col-sm-10" id="col-sm-10-extra">
						<!-- ------------------------------第一天数据------------------------------------- -->
						<div class="col-sm-10-sub-one" id="col_sm_10_sub_1"
							<c:if test= "${prodInfo.afterEightFlag == 1}">
					            style= "display: none;"
					        </c:if>>
							<div class="col-sm-10-sub-two">
								<div class="div_two"
									onclick="checkFlag('${prodInfo.first.flag1}','10:00')">
									<span class="div_two_span_1"
										<c:if test="${prodInfo.first.flag1 == 1}">
							                    	 style = "background-color: rgba(204, 204, 204, 1);"
							                 </c:if>>10:00</span>
									<span> ${prodInfo.first.count1}家</span>
								</div>
								<div class="div_two"
									onclick="checkFlag('${prodInfo.first.flag2}','14:00')">
									<span class="div_two_span_1"
										<c:if test="${prodInfo.first.flag2 == 1}">
							                    	 style = "background-color: rgba(204, 204, 204, 1);"
							                 </c:if>>14:00</span>
									<span> ${prodInfo.first.count2}家</span>
								</div>
							</div>
							<div class="col-sm-10-sub-two">
								<div class="div_two"
									onclick="checkFlag('${prodInfo.first.flag3}','16:00')">
									<span class="div_two_span_1"
										<c:if test="${prodInfo.first.flag3 == 1}">
							                    	 style = "background-color: rgba(204, 204, 204, 1);"
							                 </c:if>>16:00</span>
									<span> ${prodInfo.first.count3}家</span>
								</div>
								<div class="div_two"
									onclick="checkFlag('${prodInfo.first.flag4}','20:00')">
									<span class="div_two_span_1"
										<c:if test="${prodInfo.first.flag4 == 1}">
							                    	 style = "background-color: rgba(204, 204, 204, 1);"
							                 </c:if>>20:00</span>
									<span> ${prodInfo.first.count4}家</span>
								</div>
							</div>
						</div>

						<!-- ------------------------------第二天数据------------------------------------- -->
						<div class="col-sm-10-sub-one" id="col_sm_10_sub_2"
							<c:if test= "${prodInfo.afterEightFlag == 0}">
					    	 style= "display: none;"
					     </c:if>>
							<div class="col-sm-10-sub-two">
								<div class="div_two" onclick="checkFlag(0,'10:00')">
									<span class="div_two_span_1">10:00</span> <span>
										${prodInfo.second.count1}家</span>
								</div>
								<div class="div_two" onclick="checkFlag(0,'14:00')">
									<span class="div_two_span_1">14:00</span> <span>
										${prodInfo.second.count2}家</span>
								</div>
							</div>
							<div class="col-sm-10-sub-two">
								<div class="div_two" onclick="checkFlag(0,'16:00')">
									<span class="div_two_span_1">16:00</span> <span>
										${prodInfo.second.count3}家</span>
								</div>
								<div class="div_two" onclick="checkFlag(0,'20:00')">
									<span class="div_two_span_1">20:00</span> <span>
										${prodInfo.second.count4}家</span>
								</div>
							</div>
						</div>



						<div class="col-sm-10-sub-one" id="col_sm_10_sub_3"
							style="display: none;">
							<!-- 第三天数据 -->
							<div class="col-sm-10-sub-two">
								<div class="div_two" onclick="checkFlag(0,'10:00')">
									<span class="div_two_span_1">10:00</span> <span>
										${prodInfo.third.count1}家</span>
								</div>
								<div class="div_two" onclick="checkFlag(0,'14:00')">
									<span class="div_two_span_1">14:00</span> <span>
										${prodInfo.third.count2}家</span>
								</div>
							</div>
							<div class="col-sm-10-sub-two">
								<div class="div_two" onclick="checkFlag(0,'16:00')">
									<span class="div_two_span_1">16:00</span> <span>
										${prodInfo.third.count3}家</span>
								</div>
								<div class="div_two" onclick="checkFlag(0,'20:00')">
									<span class="div_two_span_1">20:00</span> <span>
										${prodInfo.third.count4}家</span>
								</div>
							</div>
						</div>
						<div class="col-sm-10-sub-one" id="col_sm_10_sub_4"
							style="display: none;">
							<!-- 第四天数据 -->
							<div class="col-sm-10-sub-two">
								<div class="div_two" onclick="checkFlag(0,'10:00')">
									<span class="div_two_span_1">10:00</span> <span>
										${prodInfo.fourth.count1}家</span>
								</div>
								<div class="div_two" onclick="checkFlag(0,'14:00')">
									<span class="div_two_span_1">14:00</span> <span>
										${prodInfo.fourth.count2}家</span>
								</div>
							</div>
							<div class="col-sm-10-sub-two">
								<div class="div_two" onclick="checkFlag(0,'16:00')">
									<span class="div_two_span_1">16:00</span> <span>
										${prodInfo.fourth.count3}家</span>
								</div>
								<div class="div_two" onclick="checkFlag(0,'20:00')">
									<span class="div_two_span_1">20:00</span> <span>
										${prodInfo.fourth.count4}家</span>
								</div>
							</div>
						</div>
						<div class="col-sm-10-sub-one" id="col_sm_10_sub_5"
							style="display: none;">
							<!-- 第五天数据 -->
							<div class="col-sm-10-sub-two">
								<div class="div_two" onclick="checkFlag(0,'10:00')">
									<span class="div_two_span_1">10:00</span> <span>
										${prodInfo.fifth.count1}家</span>
								</div>
								<div class="div_two" onclick="checkFlag(0,'14:00')">
									<span class="div_two_span_1">14:00</span> <span>${prodInfo.fifth.count2}家</span>
								</div>
							</div>
							<div class="col-sm-10-sub-two">
								<div class="div_two" onclick="checkFlag(0,'16:00')">
									<span class="div_two_span_1">16:00</span> <span>
										${prodInfo.fifth.count3}家</span>
								</div>
								<div class="div_two" onclick="checkFlag(0,'20:00')">
									<span class="div_two_span_1">20:00</span> <span>
										${prodInfo.fifth.count4}家</span>
								</div>
							</div>
						</div>
					</div>

					<!-- 错误信息显示 -->
					<div class="col-sm-10"
						style="padding-right: 0; padding-left: 0; float: right; width: 66%;">
						<input value="" id="errorInfo" name="errorInfo"
							style="border: 0; width: 100%; color: red; font-size: 16px;" />
					</div>

				</div>

				<!-- 预约上线时间 -->
				<div class="form-group"   id = "orderTimeInfo">
					<label class="col-sm-4 control-label">预约上线时间</label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" id="orderTime"
							class="form-control" name="orderTimeStr" value="${orderTimeStr }"  >
					</div>
				</div>
				
				
							
				<div class="form-group" id="offlineReasonInfo">
					<label class="col-sm-4 control-label">下线理由<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<textarea id="offlineReason" name="offlineReason" cols="45" rows="5"
							style="overflow: auto">${prodExt.offlineReason}</textarea>
					</div>
				</div> 
				
				
				
				
				<div class="form-group" id="shangxian" >
					<label class="col-sm-4 control-label">请选择上线时间</label>
					<div class="col-sm-8">
						<input type="text" id="shangxianTime" class="form-control"
							name="shangxianTime" value="${prodExt.shangxianTimeStr }"
							placeholder="请选择上线时间"     onchange="checkTime(1)"  autocomplete="off"/>
					</div>
				</div> 
				
				<div class="form-group" id="xiaxian"  >
					<label class="col-sm-4 control-label" >请选择下线时间</label>
					<div class="col-sm-8" >
						<input type="text" id="xiaxianTime" class="form-control"
							name="xiaxianTime" value="${prodExt.xiaxianTimeStr }"
							placeholder="请选择下线时间" onchange="checkTime(2)"   autocomplete="off">

					</div>
				</div>
				
				
					<div class="form-group"  id = "sort_dev">
						<label class="col-sm-4 control-label">权重值<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="number" id="sort" class="form-control" name="sort"
								value="${entity.sort}">
						</div>
					</div>
				
					<div class="form-group" id = "is_recommend_dev">
						<label class="col-sm-4 control-label">是否推荐<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<select class="form-control" id="is_recommend" name="isRecommend">
								<option value="1">推荐</option>
								<option value="2">不推荐</option>
							</select>
						</div>
					</div>
				<!-- 只修改不审核区域结束 -->
				</div>
				<div class="form-group" id = "refuseReasonDev">
					<label class="col-sm-4 control-label">审核不通过理由<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="refuseReason" class="form-control" name="refuseReason" value="">
					</div>
				</div>
				
				
			</form>
		</div>
		<div class="modal-footer">
		<span id="tip" style="color: red; font-size: 16px; margin-left: 20px;float:left;"></span>
		<!-- 
		    //只有待审核状态才能被审核
			// 添加权限：审批
			-->
			
			<shiro:hasPermission name="spread:add">
				<c:if test="${prodExt != null}">
					<c:if test="${prodExt.auditStatus == 1}">
						<button class="btn btn-primary" data-dismiss="modal" onclick="submitType(1)">审核不通过</button>
						<button class="btn btn-primary" data-dismiss="modal" onclick="submitType(2)">审核通过</button>
					</c:if>
				</c:if>
			</shiro:hasPermission>
			<button class="btn btn-default" data-dismiss="modal" aria-hidden="true" >取消</button>
			<button class="btn btn-primary" data-dismiss="modal" onclick="submitType(3)">保存</button>
		</div>
	</div>
</div>

<script type="text/javascript">
	Date.prototype.Format = function (fmt) { //author: meizz   
	    var o = {  
	        "M+": this.getMonth() + 1, //月份   
	        "d+": this.getDate(), //日   
	        "H+": this.getHours(), //小时   
	        "m+": this.getMinutes(), //分   
	        "s+": this.getSeconds(), //秒   
	        "q+": Math.floor((this.getMonth() + 3) / 3),
	        "S": this.getMilliseconds()
	    };  
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
	    for (var k in o)  
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
	    return fmt;  
	}  
	
	
	
	//商务权限需要屏蔽设置
	<shiro:hasPermission name = "spread:upload">
		//所属专区
		$("#zhuanquDev").hide();
		//权重
		$("#sort_dev").hide();
		//是否推荐
		$("#is_recommend_dev").hide();
		//备用权重
		$("#backupSortDev").hide();
		//推荐理由
		$("#recommendReasonDev").hide();
		//颜色展示
		$("#colorDev").hide();
		//推荐理由带颜色
		$("#recommendReasonColorDev").hide();
		//是否在贷款大全显示
		$("#showFlagDev").hide();
		//只修改不审核
		$("#onlyUpdate").hide();
		$("#borderDev").css({border:"none"});
		$("#borderDevSub").hide();
		$("#offlineReasonInfo").show();
		//备用链接
		// $("#backupProductUrlDev").hide();
		
	</shiro:hasPermission>
	
		//审核不通过理由
		$("#refuseReasonDev").hide();
	
	
	

	if ('${entity}' != '') {
		//只有用户拥有编辑部分权限才能显示
		//删除权限：编辑部分
		var  backFlag = '${prodExt.backFlag}' ;
		if(backFlag == null || backFlag == ''){
			$("#backFlag").val(0);
		}else{
			if(backFlag==0){
				//清除有后台数据
				//隐藏后台相关输入框
		    	$("#backUrlDev").val("");
				$("#backAccountDev").val("");
				$("#backPwdDev").val("");
				
		    	$("#backUrlDev").hide();
				$("#backAccountDev").hide();
				$("#backPwdDev").hide();
			}else{
				//隐藏后台相关输入框
		    	$("#backUrlDev").show();
				$("#backAccountDev").show();
				$("#backPwdDev").show();
			}
			$("#backFlag").val(backFlag);
		}
		$("#productName").attr("disabled",true); 
		$("#productNameSelect").hide();
		$("#productNameShow").show();
		$("#productNameHide").attr("disabled",false); 
		
		
		$("#productName").attr("readonly","readonly"); 
		$("#product_category_id").val('${entity.productCategoryId}');
		$("#zoneId").val('${entity.zoneId}');
		$("#supplierId").val('${entity.supplierId}');
		$("#is_recommend").val('${entity.isRecommend}');
		$("#showFlag").val('${entity.showFlag}');
		
	
		
		$("#timeLimitType").val('${entity.timeLimitType}');
		$("#rateType").val('${entity.rateType}');
		if ('${entity.productLabel}' != '') {
			var jsondata = '${entity.productLabel}';
			var json = eval(jsondata);
			$.each(json, function(i, n) {
				$("input:checkbox[value=" + json[i].productLabelId + "]").attr(
						'checked', 'true');
			});
		}
		if ('${entity.zoneId}' != '') {
			var zones = '${entity.zoneId}';
			var zones2 = zones.split(",");
			$.each(zones2, function(i, n) {
				$("input:checkbox[value=" + zones2[i] + "]").attr('checked',
						'true');
			});
		}

		$("#onlineInfo").css({display : "none"});
		$("#offlineReasonInfo").hide();
		$("#shangxian").hide();
		$("#xiaxian").hide();
		$("#orderTimeInfo").hide();
		
		
		//根据商户状态选中操作类型
		var  prodStatus = '${prodExt.productStatus}';
		//待上线
		if(prodStatus == 4){
			$("input:radio[name='operateType']").eq(0).attr("checked",'checked');
			showTimeInfo(1);
		}
		//待预约上线
		if(prodStatus == 30){
			$("input:radio[name='operateType']").eq(1).attr("checked",'checked');
			showTimeInfo(3);
		}
		//APP在线
		if(prodStatus == 1 || prodStatus == 3 || prodStatus == 5 ){
			$("#offlineReasonInfo").show();
			$("input:radio[name='operateType']").eq(0).attr("checked",'checked');
			showTimeInfo(2);
		}
		
		//删除：编辑部分权限
		<shiro:hasPermission name = "spread:delete">
			$("#hideDev").css("background-color","#DCDCDC");
			$("#hideDev").css("filter","Alpha(Opacity=60)");
			$("#hideDev").css("pointer-events","none");
			//备用链接+商户链接不可改   
			$("#productUrl").attr("readonly","readonly"); 
			$("#backupProductUrl").attr("readonly","readonly"); 
			
			/* $("#product_category_id").attr("disabled",true); 
			$("#supplierId").attr("disabled",true); 
			$("input:checkbox[name='productLabel']").attr("disabled",true);
			$("#timeLimitType").attr("disabled",true); 
			$("#rateType").attr("disabled",true); 
			$("#spreadType").attr("disabled",true); 
			$("#productImgFile").attr("disabled",true); 
			$("#smallLabelImgFile").attr("disabled",true); 
			$("#delImg").attr("disabled",true); 
			$("#uploadsmallLabelImg").attr("disabled",true); 
			$("#uploadproductImg").attr("disabled",true); 
			$("#applyProcessFile").attr("disabled",true);  */
		</shiro:hasPermission>
		
		
		
		
		
	} else {
		//隐藏后台相关输入框
    	$("#backUrlDev").hide();
		$("#backAccountDev").hide();
		$("#backPwdDev").hide();
		$("#productNameShow").hide();
		$("#productNameSelect").show();
		$("#productName").attr("disabled",false);
		$("#productNameHide").attr("disabled",true);
		$("#onlineInfo").css({display : "none"});
		$("#offlineReasonInfo").hide();
		$("#shangxian").hide();
		$("#xiaxian").hide();
		$("#orderTimeInfo").hide();
	}
	
	
	  //字符串转日期格式，strDate要转为日期格式的字符串
    function submitType(obj) {
    	$("#submitType").val(obj);
		//判断选择的是什么类型  
		var state = $("#myState").val();
		
		if(state == null || state.trim() == ''){
			$("#tip").html("请选择操作类型");
			return false;
		} 
		  
		  
		if(obj == 1){
			if(state == 4){
				$("#refuseReasonDev").hide();
			}else{
				$("#refuseReasonDev").show();
			}
		}else{
			$("#refuseReasonDev").hide();
		}
        
    }
	
	//上线时间+下线时间判断逻辑
	function checkTime(param){
		var currDate =  new Date();
		var currTimeStr = currDate.Format("yyyy-MM-dd HH:mm:ss");
		$("#tip").html("");
		//上线时间
		var currTime = getDate(currTimeStr);
		if(param == 1){
			var  shangxianTimeStr = $("#shangxianTime").val();
			if(shangxianTimeStr == null || shangxianTimeStr.trim() == ''){
				return true;
			}else{
				var sxDate = getDate(shangxianTimeStr);
				if(sxDate < currTime){
					$("#tip").html("上线时间不能早于当前时间，上线时间为空表示立即上线");
					$("#shangxianTime").val("");
				}
			}
		}
		//下线时间
		if(param == 2){
			var  xiaxianTimeStr = $("#xiaxianTime").val();
			if(xiaxianTimeStr == null ||  xiaxianTimeStr.trim() == ''){
				return true ;
			}else{
				//新增，当前时间取上线时间或者系统当前时间
				//if ('${entity}' == '') {
				var  shangxianTimeStr = $("#shangxianTime").val();
				if(shangxianTimeStr == null  ||  shangxianTimeStr == ''){
					/* $("#shangxianTime").val(currTimeStr); */
				}else{
					currTime = getDate(shangxianTimeStr);
				}
				//}
				var xxDate = getDate(xiaxianTimeStr);
				if(xxDate < currTime){
					$("#tip").html("下线时间不能早于上线时间或者当前时间");
					return false;
				}else{
					return true;
				}
			}
		} 
	}
	
	

	  //字符串转日期格式，strDate要转为日期格式的字符串
    function getDate(strDate) {
        var st = strDate;
        var a = st.split(" ");
        var b = a[0].split("-");
        var c = a[1].split(":");
        var date = new Date(b[0], (b[1]-1), b[2], c[0], c[1], c[2]);
        return date;
    }
	
	

	function delsmallLaelImg() {
		$("#smallLabelImgsrc").attr("src", "");
		$("#smallLabelImg").val("");
	}
	


	function showTimeInfo(param) {
		$("#refuseReasonDev").hide();
		$("#errorInfo").val("");
		$("#myState").val("");
		$("#myState").val(param);
		//商务角色直接返回不展示
		<shiro:hasPermission name = "spread:upload">
			return ;
		</shiro:hasPermission>
		if (param == 1 ) {//上线
			$("#onlineInfo").css({display : "none"});
			$("#offlineReasonInfo").hide();
			$("#orderTimeInfo").hide();
			$("#shangxian").show();
			$("#xiaxian").show();
		} else if (param == 2) {//下线
			$("#onlineInfo").css({display : "none"});
			$("#offlineReasonInfo").show();
			$("#orderTimeInfo").hide();
			//将下线理由置空
			$("#shangxian").hide();
			$("#xiaxian").show();
		} else if (param == 3) {//预约上线
			$("#onlineInfo").css({display : "none"});
			$("#onlineInfo").css({display : "inline-block"});
			$("#offlineReasonInfo").hide();
			$("#orderTimeInfo").show();
			//将下线时间置空
			$("#shangxian").css({display : "none"});
			$("#xiaxian").css({display : "none"});
		}else if(param == 4){
			//只修改不审核，根据商户状态展示相应的数据
			//根据商户状态选中操作类型
			var  prodStatus = '${prodExt.productStatus}';
			//待上线
			if(prodStatus == 4){
				showTimeInfo(1);
			}
			//待预约上线
			if(prodStatus == 30){
				showTimeInfo(3);
			}
			//APP在线
			if(prodStatus == 1 || prodStatus == 3 || prodStatus == 5 ){
				showTimeInfo(2);
			}
		}
		
		$("#myState").val("");
		$("#myState").val(param);
	}

	function checkFlag(index, name) {
		if (index == 1) {
			$("#errorInfo").val("已经超出当前时间，不可选择！！");
			$("#hourStr").val("");
			$("#orderTime").val("");
			$("#tip").html("");
			return;
		} else {
			$("#errorInfo").val("");
			$("#hourStr").val(name);

			var now = new Date();
			var num = $("#myDateIndex").val();
			now.setDate(now.getDate() + (num - 1));

			var month = now.getMonth() + 1;
			var day = now.getDate();
			var ymd = now.getFullYear() + '-' + getFormatDate(month) + '-'
					+ getFormatDate(day);
			var hms = " " + name + ":00";
			$("#orderTime").val(ymd + hms);
			$("#tip").html("");
			return;
		}
	}

	function changebackground(index) {
		$("#errorInfo").val("");
		var id = "#label_" + index;
		$(".col-sm-10 .col-sm-4").find(".p_common").css({
			color : "#000",
			background : "#fff"
		});
		$(id).find(".p_common").css({
			color : "#fff",
			background : "rgba(255, 153, 0, 1)"
		});
		for (i = 1; i <= 5; i++) {
			if (i == index) {
				continue;
			}
			var showId = "#col_sm_10_sub_" + i;
			$(showId).css({
				display : "none"
			});
		}
		var showId = "#col_sm_10_sub_" + index;
		$("#myDateIndex").val(index);
		$(showId).css({
			display : "inline-block"
		});
	}

	// 日期月份/天的显示，如果是1位数，则在前面加上'0'
	function getFormatDate(arg) {
		if (arg == undefined || arg == '') {
			return '';
		}

		var re = arg + '';
		if (re.length < 2) {
			re = '0' + re;
		}

		return re;
	}

	function check_fun() {
		//商务角色标识
		var  swFlag = false ;
		<shiro:hasPermission name = "spread:upload">
		     swFlag =  true ;
		</shiro:hasPermission>
		
		
		//删除：编辑部分
 		/* <shiro:hasPermission name = "spread:delete">
			$("#product_category_id").attr("disabled",false); 
			$("#supplierId").attr("disabled",false); 
			$("input:checkbox[name='productLabel']").attr("disabled",false);
			$("#timeLimitType").attr("disabled",false); 
			$("#rateType").attr("disabled",false); 
			$("#spreadType").attr("disabled",false); 
			$("#productImgFile").attr("disabled",false); 
			$("#smallLabelImgFile").attr("disabled",false); 
			$("#delImg").attr("disabled",false); 
			$("#uploadsmallLabelImg").attr("disabled",false); 
			$("#uploadproductImg").attr("disabled",false); 
			$("#applyProcessFile").attr("disabled",false);  
	    </shiro:hasPermission> */ 
	    
	    
	    
		$("#is_recommend").attr("disabled",false); 
	    
	    var  productSelect = "";
	    //获取商户名称
	    if ('${entity}' == '') {
		    productSelect = $("#productSelect").val();
		    $("#productName").val(productSelect);
	    }
	    
		$("#tip").html("");
		var b = true;
		var recommendReason = $("#recommendReason").val();
		var productUrl = $("#productUrl").val();
		var productImgsrc = $("#productImg").val();
		var loanRangeMin = $("#loanRangeMin").val();
		var loanRangeMax = $("#loanRangeMax").val();
		
		var productCategoryId = $("#product_category_id").val();
		var state = $("#myState").val();
		var sort = $("#sort").val();
		
		var supplierId = $("#supplierId").val();
		var isRecommend = $("#is_recommend").val();
		var applyProcess = $("#applyProcess").val();
		var loanTime = $("#loanTime").val();
		var  timeLimitMint = $("#timeLimitMint").val();
		var  timeLimitMax = $("#timeLimitMax").val();
		var productName = $("#productName").val();
		
		if ('${entity}' == '') {
			if(productName == null || productName.trim() == ''){
				$("#tip").html("商户名称不能为空！！");
				b = false;
				return b ;
			}
		}
		
		//商户类型校验
	    if (productCategoryId == null || productCategoryId.trim() == '') {
			$("#tip").html("请选择商户类型");
			b = false;
			return b;
		} 
        //商户链接校验
        if (productUrl == null || productUrl.trim() == '') {
			$("#tip").html("请选择商户链接地址");
			b = false;
			return b;
		}  
        //商户图片校验
        if (productImgsrc == null || productImgsrc.trim() == '') {
			$("#tip").html("请选择商户图片");
			b = false;
			return b;
		}
        //申请流程校验
		if (applyProcess == null || applyProcess.trim() == '') {
			$("#tip").html("请填写申请流程");
			b = false;
			return b;
		}else {
			//判断申请流程个数>1  <5
			var applyProcessCount = applyProcess.split(",");
			if (applyProcessCount.length < 2) {
				$("#tip").html("申请流程不得少于2个");
				b = false;
				return b;
			} else if (applyProcessCount.length > 4) {
				$("#tip").html("申请流程不得大于4个");
				b = false;
				return b;
			}
		}
        //最小额度校验
		if (loanRangeMin == null || loanRangeMin.trim() == '') {
			$("#tip").html("请选择最小额度");
			b = false;
			return b;
		}else if(isNotNum(loanRangeMin)){
	    	$("#tip").html("最小额度只能是整数或者小数");
			b = false;
			return b;
	    }
	   //最大额度校验
		if (loanRangeMax == null || loanRangeMax.trim() == '') {
			$("#tip").html("请选择最大额度");
			b = false;
			return b;
		}else  if(isNotNum(loanRangeMax)){
	    	$("#tip").html("最大额度只能是整数或者小数");
			b = false;
			return b;
	    }
	   
		//提交类型
		var  submitType = $("#submitType").val();
        //操作类型校验		
		if (state == null || state.trim() == '') {
			$("#tip").html("请选择操作类型");
			b = false;
			return b;
		}else if (state == 3) {
			//不是商务角色需要校验预约上线时间是否存在，商务角色无需校验
			if(!swFlag){
				var orderTime = $("#orderTime").val();
				if (orderTime == null || orderTime.trim() == '') {
					$("#tip").html("预约上线，请选择预约上线时间");
					b = false;
					return b;
				}
			}
		}else if(state  == 1){
			//上线操作校验
			b= checkTimeExt(1);
		}else if(state == 2){
			//下线操作校验
			b= checkTimeExt(2);
		}else if (state == 4){
			//只修改不审核
			if(submitType != 3){
				$("#tip").html("只修改不审核只能保存");
				b = false ;
			}
		}else if (state == 999){
			$("#tip").html("请选择操作类型");
			b = false ;
		}
		
		//供应商校验
		if (supplierId == null || supplierId.trim() == '') {
			$("#tip").html("请选择供应商");
			b = false;
			return b;
		}
		//最小期限校验
		if (timeLimitMint == null || timeLimitMint.trim() == '') {
			$("#tip").html("请填写最小期限");
			b = false;
			return b;
		}else if(isNotNum(timeLimitMint)){
	    	$("#tip").html("最小期限只能是整数或者小数");
			b = false;
			return b;
	    }
	   //最大期限校验
		if (timeLimitMax == null || timeLimitMax.trim() == '') {
			$("#tip").html("请填写最大期限");
			b = false;
			return b;
		}else if(isNotNum(timeLimitMax)){
	    	$("#tip").html("最大期限只能是整数或者小数");
			b = false;
			return b;
	    }
	    
	    //放款时间校验
		if (loanTime == null || loanTime.trim() == '') {
			$("#tip").html("请填写放款时间");
			b = false;
			return b;
		}else if(isNotNum(loanTime)){
	    	$("#tip").html("放款时间只能是整数或者小数");
			b = false;
			return b;
	    }
	    
	    //利率校验，不填不校验，填了要校验
	    var dayRate = $("#dayRate").val();
	    if(dayRate==null ||dayRate == undefined || dayRate==""){
	    }else {
	    	if(isNotNum(dayRate)){
		    	$("#tip").html("利率只能是整数或者小数");
				b = false;
				return b;
		    }
	    }
	    
		
		//权重值校验
		if (sort == null || sort.trim() == '') {
			if(!swFlag){
				$("#tip").html("请选择权重值");
				b = false;
				return b;
			}
		}else{
			if(sort > 3000){
				$("#tip").html("权重值不大于3000");
				return false;
			}
		}
		var backupSort = $("#backupSort").val();
		var  checkFlag =  false ;
		//权重值校验
		if (backupSort == null || backupSort.trim() == '') {
		}else{
			var bsArry = backupSort.split("#");
			$.each(bsArry, function(i, n) {
				var s =  bsArry[i];
				if(isNotNumExt(s)){
					 checkFlag =  true ;
					 $("#tip").html("备用权重值只能是整数");
					 return false ;
				}
			});
		}
		
		if(checkFlag){
			return false ;
		}
		
		//推荐理由校验
	    if (recommendReason == null || recommendReason.trim() == '') {
			if(!swFlag){
		    	$("#tip").html("请选择推荐理由");
				b = false;
				return b;
			}
		}
		
		//提交类型为审核不通过需要判断审核不通过理由
		if(submitType == 1){
			if(state < 4){
				var refuseReason = $("#refuseReason").val();
				if(refuseReason == null || refuseReason.trim() == ''){
					$("#tip").html("请选择审核不通过理由");
					return false;
				}
			}
		}
		
		if(!b){
			return b;
		}else{
			$("#myState").val("");
		}
		
		//alert("======可以提交=====");
		//return false; 
		return b;
	}
	
	//整数
 	function isNotNumExt (numStr){
		var r = /^\+?[1-9][0-9]*$/;
		return !r.test(numStr);
	} 
	//包含小数点，不是数字返回true 否则返回false
	function isNotNum (numStr){
		var r = /^(([1-9]\d*)(\.\d{1,2})?|0\.([1-9]|\d[1-9])|\d.[0-9]{0,4}|0)$/;
		return !r.test(numStr);
	}
	
	
	function checkTimeExt(param){
		var currDate =  new Date();
		var currTimeStr = currDate.Format("yyyy-MM-dd HH:mm:ss");
		$("#tip").html("");
		//上线时间
		var currTime = getDate(currTimeStr);
		if(param == 1){
			//上线时间判断
			var  shangxianTimeStr = $("#shangxianTime").val();
			if(shangxianTimeStr == null || shangxianTimeStr.trim() == ''){
				
			}else{
				var sxDate = getDate(shangxianTimeStr);
				if(sxDate < currTime){
					$("#tip").html("上线时间不能早于当前时间，上线时间为空表示立即上线");
					$("#shangxianTime").val("");
					return false;
				}
				currTime = sxDate;
			}
			//判断下线时间
			var  xiaxianTimeStr = $("#xiaxianTime").val();
			if(xiaxianTimeStr == null ||  xiaxianTimeStr.trim() == ''){
				return true ;
			}else{
				//新增，当前时间取上线时间或者系统当前时间
				var xxDate = getDate(xiaxianTimeStr);
				if(xxDate < currTime){
					$("#tip").html("下线时间不能早于上线时间或者当前时间");
					return false;
				}else{
					return true;
				}
			}
		}
		//下线时间
		if(param == 2){
			//上线时间置空，因为上线时间对下线操作没有意义
			$("#shangxianTime").val("");
			var  offlineReason = $("#offlineReason").val();
			if(offlineReason == null || offlineReason.trim() == ''){
				$("#tip").html("下线操作，下线理由不能为空");
				return false ;
			}
			var  xiaxianTimeStr = $("#xiaxianTime").val();
			if(xiaxianTimeStr == null ||  xiaxianTimeStr.trim() == ''){
				return true ;
			}else{
				//新增，当前时间取上线时间或者系统当前时间
				var xxDate = getDate(xiaxianTimeStr);
				if(xxDate < currTime){
					$("#tip").html("下线时间不能早于上线时间或者当前时间");
					return false;
				}else{
					return true;
				}
			}
			
			
		} 
	}
	
	
	
	
	
	
	function setInputValue(){
    	$("#productSelect").attr("onclick","showValue()");
	}
	function showValue(){
	    var index = document.getElementById("productSelect").selectedIndex;
	    var text = document.getElementById("productSelect").options[index].text;
	    $("#input").val(text);
        $("#inputProductId").val("");
        // 刷新下拉列表
        if (text == '--全部--') {
        	$("#companyName").val("");
        	$("#productSelect").empty();
            var selectStrNew = "<option value=\" \">--全部--</option>";
            <c:forEach var="info" items="${prodNameList }">
	            var proIdNew  = "${info.productId }";
	            var proNameNew = "${info.productName }";
	            selectStrNew = selectStrNew + "<option value=\""+proIdNew+"\">"+proNameNew+"</option>";
            </c:forEach>
            $("#productSelect").html(selectStrNew);
        }else{
		    //////////////////////////////////////////////////////////////////////////////////////
		    <c:forEach var="info" items="${prodNameList }">
		        var productIdSelect = $("#productSelect").val();
		        var  productId =  "${info.productId }"; 
		        if(productId == productIdSelect){
		           var  companyName = "${info.companyName }";
		           $("#companyName").val(companyName);
		           return;
		        }
		    </c:forEach>
		    //////////////////////////////////////////////////////////////////////////////////////
        }
    }
	
	function getProductValue(data){
		$("#productSelect").empty();
        var str = "<option value=\" \">--全部--</option>";
        <c:forEach var="info" items="${prodNameList }">
            var proIdNew  = "${info.productId }";
            var proNameNew = "${info.productName }"
            // 将包含输入文字的下拉框填充进下拉列表
            if (proNameNew.indexOf(data) != -1){
                str = str + "<option value=\""+proIdNew+"\">"+proNameNew+"</option>";
            }
            // 如果输入文字后没有出发下拉框的选择事件，则会使用到此处的商户ID
            if (data == proNameNew) {
                document.getElementById("inputProductId").value = proIdNew;
                //$("#companyName").val("123456");
            }
            // 输入空的时候查询所有
            if (data == ""){
                $("#inputProductId").val("");
            }
        </c:forEach>
        $("#productSelect").html(str);
        $("#productSelect").attr("onclick","setInputValue()");
    }
	
	

	$(function() {
		
		$("#product_company_id").hide();
		
		////////////////////模糊查询///////////////////////////
	    $("#productSelect").empty();
           var selectStr = "<option value=\" \">--全部--</option>";
           <c:forEach var="info" items="${prodNameList }">
            var proId  = "${info.productId }";
            var proName = "${info.productName }"
            selectStr = selectStr + "<option value=\""+proId+"\">"+proName+"</option>";
           </c:forEach>
           $("#productSelect").html(selectStr);
		////////////////////模糊查询///////////////////////////
		if ('${entity}' != '') {
		$("#nameShow").attr("readonly","readonly");  
		//权限控制
	/* 	<shiro:hasPermission name = "spread:delete">
		</shiro:hasPermission> */
		}
		$('#shangxianTime').datetimepicker({
			format : 'yyyy-mm-dd hh:ii:ss'
		});
		$('#xiaxianTime').datetimepicker({
			format : 'yyyy-mm-dd hh:ii:ss'
		});

	 	$('#servicePhone').blur(function() {
			var  servicePhone=  $("#servicePhone").val();
			var  ret = /^1[3,5,7,8]\d{9}$|\d{3}-\d{8}|\d{4}-\d{7}/;
	 	    if(!ret.test(servicePhone)){
	 	    	$("#servicePhone").val("");
		    	$("#servicePhone").attr('placeholder','请输入正确的手机号码');
		    } 
		}); 
		
		
		$('#uploadproductImg').click(function() {
			var file = document.productForm.productImgFile.value;
			if (file == "") {
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'productImgFile',
				url : "upload/product.htm",
				data : {
					"id" : "${entity.productId }"
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
			if (file == "") {
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'smallLabelImgFile',
				url : "upload/smalLabelImg.htm",
				data : {
					"id" : "${entity.productId }"
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
			if (file == "") {
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'applyProcessFile',
				url : "upload/applyprocessImg.htm",
				data : {
					"id" : "${entity.productId }"
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
		$('#backFlag').change(function() {
			var backFlag =  $("#backFlag").val();
			if(backFlag == 0){
				$("#backUrlDev").hide();
				$("#backAccountDev").hide();
				$("#backPwdDev").hide();
			}else{
				$("#backUrlDev").show();
				$("#backAccountDev").show();
				$("#backPwdDev").show();
			}
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
							t <= 0 ? sel.moveStart("character", wee - 2 * t
									- myValue.length) : sel.moveStart(
									"character", wee - t - myValue.length);
							sel.select();
						}
					} else if ($t.selectionStart || $t.selectionStart == '0') {
						var startPos = $t.selectionStart;
						var endPos = $t.selectionEnd;
						var scrollTop = $t.scrollTop;
						$t.value = $t.value.substring(0, startPos) + myValue
								+ $t.value.substring(endPos, $t.value.length);
						this.focus();
						$t.selectionStart = startPos + myValue.length;
						$t.selectionEnd = startPos + myValue.length;
						$t.scrollTop = scrollTop;
						if (arguments.length == 2) {
							$t.setSelectionRange(startPos - t, $t.selectionEnd
									+ t);
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