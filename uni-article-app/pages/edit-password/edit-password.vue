<template>
	<view class="px-2">
		<input
			class="uni-input p-2 bg-white border-bottom my-2"
			type="text"
			placeholder="输入新密码"
			v-model="newpassword"
		/>
		<input
			class="uni-input p-2 bg-white border-bottom my-2"
			type="text"
			placeholder="请再次输入密码"
			v-model="renewpassword"
		/>

		<view class="py-2">
			<button
				class="bg-pink text-white rounded-circle"
				:disabled="disabled"
				:class="disabled ? 'bg-pink-disabled' : ''"
				@tap="submit()"
			>
				确定
			</button>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			user: {},
			newpassword: '',
			renewpassword: ''
		};
	},
	computed: {
		disabled() {
			return this.newpassword == '' || this.renewpassword == '';
		}
	},
	onShow() {
		this.user = uni.getStorageSync('user');
	},
	methods: {
		// 校验两次密码是否一致
		check() {
			if (this.newpassword != this.renewpassword) {
				this.$msg.toast('两次密码不一致', 'err');
				return false;
			}

			return true;
		},
		submit() {
			if (!this.check()) {
				return;
			}
			let data = {
				phone: this.user.phone,
				password: this.newpassword,
				nickname: this.user.nickname,
				avatar: this.user.avatar,
				gender: this.user.gender,
				birthday: this.user.birthday,
				address: this.user.address,
				bgImg: this.user.bgImg
			};
			this.$http.post('/users/update', data, 'json').then(res => {
				console.log(res.code)
				if (res.code === 1) {
					this.$msg.toast('修改成功','suc');
					console.log(123)
					console.log(this.user);
					uni.navigateBack({
						delta: 1
					});
				}else{
					this.$msg.toast('密码修改失败请稍后再试', 'err');
				}
			});
		}
	}
};
</script>

<style></style>
