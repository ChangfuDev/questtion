package cn.edu.dlnu.question.realm;

import cn.edu.dlnu.question.entity.Permission;
import cn.edu.dlnu.question.entity.Role;
import cn.edu.dlnu.question.entity.User;
import cn.edu.dlnu.question.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

  @Autowired
  private UserService userService;

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
// 获取登录用户名
    String name = (String) principalCollection.getPrimaryPrincipal();
//查询用户名称
    User user = userService.findByName(name);
//添加角色和权限
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    for (Role role : user.getRoles()) {
//添加角色
      simpleAuthorizationInfo.addRole(role.getName());
      for (Permission permission : role.getPermissions()) {
//添加权限
        simpleAuthorizationInfo.addStringPermission(permission.getName());
      }
    }
    return simpleAuthorizationInfo;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
//加这一步的目的是在Post请求的时候会先进认证，然后在到请求
    if (authenticationToken.getPrincipal() == null) {
      return null;
    }
//获取用户信息
    String name = authenticationToken.getPrincipal().toString();
    User user = userService.findByName(name);
    if (user == null) {
//这里返回后会报出对应异常
      return null;
    } else {
//这里验证authenticationToken和simpleAuthenticationInfo的信息
      SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name,
          user.getPassword().toString(), getName());
      return simpleAuthenticationInfo;
    }
  }
}