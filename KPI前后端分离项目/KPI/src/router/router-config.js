import App from '../App'
import { getStore } from '../common/JS/mUtils.js'
var user = getStore('user');
//按需加载组件


const home = r => require.ensure([],() => r(require('../components/Home/home.vue')), 'home')
const login = r => require.ensure([],() => r(require('../components/login/login.vue')), 'login')
const register = r => require.ensure([],() => r(require('../components/login/register.vue')), 'register')
const kpi = r => require.ensure([],() => r(require('../components/KPI/kpiHome.vue')), 'kpi')

const advise = r => require.ensure([],() => r(require('../components/Advise/adviseHome.vue')), 'advise')
const review = r => require.ensure([],() => r(require('../components/Advise/children/review.vue')), 'review')
const comment = r => require.ensure([],() => r(require('../components/Advise/children/comment.vue')), 'comment')
const myTalk = r => require.ensure([],() => r(require('../components/Advise/children/myTalk.vue')), 'myTalk')
const adviseOur = r => require.ensure([],() => r(require('../components/Advise/children/adviseOur.vue')), 'adviseOur')

const personHome = r => require.ensure([],() => r(require('../components/personHome/personHome.vue')), 'personHome')
const exportKpi = r => require.ensure([],() => r(require('../components/personHome/children/exportKpi.vue')), 'exportKpi')
const changePsw = r => require.ensure([],() => r(require('../components/personHome/children/changePsw.vue')), 'changePsw')
const changeEmail = r => require.ensure([],() => r(require('../components/personHome/children/changeEmail.vue')), 'changeEmail')
const kpiBalance = r => require.ensure([],() => r(require('../components/personHome/children/kpiBalance.vue')), 'kpiBalance')
const itemPrefix = r => require.ensure([],() => r(require('../components/personHome/children/itemPrefix.vue')), 'itemPrefix')

export default[
{
	path:'/',
	component:App,
	children:[
		{path:'',redirect : 'home'},
		{path:'login',component:login},
		{path:'register',component:register},
		{path:'home',component:home},
		{path:'kpi',component:kpi},
		{
			path:'advise',
			redirect : 'advise/review',
			component:advise,
			children:[
				{path:'review',component:review},
				{path:'myTalk',component:myTalk},
				{path:'adviseOur',component:adviseOur}
			]
		},
		{path:'comment',component:comment},
		{path:'personHome',component:personHome},
		{path:'exportKpi',component:exportKpi},
		{path:'changePsw',component:changePsw},
		{path:'changeEmail',component:changeEmail},
		{path:'kpiBalance',component:kpiBalance},
		{path:'itemPrefix',component:itemPrefix},

	]
}]