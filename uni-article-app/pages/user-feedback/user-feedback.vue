<template>
	<view>
		<uni-collapse class="pt-2">
			<uni-collapse-item title="标题" showAnimation>
				<view class="content">
					<input type="text" placeholder="标题" class="border-bottom p-2"/>
				</view>
			</uni-collapse-item>
			<uni-collapse-item title="反馈内容" showAnimation>
				<view class="content">
					<input type="text" placeholder="反馈内容" class="border-bottom p-2"/>
				</view>
			</uni-collapse-item>
		</uni-collapse>
		<view class="p-2">
			<button class="rounded-circle bg-pink text-white shadow">意见反馈</button>
		</view>
		
	</view>
</template>

<script>
	import uniCollapse from '@/components/uni/uni/uni-collapse/uni-collapse.vue';
	import uniCollapseItme from '@/components/uni/uni-collapse-item/uni-collapse-item.vue'
	export default {
		components:{
			uniCollapse,
			uniCollapseItme
		},
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style>

</style>
