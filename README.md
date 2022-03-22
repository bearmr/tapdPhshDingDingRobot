#### tapd钉钉机器人操作手册

>  本项目适用于采用TAPD研发平台+钉钉办公的团队
</br>
将TAPD平台中的事件通知通过钉钉群消息提醒的方式呈现出来，将TAPD平台和日常钉钉办公环境集成起来，能极大的提高团队间的协作效率

##### 前置条件--钉钉配置
>   该步骤需要钉钉群主/群管理配合操作</br>

- 钉钉电脑版，打开钉钉群设置 </br>
  ![image](https://note.youdao.com/yws/res/4/WEBRESOURCE80541faf7868d3b2e305344954255174)
- 找到智能群助手 </br>
  ![image](https://note.youdao.com/yws/res/1/WEBRESOURCE6cee0d891a6965876a088b4ba4f39341)
- 找到添加机器人 </br>
  ![image](https://note.youdao.com/yws/res/5/WEBRESOURCE44edb33b2db2069ab2147ea0379c6ea5)
- 选择添加webhook机器人 </br>
  ![image](https://note.youdao.com/yws/res/2/WEBRESOURCE75f3c77c070d0d8cfbd485876790de32)
- 安全设置 </br>
  ![image](https://note.youdao.com/yws/res/8/WEBRESOURCE4d10b43a9f8d4340c99420885e660e28)
- 复制Token </br>
  ![image](https://note.youdao.com/yws/res/3/WEBRESOURCE0ff754e79d0711d628a3b08247e3c073)

#### Tapd前置准备
- 使用浏览器打开TAPD平台,选择某个项目 </br>
  ![image](https://note.youdao.com/yws/res/5/WEBRESOURCE6a805ad94d250710d327ea665f8593c5)
- 点击更多-设置</br>
  ![image](https://note.youdao.com/yws/res/4/WEBRESOURCE972cbaca993c35d475c338b14c59bcc4)
- 点击自动化助手 </br>
  ![image](https://note.youdao.com/yws/res/0/WEBRESOURCE45ab105541e072a41fcc54c18e815a40)
- 添加自动化规则</br>
  ![image](https://note.youdao.com/yws/res/3/WEBRESOURCE4d880582d405c4d7ed3d103eab1aa313)
- 选择想提醒的事件内容 </br>
  ![image.png](https://note.youdao.com/yws/res/7/WEBRESOURCE2eb79ee5987ca974b9ca0929c6a5ad37)
- 执行**WEBhook**动作 </br>
  ![image](https://note.youdao.com/yws/res/4/WEBRESOURCE4439c0029e7fcabdb04f7de26c7ac1c4)
- Webhook推送 </br>
  ![image.png](https://note.youdao.com/yws/res/1/WEBRESOURCE3cf145c0a7e6971fc2c53c613cb99621)
- 配置服务器相关配置</br>
  ![image](https://note.youdao.com/yws/res/5/WEBRESOURCE44fa58b9c0315ea579a0e0d80de64185)


>  Webhook请求url对应配置示例</br>

```
http://对应公网ip与端口或者域名/dingdingpush/tapdWebhook?accessToken=钉钉群对应token&secret=对应秘钥&type=md 
```

>   对应type支持text（文本类容）,md(mackdown)
#### 支持事件
- 任务(创建,字段变更,状态变更)
- 需求(创建,字段变更,状态变更)
- 缺陷(创建,字段变更,状态变更)