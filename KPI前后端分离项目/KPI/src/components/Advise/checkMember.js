import { getStore,setStore,getStoreUser } from '../../common/JS/mUtils.js'
import axios from '../../common/JS/axiosUtil.js'
import {MessageBox,Toast} from 'mint-ui'
import routers from '../../router/index.js';

export default () => {
	let user = getStoreUser();
	let userMsg = JSON.parse(getStore('memberMsg'));
	let department = ''
	if(user != null){
		department = user.accountName;
	}
	//let vm_this = this;
	//拿到用户列表，表明人员身份
	return new Promise(resolve => {
		if(null == user){
			MessageBox.confirm('是否进入登录?').then(action => {
				routers.push('/login');
			})
		}else if(undefined==userMsg ||userMsg == ''){
			axios('/member/getAllMember','post',{'accountName' : department})
			.then( data => {
				MessageBox.prompt('请输入姓名').then(obj => {
					let isChoose = false;
					data.member.forEach(row => {
						if(obj.value == row.memberName){
							isChoose = true;
							setStore('memberMsg',row);
							userMsg = row;
						}
					})
					if(!isChoose){
						Toast({message: '不存在' ,duration: 3000});
					}else{
						Toast({message: '操作成功' ,duration: 3000});
					}
						
					return resolve(userMsg);
				})
			})
		}else
			return resolve(userMsg);
	})
}
