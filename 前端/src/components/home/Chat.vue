<template>
	<div class="message-contain">
		<el-tabs 
			class="message-tabs" 
			type="border-card" 
			tab-position="left" 
			v-model="select" 
			:key="selectNum"
			@tab-click="clickTab"
			@tab-remove="removeTab"
		>
			<el-tab-pane class="custom-tabs-pane" disabled>
				<template #label >
					<div style="height: 80px;display: flex;">
						<a title="用户首页" @click="this.$router.push('/userhome')">
							<el-avatar :size="60" :src="$store.state.circleUrl" style="margin-top: 10px;"></el-avatar>
						</a>
						<p style="padding-top: 20px;padding-left: 10px;font-size: 18px;color: #000000;">{{this.$store.state.username}}</p>
					</div>
				</template>
				<el-empty description="您还未选中或者发起聊天，快去跟好友聊一聊吧" style="margin-top: 120px;"/>
			</el-tab-pane>
			<!-- 下面的name比数组的index要大1 -->
		    <el-tab-pane v-for="(chat,index) in $store.state.chatList" closable :name="index+1+''" class="custom-tabs-pane">
				<template #label>
					<div style="height: 80px;display: flex;">
						<el-badge :hidden="!chat.haveMessage" :value="chat.unreadMessage" type="danger">
						    <el-avatar :size="60" :src="$store.state.circleUrl" style="margin-top: 10px;"></el-avatar>
						</el-badge>
						<div>
							<p style="height: 30px;padding-top: 2px;padding-left: 10px;font-size: 16px;color: #000000;">{{chat.username}}</p>
							<span style="padding-left: 12px;font-size: 14px;color: #909399;">{{chat.username}}</span>
						</div>
						<p style="padding-left: 100px;font-size: 10px;color: #909399;">{{chat.username}}</p>
					</div>
				</template>
				<div class="chat-contain">
					<el-container>
					    <el-header height="30px" style="font-size: 16px;text-align: center;">
							{{chat.username}}
						</el-header>
						<el-divider />
					    <el-main>
							<el-scrollbar>
							    <div v-for="item in chat.message" class="message-demo">
									<div style="width: 140px;height: 20px;padding-left: 347px;text-align: center;">
										<p style="width: auto;background-color: #ccc;border-radius: 15px 15px 15px 15px;">
											{{timeDecide(item.sendTime)}}
										</p>
									</div>
									<div v-if="item.from === chat.account">
										<a title="用户首页" @click="this.$router.push('/userhome')">
											<el-avatar :size="40" :src="item.circleUrl" style="margin-top: 10px;"></el-avatar>
										</a>
										<span style="position: absolute;margin-top: 20px;margin-left: 10px;">
											{{item.content}}
										</span>
									</div>
									<div v-else style="height: 74px;">
										<a title="用户首页" @click="this.$router.push('/userhome')" style="float: right;">
											<el-avatar :size="40" :src="item.circleUrl" style="margin-top: 10px;"></el-avatar>
										</a>
										<span style="float: right;margin-top: 20px;margin-right: 10px;">
											{{item.content}}
										</span>
									</div>
								</div>
							</el-scrollbar>
						</el-main>
					    <el-footer height="180px">
							<el-input
								v-model="textarea[index]"
								:rows="6"
								type="textarea"
								:clearable="true"
								maxlength="500"
								show-word-limit
								:input="inputToSend(index)"
								style="font-size: 18px;color: #000000;"
							/>
							<span style="position: absolute;bottom: 25px;right: 40px;font-size: 12px;color: #ccc;">按下Enter发送内容</span>
						</el-footer>
					</el-container>
				</div>
			</el-tab-pane>
		</el-tabs>
	</div>
</template>

<script  lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance } from 'vue'
	import { ElNotification } from 'element-plus'
	const { proxy } = getCurrentInstance()
	const emit = defineEmits(['sendMessage'])
	const select = ref('0')
	const selectNum = ref(0)
	const textarea = reactive([])
	const inputToSend = (index) => {
		if (proxy.textarea[index] !== undefined) {
			if (proxy.textarea[index].slice(proxy.textarea[index].length-1,proxy.textarea[index].length) === '\n') {
				const message = {
					id: 0,
					from: proxy.$store.state.account,
					to: proxy.$store.state.chatList[index].account,
					content: proxy.textarea[index].slice(0,proxy.textarea[index].length-1),
					sendTime: timestampToTime()
				};
				if (index !== 0) {
					let list = [];
					let list1 = [];
					list.push(proxy.$store.state.chatList.splice(index));
					//textarea也需要换位
					list.push(textarea.splice(index));
					for (let i = 0;i < proxy.$store.state.chatList.length;i++) {
						list.push(proxy.$store.state.chatList[i]);
						list1.push(textarea[i]);
					}
					proxy.$store.state.chatList = list;
					proxy.select = '1';
				}
				emit('sendMessage',message);
				console.log(proxy.$store.state.chatList);
				proxy.$store.state.chatList[0].message.push(message);
				proxy.textarea[0] = '';
				renovate();
			}
		}
	}
	const timestampToTime = () => {
		let time = new Date();
		let times = time.toLocaleString('en-US',{hour12: false}).split(" ");
		let time1 = times[1].split(":")
		let time2 = time1[0]+":"+time1[1]
		let mdy = times[0]
		mdy = mdy.split('/')
		let month = parseInt(mdy[0]);
		let day = parseInt(mdy[1]);
		let year = parseInt(mdy[2])
		return year + '-' + month + '-' + day + ' ' + time2
	}
	//处理显示消息时间
	const timeDecide = (value: any) => {
		const systemTime = timestampToTime().split(" ")[0];
		if (value.split(' ')[0] === systemTime) {
			return value.split(' ')[1];
		}
		return value;
	}
	const complement = (value: any) => {
		return value < 10 ? `0${value}` : value;
	}
	//点击会话清除红点
	const clickTab = (pane: TabsPaneContext, ev: Event) => {
		proxy.$store.state.chatList[pane.index-1].haveMessage = false;
		proxy.$store.state.chatList[pane.index-1].unreadMessage = 0;
	}
	const removeTab = (name: TabPanelName) => {
		//删除会话时要删除会话和对应的输入框
		proxy.$store.state.chatList.splice(name-1,1);
		textarea.splice(name-1,1);
	}
	const renovate = () => {
		//用来刷新页面
		proxy.selectNum += 1;
	}
	const checkSelect = (flag) => {
		renovate();
		// if (select !== '0') {
		// 	var a = Number(select);
		// 	//当来消息时判断该消息是不是就是给当前所在的那个会话的
		// 	if (proxy.$store.state.chatList[a-1].account === from) {
		// 		return true;
		// 	}
		// 	else {
		// 		return false;
		// 	}
		// }
	}
	onMounted(() => {
		let chatList = proxy.$store.state.chatList;
		//当前有几个会话就需要有几个textarea
		for (let i = 0;i < chatList.length;i++) {
			textarea.push('');
		}
		proxy.$store.state.chat = proxy;
	})
</script>

<style>
	.message-contain {
		position : absolute;
		width: 80%;
		height: 670px;
		margin-left: 140px;
	}
	.message-tabs {
		width: 100%;
		height: 655px;
	}
	.custom-tabs-pane {
		width: 100px;
		height: 100px;
	}
	.el-tabs--left .el-tabs__nav.is-left, .el-tabs--left .el-tabs__nav.is-right, .el-tabs--right .el-tabs__nav.is-left, .el-tabs--right .el-tabs__nav.is-right {
		width: 300px;
	}
	.el-tabs--left .el-tabs__item.is-left {
		text-align: left;
		height: 80px;
	}
	.el-tabs--border-card>.el-tabs__content {
		height: 600px;
	}
	#pane-0 > div > div.el-empty__description > p {
		width: 300px;
	}
	.custom-tabs-pane {
		width: 100%;
		height: 100%;
	}
	.chat-contain {
		width: 100%;
		height: 100%;
	}
	.el-divider--horizontal {
		margin: 0;
	}
	#pane-1 > div > section > main {
		height: 400px;
	}
	.el-textarea .el-input__count {
		right: 0;
		left: 10px;
	}
	.message-demo {
		width: 100%;
		height: auto;
	}
	.el-badge__content--danger {
		margin-top: 17px;
		margin-right: 14px;
	}
	.el-tabs__item .el-icon-close {
		position: absolute;
		top: 33px;
		right: 10px;
	}
</style>
