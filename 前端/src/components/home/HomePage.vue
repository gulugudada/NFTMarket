<template>
	<div class="blog-contain">
		<el-container>
		    <el-header style="padding: 0;height: 160px;">
				<img style="width: 100%;height: 100px;" src="../../assets/blog_recommend_background.png"/>
				<el-tabs type="border-card">
				    <el-tab-pane label="User">User</el-tab-pane>
				    <el-tab-pane label="Config">Config</el-tab-pane>
				    <el-tab-pane label="Role">Role</el-tab-pane>
				    <el-tab-pane label="Task">Task</el-tab-pane>
				</el-tabs>
			</el-header>
		    <el-container>
		        <el-aside width="200px">Aside</el-aside>
		        <el-main>Main</el-main>
		    </el-container>
		</el-container>
	</div>
</template>

<script lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance } from 'vue'
	import { ElNotification } from 'element-plus'
	const { proxy } = getCurrentInstance()
	onMounted(() => {
		
	})
</script>

<style>
	.blog-contain {
		position : absolute;
		width: 90%;
		height: 1000px;
		margin-left: 65px;
		background-color: #67C23A;
	}
</style>
