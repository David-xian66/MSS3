<template>
  <base-main title="服务端导入" index="2-3">
    <el-card class="card" body-style="padding: 10px; padding-left: 10px" shadow="hover">
      <h1 class="card_title"><b>从外部导入服务端</b></h1>
      <el-divider/>
      <el-steps style="margin: 0 auto; padding-left:130px;  width: 500px" :space="200" :active="Step" finish-status="success">
        <el-step title="基本设置" />
        <el-step title="上传" />
        <el-step title="完成" />
      </el-steps>
      <div style="margin-left: 100px; margin-right: 100px; margin-top: 40px">
        <el-form v-show="Step === 0" ref="ruleFormRef" label-position="left" :model="setting" label-width="120px">
          <el-form-item label="服务端名称：">
            <el-input v-model="setting.name" placeholder="服务端的名称，将会显示在控制面板（必须）"/>
          </el-form-item>
          <el-form-item label="服务端介绍：">
            <el-input v-model="setting.des" placeholder="服务端的介绍，防止名称相同分辨不出"/>
          </el-form-item>
          <el-form-item label="所属用户ID：">
            <el-input v-model="setting.userid" placeholder="这个服务端应该给予哪个用户"/>
          </el-form-item>
          <el-form-item label="核心类型：">
            <el-select v-model="setting.type" placeholder="核心的种类（必须）">
              <el-option label="Paper (纸张)" value="paper" />
              <el-option label="Bukkit (水桶)" value="bukkit" />
              <el-option label="Mojang (官服)" value="mojang" />
              <el-option label="Spigot (水龙头)" value="spigot" />
              <el-option label="PE (基岩版)" value="pe" />
              <el-option label="BungeeCord (金蛋)" value="bungeecord" />
              <el-option label="其他" value="other" />
            </el-select>
          </el-form-item>
          <el-form-item label="停止时间">
            <el-date-picker
                v-model="setting.stopdate"
                type="date"
                style="width: 300px"
                placeholder="服务端到期时间（不设置为无限"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="next(ruleFormRef)">下一步</el-button>
            <span v-show="!IsSuccess" style="color: red; margin-left: 20px">错误，请检查是否填写完成</span>
          </el-form-item>
        </el-form>
        <el-upload v-show="Step === 1" style="margin-left: 29%" accept=".jar" drag :headers="Head" action="/api-server/jarUpload" @on-success="next">
          <el-icon class="el-icon--upload"><UploadFilled/></el-icon>
          <div class="el-upload__text">
            把文件拖到这里或者 <em>点击这里上传</em>
          </div>
          <template #tip>
            <div class="el-upload__tip" style="margin-left: -90px">
              为了保证服务器安全，这里只能上传jar文件，需要上传服务端整合包，请根据官方文档让服务提供者上传
            </div>
            <div v-show="fileHave" class="el-upload__tip" style="color: red">
              文件重复
            </div>
            <el-button @click="back" style="margin-left: 20%; margin-top: 20px; margin-bottom: 20px">上一步</el-button>
          </template>
        </el-upload>
        <el-result v-show="Step === 2" style="margin-top: -40px" icon="success" title="添加成功！" sub-title="点击下面的按钮回到列表">
          <template #extra>
            <el-button @click="backList" type="primary">Back</el-button>
          </template>
        </el-result>
      </div>
    </el-card>
  </base-main>
</template>

<!--suppress HtmlUnknownTarget -->
<style :src="baseMainCss"/>

<script setup>
import { UploadFilled } from '@element-plus/icons-vue'
import axios from "axios";
const {reactive, ref} = require("vue");
const setting = reactive({
  name: '',
  des: '',
  type: '',
  userid: '',
  stopdate: ''
})
const fileHave = ref(false)
const Step = ref(0)
const IsSuccess = ref(true)

function next(response){
  switch (Step.value){
    case 0:
      if(setting.name === '' || setting.type === ''){
        IsSuccess.value = false
        return;
      }
      IsSuccess.value = true;
      Step.value++;
      break;
    case 1:
      if(response.data.code === 0){
        fileHave.value = true
        return;
      }
      if(response.data.code === -5){
        window.location.href="#/login"
        return;
      }
      Step.value++;
      break;
  }
}

function back(){
  switch (Step.value){
    case 1:
      Step.value--;
      break;
  }
}

function backList(){
  axios.post('/api-server/addServer',setting,{
    headers:{
      'Token' : getCookie("Token"),
    },
  }).then(response => {
    if(response.data.code === 1){
      window.location.href="#/serverManager"
      return;
    }
    if(response.data.code === -5){
      window.location.href="#/login"
    }
  });
}

</script>

<script>
import BaseMain from "@/components/BaseMain";

const {getCookie} = require("@/assets/js/cookie");
export default {
  name: "ServerInput",
  components:{
    BaseMain
  },
  data(){
    return{
      baseMainCss:require('../assets/css/BaseMain.css'),
      Head:{
        'Token' : getCookie("Token"),
      }
    }
  }
}
</script>

<style scoped>

</style>