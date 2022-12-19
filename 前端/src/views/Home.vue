<template>
	<div class="home">
		<el-container>
			<el-header style="padding: 0;height: auto;">
				<el-carousel height="190px" :interval="5000" arrow="always">
				    <el-carousel-item v-for="item in bannerImage" :key="item" style="height: 190px;">
						<div :style="'background-image: url(' + item + ');height :190px;'"></div>
				    </el-carousel-item>
				</el-carousel>
				<div class="toolbar">
					<el-affix :offset="0" style="height: 56px;">
						<el-row :gutter="20" style="height: 56px;width: 100%;background-color: #ffffff;">
							<el-col :span="3">
								<a class="toolbar-logo" title="NFT商城首页" href="/">
									<p>NFT交易平台</p>
								</a>
							</el-col>
							<el-col :span="6">
								<el-menu
									default-active="/blog"
									class="el-menu-demo"
									mode="horizontal"
									style="width: 370px;height: 59px;"
									:router="true"
								>
									<el-menu-item index="/blog">博客</el-menu-item>
									<el-menu-item index="/mall">商城</el-menu-item>
									<el-menu-item index="/merchant">商家</el-menu-item>
									<el-menu-item index="/auctionhouse">拍卖行</el-menu-item>
									<el-menu-item index="/blockchain">区块链</el-menu-item>
								</el-menu>
							</el-col>
							<el-col :span="8">
								<div class="mt-4">
								    <el-input
										v-model="search"
										placeholder="请输入搜索的内容"
								    >
								    <template #append>
								        <el-button @click="">
											<Search style="width: 20px;height: 20px;"/>
										</el-button>
								    </template>
								    </el-input>
								</div>
							</el-col>
							<el-col :span="7">
								<div style="width: 70px;height: 100%;float: left;margin-left: 10px;">
									<el-dropdown v-if="$store.state.isLogin" style="margin-right: 30px">
									    <a title="用户首页" @click="this.$router.push('/userhome')">
									    	<el-avatar :size="50" :src="$store.state.url + $store.state.circleUrl"  style="margin-top: 3.5px;margin-left: 10px;margin-right: 10px;"></el-avatar>
									    </a>
									    <template #dropdown>
											<el-dropdown-menu>
												<el-dropdown-item>{{this.$store.state.username}}</el-dropdown-item>
												<el-dropdown-item divided>{{this.$store.state.account}}</el-dropdown-item>
												<el-dropdown-item divided>个人资料</el-dropdown-item>
												<el-dropdown-item>我的商品</el-dropdown-item>
												<el-dropdown-item>动态空间</el-dropdown-item>
												<el-dropdown-item divided @click="quitLogin">退出登录</el-dropdown-item>
											</el-dropdown-menu>
									    </template>
									</el-dropdown>
									<el-button type="text" style="font-size: 16px;color: #000000;margin-top: 9px;" @click="dialogLoginVisible = true" v-else>登录/注册</el-button>
								</div>
								<el-button type="text" style="font-size: 16px;color: #000000;padding-top: 21px;padding-left: 15px;">收藏</el-button>
								<el-button type="text" style="font-size: 16px;color: #000000;padding-top: 21px;padding-left: 5px;">足迹</el-button>
								<el-button type="text" style="font-size: 16px;color: #000000;padding-top: 21px;padding-left: 5px;">动态</el-button>
								<el-dropdown style="padding-left: 15px;">
									<el-button type="text" style="font-size: 16px;color: #000000;">
										<el-badge :is-dot="haveMessage()" @click="toMessage('message')">
											<div style="margin-bottom: 3px;">
												消息
											</div>
										</el-badge>
									</el-button>
									<template #dropdown>
									    <el-dropdown-menu>
											<el-dropdown-item @click="toMessage('message/comment')">
												<el-badge :is-dot="$store.state.haveComment">
													评论和@
												</el-badge>
											</el-dropdown-item>
											<el-dropdown-item @click="toMessage('message/attention')">
												<el-badge :is-dot="$store.state.haveAttention">
													新增粉丝
												</el-badge>
											</el-dropdown-item>
											<el-dropdown-item @click="toMessage('message/like')">
												<el-badge :is-dot="$store.state.haveLike">
													赞和收藏
												</el-badge>
											</el-dropdown-item>
											<el-dropdown-item @click="toMessage('chat')">
												<el-badge :is-dot="haveChat()">
													私信
												</el-badge>
											</el-dropdown-item>
									    </el-dropdown-menu>
									</template>
								</el-dropdown>
								<el-dropdown split-button type="primary" @click="open()" style="padding-left: 15px;">
								    铸造NFT
								    <template #dropdown>
								        <el-dropdown-menu>
											<el-dropdown-item @click="">发动态</el-dropdown-item>
											<el-dropdown-item @click="connectMetaMask()">连接MetaMask</el-dropdown-item>
								        </el-dropdown-menu>
								    </template>
								</el-dropdown>
							</el-col>
						</el-row>
					</el-affix>
				</div>
			</el-header>
			<el-main style="height: auto;">
				<router-view ref="chat" @sendMessage="sendMessage"></router-view>
				<el-backtop :right="60" :bottom="60" />
			</el-main>
		</el-container>
	</div>
	<el-dialog v-model="dialogLoginVisible" title="登录" center :before-close="cloceLogin()">
	    <el-form 
			:model="loginForm"
			label-position="right"
			label-width="60px"
		>
			<el-form-item label="账号:" style="width: 400px;padding-left: 160px;">
				<el-input v-model="loginForm.account" clearable style="font-size: 20px;width: 300px;"/>
			</el-form-item>
			<el-form-item label="密码:" style="width: 400px;padding-left: 160px;">
				<el-input v-model="loginForm.password" clearable show-password style="font-size: 20px;width: 300px;"/>
			</el-form-item>
	    </el-form>
		<template #footer>
			<div>
				<el-button type="primary" style="margin-right: 100px;" @click="login(0)">登录</el-button>
				<el-button @click="loginForm.account='',loginForm.password=''">重置</el-button>
			</div>
			<el-button type="text" style="color: #000000;margin-right: 20px;margin-top: 10px;" @click="dialogResetPasswordVisible = true,dialogLoginVisible = false,cloceLogin()">忘记密码？</el-button>
		    <el-button type="text" @click.active="dialogRegisterVisible = true,dialogLoginVisible = false,loginForm.account = '',loginForm.password = ''">没有账号？那就注册一个吧</el-button>
		</template>
	</el-dialog>
	<el-dialog v-model="dialogRegisterVisible" title="注册" center :before-close="cloceRegister()">
	    <el-form
			:model="registerForm"
			label-position="right"
			label-width="80px"
		>
			<el-form-item label="账号:" style="padding-left: 160px;">
				<el-input v-model="registerForm.account" clearable style="font-size: 20px;width: 300px;"/>
			</el-form-item>
			<el-form-item label="验证码:" style="padding-left: 160px;">
				<el-input v-model="registerForm.code" clearable style="font-size: 20px;width: 150px;"/>
				<el-button type="primary" style="width: 120px;margin-left: 30px;padding-top: 15px;" @click="getCode()" :disabled="canGetCode">
					<span v-if="!canGetCode">获得验证码</span>
					<span v-if="canGetCode">等待{{count}}秒</span>
				</el-button>
			</el-form-item>
			<el-form-item label="用户名:" style="padding-left: 160px;">
				<el-input v-model="registerForm.username" clearable style="font-size: 20px;width: 300px;"/>
			</el-form-item>
			<el-form-item label="密码:" style="padding-left: 160px;">
				<el-input v-model="registerForm.password" clearable show-password style="font-size: 20px;width: 300px;"/>
			</el-form-item>
			<el-form-item label="确认密码:" style="padding-left: 160px;">
				<el-input v-model="password2" clearable show-password style="font-size: 20px;width: 300px;"/>
			</el-form-item>
	    </el-form>
		<template #footer>
			<div>
				<el-button type="primary" @click="register()">注册</el-button>
				<el-button @click="registerForm.account='',registerForm.code='',registerForm.username='',registerForm.password='',password2=''">重置</el-button>
			</div>
		</template>
	</el-dialog>
	<el-dialog v-model="dialogResetPasswordVisible" title="重置密码" center :before-close="cloceResetPassword()">
	    <el-form
			:model="resetPasswordForm"
			label-position="right"
			label-width="80px"
		>
			<el-form-item label="账号:" style="padding-left: 160px;">
				<el-input v-model="resetPasswordForm.account" clearable style="font-size: 20px;width: 300px;"/>
			</el-form-item>
			<el-form-item label="验证码:" style="padding-left: 160px;">
				<el-input v-model="resetPasswordForm.code" clearable style="font-size: 20px;width: 150px;"/>
				<el-button type="primary" style="width: 120px;margin-left: 30px;padding-top: 15px;" @click="getCode1()" :disabled="canGetCode1">
					<span v-if="!canGetCode1">获得验证码</span>
					<span v-if="canGetCode1">等待{{count_}}秒</span>
				</el-button>
			</el-form-item>
			<el-form-item label="密码:" style="padding-left: 160px;">
				<el-input v-model="resetPasswordForm.password" clearable show-password style="font-size: 20px;width: 300px;"/>
			</el-form-item>
			<el-form-item label="确认密码:" style="padding-left: 160px;">
				<el-input v-model="password3" clearable show-password style="font-size: 20px;width: 300px;"/>
			</el-form-item>
	    </el-form>
		<template #footer>
			<div>
				<el-button type="primary" @click="resetPassword()">确认</el-button>
				<el-button @click="resetPasswordForm.account='',resetPasswordForm.code='',resetPasswordForm.username='',resetPasswordForm.password='',password3=''">重置</el-button>
			</div>
		</template>
	</el-dialog>
</template>

<script lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance } from 'vue'
	import { ElNotification } from 'element-plus'
	import Cookies from "ts-cookies"
	const { proxy } = getCurrentInstance()
	const bannerImage = ref([
		"https://s1.imagehub.cc/images/2021/11/09/_b5770dfc-28f3-40ae-9008-1f175d8a5391-10886022.webp",
		"https://s1.imagehub.cc/images/2021/11/09/_374c7865-67ea-45e3-877c-d1c67b92cc5d-10886022.webp",
		"https://s1.imagehub.cc/images/2021/11/09/_df764511-2618-428d-8373-c6c78aede70c-10886022.webp"
	])
	const select = ref('商品')
	const search = ref('')
	const dialogLoginVisible = ref(false)
	const dialogRegisterVisible = ref(false)
	const dialogResetPasswordVisible = ref(false)
	const canGetCode = ref(false)
	const canGetCode1 = ref(false)
	const count = ref(0)
	const count_ = ref(0)
	let socket
	const loginForm = reactive({
		account:'',
		password:''
	})
	const registerForm = reactive({
		account:'',
		code:'',
		username:'',
		password:''
	})
	const resetPasswordForm = reactive({
		account:'',
		code:'',
		password:''
	})
	const password2 = ref('')
	const password3 = ref('')
	const handleSearch = () => {
	}
	const cloceLogin = () => {
		loginForm.account = '',
		loginForm.password = ''
	}
	const cloceRegister = () => {
		registerForm.account = '',
		registerForm.code = '',
		registerForm.username = '',
		registerForm.password = ''
		password2.value = ''
	}
	const cloceResetPassword = () => {
		resetPasswordForm.account = '',
		resetPasswordForm.code = '',
		resetPasswordForm.password = ''
		password3.value = ''
	}
	//登录表单提交
	const login = (flag: int) => {
		var sha1 = require('sha-1')
		if(loginForm.account === ''){
			proxy.$message.error("账号不能为空！")
		}
		else if(loginForm.password === ''){
			proxy.$message.error("密码不能为空！");
		}
		else {
			var that = proxy
			if (flag === 0) {
				that.loginForm.password = sha1(that.loginForm.password)
			}
			var password = that.loginForm.password
			proxy.$axios.post(that.$store.state.url + '/login',loginForm)
			.then(res => {
				if(res.data.code === -1){
					that.loginForm.password = '';
					that.$message.error(res.data.msg);
				}
				else {
					that.$message.success(res.data.msg);
					that.$store.state.isLogin = true;
					that.$store.state.circleUrl = res.data.avatar;
					that.$store.state.username = res.data.username;
					that.$store.state.account = res.data.account;
					that.$store.state.password = password;
					that.dialogLoginVisible = false;
					cloceLogin();
					connectSocket();
				}
			})
			.catch(err => {
				console.log(err)
			})
		}
	}
	//获取验证码
	const getCode = () => {
		if(registerForm.account === ''){
			proxy.$message.error("请先输入手机号！");
		}
		else {
			var that = proxy;
			proxy.$axios.post(that.$store.state.url + '/getCode',{
				account:registerForm.account,
			})
			.then(res => {
				if(res.data.code === -1){
					that.$message.error(res.data.msg);
				}
				else {
					countDown();
					that.$message.success(res.data.msg);
				}
			})
			.catch(err => {
				console.log(err)
			})
		}
	}
	const getCode1 = () => {
		if(resetPasswordForm.account === ''){
			proxy.$message.error("请先输入手机号！");
		}
		else {
			var that = proxy;
			proxy.$axios.post(that.$store.state.url + '/getCode1',{
				account:resetPasswordForm.account,
			})
			.then(res => {
				if(res.data.code === -1){
					that.$message.error(res.data.msg);
				}
				else {
					countDown1();
					that.$message.success(res.data.msg);
				}
			})
			.catch(err => {
				console.log(err)
			})
		}
	}
	const countDown = () => {
		proxy.canGetCode = true;
		proxy.count = 60;
		var times = setInterval(() => {
		    proxy.count--; //递减
		    if (count.value <= 0) {
				// <=0 变成获取按钮
				canGetCode = false;
				clearInterval(times);
		    }
		}, 1000); //1000毫秒后执行
	}
	const countDown1 = () => {
		proxy.canGetCode1 = true;
		proxy.count_ = 60;
		var times = setInterval(() => {
		    proxy.count_--; //递减
		    if (proxy.count_ <= 0) {
				// <=0 变成获取按钮
				proxy.canGetCode1 = false;
				clearInterval(times);
		    }
		}, 1000); //1000毫秒后执行
	}
	const register = () => {
		var sha1 = require('sha-1')
		if(registerForm.account === ''){
			proxy.$message.error("请先输入手机号！");
		}
		else if(registerForm.code === ''){
			proxy.$message.error("验证码不能为空！");
		}
		else if(registerForm.username === ''){
			proxy.$message.error("用户名不能为空！");
		}
		else if(registerForm.password === ''){
			proxy.$message.error("密码不能为空！");
		}
		else if(registerForm.password !== proxy.password2){
			proxy.$message.error("两次输入的密码不一致！");
		}
		else {
			var that = proxy;
			that.registerForm.password = sha1(that.registerForm.password)
			proxy.$axios.post(that.$store.state.url + '/registerAccount',registerForm)
			.then(res => {
				if(res.data.code === -1){
					that.registerForm.password = '';
					that.$message.error(res.data.msg);
				}
				else {
					that.$message.success(res.data.msg);
					that.dialogRegisterVisible = false;
					cloceRegister();
				}
			})
			.catch(err => {
				console.log(err)
			})
		}
	}
	const resetPassword = () => {
		if(resetPasswordForm.account === ''){
			proxy.$message.error("请先输入手机号！");
		}
		else if(resetPasswordForm.code === ''){
			proxy.$message.error("验证码不能为空！");
		}
		else if(resetPasswordForm.password === ''){
			proxy.$message.error("密码不能为空！");
		}
		else if(resetPasswordForm.password !== proxy.password3){
			proxy.$message.error("两次输入的密码不一致！");
		}
		else {
			var that = proxy;
			that.resetPasswordForm.password = sha1(that.resetPasswordForm.password);
			proxy.$axios.post(that.$store.state.url + '/resetPassword',resetPasswordForm)
			.then(res => {
				if(res.data.code === -1){
					that.resetPasswordForm.password = '';
					that.$message.error(res.data.msg);
				}
				else {
					that.$message.success(res.data.msg);
					that.dialogResetPasswordVisible = false;
					cloceResetPassword();
				}
			})
			.catch(err => {
				console.log(err)
			})
		}
	}
	const quitLogin = () => {
		proxy.$store.state.isLogin=false;
		proxy.$store.state.circleUrl='';
		proxy.$store.state.username='';
		proxy.$store.state.account='';
		proxy.$store.state.password='';
		proxy.$store.state.haveMessage=false;
		Cookies.remove('loginStatus');
		proxy.$message({message: '已退出'});
		proxy.socket.close();
		proxy.$router.push('/blog/recommend')
	}
	const haveMessage = () => {
		if (!proxy.$store.state.haveMessage) {
			if (!proxy.$store.state.haveComment) {
				if (!proxy.$store.state.haveAttention) {
					if (!proxy.$store.state.haveLike) {
						return false;
					}
				}
			}
		}
		return true;
	}
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
	const connectSocket = () => {
		if(!window.WebSocket){
			//有的浏览器里可能没有Websocket对象，window就代表浏览器，就做一下兼容性处理
			window.WebSocket=window.MozWebSocket;
		}
		if(window.WebSocket){
			proxy.socket=new WebSocket(proxy.$store.state.wsurl + "/ws");//websocket用的是ws协议
			//接受到消息
			proxy.socket.onmessage = (event) => {
				var message = JSON.parse(event.data);
				let chatList = proxy.$store.state.chatList;
				let flag = -1;
				//如果chatList中存在会话，标识flag置为i
				for (let i = 0;i < chatList.length;i++) {
					if (chatList[i].account === message.from) {
						flag = i;
						break;
					}
				}
				if (flag !== -1) {
					proxy.$store.state.chatList[flag].message.push(message);
					let list = [];
					list.push(proxy.$store.state.chatList[flag]);
					for (let i = 0;i < proxy.$store.state.chatList.length;i++) {
						if (i !== flag) {
							list.push(proxy.$store.state.chatList[i]);
						}
					}
					proxy.$store.state.chatList = list;
				}
				else {
					var chat = {
						account:message.from,
						username:'',
						circleUrl:'',
						message:[],
						haveMessage:true,
						unreadMessage:1
					}
					proxy.$axios.post(proxy.$store.state.url + '/getAccount',{
						account:chat.account
					})
					.then(res => {
						var account = res.data.data;
						chat.username = account.username;
						chat.circleUrl = account.avatar;
						chat.message.push(message);
						let list = [];
						list.push(chat);
						for (let i = 0;i < proxy.$store.state.chatList.length;i++) {
							list.push(proxy.$store.state.chatList[i]);
						}
						proxy.$store.state.chatList = list;
					})
					.catch(err => {
						console.log(err)
					})
				}
				//收到信息时判断当前路由是否在信息界面
				if (proxy.$router.currentRoute.value.fullPath === '/chat') {
					//当来消息时判断该消息是不是就是给当前所在的那个会话的(这个方法在Chat.vue)
					proxy.$store.state.chat.checkSelect(flag);
				}
				else {
					proxy.$store.state.haveMessage = true;
					proxy.$store.state.chatList[flag].unreadMessage += 1;
					proxy.$store.state.chatList[flag].haveMessage = true;
					
				}
			}
			proxy.socket.onopen = (event) => {
				proxy.socket.send(proxy.$store.state.account);
			}
			proxy.socket.onclose = (event) => {}
			// 发生错误时
			proxy.socket.onerror = (event) => {
			    console.log("socket错误：", event);
			}
			//socket不断接受服务端传来的数据，然后通过三个事件进行处理
		}
	}
	const sendMessage = (message) => {
		if (proxy.socket.readyState === 1) {
			proxy.socket.send(JSON.stringify(message));
		}
		else {
			proxy.$message.error("socket错误！");
		}
	}
	const toMessage = (i:String) => {
		if (proxy.$store.state.isLogin) {
			proxy.$router.push("/"+i);
			if (i === "message/comment") {
				proxy.$store.state.haveComment = false;
			}
			else if (i === "message/attention") {
				proxy.$store.state.haveAttention = false;
			}
			else if (i === "message/like") {
				proxy.$store.state.haveLike = false;
			}
		}
		else {
			proxy.$message.error("请先登录！");
		}
	}
	const saveCookie = () => {
		if (proxy.$store.state.isLogin) {
			var loginStatus = {
				account: proxy.$store.state.account,
				password: proxy.$store.state.password,
				haveMessage: proxy.$store.state.haveMessage
			}
			//保存一个7天时效的Cookie
			Cookies.set('loginStatus', loginStatus, { expires: 7 })
			//根据账号保存一个30天时效的有关聊天记录的Cookie
			Cookies.set(proxy.$store.state.account + 'chatList', proxy.$store.state.chatList, { expires: 30 })
		}
	}
	const connectMetaMask = () => {
		var that = this
		// 开启浏览器插件
		ethereum.enable().then(function () {
		 
			// 声明实例
			var provider = new that.$ethers.providers.Web3Provider(web3.currentProvider);
		 
			// 上链
			provider.getNetwork().then(function (result) {
			 
				if (result['chainId'] != 1) {
					alert("请更换区块链")
					return false
				}
				// 上链成功
				// 列出所有的钱包地址
				provider.listAccounts().then(function (result) {
			 
					console.log(result)
					//获取用户钱包地址
					that.accountaddress = result[0];
					//获取签名
					var signer = provider.getSigner()
					//时间戳
					var rightnow = (Date.now() / 1000).toFixed(0)
					var sortanow = rightnow - (rightnow % 600)
					// 生成签名                             //当前域名
					signer.signMessage('Signning in to ' + document.domain + 'at' + sortanow, that.accountaddress,'test password!').then(signature => {
						console.log(that.accountaddress, signature)
						that.signature = signature;
						that.send_acc();
					})
				})
			})
		})
	}
	onMounted(() => {
		var str = Cookies.get('loginStatus')
		if (str != undefined) {
			var loginStatus = JSON.parse(str)
			proxy.loginForm.account = loginStatus.account;
			proxy.loginForm.password = loginStatus.password;
			login(1);
			if (proxy.$store.state.isLogin) {
				proxy.$store.state.haveMessage = loginStatus.haveMessage
				var chatList = Cookies.get(proxy.$store.state.account + 'chatList')
				if (chatList != undefined) {
					proxy.$store.state.chatList = JSON.parse(chatList);
				}
				var haveMessage = Cookies.get('haveMessage')
				if (haveMessage != undefined) {
					proxy.$store.state.haveMessage = haveMessage
				}
			}
		}
		proxy.$router.push("/blog/recommend")
		//添加监听，当网页关闭时保存当前状态为Cookie
		window.addEventListener('beforeunload', e => proxy.saveCookie(e))
	})
</script>

<style>
	.home {
		position : absolute;
		width : 100%;
	}
	.el-container {
		width : 100%;
		height : 100%;
	}
	.toolbar {
		height: 56px;
	}
	.toolbar-logo {
		display:block;
		font-size: 28px;
		margin-top: 12px;
		margin-left: 4px;
		background-image: -webkit-linear-gradient(left,blue,#66ffff 10%,#cc00ff 20%,#CC00CC 30%, #CCCCFF 40%, #00FFFF 50%,#CCCCFF 60%,#CC00CC 70%,#CC00FF 80%,#66FFFF 90%,blue 100%);
		-webkit-text-fill-color: transparent;/* 将字体设置成透明色 */
		-webkit-background-clip: text;/* 裁剪背景图，使文字作为裁剪区域向外裁剪 */
		-webkit-background-size: 200% 100%; 
		-webkit-animation: masked-animation 4s linear infinite;
	}
	.mt-4 {
		margin-top: 10px;
	}
	#app > div > section > header > div.toolbar > div > div > div > div.el-col.el-col-8.is-guttered > div > div > div.el-input-group__append {
		width: 20px;
		padding-left: 10px;
		padding-right: 10px;
	}
	#app > div.el-overlay > div > div.el-dialog__header {
		font-family:隶书;
		font-size: 55px;
	}
	#app > div.el-overlay > div > div.el-dialog__header > span {
		font-size: 55px;
	}
	#app > div.el-overlay > div > div.el-dialog__body {
		padding-top: 10px;
		padding-bottom: 0px;
	}
	.el-notification .el-icon-success {
		color: #67c23a !important;
	}
	#app > div.home > section > header > div.toolbar > div > div > div > div.el-col.el-col-3.is-guttered {
		padding-right: 0px !important;
		padding-left: 15px !important;
	}
	#app > div.home > section > header > div.toolbar > div > div > div > div.el-col.el-col-7.is-guttered > div:nth-child(5) > button > span > div > sup {
		top: 0px !important;
	}
	.el-badge__content.is-fixed.is-dot {
		top: 8px;
	}
</style>