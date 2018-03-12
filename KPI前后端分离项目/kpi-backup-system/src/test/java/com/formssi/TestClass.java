package com.formssi;

import static org.junit.Assert.assertArrayEquals;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.formssi.bean.FormssiKpi;
import com.formssi.bean.Holiday;
import com.formssi.bean.KpiStyle;
import com.formssi.bean.Member;
import com.formssi.bean.Post;
import com.formssi.constans.LeaveDayType;
import com.formssi.constans.Rank;
import com.formssi.constans.WorkType;
import com.formssi.dao.AccountDao;
import com.formssi.dao.FormssiKpiDao;
import com.formssi.dao.HolidayDao;
import com.formssi.dao.KpiStyleDao;
import com.formssi.dao.MemberDao;
import com.formssi.exception.Result;
import com.formssi.redis.RedisClient;
import com.formssi.service.AccountService;
import com.formssi.service.FormssiKpiService;
import com.formssi.service.KpiStyleService;
import com.formssi.service.MemberService;
import com.formssi.service.PostService;
import com.formssi.util.DateUtil;
import com.formssi.util.ParamMap;
import com.formssi.util.ParamUtil;
import com.formssi.util.PasswordUtil;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestClass {

	@Autowired
	PasswordUtil passwordHelper;
	@Autowired
	HolidayDao holidayDao;
	@Autowired
	PostService postService;
	@Autowired
	FormssiKpiDao formssiKpiDao;
	@Autowired
	RedisClient redisClient;
	@Autowired
	@Qualifier("formssiKpiServiceImpl2")
	FormssiKpiService formssiKpiService;
	@Autowired
	MemberService memberService;
	@Autowired
	ParamUtil<String, Object> paramUtil;
	@Autowired
	KpiStyleService kpiService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	KpiStyleDao kpiStyleDao;
	
	@Autowired
	AccountDao accountDao;
	
	@Test
	public void testPassword() {
		try {
			System.out.println("############wts  "+passwordHelper.encryptPassword("wts", "123456"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestResult() {
		System.out.println(Result.error(10, "登錄錯誤！"));
	}
	
	@Test
	public void login() {
		final String POST_URL = "http://192.168.31.202:9001/login?userName=smp&&password=123456";
	       try {
	 
	           URL url = new URL(POST_URL);
	 
	           // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
	 
	           HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	           // 此时cnnection只是为一个连接对象,待连接中
	 
	           // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
	 
	           connection.setDoOutput(true);
	         
	           // 设置连接输入流为true
	 
	           connection.setDoInput(true);
	          
	           // 设置请求方式为post
	 
	           connection.setRequestMethod("POST");
	          
	           // post请求缓存设为false
	 
	           connection.setUseCaches(false);
	            
	           // 设置该HttpURLConnection实例是否自动执行重定向
	 
	           connection.setInstanceFollowRedirects(true);
	           
	           // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
	 
	           // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
	 
	           connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	           
	           // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
	 
	           connection.connect();
	            
	           // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
	 
	           DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
	 
	           String parm = "storeId=" + URLEncoder.encode("32", "utf-8"); //URLEncoder.encode()方法  为字符串进行编码
	            
	           // 将参数输出到连接
	 
	           dataout.writeBytes(parm);
	            
	           // 输出完成后刷新并关闭流
	 
	           dataout.flush();
	 
	           dataout.close(); // 重要且易忽略步骤 (关闭流,切记!) 
	            
	           System.out.println(connection.getResponseCode());
	           
	           // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
	 
	           BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
	 
	           @SuppressWarnings("unused")
			   String line;
	 
	           StringBuilder sb = new StringBuilder(); // 用来存储响应数据
	           
	           // 循环读取流,若不到结尾处
	 
	           while ((line = bf.readLine()) != null) {
	 
	               sb.append(bf.readLine());
	 
	           }
	 
	           bf.close();    // 重要且易忽略步骤 (关闭流,切记!) 
	 
	           connection.disconnect(); // 销毁连接
	 
	           System.out.println(sb.toString());
	    
	       } catch (Exception e) {
	 
	           e.printStackTrace();
	 
	       }
	}
	
	@Test
	public void testBatchImportHoliday() {
		String hoildayStr = "[{'holiday_type':'holiday','holiday_date':'2018/01/01','year':2018,'month':1},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/02/15','year':2018,'month':2},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/02/16','year':2018,'month':2},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/02/19','year':2018,'month':2},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/02/20','year':2018,'month':2},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/02/21','year':2018,'month':2},"
				+ "{'holiday_type':'workday','holiday_date':'2018/02/24','year':2018,'month':2},"
				+ "{'holiday_type':'workday','holiday_date':'2018/02/11','year':2018,'month':2},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/04/05','year':2018,'month':4},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/04/06','year':2018,'month':4},"
				+ "{'holiday_type':'workday','holiday_date':'2018/04/08','year':2018,'month':4},"
				+ "{'holiday_type':'workday','holiday_date':'2018/04/28','year':2018,'month':4},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/04/30','year':2018,'month':4},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/05/01','year':2018,'month':5},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/06/18','year':2018,'month':6},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/09/24','year':2018,'month':9},"
				+ "{'holiday_type':'workday','holiday_date':'2018/09/29','year':2018,'month':9},"
				+ "{'holiday_type':'workday','holiday_date':'2018/09/30','year':2018,'month':9},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/10/01','year':2018,'month':10},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/10/02','year':2018,'month':10},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/10/03','year':2018,'month':10},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/10/04','year':2018,'month':10},"
				+ "{'holiday_type':'holiday','holiday_date':'2018/10/05','year':2018,'month':10},]";
		List<Holiday> holidayList = new ArrayList<Holiday>();
		holidayList = JSONObject.parseArray(hoildayStr,Holiday.class);
		holidayDao.batchImportHoliday(holidayList);
	}
	
	@Test
	public void testGetHolidayList() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("year", 2018);
		param.put("month", 10);
		List<Holiday> holidayList = holidayDao.getHolidayList(param);
		for (Holiday holiday : holidayList) {
			System.out.println(holiday.toString());
		}
	}
	
	@Test
	public void testArray() {
		String date = "2018-01-01";
		int[] dateArray = Arrays.stream(date.split("-")).mapToInt(e -> Integer.parseInt(e)).toArray();
		for (int i : dateArray) {
			System.out.println(i);
		}
	}
	
	@Test
	public void testErum() {
		WorkType kt = WorkType.valueOf("Leave");
		System.out.println(kt == WorkType.Leave);
	}
	
	@Test
	public void testList() {
		FormssiKpi formssiKpi1 = new FormssiKpi();
		FormssiKpi formssiKpi2 = new FormssiKpi();
		FormssiKpi formssiKpi3 = new FormssiKpi();
		FormssiKpi formssiKpi4 = new FormssiKpi();
		FormssiKpi formssiKpi5 = new FormssiKpi();
		formssiKpi1.setId(1L);
		formssiKpi1.setExtraWork(0.25);
		formssiKpi1.setWorkType("ExtraWork");
		formssiKpi1.setRank("SA");
		
		formssiKpi2.setId(2L);
		formssiKpi2.setExtraWork(0.25);
		formssiKpi2.setWorkType("ExtraWork");
		formssiKpi2.setRank("AP");
		
		formssiKpi3.setId(3L);
		formssiKpi3.setNormalWork(0.25);
		formssiKpi3.setWorkType("Leave");
		formssiKpi3.setRank("PR");
		
		formssiKpi4.setId(4L);
		formssiKpi4.setNormalWork(0.5);
		formssiKpi4.setWorkType("Leave");
		formssiKpi4.setRank("T1");
		
		formssiKpi5.setId(5L);
		formssiKpi5.setNormalWork(1.0);
		formssiKpi5.setWorkType("Leave");
		
		List<FormssiKpi> formssiKpiList = new ArrayList<>();
		formssiKpiList.add(formssiKpi1);
		formssiKpiList.add(formssiKpi2);
		formssiKpiList.add(formssiKpi3);
		formssiKpiList.add(formssiKpi4);
		formssiKpiList.add(formssiKpi5);
		List<FormssiKpi> formssiWorkDayList = formssiKpiList.stream().filter((FormssiKpi fk) -> 
		(WorkType.valueOf(fk.getWorkType()) == WorkType.ExtraWork )).collect(Collectors.toList());
		List<FormssiKpi> formssiLeaveDayList = formssiKpiList.stream().filter((FormssiKpi fk) -> 
		(WorkType.valueOf(fk.getWorkType()) == WorkType.Leave)).collect(Collectors.toList());
		System.out.println("**********" + formssiLeaveDayList.size());
		if (formssiLeaveDayList.size() != 0) {
			for (FormssiKpi formssiKpi : formssiLeaveDayList) {
				System.out.println(formssiKpi.toString());
			}
		}
		
		if (formssiWorkDayList.size() != 0) {
			for (FormssiKpi formssiKpi : formssiWorkDayList) {
				System.out.println(formssiKpi.toString());
			}
		}
	}
	
	@Test
	public void testDateFormat() {
		String date = "2017-08-12";
		System.out.println(date.replace("-", "/"));
	}
	
	@Test
	public void testPro() {
		FormssiKpi formssiKpi1 = new FormssiKpi();
		formssiKpi1.setId(1L);
		formssiKpi1.setExtraWork(0.25);
		formssiKpi1.setWorkType("ExtraWork");
		formssiKpi1.setRank("SA");
		
		FormssiKpi formssiKpi2 = new FormssiKpi();
		formssiKpi2.setId(2L);
		formssiKpi2.setExtraWork(0.25);
		formssiKpi2.setWorkType("ExtraWork");
		formssiKpi2.setRank("AP");
		
		KpiStyle kpiStyle = new KpiStyle();
		kpiStyle.setProgramNamePrefix("中银香港-香港-开放系统-销售流程管理系统-");
		kpiStyle.setProgramSerialNumPrefix("BOCHK-HK-OPEN-SMP-");
		setNormalProgramDes(formssiKpi1, kpiStyle);
		setNormalProgramDes(formssiKpi2, kpiStyle);
		System.out.println(formssiKpi1.toString() + " ****** " + formssiKpi2.toString() );
	}
	
	@Test
	public void testLeavePro() {
		FormssiKpi formssiKpi1 = new FormssiKpi();
		formssiKpi1.setId(1L);
		formssiKpi1.setExtraWork(0.25);
		formssiKpi1.setWorkType("ExtraWork");
		formssiKpi1.setRank("SA");
		formssiKpi1.setLeaveDayType("L1");
		
		FormssiKpi formssiKpi2 = new FormssiKpi();
		formssiKpi2.setId(2L);
		formssiKpi2.setExtraWork(0.25);
		formssiKpi2.setWorkType("Leave");
		formssiKpi2.setRank("AP");
		formssiKpi2.setLeaveDayType("L2");
		
		/*KpiStyle kpiStyle = new KpiStyle();
		kpiStyle.setProgramNamePrefix("中银香港-香港-开放系统-销售流程管理系统-");
		kpiStyle.setProgramSerialNumPrefix("BOCHK-HK-OPEN-SMP-");
		setLeaveProgramDes(formssiKpi1, kpiStyle);
		setLeaveProgramDes(formssiKpi2, kpiStyle);
		System.out.println(formssiKpi1.toString() + " ****** " + formssiKpi2.toString() );*/
		List<FormssiKpi> formssiKpiList = new ArrayList<>();
		formssiKpiList.add(formssiKpi1);
		formssiKpiList.add(formssiKpi2);
		FormssiKpi formssiKpi3 = formssiKpiList.stream().filter(f -> WorkType.Leave == WorkType.valueOf(f.getWorkType())).collect(Collectors.toList()).get(0);
		System.out.println(formssiKpi3.toString());
	}
	
	private void setNormalProgramDes(FormssiKpi formssiKpi,KpiStyle kpiStyle) {
		Arrays.stream(Rank.values()).map(e -> Rank.valueOf(formssiKpi.getRank())).forEach(e -> 
		{
			formssiKpi.setProgramSerialNum(kpiStyle.getProgramSerialNumPrefix() + e.getEngDescription());
			formssiKpi.setProgramName(kpiStyle.getProgramNamePrefix() + e.getChiDescriptione());
		});
	}
	
	@SuppressWarnings("unused")
	private void setLeaveProgramDes(FormssiKpi formssiKpi,KpiStyle kpiStyle) {
		Arrays.stream(LeaveDayType.values()).map(e -> LeaveDayType.valueOf(formssiKpi.getLeaveDayType())).forEach(e -> 
		{
			formssiKpi.setProgramSerialNum(kpiStyle.getProgramSerialNumPrefix() + e.getEngDescription());
			formssiKpi.setProgramName(kpiStyle.getProgramNamePrefix() + e.getChiDescriptione());
		});
	}
	
	@Test
	public void uuid() {
		for (int i = 0; i < 15; i++) {
			System.out.println(UUID.randomUUID());
		}
	}
	
	@Test
	public void testCreatePost() {
		postService.createPostService("afasdfasdfasdf", 1L);
	}
	
	@Test
	public void testKpiData() {
		Map<String, Object> map = new HashMap<>();
		map.put("year", 2018);
		map.put("month", 2);
		map.put("department", "smp");
		Result result;
		assertArrayEquals(new Object[]{
				result = formssiKpiService.getKpiData(map)
		}, new Object[] {
				result
		});
	}
	
	@Test
	public void testSaveMember() {
		Member member = new Member();
		member.setDepartment("smp");
		member.setMemberName("test");
		member.setRank("SA");
		assertArrayEquals(new Object[]{
				member = memberService.saveMember(member)
		}, new Object[] {
				
		});
	}
	
	@Test
	public void testDeleteMember() {
		Long memberId = 34L;
		assertArrayEquals(new Object[]{
				memberService.deleteMember(memberId)
		}, new Object[] {
				
		});
	}
	
	@Test
	public void testGetKpiBalance() {
		
		assertArrayEquals(new Object[]{
				formssiKpiService.getKpiBalance("smp")
		}, new Object[] {
		});
	}
	
	
	@Test
	public void testOperateKPi() {
		double kpiData = 0.5;
		FormssiKpi formssiKpi = new FormssiKpi();
		List<FormssiKpi> formssiKpiList = new ArrayList<>();
		handleData(formssiKpi, formssiKpiList);
		formssiKpiService.operateKpi(formssiKpiList, kpiData);
	}
	
	private void handleData(FormssiKpi formssiKpi,List<FormssiKpi> formssiKpiList) {
		formssiKpi = new FormssiKpi();
		formssiKpi.setDate("2018-02-16");
		formssiKpi.setDepartment("smp");
		formssiKpi.setMemberId(31L);
		formssiKpi.setMemberName("wkk");
		formssiKpi.setWorkType("ExtraWork");
		formssiKpi.setRank("SA");
		formssiKpi.setExtraWork(0.5);
		formssiKpiList.add(formssiKpi);
	}
	
	@Test
	public void testComment() {
		assertArrayEquals(new Object[] {
				postService.coment(2L, "I am first floor", 1L),
				postService.coment(3L, "I am second floor", 1L),
				postService.coment(4L, "I am third floor", 1L)
		}, new Object[] {
				
		});
	}
	
	@Test
	public void testReply() {
		assertArrayEquals(new Object[] {
				postService.createReply(4L, "I am first reply", 1L, 2L),
				postService.createReply(4L, "I am second reply", 1L, 3L),
				postService.createReply(4L, "I am third reply", 1L, 4L)
		}, new Object[] {
				
		});
	}
	
	@Test
	public void testGetPost() {
		List<Post> postList = postService.getPost(0L, 2L);
		postList.stream().forEach(post -> System.out.println(post.toString()));
	}
	
	@Test
	public void testGetSpecificPersonPost() {
		List<Post> postList = postService.getSpecificPersonPost(0L, 4L, "smp");
		postList.stream().forEach(post -> System.out.println(post.toString()));
	}
	
	@Test
	public void testGetSpecificPostInfo() {
		Map<String, Object> map = postService.getSpecificPostInfo(0L, 4L, 1L);
		System.out.println(((Post)map.get("post")).toString());
	}
	
	@Test
	public void testKpiStyle() {
		kpiService.getKpiStyle("afds");
	}
	
	@Test
	public void testInitFormssiKpi() {
		String date = "2018-02-01";
		String department = "smp";
		final String DEFAULT = "default";
		int year = DateUtil.getDateArray(date)[0];
		int month = DateUtil.getDateArray(date)[1];
		FormssiKpi formssiKpi = new FormssiKpi();
		List<FormssiKpi> formssiKpiList = new ArrayList<>();
		ParamMap<String, Object> params = new ParamMap<>();
		List<Member> memberList = memberDao.list(paramUtil.getParamMap(params, null, department,null));
		KpiStyle kpiStyle = Optional.ofNullable(kpiStyleDao.getKpiStyle(department)).orElse(kpiStyleDao.getKpiStyle(DEFAULT));
		List<Holiday> holidayList = Optional.ofNullable(holidayDao.getHolidayList(
				paramUtil.getParamMap(params, year, month,null,null,null))).orElse(new ArrayList<>());
		List<String> dates = DateUtil.getDatesList(year, month, holidayList);
		memberList.forEach(member -> {
			dates.forEach(strDate ->{
				FormssiKpi formssiKpiClone = (FormssiKpi) formssiKpi.clone();
				formssiKpiClone.setDate(strDate);
				formssiKpiClone.setMemberName(member.getMemberName());
				formssiKpiClone.setRank(member.getRank());
				formssiKpiClone.setNormalWork(1.0);
				Arrays.stream(Rank.values()).map(rank -> Rank.valueOf(member.getRank())).forEach(rank ->{
					formssiKpiClone.setProgramSerialNum(kpiStyle.getProgramSerialNumPrefix() + rank.getEngDescription());
					formssiKpiClone.setProgramName(kpiStyle.getProgramNamePrefix() + rank.getChiDescriptione());
				});
				formssiKpiClone.setYear(year);
				formssiKpiClone.setMonth(month);
				formssiKpiClone.setMemberId(member.getMemberId());
				formssiKpiClone.setVersion(1);
				formssiKpiClone.setDepartment(department);
				formssiKpiClone.setWorkType(WorkType.NormalWork.name());
				formssiKpiList.add(formssiKpiClone);
			});
		});
		formssiKpiList.parallelStream().forEach(formssiKpi1 -> System.out.println(formssiKpi1.toString()));
	}
	
	@Test
	public void testGetPersonalKpi() {
		String date="2018-02-01";
		String department = "smp";
		String memberName="wkk";
		ParamMap<String, Object> params = new ParamMap<>();
		int[] dateArray =DateUtil.getDateArray(date);
		double db = formssiKpiDao.getPersonalKpi(paramUtil.getParamMap(params, dateArray[0], dateArray[1],memberName,department,null));
		System.out.println("kpi统计值是: " + db);
	}
	
	@Test
	public void testGetAccount() {
		accountDao.getAccount("smp");
	}
	
	@Test
	public void testErumAndString() {
		System.out.println("workday".equals(HolidayType.workday.name()));
	}
	
	@Test
	public void testUpdateMember() {
		Member member1 = new Member();
		Member member2 = new Member();
		List<Member> memberList = new ArrayList<>();
		member1.setMemberId(31L);
		member1.setRank("L1");
		member2.setMemberId(32L);
		member2.setRank("L1");
		memberList.add(member1);
		memberList.add(member2);
		memberService.updateAllMember(memberList);
	}
	
	@Test
	public void testDouble() {
		ParamMap<String, Object> params = new ParamMap<String, Object>();
		int year = 2018;
		int month = 1;
		String department ="smp";
		params = paramUtil.getParamMap(params, year, month,department,null,null);
		List<FormssiKpi> formssiKpiList = Optional.ofNullable(formssiKpiDao.getAllNotLeftData(params)).get();
		formssiKpiList = formssiKpiList.stream().filter(formssiKpi -> {
			if (formssiKpi.getExtraWork() != null) {
				if (formssiKpi.getNormalWork() != null) {
					if (0 == Optional.ofNullable(formssiKpi.getExtraWork()).orElse(0.0)  && 0 == Optional.ofNullable(formssiKpi.getNormalWork()).orElse(0.0)) {
						return false;
					}
				}
			}
			return true;
		}).collect(Collectors.toList());
		formssiKpiList.stream().forEach(formssikpi -> System.out.println(formssikpi.toString()));
	}
	
}

enum HolidayType {
	
	workday,
	
	holiday,
	
	;
	
}
