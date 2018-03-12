import Vue from 'vue'
import vueRouter from 'vue-router'
import routes from './router-config.js'

//安装VueRouter插件
Vue.use(vueRouter);
var router = new vueRouter({
	mode:'hash',
	routes,
	scrollBehavior (to, from, savedPosition) {
	    if (savedPosition) {
		    return savedPosition
		} else {
			if (from.meta.keepAlive) {
				from.meta.savedPosition = document.body.scrollTop;
			}
		    return { x: 0, y: to.meta.savedPosition ||0}
		}
	}
});
export default router;
