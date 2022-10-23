import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'

const routes: Array<RouteRecordRaw> = [
	{
		path: '/',
		name: 'home',
		component: Home,
		children:[
			{
				path:'/homepage',
				name:'homepage',
				component: () => import(/* webpackChunkName: "about" */ '../components/home/HomePage.vue')
			},
			{
				path:'/chat',
				name:'chat',
				component: () => import(/* webpackChunkName: "about" */ '../components/home/Chat.vue')
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
		path: '/about',
		name: 'about',
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
	}
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
