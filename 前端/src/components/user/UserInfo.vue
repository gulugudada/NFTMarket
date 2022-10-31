<template>
	<div class="avatar-uploader">
		<el-avatar :size="120" :src="$store.state.url + $store.state.circleUrl" />
		<el-avatar v-if="dialogAvatarVisible" class="el-upload-list__item-actions">
			<div style="width: 100%;height: 100%;position: relative;" class="el-upload-list__item-actions-div">
				<span
					style="width: auto;height: auto;"
					@click="handlePictureCardPreview()"
				>
					<el-icon style="width: auto;height: auto;"><zoom-in /></el-icon>
				</span>
				<span
					style="width: auto;height: auto;padding-left: 10px;"
					@click="handlePictureCardUpload()"
				>
					<el-icon style="width: auto;height: auto;"><SetUp /></el-icon>
				</span>
			</div>
		</el-avatar>
	</div>
	<el-dialog v-model="dialogPreviewVisible">
	    <img style="width: 500px;height: 500px;" :src="$store.state.url + $store.state.circleUrl" alt="Preview Image" />
	</el-dialog>
	<el-dialog v-model="dialogUploadVisible">
	    <el-upload
			:action="$store.state.url + '/uploadAvatar'"
	        drag
			class="upload-diaload-upload"
			:show-file-list="false"
			:on-success="handleAvatarSuccess"
			:before-upload="beforeAvatarUpload"
			:data="uploadData"
			accept="image/png,image/jpg,image/jpeg"
	    >
	        <el-icon style="width: auto;height: auto;padding-top: 50px;"><upload-filled /></el-icon>
	        <div>
				点击或拖动图片至此处
	        </div>
	        <template #tip>
				<div style="padding-top: 15px;">
					仅支持JPG/PNG格式大小不超过2MB的图片(纵横比要为1:1)
				</div>
	        </template>
	    </el-upload>
	</el-dialog>
</template>

<script lang="ts" setup>
	import { ref,onMounted,reactive,getCurrentInstance } from 'vue'
	const { proxy } = getCurrentInstance()
	import $ from 'jquery'
	import type { UploadProps } from 'element-plus'
	const dialogPreviewVisible = ref(false)
	const dialogAvatarVisible = ref(false)
	const dialogUploadVisible = ref(false)
	const dialogBeforeUploadVisible = ref(false)
	const uploadData = reactive({
		account:''
	})
	const handlePictureCardPreview = () => {
		proxy.dialogPreviewVisible=true;
	}
	const handlePictureCardUpload = () => {
		proxy.dialogUploadVisible=true;
	}
	const handleAvatarSuccess: UploadProps['onSuccess'] = (
		response
	) => {
		if (response.code === -1) {
			proxy.$message.error(response.msg + "！");
		}
		else {
			proxy.$store.state.circleUrl = response.avatarurl;
			proxy.$message.success(response.msg + "！");
		}
		proxy.dialogUploadVisible = false;
	}
	const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
		var flag = 0;
		if (rawFile.type !== 'image/jpeg') {
			if (rawFile.type !== 'image/jpg') {
				if (rawFile.type !== 'image/png') {
					flag = 1;
				}
			}
		}
		if (rawFile.size / 1024 / 1024 <= 2) {
			var reader = new FileReader();
			reader.onload = function (event) {
				var txt = event.target.result;
				var img = document.createElement("img");
				img.src = txt;
				img.onload = function () {
					if (img.height !== img.width) {
						flag = 2;
					}
				}
			};
			reader.readAsDataURL(rawFile);
		}
		else {
			flag = 3;
		}
		if (flag == 0) {
			uploadData.account = proxy.$store.state.account
			return true
		}
		else if (flag == 1) {
			proxy.$message.error("文件格式不符合要求！");
			return false
		}
		else if (flag == 2) {
			proxy.$message.error("文件尺寸不符合要求！");
			return false
		}
		else {
			proxy.$message.error("文件大小不符合要求！");
			return false
		}
	}
	const avatarEvent = () => {
		var that = proxy;
		$(function(){
		    $("#app > div > section > main > div > section > main > div.avatar-uploader").hover(function(){
		        that.dialogAvatarVisible=true;// 鼠标悬浮时触发
		    },function(){
		        that.dialogAvatarVisible=false;// 鼠标离开时触发
		    })
		})
	}
	onMounted(() => {
		avatarEvent()
	})
</script>

<style>
	.avatar-uploader {
		width: 120px;
		height: 120px;
		display: block;
		position:relative;
	}
	.el-upload-list__item-actions {
		width: 120px;
		height: 120px;
		display: block;
		position:absolute;
		top:0px;
		background:rgba(96,98,102,0);
	}
	.el-upload-list__item-actions-div {
		display: flex;
		justify-content: center;
		align-items: center;
	}
	#app > div > section > main > div > section > main > div.avatar-uploader > span.el-avatar.el-avatar--large.el-avatar--circle.el-upload-list__item-actions > div > span:nth-child(1) > i > svg,#app > div > section > main > div > section > main > div.avatar-uploader > span.el-avatar.el-avatar--large.el-avatar--circle.el-upload-list__item-actions > div > span:nth-child(2) > i > svg {
		width: 25px;
		height: 25px;
	}
	.avatar-uploader:hover .el-upload-list__item-actions {
		background:rgba(96,98,102,0.6);
	}
	#app > div > section > main > div > section > main > div:nth-child(3) > div > div.el-dialog__body > div > div.el-upload.el-upload--text > div > i > svg {
		width: 50px;
		height: 50px;
	}
	.upload-diaload-upload,.upload-diaload-upload > div,.upload-diaload-upload > div >div {
		width: 100%;
	}
</style>
