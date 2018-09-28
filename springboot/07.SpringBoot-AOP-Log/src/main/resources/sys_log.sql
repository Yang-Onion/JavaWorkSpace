CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户',
  `operation` varchar(100) DEFAULT NULL COMMENT '操作',
  `time` int(11) DEFAULT NULL COMMENT '耗时',
  `method` varchar(100) DEFAULT NULL COMMENT '方法',
  `params` varchar(100) DEFAULT NULL COMMENT '参数',
  `ip` varchar(100) DEFAULT NULL COMMENT 'ip',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
