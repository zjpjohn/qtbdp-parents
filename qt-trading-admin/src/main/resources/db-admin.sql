/*==============================================================*/
/* Table: SYS_USERS      [系统管理]用户                           */
/*==============================================================*/
alter table sys_users_roles drop FK_SYS_USER_REFERENCE_SYS_USER;

drop table if exists sys_users;

create table sys_users  (
   user_id              int                 not null AUTO_INCREMENT COMMENT '用户ID',
   username             varchar(100)        not null COMMENT '用户名',
   name                 varchar(100)        COMMENT '用户姓名',
   password             varchar(100)        not null COMMENT '密码',
   dt_create            timestamp   DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
   last_login           timestamp   COMMENT '最后登录日期',
   deadline             timestamp   COMMENT '截止日期',
   login_ip             varchar(100)  COMMENT '最后登录IP地址',
   v_qzjgid             varchar(100)  COMMENT '所属机构ID',
   v_qzjgmc             varchar(100)  COMMENT '所属机构名称',
   dep_id               varchar(100)  COMMENT '地区编号',
   dep_name             varchar(100)  COMMENT '地区名称',
   enabled              tinyint(4)  COMMENT '是否可用，0不可用 1可用',
   account_non_expired  tinyint(4)  COMMENT '用户是否过期，0过期 1未过期',
   account_non_locked   tinyint(4)  COMMENT '用户是否锁定，0锁定 1未锁定',
   credentials_non_expired tinyint(4)  COMMENT '用户证书是否有效，0无效 1有效',

   PRIMARY KEY (`user_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 default charset=utf8 ;

/*==============================================================*/
/* Table: SYS_ROLES       [系统管理]角色                          */
/*==============================================================*/
alter table sys_roles_authorities drop FK_SYS_ROLE_REFERENCE_SYS_ROLE;

alter table sys_roles_moudles drop FK_S_ROLE_REFERENCE_SYS_ROLE;

alter table sys_users_roles drop FK_SYS_USER_REFERENCE_SYS_ROLE;

drop table if exists sys_roles;

create table sys_roles  (
   role_id              int                   not null AUTO_INCREMENT COMMENT '角色ID',
   role_name            varchar(100)  COMMENT '角色名称',
   role_desc            varchar(200)  COMMENT '角色说明',
   enable               tinyint(4)  COMMENT '是否可用，0不可用 1可用',
   issys                tinyint(4)  COMMENT '是否系统权限，0否 1是',
   module_id            int   COMMENT '模块',
   constraint PK_SYS_ROLES primary key (role_id)
) ENGINE=INNODB AUTO_INCREMENT=1 default charset=utf8 ;


/*==============================================================*/
/* Table: SYS_USERS_ROLES      [系统管理]用户角色表               */
/*==============================================================*/

alter table sys_users_roles drop FK_SYS_USER_REFERENCE_SYS_USER;

alter table sys_users_roles drop FK_SYS_USER_REFERENCE_SYS_ROLE;

drop table if exists sys_users_roles;

create table sys_users_roles  (
   id                   int          not null AUTO_INCREMENT COMMENT 'ID',
   role_id              int          not null COMMENT '角色ID',
   user_id              int          not null COMMENT '用户ID',
   constraint PK_SYS_USERS_ROLES primary key (id)
) ENGINE=INNODB AUTO_INCREMENT=1 default charset=utf8 ;

alter table sys_users_roles
   add constraint FK_SYS_USER_REFERENCE_SYS_USER foreign key (user_id)
      references sys_users (user_id);

alter table sys_users_roles
   add constraint FK_SYS_USER_REFERENCE_SYS_ROLE foreign key (role_id)
      references sys_roles (role_id);


/*==============================================================*/
/* Table: SYS_AUTHORITIES        [系统管理]权限                   */
/*==============================================================*/
alter table sys_authorities_resources drop FK_SYS_AUTH_REFERENCE_SYS_AUTH;

alter table sys_roles_authorities drop FK_SYS_ROLE_REFERENCE_SYS_AUTH;

drop table if exists sys_authorities;

create table sys_authorities  (
   authority_id         int                   not null AUTO_INCREMENT COMMENT '权限Id',
   authority_mark       varchar(100)  COMMENT '权限标识',
   authority_name       varchar(100)          not null COMMENT '权限名称',
   authority_desc       varchar(200)  COMMENT '权限说明',
   message              varchar(100)  COMMENT '提示信息',
   enable               tinyint(4)  COMMENT '是否可用，0不可用 1可用',
   issys                tinyint(4)  COMMENT '是否系统权限 0否 1是',
   module_id            int COMMENT '模块',
   constraint PK_SYS_AUTHORITIES primary key (authority_id)
) ENGINE=INNODB AUTO_INCREMENT=1 default charset=utf8 ;


/*==============================================================*/
/* Table: SYS_ROLES_AUTHORITIES       [系统管理]角色权限表        */
/*==============================================================*/
alter table sys_roles_authorities drop FK_SYS_ROLE_REFERENCE_SYS_ROLE;

alter table sys_roles_authorities drop FK_SYS_ROLE_REFERENCE_SYS_AUTH;

drop table if exists sys_roles_authorities;

create table sys_roles_authorities  (
   id                   int                   not null  AUTO_INCREMENT COMMENT 'id',
   authority_id         int                   not null  COMMENT '权限Id',
   role_id              int                   not null  COMMENT '角色ID',
   constraint PK_SYS_ROLES_AUTHORITIES primary key (id)
) ENGINE=INNODB AUTO_INCREMENT=1 default charset=utf8 ;

alter table sys_roles_authorities
   add constraint FK_SYS_ROLE_REFERENCE_SYS_ROLE foreign key (role_id)
      references sys_roles (role_id);

alter table sys_roles_authorities
   add constraint FK_SYS_ROLE_REFERENCE_SYS_AUTH foreign key (authority_id)
      references sys_authorities (authority_id);


/*==============================================================*/
/* Table: SYS_RESOURCES        [系统管理]资源                     */
/*==============================================================*/

alter table sys_authorities_resources drop FK_SYS_AUTH_REFERENCE_SYS_RESO;

alter table sys_resources drop FK_SYS_RESO_REFERENCE_SYS_MODU;

drop table if exists sys_resources;

create table sys_resources  (
   resource_id          int                   not null AUTO_INCREMENT COMMENT '资源ID',
   resource_type        char(6)               not null COMMENT '资源类型：URL 链接，METHOD 方法',
   resource_name        varchar(100)          not null COMMENT '资源名称',
   resource_desc        varchar(200)          COMMENT '资源描述',
   resource_path        varchar(200)          COMMENT '资源路径',
   priority             tinyint(4)            COMMENT '优先级',
   enable               tinyint(4)            COMMENT '是否可用，0不可用 1可用',
   issys                tinyint(4)            COMMENT '是否系统权限，0 否 1是',
   module_id            int                   COMMENT '模块',
   constraint PK_SYS_RESOURCES primary key (resource_id)
) ENGINE=INNODB AUTO_INCREMENT=1 default charset=utf8 ;

alter table sys_resources
   add constraint FK_SYS_RESO_REFERENCE_SYS_MODU foreign key (module_id)
      references sys_modules (module_id);

/*==============================================================*/
/* Table: SYS_AUTHORITIES_RESOURCES     [系统管理]权限资源表      */
/*==============================================================*/

alter table sys_authorities_resources drop FK_SYS_AUTH_REFERENCE_SYS_AUTH;

alter table sys_authorities_resources drop FK_SYS_AUTH_REFERENCE_SYS_RESO;

drop table if exists sys_authorities_resources;

create table sys_authorities_resources  (
   id                   int                   not null  AUTO_INCREMENT COMMENT 'ID',
   resource_id          int                   not null  COMMENT '资源ID',
   authority_id         int                   not null  COMMENT '权限Id',
   constraint PK_SYS_AUTHORITIES_RESOURCES primary key (id)
) ENGINE=INNODB AUTO_INCREMENT=1 default charset=utf8 ;

alter table sys_authorities_resources
   add constraint FK_SYS_AUTH_REFERENCE_SYS_AUTH foreign key (authority_id)
      references sys_authorities (authority_id);

alter table sys_authorities_resources
   add constraint FK_SYS_AUTH_REFERENCE_SYS_RESO foreign key (resource_id)
      references sys_resources (resource_id);


/*==============================================================*/
/* Table: SYS_MODULES          [系统管理]模块                     */
/*==============================================================*/

alter table sys_resources drop FK_SYS_RESO_REFERENCE_SYS_MODU;

alter table sys_roles_moudles drop FK_SYS_ROLE_REFERENCE_SYS_MODU;

drop table if exists sys_modules;

create table sys_modules  (
   module_id            int                   not null  AUTO_INCREMENT COMMENT '模块ID',
   module_name          varchar(100)          not null  COMMENT '模块名称',
   module_desc          varchar(200)          COMMENT '模块说明',
   module_type          tinyint(4)            COMMENT '模块类型',
   parent               int           COMMENT '模块上级',
   module_url           varchar(100)  COMMENT '模块地址',
   i_level              tinyint(4)    COMMENT '菜单级别，默认1',
   leaf                 tinyint(4)    COMMENT '最下级',
   application          varchar(100)  COMMENT '应用名称',
   controller           varchar(100)  COMMENT '控制器名称',
   enable               tinyint(4)    COMMENT '是否可用',
   priority             tinyint(4)    COMMENT '优先级',
   constraint PK_SYS_MODULES primary key (module_id)
) ENGINE=INNODB AUTO_INCREMENT=1 default charset=utf8 ;

/*==============================================================*/
/* Table: SYS_ROLES_MOUDLES 控制角色对模块的访问权，主要用于生成菜单 */
/*==============================================================*/

alter table sys_roles_moudles drop FK_SYS_ROLE_REFERENCE_SYS_MODU;

alter table sys_roles_moudles drop FK_S_ROLE_REFERENCE_SYS_ROLE;

drop table if exists sys_roles_moudles;

create table sys_roles_moudles  (
   id                   int                   not null  AUTO_INCREMENT COMMENT 'ID',
   module_id            int                   not null  COMMENT '模块ID',
   role_id              int                   not null  COMMENT '角色ID',
   constraint PK_SYS_ROLES_MOUDLES primary key (id)
) ENGINE=INNODB AUTO_INCREMENT=1 default charset=utf8 ;

alter table sys_roles_moudles
   add constraint FK_SYS_ROLE_REFERENCE_SYS_MODU foreign key (module_id)
      references sys_modules (module_id);

alter table sys_roles_moudles
   add constraint FK_S_ROLE_REFERENCE_SYS_ROLE foreign key (role_id)
      references sys_roles (role_id);

/*==============================================================*/
/* Table: PERSISTENT_LOGINS    Spring Remember me 持久化         */
/*==============================================================*/
drop table if exists persistent_logins;

create table persistent_logins  (
   username             varchar(64),
   series               varchar(64)                    not null,
   token                varchar(64),
   last_used            timestamp,
   constraint PK_PERSISTENT_LOGINS primary key (series)
) ENGINE=INNODB AUTO_INCREMENT=1 default charset=utf8 ;


/* 初始化数据 */
INSERT INTO `sys_users` (`user_id`, `v_qzjgid`, `v_qzjgmc`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `deadline`, `dep_id`, `dep_name`, `dt_create`, `enabled`, `last_login`, `login_ip`, `name`, `password`, `username`) VALUES ('40289fa55c152593015c15259e0e0000', NULL, NULL, 1, 1, 1, '2017-5-18', NULL, NULL, '2017-5-17 14:43:26', 1, '2017-5-31 08:55:46', '0:0:0:0:0:0:0:1', '系统管理员', '$2a$04$dYqUiNaZDypNFtGZroWMCOMsHQrzNNYpP.q3uegoB3VJajfSeuJ3K', 'admin');
INSERT INTO `sys_users` (`user_id`, `v_qzjgid`, `v_qzjgmc`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `deadline`, `dep_id`, `dep_name`, `dt_create`, `enabled`, `last_login`, `login_ip`, `name`, `password`, `username`) VALUES ('40289fa55c197723015c19772f0b0000', NULL, NULL, 1, 1, 1, '2017-5-18', NULL, NULL, '2017-5-18 10:51:00', 1, NULL, NULL, '系统普通用户', '$2a$04$z79WM9zByXY1k0ZMo.7Xk.ffwVu0qPLQAIc4/9N8GniE7wpf9Lm2a', 'user');

INSERT INTO `sys_roles` (`role_id`, `enable`, `issys`, `module_id`, `role_desc`, `role_name`, `user_id`) VALUES ('40289fa55c15371d015c153728570000', 1, 1, NULL, '系统管理员, 不允许删除', 'ROLE_ADMIN', NULL);
INSERT INTO `sys_roles` (`role_id`, `enable`, `issys`, `module_id`, `role_desc`, `role_name`, `user_id`) VALUES ('40289fa55c197347015c197352a70000', 1, 1, NULL, '系统普通用户, 不允许删除', 'ROLE_USER', NULL);

INSERT INTO `sys_authorities` (`authority_id`, `authority_desc`, `authority_mark`, `authority_name`, `enable`, `issys`, `message`, `module_id`, `remark`) VALUES ('40289fa55c1a0322015c1a032df70000', '登录后首页', 'AUTH_DASHBOARD', '登录后首页', 1, 1, '登录后首页', NULL, NULL);
INSERT INTO `sys_authorities` (`authority_id`, `authority_desc`, `authority_mark`, `authority_name`, `enable`, `issys`, `message`, `module_id`, `remark`) VALUES ('40289fa55c1a0322015c1a032e070001', '产品列表页', 'AUTH_PRODUCT_LIST', '产品列表页', 1, 1, '产品列表页', NULL, NULL);
INSERT INTO `sys_authorities` (`authority_id`, `authority_desc`, `authority_mark`, `authority_name`, `enable`, `issys`, `message`, `module_id`, `remark`) VALUES ('40289fa55c1a0322015c1a032e070002', '产品列表页 - 添加', 'AUTH_PRODUCT_ADD', '产品列表页 - 添加', 1, 1, '产品列表页 - 添加', NULL, NULL);
INSERT INTO `sys_authorities` (`authority_id`, `authority_desc`, `authority_mark`, `authority_name`, `enable`, `issys`, `message`, `module_id`, `remark`) VALUES ('40289fa55c1a0322015c1a032e070003', '产品列表页 - 删除', 'AUTH_PRODUCT_DELETE', '产品列表页 - 删除', 1, 1, '产品列表页 - 删除', NULL, NULL);
INSERT INTO `sys_authorities` (`authority_id`, `authority_desc`, `authority_mark`, `authority_name`, `enable`, `issys`, `message`, `module_id`, `remark`) VALUES ('40289fa55c1a0322015c1a032e070004', '产品列表页 - 编辑', 'AUTH_PRODUCT_UPDATE', '产品列表页 - 编辑', 1, 1, '产品列表页 - 编辑', NULL, NULL);
INSERT INTO `sys_authorities` (`authority_id`, `authority_desc`, `authority_mark`, `authority_name`, `enable`, `issys`, `message`, `module_id`, `remark`) VALUES ('40289fa55c1a0322015c1a032e070005', '产品列表页 - 上架', 'AUTH_PRODUCT_UP', '产品列表页 - 上架', 1, 1, '产品列表页 - 上架', NULL, NULL);
INSERT INTO `sys_authorities` (`authority_id`, `authority_desc`, `authority_mark`, `authority_name`, `enable`, `issys`, `message`, `module_id`, `remark`) VALUES ('40289fa55c1a0322015c1a032e080006', '产品列表页 - 下架', 'AUTH_PRODUCT_DOWN', '产品列表页 - 下架', 1, 1, '产品列表页 - 下架', NULL, NULL);

INSERT INTO `sys_modules` (`module_id`, `i_level`, `application`, `controller`, `enable`, `issys`, `leaf`, `module_desc`, `module_name`, `module_type`, `module_url`, `parent`, `priority`) VALUES ('40289fa55c158562015c15856d4c0000', 1, 'APP_TRADING_ADMIN', NULL, 1, 1, '\0', '数据商城', '数据商城', 'normal', NULL, NULL, 10);
INSERT INTO `sys_modules` (`module_id`, `i_level`, `application`, `controller`, `enable`, `issys`, `leaf`, `module_desc`, `module_name`, `module_type`, `module_url`, `parent`, `priority`) VALUES ('40289fa55c158562015c15856d7c0001', 2, 'APP_TRADING_ADMIN', NULL, 1, 1, 1, '数据商城 - 产品管理', '产品管理', 'normal', '/products', '40289fa55c158562015c15856d4c0000', 100);
INSERT INTO `sys_modules` (`module_id`, `i_level`, `application`, `controller`, `enable`, `issys`, `leaf`, `module_desc`, `module_name`, `module_type`, `module_url`, `parent`, `priority`) VALUES ('40289fa55c158562015c15856d7d0002', 2, 'APP_TRADING_ADMIN', NULL, 1, 1, 1, '数据商城 - 类目管理', '类目管理', 'normal', '/types', '40289fa55c158562015c15856d4c0000', 110);

INSERT INTO `sys_resources` (`resource_id`, `enable`, `http_method`, `issys`, `module_id`, `priority`, `resource_desc`, `resource_name`, `resource_path`, `resource_type`) VALUES ('40289fa55c15a4c7015c15a4d2750000', 1, NULL, 1, NULL, 1, '添加产品', '添加产品', '/products/add', 'METHOD');
INSERT INTO `sys_resources` (`resource_id`, `enable`, `http_method`, `issys`, `module_id`, `priority`, `resource_desc`, `resource_name`, `resource_path`, `resource_type`) VALUES ('40289fa55c15a4c7015c15a4d2840001', 1, NULL, 1, NULL, 2, '修改产品', '修改产品', '/products/update', 'METHOD');
INSERT INTO `sys_resources` (`resource_id`, `enable`, `http_method`, `issys`, `module_id`, `priority`, `resource_desc`, `resource_name`, `resource_path`, `resource_type`) VALUES ('40289fa55c15a4c7015c15a4d2840002', 1, NULL, 1, NULL, 3, '产品下架', '下架', '/products/down', 'METHOD');
INSERT INTO `sys_resources` (`resource_id`, `enable`, `http_method`, `issys`, `module_id`, `priority`, `resource_desc`, `resource_name`, `resource_path`, `resource_type`) VALUES ('40289fa55c15a4c7015c15a4d2840003', 1, NULL, 1, NULL, 4, '产品上架', '上架', '/products/up', 'METHOD');
INSERT INTO `sys_resources` (`resource_id`, `enable`, `http_method`, `issys`, `module_id`, `priority`, `resource_desc`, `resource_name`, `resource_path`, `resource_type`) VALUES ('40289fa55c1a0dcc015c1a0ddd570000', 1, NULL, 1, NULL, 5, '产品列表', '产品列表', '/products', 'URL');
INSERT INTO `sys_resources` (`resource_id`, `enable`, `http_method`, `issys`, `module_id`, `priority`, `resource_desc`, `resource_name`, `resource_path`, `resource_type`) VALUES ('40289fa55c1a0dcc015c1a0ddd720001', 1, NULL, 1, NULL, 6, '登录后首页', '登录后首页', '/dashboard', 'URL');
INSERT INTO `sys_resources` (`resource_id`, `enable`, `http_method`, `issys`, `module_id`, `priority`, `resource_desc`, `resource_name`, `resource_path`, `resource_type`) VALUES ('40289fa55c1a220e015c1a221f300000', 1, NULL, 1, NULL, 7, '产品删除', '删除', '/products/delete', 'METHOD');


INSERT INTO `sys_users_roles` (`id`, `czybh`, `role_id`, `user_id`) VALUES ('40289fa55c154d70015c154d7afc0000', NULL, '40289fa55c15371d015c153728570000', '40289fa55c152593015c15259e0e0000');
INSERT INTO `sys_users_roles` (`id`, `czybh`, `role_id`, `user_id`) VALUES ('40289fa55c1a1441015c1a1451e70000', NULL, '40289fa55c197347015c197352a70000', '40289fa55c197723015c19772f0b0000');

INSERT INTO `sys_roles_modules` (`id`, `module_id`, `role_id`) VALUES ('40289fa55c1591f8015c1592038b0000', '40289fa55c158562015c15856d4c0000', '40289fa55c15371d015c153728570000');
INSERT INTO `sys_roles_modules` (`id`, `module_id`, `role_id`) VALUES ('40289fa55c1591f8015c159203950001', '40289fa55c158562015c15856d7c0001', '40289fa55c15371d015c153728570000');
INSERT INTO `sys_roles_modules` (`id`, `module_id`, `role_id`) VALUES ('40289fa55c1591f8015c159203950002', '40289fa55c158562015c15856d7d0002', '40289fa55c15371d015c153728570000');
INSERT INTO `sys_roles_modules` (`id`, `module_id`, `role_id`) VALUES ('40289fa55c1a17f0015c1a18023f0000', '40289fa55c158562015c15856d4c0000', '40289fa55c197347015c197352a70000');

INSERT INTO `sys_roles_authorities` (`id`, `authority_id`, `role_id`) VALUES ('40289fa55c1a26dc015c1a26e7260000', '40289fa55c1a0322015c1a032df70000', '40289fa55c15371d015c153728570000');
INSERT INTO `sys_roles_authorities` (`id`, `authority_id`, `role_id`) VALUES ('40289fa55c1a26dc015c1a26e7310001', '40289fa55c1a0322015c1a032e070001', '40289fa55c15371d015c153728570000');
INSERT INTO `sys_roles_authorities` (`id`, `authority_id`, `role_id`) VALUES ('40289fa55c1a26dc015c1a26e7310002', '40289fa55c1a0322015c1a032e070002', '40289fa55c15371d015c153728570000');
INSERT INTO `sys_roles_authorities` (`id`, `authority_id`, `role_id`) VALUES ('40289fa55c1a26dc015c1a26e7310003', '40289fa55c1a0322015c1a032e070003', '40289fa55c15371d015c153728570000');
INSERT INTO `sys_roles_authorities` (`id`, `authority_id`, `role_id`) VALUES ('40289fa55c1a26dc015c1a26e7310004', '40289fa55c1a0322015c1a032e070004', '40289fa55c15371d015c153728570000');
INSERT INTO `sys_roles_authorities` (`id`, `authority_id`, `role_id`) VALUES ('40289fa55c1a26dc015c1a26e7310005', '40289fa55c1a0322015c1a032e070005', '40289fa55c15371d015c153728570000');
INSERT INTO `sys_roles_authorities` (`id`, `authority_id`, `role_id`) VALUES ('40289fa55c1a26dc015c1a26e7310006', '40289fa55c1a0322015c1a032e080006', '40289fa55c15371d015c153728570000');
INSERT INTO `sys_roles_authorities` (`id`, `authority_id`, `role_id`) VALUES ('40289fa55c1a26dc015c1a26e7310007', '40289fa55c1a0322015c1a032df70000', '40289fa55c197347015c197352a70000');
INSERT INTO `sys_roles_authorities` (`id`, `authority_id`, `role_id`) VALUES ('40289fa55c1a26dc015c1a26e7320008', '40289fa55c1a0322015c1a032e070001', '40289fa55c197347015c197352a70000');

INSERT INTO `sys_authorities_resources` (`id`, `authority_id`, `resource_id`) VALUES ('40289fa55c1a2296015c1a22a8320000', '40289fa55c1a0322015c1a032e070001', '40289fa55c1a0dcc015c1a0ddd570000');
INSERT INTO `sys_authorities_resources` (`id`, `authority_id`, `resource_id`) VALUES ('40289fa55c1a2296015c1a22a8420001', '40289fa55c1a0322015c1a032e070002', '40289fa55c15a4c7015c15a4d2750000');
INSERT INTO `sys_authorities_resources` (`id`, `authority_id`, `resource_id`) VALUES ('40289fa55c1a2296015c1a22a8420002', '40289fa55c1a0322015c1a032e070003', '40289fa55c1a220e015c1a221f300000');
INSERT INTO `sys_authorities_resources` (`id`, `authority_id`, `resource_id`) VALUES ('40289fa55c1a2296015c1a22a8420003', '40289fa55c1a0322015c1a032e070004', '40289fa55c15a4c7015c15a4d2840001');
INSERT INTO `sys_authorities_resources` (`id`, `authority_id`, `resource_id`) VALUES ('40289fa55c1a2296015c1a22a8420004', '40289fa55c1a0322015c1a032e070005', '40289fa55c15a4c7015c15a4d2840003');
INSERT INTO `sys_authorities_resources` (`id`, `authority_id`, `resource_id`) VALUES ('40289fa55c1a2296015c1a22a8420005', '40289fa55c1a0322015c1a032e080006', '40289fa55c15a4c7015c15a4d2840002');
INSERT INTO `sys_authorities_resources` (`id`, `authority_id`, `resource_id`) VALUES ('40289fa55c1a2296015c1a22a8420006', '40289fa55c1a0322015c1a032df70000', '40289fa55c1a0dcc015c1a0ddd720001');
