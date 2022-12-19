<template>
	<div class="like-contain">
		<div class="like-contain-title">
			<span>赞和收藏</span>
		</div>
	</div>
</template>

<script lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance } from 'vue'
	import { ElNotification } from 'element-plus'
	import Cookies from "ts-cookies"
	const { proxy } = getCurrentInstance()
	onMounted(() => {
		
	})
</script>

<style>
	.like-contain {
		width: 100%;
		height: 100%;
	}
	.like-contain-title {
		width: 95%;
		height: 56px;
		margin-left: 20px;
		line-height: 60px;
		font-weight: 600;
		border-bottom: 1px solid #f0f0f2;
	}
</style>
