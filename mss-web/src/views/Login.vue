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
            <el-row>
              <el-col :span="4">
                <h4>用户名:</h4>
              </el-col>
              <el-col :span="17">
                <el-input v-model="userName" class="input" placeholder="请输入用户名" />
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="4">
                <h4>密码:</h4>
              </el-col>
              <el-col :span="17">
                <el-input v-model="passWord" type="password" class="input" placeholder="请输入密码" />
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
                <el-button class="Login-btn" size="large" round>登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</el-button>
              </el-col>
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

export default {
  name: "LoginPage",
  components: {
    Login_LeftPanel_Title
  },
  data(){
    return{
      backGroundImg:require('../assets/images/login_background.png'),
      bottomImg:require('../assets/images/login_leftpanel_bottom.png'),
      loginCss:require('../assets/css/Login.css')
    }
  },
  methods:{
    forgetPassword() {
      alert("咕咕咕。还没写好啊...")
    }
  }
}
</script>