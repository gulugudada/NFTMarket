<template>
	<div class="comment-contain">
		<div class="comment-contain-title">
			<span>评论和@</span>
			
		</div>
		<div class="comment-contain-contain">
			666
		</div>
		<div class="comment-contain-bottom">
			<el-pagination
			    v-model:current-page="currentPage3"
			    v-model:page-size="pageSize3"
			    :small="small"
			    :disabled="disabled"
			    :background="true"
			    layout="total,prev, pager, next, jumper"
			    :total="total"
			    @size-change="handleSizeChange"
			    @current-change="handleCurrentChange"
			/>
		</div>
	</div>
</template>

<script lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance } from 'vue'
	import { ElNotification } from 'element-plus'
	import Cookies from "ts-cookies"
	const { proxy } = getCurrentInstance()
	const total = ref(0)
	onMounted(() => {
		
	})
</script>

<style scoped>
	.comment-contain {
		width: 100%;
		height: 100%;
	}
	.comment-contain-title {
		width: 95%;
		height: 56px;
		margin-left: 20px;
		line-height: 60px;
		font-weight: 600;
		border-bottom: 1px solid #f0f0f2;
	}
	.comment-contain-contain {
		width: 95%;
		height: 700px;
		margin-left: 22px;
		background-color: #42B983;
	}
	.comment-contain-bottom {
		height: 80px;
		margin-top: 8px;
		padding-top: 30px;
		text-align: center;
	}
</style>
