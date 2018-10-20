package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Description: 修改形象照
 *
 * @author chenpeng
 * @date 2018/10/18 17:19
 */
public class RemoveAppearanceReq extends UserCenterRequest {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RemoveAppearanceReq{" +
                "id=" + id +
                '}';
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.removeAppearance;
    }
}
