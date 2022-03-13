<template>
<h1>这里是就是主页啦！</h1>
</template>

<script>
import {getCookie} from "@/assets/js/cookie";
import axios from "axios";

export default {
  name: "HomePage",
  mounted() {
    this.isLogin();
  },
  methods:{
    isLogin(){
      let userinfo = getCookie("Token");
      if(userinfo.length === 0){
        this.$router.push({path : "#/login"});
      }
      axios
          .get('/api-login/isLive',{
            headers:{
              'Token' : userinfo,
            },
          })
          .then(response => {
            if(response.data.code.startsWith("-5")){
              this.$router.push({path : "/login"});
            }
          });
    }
  }
}
</script>

<style scoped>

</style>