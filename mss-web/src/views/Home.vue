<template>
  <base-main title="概览" index="1">
    <div>
      <el-row class="myCard">
        <el-col :span="11">
          <el-card class="card" body-style="padding: 10px; padding-left: 10px" shadow="hover">
            <h1 class="card_title">玩家列表</h1>
            <el-divider/>
            <el-table :data="tableData" height="250" style="width: 100%">
              <el-table-column prop="name" label="名称" width="100" />
              <el-table-column prop="uuid" label="UUID" width="200" />
              <el-table-column prop="joinTime" label="入服时间" width="100" />
              <el-table-column prop="type" label="权限" />
            </el-table>
          </el-card>
        </el-col>
        <el-col :offset="1" :span="12">
          <el-card class="card" body-style="padding: 10px; padding-left: 10px" shadow="hover">
            <h1 class="card_title">CPU及内存信息</h1>
            <el-divider/>
            <div id="cpu_info" class="cpu_info"></div>
          </el-card>
        </el-col>
      </el-row>
      <el-row class="myCard">
        <el-col :span="24">
          <el-card class="card" body-style="padding: 10px; padding-left: 10px" shadow="hover">
            <h1 class="card_title">其他信息</h1>
            <el-divider/>
            <div id="other_info" class="other_info"></div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </base-main>
</template>

<!--suppress HtmlUnknownTarget -->
<style :src="DataCss"/>

<script>

import BaseMain from "@/components/BaseMain";
import * as echarts from "echarts";

export default {
  name: "HomePage",
  components :{
    BaseMain
  },
  data(){
    return{
      tableData:[
        {
          name: 'Xiaoyi311',
          uuid: 'xxxx-xxxx-xxxx-xxxx',
          joinTime: '2222-2-22',
          type: 'admin',
        },
        {
          name: 'Xiaoyi311',
          uuid: 'xxxx-xxxx-xxxx-xxxx',
          joinTime: '2222-2-22',
          type: 'admin',
        },
        {
          name: 'Xiaoyi311',
          uuid: 'xxxx-xxxx-xxxx-xxxx',
          joinTime: '2222-2-22',
          type: 'admin',
        },
        {
          name: 'Xiaoyi311',
          uuid: 'xxxx-xxxx-xxxx-xxxx',
          joinTime: '2222-2-22',
          type: 'admin',
        },
        {
          name: 'Xiaoyi311',
          uuid: 'xxxx-xxxx-xxxx-xxxx',
          joinTime: '2222-2-22',
          type: 'admin',
        },
        {
          name: 'Xiaoyi311',
          uuid: 'xxxx-xxxx-xxxx-xxxx',
          joinTime: '2222-2-22',
          type: 'admin',
        }
      ],
      cpuInfo:null,
      otherInfo:null,
      DataCss:require('../assets/css/Data.css'),
    }
  },
  mounted() {
    this.drawCpuInfo()
    this.drawOtherInfo()
  },
  methods:{
    drawCpuInfo(){
      this.cpuInfo = echarts.init(document.getElementById('cpu_info'));
      this.cpuInfo.setOption(
          {
            tooltip : {
              trigger: 'axis'
            },
            legend: {
              data:['CPU','内存','硬盘']
            },
            calculable : true,
            xAxis : [
              {
                type : 'category',
                boundaryGap : false,
                axisTick: {
                  show: false
                },
                data : ['1:00','2:00','3:00','4:00','5:00','7:00','8:00','9:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00','24:00']
              }
            ],
            yAxis : [
              {
                type : 'value',
                axisTick: {
                  show: false
                },
                name: '（使用率）'
              }
            ],
            series : [
              {
                name:'CPU',
                type:'line',
                stack: '总量',
                data:[0,30,60,20,90,50,60,53,62,74,36,84,63,85,25,35,25,44,25,11,34,64,33,53]
              },
              {
                name:'内存',
                type:'line',
                stack: '总量',
                data:[0,50,80,50,60,90,30,35,74,63,53,35,75,21,35,15,11,45,44,77,34,12,32,44]
              },
              {
                name:'硬盘',
                type:'line',
                stack: '总量',
                data:[0,30,40,80,60,50,20,43,65,32,43,56,23,54,43,66,22,43,10,42,43,64,89,65]
              }
            ]
          }
      );
    },
    drawOtherInfo(){
      this.otherInfo = echarts.init(document.getElementById('other_info'));
      this.otherInfo.setOption(
          {
            tooltip : {
              trigger: 'axis'
            },
            legend: {
              data:['在线人数','平均在线时间']
            },
            calculable : true,
            xAxis : [
              {
                type : 'category',
                boundaryGap : false,
                axisTick: {
                  show: false
                },
                data : ['1:00','2:00','3:00','4:00','5:00','7:00','8:00','9:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00','24:00']
              }
            ],
            yAxis : [
              {
                type : 'value',
                axisTick: {
                  show: false
                },
                name: '人数/小时'
              }
            ],
            series : [
              {
                name:'在线人数',
                type:'line',
                stack: '总量',
                data:[5,2,3,6,7,3,8,3,6,2,8,2,7,3,8,2,8,3,7,4,3,3,7,2]
              },
              {
                name:'平均在线时间',
                type:'line',
                stack: '总量',
                data:[1,3,2,2,4,5,2,7,3,9,2,5,5,9,2,8,6,3,5,9,4,8,9,3]
              }
            ]
          }
      );
    }
  }
}
</script>

<style scoped>

</style>