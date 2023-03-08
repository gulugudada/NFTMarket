<template>
	<div v-loading.fullscreen.lock="fullscreenLoading" class="blog-details">
		<div class="blog-detailse-contain">
			<div class="blog-details-left">
				<div class="blog-details-author">
					<div style="width: 268px;height: 48px;padding: 16px;padding-bottom: 6px;display: flex;">
						<a href="/" style="">
							<el-avatar :size="48" :src="$store.state.url + author.avatar" />
						</a>
						<div style="width: 212px;height: 100%;margin-left: 14px;padding-top: 2px;">
							<a href="/" style="text-decoration: none;font-size: 16px;">
								{{author.username}}
							</a>
							<span style="display:block;padding-top: 4px;font-size: 14px;color: #999aaa;">
								于{{author.createTime}}加入
							</span>
						</div>
					</div>
					<div class="blog-details-author-count">
						<div style="width: 70px;height: 100%;">
							<a class="blog-details-author-a" href="/" style=";text-decoration: none;color: #000000;">
								<span>
									{{author.blogCount}}
								</span>
								<span style="display: block;padding-top: 8px;" class="font">
									博客
								</span>
							</a>
						</div>
						<div style="width: 70px;height: 100%;">
							<a class="blog-details-author-a" href="/" style=";text-decoration: none;color: #000000;">
								<span>
									{{author.blogCount}}
								</span>
								<span style="display: block;padding-top: 8px;" class="font">
									NFT
								</span>
							</a>
						</div>
						<div style="width: 70px;height: 100%;">
							<a class="blog-details-author-a" href="/" style="text-decoration: none;color: #000000;">
								<span>
									{{author.blogCount}}
								</span>
								<span style="display: block;padding-top: 8px;" class="font">
									动态
								</span>
							</a>
						</div>
						<div style="width: 70px;height: 100%;">
							<span>
								{{author.blogCount}}
							</span>
							<span style="display: block;padding-top: 8px;color: #999aaa;">
								访问量
							</span>
						</div>
					</div>
					<el-divider style="margin: 0px;"/>
					<div class="blog-details-author-count">
						<div style="width: 70px;height: 100%;">
							<span>
								{{author.blogCount}}
							</span>
							<span style="display: block;padding-top: 8px;color: #999aaa;">
								粉丝
							</span>
						</div>
						<div style="width: 70px;height: 100%;">
							<span>
								{{author.thumbsUpCount}}
							</span>
							<span style="display: block;padding-top: 8px;color: #999aaa;">
								获赞
							</span>
						</div>
						<div style="width: 70px;height: 100%;">
							<span>
								{{author.blogCount}}
							</span>
							<span style="display: block;padding-top: 8px;color: #999aaa;">
								评论
							</span>
						</div>
						<div style="width: 70px;height: 100%;">
							<span>
								{{author.collectCount}}
							</span>
							<span style="display: block;padding-top: 8px;color: #999aaa;">
								收藏
							</span>
						</div>
					</div>
				</div>
				<div class="blog-details-author-blog">
					<div style="padding: 9px 16px;">
						<h3>博客信息</h3>
						<b style="display: block;width: 20px;height: 1px;background: #555666;;margin-top: 6px;"></b>
						<div style="padding: 8px 0 10px 0;display: flex;">
							<div>
								博客名：
							</div>
							<div style="width: 204px;">
								{{blog.name}}
							</div>
						</div>
						<div style="padding: 8px 0 10px 0;display: flex;">
							<div style="width: 50px;padding-left: 16px;">
								作者：
							</div>
							<div style="width: 204px;">
								{{blog.name}}
							</div>
						</div>
						<div v-if="blog.modified == null" style="padding: 8px 0 10px 0;display: flex;padding-left: 28px;color: #999aaa;">
							于 {{blog.publishTime}} 发布
						</div>
						<div v-else style="padding: 8px 0 10px 0;display: flex;text-align: center;padding-left: 28px;color: #999aaa;">
							于 {{blog.modified}} 修改
						</div>
						<div style="padding: 8px 0 10px 0;display: flex;text-align: center;padding-left: 28px;color: #999aaa;">
							<el-icon :size="14" style="top: 4px;padding-left: 0px;"><View /></el-icon>
							{{blog.visits}}
							<a v-if="blog.account == $store.state.account" href="#" @click="toBlogEdit(blog.id)" style="text-decoration: none;padding-left: 120px;">编辑</a>
						</div>
					</div>
				</div>
				<div class="blog-details-author-blog">
					<div style="padding: 9px 16px;">
						<h3>热门博客</h3>
						<b style="display: block;width: 20px;height: 1px;background: #555666;;margin-top: 6px;"></b>
						<div style="padding: 8px 0 16px 0;">
							<ul class="infinite-list" style="font-size: 14px;">
							    <li v-for="(item,index) in hotBlog" class="infinite-list-item">
									<a href="/" style="text-decoration: none;color: #555666;">
										{{item.name}}
										<div style="height: 20px;padding-left: 10px;display: inline-block">
											<el-icon :size="14" style="top: 2px;"><View /></el-icon>
											<span>
												{{item.visits}}
											</span>
										</div>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="blog-details-author-blog">
					<div style="padding: 9px 16px;">
						<h3>最新博客</h3>
						<b style="display: block;width: 20px;height: 1px;background: #555666;;margin-top: 6px;"></b>
						<div style="padding: 8px 0 16px 0;">
							<ul class="infinite-list" style="font-size: 14px;">
							    <li v-for="(item,index) in latestBlog" class="infinite-list-item">
									<a href="/" style="text-decoration: none;color: #555666;">
										{{item.name}}
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="blog-details-right">
				<el-empty description="" style="position: absolute;margin: auto;top: 50%;left: 50%;transform:translate(-50%,-50%);"/>
				<v-md-preview :text="blog.content"></v-md-preview>
			</div>
		</div>
	</div>
</template>

<script lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance } from 'vue'
	import $ from 'jquery'
	const { proxy } = getCurrentInstance()
	const fullscreenLoading = ref(false)
	const author = reactive({
		avatar : '',
	})
	const hotBlog = reactive([])
	const latestBlog = reactive([])
	const blog = reactive({
		id : 19,
		name : '',
		account : '',
		content : '',
		abstractContent : ''
	})
	const getAuthor = () => {
		var that = proxy;
		proxy.fullscreenLoading = true;
		proxy.$axios.post(that.$store.state.url + '/getAuthor', {
			account : that.blog.account,
		})
		.then(res => {
			if(res.data.code === -1){
				that.$message.error(res.data.msg);
			}
			else {
				that.blog = res.data;
			}
			that.fullscreenLoading = false;
		})
		.catch(err => {
			console.log(err)
		})
	}
	const getBlog = () => {
		var that = proxy;
		proxy.fullscreenLoading = true;
		proxy.$axios.post(that.$store.state.url + '/getBlogPreview', {
			id : that.blog.id,
		})
		.then(res => {
			if(res.data.code === -1){
				that.$message.error(res.data.msg);
			}
			else {
				that.blog = res.data.data;
			}
			that.fullscreenLoading = false;
		})
		.catch(err => {
			console.log(err)
		})
	}
	const getAuthorHotBlog = () => {
		var that = proxy;
		proxy.fullscreenLoading = true;
		proxy.$axios.post(that.$store.state.url + '/getAuthorHotBlog', {
			account : that.blog.account,
		})
		.then(res => {
			if(res.data.code === -1){
				that.$message.error(res.data.msg);
			}
			else {
				that.hotBlog = res.data.data;
			}
			that.fullscreenLoading = false;
		})
		.catch(err => {
			console.log(err)
		})
	}
	const getAuthorLatestBlog = () => {
		var that = proxy;
		proxy.fullscreenLoading = true;
		proxy.$axios.post(that.$store.state.url + '/getAuthorLatestBlog', {
			account : that.blog.account,
		})
		.then(res => {
			if(res.data.code === -1){
				that.$message.error(res.data.msg);
			}
			else {
				that.latestBlog = res.data.data;
			}
			that.fullscreenLoading = false;
		})
		.catch(err => {
			console.log(err)
		})
	}
	const toBlogEdit = (id:int) => {
		proxy.$router.push({path:'/blogedit',query: {id: id}});
	}
	onMounted(() => {
		proxy.blog.id = proxy.$route.query.id;
		$.ajax({
			method: "post",
			async: false,
			url: proxy.$store.state.url + '/getBlogPreview',
			contentType:'application/json;charset=utf-8',
			data: '{"id":"'+proxy.blog.id+'"}',
			success: function(res){
				console.log(res.data);
				proxy.blog = res.data;
			}
		})
		$.ajax({
			method: "post",
			async: false,
			url: proxy.$store.state.url + '/getAuthor',
			contentType:'application/json;charset=utf-8',
			data: '{"account":"'+proxy.blog.account+'"}',
			success: function(res){
				proxy.author.avatar = res.avatar;
				proxy.author = res;
			}
		})
		$.ajax({
			method: "post",
			async: false,
			url: proxy.$store.state.url + '/getAuthorHotBlog',
			contentType:'application/json;charset=utf-8',
			data: '{"account":"'+proxy.blog.account+'"}',
			success: function(res){
				proxy.hotBlog = res.data;
			}
		})
		$.ajax({
			method: "post",
			async: false,
			url: proxy.$store.state.url + '/getAuthorLatestBlog',
			contentType:'application/json;charset=utf-8',
			data: '{"account":"'+proxy.blog.account+'"}',
			success: function(res){
				proxy.latestBlog = res.data;
			}
		})
	})
</script>

<style scoped>
	.blog-details {
		width: 100%;
		height: auto;
	}
	.blog-detailse-contain {
		width: 90%;
		height: auto;
		margin-left: 120px;
		display: flex;
	}
	.blog-details-left {
		width: 300px;
		height: auto;
	}
	.blog-details-right {
		width: 960px;
		height: auto;
		margin-left: 10px;
		padding-left: 24px;
		padding-right: 24px;
		padding-bottom: 16px;
		box-shadow: rgba(14, 30, 37, 0.12) 0px 2px 4px 0px, rgba(14, 30, 37, 0.32) 0px 2px 16px 0px;
	}
	.blog-details-author {
		width: 100%;
		height: auto;
		padding-bottom: 8px;
		margin-bottom: 10px;
		box-shadow: rgba(14, 30, 37, 0.12) 0px 2px 4px 0px, rgba(14, 30, 37, 0.32) 0px 2px 16px 0px;
	}
	.blog-details-author-blog {
		width: 100%;
		height: auto;
		margin-bottom: 10px;
		box-shadow: rgba(14, 30, 37, 0.12) 0px 2px 4px 0px, rgba(14, 30, 37, 0.32) 0px 2px 16px 0px;
	}
	.blog-details-author-count {
		height: 50px;
		width: 282px;
		padding: 9px;
		padding-top: 10px;
		padding-bottom: 10px;
		display: flex;
		text-align: center;
		font-size: 14px;
		color: #000000;
	}
	.blog-details-author-a:hover .font {
		color: #fc5531;
	}
	.infinite-list-item {
		margin-top: 8px;
		list-style: none;
	}
</style>