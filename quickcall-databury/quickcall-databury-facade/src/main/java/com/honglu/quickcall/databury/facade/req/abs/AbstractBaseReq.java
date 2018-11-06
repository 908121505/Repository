package com.honglu.quickcall.databury.facade.req.abs;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author xiangping
 * @date 2018-10-31 10:49
 */
public class AbstractBaseReq implements Serializable {
    /**虚拟id**/
    @NotBlank
    private String virUserId;

    public String getVirUserId() {
        return virUserId;
    }

    public void setVirUserId(String virUserId) {
        this.virUserId = virUserId;
    }

    @Override
    public String toString() {
        return "AbstractBaseReq{" +
                "virUserId='" + virUserId + '\'' +
                '}';
    }
}
