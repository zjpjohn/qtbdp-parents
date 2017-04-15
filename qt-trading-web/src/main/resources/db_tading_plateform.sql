/*&&&&&&&&&&&&&&&&&&&&&&&&&新建表和字段&&&&&&&&&&&&&&&&&&&&&&&&&&&*/

/*==============================================================*/
/* Table: data_type_attr      数据类型属性                       */
/*==============================================================*/
drop table if exists data_type_attr;

create table data_type_attr
(
   id                   int                            not null,
   data_type_id         int                            null,
   attr_name            varchar(50)                    null,
   is_must              tinyint(4)                     null default '0',
   is_multi             tinyint(4)                     null default '0',
   sort_order           tinyint(4)                     null default '0',
   constraint PK_DATA_TYPE_ATTR primary key clustered (id)
);

/*==============================================================*/
/* Table: data_type_attr_val      数据类型属性值                  */
/*==============================================================*/
drop table if exists data_type_attr_val;

create table data_type_attr_val
(
   id                   int                            not null,
   attr_id              int                            null,
   attr_name            varchar(50)                    null,
   name                 varchar(50)                    null,
   sort_order           tinyint(4)                     null default '0',
   constraint PK_DATA_TYPE_ATTR_VAL primary key clustered (id)
);

/*==============================================================*/
/* Table: data_product   产品表新增字段data_type_props            */
/*==============================================================*/
ALTER TABLE data_product ADD data_type_props VARCHAR(1000) ;