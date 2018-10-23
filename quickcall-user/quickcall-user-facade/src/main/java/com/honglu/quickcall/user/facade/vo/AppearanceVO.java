package com.honglu.quickcall.user.facade.vo;

/**
 * Description: 用户 头像，形象照，声鉴卡
 *
 * @author chenpeng
 * @date 2018/10/23 20:04
 */
public class AppearanceVO {
    /**主键id(15位时间+4位随机数)**/
    private Long id;

    /**客户ID**/
    private Long customerId;

    /**待审核照片**/
    private String auditAppearance;

    /**照片（正在使用的）**/
    private String appearance;

    /**0=形象照,1=头像照,2=声鉴卡**/
    private Integer type;

    /**审核状态 0=待审核,1=已通过,2=已拒绝**/
    private Integer auditStatus;


    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setCustomerId(Long customerId){
        this.customerId = customerId;
    }

    public Long getCustomerId(){
        return this.customerId;
    }

    public void setAuditAppearance(String auditAppearance){
        this.auditAppearance = auditAppearance;
    }

    public String getAuditAppearance(){
        return this.auditAppearance;
    }

    public void setAppearance(String appearance){
        this.appearance = appearance;
    }

    public String getAppearance(){
        return this.appearance;
    }

    public void setType(Integer type){
        this.type = type;
    }

    public Integer getType(){
        return this.type;
    }

    public void setAuditStatus(Integer auditStatus){
        this.auditStatus = auditStatus;
    }

    public Integer getAuditStatus(){
        return this.auditStatus;
    }


}
