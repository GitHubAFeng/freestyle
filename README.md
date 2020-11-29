## 项目简介

曾经有段时间为了搭建一个“好用”的快速开发框架，几乎看遍码云与github平台上排行前列的优秀开源项目，但是没有发现合适的，主要是自己平时以API接口开发为主，而这些开源项目大多数是专精于后台管理系统，相对于用来开发API接口，我认为不够轻量、不够灵活，我希望的是能够帮助我快速地实现接口功能并交付给对接前端，同时还能有个稍为漂亮、易于复用的管理后台给运营或BOSS使用即可！

下一步的计划，把free打造成一款能够平滑无痛地向微服务演化的单应用框架。

free，谐音“飞耶”，自由、随心所欲之意，我希望她能像风一样自由自在无拘束的，也希望她是个有生命、有演化能力的精灵。

free 是一套全部开源的快速开发平台，毫无保留给个人及企业免费使用。

* 感谢 [RuoYi](https://gitee.com/y_project/RuoYi) 一个开源成熟的管理后台项目，free项目中使用了若依管理后台。
* 感谢 [boot_master](https://gitee.com/bootstrap2table/boot_master) 开源项目，对于作者技术的深度、广度我十份佩服。

### 核心依赖

| 依赖                   | 版本          | 说明            |
| ---------------------- | ------------- | ------------- |
| Spring Boot            | 2.3.6.RELEASE | 系统核心框架  |
| mybatis                | 2.1.4        |数据持久框架 |
| Redis                  | 3.3.0        | 系统缓存框架  |
| Redisson               | 3.11.4      |  后台session管理   |
| Alibaba Druid          | 1.2.3       | 数据库连接池 |
| thymeleaf              | last        | 系统前端框架 |
| JwtToken+AES           | last        | 安全授权框架 |
| RedisMq                | last        | 系统消息队列 |

### 模块说明

```lua

free
├── doc -- 文档、数据库文件
└── afeng -- 系统公共模块
     ├── common -- 公共工具类核心包
     ├── framework -- 框架核心配置
     └── module -- 服务模块

```

## 快速开始
1.  打开doc目录，free目前支持mysql与sqlServer数据库，新建一个名为free的数据库并录入表结构数据。
2.  修改application-dev.yml配置文件中的spring.profiles.include选项，根据需要选择mysql或sqlServer配置，并修改相应数据库配置中的连接信息。
3.  修改application-dev.yml配置文件中的spring.redis的连接信息并先运行redis服务。
4.  至此，启动项目即可。


## 项目交流群

暂无

## 管理后台功能

1.  用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.  部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限。
3.  岗位管理：配置系统用户所属担任职务。
4.  菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5.  角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6.  字典管理：对系统中经常使用的一些较为固定的数据进行维护。
7.  参数管理：对系统动态配置常用参数。
8.  通知公告：系统通知公告信息发布维护。
9.  操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
10. 登录日志：系统登录日志记录查询包含登录异常。
11. 在线用户：当前系统中活跃用户状态监控。(此功能暂未实现，因为目前项目的管理后台主要是对内使用)
12. 定时任务：在线（添加、修改、删除)任务调度包含执行结果日志。
13. 代码生成：前后端代码的生成（java、html、xml、sql）支持CRUD下载 。
14. 系统接口：根据业务代码自动生成相关的api接口文档。
15. 服务监控：监视当前系统CPU、内存、磁盘、堆栈等相关信息。
16. 在线构建器：拖动表单元素生成相应的HTML代码。
17. 连接池监视：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。

## 管理后台在线体验

- 目前尚未没有用于演示的服务器，希望各位同学谅解，因为free使用了若依的管理后台页面框架，所以前往https://demo.ruoyi.vip/login体验即可。
- 用户名与密码 admin/admin123  


## 管理后台部分演示图

<table>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-42e518aa72a24d228427a1261cb3679f395.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-7f20dd0edba25e5187c5c4dd3ec7d3d9797.png"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-2dae3d87f6a8ca05057db059cd9a411d51d.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-ea4d98423471e55fba784694e45d12bd4bb.png"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-7f6c6e9f5873efca09bd2870ee8468b8fce.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-c708b65f2c382a03f69fe1efa8d341e6cff.png"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-9ab586c47dd5c7b92bca0d727962c90e3b8.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-ef954122a2080e02013112db21754b955c6.png"/></td>
    </tr>	 
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-088edb4d531e122415a1e2342bccb1a9691.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-f886fe19bd820c0efae82f680223cac196c.png"/></td>
    </tr>

</table>



