CREATE DATABASE lxlmanage DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `admin_user` (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `admin_user_phone` char(11) NOT NULL COMMENT '手机号',
  `admin_user_name` varchar(16) NOT NULL DEFAULT '' COMMENT '姓名',
  `admin_user_email` varchar(64) NOT NULL DEFAULT '' COMMENT '邮箱',
  `admin_user_fullname` varchar(48) NOT NULL DEFAULT '' COMMENT '企业微信授权登录用户的名称',
  `admin_user_avatar` varchar(128) NOT NULL DEFAULT '' COMMENT '企业微信授权登录用户的头像',
  `admin_user_position` varchar(32) NOT NULL DEFAULT '' COMMENT '企业微信授权登录用户的职位',
  `admin_user_auth_key` varchar(64) NOT NULL DEFAULT '',
  `admin_user_password_hash` varchar(64) NOT NULL DEFAULT '',
  `admin_user_password_reset_token` varchar(64) NOT NULL DEFAULT '',
  `admin_user_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 0-正常 1-停用',
  `admin_user_create_at` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `admin_user_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `admin_user_create_by` varchar(16) NOT NULL DEFAULT '' COMMENT '创建人',
  `admin_user_update_by` varchar(16) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`admin_user_id`),
  UNIQUE KEY `admin_user_name_UNIQUE` (`admin_user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理后台用户表';

INSERT INTO admin_user(admin_user_phone, admin_user_name, admin_user_email, admin_user_password_hash)
VALUES ('15301452513', '超级管理员', 'lxl@admin.com', '123456');

CREATE TABLE `admin_role_user` (
  `admin_role_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_role_user_admin_role_id` int(11) NOT NULL COMMENT '角色id',
  `admin_role_user_admin_user_id` int(11) NOT NULL COMMENT '用户id',
  `admin_role_use_create_at` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `admin_role_use_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `admin_role_use_create_by` varchar(16) NOT NULL DEFAULT '',
  `admin_role_use_update_by` varchar(16) NOT NULL DEFAULT '',
  PRIMARY KEY (`admin_role_user_id`),
  KEY `admin_role_user_idx1_idx` (`admin_role_user_admin_role_id`),
  KEY `admin_role_user_idx2_idx` (`admin_role_user_admin_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色用户关联表';

INSERT INTO `admin_role_user` VALUES (1,1,1,'2017-07-19 17:07:09','1000-01-01 00:00:00','','');

CREATE TABLE `admin_resource` (
  `admin_resource_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `admin_resource_parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '资源父id',
  `admin_resource_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '资源类型 0-菜单 1-功能',
  `admin_resource_target` varchar(64) NOT NULL DEFAULT '' COMMENT '语义化索引',
  `admin_resource_data` varchar(1024) NOT NULL DEFAULT '' COMMENT '资源data，json格式',
  `admin_resource_create_at` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `admin_resource_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `admin_resource_create_by` varchar(16) NOT NULL DEFAULT '' COMMENT '创建人',
  `admin_resource_update_by` varchar(16) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`admin_resource_id`),
  KEY `admin_resource_idx1` (`admin_resource_parent_id`),
  KEY `admin_resource_idx2` (`admin_resource_target`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源表';

INSERT INTO `admin_resource`(admin_resource_id, admin_resource_parent_id, admin_resource_parent_id)
VALUES 
    (1,0,'{"title":"系统管理","path":"/system","icon":"","seq":50,"type":"layout"}'),
    (2,1,'{"title":"菜单管理","path":"system/menu","icon":"","seq":9,"type":"default"}'),
    (3,1,'{"title":"用户管理","path":"system/user","icon":"","seq":8,"type":"default"}'),
    (4,1,'{"title":"角色管理","path":"system/role","icon":"","seq":7,"type":"default"}');

CREATE TABLE `admin_role` (
  `admin_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `admin_role_parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父角色id',
  `admin_role_name` varchar(16) NOT NULL DEFAULT '' COMMENT '角色名称',
  `admin_role_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '角色描述',
  `admin_role_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 0-正常 1-停用',
  `admin_role_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '类型 0-成员角色member 1-群组角色group',
  `admin_role_create_at` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `admin_role_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `admin_role_create_by` varchar(16) NOT NULL DEFAULT '' COMMENT '创建人',
  `admin_role_update_by` varchar(16) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`admin_role_id`),
  UNIQUE KEY `admin_role_name_UNIQUE` (`admin_role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后台角色表';

INSERT INTO `admin_role` VALUES (1,0,'超级管理员','拥有所有权限',0,1,'2017-07-10 14:31:45','2017-07-18 10:39:24','','');

CREATE TABLE `admin_privilege` (
  `admin_privilege_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `admin_privilege_admin_resource_id` int(11) NOT NULL DEFAULT '0' COMMENT '资源id',
  `admin_privilege_admin_role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色id',
  `admin_privilege_type` varchar(16) NOT NULL DEFAULT '' COMMENT '权限类型 all-全部 access-访问 manage-管理',
  `admin_privilege_create_at` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `admin_privilege_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `admin_privilege_create_by` varchar(16) NOT NULL DEFAULT '' COMMENT '创建人',
  `admin_privilege_update_by` varchar(16) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`admin_privilege_id`),
  KEY `admin_privilege_resouce_idx` (`admin_privilege_admin_resource_id`),
  KEY `admin_privilege_role_idx` (`admin_privilege_admin_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

CREATE TABLE `house` (
  `house_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '楼栋id',
  `house_code` varchar(32) NOT NULL DEFAULT '' COMMENT '楼栋编号',
  `house_name` varchar(100) NOT NULL DEFAULT '' COMMENT '楼栋名称',
  `house_stages` int(11) NOT NULL DEFAULT '0' COMMENT '楼栋分期',
  `house_description` varchar(512) NOT NULL DEFAULT '' COMMENT '楼栋说明',
  `house_create_at` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `house_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`house_id`),
  KEY `house_idx1` (`house_code`, `house_name`),
  KEY `house_idx2` (`house_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='楼栋管理表';

CREATE TABLE `room` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房间ID',
  `room_house_id` int(11) NOT NULL DEFAULT '0' COMMENT '房间楼栋ID',
  `room_code` varchar(32) NOT NULL DEFAULT '' COMMENT '房间编号',
  `room_name` varchar(32) NOT NULL DEFAULT '' COMMENT '房间名称',
  `room_unit` tinyint(3) NOT NULL DEFAULT '0' COMMENT '房间所属单元',
  `room_floor` tinyint(3) NOT NULL DEFAULT '0' COMMENT '房间楼层',
  `room_description` varchar(512) NOT NULL DEFAULT '' COMMENT '房间说明',
  `room_create_at` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `room_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`room_id`),
  KEY `room_idx1` (`room_code`),
  KEY `room_idx2` (`room_house_id`, `room_unit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='房间管理表';

CREATE TABLE `house_hold` (
  `house_hold_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '住户ID',
  `house_hold_room_id` int(11) NOT NULL DEFAULT '0' COMMENT '住户房间ID',
  `house_hold_name` varchar(32) NOT NULL DEFAULT '' COMMENT '住户姓名',
  `house_hold_phone` varchar(20) NOT NULL DEFAULT '' COMMENT '住户联系方式',
  `house_hold_sex` enum('f', 'm') NOT NULL DEFAULT 'f' COMMENT '住户性别 f-女, m-男',
  `house_hold_individual` varchar(18) NOT NULL DEFAULT '' COMMENT '住户身份证号',
  `house_hold_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '住户类型 0-业主，1-租客',
  `house_hold_description` varchar(512) NOT NULL DEFAULT '' COMMENT '住户说明',
  `house_hold_create_at` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `house_hold_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`house_hold_id`),
  KEY `house_hold_idx1` (`house_hold_name`),
  KEY `house_hold_idx2` (`house_hold_individual`),
  KEY `house_hold_idx3` (`house_hold_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='住户管理表';

CREATE TABLE `advert` (
  `advert_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `advert_department` varchar(32) NOT NULL DEFAULT '' COMMENT '公告发布单位',
  `advert_title` varchar(32) NOT NULL DEFAULT '' COMMENT '公告标题',
  `advert_pic` varchar(100) NOT NULL DEFAULT '' COMMENT '公告图片',
  `advert_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '公告类型 0-默认公告信息, 1-首页公告',
  `advert_sort` tinyint(2) NOT NULL DEFAULT '0' COMMENT '公告排序',
  `advert_is_top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '公告置顶 1-置顶',
  `advert_content` varchar(512) NOT NULL DEFAULT '' COMMENT '公告内容',
  `advert_create_at` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `advert_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`advert_id`),
  KEY `advert_idx1` (`advert_title`),
  KEY `advert_idx2` (`advert_department`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公告管理表';

CREATE TABLE `fast_mail` (
  `fast_mail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '快递ID',
  `fast_mail_order_no` varchar(32) NOT NULL DEFAULT '' COMMENT '快递单号',
  `fast_mail_custom_no` varchar(32) NOT NULL DEFAULT '' COMMENT '快递自定义单号',
  `fast_mail_business` varchar(100) NOT NULL DEFAULT '' COMMENT '快递商',
  `fast_mail_receive_room_id` int(11) NOT NULL DEFAULT '0' COMMENT '快递收件人房间ID',
  `fast_mail_receive_name` varchar(100) NOT NULL DEFAULT '' COMMENT '快递收件人姓名',
  `fast_mail_receive_phone` varchar(20) NOT NULL DEFAULT '' COMMENT '快递收件人联系方式',
  `fast_mail_receive_address` varchar(512) NOT NULL DEFAULT '' COMMENT '快递收件人详细地址',
  `fast_mail_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '快递状态 0-未收，1-已收',
  `fast_mail_memo` varchar(512) NOT NULL DEFAULT '' COMMENT '其他备注',
  `fast_mail_create_at` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `fast_mail_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`fast_mail_id`),
  KEY `fast_mail_idx1` (`fast_mail_receive_name`),
  KEY `fast_mail_idx2` (`fast_mail_order_no`),
  KEY `fast_mail_idx3` (`fast_mail_custom_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='快递管理表';

CREATE TABLE `repair_order` (
  `repair_order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报修ID',
  `repair_order_code` varchar(32) NOT NULL DEFAULT '' COMMENT '报修单号',
  `repair_order_room_id` int(11) NOT NULL DEFAULT '0' COMMENT '报修房间ID',
  `repair_order_repair_range_id` int(11) NOT NULL DEFAULT '0' COMMENT '报修范围ID',
  `repair_order_phone` varchar(20) NOT NULL DEFAULT '' COMMENT '报修联系方式',
  `repair_order_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '报修状态 0-未处理，1-已修复，2-其他问题',
  `repair_order_admin_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '报修处理人',
  `repair_order_description` varchar(512) NOT NULL DEFAULT '' COMMENT '报修详细描述',
  `repair_order_create_at` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `repair_order_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`repair_order_id`),
  KEY `repair_order_idx1` (`repair_order_code`),
  KEY `repair_order_idx2` (`repair_order_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='报修管理表';

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '支付ID',
  `payment_serial_no` varchar(32) NOT NULL DEFAULT '' COMMENT '支付流水号',
  `payment_card_no` varchar(20) NOT NULL DEFAULT '0' COMMENT '支付卡号',
  `payment_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '支付类型 0-未知，1-银行卡，2-支付宝，3-微信',
  `payment_order_id` int(11) NOT NULL DEFAULT '' COMMENT '支付订单ID',
  `payment_create_at` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `payment_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`payment_id`),
  KEY `repair_order_idx1` (`payment_serial_no`),
  KEY `repair_order_idx2` (`payment_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付流水管理表';

CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '支付订单ID',
  `order_code` varchar(32) NOT NULL DEFAULT '' COMMENT '支付订单单号',
  `order_house_hold_id` int(11) NOT NULL DEFAULT '0' COMMENT '支付订单住户',
  `order_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '支付类型 1-水电费，2-物业费，3-其他费用',
  `order_amount` decimal(10,2) NOT NULL DEFAULT '0' COMMENT '支付总金额',
  `order_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '支付状态 0-待支付，1-支付中，2-支付成功，3-支付失败',
  `order_create_at` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `order_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_id`),
  KEY `order_idx1` (`order_code`),
  KEY `order_idx2` (`order_house_hold_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付订单管理表';

CREATE TABLE `order_detail` (
  `order_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '支付详情ID',
  `order_detail_order_id` varchar(32) NOT NULL DEFAULT '' COMMENT '支付详情支付单号',
  `order_detail_terms` int(11) NOT NULL DEFAULT '0' COMMENT '支付项',
  `order_detail_amount` decimal(10,2) NOT NULL DEFAULT '0' COMMENT '支付金额',
  `order_detail_create_at` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `order_detail_update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_detail_id`),
  KEY `order_detail_idx1` (`order_detail_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付订单详情表';