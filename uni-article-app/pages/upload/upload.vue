<template>
	<view class="px-2">
		<view class="bg-white border-bottom my-1 rounded">
			<uni-list-item title="头像" @click="changeAvatar">
				<view class="flex align-center" slot="right">
					<image :src="avatar ? avatar : '/static/img/nologin.jpeg'" class="rounded-circle size-100"></image>
					<text class="iconfont icon-bianji1 ml-2"></text>
				</view>
			</uni-list-item>
		</view>

		<view class="bg-white border-bottom my-1 rounded">
			<uni-list-item title="昵称">
				<view class="flex align-center" slot="right">
					<input class="uni-input text-right" v-model="nickname" />
					<text class="iconfont icon-bianji1 ml-2"></text>
				</view>
			</uni-list-item>
		</view>

		<view class="bg-white border-bottom my-1 rounded" @tap="changeGender">
			<uni-list-item title="性别">
				<view class="flex align-center" slot="right">
					<text>{{ genderText }}</text>
					<text class="iconfont icon-bianji1 ml-2"></text>
				</view>
			</uni-list-item>
		</view>
		<picker mode="date" :value="birthday" @change="onDateChange">
			<uni-list-item title="生日">
				<view class="flex align-center" slot="right">
					<text>{{ birthday }}</text>
					<text class="iconfont icon-bianji1 ml-2"></text>
				</view>
			</uni-list-item>
		</picker>
		<view class="bg-white border-bottom my-1 rounded" @tap="showCityPicker">
			<uni-list-item title="地址">
				<view class="flex align-center" slot="right">
					<text class="flex-1">{{ address }}</text>
					<text class="iconfont icon-bianji1 ml-2"></text>
				</view>
			</uni-list-item>
		</view>
		<view class="p-2"><button class="rounded-circle bg-pink text-white shadow" @tap="submit()">完成</button></view>
		<mpvue-city-picker
			:themeColor="themeColor"
			ref="mpvueCityPicker"
			:pickerValueDefault="cityPickerValueDefault"
			@onConfirm="onConfirm"
		></mpvue-city-picker>
	</view>
</template>

<script>
const genderArray = ['保密', '男', '女'];
import uniListItem from '@/components/uni/uni-list-item/uni-list-item.vue';
import config from '@/lib/config.js';
import mpvueCityPicker from '@/components/uni/mpvue-citypicker/mpvueCityPicker.vue';
export default {
	components: {
		uniListItem,
		mpvueCityPicker
	},
	data() {
		return {
			themeColor:'#FC5C82',
			cityPickerValueDefault: [0, 0, 1],
			birthday: '',
			nickname: '',
			gender: 0,
			avatar: '',
			user: {}
		};
	},

	onBackPress() {
		if (this.$refs.mpvueCityPicker.showPicker) {
			this.$refs.mpvueCityPicker.pickerCancel();
			return true;
		}
	},
	onShow() {
		this.user = uni.getStorageSync('user');
		this.nickname = this.user.nickname;
		this.avatar = this.user.avatar;
		this.gender = this.user.gender;
		this.birthday = this.user.birthday;
		this.address = this.user.address;
	},
	onUnload() {
		if (this.$refs.mpvueCityPicker.showPicker) {
			this.$refs.mpvueCityPicker.pickerCancel();
		}
	},
	computed: {
		genderText: {
			set(newValue) {
				this.genderText = newValue;
			},
			get() {
				return genderArray[this.gender];
			}
		}
	},
	methods: {
		// 显示三级联动城市选择组件
		showCityPicker() {
			this.$refs.mpvueCityPicker.show();
		},
		// 三级联动选择组件提交事件,监听相应的值并显示
		onConfirm(e) {
			this.address = e.label;
		},
		// 监听日期选取，改变生日显示的值
		onDateChange(e) {
			this.birthday = e.detail.value;
		},
		// 选择头像
		changeAvatar() {
			uni.chooseImage({
				count: 1,
				sizeType: ['compressed'],
				sourceType: ['album', 'camera'],
				success: res => {
					this.avatar = res.tempFilePaths[0];
				}
			});
		},
		// 修改性别
		changeGender() {
			uni.showActionSheet({
				itemList: genderArray,
				success: res => {
					this.gender = res.tapIndex;
					if (this.gender == 0) {
						this.genderText = '保密';
					} else if (this.gender == 1) {
						this.genderText = '男';
					} else if (this.gender == 2) {
						this.genderText = '女';
					}
				}
			});
		},
		// 修改头像
		submit() {
			uni.uploadFile({
				url: config.baseUrl + '/users/upload',
				filePath: this.avatar,
				name: 'file',
				success: uploadFileRes => {
					this.avatar = JSON.parse(uploadFileRes.data).data;
					let data = {
						id: this.user.id,
						phone: this.user.phone,
						password: this.user.password,
						nickname: this.user.nickname === '' ? this.user.nickname : this.nickname,
						avatar: this.avatar,
						gender: this.gender,
						birthday: this.user.birthday === '' ? this.user.birthday : this.birthday,
						address: this.address === '' ? this.user.address : this.address,
						bgImg: this.user.bgImg
					};

					this.$http.post('/users/update', data, 'json').then(res => {
						if (res.code === 1) {
							this.$msg.toast('修改成功');
							uni.setStorageSync('user', res.data);
							uni.switchTab({
								url: '../my/my'
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
