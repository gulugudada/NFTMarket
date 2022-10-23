<template>
	<div class="common-layout" style="width: 80%;height: auto;padding-left: 140px;">
	    <el-container>
			<el-aside style="width: 100px;height: auto;">
				<el-menu
				default-active="/userhome/userinfo"
				class="el-menu-vertical-demo"
				:unique-opened="true"
				:router="true"
				>
					<el-menu-item index="/userhome/userinfo">个人资料</el-menu-item>
				</el-menu>
			</el-aside>
			<el-main>
				<router-view></router-view>
			</el-main>
		</el-container>
	</div>
</template>

<script lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance } from 'vue'
	const { proxy } = getCurrentInstance()
	onMounted(() => {
		proxy.$router.push("/userhome/userinfo")
	})
</script>

<style>
</style>
