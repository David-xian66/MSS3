<template>
  <base-main title="服务端设置" index="2-2">
    <el-card class="card" body-style="padding: 10px; padding-left: 10px" shadow="hover">
      <h1 class="card_title">基本设置</h1>
      <el-divider/>
      <h3>哎呀，还没写qwq</h3>
    </el-card>
  </base-main>
</template>

<!--suppress HtmlUnknownTarget -->
<style :src="baseMainCss"/>

<script>
import BaseMain from "@/components/BaseMain";
export default {
  name: "ServerSetting",
  components: {BaseMain},
  data(){
    return{
      baseMainCss:require('../assets/css/BaseMain.css'),
    }
  }
}
</script>

<style scoped>

</style>