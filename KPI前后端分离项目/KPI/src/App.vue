<template>
<div>
	<transition>
		<keep-alive>
		    <router-view v-if="$route.meta.keepAlive"></router-view>
		</keep-alive>
	</transition>
	<transition >
		<router-view v-if="!$route.meta.keepAlive"></router-view>
	</transition>
</div>
	
	
</template>

<script>
import {getStore,setStore} from './common/JS/mUtils.js'
export default {
	created(){
		
	}
 }
</script>
	
<style lang="scss">
	@import './common/stylus/common.scss';
			/*.router-fade-enter-active, .router-fade-leave-active {
			  	transition: opacity .3s;
			}
			.router-fade-enter, .router-fade-leave-active {
			  	opacity: 0;
			}*/
			.router-fade-enter-active, .router-fade-leave-active {
		        transition: all 1s;
		    }
		    .router-fade-enter, .router-fade-leave-active {
		        transform: translate3d(2rem, 0, 0);
		        opacity: 0;
		    }
		  
</style>
