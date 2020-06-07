package com.spring.herethere.schedule.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.herethere.common.LogCheck;
import com.spring.herethere.common.model.service.CommonService;
import com.spring.herethere.schedule.model.service.ScheduleService;
import com.spring.herethere.vo.MemberVo;
import com.spring.herethere.vo.ScheduleVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller("scheduleController")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/schedule/mvList.do", method = RequestMethod.GET)
	public String mvList() {
		return "schedule/list";
	}

	@RequestMapping(value = "/schedule/list.do", method = RequestMethod.POST)
	public @ResponseBody String list(@RequestBody Map<String, Object> map) {
		List<ScheduleVo> list = scheduleService.selectScheduleList(map);
		int totalCount = scheduleService.getScheduleListTotalCount(map);

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

	@RequestMapping(value = "/schedule/mvWrite.do", method = RequestMethod.GET)
	public String mvWrite() {
		return "schedule/write";
	}

	// summernote에 포함된 이미지를 서버에 업로드하고 이미지의 경로를 리턴
	@RequestMapping(value = "/schedule/uploadImageSummernote.do", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject uploadImageSummernote(@RequestParam("file") MultipartFile multipartFile) {
		JSONObject jsonObject = new JSONObject();

		// 첨부된 파일이 있는 경우
		if (multipartFile.getSize() != 0) {

			// 프로젝트의 resource에 대한 서버에서의 실제 경로를 얻는다.
			String realPath = servletContext.getRealPath("/resources/");
			// contextPath를 얻는다
			String rootPath = servletContext.getContextPath();
			// 날짜 폴더를 만든다.
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String saveFolder = "images/" + df.format(new Date());
			// 이미지가 저장될 실제 폴더를 얻는다.
			String realSaveFolder = realPath + saveFolder;

			// 이미지가 저장될 실제 위치에 의거한 File 객체를 얻는다.
			File dir = new File(realSaveFolder);
			// 폴더가 없으면 만든다.
			if (!dir.exists()) {
				dir.mkdir();
			}

			// 파일의 원래 이름
			String originName = multipartFile.getOriginalFilename();
			// 파일의 확장자
			String extension = originName.substring(originName.lastIndexOf("."));
			// 실제 저장할 파일 이름(UUID 클래스: 특수문자를 포함한 문자를 랜덤으로 생성. "-"문자는 ""으로 대체)
			String saveFileName = UUID.randomUUID().toString().replace("-", "") + extension;

			// 이미지를 저장하기 위한 최종 File 객체를 얻는다.
			File file = new File(realSaveFolder, saveFileName);

			try {
				multipartFile.transferTo(file);
				jsonObject.put("url", rootPath + "/resources/" + saveFolder + "/" + saveFileName);
				jsonObject.put("responseCode", "success");
			} catch (IOException e) {
				e.printStackTrace();
				jsonObject.put("responseCode", "error");
			}

		}

		return jsonObject;
	}

	@RequestMapping(value = "/schedule/write.do", method = RequestMethod.POST)
	public ModelAndView write(@RequestParam Map<String, Object> map, HttpSession session, @RequestParam("uploadFile") MultipartFile multipartFile) {
		ModelAndView mav = new ModelAndView();
		String viewName = "redirect:/schedule/mvList.do";
		MemberVo memberVo = (MemberVo) session.getAttribute("userInfo");

		map.put("userId", memberVo.getUserId());
		map.put("postSeq", scheduleService.getMaxPostSeq());

		LogCheck.logger.info(LogCheck.logMsg + map.toString());

		if (multipartFile.getSize() != 0) {
			// if (contentType.toLowerCase().startsWith("application/")) {
			// 프로젝트의 resource에 대한 서버에서의 실제 경로를 얻는다.
			String realPath = servletContext.getRealPath("/resources/");
			// contextPath를 얻는다
			String rootPath = servletContext.getContextPath();
			// 날짜 폴더를 만든다.
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String saveFolder = "images/" + df.format(new Date());
			// 이미지가 저장될 실제 폴더를 얻는다.
			String realSaveFolder = realPath + saveFolder;

			// 이미지가 저장될 실제 위치에 의거한 File 객체를 얻는다.
			File dir = new File(realSaveFolder);
			// 폴더가 없으면 만든다.
			if (!dir.exists()) {
				dir.mkdir();
			}

			// 파일의 원래 이름
			String originName = multipartFile.getOriginalFilename();
			// 파일의 확장자
			String extension = originName.substring(originName.lastIndexOf("."));
			// 실제 저장할 파일 이름(UUID 클래스: 특수문자를 포함한 문자를 랜덤으로 생성. "-"문자는 ""으로 대체)
			String saveFileName = UUID.randomUUID().toString().replace("-", "") + extension;

			// 이미지를 저장하기 위한 최종 File 객체를 얻는다.
			File file = new File(realSaveFolder, saveFileName);

			try {
				multipartFile.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
				originName = "noimg.gif";
				saveFileName = "noimg.gif";
				saveFolder = "images";
			}

			map.put("originName", originName);
			map.put("saveName", saveFileName);
			map.put("savePath", saveFolder);
		} else {
			map.put("originName", "noimg.gif");
			map.put("saveName", "noimg.gif");
			map.put("savePath", "images");
		}

		int cnt = scheduleService.insertSchedule(map);

		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/schedule/write.do", method = RequestMethod.GET)
	public ModelAndView write(@RequestParam Map<String, Object> map, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String viewName = "redirect:/schedule/mvList.do";
		MemberVo memberVo = (MemberVo) session.getAttribute("userInfo");

		map.put("userId", memberVo.getUserId());
		map.put("postSeq", scheduleService.getMaxPostSeq());
		map.put("originName", "noimg.gif");
		map.put("saveName", "noimg.gif");
		map.put("savePath", "images");

		LogCheck.logger.info(LogCheck.logMsg + map.toString());

		int cnt = scheduleService.insertSchedule(map);

		mav.setViewName(viewName);
		return mav;

	}

	@RequestMapping(value = "/schedule/view.do", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam("postSeq") String postSeq) {
		ModelAndView mav = new ModelAndView();
		String viewName = "schedule/view";
		// 조회수 증가가 클릭때 마다 되는 것을 막기 위해 쿠키를 사용하기도 하지만 일단 생략
		int cnt = commonService.updateHitCount(postSeq);

		ScheduleVo scheduleVo = scheduleService.selectSchedule(postSeq);

		mav.addObject("scheduleVo", scheduleVo);

		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/schedule/mvRewrite.do", method = RequestMethod.GET)
	public ModelAndView mvRewrite(@RequestParam("postSeq") String postSeq) {
		ModelAndView mav = new ModelAndView();
		String viewName = "schedule/rewrite";

		ScheduleVo scheduleVo = scheduleService.selectSchedule(postSeq);

		mav.addObject("scheduleVo", scheduleVo);

		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/schedule/rewrite.do", method = RequestMethod.POST)
	public ModelAndView rewrite(@RequestParam Map<String, Object> map, HttpSession session, @RequestParam("uploadFile") MultipartFile multipartFile) {
		ModelAndView mav = new ModelAndView();
		String viewName = "redirect:/schedule/mvList.do";
		MemberVo memberVo = (MemberVo) session.getAttribute("userInfo");

		map.put("userId", memberVo.getUserId());

		if (multipartFile.getSize() != 0) {
			// 프로젝트의 resource에 대한 서버에서의 실제 경로를 얻는다.
			String realPath = servletContext.getRealPath("/resources/");
			// contextPath를 얻는다
			String rootPath = servletContext.getContextPath();
			// 날짜 폴더를 만든다.
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String saveFolder = "images/" + df.format(new Date());
			// 이미지가 저장될 실제 폴더를 얻는다.
			String realSaveFolder = realPath + saveFolder;

			// 이미지가 저장될 실제 위치에 의거한 File 객체를 얻는다.
			File dir = new File(realSaveFolder);
			// 폴더가 없으면 만든다.
			if (!dir.exists()) {
				dir.mkdir();
			}

			// 파일의 원래 이름
			String originName = multipartFile.getOriginalFilename();
			// 파일의 확장자
			String extension = originName.substring(originName.lastIndexOf("."));
			// 실제 저장할 파일 이름(UUID 클래스: 특수문자를 포함한 문자를 랜덤으로 생성. "-"문자는 ""으로 대체)
			String saveFileName = UUID.randomUUID().toString().replace("-", "") + extension;

			// 이미지를 저장하기 위한 최종 File 객체를 얻는다.
			File file = new File(realSaveFolder, saveFileName);

			try {
				multipartFile.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
				originName = "noimg.gif";
				saveFileName = "noimg.gif";
				saveFolder = "images";
			}

			map.put("originName", originName);
			map.put("saveName", saveFileName);
			map.put("savePath", saveFolder);
		} else {
			map.put("originName", "noimg.gif");
			map.put("saveName", "noimg.gif");
			map.put("savePath", "images");
		}

		int cnt = scheduleService.updateSchedule(map);

		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/schedule/rewrite.do", method = RequestMethod.GET)
	public ModelAndView rewrite(@RequestParam Map<String, Object> map, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String viewName = "redirect:/schedule/mvList.do";
		MemberVo memberVo = (MemberVo) session.getAttribute("userInfo");

		map.put("userId", memberVo.getUserId());

		int cnt = scheduleService.updateSchedule(map);

		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/schedule/delete.do", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		String viewName = "redirect:/schedule/mvList.do";

		int cnt = scheduleService.deleteSchedule(map);

		mav.setViewName(viewName);
		return mav;
	}

}
