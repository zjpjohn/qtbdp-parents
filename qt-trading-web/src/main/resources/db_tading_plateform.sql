/*&&&&&&&&&&&&&&&&&&&&&&&&&新建表和字段&&&&&&&&&&&&&&&&&&&&&&&&&&&*/

/*==============================================================*/
/* Table: data_type_attr      数据类型属性                       */
/*==============================================================*/
drop table if exists data_type_attr;

create table data_type_attr
(
   id                   int                            not null AUTO_INCREMENT,
   data_type_id         int                            null,
   attr_name            varchar(50)                    null,
   is_must              tinyint(4)                     null default '0',
   is_multi             tinyint(4)                     null default '0',
   sort_order           tinyint(4)                     null default '0',
   is_used              tinyint(4)                     not null default '1',
   constraint PK_DATA_TYPE_ATTR primary key clustered (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ;

/*==============================================================*/
/* Table: data_type_attr_val      数据类型属性值                  */
/*==============================================================*/
drop table if exists data_type_attr_val;

create table data_type_attr_val
(
   id                   int                            not null AUTO_INCREMENT,
   attr_id              int                            null,
   attr_name            varchar(50)                    null,
   name                 varchar(50)                    null,
   sort_order           tinyint(4)                     null default '0',
   is_used              tinyint(4)                     not null default '1',
   constraint PK_DATA_TYPE_ATTR_VAL primary key clustered (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ;


/*==============================================================*/
/* Table: data_product_attr_relation  数据包产品属性关联表        */
/*==============================================================*/
drop table if exists data_product_attr_relation;

create table data_product_attr_relation
(
   id                   int                            not null AUTO_INCREMENT,
   product_id           int                            not null,
   type_id              int                            null,
   attr_id              int                            not null,
   val_id               int                            not null,
   attr_name            varchar(50)                    not null,
   val_name             varchar(50)                    not null,
   constraint PK_DATA_PRODUCT_PROP primary key clustered (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ;


/*==============================================================*/
/* Table: data_product   产品表新增字段data_type_props            */
/*==============================================================*/
ALTER TABLE data_product ADD data_type_props VARCHAR(1000),ADD download_count INT,ADD buy_count INT  ;

/*==============================================================*/
/* Table: data_user_info   用户表新增系统用户ID、昵称              */
/*==============================================================*/
ALTER TABLE data_user_info ADD nick VARCHAR(50),ADD sso_user_id VARCHAR(100) ;

/*==============================================================*/
/* Table: data_institution_type_relation  修改字段不为空          */
/*==============================================================*/
ALTER TABLE data_institution_type_relation MODIFY institution_id INT NOT NULL, MODIFY data_type INT NOT NULL;

/*==============================================================*/
/* Table: data_institution_info  新增province、city              */
/*==============================================================*/
ALTER TABLE data_institution_info ADD `province` VARCHAR(50) NULL DEFAULT NULL COMMENT '省',ADD `city` VARCHAR(50) NULL DEFAULT NULL COMMENT '市' ;

/*==============================================================*/
/*                         建索引                                */
/*==============================================================*/
ALTER TABLE `data_product_attr_relation` ADD INDEX idx_val_id_and_product_id ( `val_id`, `product_id` ) ;

/*==============================================================*/
/*                         基础数据                              */
/*==============================================================*/
INSERT INTO `data_type_attr` VALUES (1, 0, '计价方式', 0, 0, 0, 1);
INSERT INTO `data_type_attr` VALUES (2, 0, '数据格式', 0, 0, 1, 1);
INSERT INTO `data_type_attr` VALUES (3, 0, '数据来源', 0, 0, 2, 1);
INSERT INTO `data_type_attr` VALUES (4, 0, '数据大小', 0, 0, 3, 1);

INSERT INTO `data_type_attr_val` VALUES (1, 1, '计价方式', '免费', 0, 1);
INSERT INTO `data_type_attr_val` VALUES (2, 1, '计价方式', '收费', 1, 1);
INSERT INTO `data_type_attr_val` VALUES (3, 2, '数据格式', '文件', 0, 1);
INSERT INTO `data_type_attr_val` VALUES (4, 2, '数据格式', 'API', 1, 1);
INSERT INTO `data_type_attr_val` VALUES (5, 3, '数据来源', '政府', 0, 1);
INSERT INTO `data_type_attr_val` VALUES (6, 3, '数据来源', '机构', 1, 1);
INSERT INTO `data_type_attr_val` VALUES (7, 3, '数据来源', '合作商', 2, 1);
INSERT INTO `data_type_attr_val` VALUES (8, 3, '数据来源', '个人', 3, 1);
INSERT INTO `data_type_attr_val` VALUES (9, 3, '数据来源', '其它', 4, 1);
INSERT INTO `data_type_attr_val` VALUES (10, 4, '数据大小', '100M以下', 0, 1);
INSERT INTO `data_type_attr_val` VALUES (11, 4, '数据大小', '100M-500M', 1, 1);
INSERT INTO `data_type_attr_val` VALUES (12, 4, '数据大小', '100M-500M', 2, 1);
INSERT INTO `data_type_attr_val` VALUES (13, 4, '数据大小', '500M-1G', 3, 1);
INSERT INTO `data_type_attr_val` VALUES (14, 4, '数据大小', '1G以上', 4, 1);


/* 新增字段 2017-5-12 v1.1.0 */

/*==============================================================*/
/* Table: data_type  新增is_parent                               */
/*==============================================================*/
ALTER TABLE data_type ADD is_parent TINYINT NOT NULL DEFAULT 0 COMMENT '是否父节点, 0是，1否';

/* 新增字段 2017-5-22 v1.1.0 */

/*==============================================================*/
/* Table: data_product  新增price,item_price                    */
/*==============================================================*/
ALTER TABLE data_product ADD price DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '数据包价格', ADD item_price DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '数据包单个条目价格';

ALTER TABLE data_product MODIFY user_id VARCHAR(100) ;

ALTER TABLE data_product ADD data_size INT COMMENT '数据文件大小' ;

/*==============================================================*/
/* Table: data_product  新增price,item_price                    */
/*==============================================================*/
ALTER TABLE data_item ADD price DECIMAL(10,2) COMMENT '价格',ADD file_url VARCHAR(1000) COMMENT '数据包路径' ;

/* 调整类目基础数据 2017-5-25 v1.1.0 */
INSERT INTO `data_type` (`id`, `pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (28, 0, '产权专利', 1, 0, null, 1);
INSERT INTO `data_type` (`id`, `pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (29, 0, '行业综合', 1, 1, null, 1);
INSERT INTO `data_type` (`id`, `pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (30, 0, '工业生产', 1, 2, null, 1);
INSERT INTO `data_type` (`id`, `pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (31, 0, '工业产品', 1, 3, null, 1);
INSERT INTO `data_type` (`id`, `pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (32, 0, '智能制造', 1, 4, null, 1);
INSERT INTO `data_type` (`id`, `pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (33, 0, '物联网', 1, 5, null, 1);
INSERT INTO `data_type` (`id`, `pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (34, 0, '交通物流', 1, 6, null, 1);
INSERT INTO `data_type` (`id`, `pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (35, 0, '科技金融', 1, 7, null, 1);
INSERT INTO `data_type` (`id`, `pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (36, 0, '综合服务', 1, 8, null, 1);
INSERT INTO `data_type` (`id`, `pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (37, 0, '其他', 1, 9, null, 0);

INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (28, '知识产权', 1, 10, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (28, '研发专利', 1, 11, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (28, '科研数据', 1, 12, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (28, '其他', 1, 10, null, 0);

INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (29, '工业', 1, 20, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (29, '农业', 1, 21, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (29, '服务业', 1, 22, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (29, '互联网', 1, 23, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (29, '电子商务', 1, 24, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (29, '其他', 1, 25, null, 0);

INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (30, '采购供应', 1, 30, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (30, '研发设计', 1, 31, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (30, '生产制造', 1, 32, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (30, '检验测试', 1, 33, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (30, '其他', 1, 34, null, 0);

INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (31, '企业数据', 1, 40, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (31, '产品数据', 1, 41, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (31, '客户数据', 1, 42, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (31, '市场数据', 1, 43, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (31, '供销数据', 1, 44, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (31, '其他', 1, 45, null, 0);

INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (32, '先进设备', 1, 50, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (32, '工业机器人', 1, 51, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (32, '工业传感', 1, 52, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (32, '3D打印', 1, 52, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (32, '其他', 1, 52, null, 0);

INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (33, '智能家居', 1, 60, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (33, '智慧交通', 1, 61, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (33, '智能楼宇', 1, 62, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (33, '其他', 1, 63, null, 0);

INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (34, '交通运输', 1, 70, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (34, '物流数据', 1, 71, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (34, '其他', 1, 72, null, 0);

INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (35, '产业金融', 1, 80, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (35, '投资理财', 1, 81, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (35, '融资市场', 1, 82, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (35, '未来科技', 1, 83, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (35, '其他', 1, 84, null, 0);

INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (36, '生活服务', 1, 90, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (36, '社交媒体', 1, 91, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (36, '位置信息', 1, 92, null, 0);
INSERT INTO `data_type` (`pid`, `name`, `is_used`, `sort`, `remark`, `is_parent`) VALUES (36, '其他', 1, 93, null, 0);

/* 调整类目属性 */
INSERT INTO `data_type_attr` VALUES (4, 0, '收费方式', 0, 0, 0, 1);
INSERT INTO `data_type_attr` VALUES (5, 0, '产品类型', 0, 0, 1, 1);
INSERT INTO `data_type_attr` VALUES (6, 0, '数据来源', 0, 0, 2, 1);

/* 调整类目属性值 */
INSERT INTO `data_type_attr_val` VALUES (100, 4, '收费方式', '免费', 0, 1);
INSERT INTO `data_type_attr_val` VALUES (101, 4, '收费方式', '收费', 1, 1);

INSERT INTO `data_type_attr_val` VALUES (200, 5, '产品类型', '数据包', 0, 1);
INSERT INTO `data_type_attr_val` VALUES (201, 5, '产品类型', 'API接口', 1, 1);
INSERT INTO `data_type_attr_val` VALUES (202, 5, '产品类型', '数据报告', 2, 1);

INSERT INTO `data_type_attr_val` VALUES (300, 6, '数据来源', '政府', 0, 1);
INSERT INTO `data_type_attr_val` VALUES (301, 6, '数据来源', '企业', 1, 1);
INSERT INTO `data_type_attr_val` VALUES (302, 6, '数据来源', '组织机构', 2, 1);
INSERT INTO `data_type_attr_val` VALUES (303, 6, '数据来源', '互联网', 3, 1);
INSERT INTO `data_type_attr_val` VALUES (304, 6, '数据来源', '个人', 4, 1);

/* 2017-06-01 v1.1.0 */
ALTER TABLE data_product ADD COLUMN data_type_path  VARCHAR(200) COMMENT '数据类目路径';
ALTER TABLE data_user_info MODIFY COLUMN sso_user_id  VARCHAR(100) COMMENT '系统用户ID';


/* 2017-06-01 v1.2.0 */
ALTER TABLE data_product ADD data_profile VARCHAR(1000) COMMENT '产品简介', ADD market_price DECIMAL(10,2) COMMENT '市场价' ;
