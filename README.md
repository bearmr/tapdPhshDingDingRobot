#### tapd钉钉机器人操作手册

>  本项目适用于采用TAPD研发平台+钉钉办公的团队
</br>
将TAPD平台中的事件通知通过钉钉群消息提醒的方式呈现出来，将TAPD平台和日常钉钉办公环境集成起来，能极大的提高团队间的协作效率

##### 前置条件--钉钉配置
>   该步骤需要钉钉群主/群管理配合操作</br>

- 钉钉电脑版，打开钉钉群设置 </br>
  ![钉钉群设置.png](https://pic.jitudisk.com/public/2022/07/12/13e23a4779101.png)
- 找到智能群助手 </br>
  ![钉钉找到群助手.png](https://pic.jitudisk.com/public/2022/07/12/469e378715351.png)
- 找到添加机器人 </br>
  ![找到添加机器人.png](https://pic.jitudisk.com/public/2022/07/12/f1cff573d5347.png)
- 选择添加webhook机器人 </br>
  ![选择添加安全机器人.png](https://pic.jitudisk.com/public/2022/07/12/c6fc263cb1b8d.png)
- 安全设置 </br>
  ![安全设置.png](https://pic.jitudisk.com/public/2022/07/12/725f1eb9cdafd.png)
- 复制Token </br>
  ![复制Token.png](https://pic.jitudisk.com/public/2022/07/12/f36f47f2ffdc8.png)

#### Tapd前置准备
- 使用浏览器打开TAPD平台,选择某个项目 </br>
  ![使用浏览器打开TAPD平台,选择某个项目.png](https://pic.jitudisk.com/public/2022/07/12/e36e8734dbb25.png)
- 点击更多-设置</br>
  ![点击更多-设置.png](https://pic.jitudisk.com/public/2022/07/12/a3795a3dab2c0.png)
- 点击自动化助手 </br>
  ![点击自动化助手.png](https://pic.jitudisk.com/public/2022/07/12/e451711b39631.png)
- 添加自动化规则</br>
  ![添加自动化规则.png](https://pic.jitudisk.com/public/2022/07/12/6fcc673dea08a.png)
- 选择想提醒的事件内容 </br>
  ![选择想提醒的事件内容.png](https://pic.jitudisk.com/public/2022/07/12/1773b7a229d9c.png)
- 执行**WEBhook**动作 </br>
  ![执行WEBhook.png](https://pic.jitudisk.com/public/2022/07/12/0216412cbfa4d.png)
- Webhook推送 </br>
  ![Webhook推送.png](https://pic.jitudisk.com/public/2022/07/12/6233f62e7d1f7.png)
- 配置服务器相关配置</br>
  ![服务器相关配置.png](https://pic.jitudisk.com/public/2022/07/12/7658d99ab7698.png)


>  Webhook请求url对应配置示例</br>

```
http://对应公网ip与端口或者域名/dingdingpush/tapdWebhook?accessToken=钉钉群对应token&secret=对应秘钥&type=md 
```

>   对应type支持text（文本类容）,md(mackdown)
#### 支持事件
- 任务(创建,字段变更,状态变更)
- 需求(创建,字段变更,状态变更)
- 缺陷(创建,字段变更,状态变更)