


-- 设备管理
create table if not exists dv_serial_setting
(
    id               INTEGER      not null
        constraint pk_dv_serial_setting
            primary key autoincrement,
    device_name      VARCHAR(128) not null,
    device_type      VARCHAR(128) not null
        constraint dv_serial_setting_unqiue_device_type
            unique,
    agreement_type   VARCHAR(32)  not null,
    serial_number    VARCHAR(16)  not null,
    baud_rate        INTEGER      not null,
    data_bit         INTEGER      not null,
    parity_bit       INTEGER      not null,
    stop_bit         INTEGER      not null,
    interface_status INTEGER default 0 not null,
    create_time      INTEGER      not null,
    update_time      INTEGER      not null,
    remark           VARCHAR(256)
);

--采集数据
create table if not exists da_voltage_data
(
    id                                  INTEGER        not null
        constraint pk_da_voltage_data
            primary key autoincrement,
    board_num                           INTEGER        not null,
    board_channel                       INTEGER        not null,
    channel_num                         INTEGER        not null,
    collect_time                        INTEGER        not null,
    time_type                           INTEGER        not null,
    aa_fundamental_frequency            NUMERIC(10, 5) not null,
    aa_fundamental_amplitude            NUMERIC(10, 5) not null,
    a3_harmonic_amplitude               NUMERIC(12, 7) not null,
    a5_harmonic_amplitude               NUMERIC(12, 7) not null,
    aa_fundamental_phase                NUMERIC(10, 2) not null,
    a3_harmonic_phase                   NUMERIC(10, 2) not null,
    a5_harmonic_phase                   NUMERIC(10, 2) not null,
    bb_fundamental_frequency            NUMERIC(10, 5) not null,
    bb_fundamental_amplitude            NUMERIC(10, 5) not null,
    b3_harmonic_amplitude               NUMERIC(12, 7) not null,
    b5_harmonic_amplitude               NUMERIC(12, 7) not null,
    bb_fundamental_phase                NUMERIC(10, 2) not null,
    b3_harmonic_phase                   NUMERIC(10, 2) not null,
    b5_harmonic_phase                   NUMERIC(10, 2) not null,
    cc_fundamental_frequency            NUMERIC(10, 5) not null,
    cc_fundamental_amplitude            NUMERIC(10, 5) not null,
    c3_harmonic_amplitude               NUMERIC(12, 7) not null,
    c5_harmonic_amplitude               NUMERIC(12, 7) not null,
    cc_fundamental_phase                NUMERIC(10, 2) not null,
    c3_harmonic_phase                   NUMERIC(10, 2) not null,
    c5_harmonic_phase                   NUMERIC(10, 2) not null,
    zero_sequence_voltage_imbalance     NUMERIC(13, 5) default 0.00000 not null,
    negative_sequence_voltage_imbalance NUMERIC(13, 5) default 0.00000 not null,
    status                              INTEGER        not null,
    channel_level                       INTEGER        not null,
    create_time                         INTEGER        not null,
    column1                             VARCHAR(128),
    column2                             VARCHAR(128),
    column3                             VARCHAR(128),
    is_supplement                       INTEGER        not null,
    aa_board_sort_num                   INT(5)         default "0",
    bb_board_sort_num                   INT(5)         default "0",
    cc_board_sort_num                   INT(5)         default "0",
    aa_clock_status                     INT(5)         default "0",
    bb_clock_status                     INT(5)         default "0",
    cc_clock_status                     INT(5)         default "0",
    aa_logic_data_quality               VARCHAR(32)    default NULL,
    bb_logic_data_quality               VARCHAR(32)    default NULL,
    cc_logic_data_quality               VARCHAR(32)    default NULL
);

-- 索引
create unique index if not exists da_voltage_data_channel_collect_time
    on da_voltage_data (collect_time, channel_num);

-- 计算数据
create table if not exists err_voltage_error
(
    id                    INTEGER        not null
        constraint pk_err_voltage_error
            primary key autoincrement,
    channel_num           INTEGER        not null,
    calculate_time        INTEGER        not null,
    aa_ratio_error        NUMERIC(14, 8) not null,
    bb_ratio_error        NUMERIC(14, 8) not null,
    cc_ratio_error        NUMERIC(14, 8) not null,
    aa_ratio_variance     NUMERIC(14, 8) default 0.00000000 not null,
    bb_ratio_variance     NUMERIC(14, 8) default 0.00000000 not null,
    cc_ratio_variance     NUMERIC(14, 8) default 0.00000000 not null,
    aa_angle_error        NUMERIC(14, 8) default 0.00000000 not null,
    bb_angle_error        NUMERIC(14, 8) default 0.00000000 not null,
    cc_angle_error        NUMERIC(14, 8) default 0.00000000 not null,
    aa_angle_variance     NUMERIC(14, 8) default 0.00000000 not null,
    bb_angle_variance     NUMERIC(14, 8) default 0.00000000 not null,
    cc_angle_variance     NUMERIC(14, 8) default 0.00000000 not null,
    data_start_time       INTEGER        not null,
    data_end_time         INTEGER        not null,
    ub_new                VARCHAR(512)   not null,
    inputub               VARCHAR(512)   not null,
    channel_level         INTEGER        not null,
    create_time           INTEGER        not null,
    calculate_status      varchar(8),
    calculate_msg         varchar(512),
    ratio_varargin        varchar(1024),
    angle_varargin        varchar(1024),
    group_id              bigint,
    group_priority        INT,
    aa_qualitative_result varchar(16),
    bb_qualitative_result varchar(16),
    cc_qualitative_result varchar(16)
);

create index if not exists err_voltage_error_calculate_time
    on err_voltage_error (calculate_time);

create unique index if not exists err_voltage_error_time_channel_index
    on err_voltage_error (calculate_time, channel_num);


-- 事件数据
create table if not exists inspection_event_info
(
    id                  INTEGER       not null
        constraint pk_inspection_event_info
            primary key autoincrement,
    num                 INTEGER       not null,
    event_type          INTEGER       not null,
    event_code          INTEGER       not null,
    event_name          VARCHAR(100)  not null,
    event_source        VARCHAR(1024) not null,
    start_time          INTEGER,
    recovery_time       INTEGER,
    recovery_reason     VARCHAR(1024),
    source_code         VARCHAR(32),
    source_unit_id      INTEGER,
    source_unit_type    VARCHAR(4),
    source_board_num    INTEGER,
    source_channel_num  INTEGER,
    source_channel_name VARCHAR(255),
    event_status        INTEGER,
    create_time         INTEGER       not null,
    exception_id        INTEGER,
    reserved1           VARCHAR(255),
    reserved2           VARCHAR(255)
);

create index if not exists exception_id_index
    on inspection_event_info (exception_id);

create index if not exists inspection_event_info_channel_num_index
    on inspection_event_info (source_channel_num);

create index if not exists inspection_event_info_create_time_index
    on inspection_event_info (create_time);

create index if not exists start_time_index
    on inspection_event_info (start_time);

-- 数据字典
create table if not exists sys_dict_type
(
    dict_id     INTEGER not null
        constraint pk_sys_dict_type
            primary key autoincrement,
    dict_name   VARCHAR(100) default '',
    dict_type   VARCHAR(100) default ''
        constraint un_index_dict_type
            unique,
    status      CHAR(1)      default '0',
    create_by   VARCHAR(64)  default '',
    create_time INTEGER,
    update_by   VARCHAR(64)  default '',
    update_time INTEGER,
    remark      VARCHAR(500)
);


create table if not exists sys_dict_data
(
    dict_code   INTEGER not null
        constraint pk_sys_dict_data
            primary key autoincrement,
    dict_sort   INTEGER      default 0,
    dict_label  VARCHAR(100) default '',
    dict_value  VARCHAR(100) default '',
    dict_type   VARCHAR(100) default '',
    css_class   VARCHAR(100),
    list_class  VARCHAR(100),
    is_default  CHAR(1)      default 'N',
    status      CHAR(1)      default '0',
    create_by   VARCHAR(64)  default '',
    create_time INTEGER,
    update_by   VARCHAR(64)  default '',
    update_time INTEGER,
    remark      VARCHAR(500)
);

create unique index if not exists type_value_idx
    on sys_dict_data (dict_type, dict_value);

-- 菜单数据
create table if not exists sys_menu
(
    menu_id     INTEGER     not null
        constraint pk_sys_menu
            primary key autoincrement,
    menu_name   VARCHAR(50) not null,
    parent_id   BIGINT       default 0,
    order_num   INTEGER      default 0,
    url         VARCHAR(200) default '#',
    target      VARCHAR(20)  default '',
    menu_type   CHAR(1)      default '',
    visible     CHAR(1)      default '0',
    is_refresh  CHAR(1)      default '1',
    perms       VARCHAR(100),
    icon        VARCHAR(100) default '#',
    create_by   VARCHAR(64)  default '',
    create_time INTEGER,
    update_by   VARCHAR(64)  default '',
    update_time INTEGER,
    remark      VARCHAR(500) default ''
);


-- 字典数据

-- 设备类型
delete from sys_dict_type where dict_type = 'sys_485_device_type';
INSERT INTO "sys_dict_type" ("dict_name", "dict_type", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES ('设备类型', 'sys_485_device_type', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '采集单元类型');

delete from sys_dict_data where dict_type = 'sys_485_device_type';
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (1, '能源控制器', '1', 'sys_485_device_type', '', '', 'Y', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '能源控制器');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (2, '采集终端', '2', 'sys_485_device_type', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '采集终端');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (3, '宁波三维', '3', 'sys_485_device_type', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '宁波三维');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (4, '武高所台体MODBUS', '4', 'sys_485_device_type', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '武高所台体MODBUS');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (5, '江苏4G模块', '5', 'sys_485_device_type', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '江苏4G模块');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (6, '国网DL/T645', '6', 'sys_485_device_type', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '国网DL/T645');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (7, '南网645', '7', 'sys_485_device_type', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '南网645');

-- 通讯协议
delete from sys_dict_type where dict_type = 'sys_protocol_type';
INSERT INTO "sys_dict_type" ("dict_name", "dict_type", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES ('协议类型', 'sys_protocol_type', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '协议类型');

delete from sys_dict_data where dict_type = 'sys_protocol_type';
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (1, 'DL/T-698', '698', 'sys_protocol_type', '', '', 'Y', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '能源控制器');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (2, 'DL/T-645', '645', 'sys_protocol_type', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '采集终端');

-- 波特率

delete from sys_dict_type where dict_type = '485_baud_rate';
INSERT INTO "sys_dict_type" ("dict_name", "dict_type", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES ('485波特率', '485_baud_rate', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '485波特率');

delete from sys_dict_data where dict_type = '485_baud_rate';
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (1, '300', '300', '485_baud_rate', '', '', 'Y', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '300');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (2, '600', '600', '485_baud_rate', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '600');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (3, '1200', '1200', '485_baud_rate', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '1200');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (4, '2400', '2400', '485_baud_rate', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '2400');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (5, '4800', '4800', '485_baud_rate', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '4800');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (6, '9600', '9600', '485_baud_rate', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '9600');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (7, '14400', '14400', '485_baud_rate', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '14400');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (8, '56000', '56000', '485_baud_rate', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '56000');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (9, '115200', '115200', '485_baud_rate', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '115200');


-- 数据位

delete from sys_dict_type where dict_type = '485_data_bit';
INSERT INTO "sys_dict_type" ("dict_name", "dict_type", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES ('485数据位', '485_data_bit', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '485数据位');

delete from sys_dict_data where dict_type = '485_data_bit';
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (1, '5', '5', '485_data_bit', '', '', 'Y', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '5');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (2, '6', '6', '485_data_bit', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '6');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (3, '7', '7', '485_data_bit', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '7');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (4, '8', '8', '485_data_bit', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '8');

-- 校验值
delete from sys_dict_type where dict_type = '485_check_bit';
INSERT INTO "sys_dict_type" ("dict_name", "dict_type", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES ('485校验位', '485_check_bit', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '485校验位');

delete from sys_dict_data where dict_type = '485_data_bit';
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (1, 'None', '0', '485_check_bit', '', '', 'Y', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, 'None');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (2, 'Odd', '1', '485_check_bit', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, 'Odd');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (3, 'Even', '2', '485_check_bit', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, 'Even');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (4, 'Mark', '3', '485_check_bit', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, 'Mark');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (4, 'Space', '4', '485_check_bit', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, 'Space');

-- 停止位
delete from sys_dict_type where dict_type = '485_stop_bit';
INSERT INTO "sys_dict_type" ("dict_name", "dict_type", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES ('485停止位', '485_stop_bit', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '485停止位');

delete from sys_dict_data where dict_type = '485_stop_bit';
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (1, '1', '1', '485_stop_bit', '', '', 'Y', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '1');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (2, '1.5', '3', '485_stop_bit', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '1.5');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (3, '2', '2', '485_stop_bit', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '2');

-- 事件类型
delete from sys_dict_type where dict_type = 'inspect_event_type';
INSERT INTO "sys_dict_type" ("dict_name", "dict_type", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES ('事件类型', 'inspect_event_type', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '事件类型');

delete from sys_dict_data where dict_type = 'inspect_event_type';
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (1, '异常', '0', 'inspect_event_type', '', '', 'Y', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '异常');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (2, '告警', '1', 'inspect_event_type', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '告警');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (3, '提示', '2', 'inspect_event_type', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '提示');

-- 恢复状态
delete from sys_dict_type where dict_type = 'inspect_event_status';
INSERT INTO "sys_dict_type" ("dict_name", "dict_type", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES ('事件状态', 'inspect_event_status', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '事件状态');

delete from sys_dict_data where dict_type = 'inspect_event_status';
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (1, '未恢复', '0', 'inspect_event_status', '', '', 'Y', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '未恢复');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (2, '已恢复', '1', 'inspect_event_status', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '已恢复');

-- 校验进度
delete from sys_dict_type where dict_type = 'check_status';
INSERT INTO "sys_dict_type" ("dict_name", "dict_type", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES ('校验进度', 'check_status', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '校验进度');

delete from sys_dict_data where dict_type = 'check_status';
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (1, '已完成', '0', 'check_status', '', '', 'Y', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '已完成');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (2, '进行中', '1', 'check_status', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '进行中');

-- 校验结果
delete from sys_dict_type where dict_type = 'check_result';
INSERT INTO "sys_dict_type" ("dict_name", "dict_type", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES ('校验结果', 'check_result', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '校验结果');

delete from sys_dict_data where dict_type = 'check_result';
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (1, '正确', '0', 'check_result', '', '', 'Y', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '正确');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (2, '错误', '1', 'check_result', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '错误');

-- 校验类型

delete from sys_dict_type where dict_type = 'check_type';
INSERT INTO "sys_dict_type" ("dict_name", "dict_type", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES ('校验类型', 'check_type', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '校验类型');

delete from sys_dict_data where dict_type = 'check_type';
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (1, '电压采集数据', '0', 'check_type', '', '', 'Y', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '电压采集数据');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (2, '电压计算数据', '1', 'check_type', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '电压计算数据');
INSERT INTO "sys_dict_data" ("dict_sort", "dict_label", "dict_value", "dict_type", "css_class", "list_class", "is_default", "status", "create_by", "create_time", "update_by", "update_time", "remark")
VALUES (3, '事件数据', '2', 'check_type', '', '', 'N', 0, 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '事件数据');


-- 菜单
create table if not exists sys_menu
(
    menu_id     INTEGER     not null
        constraint pk_sys_menu
            primary key autoincrement,
    menu_name   VARCHAR(50) not null,
    parent_id   BIGINT       default 0,
    order_num   INTEGER      default 0,
    url         VARCHAR(200) default '#',
    target      VARCHAR(20)  default '',
    menu_type   CHAR(1)      default '',
    visible     CHAR(1)      default '0',
    is_refresh  CHAR(1)      default '1',
    perms       VARCHAR(100),
    icon        VARCHAR(100) default '#',
    create_by   VARCHAR(64)  default '',
    create_time INTEGER,
    update_by   VARCHAR(64)  default '',
    update_time INTEGER,
    remark      VARCHAR(500) default ''
);

-- 设备管理
DELETE from sys_menu where perms like 'serialsetting:serialsetting:%';
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('设备管理', '0', '1', '/serialsetting', 'C', '0', 'serialsetting:serialsetting:view', '#', 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '设备管理');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('RS-485', (select menu_id from sys_menu where perms = 'serialsetting:serialsetting:view'), '1', '/serialsetting/serialsetting', 'C', '0', 'serialsetting:serialsetting:lits', '#', 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, 'RS-485');

-- 数据查询
DELETE from sys_menu where perms like 'dataSelect:%';
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('数据查询', '0', '2', '/dataSelect', 'C', '0', 'dataSelect:view', '#', 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '数据查询');


insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('电压采集数据', (select menu_id from sys_menu where perms = 'dataSelect:view'), '1', '/dataSelect/voltageCollect', 'C', '0', 'dataSelect:voltageCollect', '#', 'admin', strftime('%s','now')*1000, 'admin', strftime('%s','now')*1000, '电压采集数据');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('电压计算数据', (select menu_id from sys_menu where perms = 'dataSelect:view'), '2',  '/dataSelect/voltageCalculate',  'C', '0', 'dataSelect:voltageCalculate','#', 'admin', strftime('%s','now')*1000, 'admin', strftime('%s','now')*1000, '电压计算数据');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('事件数据', (select menu_id from sys_menu where perms = 'dataSelect:view'), '3',  '/dataSelect/event',  'C', '0', 'communication:event',  '#', 'admin', strftime('%s','now')*1000, 'admin', strftime('%s','now')*1000, '事件数据');



-- 数据校验
DELETE from sys_menu where perms like 'dataCheck:%';
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('数据校验', '0', '3', '/dataCheck', 'C', '0', 'dataCheck:view', '#', 'admin', strftime('%s','now') * 1000, 'admin', strftime('%s','now') * 1000, '数据校验');

