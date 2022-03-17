<template>
  <base-main title="服务端管理" index="2-1">
    <el-card class="card" body-style="padding: 10px; padding-left: 10px" shadow="hover">
      <h1 class="card_title"><b>
        服务端列表
      </b></h1>
      <el-divider/>
      <el-row style="margin-bottom: 20px">
        <el-col :span="6">
          <el-input v-model="sc" placeholder="搜索服务端"/>
        </el-col>
        <el-col :span="1" style="margin-left: 10px">
          <el-button :icon="RefreshRight" @click="scanServers">刷新</el-button>
        </el-col>
        <el-col :span="1" style="margin-left: 40px">
          <el-button :icon="Search" @click="reloadData">搜索</el-button>
        </el-col>
        <el-col :span="1" style="margin-left: 40px">
          <el-button :icon="SwitchButton">开启</el-button>
        </el-col>
        <el-col :span="1" style="margin-left: 40px">
          <el-button :icon="CircleClose">关闭</el-button>
        </el-col>
        <el-col :span="1" style="margin-left: 40px">
          <el-button :icon="Close">终止</el-button>
        </el-col>
        <el-col :span="1" style="margin-left: 40px">
          <el-button type="danger" :icon="Delete">删除</el-button>
        </el-col>
      </el-row>
      <el-table v-show="!noServer" :data="reallyData" style="width: 100%">
        <el-table-column type="selection" width="55" />
        <el-table-column label="服务端名称" width="600">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span>{{ scope.row.serverName }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="180">
          <template #default="scope">
            <el-tag :type="scope.row.serverStatus">{{ scope.row.statusName }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="类型">
          <template #default="scope">
            <span>{{ scope.row.serverType }}</span>
          </template>
        </el-table-column>
        <el-table-column label="控制">
          <template #default="scope">
            <el-button
                size="small"
            >管理</el-button>
            <el-button
                size="small"
                @click="test(scope.$index)"
            >设置</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-empty v-show="noServer" description="Oops！看起来你好像没有安装任何服务端呢！快去下载/导入页面找一个吧！" />
    </el-card>
  </base-main>
</template>

<script setup>
import {RefreshRight, SwitchButton, CircleClose, Close, Delete, Search} from "@element-plus/icons-vue";
</script>

<script>
import BaseMain from "@/components/BaseMain";
import axios from "axios";
import {getCookie} from "@/assets/js/cookie";
export default {
  name: "ServerManager",
  components: {BaseMain},
  data(){
    return{
      noServer : false,
      dataServers : [],
      sc: null,
      reallyData : null
    }
  },
  mounted() {
    this.reloadData();
  },
  methods:{
    scanServers(){
      let userinfo = getCookie("Token");
      axios
          .get('/api-server/getServer',{
            headers:{
              'Token' : userinfo,
            }
          })
          .then(response => {
            if(response.data.code === -5){
              window.location.href="#/login"
            }else {
              if(response.data.data.count === 0){
                this.noServer = true;
              }else{
                this.dataServers = [];
                response.data.data.servers.forEach((a) => {
                  let status;
                  let statusName;
                  switch (a.status) {
                    case 0:
                      status = 'info';
                      statusName = '已停止';
                      break;
                    case 1:
                      status = 'success';
                      statusName = '已启动';
                      break;
                    case 2:
                      status = 'danger';
                      statusName = '正在关闭';
                      break;
                  }
                  this.dataServers[this.dataServers.length] = {
                    "serverId" : a.serverId,
                    "serverName" : a.name,
                    "serverStatus" : status,
                    "statusName" : statusName,
                    "serverType" : a.type
                  };
                  this.reloadData();
                })
              }
            }
          }
      )
    },
    reloadData(){
      this.reallyData = this.dataServers.filter(
          (data) =>
              !this.sc ||
              data.serverName.toLowerCase().includes(this.sc.toLowerCase())
      )
    }
  }
}
</script>

<style scoped>

</style>