<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="theme-blue">	
	<div class="sidebar-nav">
		<ul>
			<c:forEach items="${menus }" var="menu" varStatus="status">
				<li>
					<c:choose>
						<c:when test="${empty menu.mList}">
							<a onclick="$('#content').load('${menu.url }')" class="nav-header" >
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0)" data-target=".accounts-menu-${status.index }" class="nav-header collapsed" data-toggle="collapse">
						</c:otherwise>
					</c:choose>
						<span class="glyphicon glyphicon-fw ${menu.img_icon }"></span> 
						<strong>${menu.names }</strong>
						<c:if test="${fn:length(menu.mList)>0 }">
							<span class="label label-info">${fn:length(menu.mList) }</span>
						</c:if>
					</a>
				</li>
				
				<li>
					<ul class="accounts-menu-${status.index } nav nav-list collapse">
						<c:forEach items="${menu.mList }" var="cMenu">
							<li><a onclick="$('#content').load('${cMenu.url }')"><span class="fa fa-caret-right"></span> ${cMenu.names }</a></li>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>
        </ul>
    </div>
</div>