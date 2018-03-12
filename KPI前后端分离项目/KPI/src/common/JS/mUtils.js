/**
 * 存储localStorage
 */
export const setStore = (name, content) => {
	if (!name) return;
	if (typeof content !== 'string') {
		content = JSON.stringify(content);
	}
	window.localStorage.setItem(name, content);
}

/**
 * 获取localStorage
 */
export const getStore = name => {
	if (!name) return;
	return window.localStorage.getItem(name);
}

/**
 * 获取localStorage user
 */
export const getStoreUser = () => {
	let user = JSON.parse(window.localStorage.getItem('user'))
	/*if(null == user){
		MessageBox.confirm('是否进入登录?').then(action => {
  			router.push('/login');				  
		});
		return ;
	}else*/
		return user;
}
/**
 * 删除localStorage
 */
export const removeStore = name => {
	if (!name) return;
	window.localStorage.removeItem(name);
}

/**
 * 显示返回顶部按钮，开始、结束、运动 三个过程中调用函数判断是否达到目标点
 */
export const showBack = callback => {
    let requestFram;
    let oldScrollTop;

    document.addEventListener('scroll',() => {
       showBackFun();
    }, false)
    document.addEventListener('touchstart',() => {
       showBackFun();
    },{passive: true})

    document.addEventListener('touchmove',() => {
       showBackFun();
    },{passive: true})

    document.addEventListener('touchend',() => {
        oldScrollTop = document.body.scrollTop;
        moveEnd();
    },{passive: true})
    
    const moveEnd = () => {
        requestFram = requestAnimationFrame(() => {
            if (document.body.scrollTop != oldScrollTop) {
                oldScrollTop = document.body.scrollTop;
                moveEnd();
            }else{
                cancelAnimationFrame(requestFram);
            }
            showBackFun();
        })
    }

    //判断是否达到目标点
    const showBackFun = () => {
        if (document.body.scrollTop > 500) {
            callback(true);
        }else{
            callback(false);
        }
    }
}
/**
 * 返回时间格式YYYY-MM-DD
 */
export const DateFormate = date => {
	if (!date) return;
	let year = date.getFullYear();
	let month = date.getMonth()<9 ? '0'+(date.getMonth()+1) : date.getMonth()+1;
	let day = date.getDate()<=9 ? '0'+date.getDate() : date.getDate();
	
	return year + '-' + month + '-' + day;
}

/*
 * 实现对象深复制
 */
export const cloneObj = function(obj){
    var str, newobj = obj.constructor === Array ? [] : {};
    if(typeof obj !== 'object'){
        return;
    } else if(window.JSON){
        str = JSON.stringify(obj), //系列化对象
        newobj = JSON.parse(str); //还原
    } else {
        for(var i in obj){
            newobj[i] = typeof obj[i] === 'object' ? 
            cloneObj(obj[i]) : obj[i]; 
        }
    }
    return newobj;
};
/**
 * 输入时间字符串，格式为：yyyy/mm/dd hh:MM/ss，返回与当前时间差
 */
export const dateStyleStr = function(dataStr){
    let list1 = dataStr.split("/");
	let list2 = list1[2].split(" ");
	let list3 = list2[1].split(":");
	let returnDate = new Date(list1[0],list1[1]-1,list2[0],list3[0],list3[1],list3[2]);
	let newDate = new Date();
	let diff = parseInt(newDate - returnDate) ;
	let diffS = diff / 1000;
	let diffM = diff / 60000;
	let diffH = diff / 3600000;
	let diffD = diff/(3600000 * 24);
	let returnStr = '';
	if(diff <= 1000){
		return returnStr='刚刚';
	}else if(diffS < 60){
		return returnStr = parseInt(diffS) + '秒前';
	}else if(diffM < 60 ){
		return returnStr = parseInt(diffM) + '分钟前';
	}else if(diffH < 24){
		return returnStr = parseInt(diffH) + '小时前';
	}else if(diffD < 7){
		return returnStr = parseInt(diffD) + '天前';
	}else{
		return returnStr = list1[0]+'-'+list1[1]+'-'+list2[0];
	}
};
