<template>
	<view>
		<!-- 留出状态条 -->
		<!-- #ifndef MP -->
		<uni-status-bar></uni-status-bar>
		<view class="iconfont icon-guanbi flex  align-center justify-start font-lg size-100 p-2" @tap="back()"></view>

		<!-- #endif -->
		<template v-if="choice">
			<view class="flex flex-column">
				<text class="font-weight-bold font-lg text-center" style="padding-top: 90rpx; padding-bottom: 90rpx;">
					账号密码登录
				</text>

				<view class=" md-2 flex px-2">
					<input v-model="phone" placeholder="手机号" class="border-bottom p-2 flex-1" />
				</view>
				<view class="  md-2 flex px-2 py-4">
					<input v-model="password" placeholder="请输入密码" class="border-bottom p-2 flex-1" />
					<view class=" font-sm text-muted px-3 flex align-center">忘记密码?</view>
				</view>
				<view class="p-2">
					<button
						class="rounded-circle bg-pink text-white shadow"
						:class="disabled ? 'bg-pink-disabled' : 'bg-pink'"
						:loading="loading"
						@tap="passwordLogin()"
					>
						{{ loading ? '登录中...' : '登录' }}
					</button>
				</view>
				<view class="text-center mt-3">
					<text class="p-3 text-primary font-sm" @tap="choice = !choice">验证码登录</text>
					<text>|</text>
					<text class="p-3 text-primary font-sm">登录遇到问题</text>
				</view>
				<view class="text-center mt-3 text-muted">
					<label class="flex justify-center">
						<checkbox :value="checked" />
						<view>
							<text class="font-sm text-muted">注册即代表同意</text>
							<text class="font-sm text-primary">《×××协议》</text>
						</view>
					</label>
				</view>
			</view>
		</template>
		<template v-else>
			<view class="flex flex-column">
				<text class="font-weight-bold font-lg text-center" style="padding-top: 90rpx; padding-bottom: 90rpx;">
					手机验证码登录
				</text>

				<view class=" md-2 flex px-2">
					<view class="border-bottom  border-right px-2 flex align-center">+86</view>
					<input v-model="phone" placeholder="手机号" class="border-bottom p-2 flex-1" />
				</view>
				<view class="  md-2 flex px-2 py-4">
					<input v-model="verifyCode" placeholder="请输入验证码" class="border-bottom p-2 flex-1" />
					<view
						@tap="getCode()"
						:class="limitTime > 0 ? 'bg-pink-disabled' : 'bg-pink'"
						class=" font-sm  px-3 flex align-center  rounded text-white"
					>
						{{ limitTime > 0 ? limitTime + 's' : '获取验证码' }}
					</view>
				</view>
				<view class="p-2">
					<button
						class="rounded-circle bg-pink text-white shadow"
						:class="disabled ? 'bg-pink-disabled' : 'bg-pink'"
						@tap="validateLogin()"
					>
						登录
					</button>
				</view>
				<view class="text-center mt-3">
					<text class="p-3 text-primary font-sm" @tap="choice = !choice">账号密码登录</text>
					<text>|</text>
					<text class="p-3 text-primary font-sm">登录遇到问题</text>
				</view>
				<view class="text-center mt-3">
					<text class="font-sm text-muted">注册即代表同意</text>
					<text class="font-sm text-primary">《×××协议》</text>
				</view>
			</view>
			<!-- 第三方登录 -->
			<view class="flex align-center px-5 py-3 mt-3">
				<view class="flex-1 flex  align-center  justify-center">
					<view
						@tap="appLogin()"
						class="iconfont icon-weixin bg-success font-lg text-white flex align-center justify-center rounded-circle size-100"
					></view>
				</view>
				<view class="flex-1 flex  align-center  justify-center">
					<view
						class="iconfont icon-QQ bg-primary font-lg text-white flex align-center justify-center rounded-circle size-100"
					></view>
				</view>
				<view class="flex-1 flex  align-center  justify-center">
					<view
						class="iconfont icon-xinlangweibo bg-warning font-lg text-white flex align-center justify-center rounded-circle size-100"
					></view>
				</view>
			</view>
		</template>
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
			choice: false,
			disabled: false,
			checked: false,
			loading: false,
			phone: '13913457284',
			password: '123123',
			verifyCode: '',
			limitTime: 0
		};
	},
	methods: {
		back() {
			uni.navigateBack({
				delta: 1
			});
		},
		validate() {
			//手机号正则
			var mPattern = /^1[34578]\d{9}$/;
			if (!mPattern.test(this.phone)) {
				uni.showToast({
					title: '手机号格式不正确',
					icon: 'none'
				});
				return false;
			}
			return true;
		},
		getCode() {
			// 防止重复获取
			if (this.limitTime > 0) {
				return;
			}
			// 校验手机号
			if (!this.validate(this.phone)) {
				this.$msg.toast('手机号格式不正确');
				return;
			}
			// 请求验证码接口
			this.$http.post('/users/sms?phone=' + this.phone).then(res => {
				if (res.code === 1) {
					this.limitTime = 60;
					let timer = setInterval(() => {
						if (this.limitTime >= 1) {
							this.limitTime--;
						} else {
							this.limitTime = 0;
							clearInterval(timer);
						}
					}, 1000);
				} else {
					this.$msg.toast(res.msg);
				}
			});
		},

		passwordLogin() {
			let data = {
				phone: this.phone,
				password: this.password
			};
			this.$http.post('/users/passwordLogin', data, 'json').then(res => {
				if (res.code === 1) {
					this.$msg.toast(res.msg);
					uni.setStorageSync('user', res.data);
					uni.switchTab({
						url: '../my/my'
					});
				} else {
					this.$msg.toast(res.msg);
				}
			});
		},

		validateLogin() {
			let data = {
				phone: this.phone,
				code: this.verifyCode
			};
			this.$http.post('/users/verifyLogin', data, 'json').then(res => {
				if (res.code === 1) {
					this.$msg.toast(res.msg);
					uni.setStorageSync('user', res.data);
					uni.switchTab({
						url: '../my/my'
					});
				} else {
					this.$msg.toast(res.msg);
				}
			});
		},
		appLogin() {
			let self = this;
			uni.login({
				provider: 'weixin',
				success: function(loginRes) {
					provider: 'weixin',
						uni.getUserInfo({
							success: infoRes => {
								console.log(infoRes);
								let wxLoginDto = {
									wxOpenId: infoRes.userInfo.openId,
									nickname: infoRes.userInfo.nickName,
									avatar: infoRes.userInfo.avatarUrl,
									gender: infoRes.userInfo.gender
								};
								self.$http.post('/users/login/wx', wxLoginDto, 'json').then(res => {
									console.log(res);
									if (res.code === 1) {
										uni.showModal({
											title: '登录成功',
											success() {
												uni.setStorageSync('user', res.data);
												console.log(res.data)
												if (res.data.phone !=null) {
													uni.switchTab({
														url: '../my/my'
													});
												} else {
													uni.navigateTo({
														url: '../bind-phone/bind-phone'
													});
												}
											}
										});
									} else {
										uni.showModal({
											title: '登录失败'
										});
										return false;
									}
								});
							}
						});
				}
			});
		}
	}
};
</script>

<style></style>
