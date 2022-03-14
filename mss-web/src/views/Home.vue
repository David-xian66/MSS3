<template>
  <base-main title="概览" index="1">
    <el-row class="myCard">
      <el-col :span="24">
        <el-card body-style="padding: 10px; padding-left: 20px" shadow="hover">
          <b>温馨提示：</b>
          主页还没有做好哦，这个页面是用来测试的。没啥别的用处
        </el-card>
      </el-col>
    </el-row>
  </base-main>
</template>

<script>

import BaseMain from "@/components/BaseMain";

export default {
  name: "HomePage",
  components :{
    BaseMain
  }
}
</script>

<style scoped>

</style>