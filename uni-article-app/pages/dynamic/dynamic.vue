<template>
	<view>
		<!-- 留出状态条 -->
		<!-- #ifndef MP -->
		<uni-status-bar></uni-status-bar>
		<view class="banner" :style="{ '--bgurl': 'url(' + user.bgImg + ')' }">
			<view style="position: absolute; z-index: 20; left: 20rpx; padding-top: 200px;" class="flex">
				<image :src="user.avatar" class="size-100 rounded-circle"></image>
				<view class="flex-1">
					<text>{{ user.nickname }}</text>
					<text>{{ user.address }}</text>
				</view>
			</view>
		</view>
		<view class="bg-pink" style="z-index: 20; height: 100rpx; width: 100%;"></view>
		<!-- #endif -->
	</view>
</template>

<script>
import uniStatusBar from '@/components/uni/uni-status-bar/uni-status-bar.vue';
export default {
	components: {
		uniStatusBar
	},
	data() {
		return {
			user: {}
		};
	},
	onShow() {
		this.user = uni.getStorageSync('user');
	},
	methods: {}
};
</script>

<style scoped>
.banner {
	box-sizing: border-box;
	position: relative;
	width: 100%;
	height: 500rpx;
	background-color: #007aff;
}
.banner::before {
	content: '';
	position: absolute;
	height: 500rpx;
	top: 0;
	left: 0;
	width: 100%;
	background-image: var(--bgurl);
	/* background-image: url(../../static/img/banner.jpg); */
	background-repeat: no-repeat;
	background-size: cover;
	/* background-image: linear-gradient(45deg, #bbdefb, #e3f2fd); */
	border-radius: 0 0 50% 50%/0 0 100% 100%;
	transform: scaleX(1.5);
	z-index: 10;
}
</style>
