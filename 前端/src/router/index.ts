import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'

const routes: Array<RouteRecordRaw> = [
	{
		path: '/',
		name: 'home',
		component: Home,
		children:[
			{
				path:'/blog',
				name:'blog',
				component: () => import(/* webpackChunkName: "about" */ '../components/blog/Blog.vue'),
				children:[
					{
						path:'/blog/recommend',
						name:'blogrecommend',
						component: () => import(/* webpackChunkName: "about" */ '../components/blog/BlogRecommend.vue')
					},
					{
						path:'/blog/attention',
						name:'blogattention',
						component: () => import(/* webpackChunkName: "about" */ '../components/blog/BlogAttention.vue')
					},
					{
						path:'/blog/latest',
						name:'bloglatest',
						component: () => import(/* webpackChunkName: "about" */ '../components/blog/BlogLatest.vue')
					},
					{
						path:'/blog/my',
						name:'blogmy',
						component: () => import(/* webpackChunkName: "about" */ '../components/blog/BlogMy.vue')
					}
				]
			},
			{
				path: '/blogdetails',
				name: 'blogdetails',
				component: () => import(/* webpackChunkName: "about" */ '../components/blog/BlogDetails.vue')
			},
			{
				path:'/message',
				name:'message',
				component: () => import(/* webpackChunkName: "about" */ '../components/message/Message.vue'),
				children:[
					{
						path:'/message/comment',
						name:'messagecomment',
						component: () => import(/* webpackChunkName: "about" */ '../components/message/Comment.vue')
					},
					{
						path:'/message/attention',
						name:'messageattention',
						component: () => import(/* webpackChunkName: "about" */ '../components/message/Attention.vue')
					},
					{
						path:'/message/like',
						name:'messagelike',
						component: () => import(/* webpackChunkName: "about" */ '../components/message/Like.vue')
					}
				]
			},
			{
				path:'/chat',
				name:'chat',
				component: () => import(/* webpackChunkName: "about" */ '../components/message/Chat.vue')
			},
			{
				path:'/userhome',
				name:'userhome',
				component: () => import(/* webpackChunkName: "about" */ '../components/user/UserHome.vue'),
				children:[
					{
						path:'/userhome/userinfo',
						name:'userinfo',
						component: () => import(/* webpackChunkName: "about" */ '../components/user/UserInfo.vue')
					}
				]
			},
		]
	},
	{
		path: '/blogedit',
		name: 'blogedit',
		component: () => import(/* webpackChunkName: "about" */ '../components/blog/BlogEdit.vue')
	},
	{
		path: '/about',
		name: 'about',
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
	}
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
