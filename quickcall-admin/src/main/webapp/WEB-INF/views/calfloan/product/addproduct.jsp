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
				action="product/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				 <c:if test="${empty entity==false}">
					<div class="form-group">
						<label class="col-sm-4 control-label">产品id</label>
							<div class="col-sm-8">
								<input type="text" id="product_id" readonly="readonly" class="form-control" name="productId"
									value="${entity.productId}">
							</div>
						
					</div>
				</c:if>
				<div class="form-group">
					<label class="col-sm-4 control-label">产品名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
					   <c:if test="${empty entity==false}">
							<input type="hidden" value="${entity.createTime }" id="createTime" name="createTime" /> 
							<input type="hidden" value="${entity.createMan }" id="createMan" name="createMan" /> 
							<input type="hidden" value="${entity.modifyTime }" id="modifyTime" name="modifyTime" /> 
							<input type="hidden" value="${entity.modifyMan }" id="modifyMan" name="modifyMan" /> 
							<input  type="hidden" value="${orderTimeStr }" id="oldOrderTimeStr" name="oldOrderTimeStr" /> 
							<input  type="hidden" value="${entity.state }" id="oldState" name="oldState" /> 
						</c:if>
						<input type="text" class="form-control" id="product_name" name="productName" value="${entity.productName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">副标题</label>
					<div class="col-sm-8">
						<input type="text" id="viceName"  class="form-control" name="viceName"
							value="${entity.viceName}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">分享标题</label>
					<div class="col-sm-8">
						<input type="text" id="shareTitle"  class="form-control" name="shareTitle"
							value="${entity.shareTitle}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">分享内容</label>
					<div class="col-sm-8">
						<input type="text" id="shareContent"  class="form-control" name="shareContent"
							value="${entity.shareContent}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">产品类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<select class="form-control" id="product_category_id" name="productCategoryId">
								<c:forEach var="info" items="${productCategorys }">
									<option value="${info.productCategoryId }">${info.categoryName }</option>
								</c:forEach>
						</select>
					</div>
				</div>
					<div class="form-group">
					<label class="col-sm-4 control-label">是否推荐<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<select class="form-control" id="is_recommend" name="isRecommend">
							  <option value="1">推荐</option>
							  <option value="2">不推荐</option>
						</select>
					</div>
				</div>
				
					<div class="form-group">
					<label class="col-sm-4 control-label">权重值<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="number" id="sort"  class="form-control" name="sort"
							value="${entity.sort}">
					</div>
					
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">备用权重值(市场推广用,多个以#分隔)</label>
					<div class="col-sm-8">
						<input type="text" id="backupSort"  class="form-control" name="backupSort"
							value="${entity.backupSort}">
					</div>
				</div>
					<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">推荐理由</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
								<textarea  id="recommendReason" name="recommendReason"  cols="45" rows="5" style="overflow:auto">${entity.recommendReason}</textarea>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">上榜理由</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
								<textarea  id="productJoice" name="productJoice"  cols="45" rows="5" style="overflow:auto">${entity.productJoice}</textarea>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">供应商<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<select class="form-control" id="supplierId" name="supplierId">
								<c:forEach var="info" items="${suppliers }">
									<option value="${info.supplierId }">${info.supplierName }</option>
								</c:forEach>
								
								
						</select>
					</div>
				</div>
			
				<!-- <div class="form-group">
					<label class="col-sm-4 control-label">产品状态 <font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<select class="form-control" id="state_id" name="state">
							  <option value="1">有效</option>
							  <option value="2">无效</option>
						</select>
					</div>
				</div> -->
					<div class="form-group">
					<label class="col-sm-4 control-label">所属专区<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
								<c:forEach var="info" items="${zones}">
					                     <label><input type="checkbox" name="zoneId" value="${info.zoneId}"/>${info.zoneName }</label>  
					            </c:forEach> 
						
					</div>
				</div>
				
			
				<div class="form-group">
					<label class="col-sm-4 control-label">产品链接地址</label>
					<div class="col-sm-8">
						<input type="text" id="productUrl"  class="form-control" name="productUrl"
							value="${entity.productUrl}">
					</div>
				</div>
					<div class="form-group">
					<label class="col-sm-4 control-label">备用链接(市场推广用,多个以#分隔)</label>
					<div class="col-sm-8">
						<input type="text" id="backupProductUrl"  class="form-control" name="backupProductUrl"
							value="${entity.backupProductUrl}">
					</div>
				</div>
				
				
				<div class="form-group">
					<label class="col-sm-4 control-label">申请完成链接地址</label>
					<div class="col-sm-8">
						<input type="text" id="linkUrlH"  class="form-control" name="linkUrlH"
							value="${entity.linkUrlH}">
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
					<label for="inputEmail3" class="col-sm-4 control-label">产品图片</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<img src="${entity.productImg }"
								class="img-rounded" style="max-width: 100px; max-height: 80px;"
								id="productImgsrc"/>
							<input type="hidden" class="form-control" name="productImg"
							id="productImg" value="${entity.productImg }">
						</div>
					</div>
				</div>
				
			
				
				<div class="form-group">
					<label class="col-sm-4 control-label">新手指导</label>
					<div class="col-sm-8">
							<textarea  id="remark" name="remark"  cols="45" rows="5" style="overflow:auto">${entity.remark}</textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">产品小标签文字</label>
					<div class="col-sm-8">
						<input type="text" id="smallLabelFont"  class="form-control" name="smallLabelFont"
							value="${entity.smallLabelFont}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">产品小标签图片上传</label>
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
					<label for="inputEmail3" class="col-sm-4 control-label">产品小标签图片</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<img src="${entity.smallLabelImg }"
								class="img-rounded" style="max-width: 100px; max-height: 80px;"
								id="smallLabelImgsrc"/>
							<input type="hidden" class="form-control" name="smallLabelImg"
							id="smallLabelImg" value="${entity.smallLabelImg }">
							<button class="btn btn-success" type="button"
									id="delImg" onclick="delsmallLaelImg()">清空图片</button>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">产品标签</label>
					<div class="col-sm-8">
					     <c:forEach var="parentLabel" items="${parentLabels}">
					          <label><b>${parentLabel.labelName }</b></label>
					           <br>
					            <c:forEach var="plabel" items="${productLabels}">
					                 <c:if test="${plabel.parentId == parentLabel.productLabelId}">
					                     <label><input type="checkbox" name="productLabel" value="${plabel.productLabelId }"/>${plabel.labelName }</label>  
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
								<textarea  id="auditDesc" name="auditDesc"  cols="45" rows="5" style="overflow:auto">${entity.auditDesc}</textarea>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">申请条件(多个条件用','号分割)</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
								<textarea  id="applyCon" name="applyCon"  cols="45" rows="5" style="overflow:auto">${entity.applyCon}</textarea>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">申请流程格式：图标|内容(多个流程用','号分割)</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
								<textarea  id="applyProcess"  name="applyProcess"  cols="45" rows="10" style="overflow:auto">
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
						<input type="number" id="applyNum"  class="form-control" name="applyNum"
							value="${entity.applyNum}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">下款率 <font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="number" id="theRateOf" class="form-control" name="theRateOf"
							value="${entity.theRateOf}">
					</div>
				</div>
				
				
				
				<%-- <div class="form-group">
					<label class="col-sm-4 control-label">日还款</label>
					<div class="col-sm-8">
						<input type="text" id="dayRepayment"  class="form-control" name="dayRepayment"
							value="${entity.dayRepayment}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">总利息</label>
					<div class="col-sm-8">
						<input type="text" id="allAccrual"  class="form-control" name="allAccrual"
							value="${entity.allAccrual}">
					</div>
				</div> --%>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">最小额度</label>
					<div class="col-sm-8">
						<input type="text" id="loanRangeMin"  class="form-control" name="loanRangeMin"
							value="${entity.loanRangeMin}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">最大额度</label>
					<div class="col-sm-8">
						<input type="text" id="loanRangeMax"  class="form-control" name="loanRangeMax"
							value="${entity.loanRangeMax}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">最小期限<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="timeLimitMint"  class="form-control" name="timeLimitMint"
							value="${entity.timeLimitMint}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">最大期限<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="timeLimitMax"  class="form-control" name="timeLimitMax"
							value="${entity.timeLimitMax}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">期限类型</label>
					<div class="col-sm-8">
						<select class="form-control" id="timeLimitType" name="timeLimitType">
							  <option value="1">天</option>
							  <option value="2">月</option>
						</select>
					</div>
				</div>
				
				<%-- <div class="form-group">
					<label class="col-sm-4 control-label">产品详情攻略</label>
					<div class="col-sm-8">
						<input type="text" id="articleId"  class="form-control" name="articleId"
							value="${entity.articleId}">
					</div>
				</div> --%>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">放款时间（小时）<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="loanTime"  class="form-control" name="loanTime"
							value="${entity.loanTime}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">成功率(如0.1=10%)</label>
					<div class="col-sm-8">
						<input type="text" id="successRate"  class="form-control" name="successRate"
							value="${entity.successRate}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">利率(如0.1=10%)</label>
					<div class="col-sm-8">
						<input type="text" id="dayRate"  class="form-control" name="dayRate"
							value="${entity.strdayRate}">
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
						<input type="text" id="serviceRate"  class="form-control" name="serviceRate"
							value="${entity.serviceRate}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">推荐个数</label>
					<div class="col-sm-8">
						<input type="text" id="recommendNum"  class="form-control" name="recommendNum"
							value="${entity.recommendNum}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">不推荐个数</label>
					<div class="col-sm-8">
						<input type="text" id="noRecommendNum"  class="form-control" name="noRecommendNum"
							value="${entity.noRecommendNum}">
					</div>
				</div>
				<%-- <div class="form-group">
					<label class="col-sm-4 control-label">关联规则</label>
					<div class="col-sm-8">
						<select class="form-control" id="ruleId" name="ruleId">
							<c:forEach var="info" items="${rules}">
								<option value="${info.productRuleId }"${fn:contains(info.productRuleId,entity.ruleId)?'selected':'' }>
										${info.ruleName}
								</option>
							</c:forEach>
						</select>
					</div>
				</div> --%>
				
				
								<div class="form-group">
					<label class="col-sm-2 control-label"  style="width:33.33%;">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10"   style="width:66.66%;">
						<label class="checkbox-inline"  onclick="showTimeInfo('1')"> <input type="radio"
							name="state" value="1" ${entity.state=='1'?'checked':'' }>
							上线
						</label> 
						
						<label class="checkbox-inline"  onclick="showTimeInfo('2')"> <input type="radio"
							name="state" value="2" ${entity.state=='2'?'checked':'' }>
							下线
						</label>
						</label> 
						<label class="checkbox-inline"  onclick="showTimeInfo('3')"> <input type="radio"
							name="state" value="3" ${entity.state=='3'?'checked':'' }>
							预约上线
						</label>
						<input  type ="hidden"  value="${entity.state}" id="myState"  /> 
					</div>
				</div>
				
				
				<div class="form-group"  style = "height:170px;" id = "onlineInfo">
				<!-- <div class="col-sm-10" style="padding-right: 0;padding-left: 0;"></div> -->
					<div class="col-sm-10" style="padding-right: 0;padding-left: 0;float: right;width:66%;">
					
					  <label class="col-sm-4 control-label lable_1" style = "width:25%;"  id = "label_1"  onclick="changebackground('1')">
						  <p class ="p_common"
						      <c:if test= "${prodInfo.afterEightFlag == 0}">  
						     		style = "background:rgba(255, 153, 0, 1);color:rgba(255, 255, 255, 255);" 
						     </c:if>
						  
						  >
							<span class = "span_1">今日${prodInfo.firstStr}</span>
						  </p>
					  </label>
					  <label class="col-sm-4 control-label lable_1"  id = "label_2" onclick="changebackground('2')">
						  <p class = "p_common"
						     <c:if test= "${prodInfo.afterEightFlag == 1}">  
						     		style = "background:rgba(255, 153, 0, 1);color:rgba(255, 255, 255, 255);" 
						     </c:if>
						  >
							<span  class = "span_1"  > ${prodInfo.secondStr} </span>
						  </p>
					  </label>
					  <label class="col-sm-4 control-label lable_1"  id = "label_3" onclick="changebackground('3')">
						  <p class = "p_common">
							<span  class = "span_2"> ${prodInfo.thirdStr} </span>
						  </p>
					  </label>
					  <label class="col-sm-4 control-label lable_1"  id = "label_4" onclick="changebackground('4')">
						  <p class = "p_common">
							<span  class = "span_2"> ${prodInfo.fourthStr} </span>
						  </p>
					  </label>
					  <label class="col-sm-4 control-label lable_1"   id = "label_5" onclick="changebackground('5')">
						  <p class = "p_common">
							<span  class = "span_2"> ${prodInfo.fifthStr} </span>
						  </p>
					  </label><!-- type="hidden" -->
					<!-- 隐藏数据，以便于区分选中的哪一天 -->
					  <input    type="hidden"  value="${prodInfo.myDateIndex} " id="myDateIndex" name="myDateIndex" /> 
					  <input    type="hidden"  value="" id="hourStr" name="hourStr" /> 
					</div>
					<div class="col-sm-10" id = "col-sm-10-extra" >
					 <!-- ------------------------------第一天数据------------------------------------- -->
					     <div  class="col-sm-10-sub-one"   id = "col_sm_10_sub_1"  
					        <c:if test= "${prodInfo.afterEightFlag == 1}">
					            style= "display: none;"
					        </c:if>
					     >
					          <div  class="col-sm-10-sub-two"  >
					                <div class = "div_two"   onclick ="checkFlag('${prodInfo.first.flag1}','10:00')">
					                     <span  class="div_two_span_1" 
						                     <c:if test="${prodInfo.first.flag1 == 1}">
							                    	 style = "background-color: rgba(204, 204, 204, 1);"
							                 </c:if>
					                     >10:00</span>  
					                     <span> ${prodInfo.first.count1}家</span>
					                </div>
					                <div  class = "div_two"  onclick ="checkFlag('${prodInfo.first.flag2}','14:00')">
					                     <span class="div_two_span_1"
					                      	  <c:if test="${prodInfo.first.flag2 == 1}">
							                    	 style = "background-color: rgba(204, 204, 204, 1);"
							                 </c:if>
					                     
					                     >14:00</span>  
					                     <span> ${prodInfo.first.count2}家</span>
					                </div>
					          </div>
					          <div   class="col-sm-10-sub-two">
					                <div   class = "div_two"  onclick ="checkFlag('${prodInfo.first.flag3}','16:00')">
					                     <span   class="div_two_span_1"
					                     	<c:if test="${prodInfo.first.flag3 == 1}">
							                    	 style = "background-color: rgba(204, 204, 204, 1);"
							                 </c:if>
					                     >16:00</span>  
					                     <span> ${prodInfo.first.count3}家</span>
					                </div>
					                <div   class = "div_two"   onclick ="checkFlag('${prodInfo.first.flag4}','20:00')">
					                     <span  class="div_two_span_1"
					                     	<c:if test="${prodInfo.first.flag4 == 1}">
							                    	 style = "background-color: rgba(204, 204, 204, 1);"
							                 </c:if>
					                     >20:00</span>  
					                     <span> ${prodInfo.first.count4}家</span>
					                </div>
					          </div>
					     </div>
					     
					     <!-- ------------------------------第二天数据------------------------------------- -->
					     <div  class="col-sm-10-sub-one"   id = "col_sm_10_sub_2" 
					     
					     <c:if test= "${prodInfo.afterEightFlag == 0}">
					    	 style= "display: none;"
					     </c:if>
					     
					     >
					          <div  class="col-sm-10-sub-two"  >
					                <div class = "div_two"       onclick ="checkFlag(0,'10:00')">
					                     <span  class="div_two_span_1" >10:00</span>  
					                     <span> ${prodInfo.second.count1}家</span>
					                </div>
					                <div  class = "div_two"      onclick ="checkFlag(0,'14:00')">
					                     <span class="div_two_span_1">14:00</span>  
					                     <span> ${prodInfo.second.count3}家</span>
					                </div>
					          </div>
					          <div   class="col-sm-10-sub-two"       >
					                <div   class = "div_two" onclick ="checkFlag(0,'16:00')">
					                     <span   class="div_two_span_1">16:00</span>  
					                     <span> ${prodInfo.second.count3}家</span>
					                </div>
					                <div   class = "div_two"     onclick ="checkFlag(0,'20:00')">
					                     <span  class="div_two_span_1">20:00</span>  
					                     <span> ${prodInfo.second.count4}家</span>
					                </div>
					          </div>
					     </div>
					     
					     
					     
					     <div  class="col-sm-10-sub-one"   id = "col_sm_10_sub_3" style= "display: none;"><!-- 第三天数据 -->
					          <div  class="col-sm-10-sub-two"  >
					                <div class = "div_two"    onclick ="checkFlag(0,'10:00')">
					                     <span  class="div_two_span_1" >10:00</span>  
					                     <span> ${prodInfo.third.count1}家</span>
					                </div>
					                <div  class = "div_two"    onclick ="checkFlag(0,'14:00')">
					                     <span class="div_two_span_1">14:00</span>  
					                     <span> ${prodInfo.third.count2}家</span>
					                </div>
					          </div>
					          <div   class="col-sm-10-sub-two" >
					                <div   class = "div_two"    onclick ="checkFlag(0,'16:00')">
					                     <span   class="div_two_span_1">16:00</span>  
					                     <span> ${prodInfo.third.count3}家</span>
					                </div>
					                <div   class = "div_two"    onclick ="checkFlag(0,'20:00')">
					                     <span  class="div_two_span_1">20:00</span>  
					                     <span> ${prodInfo.third.count4}家</span>
					                </div>
					          </div>
					     </div>
					     <div  class="col-sm-10-sub-one"   id = "col_sm_10_sub_4"  style= "display: none;"><!-- 第四天数据 -->
					          <div  class="col-sm-10-sub-two"  >
					                <div class = "div_two"    onclick ="checkFlag(0,'10:00')">
					                     <span  class="div_two_span_1">10:00</span>  
					                     <span>  ${prodInfo.fourth.count1}家</span>
					                </div>
					                <div  class = "div_two"    onclick ="checkFlag(0,'14:00')">
					                     <span class="div_two_span_1">14:00</span>  
					                     <span> ${prodInfo.fourth.count2}家</span>
					                </div>
					          </div>
					          <div   class="col-sm-10-sub-two">
					                <div   class = "div_two"    onclick ="checkFlag(0,'16:00')">
					                     <span   class="div_two_span_1">16:00</span>  
					                     <span> ${prodInfo.fourth.count3}家</span>
					                </div>
					                <div   class = "div_two"    onclick ="checkFlag(0,'20:00')">
					                     <span  class="div_two_span_1">20:00</span>  
					                     <span> ${prodInfo.fourth.count4}家</span>
					                </div>
					          </div>
					     </div>
					     <div  class="col-sm-10-sub-one"   id = "col_sm_10_sub_5"  style= "display: none;"><!-- 第五天数据 -->
					          <div  class="col-sm-10-sub-two"  >
					                <div class = "div_two"    onclick ="checkFlag(0,'10:00')">
					                     <span  class="div_two_span_1" >10:00</span>  
					                     <span> ${prodInfo.fifth.count1}家</span>
					                </div>
					                <div  class = "div_two"    onclick ="checkFlag(0,'14:00')">
					                     <span class="div_two_span_1">14:00</span>  
					                     <span>${prodInfo.fifth.count2}家</span>
					                </div>
					          </div>
					          <div   class="col-sm-10-sub-two">
					                <div   class = "div_two"    onclick ="checkFlag(0,'16:00')">
					                     <span   class="div_two_span_1">16:00</span>  
					                     <span> ${prodInfo.fifth.count3}家</span>
					                </div>
					                <div   class = "div_two"    onclick ="checkFlag(0,'20:00')">
					                     <span  class="div_two_span_1">20:00</span>  
					                     <span> ${prodInfo.fifth.count4}家</span>
					                </div>
					          </div>
					     </div>
					</div>
					
					<!-- 错误信息显示 -->
					<div class="col-sm-10" style="padding-right: 0;padding-left: 0;float: right;width:66%;">
					    <input    value="" id="errorInfo" name="errorInfo" style = "border:0;width:100%;color: red;font-size: 16px;"/> 
					</div>
					
				</div>
				
				<!-- 预约上线时间 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">预约上线时间</label>
					<div class="col-sm-8">
						<input type="text"  readonly="readonly" id="orderTime"  class="form-control" name="orderTimeStr"
							value="${orderTimeStr }">
							
					</div>
				</div>
				
				
				
				
				<span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">取消</button>
			<!-- <button class="btn btn-primary" data-dismiss="modal">保存</button> -->
		</div>
	</div>
</div>

<script type="text/javascript">
	if('${entity}'!=''){
		$("#product_category_id").val('${entity.productCategoryId}');
		$("#zoneId").val('${entity.zoneId}');
		$("#supplierId").val('${entity.supplierId}');
		/* $("#state_id").val('${entity.state}'); */
		$("#is_recommend").val('${entity.isRecommend}');
		$("#timeLimitType").val('${entity.timeLimitType}');
		$("#rateType").val('${entity.rateType}');
		if('${entity.productLabel}'!=''){
			var jsondata = '${entity.productLabel}';
			  var json=eval(jsondata);
			  $.each(json,function(i,n){
			       $("input:checkbox[value="+json[i].productLabelId+"]").attr('checked','true');
			  });
		}
		if('${entity.zoneId}'!=''){
			 var zones = '${entity.zoneId}';
			  var zones2=zones.split(",");
			  $.each(zones2,function(i,n){
			       $("input:checkbox[value="+zones2[i]+"]").attr('checked','true');
			  });
		}
		
		var  param = '${entity.state}'; 
		
		if(param == 1  ||  param == 2){
			$("#onlineInfo").css({display:"none"});
			$("#orderTime").val("");
		}else if(param == 3 ){
			$("#onlineInfo").css({display:"none"});
			$("#onlineInfo").css({display:"inline-block"});
		}
	} else{
		$("#onlineInfo").css({display:"none"});
	}
	
	
	
	
	
	
	
	function delsmallLaelImg(){
		$("#smallLabelImgsrc").attr("src","");
		$("#smallLabelImg").val("");
	}
	
	function showTimeInfo(param){
		
		if(param == 1  ||  param == 2){
			$("#onlineInfo").css({display:"none"});
			$("#orderTime").val("");
		}else if(param == 3 ){
			$("#onlineInfo").css({display:"none"});
			$("#onlineInfo").css({display:"inline-block"});
			
		}
		$("#myState").val("");
		$("#myState").val(param);
		/* $("#oldState").val(param); */
	}
	
	function  checkFlag(index,name){
		if(index == 1){
			$("#errorInfo").val("已经超出当前时间，不可选择！！");
			$("#hourStr").val(""); 
			$("#orderTime").val("");
			$("#tip").html("");
			/* $("#myDateIndex").val("");  */
			return ;
		}else{
			$("#errorInfo").val("");
			$("#hourStr").val(name);
			
			var now = new Date();
			var num = $("#myDateIndex").val();
			now.setDate(now.getDate()+(num -1));
			
			var month = now.getMonth() + 1;
            var day = now.getDate();
			var  ymd = now.getFullYear() + '-' + getFormatDate(month) + '-' + getFormatDate(day);
			var  hms  = " "+ name +":00";
			$("#orderTime").val(ymd + hms);
			$("#tip").html("");
			/* $("#myDateIndex").val(index);  */
			return ;
		}
	}
	
	
	
	function  changebackground(index){
		$("#errorInfo").val("");
		var id =  "#label_"+ index;
		$(".col-sm-10 .col-sm-4").find(".p_common").css({color:"#000",background:"#fff"});
		$(id).find(".p_common").css({color:"#fff",background:"rgba(255, 153, 0, 1)"}); 
		for (i = 1; i<= 5; i++) {
			if(i == index){
				continue;
			}
			var  showId = "#col_sm_10_sub_"+i;
			$(showId).css({display:"none"});
		}
		var  showId = "#col_sm_10_sub_"+index;
		$("#myDateIndex").val(index);
		$(showId).css({display:"inline-block"});
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
	
	
	function check_fun(){
		$("#tip").html("");
		var b = true;
		
		var timeLimitMint=$("#timeLimitMint").val();
		var timeLimitMax=$("#timeLimitMax").val();
		var productName = $("#product_name").val();
		var productCategoryId = $("#product_category_id").val();
		var state = $("#myState").val(); 
		var sort = $("#sort").val();
		var supplierId = $("#supplierId").val(); 
		var isRecommend = $("#is_recommend").val(); 
		var applyProcess = $("#applyProcess").val(); 
		var theRateOf=$("#theRateOf").val();
		var loanTime = $("#loanTime").val();
		if(productName == null || productName.trim() == ''){
			$("#tip").html("请输入产品名称");
			b = false;
		}else if(productCategoryId == null || productCategoryId.trim() == ''){
			$("#tip").html("请选择产品类型");
			b = false;
		}else if(sort == null || sort.trim() == ''){
			$("#tip").html("请输入权重值");
			b = false;
		}
		else if(state == null || state.trim() == ''){
			$("#tip").html("请选择产品状态");
			b = false; 
		}else if(isRecommend == null || isRecommend.trim() == ''){
			$("#tip").html("请选择是否推荐");
			b = false;
		}else if(supplierId == null || supplierId.trim() == ''){
			$("#tip").html("请选择供应商");
			b = false;
		}else if(applyProcess == null || applyProcess.trim() == ''){
			$("#tip").html("请填写申请流程");
			b = false; return b;
		}else if (theRateOf == null || theRateOf.trim() == ''){
			$("#tip").html("请填写下款率");
			b = false; return b;
		}else if (loanTime == null || loanTime.trim() == ''){
            $("#tip").html("请填写放款时间");
            b = false; return b;
        }
		else if (timeLimitMint == null || timeLimitMint.trim() == ''){
            $("#tip").html("请填写最小期限");
            b = false; return b;
        }
		else if (timeLimitMax == null || timeLimitMax.trim() == ''){
            $("#tip").html("请填写最大期限");
            b = false; return b;
        }
		
		
		if(state == 3){
			var  orderTime = $("#orderTime").val();
			if (orderTime == null || orderTime.trim() == ''){
				$("#tip").html("预约上线，请选择预约上线时间");
				b = false;
				return b;
			}
			
		}
		
		//判断申请流程个数>1  <5
		var applyProcessCount = applyProcess.split(",");
		
		if(applyProcessCount.length < 2){
			$("#tip").html("申请流程不得少于2个");
			b = false;
		}else if(applyProcessCount.length > 4){
			$("#tip").html("申请流程不得大于4个");
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