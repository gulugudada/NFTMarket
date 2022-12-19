<template>
	<div class="blog-contain">
		<el-container>
		    <el-header style="padding: 0;height: auto;">
				<img style="width: 100%;height: 100px;" src="../../assets/blog_background.png"/>
				<el-menu
				    default-active="/blog/recommend"
				    class="el-menu-demo"
				    mode="horizontal"
				    :router="true"
				>
					<el-menu-item index="/blog/recommend">推荐</el-menu-item>
					<el-menu-item index="/blog/attention">关注</el-menu-item>
					<el-menu-item index="/blog/latest">最新</el-menu-item>
					<el-menu-item index="/daiding">待定</el-menu-item>
					<el-menu-item index="/daiding">待定</el-menu-item>
				</el-menu>
			</el-header>
		    <el-main style="padding-left: 0;padding-right: 0;">
				<router-view></router-view>
			</el-main>
		</el-container>
	</div>
</template>

<script lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance } from 'vue'
	import { ElNotification } from 'element-plus'
	const { proxy } = getCurrentInstance()
	onMounted(() => {
		proxy.$router.push("/blog/recommend")
	})
</script>

<style scoped>
	.blog-contain {
		position : absolute;
		width: 90%;
		height: auto;
		margin-left: 65px;
	}
	.el-menu-demo {
		margin-top: 5px;
		box-shadow: 0 4px 30px 0 rgb(232 232 237 / 50%);
	}
	.el-menu--horizontal>.el-menu-item.is-active {
		border-bottom: 2px;
		color: #fc5531;
	}
</style>
