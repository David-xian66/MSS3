<template>
  <div>
    <img :src="backGroundImg" class="background" alt=""/>
    <el-container class="main">
      <el-aside width="auto" class="aside card">
        <a href="https://mss3.top">
          <img :src="Logo" class="logo" v-show="!isHideLogo" alt="">
        </a>
        <el-menu
            :default-active="index"
            class="menu"
            :collapse="isHideMenu"
            @select="menuSelect"
        >
          <el-menu-item index="1">
            <el-icon><data-analysis /></el-icon>
            <span>概览</span>
          </el-menu-item>
          <el-sub-menu index="2" title="服务端管理">
            <template #title>
              <el-icon><Grid/></el-icon>
              <span>服务端</span>
            </template>
            <el-menu-item index="2-1">
              <el-icon><Collection/></el-icon>
              <span>服务端管理</span>
            </el-menu-item>
            <el-menu-item index="2-2">
              <el-icon><Document/></el-icon>
              <span>服务端设置</span>
            </el-menu-item>
            <el-menu-item index="2-3">
              <el-icon><Cloudy/></el-icon>
              <span>服务端导入</span>
            </el-menu-item>
            <el-menu-item index="2-4">
              <el-icon><Bottom/></el-icon>
              <span>服务端下载</span>
            </el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header class="header">
          <el-row>
            <el-col :span="1">
              <el-icon v-on:click="hideMenu" :size="20" color="#FFFFFF">
                <fold v-show="!isHideMenu" />
                <expand v-show="isHideMenu"/>
              </el-icon>
            </el-col>
            <el-col :span="6">
              <h3 class="date header_text">{{ date }}</h3>
            </el-col>
            <el-col :offset="8" :span="2">
              <el-link href="https://release.mss3.top" class="header_text" :underline="false" :icon="Bottom">版本列表</el-link>
            </el-col>
            <el-col :span="3">
              <el-link href="/swagger-ui/index.html" class="header_text" :underline="false" :icon="Collection">开放文档(本地)</el-link>
            </el-col>
            <el-col :span="2">
              <el-link href="https://www.yuque.com/xiaoyi311-m10j2/mss3/dfrg06" class="header_text" :underline="false" :icon="Search">帮助文档</el-link>
            </el-col>
            <el-col :span="2">
              <el-link href="/api-login/loginOut" class="header_text" :underline="false" :icon="Close">登出</el-link>
            </el-col>
          </el-row>
        </el-header>
        <el-main class="webMain">
          <el-row class="card webMain_Title">
            <span class="Title">{{ title }}</span>
          </el-row>
          <div class="webBody">
            <transition name="slide-fade" appear>
              <slot></slot>
            </transition>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<!--suppress HtmlUnknownTarget -->
<style :src="baseMainCss"/>

<script setup>
import { Bottom, Collection, Search, Close, Grid, Cloudy, Document } from "@element-plus/icons-vue";
</script>

<script>
import {getCookie} from "@/assets/js/cookie";
import axios from "axios";
import {Fold, DataAnalysis, Expand} from "@element-plus/icons-vue";

export default {
  name: "BaseMain",
  components: {
    Fold,
    DataAnalysis
  },
  props: ['title', 'index'],
  data(){
    return{
      baseMainCss:require('../assets/css/BaseMain.css'),
      backGroundImg:require('../assets/images/home_background.png'),
      Logo:require('../assets/images/logo.png'),
      isHideMenu:false,
      isHideLogo:false,
      hideTimer:null,
      date:"",
    }
  },
  mounted() {
    this.isLogin();
    this.getTime();
    this.timer = setInterval(() => {
      this.getTime();
    }, 3000)
  },
  methods:{
    isLogin(){
      let userinfo = getCookie("Token");
      alert(userinfo.length)
      if(userinfo.length === 0){
        window.location.href="#/login"
      }
      axios
          .get('/api-login/isLive',{
            headers:{
              'Token' : userinfo,
            },
          })
          .then(response => {
            if(response.data.code === -5){
              window.location.href="#/login"
            }
          });
    },
    hideMenu(){
      if(this.isHideMenu){
        this.isHideMenu = !this.isHideMenu;
        clearTimeout(this.hideTimer);
        this.hideTimer = setTimeout(() => {
          console.log("1")
          this.isHideLogo = false;
        }, 300);
      }else {
        this.isHideMenu = !this.isHideMenu;
        this.isHideLogo = true;
      }
    },
    getTime() {
      let weeks = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
      let time = new Date();
      let year = time.getFullYear();
      let month = time.getMonth() + 1;
      let day = time.getDate();
      let week = weeks[time.getDay()];
      let hour = time.getHours();
      let minutes = time.getMinutes();
      if(minutes<10){
        minutes='0'+minutes
      }
      this.date = year + '/' + month + '/' + day + " " + week + " " + hour + ":" + minutes;
    },
    menuSelect(index){
      switch (index) {
        case "1":
          window.location.href="#/"
          break;
        case "2-1":
          window.location.href="#/serverManager"
          break;
      }
    }
  }
}
</script>

<style scoped>

</style>