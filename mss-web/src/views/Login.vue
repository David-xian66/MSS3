<template>
  <div class="main">
    <img :src="backGroundImg" class="background" alt="background"/>
    <el-row class="things">
      <el-col :offset="11" :span="12" class="panel">
        <el-row class="things">
          <el-col :span="9" class="panel_left">
            <Login_LeftPanel_Title title="Minecraft"/>
            <Login_LeftPanel_Title title="Server"/>
            <Login_LeftPanel_Title title="Start"/>
            <h1 class="Des">以繁化简<br/>——重构想象</h1>
            <img :src="bottomImg" class="BottomImg" alt="">
          </el-col>
          <el-col :span="15" class="panel_right">
            <h1 class="a-Title">欢迎来到MSS 3<br/>管理后台</h1>
            <el-form :model="userInfo" ref="userInfo">
              <el-row>
                <el-col :span="4">
                  <h4>用户名:</h4>
                </el-col>
                <el-col :span="17">
                  <el-form-item>
                    <el-input v-model="userInfo.userName" class="input" placeholder="请输入用户名" />
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="4">
                  <h4>密码:</h4>
                </el-col>
                <el-col :span="17">
                  <el-form-item>
                    <el-input v-model="userInfo.passWord" type="password" class="input" placeholder="请输入密码" />
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :offset="17">
                  <!--suppress HtmlUnknownAnchorTarget -->
                  <a href="#/login" @click="forgetPassword">忘记密码</a>
                </el-col>
              </el-row>
              <el-row class="Login">
                <el-col :offset="5" :span="12">
                  <el-form-item>
                    <el-button class="Login-btn" @click="userLogin" size="large" round>登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</el-button>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
            <el-row>
              <h3 class="errorMsg">{{ errorMsg }}</h3>
            </el-row>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<!--suppress HtmlUnknownTarget -->
<style :src="loginCss"/>

<script>
import 'element-plus/theme-chalk/display.css'
import Login_LeftPanel_Title from "@/components/Login_LeftPanel_Title";
import {reactive} from "vue";
import axios from "axios";

console.log(
    "%c                                         \n"+
    "———————————————————————————————————————————\n"+
    "            MSS3 DEBUG CONSOLE             \n"+
    "Minecraft Server Start 3 By XiaoyiStudio311\n"+
    "                v0.0.2-dev                 \n"+
    "———————————————————————————————————————————\n"+
    "                                           \n",
    "color:green"
);

export default {
  name: "LoginPage",
  components: {
    Login_LeftPanel_Title
  },
  data(){
    return{
      backGroundImg:require('../assets/images/login_background.png'),
      bottomImg:require('../assets/images/login_leftpanel_bottom.png'),
      loginCss:require('../assets/css/Login.css'),
      userInfo:reactive({passWord: '', userName: ''}),
      errorMsg:''
    }
  },
  methods:{
    forgetPassword() {
      alert("其他用户密码可以用管理员账户修改，如果忘记管理员密码，请删除MSS主程序目录下 data/MSSUsers.json 以此来重置账户系统");
    },
    userLogin(){
      axios
          .get('/api-login/userLogin?UserName=' + this.userInfo.userName + "&PassWord=" + this.userInfo.passWord)
          .then(response => {
            if(response.data.toString().startsWith("0")){
              this.errorMsg = '用户名或密码错误';
            }else if(response.data.toString().startsWith("-1")){
              this.errorMsg = '内部错误：' + response.data.split("\\|")[1];
            }else if(response.data.toString().startsWith("1")){
              this.$router.push({path : "/"});
            }
          })
    }
  }
}
</script>