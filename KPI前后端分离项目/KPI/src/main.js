import Vue from 'vue'
import App from './App'
import {store} from './store/index.js'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
/*import '../node_modules/bootstrap/dist/js/bootstrap.min'*/
import router from './router/index.js' 
import axiosUtil from './common/JS/axiosUtil.js'
import './common/JS/rem.js'
import Icon from 'vue-svg-icon/Icon.vue';

Vue.component('icon', Icon);  

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(MintUI);
Vue.prototype.$http = axiosUtil;
Vue.prototype.Toast = MintUI.Toast;
/**导出http，在mainjs中引用
import http from './config/axiosConfig';
Vue.prototype.$http = http;
**/

///* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store: store,
  render: h => h(App)		//渲染Dom界面
})
