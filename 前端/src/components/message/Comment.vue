<template>
	<div class="comment-contain">
		<div class="comment-contain-title">
			<span>评论和@</span>
			<span style="float: right;font-size: 14px;font-weight: 400;color: #277ecc;">
				<el-button type="text"  @click="dialogVisible = true">
					清空所有消息
				</el-button>
			</span>
		</div>
		<div class="comment-contain-contain">
			<ul v-if="list.commentList.length != 0"  class="comment-contain-contain-ul">
				<li v-for="(item,index) in list.commentList" class="comment-contain-contain-li">
					<div style="width: 42px;empty-cells: 100%;">
						<el-avatar :size="42" :src="$store.state.url+item.avatar" style=""></el-avatar>
					</div>
					<div style="width: 100%;padding-left: 16px;">
						<span style="display: flex;width: 100%;">
							<a style="margin-right: 8px;" class="comment-contain-a">
								{{item.username}}
							</a>
							<p v-if="item.type == 'comment'" class="comment-contain-p">
								回复了你的评论
							</p>
							<p v-if="item.type == 'blog'" class="comment-contain-p">
								评论了你的博文
							</p>
							<span style="position: absolute;right: 26px;width: 14px;height: 14px;">
								<el-button type="text" style="width: 14px;height: 14px;" @click="selectDeleteComment = index,dialogVisible1 = true">
									<el-icon color="#F56C6C"><Delete /></el-icon>
								</el-button>
							</span>
						</span>
						<div style="width: 100%;height: 60px;margin-top: 8px;display: flex;">
							<div style="width: 715px;height: 100%;">
								<p style="width: 100%;font-weight: 600;font-size: 14px;">
									<a>
										{{item.content}}
									</a>
								</p>
								<p style="width: 100%;margin-top: 16px;font-size: 14px;">
									<a>
										{{item.object.name}}
									</a>
								</p>
							</div>
							<p style="height: auto;width: auto;margin-left: 40px;color: #9999AA;font-size: 14px;">
								{{getDateFromDateTime(item.commentTime)}}
							</p>
						</div>
					</div>
				</li>
			</ul>
			<el-empty v-if="list.commentList.length == 0" description="没有评论!" style="padding-top:230px;padding-left: 50px;" />
		</div>
		<div class="comment-contain-bottom">
			<el-pagination
			    v-model:current-page="currentPage"
				:hide-on-single-page="false"
			    layout="total, prev, pager, next, jumper"
				prev-text="上一页"
				next-text="下一页"
				v-model:total="total"
			    v-model:page-count="total"
				@update:current-page="currentPage"
			    @current-change="handleCurrentChange"
				
			/>
		</div>
	</div>
	<el-dialog v-model="dialogVisible" title="确定清空所有的评论消息吗？">
		<template #footer>
			<div>
				<el-button @click="dialogVisible = false">取消</el-button>
				<el-button type="danger" @click="clearComment()">确定</el-button>
			</div>
		</template>
	</el-dialog>
	<el-dialog v-model="dialogVisible1" title="确定删除该条评论消息吗？">
		<template #footer>
			<div>
				<el-button @click="dialogVisible1 = false,selectDeleteComment = 0">取消</el-button>
				<el-button type="danger" @click="deleteComment()">确定</el-button>
			</div>
		</template>
	</el-dialog>
</template>

<script lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance } from 'vue'
	import { ElNotification } from 'element-plus'
	import $ from 'jquery'
	const { proxy } = getCurrentInstance()
	const currentPage = ref(1)
	const dialogVisible = ref(false)
	const dialogVisible1 = ref(false)
	const selectDeleteComment = ref(0)
	const total = ref(0)
	const list = reactive({
		commentList:[]
	})
	const getDateFromDateTime = (datetime:String) => {
		return datetime.split(" ")[0];
	}
	const clearComment = () => {
		var that = proxy;
		proxy.$axios.post(that.$store.state.url + '/clearCommentMessage',{
			account: that.$store.state.account,
		})
		.then(res => {
			that.currentPage = 1;
			that.getCommentMessage();
			that.dialogVisible = false;
		})
		.catch(err => {
			console.log(err)
		})
	}
	const deleteComment = () => {
		var that = proxy;
		proxy.$axios.post(that.$store.state.url + '/deleteCommentMessage',{
			id: that.list.commentList[that.selectDeleteComment].id,
		})
		.then(res => {
			that.getCommentMessage();
			that.dialogVisible1 = false;
		})
		.catch(err => {
			console.log(err)
		})
	}
	const handleCurrentChange = (value: number) => {
		getCommentMessage();
	}
	const getCommentMessage = () => {
		var that = proxy;
		proxy.$axios.post(that.$store.state.url + '/getCommentMessage',{
			account: that.proxy.$store.state.account,
			pageNum: that.currentPage,
		})
		.then(res => {
			that.total = res.data.total;
			that.list.commentList = res.data.data;
		})
		.catch(err => {
			console.log(err)
		})
	}
	onMounted(() => {
		$.ajax({
			method: "post",
			async: false,
			url: proxy.$store.state.url + '/getCommentMessage',
			contentType:'application/json;charset=utf-8',
			data: '{"account":"'+proxy.$store.state.account+'","pageNum":"1"}',
			success: function(res){
				proxy.total = res.total;
				proxy.list.commentList.push(...res.data);
			}
		})
	})
</script>

<style scoped>
	.comment-contain {
		width: 100%;
		height: 100%;
	}
	.comment-contain-title {
		width: 95%;
		height: 56px;
		margin-left: 20px;
		line-height: 60px;
		font-weight: 600;
		border-bottom: 1px solid #f0f0f2;
	}
	.comment-contain-contain {
		width: 95%;
		height: 647px;
		margin-left: 22px;
	}
	.comment-contain-contain-ul {
		width: 100%;
		height: auto;
	}
	.comment-contain-contain-li {
		width: 100%;
		height: 80px;
		list-style: none;
		padding-top: 24px;
		padding-bottom: 24px;
		display: flex;
		border-bottom: 1px solid #f0f0f2;
	}
	.comment-contain-bottom {
		height: 20px;
		margin-top: 4px;
		padding-top: 20px;
		text-align: center;
	}
	.comment-contain-a {
		font-size: 14px;
		font-family: PingFang SC,Hiragino Sans GB,Arial,Microsoft YaHei,Verdana,Roboto,Noto,Helvetica Neue,sans-serif!important;
	}
	.comment-contain-p {
		font-size: 14px;
		color: rgb(153,153,170);
		font-family: PingFang SC,Hiragino Sans GB,Arial,Microsoft YaHei,Verdana,Roboto,Noto,Helvetica Neue,sans-serif!important;
	}
</style>
