CREATE TABLE `dp_daily_input` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_stay_nday_rate` decimal(15,4) DEFAULT NULL COMMENT '用户次日留存率',
  `shandai_to_guanjia_rate` decimal(15,4) DEFAULT NULL COMMENT '闪贷转化率',
  `newproduct_num_yday_per` decimal(15,4) DEFAULT NULL COMMENT '昨日人均注册商家数',
  `income_yday_per` decimal(15,4) DEFAULT NULL COMMENT '昨日人均收入',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `stats_time` datetime DEFAULT NULL COMMENT '统计时间',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `stats_time_UNIQUE` (`stats_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='大屏录入数据：次日留存率、转化率、昨日人均注册商家数等';
