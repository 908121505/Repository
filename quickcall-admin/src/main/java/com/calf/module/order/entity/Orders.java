package com.calf.module.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Orders {
    /**
     * 订单号(15位时间+4位随机数)
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private Long orderNo;

    /**
     * 用户技能ID
     */
    private Long customerSkillId;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 服务方ID
     */
    private Long serviceId;

    /**
     * 交易号
     */
    private Long tradeId;

    /**
     * 支付类型
     */
    private Byte paymentType;

    /**
     * 数量
     */
    private Integer orderNum;

    /**
     * 服务单位（小时/半小时/次）
     */
    private String serviceUnit;

    /**
     * 服务单价
     */
    private BigDecimal servicePrice;

    /**
     * 订单金额
     */
    private BigDecimal orderAmounts;

    /**
     * 下单时折扣
     */
    private BigDecimal discountRate;

    /**
     * 用户自主取消时间
     */
    private Date custCancelTime;

    /**
     * 系统自动取消时间
     */
    private Date systemCancelTime;

    /**
     * 主播接单时间
     */
    private Date receiveOrderTime;

    /**
     * 主播发起服务时间
     */
    private Date startServiceTime;

    /**
     * 大V发起完成服务时间
     */
    private Date appayEndTime;

    /**
     * 订单开始时间(进入进行中的那一刻)
     */
    private Date startTime;

    /**
     * 订单预计结束时间
     */
    private Date expectEndTime;

    /**
     * d订单结束时间
     */
    private Date endTime;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 技能类型 1：不可重复接单  2：可重复接单
     */
    private Byte skillType;

    /**
     * 2.待接单;4.取消（用户下单后自主取消）6.取消（待接单-大V超时未接）15分钟8.已拒绝（大V拒绝接单）10.待开始（大V接单）;12.取消（大V接单，大V同一时间其它订单取消）;14.取消（大V接单后用户自主取消）;16.取消（待开始5分钟大V未发起开始服务）18.待开始(大V发起开始服务)20.取消（大V发起开始服务用户自主取消）;22.取消（大V发起开始服务用户5分钟未接）24.待进行（大V发起开始服务用户5分钟内同意，叫醒特享）26.进行中（大V发起开始服务用户5分钟内同意）;28.进行中（大V发起完成服务，在服务时间内）30.已完成（用户同意对方）32.已完成（大V发起已完成服务，12小时客户不响应自动完成）34.已完成（用户发起完成服务）36.已完成（大V在服务时间外完成）38.已完成（双方12小时内未发起完成服务）40.已完成（用户评价完成）
     */
    private Byte orderStatus;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 买家是否评价(0=未评价,1=评价)
     */
    private Byte customerIsEvaluate;

    /**
     * 客户留言
     */
    private String customerMessage;

    /**
     * 评价星级
     */
    private Integer evaluateStart;

    /**
     * 客户评价
     */
    private String customerEvaluate;

    /**
     * 订单描述
     */
    private String orderDescribe;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单修改时间
     */
    private Date modifyTime;

    /**
     * 创建人
     */
    private String createMan;

    /**
     * 修改人
     */
    private String modifyMan;

    /**
     * 选中原因
     */
    private String selectReason;

    /**
     * 取消原因
     */
    private String remarkReason;

    /**
     * 备注
     */
    private String remark;

    /**
     * 订单号(15位时间+4位随机数)
     * @return order_id 订单号(15位时间+4位随机数)
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 订单号(15位时间+4位随机数)
     * @param orderId 订单号(15位时间+4位随机数)
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 订单编号
     * @return order_no 订单编号
     */
    public Long getOrderNo() {
        return orderNo;
    }

    /**
     * 订单编号
     * @param orderNo 订单编号
     */
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 用户技能ID
     * @return customer_skill_id 用户技能ID
     */
    public Long getCustomerSkillId() {
        return customerSkillId;
    }

    /**
     * 用户技能ID
     * @param customerSkillId 用户技能ID
     */
    public void setCustomerSkillId(Long customerSkillId) {
        this.customerSkillId = customerSkillId;
    }

    /**
     * 客户ID
     * @return customer_id 客户ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 客户ID
     * @param customerId 客户ID
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 服务方ID
     * @return service_id 服务方ID
     */
    public Long getServiceId() {
        return serviceId;
    }

    /**
     * 服务方ID
     * @param serviceId 服务方ID
     */
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * 交易号
     * @return trade_id 交易号
     */
    public Long getTradeId() {
        return tradeId;
    }

    /**
     * 交易号
     * @param tradeId 交易号
     */
    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 支付类型
     * @return payment_type 支付类型
     */
    public Byte getPaymentType() {
        return paymentType;
    }

    /**
     * 支付类型
     * @param paymentType 支付类型
     */
    public void setPaymentType(Byte paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 数量
     * @return order_num 数量
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 数量
     * @param orderNum 数量
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 服务单位（小时/半小时/次）
     * @return service_unit 服务单位（小时/半小时/次）
     */
    public String getServiceUnit() {
        return serviceUnit;
    }

    /**
     * 服务单位（小时/半小时/次）
     * @param serviceUnit 服务单位（小时/半小时/次）
     */
    public void setServiceUnit(String serviceUnit) {
        this.serviceUnit = serviceUnit == null ? null : serviceUnit.trim();
    }

    /**
     * 服务单价
     * @return service_price 服务单价
     */
    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    /**
     * 服务单价
     * @param servicePrice 服务单价
     */
    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    /**
     * 订单金额
     * @return order_amounts 订单金额
     */
    public BigDecimal getOrderAmounts() {
        return orderAmounts;
    }

    /**
     * 订单金额
     * @param orderAmounts 订单金额
     */
    public void setOrderAmounts(BigDecimal orderAmounts) {
        this.orderAmounts = orderAmounts;
    }

    /**
     * 下单时折扣
     * @return discount_rate 下单时折扣
     */
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    /**
     * 下单时折扣
     * @param discountRate 下单时折扣
     */
    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * 用户自主取消时间
     * @return cust_cancel_time 用户自主取消时间
     */
    public Date getCustCancelTime() {
        return custCancelTime;
    }

    /**
     * 用户自主取消时间
     * @param custCancelTime 用户自主取消时间
     */
    public void setCustCancelTime(Date custCancelTime) {
        this.custCancelTime = custCancelTime;
    }

    /**
     * 系统自动取消时间
     * @return system_cancel_time 系统自动取消时间
     */
    public Date getSystemCancelTime() {
        return systemCancelTime;
    }

    /**
     * 系统自动取消时间
     * @param systemCancelTime 系统自动取消时间
     */
    public void setSystemCancelTime(Date systemCancelTime) {
        this.systemCancelTime = systemCancelTime;
    }

    /**
     * 主播接单时间
     * @return receive_order_time 主播接单时间
     */
    public Date getReceiveOrderTime() {
        return receiveOrderTime;
    }

    /**
     * 主播接单时间
     * @param receiveOrderTime 主播接单时间
     */
    public void setReceiveOrderTime(Date receiveOrderTime) {
        this.receiveOrderTime = receiveOrderTime;
    }

    /**
     * 主播发起服务时间
     * @return start_service_time 主播发起服务时间
     */
    public Date getStartServiceTime() {
        return startServiceTime;
    }

    /**
     * 主播发起服务时间
     * @param startServiceTime 主播发起服务时间
     */
    public void setStartServiceTime(Date startServiceTime) {
        this.startServiceTime = startServiceTime;
    }

    /**
     * 大V发起完成服务时间
     * @return appay_end_time 大V发起完成服务时间
     */
    public Date getAppayEndTime() {
        return appayEndTime;
    }

    /**
     * 大V发起完成服务时间
     * @param appayEndTime 大V发起完成服务时间
     */
    public void setAppayEndTime(Date appayEndTime) {
        this.appayEndTime = appayEndTime;
    }

    /**
     * 订单开始时间(进入进行中的那一刻)
     * @return start_time 订单开始时间(进入进行中的那一刻)
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 订单开始时间(进入进行中的那一刻)
     * @param startTime 订单开始时间(进入进行中的那一刻)
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 订单预计结束时间
     * @return expect_end_time 订单预计结束时间
     */
    public Date getExpectEndTime() {
        return expectEndTime;
    }

    /**
     * 订单预计结束时间
     * @param expectEndTime 订单预计结束时间
     */
    public void setExpectEndTime(Date expectEndTime) {
        this.expectEndTime = expectEndTime;
    }

    /**
     * d订单结束时间
     * @return end_time d订单结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * d订单结束时间
     * @param endTime d订单结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 下单时间
     * @return order_time 下单时间
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * 下单时间
     * @param orderTime 下单时间
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 技能类型 1：不可重复接单  2：可重复接单
     * @return skill_type 技能类型 1：不可重复接单  2：可重复接单
     */
    public Byte getSkillType() {
        return skillType;
    }

    /**
     * 技能类型 1：不可重复接单  2：可重复接单
     * @param skillType 技能类型 1：不可重复接单  2：可重复接单
     */
    public void setSkillType(Byte skillType) {
        this.skillType = skillType;
    }

    /**
     * 2.待接单;4.取消（用户下单后自主取消）6.取消（待接单-大V超时未接）15分钟8.已拒绝（大V拒绝接单）10.待开始（大V接单）;12.取消（大V接单，大V同一时间其它订单取消）;14.取消（大V接单后用户自主取消）;16.取消（待开始5分钟大V未发起开始服务）18.待开始(大V发起开始服务)20.取消（大V发起开始服务用户自主取消）;22.取消（大V发起开始服务用户5分钟未接）24.待进行（大V发起开始服务用户5分钟内同意，叫醒特享）26.进行中（大V发起开始服务用户5分钟内同意）;28.进行中（大V发起完成服务，在服务时间内）30.已完成（用户同意对方）32.已完成（大V发起已完成服务，12小时客户不响应自动完成）34.已完成（用户发起完成服务）36.已完成（大V在服务时间外完成）38.已完成（双方12小时内未发起完成服务）40.已完成（用户评价完成）
     * @return order_status 2.待接单;4.取消（用户下单后自主取消）6.取消（待接单-大V超时未接）15分钟8.已拒绝（大V拒绝接单）10.待开始（大V接单）;12.取消（大V接单，大V同一时间其它订单取消）;14.取消（大V接单后用户自主取消）;16.取消（待开始5分钟大V未发起开始服务）18.待开始(大V发起开始服务)20.取消（大V发起开始服务用户自主取消）;22.取消（大V发起开始服务用户5分钟未接）24.待进行（大V发起开始服务用户5分钟内同意，叫醒特享）26.进行中（大V发起开始服务用户5分钟内同意）;28.进行中（大V发起完成服务，在服务时间内）30.已完成（用户同意对方）32.已完成（大V发起已完成服务，12小时客户不响应自动完成）34.已完成（用户发起完成服务）36.已完成（大V在服务时间外完成）38.已完成（双方12小时内未发起完成服务）40.已完成（用户评价完成）
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }

    /**
     * 2.待接单;4.取消（用户下单后自主取消）6.取消（待接单-大V超时未接）15分钟8.已拒绝（大V拒绝接单）10.待开始（大V接单）;12.取消（大V接单，大V同一时间其它订单取消）;14.取消（大V接单后用户自主取消）;16.取消（待开始5分钟大V未发起开始服务）18.待开始(大V发起开始服务)20.取消（大V发起开始服务用户自主取消）;22.取消（大V发起开始服务用户5分钟未接）24.待进行（大V发起开始服务用户5分钟内同意，叫醒特享）26.进行中（大V发起开始服务用户5分钟内同意）;28.进行中（大V发起完成服务，在服务时间内）30.已完成（用户同意对方）32.已完成（大V发起已完成服务，12小时客户不响应自动完成）34.已完成（用户发起完成服务）36.已完成（大V在服务时间外完成）38.已完成（双方12小时内未发起完成服务）40.已完成（用户评价完成）
     * @param orderStatus 2.待接单;4.取消（用户下单后自主取消）6.取消（待接单-大V超时未接）15分钟8.已拒绝（大V拒绝接单）10.待开始（大V接单）;12.取消（大V接单，大V同一时间其它订单取消）;14.取消（大V接单后用户自主取消）;16.取消（待开始5分钟大V未发起开始服务）18.待开始(大V发起开始服务)20.取消（大V发起开始服务用户自主取消）;22.取消（大V发起开始服务用户5分钟未接）24.待进行（大V发起开始服务用户5分钟内同意，叫醒特享）26.进行中（大V发起开始服务用户5分钟内同意）;28.进行中（大V发起完成服务，在服务时间内）30.已完成（用户同意对方）32.已完成（大V发起已完成服务，12小时客户不响应自动完成）34.已完成（用户发起完成服务）36.已完成（大V在服务时间外完成）38.已完成（双方12小时内未发起完成服务）40.已完成（用户评价完成）
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 支付时间
     * @return payment_time 支付时间
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * 支付时间
     * @param paymentTime 支付时间
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 买家是否评价(0=未评价,1=评价)
     * @return customer_is_evaluate 买家是否评价(0=未评价,1=评价)
     */
    public Byte getCustomerIsEvaluate() {
        return customerIsEvaluate;
    }

    /**
     * 买家是否评价(0=未评价,1=评价)
     * @param customerIsEvaluate 买家是否评价(0=未评价,1=评价)
     */
    public void setCustomerIsEvaluate(Byte customerIsEvaluate) {
        this.customerIsEvaluate = customerIsEvaluate;
    }

    /**
     * 客户留言
     * @return customer_message 客户留言
     */
    public String getCustomerMessage() {
        return customerMessage;
    }

    /**
     * 客户留言
     * @param customerMessage 客户留言
     */
    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage == null ? null : customerMessage.trim();
    }

    /**
     * 评价星级
     * @return evaluate_start 评价星级
     */
    public Integer getEvaluateStart() {
        return evaluateStart;
    }

    /**
     * 评价星级
     * @param evaluateStart 评价星级
     */
    public void setEvaluateStart(Integer evaluateStart) {
        this.evaluateStart = evaluateStart;
    }

    /**
     * 客户评价
     * @return customer_evaluate 客户评价
     */
    public String getCustomerEvaluate() {
        return customerEvaluate;
    }

    /**
     * 客户评价
     * @param customerEvaluate 客户评价
     */
    public void setCustomerEvaluate(String customerEvaluate) {
        this.customerEvaluate = customerEvaluate == null ? null : customerEvaluate.trim();
    }

    /**
     * 订单描述
     * @return order_describe 订单描述
     */
    public String getOrderDescribe() {
        return orderDescribe;
    }

    /**
     * 订单描述
     * @param orderDescribe 订单描述
     */
    public void setOrderDescribe(String orderDescribe) {
        this.orderDescribe = orderDescribe == null ? null : orderDescribe.trim();
    }

    /**
     * 订单创建时间
     * @return create_time 订单创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 订单创建时间
     * @param createTime 订单创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 订单修改时间
     * @return modify_time 订单修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 订单修改时间
     * @param modifyTime 订单修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 创建人
     * @return create_man 创建人
     */
    public String getCreateMan() {
        return createMan;
    }

    /**
     * 创建人
     * @param createMan 创建人
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    /**
     * 修改人
     * @return modify_man 修改人
     */
    public String getModifyMan() {
        return modifyMan;
    }

    /**
     * 修改人
     * @param modifyMan 修改人
     */
    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    /**
     * 选中原因
     * @return select_reason 选中原因
     */
    public String getSelectReason() {
        return selectReason;
    }

    /**
     * 选中原因
     * @param selectReason 选中原因
     */
    public void setSelectReason(String selectReason) {
        this.selectReason = selectReason == null ? null : selectReason.trim();
    }

    /**
     * 取消原因
     * @return remark_reason 取消原因
     */
    public String getRemarkReason() {
        return remarkReason;
    }

    /**
     * 取消原因
     * @param remarkReason 取消原因
     */
    public void setRemarkReason(String remarkReason) {
        this.remarkReason = remarkReason == null ? null : remarkReason.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}