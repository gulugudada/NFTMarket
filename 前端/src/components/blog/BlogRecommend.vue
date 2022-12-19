<template>
	<div class="blog-recommend-contain">
		<el-container>
		    <el-aside :key="key" style="height: 1000px;" width="1040px">
				<ul
				v-infinite-scroll="load"
				:infinite-scroll-disabled="!canLoad"
				infinite-scroll-delay="400"
				class="blog-recommend-contain-ul">
				    <li v-for="(item,index) in list.blogList">
							<div style="height: 120px;">
								<div>
									<a href="https://element.eleme.io" style="color: #000000;text-decoration : none;">
										<span>
											{{item.name}}
										</span>
									</a>
								</div>
								<div style="display: flex;">
									<a v-if="item.cover !== null" href="https://element.eleme.io" style="text-decoration : none;">
										<el-image style="width: 120px; height: 80px;margin-top: 10px;margin-right: 10px;" :src="$store.state.url+item.cover" fit="fit" />
									</a>
									<div style="flex: 1;margin-top: 10px;height: 80px;">
										<a href="https://element.eleme.io" style="text-decoration : none;color: #000000;">
											<div style="height: 60px;width: 900px;">
												<p class="blog-content">
													{{item.content}}
												</p>
											</div>
										</a>
										<div style="display: flex;margin-top: 3px;font-size: 14px;">
											<div style="width: 70px;display: flex;">
												<div style="height: 19.2px;width: 16px;margin-right: 4px;">
													<img style="width: 16px;height: 16px;margin-top: 2px;" src="../../assets/images/20220117023149.png">
												</div>
												<span>
													{{item.thumbsUpCount}}赞
												</span>
											</div>
											<div style="width: 70px;display: flex;">
												<div style="height: 19.2px;width: 16px;margin-right: 4px;">
													<img style="width: 16px;height: 16px;margin-top: 2px;" src="../../assets/images/20220117023256.png">
												</div>
												<span>
													{{item.thumbsDownCount}}踩
												</span>
											</div>
											<div style="width: 70px;display: flex;">
												<el-icon :size="20" style="margin-right: 4px;"><View /></el-icon>
												<span>
													{{item.visits}}
												</span>
											</div>
											<a>
												<span>
													{{item.userName}}
												</span>
											</a>
										</div>
									</div>
								</div>
							</div>
						<el-divider style="margin-top: 4px;margin-bottom: 4px;"/>
					</li>
				</ul>
				<div v-if="!canLoad" class="canLoad">
					已经被掏空了
				</div>
			</el-aside>
		    <el-main style="padding: 0;">
				<div class="author-recomment">
					<div class="author-recomment-title">
						<span>
							作者推荐
						</span>
					</div>
					<div class="author-recomment-content">
						<div style="margin-top: 15px;" :key="key">
							<div v-for="(item,index) in list.authorList" class="author-recomment-content-item">
								<a title="用户首页" @click="this.$router.push('/userhome')" style="">
									<el-avatar :size="40" :src="$store.state.url+item.avatar" style=""></el-avatar>
								</a>
								<div>
									<div style="padding-top: 1px;padding-left: 6px;">
										<span style="font-size: 14px;">
											{{item.userName}}
										</span>
									</div>
									<div style="padding-left: 6px;">
										<span style="font-size: 12px;color: #999aaas;">
											{{item.profile}}
										</span>
									</div>
								</div>
								<el-button style="height: 10px;margin-top: 5px;margin-left: 30px;" size="small" round @click="load()">关注</el-button>
							</div>
						</div>
					</div>
				</div>
			</el-main>
		</el-container>
	</div>
</template>

<script lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance } from 'vue'
	import { ElNotification } from 'element-plus'
	import $ from 'jquery'
	const { proxy } = getCurrentInstance()
	const pageNum = ref(0)
	const key = ref(0)
	const canLoad = ref(true)
	const list = reactive({
		authorList:[],
		blogList:[]
	})
	const getBlogRecommend = () => {
		var that = proxy;
		proxy.$axios.post(that.$store.state.url + '/getBlogRecommend?pageNum=' + that.pageNum)
		.then(res => {
			if (res.data.code === -1) {
				canLoad.value = false;
			}
			else {
				that.list.blogList.push(...res.data.data);
			}
		})
		.catch(err => {
			console.log(err)
		})
	}
	const load = () => {
		pageNum.value += 1;
		getBlogRecommend();
	}
	const haveCover = (cover:String) => {
		console.log(cover === null);
		// console.log(cover !== "");
	}
	const getAuthorRecommend = () => {
		var that = proxy;
		proxy.$axios.post(that.$store.state.url + '/getAuthorRecommend')
		.then(res => {
			that.list.authorList.push(...res.data.data);
		})
		.catch(err => {
			console.log(err)
		})
	}
	const renovate = () => {
		//用来刷新页面
		key.value += 1;
	}
	onMounted(() => {
		$.ajax({
			method: "post",
			async: false,
			url: proxy.$store.state.url + '/getAuthorRecommend',
			success: function(res){
				proxy.list.authorList.push(...res.data);
			}
		})
		// $.ajax({
		// 	method: "post",
		// 	async: false,
		// 	url: proxy.$store.state.url + '/getBlogRecommend',
		// 	data: {
		// 		pageNum: 1
		// 	},
		// 	success: function(res){
		// 		proxy.list.blogList.push(...res.data);
		// 	}
		// })
	})
</script>

<style scoped>
	.blog-recommend-contain {
		
	}
	.author-recomment {
		margin-left: 10px;
		margin-right: 10px;
		border-radius: 2px;
		border-top: 2px solid #e8e8ed;
		box-shadow: 0px 16px 48px 16px rgba(0, 0, 0, .08), 0px 12px 32px rgba(0, 0, 0, .12), 0px 8px 16px -8px rgba(0, 0, 0, .16);
	}
	.author-recomment-title {
		padding-left: 18px;
		line-height: 48px;
		font-size: 18px;
		border-bottom: 2px solid #e8e8ed;
	}
	.author-recomment-content {
		height: 760px;
	}
	.author-recomment-content-item {
		display: flex;
		height: 50px;
		margin-left: 20px;
	}
	::-webkit-scrollbar {
	        width: 0px;
	}
	.canLoad{
		width: 100%;
		height: 50px;
		line-height: 50px;
		margin-top: -5px;
		text-align: center;
		color: #666;
		background-color: rgba(0,0,0,.05);
	}
	.blog-content {
		font-size: 14px;
		font-weight: 400;
		color: #555666;
		overflow : hidden;
		/*2. 文字用省略号替代超出的部分 */
		text-overflow: ellipsis;
		/* 3. 弹性伸缩盒子模型显示 */
		display: -webkit-box;
		/* 4. 限制在一个块元素显示的文本的行数 */
		-webkit-line-clamp: 3;
		/* 5. 设置或检索伸缩盒对象的子元素的排列方式 */
		-webkit-box-orient: vertical;
	}
</style>
