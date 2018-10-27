package com.calf.cn.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SpringContextHolder;
import com.calf.module.system.entity.SysUser;

public class CalfShiro extends AuthorizingRealm {
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		BaseManager baseManager = SpringContextHolder.getBean("baseManager");
		
		String account = (String)super.getAvailablePrincipal(arg0);
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("account", account);
		List<String> roleList = baseManager.query("SysUser.queryRoleCodeByAccount", parameters);
		
		Map<String,Object> parameters2 = new HashMap<String,Object>();
		parameters2.put("roles", roleList);
 		List<String> authorList = baseManager.query("Home.queryMenuAuthor",parameters2);
		
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		if(roleList != null && roleList.size() > 0) 
			simpleAuthorInfo.addRoles(roleList);
		if(authorList != null && authorList.size() > 0)
			simpleAuthorInfo.addStringPermissions(authorList);
		
		return simpleAuthorInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken token = (UsernamePasswordToken)arg0;
		BaseManager baseManager = SpringContextHolder.getBean("baseManager");
		SysUser user = baseManager.get("SysUser.getUserByAccount", new Object[]{token.getUsername()});
		if(user!=null){
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), getName());
			return authcInfo;
		}
		return null;
	}
	
	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		// TODO Auto-generated method stub
		if (permission.contains(OR_OPERATOR)) {
			String[] permissions = permission.split(OR_OPERATOR);
			for (String orPermission : permissions) {
				if (isPermittedWithNotOperator(principals, orPermission)) {
					return true;
				}
			}
			return false;
		} else if (permission.contains(AND_OPERATOR)) {
			String[] permissions = permission.split(AND_OPERATOR);
			for (String orPermission : permissions) {
				if (!isPermittedWithNotOperator(principals, orPermission)) {
					return false;
				}
			}
			return true;
		} else {
			return isPermittedWithNotOperator(principals, permission);
		}
	}

	private boolean isPermittedWithNotOperator(PrincipalCollection principals,
			String permission) {
		if (permission.startsWith(NOT_OPERATOR)) {
			return !super.isPermitted(principals,
					permission.substring(NOT_OPERATOR.length()));
		} else {
			return super.isPermitted(principals, permission);
		}
	}

	private static final String OR_OPERATOR = " or ";
	private static final String AND_OPERATOR = " and ";
	private static final String NOT_OPERATOR = "not ";
}