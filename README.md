#### tapd钉钉机器人操作手册

>  本项目适用于采用TAPD研发平台+钉钉办公的团队
</br>
将TAPD平台中的事件通知通过钉钉群消息提醒的方式呈现出来，将TAPD平台和日常钉钉办公环境集成起来，能极大的提高团队间的协作效率 

##### 前置条件--钉钉配置
>   该步骤需要钉钉群主/群管理配合操作</br>

- 钉钉电脑版，打开钉钉群设置 </br>
![image](https://img08.yongsam.cn/tapddingding/%E7%AC%AC%E4%B8%80%E5%88%97%E5%93%A6.png)
- 找到智能群助手 </br>
![image](https://img08.yongsam.cn/tapddingding/2.png)
- 找到添加机器人 </br>
![image](https://img08.yongsam.cn/tapddingding/QQ%E6%88%AA%E5%9B%BE20211210151026.png)
- 选择添加webhook机器人 </br>
![image](https://img08.yongsam.cn/tapddingding/%E9%80%89%E6%8B%A9%E6%B7%BB%E5%8A%A0%E5%AE%89%E5%85%A8%E6%9C%BA%E5%99%A8%E4%BA%BA.png)
- 安全设置 </br>
![image](https://img08.yongsam.cn/tapddingding/%E5%AE%89%E5%85%A8%E8%AE%BE%E7%BD%AE.png)
- 复制Token </br>
![image](https://img08.yongsam.cn/tapddingding/QQ%E6%88%AA%E5%9B%BE20211210151328.png)

#### Tapd前置准备
- 使用浏览器打开TAPD平台,选择某个项目 </br>
![image](https://img08.yongsam.cn/tapddingding/%E5%89%8D%E7%BD%AE%E5%87%86%E5%A4%87.png)
- 点击更多-设置</br>
![image](https://img08.yongsam.cn/tapddingding/%E6%9B%B4%E5%A4%9A%E8%AE%BE%E7%BD%AE.png)
- 点击自动化助手 </br>
![image](https://img08.yongsam.cn/tapddingding/%E7%82%B9%E5%87%BB%E8%87%AA%E5%8A%A8%E5%8C%96%E5%8A%A9%E6%89%8B.png)
- 添加自动化规则</br>
![image](https://img08.yongsam.cn/tapddingding/%E6%B7%BB%E5%8A%A0%E8%87%AA%E5%8A%A8%E5%8C%96%E8%A7%84%E5%88%99.png)
- 选择想提醒的事件内容 </br>
![image.png](https://img08.yongsam.cn/tapddingding/image.png)
- 执行**WEBhook**动作 </br>
![image](https://img08.yongsam.cn/tapddingding/%E6%89%A7%E8%A1%8CWEBhook.png)
- Webhook推送 </br>
![image.png](https://img08.yongsam.cn/tapddingding/image%20%281%29.png)
- 配置服务器相关配置</br>
![image](https://img08.yongsam.cn/tapddingding/%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%9B%B8%E5%85%B3%E9%85%8D%E7%BD%AE.png)


>  Webhook请求url对应配置示例</br>

```
http://对应公网ip与端口或者域名/dingdingpush/tapdWebhook?accessToken=钉钉群对应token&secret=对应秘钥&type=md

```

>   对应type支持text（文本类容）,md(mackdown) 
#### 支持事件
- 任务(创建,字段变更,状态变更)
- 需求(创建,字段变更,状态变更)
- 缺陷(创建,字段变更,状态变更)
