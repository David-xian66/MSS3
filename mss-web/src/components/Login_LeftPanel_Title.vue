<template>
<div>
  <h1 class="Title">{{title}}</h1>
</div>
</template>

<style :src="titleCss"/>

<script>
export default {
  name: "Login_LeftPanel_Title",
  props: ['title'],
  data(){
    return{
      titleCss:require('../assets/css/Login_LeftPanel_Title.css')
    }
  }
}
</script>