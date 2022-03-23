<template>
	<view>
		<!-- 未登录 -->
		<template v-if="!loginStatus">
			<view class="flex flex-1 align-center p-2">
				<image :src="avatar" class="size-100 rounded-circle"></image>
				<view class="flex flex-column flex-1 px-3">
					<text class="font-md text-muted">登录，体验更多功能</text>
				</view>
				<text class="iconfont icon-jinru"></text>
			</view>
			<view class="p-2">
				<button
					class="rounded-circle bg-pink text-white shadow"
					@tap="open()"

				>
					去登录
				</button>
			</view>
		</template>
		<!-- 登录 -->
		<template v-else>
			<view style="height: 170px;">
				<image :src="user.bgImg" mode="aspectFill"
					class="w-100 rounded"
					style="height: 170px;filter: blur(0.1px);"></image>
				</view>
			<!-- 头像区域 -->
			<view class="flex align-center p-2 bg-white  ">
				<image :src="avatar" class="size-150 rounded-circle mt5-ng border-2-white"></image>
				<view class="flex flex-column flex-1 px-3 mt1-ng">
					<text class="font-lg font-weight-bold text-dark">{{ user.nickname }}</text>
					<view class="flex align-center">
						<text class="text-muted mr-1">{{ user.address }}</text>
						<image
							:src="user.gender === 1 ? '../../static/img/female.png' : '../../static/img/male.png'"
							style="height: 20px;width: 20px;"
						></image>
					</view>
				</view>
				<text class="iconfont icon-jinru"></text>
			</view>
			<!-- 数据区域 -->
			<view class="flex align-center p-2  bg-white rounded">
				<view
					class="flex flex-column flex-1 align-center justify-center"
					v-for="(item, index) in data"
					:key="index"
				>
					<text class="font-weight-bold">{{ item.num }}</text>
					<text class="font-sm text-muted">{{ item.title }}</text>
				</view>
			</view>
			<!-- 列表区域 -->
			<view class="bg-white mt-1">
				<uni-list-item showExtraIcon title="浏览历史">
					<text slot="icon" class="iconfont icon-liulan">
						<!-- <text class="pl-2 font-md">浏览历史</text> -->
					</text>
				</uni-list-item>
			</view>
			<view class="bg-white mt-1">
				<uni-list-item showExtraIcon title="会员VIP">
					<text slot="icon" class="iconfont icon-huiyuanvip"></text>
				</uni-list-item>
			</view>
			<view class="bg-white mt-1">
				<uni-list-item showExtraIcon title="帖子审核">
					<text slot="icon" class="iconfont icon-keyboard"></text>
				</uni-list-item>
			</view>
		</template>
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
			loginStatus: true,
			user: {},
			nickname: '盏茶浅岷',
			useravatar: 'https://cdn.jsdelivr.net/gh/ycshang123/image-hosting@master/me.583hffnfvkw0.png',
			data: [
				{
					title: '帖子',
					num: 20
				},
				{
					title: '动态',
					num: 120
				},
				{
					title: '评论',
					num: 15
				},
				{
					title: '粉丝',
					num: 7
				}
			]
		};
	},
	computed: {
		avatar() {
			return this.loginStatus === true ? this.user.avatar : '/static/img/nologin.jpeg';
		}
	},
	onShow() {
		this.user = uni.getStorageSync('user');
		console.log(this.user.avatar);
		if (Object.keys(this.user).length === 0) {
			this.loginStatus = false;
		} else {
			this.loginStatus = true;
		}
		console.log(this.user);
		console.log(this.loginStatus);
	},
	onNavigationBarButtonTap() {
		uni.navigateTo({
			url: '../settings/settings'
		});
	},
	methods: {
		open(){
			// uni.redirectTo({
			// 	url:'../login/login'
			// })
			uni.navigateTo({
				url:'../login/login'
			})
		}
	}
};
</script>

<style></style>
