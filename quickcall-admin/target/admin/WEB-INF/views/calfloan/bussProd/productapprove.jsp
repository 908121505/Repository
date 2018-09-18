<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

#approvepage1 {
	display: inline-block;
	margin: 0;
	padding: 0;
	width: 40%;
	height: 20%;
	position: absolute;
	left: 50%;
	top: 40%;
	transform: translate(-50%, -50%);
	background: #2d2d2d;
}

#approvepage2 {
	display: inline-block;
	margin: 0;
	padding: 0;
	width: 40%;
	height: 20%;
	position: absolute;
	left: 50%;
	top: 40%;
	transform: translate(-50%, -50%);
	background: #2d2d2d;
}

#approvepage3 {
	display: inline-block;
	margin: 0;
	padding: 0;
	width: 40%;
	height: 20%;
	position: absolute;
	left: 50%;
	top: 40%;
	transform: translate(-50%, -50%);
	background: #2d2d2d;
}

.close .close_ext {
	display: block;
	width: 100%;
	padding-right: 0;
	padding-left: 0;
	color: #fff;
	background-color: #428bca;
	border-color: #357ebd;
	padding: 6px 12px;
	border: 1px solid transparent;
	border-top-color: transparent;
	border-right-color: transparent;
	border-bottom-color: transparent;
	border-left-color: transparent;
	border-radius: 4px;
	margin-bottom: 0;
	font-size: 14px;
	font-weight: normal;
	line-height: 1.42857143;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	cursor: pointer;
	opacity: unset;
}
</style>




</head>
<div class="modal-dialog" id="resourcepage">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">商户上线推广审核</h3>
		</div>
		<div class="modal-body" style="max-height: 600px; overflow-y: auto;" id = "myBody">
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
				<div class="form-group"   >
					<label class="col-sm-4 control-label">公司名称</label>
					<div class="col-sm-8">
						<input type="text"  class="form-control"  id = "companyName"   value="${companyName}"  readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">商户名称</label>
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
						<input type="text" class="form-control" id="product_name"
							name="productName" value="${entity.productName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">副标题</label>
					<div class="col-sm-8">
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
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">商户类型</label>
					<div class="col-sm-8">
						<select class="form-control" id="product_category_id"
							name="productCategoryId">
							<c:forEach var="info" items="${productCategorys }">
								<option value="${info.productCategoryId }">${info.categoryName }</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">备用权重值(市场推广用,多个以#分隔)</label>
					<div class="col-sm-8">
						<input type="text" id="backupSort" class="form-control"
							name="backupSort" value="${entity.backupSort}">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">推荐理由</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<textarea id="recommendReason" name="recommendReason" cols="45"
								rows="5" style="overflow: auto">${entity.recommendReason}</textarea>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">推荐理由（带颜色）</label>
					<div class="col-sm-8">
						<div class="col-sm-12">
							<textarea id="recommendReasonColor" name="recommendReasonColor" cols="45"
								rows="5" style="overflow: auto">${entity.recommendReasonColor}</textarea>
								<br>
								<h4>展示效果：${entity.recommendReasonColor}</h4>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">上榜理由</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<textarea id="productJoice" name="productJoice" cols="45"
								rows="5" style="overflow: auto">${entity.productJoice}</textarea>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">供应商</label>
					<div class="col-sm-8">
						<select class="form-control" id="supplierId" name="supplierId">
							<c:forEach var="info" items="${suppliers }">
								<option value="${info.supplierId }">${info.supplierName }</option>
							</c:forEach>


						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">所属专区</label>
					<div class="col-sm-8">
						<c:forEach var="info" items="${zones}">
							<label><input type="checkbox" name="zoneId"
								value="${info.zoneId}" />${info.zoneName }</label>
						</c:forEach>

					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-4 control-label">商户链接地址</label>
					<div class="col-sm-8">
						<input type="text" id="productUrl" class="form-control"
							name="productUrl" value="${entity.productUrl}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">备用链接(市场推广用,多个以#分隔)</label>
					<div class="col-sm-8">
						<input type="text" id="backupProductUrl" class="form-control"
							name="backupProductUrl" value="${entity.backupProductUrl}">
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-4 control-label">申请完成链接地址</label>
					<div class="col-sm-8">
						<input type="text" id="linkUrlH" class="form-control"
							name="linkUrlH" value="${entity.linkUrlH}">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">商户图片上传</label>
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



				<div class="form-group">
					<label class="col-sm-4 control-label">新手指导</label>
					<div class="col-sm-8">
						<textarea id="remark" name="remark" cols="45" rows="5"
							style="overflow: auto">${entity.remark}</textarea>
					</div>
				</div>

				<div class="form-group">
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
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">商户标签</label>
					<div class="col-sm-8">
						<c:forEach var="parentLabel" items="${parentLabels}">
							<label><b>${parentLabel.labelName }</b></label>
							<br>
							<c:forEach var="plabel" items="${productLabels}">
								<c:if test="${plabel.parentId == parentLabel.productLabelId}">
									<label><input type="checkbox" name="productLabel"
										value="${plabel.productLabelId }" />${plabel.labelName }</label>
								</c:if>
							</c:forEach>
							<hr>
						</c:forEach>
					</div>
				</div>

				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">借款审核细节</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<textarea id="auditDesc" name="auditDesc" cols="45" rows="5"
								style="overflow: auto">${entity.auditDesc}</textarea>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">申请条件(多个条件用','号分割)</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<textarea id="applyCon" name="applyCon" cols="45" rows="5"
								style="overflow: auto">${entity.applyCon}</textarea>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">申请流程格式：图标|内容(多个流程用','号分割)</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<textarea id="applyProcess" name="applyProcess" cols="45"
								rows="10" style="overflow: auto">
								${entity.applyProcess}</textarea>
						</div>
					</div>
				</div>
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
					<label class="col-sm-4 control-label">下款率</label>
					<div class="col-sm-8">
						<input type="number" id="theRateOf" class="form-control"
							name="theRateOf" value="${entity.theRateOf}">
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-4 control-label">最小额度</label>
					<div class="col-sm-8">
						<input type="text" id="loanRangeMin" class="form-control"
							name="loanRangeMin" value="${entity.loanRangeMin}">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">最大额度</label>
					<div class="col-sm-8">
						<input type="text" id="loanRangeMax" class="form-control"
							name="loanRangeMax" value="${entity.loanRangeMax}">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">最小期限</label>
					<div class="col-sm-8">
						<input type="text" id="timeLimitMint" class="form-control"
							name="timeLimitMint" value="${entity.timeLimitMint}">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">最大期限</label>
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
					<label class="col-sm-4 control-label">放款时间（小时）</label>
					<div class="col-sm-8">
						<input type="text" id="loanTime" class="form-control"
							name="loanTime" value="${entity.loanTime}">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">成功率(如0.1=10%)</label>
					<div class="col-sm-8">
						<input type="text" id="successRate" class="form-control"
							name="successRate" value="${entity.successRate}">
					</div>
				</div>

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

				<div class="form-group">
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
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">活动类型</label>
					<div class="col-sm-5">
                           <select class="form-control" id="spreadType" name = "spreadType">
                               <c:forEach var="item"   items="${spreadTypeList }" >
                                   <option value="${item.id }"   <c:if test="${item.id ==  prodExt.spreadType}"> selected="selected" </c:if> >${item.modelContent }</option>
                               </c:forEach>
                           </select>
                     </div>
				</div> 
				
				<div class="form-group">
					<label class="col-sm-4 control-label">客服电话</label>
					<div class="col-sm-8">
						<input type="text" id="servicePhone" class="form-control"
							name="servicePhone" value="${prodExt.servicePhone }"
							placeholder="请输入客服电话">
					</div>
				</div>
				<!-- 预约下线时间 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">官网地址</label>
					<div class="col-sm-8">
						<input type="text" id="officialWebset" class="form-control"
							name="officialWebset" value="${prodExt.officialWebset }"
							placeholder="请输入官网地址">

					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">商户状态</label>
					<div class="col-sm-8">
						<input type="text" id="state" class="form-control"
							readonly="readonly"
							<c:if test="${entity.state == 1}">
									value="已上线"
							</c:if>
							<c:if test="${entity.state == 2}">
									value="已下线"
							</c:if>
							<c:if test="${entity.state == 30}">
									value="待预约上线"
							</c:if>
							<c:if test="${entity.state == 3}">
									value="已预约上线"
							</c:if>
							<c:if test="${entity.state == 4}">
									value="待上线"
							</c:if>
							<c:if test="${entity.state == 5}">
									value="待下线"
							</c:if>>
					</div>
				</div>



				<!-- 预约上线时间 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">预约上线时间</label>
					<div class="col-sm-8">
							<c:if test="${prodExt.productStatus == 3  ||  prodExt.productStatus == 30 }">
								<input type="text"  class="form-control"  value="${orderTimeStr}"  readonly="readonly">
						    </c:if>
							<c:if test="${prodExt.productStatus != 3  &&  prodExt.productStatus != 30 }">
								<input type="text"  class=" form-control"  value=""  readonly="readonly">
						    </c:if>
						<%-- <input type="text" readonly="readonly" id="orderTime"
							class="form-control" name="orderTimeStr" value="${orderTimeStr }"> --%>

					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">上线时间</label>
					<div class="col-sm-8">
						<input type="text" class="form-control"
							value="${prodExt.shangxianTimeStr}" readonly="readonly"  id = "shangxianTime">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">下线时间</label>
					<div class="col-sm-8">
						<input type="text" class="form-control"
							value="${prodExt.xiaxianTimeStr}" readonly="readonly" id ="xiaxianTime">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">下线理由</label>
					<div class="col-sm-8">
						<textarea id="offlineReason" name="offlineReason" cols="45"
							rows="5" style="overflow: auto">${prodExt.offlineReason}</textarea>
					</div>
				</div>
								<div class="form-group">
					<label class="col-sm-4 control-label">是否推荐</label>
					<div class="col-sm-8">
						<select class="form-control" id="is_recommend" name="isRecommend">
							<option value="1">推荐</option>
							<option value="2">不推荐</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">权重值</label>
					<div class="col-sm-8">
						<input type="number" id="sort" class="form-control" name="sort"
							value="${entity.sort}">
					</div>

				</div>







			</form>
		</div>
		<div class="modal-footer">
			<span id="tip" style="color: red; font-size: 16px; margin-left: 20px;float:left;"></span>
			<div class="col-md-1 " style="width: 20%;">
				<div class="form-group">
					<a class="btn btn-primary btn-small btn-block" id="refuse"
						onclick="showpage(1);">不通过</a>
				</div>
			</div>
			<div class="col-md-1  " style="width: 20%;">
				<div class="form-group">
					<a class="btn btn-primary btn-small btn-block" id="confirm"
						onclick="showpage(2);">通过</a>
				</div>
			</div>
		</div>
	</div>
</div>




<!-- 不通过页面 -->
<div class="modal-dialog" id="approvepage1" style="display: none">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">不通过</h3>
		</div>
		<div class="modal-body" style="max-height: 600px; overflow-y: auto;">
			<form class="form-horizontal" method="post" action="#" role="form">
				<%-- <div class="form-group">
					<label class="col-sm-4 control-label">商户ID</label>
					<div class="col-sm-8">
						<input type="text" id="companyName" readonly="readonly"
							class="form-control" name="productId" value="${entity.productId}">
					</div>
				</div> --%>
				<div class="form-group">
					<label class="col-sm-4 control-label">商户名称</label>
					<div class="col-sm-8">
						<input type="text"  readonly="readonly"
							class="form-control" name="productId" value="${entity.productName}">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">通过状态</label>
					<div class="col-sm-8">
						<input type="text" id="refuseType" readonly="readonly"
							class="form-control" name="refuseType" value=""> <input
							id="refuseTypeValue" type="hidden" class="form-control" value="">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">不通过理由<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<textarea id="refuseReason" name="refuseReason" cols="45" rows="5"
							style="overflow: auto"></textarea>
					</div>
				</div>

				<span id="tip1"
					style="color: red; font-size: 14px; margin-left: 20px;"></span>
			</form>
		</div>

		<div class="modal-footer">
			<div class="col-md-1 " style="width: 20%;" aria-hidden="true">
				<div class="form-group">
					<button type="button" class="close  close_ext"
						style="display: block; width: 100%; padding-right: 0; padding-left: 0; color: #fff; background-color: #428bca; border-color: #357ebd; padding: 6px 12px; border: 1px solid transparent; border-top-color: transparent; border-right-color: transparent; border-bottom-color: transparent; border-left-color: transparent; border-radius: 4px; margin-bottom: 0; font-size: 14px; font-weight: normal; line-height: 1.42857143; text-align: center; white-space: nowrap; vertical-align: middle; cursor: pointer; opacity: unset;"
						data-dismiss="modal" aria-hidden="true">取消</button>
				</div>
			</div>
			<div class="col-md-1  " style="width: 20%;">
				<div class="form-group">
					<a class="btn btn-primary btn-small btn-block" id="approveno"
						onclick="submit(1)">不通过</a>
				</div>
			</div>
		</div>


	</div>
</div>


<!-- 通过页面 -->
<div class="modal-dialog" id="approvepage2" style="display: none">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">通过</h3>
		</div>
		<div class="modal-body" style="max-height: 600px; overflow-y: auto;">
			<form class="form-horizontal" method="post" action="#" role="form">
				<%-- <div class="form-group">
					<label class="col-sm-4 control-label">商户ID</label>
					<div class="col-sm-8">
						<input type="text"  readonly="readonly"
							class="form-control" name="productId" value="${entity.productId}">
					</div>
				</div> --%>
				<div class="form-group">
					<label class="col-sm-4 control-label">商户名称</label>
					<div class="col-sm-8">
						<input type="text"  readonly="readonly"
							class="form-control" name="productId" value="${entity.productName}">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">通过状态</label>
					<div class="col-sm-8">
						<input type="text" id="auditType" readonly="readonly"
							class="form-control" name="auditType" value=""> <input
							id="auditTypeValue" type="hidden" class="form-control" value="">
					</div>
				</div>

				<!-- <div class="form-group">
					<label class="col-sm-4 control-label">通过理由</label>
					<div class="col-sm-8">
						<textarea id="approveReason" name="approveReason" cols="45"
							rows="5" style="overflow: auto"  ></textarea>
					</div>
				</div> -->
			</form>
			<span id="tip2"
				style="color: red; font-size: 14px; margin-left: 20px;"></span>
		</div>

		<div class="modal-footer">
			<div class="col-md-1 " style="width: 20%;">
				<div class="form-group">
					<button type="button" class="close"
						style="display: block; width: 100%; padding-right: 0; padding-left: 0; color: #fff; background-color: #428bca; border-color: #357ebd; padding: 6px 12px; border: 1px solid transparent; border-top-color: transparent; border-right-color: transparent; border-bottom-color: transparent; border-left-color: transparent; border-radius: 4px; margin-bottom: 0; font-size: 14px; font-weight: normal; line-height: 1.42857143; text-align: center; white-space: nowrap; vertical-align: middle; cursor: pointer; opacity: unset;"
						data-dismiss="modal" aria-hidden="true">取消</button>
				</div>
			</div>
			<div class="col-md-1  " style="width: 20%;">
				<div class="form-group">
					<a class="btn btn-primary btn-small btn-block" id="approveyes"
						onclick="submit(2)">通过</a>
				</div>
			</div>
		</div>

	</div>
</div>



<!-- 提示框 -->
<div class="modal-dialog" id="approvepage3"
	style="display: none; width: 400px;">
	<div class="modal-content">
		<div class="modal-header" style="border-bottom: none;">
			<h3 id="showInfo" style="text-align: center;"></h3>
		</div>

		<div class="modal-footer" style="border-top: none;">
			<button type="button" class="close" data-dismiss="modal"
				style="display: block; width: 100%; padding-right: 0; padding-left: 0; color: #fff; background-color: #428bca; border-color: #357ebd; padding: 6px 12px; border: 1px solid transparent; border-top-color: transparent; border-right-color: transparent; border-bottom-color: transparent; border-left-color: transparent; border-radius: 4px; margin-bottom: 0; font-size: 14px; font-weight: normal; line-height: 1.42857143; text-align: center; white-space: nowrap; vertical-align: middle; cursor: pointer; opacity: unset;"
				aria-hidden="true" onclick="closePage()">确认</button>
		</div>

	</div>
</div>


<script type="text/javascript">


$("#product_company_id").hide();

if ('${entity}' != '') {
	$("#productName").attr("readonly","readonly"); 
	$("#product_category_id").val('${entity.productCategoryId}');
	$("#zoneId").val('${entity.zoneId}');
	$("#supplierId").val('${entity.supplierId}');
	$("#is_recommend").val('${entity.isRecommend}');
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

}




if ('${prodExt}' != '') {
	//前提条件，能进入到该页面的数据都是待审核状态
	var  productStatus = ${prodExt.productStatus};
	
	//现将内容置空，然后赋值
	$("#refuseType").val();
	$("#auditType").val();
	
	var shangxianTime =  "${prodExt.shangxianTimeStr}";
	var xiaxianTime =   "${prodExt.xiaxianTimeStr}";
	// 1：上线  2：下线  3：待预约上线  4：待上线  5：待下线',
	if(productStatus == 4){
		//1.上线时间和下线时间都为空
		if((shangxianTime == null || shangxianTime.trim() == '')  && (xiaxianTime == null || xiaxianTime.trim() == '')){
			$("#refuseType").val("上线不通过");
			$("#auditType").val("上线通过");
		}else if((shangxianTime != null || shangxianTime.trim() != '')  && (xiaxianTime == null || xiaxianTime.trim() == '')){
			$("#refuseType").val("上线不通过");
			$("#auditType").val("上线通过");
		}else{
			$("#refuseType").val("上线不通过和下线不通过");
			$("#auditType").val("上线通过和下线通过");
		}
		$("#auditTypeValue").val(1);
		$("#refuseTypeValue").val(1);
		
	}else if(productStatus == 30){
		$("#refuseType").val("预约上线不通过");
		$("#auditType").val("预约上线通过");
		$("#auditTypeValue").val(3);
		$("#refuseTypeValue").val(3);
	}else if (productStatus == 5){
		$("#refuseType").val("下线不通过");
		$("#auditType").val("下线通过");
		$("#auditTypeValue").val(2);
		$("#refuseTypeValue").val(2);
	}
	

}

	function showpage(type) {
		//校验必输参数
		$("#tip").html("");
		var  sort = $("#sort").val();
		
		if(type == 2){
			if(sort == null || sort.trim() == ''){
				$("#tip").html("权重值为空，不能审核，请设置权重值");
				return ;
			}
			
			var  recommend = $("#is_recommend").val();
			if(recommend == null || recommend.trim() == ''){
				$("#tip").html("是否推荐为空，不能审核，请设置是否推荐");
				return ;
			}
		}
		$("#resourcepage").css({
			display : "none"
		});
		$("#approvepage2").css({
			display : "none"
		});
		$("#approvepage1").css({
			display : "none"
		});
		$("#approvepage3").css({
			display : "none"
		});
		if (type == 1) {
			$("#approvepage1").css({
				display : "inline-block"
			});
		} else if (type == 2) {
			$("#approvepage2").css({
				display : "inline-block"
			});
		}
	}

	function closePage() {
		$("#resourcepage").css({
			display : "none"
		});
		$("#approvepage2").css({
			display : "none"
		});
		$("#approvepage1").css({
			display : "none"
		});
		$("#approvepage3").css({
			display : "none"
		});
		$('#content').load('spread/innerhome.htm');
	}
	function submit(type) {
		var productId = $("#product_id").val();
		var approveType = "";
		var reason = "";
		var showId = "";
		var showmsg = "";
		var orderTimeStr = "${orderTimeStr }";
		
		
		if (type == 1) {//不通过
			approveType = $("#refuseTypeValue").val();
			reason = $("#refuseReason").val();
			showId = "#approvepage1";
			if (1 == approveType) {
				
				showmsg = "拒绝上线";
			} else if (2 == approveType) {
				showmsg = "拒绝下线";
			} else if (3 == approveType) {
				showmsg = "拒绝预约上线";
			}
			if(reason == null || reason.trim() == ''){
				$("#tip1").html("请输入不通过理由");
				return ;
			}else{
				$("#tip1").html("");
			}
			
			
		} else if (type == 2) {//通过
			approveType = $("#auditTypeValue").val();
			/* reason = $("#approveReason").val(); */
			showId = "#approvepage2";
			

			if (1 == approveType) {
				showmsg = "上线成功";
			} else if (2 == approveType) {
				showmsg = "下线成功";
			} else if (3 == approveType) {
				showmsg = "预约上线成功";
			}
		}

		$.ajax({
			url : "spread/approvesub.htm",
			method : "post",
			dataType : "json",
			data : {
				"productId" : productId,
				"approveType" : approveType,
				"type" : type,
				"reason" : reason,
				"orderTimeStr":orderTimeStr
			},
			success : function(data) {
				$(showId).hide();
				if (data.flag === true) {
					if (showmsg != null && showmsg != '') {
						$("#showInfo").text(showmsg);
						$("#approvepage3").css({
							display : "inline-block"
						});
					}
					/* $('#content').load('spread/newhome.htm'); */
				}else {
					var reasonFlag =  data.reasonFlag;
					if(reasonFlag == 1){
						showmsg = "上线审核不通过，上线时间已过，审核不通过"
					}else if(reasonFlag == 2){
						showmsg = "下线审核不通过，下线时间已过，审核不通过"
					}else {
						showmsg = "预约上线审核不通过，预约上线时间已过，审核不通过"
					}
					if (showmsg != null && showmsg != '') {
						$("#showInfo").text(showmsg);
						$("#approvepage3").css({
							display : "inline-block"
						});
					}
				}
				
			},
			error : function() {

			}
		});

	}

	


	
	function check_fun() {
		$("#tip").html("");
		var b = true;
		return b;
	}

	$(function() {
		$("#myBody").find("input").attr("disabled",true);  
		$("#myBody").find("textarea").attr("disabled",true);  
		$("#myBody").find("select").attr("disabled",true);  
		$("#delImg").attr("disabled",true);  
		$("#uploadproductImg").attr("disabled",true);  
		$("#uploadsmallLabelImg").attr("disabled",true);  
		$("#uploadproductImg").attr("disabled",true);  
	});
</script>