<template>
	<view class="px-2">
		<view class="bg-white border-bottom my-1 rounded">
			<uni-list-item title="头像" @click="changeAvatar">
				<view class="flex align-center" slot="right">
					<image :src="avatar?avatar:'/static/img/nologin.jpeg'" class="rounded-circle size-100"></image>
					<text class="iconfont icon-bianji1 ml-2"></text>
				</view>
			</uni-list-item>
		</view>

		<view class="bg-white border-bottom my-1 rounded">
			<uni-list-item title="昵称">
				<view class="flex align-center" slot="right">
					<input class="text-right" v-model="nickname" />
					<text class="iconfont icon-bianji1 ml-2"></text>
				</view>
			</uni-list-item>
		</view>

		<view class="bg-white border-bottom my-1 rounded">
			<uni-list-item title="性别">
				<view class="flex align-center" slot="right">
					<!-- <text>{{genderArray[gender]}}</text> -->
					<text class="iconfont icon-bianji1 ml-2"></text>
				</view>
			</uni-list-item>
			
		</view>
		<view class="bg-white border-bottom my-1 rounded">
			<uni-list-item title="生日">
				<view class="flex align-center" slot="right">
					<!-- <text>{{genderArray[gender]}}</text> -->
					<text class="iconfont icon-bianji1 ml-2"></text>
				</view>
			</uni-list-item>
		</view>
		<view class="bg-white border-bottom my-1 rounded">
			<uni-list-item title="地址">
				<view class="flex align-center" slot="right">
					<!-- <text>{{genderArray[gender]}}</text> -->
					<text class="iconfont icon-bianji1 ml-2"></text>
				</view>
			</uni-list-item>
		</view>
		<view class="p-2">
			<button class="rounded-circle bg-pink text-white shadow">完成</button>
		</view>
	</view>
</template>

<script>
import uniListItem from '@/components/uni/uni-list-item/uni-list-item.vue';
export default {
	components: {
		uniListItem
	},
	data() {
		return {
			genderArray: ['男', '女', '保密'],
			nickname: '',
			gender: 0,
			avatar: '',
			user: {}
		};
	},
	onShow() {
		this.user = uni.getStorageSync('user');
		this.nickname = this.user.nickname;
		this.avatar = this.user.avatar;
		this.gender = this.user.gender;
	},
	methods: {
		// 修改头像
		changeAvatar() {
			uni.chooseImage({
				count: 1,
				sizeType: ['compressed'],
				sourceType: ['album', 'camera'],
				success: chooseImageRes => {
					const tempFilePaths = chooseImageRes.tempFilePaths;
					this.avatar = tempFilePaths[0];
					uni.uploadFile({
						url: 'http://ufys6qe0.dnat.tech:39647/api/v1/users/upload',
						filePath: tempFilePaths[0],
						name: 'file'
					})
						.then(result => {
							const res = JSON.parse(result[1].data);
							console.log(res.data);
							this.avatar = res.data;
						})
						.catch(err => {
							console.log(err);
						});
				}
			});
		}
	}
};
</script>

<style></style>
