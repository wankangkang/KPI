import axios from 'axios';
import { Indicator,Toast,MessageBox } from 'mint-ui';
import routers from '../../router/index.js';
import querystring from 'querystring';
import {getStore} from './mUtils.js';
var http = axios.create({
 timeout: 60000, /*设置请求超时时间*/
 baseURL:'/api'
});
http.interceptors.request.use(function (config) {
	let token = getStore('token');
	if(token){
		config.headers.Authorization = token;
	}
// 配置发送请求的信息
	return config;
}, function (error) {
 return Promise.reject(error);
});

http.interceptors.response.use(function (response) {
 // 配置请求回来的信息
 return response;
}, function (error) {
 return Promise.reject(error);
});

// Alter defaults after instance has been created
http.defaults.headers.common['Authorization'] = '';
//http.defaults.withCredentials = true;
http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
let transformRequest = function (data) { 
		let ret = '' 
		for (let it in data) { 
			ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&' 
		} 
		return ret.substring(0, ret.length - 1);
	};

export default (url, method, params, httpParams={}) => {
	//debugger;
	method = method.toLowerCase();
	let paramObj = {};
	if(method == 'post'){
		//paramObj = transformRequest(params);
		paramObj = querystring.stringify(params);
	}else if(method == 'get'){
		paramObj = {
			'params' : params
		}
	}
	console.dir(paramObj);
	Indicator.open('加载中...');
	return new Promise((resolve,reject) => {
		http[method] (url,paramObj,httpParams)
		  .then(response => {
		  	console.dir(response.data);
		  	if(response.data.code == 200 || (response.data.type == 'application/octet-stream')){
		  		//成功访问接口，并返回参数
		  		Indicator.close();
		  		return resolve(response.data);
		  	}else if(response.data.code == 1001){
		  		Indicator.close();
		  		//alert('axios')
		  		MessageBox.confirm('是否进入登录?').then(action => {
		  			routers.push('/login');				  
				}).catch(() => {
					Indicator.close();
					return reject(response.data);
				});
		  		//需要登录权限，路由到登录界面
		  	}else{
		  		Toast({message: response.data.msg ,duration: 3000});
		  		//成功访问接口，但出错了
		  		Indicator.close();
		  		return reject(response.data);
		  	}
		  }).catch(res => {
		  	console.dir(res);
		  	Indicator.close();
		  	Toast({
			  message: '请求超时',
			  duration: 3000
			});
		});
	});
}
