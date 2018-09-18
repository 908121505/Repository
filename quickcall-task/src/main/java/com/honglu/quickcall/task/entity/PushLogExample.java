package com.honglu.quickcall.task.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PushLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PushLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andIsTimingIsNull() {
            addCriterion("is_timing is null");
            return (Criteria) this;
        }

        public Criteria andIsTimingIsNotNull() {
            addCriterion("is_timing is not null");
            return (Criteria) this;
        }

        public Criteria andIsTimingEqualTo(Integer value) {
            addCriterion("is_timing =", value, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingNotEqualTo(Integer value) {
            addCriterion("is_timing <>", value, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingGreaterThan(Integer value) {
            addCriterion("is_timing >", value, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_timing >=", value, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingLessThan(Integer value) {
            addCriterion("is_timing <", value, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingLessThanOrEqualTo(Integer value) {
            addCriterion("is_timing <=", value, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingIn(List<Integer> values) {
            addCriterion("is_timing in", values, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingNotIn(List<Integer> values) {
            addCriterion("is_timing not in", values, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingBetween(Integer value1, Integer value2) {
            addCriterion("is_timing between", value1, value2, "isTiming");
            return (Criteria) this;
        }

        public Criteria andIsTimingNotBetween(Integer value1, Integer value2) {
            addCriterion("is_timing not between", value1, value2, "isTiming");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNull() {
            addCriterion("push_time is null");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNotNull() {
            addCriterion("push_time is not null");
            return (Criteria) this;
        }

        public Criteria andPushTimeEqualTo(Date value) {
            addCriterion("push_time =", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotEqualTo(Date value) {
            addCriterion("push_time <>", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThan(Date value) {
            addCriterion("push_time >", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("push_time >=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThan(Date value) {
            addCriterion("push_time <", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThanOrEqualTo(Date value) {
            addCriterion("push_time <=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeIn(List<Date> values) {
            addCriterion("push_time in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotIn(List<Date> values) {
            addCriterion("push_time not in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeBetween(Date value1, Date value2) {
            addCriterion("push_time between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotBetween(Date value1, Date value2) {
            addCriterion("push_time not between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTotalNumIsNull() {
            addCriterion("push_total_num is null");
            return (Criteria) this;
        }

        public Criteria andPushTotalNumIsNotNull() {
            addCriterion("push_total_num is not null");
            return (Criteria) this;
        }

        public Criteria andPushTotalNumEqualTo(Integer value) {
            addCriterion("push_total_num =", value, "pushTotalNum");
            return (Criteria) this;
        }

        public Criteria andPushTotalNumNotEqualTo(Integer value) {
            addCriterion("push_total_num <>", value, "pushTotalNum");
            return (Criteria) this;
        }

        public Criteria andPushTotalNumGreaterThan(Integer value) {
            addCriterion("push_total_num >", value, "pushTotalNum");
            return (Criteria) this;
        }

        public Criteria andPushTotalNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("push_total_num >=", value, "pushTotalNum");
            return (Criteria) this;
        }

        public Criteria andPushTotalNumLessThan(Integer value) {
            addCriterion("push_total_num <", value, "pushTotalNum");
            return (Criteria) this;
        }

        public Criteria andPushTotalNumLessThanOrEqualTo(Integer value) {
            addCriterion("push_total_num <=", value, "pushTotalNum");
            return (Criteria) this;
        }

        public Criteria andPushTotalNumIn(List<Integer> values) {
            addCriterion("push_total_num in", values, "pushTotalNum");
            return (Criteria) this;
        }

        public Criteria andPushTotalNumNotIn(List<Integer> values) {
            addCriterion("push_total_num not in", values, "pushTotalNum");
            return (Criteria) this;
        }

        public Criteria andPushTotalNumBetween(Integer value1, Integer value2) {
            addCriterion("push_total_num between", value1, value2, "pushTotalNum");
            return (Criteria) this;
        }

        public Criteria andPushTotalNumNotBetween(Integer value1, Integer value2) {
            addCriterion("push_total_num not between", value1, value2, "pushTotalNum");
            return (Criteria) this;
        }

        public Criteria andPushSuccessNumIsNull() {
            addCriterion("push_success_num is null");
            return (Criteria) this;
        }

        public Criteria andPushSuccessNumIsNotNull() {
            addCriterion("push_success_num is not null");
            return (Criteria) this;
        }

        public Criteria andPushSuccessNumEqualTo(Integer value) {
            addCriterion("push_success_num =", value, "pushSuccessNum");
            return (Criteria) this;
        }

        public Criteria andPushSuccessNumNotEqualTo(Integer value) {
            addCriterion("push_success_num <>", value, "pushSuccessNum");
            return (Criteria) this;
        }

        public Criteria andPushSuccessNumGreaterThan(Integer value) {
            addCriterion("push_success_num >", value, "pushSuccessNum");
            return (Criteria) this;
        }

        public Criteria andPushSuccessNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("push_success_num >=", value, "pushSuccessNum");
            return (Criteria) this;
        }

        public Criteria andPushSuccessNumLessThan(Integer value) {
            addCriterion("push_success_num <", value, "pushSuccessNum");
            return (Criteria) this;
        }

        public Criteria andPushSuccessNumLessThanOrEqualTo(Integer value) {
            addCriterion("push_success_num <=", value, "pushSuccessNum");
            return (Criteria) this;
        }

        public Criteria andPushSuccessNumIn(List<Integer> values) {
            addCriterion("push_success_num in", values, "pushSuccessNum");
            return (Criteria) this;
        }

        public Criteria andPushSuccessNumNotIn(List<Integer> values) {
            addCriterion("push_success_num not in", values, "pushSuccessNum");
            return (Criteria) this;
        }

        public Criteria andPushSuccessNumBetween(Integer value1, Integer value2) {
            addCriterion("push_success_num between", value1, value2, "pushSuccessNum");
            return (Criteria) this;
        }

        public Criteria andPushSuccessNumNotBetween(Integer value1, Integer value2) {
            addCriterion("push_success_num not between", value1, value2, "pushSuccessNum");
            return (Criteria) this;
        }

        public Criteria andIsReadNumIsNull() {
            addCriterion("is_read_num is null");
            return (Criteria) this;
        }

        public Criteria andIsReadNumIsNotNull() {
            addCriterion("is_read_num is not null");
            return (Criteria) this;
        }

        public Criteria andIsReadNumEqualTo(Integer value) {
            addCriterion("is_read_num =", value, "isReadNum");
            return (Criteria) this;
        }

        public Criteria andIsReadNumNotEqualTo(Integer value) {
            addCriterion("is_read_num <>", value, "isReadNum");
            return (Criteria) this;
        }

        public Criteria andIsReadNumGreaterThan(Integer value) {
            addCriterion("is_read_num >", value, "isReadNum");
            return (Criteria) this;
        }

        public Criteria andIsReadNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_read_num >=", value, "isReadNum");
            return (Criteria) this;
        }

        public Criteria andIsReadNumLessThan(Integer value) {
            addCriterion("is_read_num <", value, "isReadNum");
            return (Criteria) this;
        }

        public Criteria andIsReadNumLessThanOrEqualTo(Integer value) {
            addCriterion("is_read_num <=", value, "isReadNum");
            return (Criteria) this;
        }

        public Criteria andIsReadNumIn(List<Integer> values) {
            addCriterion("is_read_num in", values, "isReadNum");
            return (Criteria) this;
        }

        public Criteria andIsReadNumNotIn(List<Integer> values) {
            addCriterion("is_read_num not in", values, "isReadNum");
            return (Criteria) this;
        }

        public Criteria andIsReadNumBetween(Integer value1, Integer value2) {
            addCriterion("is_read_num between", value1, value2, "isReadNum");
            return (Criteria) this;
        }

        public Criteria andIsReadNumNotBetween(Integer value1, Integer value2) {
            addCriterion("is_read_num not between", value1, value2, "isReadNum");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIsNull() {
            addCriterion("link_url is null");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIsNotNull() {
            addCriterion("link_url is not null");
            return (Criteria) this;
        }

        public Criteria andLinkUrlEqualTo(String value) {
            addCriterion("link_url =", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotEqualTo(String value) {
            addCriterion("link_url <>", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlGreaterThan(String value) {
            addCriterion("link_url >", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlGreaterThanOrEqualTo(String value) {
            addCriterion("link_url >=", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLessThan(String value) {
            addCriterion("link_url <", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLessThanOrEqualTo(String value) {
            addCriterion("link_url <=", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLike(String value) {
            addCriterion("link_url like", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotLike(String value) {
            addCriterion("link_url not like", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIn(List<String> values) {
            addCriterion("link_url in", values, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotIn(List<String> values) {
            addCriterion("link_url not in", values, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlBetween(String value1, String value2) {
            addCriterion("link_url between", value1, value2, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotBetween(String value1, String value2) {
            addCriterion("link_url not between", value1, value2, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateManIsNull() {
            addCriterion("create_man is null");
            return (Criteria) this;
        }

        public Criteria andCreateManIsNotNull() {
            addCriterion("create_man is not null");
            return (Criteria) this;
        }

        public Criteria andCreateManEqualTo(String value) {
            addCriterion("create_man =", value, "createMan");
            return (Criteria) this;
        }

        public Criteria andCreateManNotEqualTo(String value) {
            addCriterion("create_man <>", value, "createMan");
            return (Criteria) this;
        }

        public Criteria andCreateManGreaterThan(String value) {
            addCriterion("create_man >", value, "createMan");
            return (Criteria) this;
        }

        public Criteria andCreateManGreaterThanOrEqualTo(String value) {
            addCriterion("create_man >=", value, "createMan");
            return (Criteria) this;
        }

        public Criteria andCreateManLessThan(String value) {
            addCriterion("create_man <", value, "createMan");
            return (Criteria) this;
        }

        public Criteria andCreateManLessThanOrEqualTo(String value) {
            addCriterion("create_man <=", value, "createMan");
            return (Criteria) this;
        }

        public Criteria andCreateManLike(String value) {
            addCriterion("create_man like", value, "createMan");
            return (Criteria) this;
        }

        public Criteria andCreateManNotLike(String value) {
            addCriterion("create_man not like", value, "createMan");
            return (Criteria) this;
        }

        public Criteria andCreateManIn(List<String> values) {
            addCriterion("create_man in", values, "createMan");
            return (Criteria) this;
        }

        public Criteria andCreateManNotIn(List<String> values) {
            addCriterion("create_man not in", values, "createMan");
            return (Criteria) this;
        }

        public Criteria andCreateManBetween(String value1, String value2) {
            addCriterion("create_man between", value1, value2, "createMan");
            return (Criteria) this;
        }

        public Criteria andCreateManNotBetween(String value1, String value2) {
            addCriterion("create_man not between", value1, value2, "createMan");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyManIsNull() {
            addCriterion("modify_man is null");
            return (Criteria) this;
        }

        public Criteria andModifyManIsNotNull() {
            addCriterion("modify_man is not null");
            return (Criteria) this;
        }

        public Criteria andModifyManEqualTo(String value) {
            addCriterion("modify_man =", value, "modifyMan");
            return (Criteria) this;
        }

        public Criteria andModifyManNotEqualTo(String value) {
            addCriterion("modify_man <>", value, "modifyMan");
            return (Criteria) this;
        }

        public Criteria andModifyManGreaterThan(String value) {
            addCriterion("modify_man >", value, "modifyMan");
            return (Criteria) this;
        }

        public Criteria andModifyManGreaterThanOrEqualTo(String value) {
            addCriterion("modify_man >=", value, "modifyMan");
            return (Criteria) this;
        }

        public Criteria andModifyManLessThan(String value) {
            addCriterion("modify_man <", value, "modifyMan");
            return (Criteria) this;
        }

        public Criteria andModifyManLessThanOrEqualTo(String value) {
            addCriterion("modify_man <=", value, "modifyMan");
            return (Criteria) this;
        }

        public Criteria andModifyManLike(String value) {
            addCriterion("modify_man like", value, "modifyMan");
            return (Criteria) this;
        }

        public Criteria andModifyManNotLike(String value) {
            addCriterion("modify_man not like", value, "modifyMan");
            return (Criteria) this;
        }

        public Criteria andModifyManIn(List<String> values) {
            addCriterion("modify_man in", values, "modifyMan");
            return (Criteria) this;
        }

        public Criteria andModifyManNotIn(List<String> values) {
            addCriterion("modify_man not in", values, "modifyMan");
            return (Criteria) this;
        }

        public Criteria andModifyManBetween(String value1, String value2) {
            addCriterion("modify_man between", value1, value2, "modifyMan");
            return (Criteria) this;
        }

        public Criteria andModifyManNotBetween(String value1, String value2) {
            addCriterion("modify_man not between", value1, value2, "modifyMan");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}