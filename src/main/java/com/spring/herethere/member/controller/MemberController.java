package com.spring.herethere.member.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.herethere.common.LogCheck;
import com.spring.herethere.member.model.service.MemberService;
import com.spring.herethere.vo.MemberVo;
import com.spring.herethere.vo.ReviewVo;
import com.spring.herethere.vo.ScheduleVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller("memberController")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/member/join/mvRegister.do", method = RequestMethod.GET)
	public String mvRegister() {
		LogCheck.logger.info(LogCheck.logMsg + "mvRegister()");
		return "member/join/register";
	}

	@RequestMapping(value = "/member/join/mvCheckId.do", method = RequestMethod.GET)
	public String mvCheckId() {
		return "member/join/checkId";
	}

	@RequestMapping(value = "/member/join/checkId.do", method = RequestMethod.POST)
	public ModelAndView checkId(@RequestParam("checkId") String id) {
		ModelAndView mav = new ModelAndView();
		String path = "member/join/checkId";
		int cnt = memberService.checkId(id);

		LogCheck.logger.info(LogCheck.logMsg + cnt);

		// 값을 다른 페이지로 넘김
		mav.addObject("checkId", id);
		mav.addObject("idCnt", cnt);

		mav.setViewName(path);
		return mav;
	}

	@RequestMapping(value = "/member/join/register.do", method = RequestMethod.POST)
	public ModelAndView register(MemberVo memberVo) {
		ModelAndView mav = new ModelAndView();
		String path = "member/join/registerFail";

		LogCheck.logger.info(LogCheck.logMsg + memberVo.toString());

		int cnt = memberService.register(memberVo);

		if (cnt != 0) {
			path = "redirect:/member/join/mvRegisterOk.do";
		}

		mav.setViewName(path);
		return mav;
	}

	@RequestMapping(value = "member/join/mvRegisterOk.do", method = RequestMethod.GET)
	public String mvRegisterOk() {
		return "member/join/registerOk";
	}

	@RequestMapping(value = "/member/login/login.do", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam Map<String, Object> map, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// 어디서 로그인을 했는지 알아야 그 페이지를 유지하며 로그인을 할 수 있다
		String path = request.getHeader("referer");
		String contextPath = request.getContextPath();
		MemberVo memberVo = memberService.login(map);

		LogCheck.logger.info(LogCheck.logMsg + path);

		if (memberVo != null) {
			LogCheck.logger.info(LogCheck.logMsg + memberVo.toString());
			// session에 사용자 정보를 넣는다.
			session.setAttribute("userInfo", memberVo);

			// 이동해야할 경로를 만든다.
			path = path.substring(path.lastIndexOf(contextPath) + contextPath.length(), path.length());
			// 메인화면을 띄워야 하는 경로의 경우
			if ("/index.do".equals(path) || "/member/login/logout.do".equals(path) || "/member/join/register.do".equals(path)
					|| "/member/join/mvRegisterOk.do".equals(path)) {
				path = "/index.do";
			}

		} else {
			path = "member/login/loginFail";
			mav.setViewName(path);
			return mav;
		}

		LogCheck.logger.info(LogCheck.logMsg + path);
		mav.setViewName("redirect:" + path);
		return mav;
	}

	@RequestMapping(value = "/member/login/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.do";
	}

	@RequestMapping(value = "/member/myMenu/myInfo/view.do", method = RequestMethod.GET)
	public String myInfoView() {
		return "member/myMenu/myInfo/view";
	}

	@RequestMapping(value = "/member/myMenu/myInfo/mvModify.do", method = RequestMethod.GET)
	public String myInfoMvModify() {
		return "member/myMenu/myInfo/modify";
	}

	@RequestMapping(value = "/member/myMenu/myInfo/modify.do", method = RequestMethod.POST)
	public ModelAndView myInfoModify(@RequestParam Map<String, Object> map, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String path = "member/myMenu/myInfo/modifiyFail";

		int cnt = memberService.updateMember(map);

		if (cnt == 1) {
			// 디비에서 사용자 값을 얻어서 MemberVo에 넣는다.
			MemberVo memberVo = memberService.login(map);

			if (memberVo != null) {
				// 세션에 사용자 정보를 넣는다.
				session.setAttribute("userInfo", memberVo);
				path = "member/myMenu/myInfo/modifyOk";
			}
		}

		mav.setViewName(path);
		return mav;
	}

	@RequestMapping(value = "/member/myMenu/myInfo/mvDelete.do", method = RequestMethod.GET)
	public String myInfoMvDelete() {
		return "member/myMenu/myInfo/delete";
	}

	@RequestMapping(value = "/member/myMenu/myInfo/delete.do", method = RequestMethod.POST)
	public ModelAndView myInfoDelete(@RequestParam Map<String, Object> map, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String path = "member/myMenu/myInfo/deleteFail";

		int cnt = memberService.deleteMember(map);
		LogCheck.logger.info(LogCheck.logMsg + cnt);

		if (cnt != 0) {
			session.invalidate();
			path = "member/myMenu/myInfo/deleteOk";
		}

		mav.setViewName(path);
		return mav;
	}

	@RequestMapping(value = "/member/myMenu/mySchedule/mvList.do", method = RequestMethod.GET)
	public String myScheduleMvList() {
		return "member/myMenu/mySchedule/list";
	}

	@RequestMapping(value = "/member/myMenu/mySchedule/list.do", method = RequestMethod.POST)
	public @ResponseBody String myScheduleList(@RequestBody Map<String, Object> map) {
		List<ScheduleVo> list = memberService.selectMyScheduleList(map);
		int totalCount = memberService.getScheduleListTotalCount(map);

		LogCheck.logger.info(LogCheck.logMsg + map.toString());

		JSONObject jsonObject = new JSONObject();
		JSONArray jarray = new JSONArray();
		for (ScheduleVo vo : list) {
			JSONObject jobj = new JSONObject();
			jobj.put("postSeq", vo.getPostSeq());
			jobj.put("postTypeCode", vo.getPostTypeCode());
			jobj.put("boardCode", vo.getBoardCode());
			jobj.put("userId", vo.getUserId());
			jobj.put("postTitle", vo.getPostTitle());
			jobj.put("hitCount", vo.getHitCount());
			jobj.put("logDate", new SimpleDateFormat("yyyy-MM-dd").format(vo.getLogDate()));
			jobj.put("renewalDate", new SimpleDateFormat("yyyy-MM-dd").format(vo.getRenewalDate()));
			jobj.put("scheduleSeq", vo.getScheduleSeq());
			jobj.put("startDate", new SimpleDateFormat("yyyy-MM-dd").format(vo.getStartDate()));
			jobj.put("endDate", new SimpleDateFormat("yyyy-MM-dd").format(vo.getEndDate()));
			jobj.put("term", vo.getTerm());
			jobj.put("thema", vo.getThema());
			jobj.put("originName", vo.getOriginName());
			jobj.put("saveName", vo.getSaveName());
			jobj.put("savePath", vo.getSavePath());
			jobj.put("dibsCount", vo.getDibsCount());
			jobj.put("recommendCount", vo.getRecommendCount());

			jarray.add(jobj);
		}

		jsonObject.put("list", jarray);
		jsonObject.put("totalCount", totalCount);

		return jsonObject.toString();

	}

	@RequestMapping(value = "/member/myMenu/myReview/mvList.do", method = RequestMethod.GET)
	public String myReviewMvList() {
		return "member/myMenu/myReview/list";
	}

	@RequestMapping(value = "/member/myMenu/myReview/list.do", method = RequestMethod.POST)
	public @ResponseBody String myReviewList(@RequestBody Map<String, Object> map) {
		List<ReviewVo> list = memberService.selectMyReviewList(map);
		int totalCount = memberService.getReviewListTotalCount(map);

		LogCheck.logger.info(LogCheck.logMsg + map.toString());

		JSONObject jsonObject = new JSONObject();
		JSONArray jarray = new JSONArray();
		for (ReviewVo vo : list) {
			JSONObject jobj = new JSONObject();
			jobj.put("postSeq", vo.getPostSeq());
			jobj.put("postTypeCode", vo.getPostTypeCode());
			jobj.put("boardCode", vo.getBoardCode());
			jobj.put("userId", vo.getUserId());
			jobj.put("postTitle", vo.getPostTitle());
			jobj.put("hitCount", vo.getHitCount());
			jobj.put("logDate", new SimpleDateFormat("yyyy-MM-dd").format(vo.getLogDate()));
			jobj.put("renewalDate", new SimpleDateFormat("yyyy-MM-dd").format(vo.getRenewalDate()));
			jobj.put("reviewSeq", vo.getReviewSeq());
			jobj.put("thema", vo.getThema());
			jobj.put("originName", vo.getOriginName());
			jobj.put("saveName", vo.getSaveName());
			jobj.put("savePath", vo.getSavePath());
			jobj.put("dibsCount", vo.getDibsCount());
			jobj.put("recommendCount", vo.getRecommendCount());

			jarray.add(jobj);
		}

		jsonObject.put("list", jarray);
		jsonObject.put("totalCount", totalCount);

		return jsonObject.toString();

	}

}
