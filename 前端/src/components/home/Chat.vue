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
							<el-avatar :size="60" :src="$store.state.url + $store.state.circleUrl" style="margin-top: 10px;"></el-avatar>
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
						    <el-avatar :size="60" :src="$store.state.url+chat.circleUrl" style="margin-top: 10px;"></el-avatar>
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
							<el-scrollbar always>
							    <div v-for="(item,index1) in chat.message" class="message-demo">
									<div v-if="timeCheck(chat,index1)" style="width: 140px;height: 20px;padding-left: 347px;text-align: center;">
										<p style="width: auto;background-color: #ccc;border-radius: 15px 15px 15px 15px;">
											{{timeDecide(item.sendTime)}}
										</p>
									</div>
									<div v-if="item.from === chat.account">
										<a title="用户首页" @click="this.$router.push('/userhome')" style="float: left;">
											<el-avatar :size="40" :src="$store.state.url+chat.circleUrl" style="margin-top: 10px;"></el-avatar>
										</a>
										<span style="float: left;margin-top: 20px;margin-left: 10px;">
											{{item.content}}
										</span>
										<div style="clear: both;"></div>
									</div>
									<div v-else>
										<a title="用户首页" @click="this.$router.push('/userhome')" style="float: right;margin-right: 18px;">
											<el-avatar :size="40" :src="$store.state.url + $store.state.circleUrl" style="margin-top: 10px;"></el-avatar>
										</a>
										<span style="float: right;margin-top: 20px;margin-right: 10px;">
											{{item.content}}
										</span>
										<div style="clear: both;"></div>
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
								style="font-size: 18px;color: #000000;"
							/>
							<el-button style="position: absolute;right: 40px;bottom: 25px;" type="primary" circle @click="inputToSend(index)">
								<el-icon :size="20"><Promotion /></el-icon>
							</el-button>
						</el-footer>
					</el-container>
				</div>
			</el-tab-pane>
		</el-tabs>
	</div>
</template>

<script  lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance,toRaw  } from 'vue'
	import { ElNotification } from 'element-plus'
	import $ from 'jquery'
	const { proxy } = getCurrentInstance()
	const emit = defineEmits(['sendMessage'])
	const select = ref('0')
	const selectNum = ref(0)
	const textarea = reactive([])
	const inputToSend = (index) => {
		if (proxy.textarea[index] !== undefined) {
			if (proxy.textarea[index] !== '') {
				const message = {
					id: 0,
					from: proxy.$store.state.account,
					to: proxy.$store.state.chatList[index].account,
					content: proxy.textarea[index],
					sendTime: timestampToTime()
				};
				if (index !== 0) {
					//将Proxy对象转换成原始对象
					let chatList = toRaw(proxy.$store.state.chatList);
					let list = [];
					//必须保持对象的类型不能变
					const list1 = reactive([]);
					list.push(chatList[index]);
					//textarea也需要换位
					list1.push(proxy.textarea[index]);
					for (let i = 0;i < chatList.length;i++) {
						if (i !== index) {
							list.push(chatList[i]);
							list1.push(proxy.textarea[i]);
						}
					}
					proxy.$store.state.chatList = list;
					proxy.textarea = list1;
					proxy.select = '1';
				}
				emit('sendMessage',message);
				proxy.$store.state.chatList[0].message.push(message);
				proxy.textarea[0] = '';
				renovate();
			}
			else {
				ElNotification({
				    title: 'Error',
				    message: '内容不能为空！',
				    type: 'error',
				})
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
	//消息时间检查，如果时间一样后面的消息不显示时间
	const timeCheck =(chat,index) => {
		if (chat.message[index-1] === undefined) {
			return true;
		}
		else {
			if (chat.message[index-1].sendTime === chat.message[index].sendTime) {
				return false;
			}
		}
		return true;
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
		proxy.textarea.splice(name-1,1);
		select.value = '0';
	}
	const renovate = () => {
		//用来刷新页面
		proxy.selectNum += 1;
	}
	const checkSelect = (flag) => {
		if (flag !== -1) {
			//是否是当前会话
			if (flag === parseInt(select.value)-1) {
				select.value = '1';
			}
			else {
				if (select.value !== '0') {
					select.value = parseInt(select.value) + 1 + '';
				}
				proxy.$store.state.chatList[0].haveMessage = true;
				proxy.$store.state.chatList[0].unreadMessage += 1;
			}
		}
		else {
			if (select.value !== '0') {
				select.value = parseInt(select.value) + 1 + '';
			}
		}
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
	.el-notification .el-icon-success {
		color: #67c23a !important;
	}
</style>
