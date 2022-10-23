import { createStore } from 'vuex'

export default createStore({
  state: {
	//后端url
	url:'http://localhost:8080',
	isLogin:false,
	circleUrl:'',
	username:'',
	account:'',
	haveMessage:false,
	chatList:[
		{
			account:'123456',
			username:'小小怪',
			circleUrl:'',
			message:[],
			haveMessage:true,
			unreadMessage:10
		},
		{
			account:'19974712583',
			username:'',
			circleUrl:'',
			message:[],
			haveMessage:false,
			unreadMessage:0
		}
	],
	//chat页面的dom对象
	chat:''
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
