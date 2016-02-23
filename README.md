B2B商城(xueMall)
===============

技术参数
-------

**短信（短信商模板短信）**

- 短信模板示例：
	
		你好，@USER_NAME@，你的验证码是：@CONFIRM_CODE@，请尽快完成注册。
		
-  发送格式示例：
	
		@USER_NAME@=小明,@CONFIRM_CODE@=6659
	
- 模板短信模板ID（eg: check-code_sms.ftl，`XM_`+`模板文件名全大写`[不包括文件扩展名]）：
	
		XM_CHECK-CODE_SMS

**短信服务商**

> - 江苏美圣：<http://www.jsmsxx.com/>
> - 后台管理地址： <http://www.mssms.cn/msm/index.html>

支付
----

**银联测试卡号信息** 短信验证码请输入6个1(例：111111)

<table cellpadding="0" cellspacing="0" border="0" style="width:100%">
	<thead>
		<tr>
			<th width="20%">卡号</th>
			<th width="12%">卡性质</th>
			<th width="13%">机构名称</th>
			<th width="12%">手机号码</th>
			<th width="7%">密码</th>
			<th width="5%">CVN2</th>
			<th width="5%">有效期</th>
			<th width="15%">证件号</th>
			<th width="20%">姓名</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>6216261000000000018</td>
			<td>借记卡</td>
			<td>平安银行</td>
			<td>13552535506</td>
			<td>123456</td>
			<td></td>
			<td></td>
			<td>341126197709218366</td>
			<td>全渠道</td>
		</tr>
		<tr>
			<td>6221558812340000</td>
			<td>贷记卡</td>
			<td>平安银行</td>
			<td>13552535506</td>
			<td>123456</td>
			<td>123</td>
			<td>1711</td>
			<td>341126197709218366</td>
			<td>互联网</td>
		</tr>
	</tbody>
</table>


开发日志
-------

- 2015-04-08 创建了`web-static`项目，规范化管理前端静态资源；
- 2015-05-25 切换至新项目，原项目停止更新，迁至：<https://gitlab.21tb.com/yuanyuan/app-xuemall>
- 2015-05-26 升级了`mdl`实体生成工具，支持mdl 7.0 版本代码生成；
- 2015-06-12 完成主体接口设计与实现；

关联项目
-------

**web-static**

- xuemall <http://localhost/xuemall>
- xuemall admin <http://localhost/xuemall/admin/page/user/index>
- xuemall project <https://gitlab.21tb.com/eln/app-xuemal>
- xuemall git <ssh://git@gitlab.21tb.com:4350/eln/app-xuemal.git>
- web-static <http://www.tbc.com/web-static/>
- web-static project <https://gitlab.21tb.com/yuanyuan/web-static>
- web-static git <ssh://git@gitlab.21tb.com:4350/yuanyuan/web-static.git>
- web-static ftp(ajax lib) `ftp: 192.168.1.216` | `username:ftp` | `password:hfftp`
- xuemall-ui  <http://192.168.1.218:8074/>
- xuemall-ui resource <https://gitlab.21tb.com/xqq/xuemall>


开源
----

- Apache SiteMesh 3: <http://wiki.sitemesh.org/wiki/display/sitemesh3/Configuring+SiteMesh+3>
- Apache shiro: <http://shiro.apache.org/>
- urlrewrite: <http://www.tuckey.org/urlrewrite/>
- guava: <https://github.com/google/guava>
- jquery: <http://www.jquery.com/>
- iconfont: <http://www.iconfont.cn/>
