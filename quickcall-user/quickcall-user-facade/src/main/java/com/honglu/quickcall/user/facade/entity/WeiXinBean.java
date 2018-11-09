package com.honglu.quickcall.user.facade.entity;

/**
 * @author xiangping
 * @date 2018-11-09 10:16
 */
public class WeiXinBean {
    private String open_id;
    private Long customer_id;
    private String nickname;

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "WeiXinBean{" +
                "open_id='" + open_id + '\'' +
                ", customer_id=" + customer_id +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
