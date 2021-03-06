USE [free]
GO
/****** Object:  Table [dbo].[app_user]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[app_user](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[minaopenid] [varchar](50) NULL,
	[wxopenid] [varchar](50) NULL,
	[mpopenid] [varchar](50) NOT NULL,
	[unionid] [varchar](50) NULL,
	[nickname] [varchar](50) NULL,
	[sex] [varchar](1) NULL,
	[city] [varchar](50) NULL,
	[country] [varchar](50) NULL,
	[province] [varchar](50) NULL,
	[headimgurl] [varchar](200) NULL,
	[usercode] [varchar](50) NULL,
 CONSTRAINT [PK_app_user] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[gen_table]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[gen_table](
	[table_id] [int] IDENTITY(1,1) NOT NULL,
	[table_name] [varchar](200) NULL,
	[table_comment] [varchar](500) NULL,
	[class_name] [varchar](100) NULL,
	[tpl_category] [varchar](200) NULL,
	[package_name] [varchar](100) NULL,
	[module_name] [varchar](30) NULL,
	[business_name] [varchar](30) NULL,
	[function_name] [varchar](50) NULL,
	[function_author] [varchar](50) NULL,
	[options] [varchar](1000) NULL,
	[create_by] [varchar](64) NULL,
	[create_time] [datetime] NULL,
	[update_by] [varchar](64) NULL,
	[update_time] [datetime] NULL,
	[remark] [varchar](500) NULL,
 CONSTRAINT [PK_gen_table] PRIMARY KEY CLUSTERED 
(
	[table_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[gen_table_column]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[gen_table_column](
	[column_id] [int] IDENTITY(1,1) NOT NULL,
	[table_id] [varchar](64) NULL,
	[column_name] [varchar](200) NULL,
	[column_comment] [varchar](500) NULL,
	[column_type] [varchar](100) NULL,
	[java_type] [varchar](500) NULL,
	[java_field] [varchar](200) NULL,
	[is_pk] [varchar](1) NULL,
	[is_increment] [varchar](1) NULL,
	[is_required] [varchar](1) NULL,
	[is_insert] [varchar](1) NULL,
	[is_edit] [varchar](1) NULL,
	[is_list] [varchar](1) NULL,
	[is_query] [varchar](1) NULL,
	[query_type] [varchar](200) NULL,
	[html_type] [varchar](200) NULL,
	[dict_type] [varchar](200) NULL,
	[sort] [int] NULL,
	[create_by] [varchar](64) NULL,
	[create_time] [datetime] NULL,
	[update_by] [varchar](64) NULL,
	[update_time] [datetime] NULL,
 CONSTRAINT [PK_gen_table_column] PRIMARY KEY CLUSTERED 
(
	[column_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_config]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_config](
	[config_id] [int] IDENTITY(1,1) NOT NULL,
	[config_name] [varchar](100) NULL,
	[config_key] [varchar](100) NULL,
	[config_value] [varchar](500) NULL,
	[config_type] [varchar](1) NULL,
	[create_by] [varchar](64) NULL,
	[create_time] [datetime] NULL,
	[update_by] [varchar](64) NULL,
	[update_time] [datetime] NULL,
	[remark] [varchar](500) NULL,
 CONSTRAINT [PK_sys_config] PRIMARY KEY CLUSTERED 
(
	[config_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_dept]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_dept](
	[dept_id] [int] IDENTITY(1,1) NOT NULL,
	[parent_id] [int] NULL,
	[ancestors] [varchar](50) NULL,
	[dept_name] [varchar](30) NULL,
	[order_num] [int] NULL,
	[leader] [varchar](20) NULL,
	[phone] [varchar](20) NULL,
	[email] [varchar](50) NULL,
	[status] [varchar](1) NULL,
	[del_flag] [varchar](1) NULL,
	[create_by] [varchar](64) NULL,
	[create_time] [datetime] NULL,
	[update_by] [varchar](64) NULL,
	[update_time] [datetime] NULL,
 CONSTRAINT [PK_sys_dept] PRIMARY KEY CLUSTERED 
(
	[dept_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_dict_data]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_dict_data](
	[dict_code] [int] IDENTITY(1,1) NOT NULL,
	[dict_sort] [int] NULL,
	[dict_label] [varchar](100) NULL,
	[dict_value] [varchar](100) NULL,
	[dict_type] [varchar](100) NULL,
	[css_class] [varchar](100) NULL,
	[list_class] [varchar](100) NULL,
	[is_default] [varchar](1) NULL,
	[status] [varchar](1) NULL,
	[create_by] [varchar](64) NULL,
	[create_time] [datetime] NULL,
	[update_by] [varchar](64) NULL,
	[update_time] [datetime] NULL,
	[remark] [varchar](500) NULL,
 CONSTRAINT [PK_sys_dict_data] PRIMARY KEY CLUSTERED 
(
	[dict_code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_dict_type]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_dict_type](
	[dict_id] [int] IDENTITY(1,1) NOT NULL,
	[dict_name] [varchar](100) NULL,
	[dict_type] [varchar](100) NULL,
	[status] [varchar](1) NULL,
	[create_by] [varchar](64) NULL,
	[create_time] [datetime] NULL,
	[update_by] [varchar](64) NULL,
	[update_time] [datetime] NULL,
	[remark] [varchar](500) NULL,
 CONSTRAINT [PK_sys_dict_type] PRIMARY KEY CLUSTERED 
(
	[dict_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_job]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_job](
	[job_id] [int] IDENTITY(1,1) NOT NULL,
	[job_name] [varchar](64) NULL,
	[job_group] [varchar](64) NULL,
	[invoke_target] [varchar](500) NULL,
	[cron_expression] [varchar](255) NULL,
	[misfire_policy] [varchar](20) NULL,
	[concurrent] [varchar](1) NULL,
	[status] [varchar](1) NULL,
	[create_by] [varchar](64) NULL,
	[create_time] [datetime] NULL,
	[update_by] [varchar](64) NULL,
	[update_time] [datetime] NULL,
	[remark] [varchar](500) NULL,
 CONSTRAINT [PK_sys_job] PRIMARY KEY CLUSTERED 
(
	[job_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_job_log]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_job_log](
	[job_log_id] [int] IDENTITY(1,1) NOT NULL,
	[job_name] [varchar](64) NULL,
	[job_group] [varchar](64) NULL,
	[invoke_target] [varchar](500) NULL,
	[job_message] [varchar](500) NULL,
	[status] [varchar](1) NULL,
	[exception_info] [varchar](2000) NULL,
	[create_time] [datetime] NULL,
 CONSTRAINT [PK_sys_job_log] PRIMARY KEY CLUSTERED 
(
	[job_log_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_logininfor]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_logininfor](
	[info_id] [int] IDENTITY(1,1) NOT NULL,
	[login_name] [varchar](50) NULL,
	[ipaddr] [varchar](50) NULL,
	[login_location] [varchar](255) NULL,
	[browser] [varchar](50) NULL,
	[os] [varchar](50) NULL,
	[status] [varchar](1) NULL,
	[msg] [varchar](255) NULL,
	[login_time] [datetime] NULL,
 CONSTRAINT [PK_sys_logininfor] PRIMARY KEY CLUSTERED 
(
	[info_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_menu]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_menu](
	[menu_id] [int] IDENTITY(1,1) NOT NULL,
	[menu_name] [varchar](50) NULL,
	[parent_id] [int] NULL,
	[order_num] [int] NULL,
	[url] [varchar](200) NULL,
	[target] [varchar](20) NULL,
	[menu_type] [varchar](1) NULL,
	[visible] [varchar](1) NULL,
	[perms] [varchar](100) NULL,
	[icon] [varchar](100) NULL,
	[create_by] [varchar](64) NULL,
	[create_time] [datetime] NULL,
	[update_by] [varchar](64) NULL,
	[update_time] [datetime] NULL,
	[remark] [varchar](500) NULL,
 CONSTRAINT [PK_sys_menu] PRIMARY KEY CLUSTERED 
(
	[menu_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_notice]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_notice](
	[notice_id] [int] IDENTITY(1,1) NOT NULL,
	[notice_title] [varchar](50) NULL,
	[notice_type] [varchar](1) NULL,
	[notice_content] [varchar](2000) NULL,
	[status] [varchar](1) NULL,
	[create_by] [varchar](64) NULL,
	[create_time] [datetime] NULL,
	[update_by] [varchar](64) NULL,
	[update_time] [datetime] NULL,
	[remark] [varchar](500) NULL,
 CONSTRAINT [PK_sys_notice] PRIMARY KEY CLUSTERED 
(
	[notice_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_oper_log]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_oper_log](
	[oper_id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](50) NULL,
	[business_type] [int] NULL,
	[method] [varchar](100) NULL,
	[request_method] [varchar](10) NULL,
	[operator_type] [int] NULL,
	[oper_name] [varchar](50) NULL,
	[dept_name] [varchar](50) NULL,
	[oper_url] [varchar](255) NULL,
	[oper_ip] [varchar](50) NULL,
	[oper_location] [varchar](255) NULL,
	[oper_param] [varchar](2000) NULL,
	[json_result] [varchar](2000) NULL,
	[status] [int] NULL,
	[error_msg] [varchar](2000) NULL,
	[oper_time] [datetime] NULL,
 CONSTRAINT [PK_sys_oper_log] PRIMARY KEY CLUSTERED 
(
	[oper_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_post]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_post](
	[post_id] [int] IDENTITY(1,1) NOT NULL,
	[post_code] [varchar](64) NULL,
	[post_name] [varchar](50) NULL,
	[post_sort] [int] NULL,
	[status] [varchar](1) NULL,
	[create_by] [varchar](64) NULL,
	[create_time] [datetime] NULL,
	[update_by] [varchar](64) NULL,
	[update_time] [datetime] NULL,
	[remark] [varchar](500) NULL,
 CONSTRAINT [PK_sys_post] PRIMARY KEY CLUSTERED 
(
	[post_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_role]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_role](
	[role_id] [int] IDENTITY(1,1) NOT NULL,
	[role_name] [varchar](30) NULL,
	[role_key] [varchar](100) NULL,
	[role_sort] [int] NULL,
	[data_scope] [varchar](1) NULL,
	[status] [varchar](1) NULL,
	[create_by] [varchar](64) NULL,
	[create_time] [datetime] NULL,
	[update_by] [varchar](64) NULL,
	[update_time] [datetime] NULL,
	[remark] [varchar](500) NULL,
	[del_flag] [varchar](1) NULL,
 CONSTRAINT [PK_sys_role] PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_role_dept]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_role_dept](
	[role_id] [int] NOT NULL,
	[dept_id] [int] NOT NULL,
 CONSTRAINT [PK_sys_role_dept] PRIMARY KEY CLUSTERED 
(
	[role_id] ASC,
	[dept_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[sys_role_menu]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_role_menu](
	[role_id] [int] NOT NULL,
	[menu_id] [int] NOT NULL,
 CONSTRAINT [PK_sys_role_menu] PRIMARY KEY CLUSTERED 
(
	[role_id] ASC,
	[menu_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[sys_user]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_user](
	[user_id] [int] IDENTITY(1,1) NOT NULL,
	[dept_id] [int] NULL,
	[login_name] [varchar](30) NULL,
	[user_name] [varchar](30) NULL,
	[user_type] [varchar](2) NULL,
	[email] [varchar](50) NULL,
	[phonenumber] [varchar](20) NULL,
	[sex] [varchar](1) NULL,
	[avatar] [varchar](100) NULL,
	[password] [varchar](50) NULL,
	[salt] [varchar](20) NULL,
	[status] [varchar](1) NULL,
	[create_by] [varchar](64) NULL,
	[create_time] [datetime] NULL,
	[update_by] [varchar](64) NULL,
	[update_time] [datetime] NULL,
	[remark] [varchar](500) NULL,
	[del_flag] [varchar](1) NULL,
	[login_ip] [varchar](50) NULL,
	[login_date] [datetime] NULL,
 CONSTRAINT [PK_sys_user] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_user_online]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_user_online](
	[sessionId] [varchar](50) NOT NULL,
	[login_name] [varchar](50) NULL,
	[dept_name] [varchar](50) NULL,
	[ipaddr] [varchar](50) NULL,
	[login_location] [varchar](255) NULL,
	[browser] [varchar](50) NULL,
	[os] [varchar](50) NULL,
	[status] [varchar](10) NULL,
	[start_timestamp] [datetime] NULL,
	[last_access_time] [datetime] NULL,
	[expire_time] [int] NULL,
 CONSTRAINT [PK_sys_user_online] PRIMARY KEY CLUSTERED 
(
	[sessionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sys_user_post]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_user_post](
	[user_id] [int] NOT NULL,
	[post_id] [int] NOT NULL,
 CONSTRAINT [PK_sys_user_post] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC,
	[post_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[sys_user_role]    Script Date: 2020/11/29 12:46:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_user_role](
	[user_id] [int] NOT NULL,
	[role_id] [int] NOT NULL,
 CONSTRAINT [PK_sys_user_role] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[app_user] ON 

INSERT [dbo].[app_user] ([id], [minaopenid], [wxopenid], [mpopenid], [unionid], [nickname], [sex], [city], [country], [province], [headimgurl], [usercode]) VALUES (2, NULL, NULL, N'testopenid', NULL, N'Mr.Lin', N'1', N'阳江', N'中国', N'广东', N'http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM6y6aia1CLP0MyuCIolzp7rGQKnHUtiaibt2wNQgf0dbnb1ZU06CqQqmiagKXp5ekxR4t8aZ72Ow4bm8P98OXnLAbCC16Xx3TeGoek/132', NULL)
SET IDENTITY_INSERT [dbo].[app_user] OFF
SET IDENTITY_INSERT [dbo].[sys_config] ON 

INSERT [dbo].[sys_config] ([config_id], [config_name], [config_key], [config_value], [config_type], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (1, N'主框架页-默认皮肤样式名称', N'sys.index.skinName', N'skin-blue', N'Y', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow')
INSERT [dbo].[sys_config] ([config_id], [config_name], [config_key], [config_value], [config_type], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (2, N'用户管理-账号初始密码', N'sys.user.initPassword', N'admin123', N'Y', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'初始化密码 admin123')
INSERT [dbo].[sys_config] ([config_id], [config_name], [config_key], [config_value], [config_type], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (3, N'主框架页-侧边栏主题', N'sys.index.sideTheme', N'theme-dark', N'Y', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue')
INSERT [dbo].[sys_config] ([config_id], [config_name], [config_key], [config_value], [config_type], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (4, N'账号自助-是否开启用户注册功能', N'sys.account.registerUser', N'false', N'Y', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'是否开启注册用户功能')
INSERT [dbo].[sys_config] ([config_id], [config_name], [config_key], [config_value], [config_type], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (5, N'云存储配置信息', N'OSS', N'{"aliyunAccessKeyId":"","aliyunAccessKeySecret":"","aliyunBucketName":"","aliyunDomain":"","aliyunEndPoint":"","aliyunPrefix":"","qcloudBucketName":"","qcloudDomain":"","qcloudPrefix":"","qcloudSecretId":"","qcloudSecretKey":"","qiniuAccessKey":"RgaVn1VVQXaQTfpUgFbKzbKiUnsw3waEqWZdRzOe","qiniuBucketName":"lizhi-yuer","qiniuDomain":"http://image.iafeng.cc","qiniuPrefix":"upload","qiniuSecretKey":"填写你的KEY","type":1}', N'Y', N'admin', CAST(0x0000ABB700937DD0 AS DateTime), N'afeng', CAST(0x0000ABB700937DD0 AS DateTime), N'云存储配置信息')
INSERT [dbo].[sys_config] ([config_id], [config_name], [config_key], [config_value], [config_type], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (6, N'系统配置信息', N'SYS', N'{"cdnPath":"http://cdn.iafeng.cc/","webPath":"http://www.iafeng.cc/"}', N'N', N'admin', CAST(0x0000ABB700937DD0 AS DateTime), N'afeng', CAST(0x0000ABB700937DD0 AS DateTime), N'根据站点决定')
INSERT [dbo].[sys_config] ([config_id], [config_name], [config_key], [config_value], [config_type], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (7, N'业务配置信息', N'Business', N'{"cdnPath":"http://cdn.iafeng.cc/","webPath":"http://www.iafeng.cc/"}', N'N', N'admin', CAST(0x0000ABB700937DD0 AS DateTime), N'afeng', CAST(0x0000ABB700937DD0 AS DateTime), N'根据具体业务决定')
SET IDENTITY_INSERT [dbo].[sys_config] OFF
SET IDENTITY_INSERT [dbo].[sys_dept] ON 

INSERT [dbo].[sys_dept] ([dept_id], [parent_id], [ancestors], [dept_name], [order_num], [leader], [phone], [email], [status], [del_flag], [create_by], [create_time], [update_by], [update_time]) VALUES (1, 0, N'0', N'若依科技', 0, N'若依', N'15888888888', N'ry@qq.com', N'0', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime))
INSERT [dbo].[sys_dept] ([dept_id], [parent_id], [ancestors], [dept_name], [order_num], [leader], [phone], [email], [status], [del_flag], [create_by], [create_time], [update_by], [update_time]) VALUES (2, 1, N'0,1', N'深圳总公司', 1, N'若依', N'15888888888', N'ry@qq.com', N'0', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime))
INSERT [dbo].[sys_dept] ([dept_id], [parent_id], [ancestors], [dept_name], [order_num], [leader], [phone], [email], [status], [del_flag], [create_by], [create_time], [update_by], [update_time]) VALUES (3, 1, N'0,1', N'长沙分公司', 2, N'若依', N'15888888888', N'ry@qq.com', N'0', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime))
INSERT [dbo].[sys_dept] ([dept_id], [parent_id], [ancestors], [dept_name], [order_num], [leader], [phone], [email], [status], [del_flag], [create_by], [create_time], [update_by], [update_time]) VALUES (4, 2, N'0,1,2', N'研发部门', 1, N'若依', N'15888888888', N'ry@qq.com', N'0', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime))
INSERT [dbo].[sys_dept] ([dept_id], [parent_id], [ancestors], [dept_name], [order_num], [leader], [phone], [email], [status], [del_flag], [create_by], [create_time], [update_by], [update_time]) VALUES (5, 2, N'0,1,2', N'市场部门', 2, N'若依', N'15888888888', N'ry@qq.com', N'0', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime))
INSERT [dbo].[sys_dept] ([dept_id], [parent_id], [ancestors], [dept_name], [order_num], [leader], [phone], [email], [status], [del_flag], [create_by], [create_time], [update_by], [update_time]) VALUES (6, 2, N'0,1,2', N'测试部门', 3, N'若依', N'15888888888', N'ry@qq.com', N'0', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime))
INSERT [dbo].[sys_dept] ([dept_id], [parent_id], [ancestors], [dept_name], [order_num], [leader], [phone], [email], [status], [del_flag], [create_by], [create_time], [update_by], [update_time]) VALUES (7, 2, N'0,1,2', N'财务部门', 4, N'若依', N'15888888888', N'ry@qq.com', N'0', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime))
INSERT [dbo].[sys_dept] ([dept_id], [parent_id], [ancestors], [dept_name], [order_num], [leader], [phone], [email], [status], [del_flag], [create_by], [create_time], [update_by], [update_time]) VALUES (8, 2, N'0,1,2', N'运维部门', 5, N'若依', N'15888888888', N'ry@qq.com', N'0', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime))
INSERT [dbo].[sys_dept] ([dept_id], [parent_id], [ancestors], [dept_name], [order_num], [leader], [phone], [email], [status], [del_flag], [create_by], [create_time], [update_by], [update_time]) VALUES (9, 3, N'0,1,3', N'市场部门', 1, N'若依', N'15888888888', N'ry@qq.com', N'0', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime))
INSERT [dbo].[sys_dept] ([dept_id], [parent_id], [ancestors], [dept_name], [order_num], [leader], [phone], [email], [status], [del_flag], [create_by], [create_time], [update_by], [update_time]) VALUES (10, 3, N'0,1,3', N'财务部门', 2, N'若依', N'15888888888', N'ry@qq.com', N'0', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime))
SET IDENTITY_INSERT [dbo].[sys_dept] OFF
SET IDENTITY_INSERT [dbo].[sys_dict_data] ON 

INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (1, 1, N'男', N'0', N'sys_user_sex', N'', N'', N'Y', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'性别男')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (2, 2, N'女', N'1', N'sys_user_sex', N'', N'', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'性别女')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (3, 3, N'未知', N'2', N'sys_user_sex', N'', N'', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'性别未知')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (4, 1, N'显示', N'0', N'sys_show_hide', N'', N'primary', N'Y', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'显示菜单')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (5, 2, N'隐藏', N'1', N'sys_show_hide', N'', N'danger', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'隐藏菜单')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (6, 1, N'正常', N'0', N'sys_normal_disable', N'', N'primary', N'Y', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'正常状态')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (7, 2, N'停用', N'1', N'sys_normal_disable', N'', N'danger', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'停用状态')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (8, 1, N'正常', N'0', N'sys_job_status', N'', N'primary', N'Y', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'正常状态')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (9, 2, N'暂停', N'1', N'sys_job_status', N'', N'danger', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'停用状态')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (10, 1, N'默认', N'DEFAULT', N'sys_job_group', N'', N'', N'Y', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'默认分组')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (11, 2, N'系统', N'SYSTEM', N'sys_job_group', N'', N'', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'系统分组')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (12, 1, N'是', N'Y', N'sys_yes_no', N'', N'primary', N'Y', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'系统默认是')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (13, 2, N'否', N'N', N'sys_yes_no', N'', N'danger', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'系统默认否')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (14, 1, N'通知', N'1', N'sys_notice_type', N'', N'warning', N'Y', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'通知')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (15, 2, N'公告', N'2', N'sys_notice_type', N'', N'success', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'公告')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (16, 1, N'正常', N'0', N'sys_notice_status', N'', N'primary', N'Y', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'正常状态')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (17, 2, N'关闭', N'1', N'sys_notice_status', N'', N'danger', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'关闭状态')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (18, 99, N'其他', N'0', N'sys_oper_type', N'', N'info', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'其他操作')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (19, 1, N'新增', N'1', N'sys_oper_type', N'', N'info', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'新增操作')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (20, 2, N'修改', N'2', N'sys_oper_type', N'', N'info', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'修改操作')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (21, 3, N'删除', N'3', N'sys_oper_type', N'', N'danger', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'删除操作')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (22, 4, N'授权', N'4', N'sys_oper_type', N'', N'primary', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'授权操作')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (23, 5, N'导出', N'5', N'sys_oper_type', N'', N'warning', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'导出操作')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (24, 6, N'导入', N'6', N'sys_oper_type', N'', N'warning', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'导入操作')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (25, 7, N'强退', N'7', N'sys_oper_type', N'', N'danger', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'强退操作')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (26, 8, N'生成代码', N'8', N'sys_oper_type', N'', N'warning', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'生成操作')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (27, 9, N'清空数据', N'9', N'sys_oper_type', N'', N'danger', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'清空操作')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (28, 1, N'成功', N'0', N'sys_common_status', N'', N'primary', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'正常状态')
INSERT [dbo].[sys_dict_data] ([dict_code], [dict_sort], [dict_label], [dict_value], [dict_type], [css_class], [list_class], [is_default], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (29, 2, N'失败', N'1', N'sys_common_status', N'', N'danger', N'N', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'停用状态')
SET IDENTITY_INSERT [dbo].[sys_dict_data] OFF
SET IDENTITY_INSERT [dbo].[sys_dict_type] ON 

INSERT [dbo].[sys_dict_type] ([dict_id], [dict_name], [dict_type], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (1, N'用户性别', N'sys_user_sex', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'用户性别列表')
INSERT [dbo].[sys_dict_type] ([dict_id], [dict_name], [dict_type], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (2, N'菜单状态', N'sys_show_hide', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'菜单状态列表')
INSERT [dbo].[sys_dict_type] ([dict_id], [dict_name], [dict_type], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (3, N'系统开关', N'sys_normal_disable', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'系统开关列表')
INSERT [dbo].[sys_dict_type] ([dict_id], [dict_name], [dict_type], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (4, N'任务状态', N'sys_job_status', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'任务状态列表')
INSERT [dbo].[sys_dict_type] ([dict_id], [dict_name], [dict_type], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (5, N'任务分组', N'sys_job_group', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'任务分组列表')
INSERT [dbo].[sys_dict_type] ([dict_id], [dict_name], [dict_type], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (6, N'系统是否', N'sys_yes_no', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'系统是否列表')
INSERT [dbo].[sys_dict_type] ([dict_id], [dict_name], [dict_type], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (7, N'通知类型', N'sys_notice_type', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'通知类型列表')
INSERT [dbo].[sys_dict_type] ([dict_id], [dict_name], [dict_type], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (8, N'通知状态', N'sys_notice_status', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'通知状态列表')
INSERT [dbo].[sys_dict_type] ([dict_id], [dict_name], [dict_type], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (9, N'操作类型', N'sys_oper_type', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'操作类型列表')
INSERT [dbo].[sys_dict_type] ([dict_id], [dict_name], [dict_type], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (10, N'系统状态', N'sys_common_status', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'登录状态列表')
SET IDENTITY_INSERT [dbo].[sys_dict_type] OFF
SET IDENTITY_INSERT [dbo].[sys_job] ON 

INSERT [dbo].[sys_job] ([job_id], [job_name], [job_group], [invoke_target], [cron_expression], [misfire_policy], [concurrent], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (1, N'系统默认（无参）', N'DEFAULT', N'ryTask.ryNoParams', N'0/10 * * * * ?', N'3', N'1', N'1', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_job] ([job_id], [job_name], [job_group], [invoke_target], [cron_expression], [misfire_policy], [concurrent], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (2, N'系统默认（有参）', N'DEFAULT', N'ryTask.ryParams(''ry'')', N'0/15 * * * * ?', N'3', N'1', N'1', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_job] ([job_id], [job_name], [job_group], [invoke_target], [cron_expression], [misfire_policy], [concurrent], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (3, N'系统默认（多参）', N'DEFAULT', N'ryTask.ryMultipleParams(''ry'', true, 2000L, 316.50D, 100)', N'0/20 * * * * ?', N'3', N'1', N'1', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
SET IDENTITY_INSERT [dbo].[sys_job] OFF
SET IDENTITY_INSERT [dbo].[sys_logininfor] ON 

INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (1, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC050104FEA2 AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (2, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC0501093752 AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (3, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC05010A01DF AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (4, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC05011442E2 AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (5, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC05011D5832 AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (6, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC050121E724 AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (7, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC7700ADA46D AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (8, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'1', N'验证码错误', CAST(0x0000AC7701245493 AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (9, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'1', N'验证码错误', CAST(0x0000AC7701245970 AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (10, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC7701245F66 AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (11, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC7800AF753E AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (12, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC7800C0AD3C AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (13, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC7800C164B0 AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (14, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'1', N'验证码错误', CAST(0x0000AC7800C1C7ED AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (15, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'1', N'验证码错误', CAST(0x0000AC7800C1CCC2 AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (16, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC7800C1D1D7 AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (17, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC7800E4BE5B AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (18, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'1', N'验证码错误', CAST(0x0000AC7800E8011C AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (19, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC7800E8084F AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (20, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC7800F25821 AS DateTime))
INSERT [dbo].[sys_logininfor] ([info_id], [login_name], [ipaddr], [login_location], [browser], [os], [status], [msg], [login_time]) VALUES (21, N'admin', N'127.0.0.1', N'内网IP', N'Chrome', N'Windows 10', N'0', N'登录成功', CAST(0x0000AC8200D1C9AC AS DateTime))
SET IDENTITY_INSERT [dbo].[sys_logininfor] OFF
SET IDENTITY_INSERT [dbo].[sys_menu] ON 

INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (1, N'系统管理', 0, 1, N'#', N'', N'M', N'0', N'', N'fa fa-gear', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'系统管理目录')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (2, N'系统监控', 0, 2, N'#', N'', N'M', N'0', N'', N'fa fa-video-camera', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'系统监控目录')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (3, N'系统工具', 0, 3, N'#', N'', N'M', N'0', N'', N'fa fa-bars', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'系统工具目录')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (4, N'用户管理', 1, 1, N'/admin/system/user', N'', N'C', N'0', N'system:user:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'用户管理菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (5, N'角色管理', 1, 2, N'/admin/system/role', N'', N'C', N'0', N'system:role:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'角色管理菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (6, N'菜单管理', 1, 3, N'/admin/system/menu', N'', N'C', N'0', N'system:menu:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'菜单管理菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (7, N'部门管理', 1, 4, N'/admin/system/dept', N'', N'C', N'0', N'system:dept:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'部门管理菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (8, N'岗位管理', 1, 5, N'/admin/system/post', N'', N'C', N'0', N'system:post:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'岗位管理菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (9, N'字典管理', 1, 6, N'/admin/system/dict', N'', N'C', N'0', N'system:dict:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'字典管理菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (10, N'参数设置', 1, 7, N'/admin/system/config', N'', N'C', N'0', N'system:config:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'参数设置菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (11, N'通知公告', 1, 8, N'/admin/system/notice', N'', N'C', N'0', N'system:notice:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'通知公告菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (12, N'日志管理', 1, 9, N'#', N'', N'M', N'0', N'', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'日志管理菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (13, N'在线用户', 2, 1, N'/admin/monitor/online', N'', N'C', N'0', N'monitor:online:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'在线用户菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (14, N'定时任务', 2, 2, N'/admin/monitor/job', N'', N'C', N'0', N'monitor:job:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'定时任务菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (15, N'数据监控', 2, 3, N'/admin/monitor/data', N'', N'C', N'0', N'monitor:data:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'数据监控菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (16, N'服务监控', 2, 3, N'/admin/monitor/server', N'', N'C', N'0', N'monitor:server:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'服务监控菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (17, N'表单构建', 3, 1, N'/admin/tool/build', N'', N'C', N'0', N'tool:build:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'表单构建菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (18, N'代码生成', 3, 2, N'/admin/tool/gen', N'', N'C', N'0', N'tool:gen:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'代码生成菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (19, N'系统接口', 3, 3, N'/admin/tool/swagger', N'', N'C', N'0', N'tool:swagger:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'系统接口菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (20, N'操作日志', 12, 1, N'/admin/monitor/operlog', N'', N'C', N'0', N'monitor:operlog:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'操作日志菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (21, N'登录日志', 12, 2, N'/admin/monitor/logininfor', N'', N'C', N'0', N'monitor:logininfor:view', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'登录日志菜单')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (22, N'用户查询', 100, 1, N'#', N'', N'F', N'0', N'system:user:list', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (23, N'用户新增', 100, 2, N'#', N'', N'F', N'0', N'system:user:add', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (24, N'用户修改', 100, 3, N'#', N'', N'F', N'0', N'system:user:edit', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (25, N'用户删除', 100, 4, N'#', N'', N'F', N'0', N'system:user:remove', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (26, N'用户导出', 100, 5, N'#', N'', N'F', N'0', N'system:user:export', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (27, N'用户导入', 100, 6, N'#', N'', N'F', N'0', N'system:user:import', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (28, N'重置密码', 100, 7, N'#', N'', N'F', N'0', N'system:user:resetPwd', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (29, N'角色查询', 101, 1, N'#', N'', N'F', N'0', N'system:role:list', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (30, N'角色新增', 101, 2, N'#', N'', N'F', N'0', N'system:role:add', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (31, N'角色修改', 101, 3, N'#', N'', N'F', N'0', N'system:role:edit', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (32, N'角色删除', 101, 4, N'#', N'', N'F', N'0', N'system:role:remove', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (33, N'角色导出', 101, 5, N'#', N'', N'F', N'0', N'system:role:export', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (34, N'菜单查询', 102, 1, N'#', N'', N'F', N'0', N'system:menu:list', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (35, N'菜单新增', 102, 2, N'#', N'', N'F', N'0', N'system:menu:add', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (36, N'菜单修改', 102, 3, N'#', N'', N'F', N'0', N'system:menu:edit', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (37, N'菜单删除', 102, 4, N'#', N'', N'F', N'0', N'system:menu:remove', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (38, N'部门查询', 103, 1, N'#', N'', N'F', N'0', N'system:dept:list', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (39, N'部门新增', 103, 2, N'#', N'', N'F', N'0', N'system:dept:add', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (40, N'部门修改', 103, 3, N'#', N'', N'F', N'0', N'system:dept:edit', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (41, N'部门删除', 103, 4, N'#', N'', N'F', N'0', N'system:dept:remove', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (42, N'岗位查询', 104, 1, N'#', N'', N'F', N'0', N'system:post:list', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (43, N'岗位新增', 104, 2, N'#', N'', N'F', N'0', N'system:post:add', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (44, N'岗位修改', 104, 3, N'#', N'', N'F', N'0', N'system:post:edit', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (45, N'岗位删除', 104, 4, N'#', N'', N'F', N'0', N'system:post:remove', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (46, N'岗位导出', 104, 5, N'#', N'', N'F', N'0', N'system:post:export', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (47, N'字典查询', 105, 1, N'#', N'', N'F', N'0', N'system:dict:list', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (48, N'字典新增', 105, 2, N'#', N'', N'F', N'0', N'system:dict:add', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (49, N'字典修改', 105, 3, N'#', N'', N'F', N'0', N'system:dict:edit', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (50, N'字典删除', 105, 4, N'#', N'', N'F', N'0', N'system:dict:remove', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (51, N'字典导出', 105, 5, N'#', N'', N'F', N'0', N'system:dict:export', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (52, N'参数查询', 106, 1, N'#', N'', N'F', N'0', N'system:config:list', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (53, N'参数新增', 106, 2, N'#', N'', N'F', N'0', N'system:config:add', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (54, N'参数修改', 106, 3, N'#', N'', N'F', N'0', N'system:config:edit', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (55, N'参数删除', 106, 4, N'#', N'', N'F', N'0', N'system:config:remove', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (56, N'参数导出', 106, 5, N'#', N'', N'F', N'0', N'system:config:export', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (57, N'公告查询', 107, 1, N'#', N'', N'F', N'0', N'system:notice:list', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (58, N'公告新增', 107, 2, N'#', N'', N'F', N'0', N'system:notice:add', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (59, N'公告修改', 107, 3, N'#', N'', N'F', N'0', N'system:notice:edit', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (60, N'公告删除', 107, 4, N'#', N'', N'F', N'0', N'system:notice:remove', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (61, N'操作查询', 500, 1, N'#', N'', N'F', N'0', N'monitor:operlog:list', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (62, N'操作删除', 500, 2, N'#', N'', N'F', N'0', N'monitor:operlog:remove', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (63, N'详细信息', 500, 3, N'#', N'', N'F', N'0', N'monitor:operlog:detail', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (64, N'日志导出', 500, 4, N'#', N'', N'F', N'0', N'monitor:operlog:export', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (65, N'登录查询', 501, 1, N'#', N'', N'F', N'0', N'monitor:logininfor:list', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (66, N'登录删除', 501, 2, N'#', N'', N'F', N'0', N'monitor:logininfor:remove', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (67, N'日志导出', 501, 3, N'#', N'', N'F', N'0', N'monitor:logininfor:export', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (68, N'账户解锁', 501, 4, N'#', N'', N'F', N'0', N'monitor:logininfor:unlock', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (69, N'在线查询', 109, 1, N'#', N'', N'F', N'0', N'monitor:online:list', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (70, N'批量强退', 109, 2, N'#', N'', N'F', N'0', N'monitor:online:batchForceLogout', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (71, N'单条强退', 109, 3, N'#', N'', N'F', N'0', N'monitor:online:forceLogout', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (72, N'任务查询', 110, 1, N'#', N'', N'F', N'0', N'monitor:job:list', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (73, N'任务新增', 110, 2, N'#', N'', N'F', N'0', N'monitor:job:add', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (74, N'任务修改', 110, 3, N'#', N'', N'F', N'0', N'monitor:job:edit', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (75, N'任务删除', 110, 4, N'#', N'', N'F', N'0', N'monitor:job:remove', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (76, N'状态修改', 110, 5, N'#', N'', N'F', N'0', N'monitor:job:changeStatus', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (77, N'任务详细', 110, 6, N'#', N'', N'F', N'0', N'monitor:job:detail', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (78, N'任务导出', 110, 7, N'#', N'', N'F', N'0', N'monitor:job:export', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (79, N'生成查询', 114, 1, N'#', N'', N'F', N'0', N'tool:gen:list', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (80, N'生成修改', 114, 2, N'#', N'', N'F', N'0', N'tool:gen:edit', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (81, N'生成删除', 114, 3, N'#', N'', N'F', N'0', N'tool:gen:remove', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (82, N'预览代码', 114, 4, N'#', N'', N'F', N'0', N'tool:gen:preview', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_menu] ([menu_id], [menu_name], [parent_id], [order_num], [url], [target], [menu_type], [visible], [perms], [icon], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (83, N'生成代码', 114, 5, N'#', N'', N'F', N'0', N'tool:gen:code', N'#', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
SET IDENTITY_INSERT [dbo].[sys_menu] OFF
SET IDENTITY_INSERT [dbo].[sys_notice] ON 

INSERT [dbo].[sys_notice] ([notice_id], [notice_title], [notice_type], [notice_content], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (1, N'温馨提醒：2018-07-01 若依新版本发布啦', N'2', N'新版本内容', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'管理员')
INSERT [dbo].[sys_notice] ([notice_id], [notice_title], [notice_type], [notice_content], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (2, N'维护通知：2018-07-01 若依系统凌晨维护', N'1', N'维护内容', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'管理员')
SET IDENTITY_INSERT [dbo].[sys_notice] OFF
SET IDENTITY_INSERT [dbo].[sys_oper_log] ON 

INSERT [dbo].[sys_oper_log] ([oper_id], [title], [business_type], [method], [request_method], [operator_type], [oper_name], [dept_name], [oper_url], [oper_ip], [oper_location], [oper_param], [json_result], [status], [error_msg], [oper_time]) VALUES (1, N'菜单管理', 2, N'com.afeng.web.module.admin.controller.MenuController.editSave()', N'POST', 1, N'admin', NULL, N'/admin/system/menu/edit', N'192.168.2.107', N'内网IP', N'{"menuId":["84"],"parentId":["0"],"menuType":["M"],"menuName":["微信管理"],"url":["#"],"target":["menuItem"],"perms":[""],"orderNum":["1"],"icon":["fa fa-comments"],"visible":["0"]}', N'{"msg":"操作成功","code":0}', 0, NULL, CAST(0x0000AC78010D9E92 AS DateTime))
INSERT [dbo].[sys_oper_log] ([oper_id], [title], [business_type], [method], [request_method], [operator_type], [oper_name], [dept_name], [oper_url], [oper_ip], [oper_location], [oper_param], [json_result], [status], [error_msg], [oper_time]) VALUES (2, N'菜单管理', 1, N'com.afeng.web.module.admin.controller.MenuController.addSave()', N'POST', 1, N'admin', NULL, N'/admin/system/menu/add', N'192.168.2.107', N'内网IP', N'{"parentId":["84"],"menuType":["M"],"menuName":["人数统计"],"url":[""],"target":["menuItem"],"perms":[""],"orderNum":["0"],"icon":["fa fa-address-book"],"visible":["0"]}', N'{"msg":"新增菜单''人数统计''失败，菜单名称已存在","code":500}', 0, NULL, CAST(0x0000AC780116D6B3 AS DateTime))
INSERT [dbo].[sys_oper_log] ([oper_id], [title], [business_type], [method], [request_method], [operator_type], [oper_name], [dept_name], [oper_url], [oper_ip], [oper_location], [oper_param], [json_result], [status], [error_msg], [oper_time]) VALUES (3, N'菜单管理', 1, N'com.afeng.web.module.admin.controller.MenuController.addSave()', N'POST', 1, N'admin', NULL, N'/admin/system/menu/add', N'192.168.2.107', N'内网IP', N'{"parentId":["84"],"menuType":["M"],"menuName":["人数统计"],"url":[""],"target":["menuItem"],"perms":[""],"orderNum":["0"],"icon":["fa fa-address-book"],"visible":["0"]}', N'{"msg":"操作成功","code":0}', 0, NULL, CAST(0x0000AC780117A995 AS DateTime))
INSERT [dbo].[sys_oper_log] ([oper_id], [title], [business_type], [method], [request_method], [operator_type], [oper_name], [dept_name], [oper_url], [oper_ip], [oper_location], [oper_param], [json_result], [status], [error_msg], [oper_time]) VALUES (4, N'菜单管理', 2, N'com.afeng.web.module.admin.controller.MenuController.editSave()', N'POST', 1, N'admin', NULL, N'/admin/system/menu/edit', N'192.168.2.107', N'内网IP', N'{"menuId":["85"],"parentId":["84"],"menuType":["C"],"menuName":["人数统计"],"url":["#"],"target":["menuItem"],"perms":["wx:user:statistics"],"orderNum":["0"],"icon":["fa fa-address-book"],"visible":["0"]}', N'{"msg":"操作成功","code":0}', 0, NULL, CAST(0x0000AC78011822EA AS DateTime))
INSERT [dbo].[sys_oper_log] ([oper_id], [title], [business_type], [method], [request_method], [operator_type], [oper_name], [dept_name], [oper_url], [oper_ip], [oper_location], [oper_param], [json_result], [status], [error_msg], [oper_time]) VALUES (5, N'菜单管理', 2, N'com.afeng.web.module.admin.controller.MenuController.editSave()', N'POST', 1, N'admin', NULL, N'/admin/system/menu/edit', N'192.168.2.107', N'内网IP', N'{"menuId":["85"],"parentId":["84"],"menuType":["C"],"menuName":["人数统计"],"url":["/admin/wx/index"],"target":["menuItem"],"perms":["wx:user:index"],"orderNum":["0"],"icon":["fa fa-address-book"],"visible":["0"]}', N'{"msg":"操作成功","code":0}', 0, NULL, CAST(0x0000AC780118A56E AS DateTime))
SET IDENTITY_INSERT [dbo].[sys_oper_log] OFF
SET IDENTITY_INSERT [dbo].[sys_post] ON 

INSERT [dbo].[sys_post] ([post_id], [post_code], [post_name], [post_sort], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (1, N'ceo', N'董事长', 1, N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_post] ([post_id], [post_code], [post_name], [post_sort], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (2, N'se', N'项目经理', 2, N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_post] ([post_id], [post_code], [post_name], [post_sort], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (3, N'hr', N'人力资源', 3, N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
INSERT [dbo].[sys_post] ([post_id], [post_code], [post_name], [post_sort], [status], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (4, N'user', N'普通员工', 4, N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'')
SET IDENTITY_INSERT [dbo].[sys_post] OFF
SET IDENTITY_INSERT [dbo].[sys_role] ON 

INSERT [dbo].[sys_role] ([role_id], [role_name], [role_key], [role_sort], [data_scope], [status], [create_by], [create_time], [update_by], [update_time], [remark], [del_flag]) VALUES (1, N'管理员', N'admin', 1, N'1', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'管理员', N'0')
INSERT [dbo].[sys_role] ([role_id], [role_name], [role_key], [role_sort], [data_scope], [status], [create_by], [create_time], [update_by], [update_time], [remark], [del_flag]) VALUES (2, N'普通角色', N'common', 2, N'2', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'普通角色', N'0')
SET IDENTITY_INSERT [dbo].[sys_role] OFF
INSERT [dbo].[sys_role_dept] ([role_id], [dept_id]) VALUES (2, 100)
INSERT [dbo].[sys_role_dept] ([role_id], [dept_id]) VALUES (2, 101)
INSERT [dbo].[sys_role_dept] ([role_id], [dept_id]) VALUES (2, 105)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 2)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 3)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 100)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 101)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 102)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 103)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 104)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 105)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 106)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 107)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 108)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 109)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 110)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 111)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 112)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 113)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 114)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 115)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 500)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 501)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1000)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1001)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1002)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1003)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1004)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1005)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1006)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1007)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1008)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1009)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1010)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1011)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1012)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1013)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1014)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1015)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1016)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1017)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1018)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1019)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1020)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1021)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1022)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1023)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1024)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1025)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1026)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1027)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1028)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1029)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1030)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1031)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1032)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1033)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1034)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1035)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1036)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1037)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1038)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1039)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1040)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1041)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1042)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1043)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1044)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1045)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1046)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1047)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1048)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1049)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1050)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1051)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1052)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1053)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1054)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1055)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1056)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1057)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1058)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1059)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1060)
INSERT [dbo].[sys_role_menu] ([role_id], [menu_id]) VALUES (2, 1061)
SET IDENTITY_INSERT [dbo].[sys_user] ON 

INSERT [dbo].[sys_user] ([user_id], [dept_id], [login_name], [user_name], [user_type], [email], [phonenumber], [sex], [avatar], [password], [salt], [status], [create_by], [create_time], [update_by], [update_time], [remark], [del_flag], [login_ip], [login_date]) VALUES (1, 103, N'admin', N'若依', N'00', N'ry@163.com', N'15888888888', N'1', N'', N'admin123', N'111111', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000AC8200D1C9B5 AS DateTime), N'管理员', N'0', N'127.0.0.1', CAST(0x0000AC8200D1C9A2 AS DateTime))
INSERT [dbo].[sys_user] ([user_id], [dept_id], [login_name], [user_name], [user_type], [email], [phonenumber], [sex], [avatar], [password], [salt], [status], [create_by], [create_time], [update_by], [update_time], [remark], [del_flag], [login_ip], [login_date]) VALUES (2, 105, N'ry', N'若依', N'00', N'ry@qq.com', N'15666666666', N'1', N'', N'8e6d98b90472783cc73c17047ddccf36', N'222222', N'0', N'admin', CAST(0x0000A8A500BE5690 AS DateTime), N'ry', CAST(0x0000A8A500BE5690 AS DateTime), N'测试员', N'0', N'127.0.0.1', CAST(0x0000A8A500BE5690 AS DateTime))
SET IDENTITY_INSERT [dbo].[sys_user] OFF
INSERT [dbo].[sys_user_post] ([user_id], [post_id]) VALUES (1, 1)
INSERT [dbo].[sys_user_post] ([user_id], [post_id]) VALUES (2, 2)
INSERT [dbo].[sys_user_role] ([user_id], [role_id]) VALUES (1, 1)
INSERT [dbo].[sys_user_role] ([user_id], [role_id]) VALUES (2, 2)
ALTER TABLE [dbo].[gen_table] ADD  CONSTRAINT [DF_gen_table_tpl_category]  DEFAULT ('crud') FOR [tpl_category]
GO
ALTER TABLE [dbo].[gen_table_column] ADD  CONSTRAINT [DF_gen_table_column_query_type]  DEFAULT ('EQ') FOR [query_type]
GO
ALTER TABLE [dbo].[sys_dept] ADD  CONSTRAINT [DF_sys_dept_parent_id]  DEFAULT ((0)) FOR [parent_id]
GO
ALTER TABLE [dbo].[sys_dept] ADD  CONSTRAINT [DF_sys_dept_order_num]  DEFAULT ((0)) FOR [order_num]
GO
ALTER TABLE [dbo].[sys_dept] ADD  CONSTRAINT [DF_sys_dept_status]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[sys_dept] ADD  CONSTRAINT [DF_sys_dept_del_flag]  DEFAULT ((0)) FOR [del_flag]
GO
ALTER TABLE [dbo].[sys_dict_data] ADD  CONSTRAINT [DF_sys_dict_data_is_default]  DEFAULT ('N') FOR [is_default]
GO
ALTER TABLE [dbo].[sys_dict_data] ADD  CONSTRAINT [DF_sys_dict_data_status]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[sys_dict_type] ADD  CONSTRAINT [DF_sys_dict_type_status]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[sys_job] ADD  CONSTRAINT [DF_sys_job_misfire_policy]  DEFAULT ((3)) FOR [misfire_policy]
GO
ALTER TABLE [dbo].[sys_job] ADD  CONSTRAINT [DF_sys_job_concurrent]  DEFAULT ((1)) FOR [concurrent]
GO
ALTER TABLE [dbo].[sys_job] ADD  CONSTRAINT [DF_sys_job_status]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[sys_job_log] ADD  CONSTRAINT [DF_sys_job_log_status]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[sys_logininfor] ADD  CONSTRAINT [DF_sys_logininfor_status]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[sys_menu] ADD  CONSTRAINT [DF_sys_menu_parent_id]  DEFAULT ((0)) FOR [parent_id]
GO
ALTER TABLE [dbo].[sys_menu] ADD  CONSTRAINT [DF_sys_menu_order_num]  DEFAULT ((0)) FOR [order_num]
GO
ALTER TABLE [dbo].[sys_menu] ADD  CONSTRAINT [DF_sys_menu_url]  DEFAULT ('#') FOR [url]
GO
ALTER TABLE [dbo].[sys_menu] ADD  CONSTRAINT [DF_sys_menu_visible]  DEFAULT ((0)) FOR [visible]
GO
ALTER TABLE [dbo].[sys_menu] ADD  CONSTRAINT [DF_sys_menu_icon]  DEFAULT ('#') FOR [icon]
GO
ALTER TABLE [dbo].[sys_notice] ADD  CONSTRAINT [DF_sys_notice_status]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[sys_oper_log] ADD  CONSTRAINT [DF_sys_oper_log_business_type]  DEFAULT ((0)) FOR [business_type]
GO
ALTER TABLE [dbo].[sys_oper_log] ADD  CONSTRAINT [DF_sys_oper_log_operator_type]  DEFAULT ((0)) FOR [operator_type]
GO
ALTER TABLE [dbo].[sys_oper_log] ADD  CONSTRAINT [DF_sys_oper_log_status]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[sys_post] ADD  CONSTRAINT [DF_sys_post_status]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[sys_role] ADD  CONSTRAINT [DF_sys_role_data_scope]  DEFAULT ((1)) FOR [data_scope]
GO
ALTER TABLE [dbo].[sys_role] ADD  CONSTRAINT [DF_sys_role_status]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[sys_role] ADD  CONSTRAINT [DF_sys_role_del_flag]  DEFAULT ((0)) FOR [del_flag]
GO
ALTER TABLE [dbo].[sys_user] ADD  CONSTRAINT [DF_sys_user_user_type]  DEFAULT ((0)) FOR [user_type]
GO
ALTER TABLE [dbo].[sys_user] ADD  CONSTRAINT [DF_sys_user_sex]  DEFAULT ((0)) FOR [sex]
GO
ALTER TABLE [dbo].[sys_user] ADD  CONSTRAINT [DF_sys_user_status]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[sys_user] ADD  CONSTRAINT [DF_sys_user_del_flag]  DEFAULT ((0)) FOR [del_flag]
GO
ALTER TABLE [dbo].[sys_user_online] ADD  CONSTRAINT [DF_sys_user_online_expire_time]  DEFAULT ((0)) FOR [expire_time]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'微信小程序openid' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'app_user', @level2type=N'COLUMN',@level2name=N'minaopenid'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'微信第三方登录openid' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'app_user', @level2type=N'COLUMN',@level2name=N'wxopenid'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'微信公众号openid' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'app_user', @level2type=N'COLUMN',@level2name=N'mpopenid'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'腾讯用户ID，只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'app_user', @level2type=N'COLUMN',@level2name=N'unionid'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户的昵称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'app_user', @level2type=N'COLUMN',@level2name=N'nickname'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户的性别，值为1时是男性，值为2时是女性，值为0时是未知' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'app_user', @level2type=N'COLUMN',@level2name=N'sex'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户所在城市' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'app_user', @level2type=N'COLUMN',@level2name=N'city'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户所在国家' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'app_user', @level2type=N'COLUMN',@level2name=N'country'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户所在省份' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'app_user', @level2type=N'COLUMN',@level2name=N'province'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户头像' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'app_user', @level2type=N'COLUMN',@level2name=N'headimgurl'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户前端标识码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'app_user', @level2type=N'COLUMN',@level2name=N'usercode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'APP用户' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'app_user'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'table_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'table_comment'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'实体类名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'class_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用的模板（crud单表操作 tree树表操作）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'tpl_category'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'生成包路径' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'package_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'生成模块名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'module_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'生成业务名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'business_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'生成功能名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'function_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'生成功能作者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'function_author'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'其它生成选项' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'options'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'update_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table', @level2type=N'COLUMN',@level2name=N'remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'代码生成业务表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'归属表编号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'table_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'列名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'column_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'列描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'column_comment'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'列类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'column_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'JAVA类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'java_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'JAVA字段名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'java_field'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否主键（1是）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'is_pk'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否自增（1是）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'is_increment'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否必填（1是）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'is_required'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否为插入字段（1是）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'is_insert'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否编辑字段（1是）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'is_edit'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否列表字段（1是）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'is_list'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否查询字段（1是）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'is_query'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'查询方式（等于、不等于、大于、小于、范围）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'query_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'html_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字典类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'dict_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'排序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'sort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'update_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column', @level2type=N'COLUMN',@level2name=N'update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'代码生成业务表字段' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'gen_table_column'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参数主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_config', @level2type=N'COLUMN',@level2name=N'config_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参数名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_config', @level2type=N'COLUMN',@level2name=N'config_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参数键名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_config', @level2type=N'COLUMN',@level2name=N'config_key'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参数键值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_config', @level2type=N'COLUMN',@level2name=N'config_value'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系统内置（Y是 N否）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_config', @level2type=N'COLUMN',@level2name=N'config_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_config', @level2type=N'COLUMN',@level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_config', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_config', @level2type=N'COLUMN',@level2name=N'update_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_config', @level2type=N'COLUMN',@level2name=N'update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_config', @level2type=N'COLUMN',@level2name=N'remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参数配置表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_config'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'部门id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept', @level2type=N'COLUMN',@level2name=N'dept_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'父部门id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept', @level2type=N'COLUMN',@level2name=N'parent_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'祖级列表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept', @level2type=N'COLUMN',@level2name=N'ancestors'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'部门名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept', @level2type=N'COLUMN',@level2name=N'dept_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'显示顺序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept', @level2type=N'COLUMN',@level2name=N'order_num'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'负责人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept', @level2type=N'COLUMN',@level2name=N'leader'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'联系电话' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept', @level2type=N'COLUMN',@level2name=N'phone'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'邮箱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept', @level2type=N'COLUMN',@level2name=N'email'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'部门状态（0正常 1停用）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标志（0代表存在 2代表删除）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept', @level2type=N'COLUMN',@level2name=N'del_flag'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept', @level2type=N'COLUMN',@level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept', @level2type=N'COLUMN',@level2name=N'update_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept', @level2type=N'COLUMN',@level2name=N'update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'部门表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dept'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字典编码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data', @level2type=N'COLUMN',@level2name=N'dict_code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字典排序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data', @level2type=N'COLUMN',@level2name=N'dict_sort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字典标签' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data', @level2type=N'COLUMN',@level2name=N'dict_label'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字典键值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data', @level2type=N'COLUMN',@level2name=N'dict_value'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字典类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data', @level2type=N'COLUMN',@level2name=N'dict_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'样式属性（其他样式扩展）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data', @level2type=N'COLUMN',@level2name=N'css_class'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表格回显样式' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data', @level2type=N'COLUMN',@level2name=N'list_class'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否默认（Y是 N否）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data', @level2type=N'COLUMN',@level2name=N'is_default'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'状态（0正常 1停用）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data', @level2type=N'COLUMN',@level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data', @level2type=N'COLUMN',@level2name=N'update_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data', @level2type=N'COLUMN',@level2name=N'update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data', @level2type=N'COLUMN',@level2name=N'remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字典数据表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_data'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字典名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_type', @level2type=N'COLUMN',@level2name=N'dict_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字典类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_type', @level2type=N'COLUMN',@level2name=N'dict_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'状态（0正常 1停用）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_type', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_type', @level2type=N'COLUMN',@level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_type', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_type', @level2type=N'COLUMN',@level2name=N'update_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_type', @level2type=N'COLUMN',@level2name=N'update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_type', @level2type=N'COLUMN',@level2name=N'remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字典类型表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_dict_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job', @level2type=N'COLUMN',@level2name=N'job_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job', @level2type=N'COLUMN',@level2name=N'job_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务组名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job', @level2type=N'COLUMN',@level2name=N'job_group'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'调用目标字符串' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job', @level2type=N'COLUMN',@level2name=N'invoke_target'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'cron执行表达式' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job', @level2type=N'COLUMN',@level2name=N'cron_expression'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'计划执行错误策略（1立即执行 2执行一次 3放弃执行）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job', @level2type=N'COLUMN',@level2name=N'misfire_policy'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否并发执行（0允许 1禁止）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job', @level2type=N'COLUMN',@level2name=N'concurrent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'状态（0正常 1暂停）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job', @level2type=N'COLUMN',@level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job', @level2type=N'COLUMN',@level2name=N'update_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job', @level2type=N'COLUMN',@level2name=N'update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job', @level2type=N'COLUMN',@level2name=N'remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'定时任务调度表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务日志ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job_log', @level2type=N'COLUMN',@level2name=N'job_log_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job_log', @level2type=N'COLUMN',@level2name=N'job_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务组名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job_log', @level2type=N'COLUMN',@level2name=N'job_group'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'调用目标字符串' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job_log', @level2type=N'COLUMN',@level2name=N'invoke_target'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'日志信息' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job_log', @level2type=N'COLUMN',@level2name=N'job_message'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'执行状态（0正常 1失败）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job_log', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'异常信息' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job_log', @level2type=N'COLUMN',@level2name=N'exception_info'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job_log', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'定时任务调度日志表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_job_log'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'访问ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_logininfor', @level2type=N'COLUMN',@level2name=N'info_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'登录账号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_logininfor', @level2type=N'COLUMN',@level2name=N'login_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'登录IP地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_logininfor', @level2type=N'COLUMN',@level2name=N'ipaddr'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'登录地点' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_logininfor', @level2type=N'COLUMN',@level2name=N'login_location'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'浏览器类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_logininfor', @level2type=N'COLUMN',@level2name=N'browser'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作系统' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_logininfor', @level2type=N'COLUMN',@level2name=N'os'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'登录状态（0成功 1失败）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_logininfor', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'提示消息' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_logininfor', @level2type=N'COLUMN',@level2name=N'msg'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'访问时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_logininfor', @level2type=N'COLUMN',@level2name=N'login_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系统访问记录' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_logininfor'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'菜单ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'menu_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'菜单名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'menu_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'父菜单ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'parent_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'显示顺序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'order_num'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'请求地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'url'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'打开方式（menuItem页签 menuBlank新窗口）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'target'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'菜单类型（M目录 C菜单 F按钮）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'menu_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'菜单状态（0显示 1隐藏）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'visible'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'权限标识' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'perms'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'菜单图标' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'icon'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'update_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu', @level2type=N'COLUMN',@level2name=N'remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'菜单权限表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_menu'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公告ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_notice', @level2type=N'COLUMN',@level2name=N'notice_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公告标题' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_notice', @level2type=N'COLUMN',@level2name=N'notice_title'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公告类型（1通知 2公告）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_notice', @level2type=N'COLUMN',@level2name=N'notice_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公告内容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_notice', @level2type=N'COLUMN',@level2name=N'notice_content'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公告状态（0正常 1关闭）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_notice', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_notice', @level2type=N'COLUMN',@level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_notice', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_notice', @level2type=N'COLUMN',@level2name=N'update_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_notice', @level2type=N'COLUMN',@level2name=N'update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_notice', @level2type=N'COLUMN',@level2name=N'remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'通知公告表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_notice'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'模块标题' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'title'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'业务类型（0其它 1新增 2修改 3删除）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'business_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'方法名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'method'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'请求方式' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'request_method'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作类别（0其它 1后台用户 2手机端用户）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'operator_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作人员' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'oper_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'部门名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'dept_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'请求URL' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'oper_url'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主机地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'oper_ip'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作地点' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'oper_location'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'请求参数' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'oper_param'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'返回参数' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'json_result'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作状态（0正常 1异常）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'错误消息' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'error_msg'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log', @level2type=N'COLUMN',@level2name=N'oper_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作日志记录' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_oper_log'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'岗位ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_post', @level2type=N'COLUMN',@level2name=N'post_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'岗位编码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_post', @level2type=N'COLUMN',@level2name=N'post_code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'岗位名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_post', @level2type=N'COLUMN',@level2name=N'post_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'显示顺序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_post', @level2type=N'COLUMN',@level2name=N'post_sort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'状态（0正常 1暂停）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_post', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_post', @level2type=N'COLUMN',@level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_post', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_post', @level2type=N'COLUMN',@level2name=N'update_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_post', @level2type=N'COLUMN',@level2name=N'update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_post', @level2type=N'COLUMN',@level2name=N'remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'岗位信息表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_post'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'role_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色权限字符串' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'role_key'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'显示顺序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'role_sort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'data_scope'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色状态（0正常 1停用）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'update_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标志（0代表存在 2代表删除）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'del_flag'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色信息表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role_dept', @level2type=N'COLUMN',@level2name=N'role_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'部门ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role_dept', @level2type=N'COLUMN',@level2name=N'dept_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色和部门关联表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role_dept'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role_menu', @level2type=N'COLUMN',@level2name=N'role_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'菜单ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role_menu', @level2type=N'COLUMN',@level2name=N'menu_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色和菜单关联表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role_menu'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'部门ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'dept_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'登录账号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'login_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户昵称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'user_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户类型（00系统用户 01注册用户）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'user_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户邮箱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'email'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'手机号码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'phonenumber'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户性别（0男 1女 2未知）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'sex'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'头像路径' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'avatar'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'密码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'password'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'盐加密' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'salt'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'帐号状态（0正常 1停用）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新者' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'update_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标志（0代表存在 2代表删除）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'del_flag'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最后登陆IP' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'login_ip'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最后登陆时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user', @level2type=N'COLUMN',@level2name=N'login_date'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户信息表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户会话id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_online', @level2type=N'COLUMN',@level2name=N'sessionId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'登录账号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_online', @level2type=N'COLUMN',@level2name=N'login_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'部门名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_online', @level2type=N'COLUMN',@level2name=N'dept_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'登录IP地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_online', @level2type=N'COLUMN',@level2name=N'ipaddr'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'登录地点' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_online', @level2type=N'COLUMN',@level2name=N'login_location'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'浏览器类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_online', @level2type=N'COLUMN',@level2name=N'browser'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作系统' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_online', @level2type=N'COLUMN',@level2name=N'os'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'在线状态on_line在线off_line离线' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_online', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'session创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_online', @level2type=N'COLUMN',@level2name=N'start_timestamp'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'session最后访问时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_online', @level2type=N'COLUMN',@level2name=N'last_access_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'超时时间，单位为分钟' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_online', @level2type=N'COLUMN',@level2name=N'expire_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'在线用户记录' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_online'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_post', @level2type=N'COLUMN',@level2name=N'user_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'岗位ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_post', @level2type=N'COLUMN',@level2name=N'post_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户与岗位关联表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_post'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_role', @level2type=N'COLUMN',@level2name=N'user_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_role', @level2type=N'COLUMN',@level2name=N'role_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户和角色关联表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_user_role'
GO
