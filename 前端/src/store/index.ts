import { createStore } from 'vuex'

export default createStore({
  state: {
	//后端url,方便映射
	url:'http://localhost:8080',
	wsurl:'ws://localhost:8088',
	isLogin:false,
	circleUrl:'',
	username:'',
	account:'',
	password:'',
	haveMessage:false,
	chatList:[
		{
			account:'123456',
			username:'小小怪',
			circleUrl:'/avatar/390dc2e41e284bb7b70eb4c16d568e95.jpg',
			message:[],
			haveMessage:true,
			unreadMessage:10
		},
		{
			account:'123',
			username:'小小怪',
			circleUrl:'/avatar/390dc2e41e284bb7b70eb4c16d568e95.jpg',
			message:[],
			haveMessage:true,
			unreadMessage:10
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
