<template>
  <!-- <img src="./assets/logo.png">
  <div>
    <p>
      If Element Plus is successfully added to this project, you'll see an
      <code v-text="'<el-button>'"></code>
      below
    </p>
    <el-button type="primary">el-button</el-button>
  </div>
  <HelloWorld msg="Welcome to Your Vue.js App"/> -->
  <router-view></router-view>
</template>

<script lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance } from 'vue'
	const { proxy } = getCurrentInstance()
	onMounted(() => {
		proxy.$router.push("/")
	})
</script>

<style>
	*{
		margin : 0;
		padding : 0;
	}
</style>
