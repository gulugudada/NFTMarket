<template>
	<div class="message-contain">
	    <el-container>
			<el-aside width="180px" class="el-aside-contain" style="height: 210px;margin-right: 20px;">
				<el-menu
					default-active="/message/comment"
					style="height: 48px;margin-top: 8px;text-align: center;"
					:router="true"
				>
					<el-menu-item class="menu-item" index="/message/comment">
						<el-badge :is-dot="$store.state.haveComment">
							评论和@
						</el-badge>
					</el-menu-item>
					<el-menu-item class="menu-item" index="/message/attention">
						<el-badge :is-dot="$store.state.haveAttention">
							新增粉丝
						</el-badge>
					</el-menu-item>
					<el-menu-item class="menu-item" index="/message/like">
						<el-badge :is-dot="$store.state.haveLike">
							赞和收藏
						</el-badge>
					</el-menu-item>
					<el-menu-item class="menu-item" index="/chat">
						<el-badge :is-dot="haveChat()">
							私信
						</el-badge>
					</el-menu-item>
				</el-menu>
			</el-aside>
			<el-main class="el-main-contain">
				<router-view></router-view>
			</el-main>
	    </el-container>
	  </div>
</template>

<script lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance } from 'vue'
	import { ElNotification } from 'element-plus'
	import Cookies from "ts-cookies"
	const { proxy } = getCurrentInstance()
	//用来检查所有会话是否都没有未读消息
	const haveChat = () => {
		for (let i = 0;i < proxy.$store.state.chatList.length;i++) {
			if (proxy.$store.state.chatList[i].haveMessage) {
				proxy.$store.state.haveMessage = true;
				return true;
			}
		}
		proxy.$store.state.haveMessage = false;
		return false;
	}
	onMounted(() => {
		proxy.$router.push("/message/comment")
	})
</script>

<style scoped>
	.message-contain {
		position : absolute;
		width: 75%;
		height: 900px;
		margin-left: 170px;
	}
	.menu-item {
		height: 48px;
		font-size: 10px;
		font-weight: 500;
		line-height: 48px;
	}
	.el-menu-item.is-active {
		color: #303133;
		background: #fafafa;
	}
	.el-menu-item:hover {
		color: #636363;
		background: #fafafa;
	}
	.el-aside-contain,.el-main-contain {
		padding: 0;
		border-radius: 2px;
		border-top: 2px solid #e8e8ed;
		box-shadow: 0px 16px 48px 16px rgba(0, 0, 0, .08), 0px 12px 32px rgba(0, 0, 0, .12), 0px 8px 16px -8px rgba(0, 0, 0, .16);
	}
	.el-main-contain {
		height: 880px;
	}
</style>

<style>
	#app > div.home > section > main > div > section > aside > ul > li > div > sup {
		top: 14px !important;
		right: 2px;
	}
</style>
