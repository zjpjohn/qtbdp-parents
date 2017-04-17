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
ALTER TABLE data_product ADD data_type_props VARCHAR(1000) ;

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
