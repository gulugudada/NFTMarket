<template>
	<div class="blog-create-title">
		<div class="blog-create-title-name">
			<el-button v-loading.fullscreen.lock="fullscreenLoading" type="text" style="font-size: 18px;color: rgb(107,107,107);padding-left: 60px;margin-bottom: 8px;"  @click="toHome()">
				&lt; 返回首页
			</el-button>
			<el-input
				v-model="blog.name"
				maxlength="100"
				show-word-limit
				minlength="5"
				round
				placeholder="请输入文章标题(5~100个字)"
				style="padding-left: 40px;font-size: 18px;"
			>
			</el-input>
			<el-button type="danger" round @click="dialogPublishVisible = true;" style="height: 40px;font-size: 18px;margin-left: 40px;">发布博客</el-button>
		</div>
	</div>
	<v-md-editor
		v-model="blog.content"
		:disabled-menus="[]"
		@upload-image="handleUploadImage"
		@save="save"
		left-toolbar="undo redo clear | bold italic h strikethrough hr | ul ol todo-list quote code emoji tip | table image link | save" 
		height="660px"
		:toc-nav-position-right="true"
	>
	</v-md-editor>
	<el-dialog
		v-model="dialogPublishVisible"
	>
	    <h2 style="position: absolute;top: 20px;">发布博客</h2>
		<div style="width: 100%;display: flex;margin-top: 20px;">
			<div style="width: 95px;height: auto;font-size: 16px;">
				封面&摘要：
			</div>
			<div style="width: 75%;">
				<el-radio-group v-model="radio" size="small">
				    <el-radio label="1">有封面</el-radio>
				    <el-radio label="2">无封面</el-radio>
				</el-radio-group>
				<el-upload
					v-if="radio == 1"
					ref="uploadRef"
					:action="$store.state.url + '/publishBlog'"
					list-type="picture-card"
					:limit="1"
					:auto-upload="false"
					:on-preview="handlePictureCardPreview"
					:on-remove="handleRemove"
					:before-upload="beforePublishUpload"
					:on-success="handlePublishSuccess"
					:data="blog"
					style="padding-top: 25px"
				>
					<el-icon><Plus /></el-icon>
				</el-upload>
				<el-input
					v-model="blog.abstractContent"
					maxlength="256"
					show-word-limit
					minlength="1"
					:autosize="{ minRows: 3, maxRows: 7 }"
					type="textarea"
					placeholder="博客摘要(必填),用于推荐和列表外显使用！"
					style="padding-top: 25px;"
				/>
			</div>
		</div>
		<template #footer>
			<div style="margin-top: 20px;">
				<el-button @click="dialogPublishVisible = false">取消</el-button>
				<el-button type="success" @click="publishBlog()">发布博客</el-button>
			</div>
		</template>
	</el-dialog>
	<el-dialog v-model="dialogPreviewVisible">
	    <img class="img" :src="dialogImageUrl" alt="Preview Image" />
	</el-dialog>
</template>

<script lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance } from 'vue'
	import $ from 'jquery'
	const { proxy } = getCurrentInstance()
	import type { UploadProps, UploadUserFile } from 'element-plus'
	const uploadRef = ref<UploadInstance>()
	const dialogPublishVisible = ref(false)
	const fullscreenLoading = ref(false)
	const dialogPreviewVisible = ref(false)
	const radio = ref('2')
	const dialogImageUrl = ref("")
	const blog = reactive({
		id : 19,
		name : '',
		account : '',
		content : '',
		abstractContent : ''
	})
	const handleUploadImage = (event, insertImage, files) => {
		var formObj = new FormData();
		formObj.append('file', files[0]);
		$.ajax({
			url: proxy.$store.state.url + '/uploadBlogImage',
			async: false,
			type:"post",
			data:formObj,
			dataType:'json',
			contentType: false, //不设置内容类型
			processData: false,
			success: function(res){
				proxy.$message.success(res.msg);
				insertImage({
					url: proxy.$store.state.url + res.imageurl,
					desc: '',
				    width: 'auto',
				    height: 'auto',
				});
			},
		})
	}
	const save = (text, html) => {
		if (proxy.blog.name.length < 5 || proxy.blog.name.length > 100) {
			proxy.$message.error("博客题目不符合要求");
		}
		else {
			var that = proxy;
			proxy.fullscreenLoading = true;
			proxy.blog.account = proxy.$store.state.account;
			proxy.$axios.post(that.$store.state.url + '/saveBlog',proxy.blog)
			.then(res => {
				if(res.data.code === -1){
					that.$message.error(res.data.msg);
				}
				else {
					that.$message.success(res.data.msg);
					if (res.data.id != null) {
						that.id = res.data.id;
					}
				}
				that.fullscreenLoading = false;
			})
			.catch(err => {
				console.log(err)
			})
		}
	}
	const publish = () => {
		if (proxy.blog.name.length < 5 || proxy.blog.name.length > 100) {
			proxy.$message.error("博客题目不符合要求");
		}
		else {
			var that = proxy;
			proxy.fullscreenLoading = true;
			let formData = new FormData();
			formData.append("id", proxy.blog.id);
			formData.append("name", proxy.blog.name);
			formData.append("account", proxy.$store.state.account);
			formData.append("content", proxy.blog.content);
			formData.append("abstractContent", proxy.blog.abstractContent);
			proxy.$axios.post(that.$store.state.url + '/publishBlog', formData)
			.then(res => {
				if(res.data.code === -1){
					that.$message.error(res.data.msg);
				}
				else {
					that.$message.success(res.data.msg);
					if (res.data.id != null) {
						that.blog.id = res.data.id;
					}
				}
				that.fullscreenLoading = false;
				that.dialogPublishVisible = false;
			})
			.catch(err => {
				console.log(err)
			})
		}
	}
	const handleRemove: UploadProps['onRemove'] = (uploadFile, uploadFiles) => {
		
	}
	const handlePictureCardPreview: UploadProps['onPreview'] = (uploadFile) => {
		dialogImageUrl.value = uploadFile.url!;
		dialogPreviewVisible.value = true;
	}
	const publishBlog = () => {
		if (proxy.radio === '1') {
			proxy.fullscreenLoading = true;
			uploadRef.value!.submit();
		}
		else {
			publish();
		}
	}
	const beforePublishUpload: UploadProps['beforeUpload'] = (rawFile) => {
		if (proxy.blog.name === "") {
			proxy.$message.error("博客名称不可以为空！");
			return false
		}
		else if (proxy.blog.abstractContent === "") {
			proxy.$message.error("摘要不可以为空！");
			return false
		}
		else {
			return true
		}
	}
	const handlePublishSuccess: UploadProps['onSuccess'] = (
		response
	) => {
		if (response.code === -1) {
			proxy.$message.error(response.msg + "！");
		}
		else {
			proxy.fullscreenLoading = false;
			proxy.blog.id = response.id;
			proxy.$message.success(response.msg + "！");
		}
		proxy.dialogPublishVisible = false;
	}
	const getBlog = () => {
		var that = proxy;
		proxy.fullscreenLoading = true;
		proxy.$axios.post(that.$store.state.url + '/getBlogEdit', {
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
	const toHome = () => {
		proxy.$router.push("/");
	}
	onMounted(() => {
		proxy.blog.id = proxy.$route.query.id;
		getBlog();
	})
</script>

<style scoped>
	.blog-create-title {
		width: 100%;
		height: 59px;
		background-color: rgb(245,245,245);
	}
	.blog-create-title-name {
		width: 96%;
		height: 50px;
		padding-top: 8px;
		display: flex;
	}
	.el-upload-list__item-thumbnail {
		width: 100%;
		height: 100%;
		object-fit: contain;
	}
	.img {
		width: 100%;
		height: 100%;
		object-fit: contain;
	}
</style>

<style>
	#app > div.el-overlay > div > div.el-dialog__body > div > div:nth-child(2) > div.el-radio-group > label:nth-child(1) > span.el-radio__label, #app > div.el-overlay > div > div.el-dialog__body > div > div:nth-child(2) > div.el-radio-group > label:nth-child(2) > span.el-radio__label,#app > div.el-overlay > div > div.el-dialog__body > div > div:nth-child(2) > div.el-textarea > textarea {
		font-size: 16px !important;
	}
	#app > div.el-overlay > div > div.el-dialog__body > div > div:nth-child(2) > div:nth-child(2) > ul > li {
		width: auto;
		height: auto;
		object-fit: contain;
	}
</style>