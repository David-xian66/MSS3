# MSS3 仓库介绍文档

---

## 关于
本项目是由XiaoyiStudio创建，发起人Xiaoyi311。所有解释权均归XiaoyiStudio所有。

## 职务分配
HeimNad —— 网站运营<br>
David —— Bug寻找（MAC）<br>
Xiaoyi311 —— 主编，设计，打包<br>
曰笙 —— 设计（学业暂时无时间）<br>
[IPv1]ICU-HKEY —— Bug寻找（Win）

## 打包过程
1. 终端进入**mss-root**目录
2. 运行**mvn install**等待完成
3. 打开**target**文件夹，找到**xxx.jar**这便是最终文件
4. 把**xxx.jar**重命名为**MSS3-x.x.x.jar**
5. 需要的话可以打包zip
6. 上传github发行版，Tag和Title设置为vx.x.x-dev
7. 发给网站运行