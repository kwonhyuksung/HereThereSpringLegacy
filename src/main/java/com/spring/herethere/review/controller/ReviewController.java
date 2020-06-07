package com.spring.herethere.review.controller;

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
import com.spring.herethere.review.model.service.ReviewService;
import com.spring.herethere.vo.MemberVo;
import com.spring.herethere.vo.ReviewVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller("reviewController")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/review/mvList.do", method = RequestMethod.GET)
	public String mvList() {
		return "review/list";
	}

	@RequestMapping(value = "/review/list.do", method = RequestMethod.POST)
	public @ResponseBody String list(@RequestBody Map<String, Object> map) {
		List<ReviewVo> list = reviewService.selectReviewList(map);
		int totalCount = reviewService.getReviewListTotalCount(map);

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

	@RequestMapping(value = "/review/mvWrite.do", method = RequestMethod.GET)
	public String mvWrite() {
		return "review/write";
	}

	// summernote에 포함된 이미지를 서버에 업로드하고 이미지의 경로를 리턴
	@RequestMapping(value = "/review/uploadImageSummernote.do", method = RequestMethod.POST)
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

	@RequestMapping(value = "/review/write.do", method = RequestMethod.POST)
	public ModelAndView write(@RequestParam Map<String, Object> map, HttpSession session, @RequestParam("uploadFile") MultipartFile multipartFile) {
		ModelAndView mav = new ModelAndView();
		String viewName = "redirect:/review/mvList.do";
		MemberVo memberVo = (MemberVo) session.getAttribute("userInfo");

		map.put("userId", memberVo.getUserId());
		map.put("postSeq", reviewService.getMaxPostSeq());

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

		LogCheck.logger.info(LogCheck.logMsg + map.toString());

		int cnt = reviewService.insertReview(map);

		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/review/write.do", method = RequestMethod.GET)
	public ModelAndView write(@RequestParam Map<String, Object> map, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String viewName = "redirect:/review/mvList.do";
		MemberVo memberVo = (MemberVo) session.getAttribute("userInfo");

		map.put("userId", memberVo.getUserId());
		map.put("postSeq", reviewService.getMaxPostSeq());
		map.put("originName", "noimg.gif");
		map.put("saveName", "noimg.gif");
		map.put("savePath", "images");

		LogCheck.logger.info(LogCheck.logMsg + map.toString());

		int cnt = reviewService.insertReview(map);

		mav.setViewName(viewName);
		return mav;

	}

	@RequestMapping(value = "/review/view.do", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam("postSeq") String postSeq) {
		ModelAndView mav = new ModelAndView();
		String viewName = "review/view";
		// 조회수 증가가 클릭때 마다 되는 것을 막기 위해 쿠키를 사용하기도 하지만 일단 생략
		int cnt = commonService.updateHitCount(postSeq);

		ReviewVo reviewVo = reviewService.selectReview(postSeq);

		mav.addObject("reviewVo", reviewVo);

		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/review/mvRewrite.do", method = RequestMethod.GET)
	public ModelAndView mvRewrite(@RequestParam("postSeq") String postSeq) {
		ModelAndView mav = new ModelAndView();
		String viewName = "review/rewrite";

		ReviewVo reviewVo = reviewService.selectReview(postSeq);

		mav.addObject("reviewVo", reviewVo);

		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/review/rewrite.do", method = RequestMethod.POST)
	public ModelAndView rewrite(@RequestParam Map<String, Object> map, HttpSession session, @RequestParam("uploadFile") MultipartFile multipartFile) {
		ModelAndView mav = new ModelAndView();
		String viewName = "redirect:/review/mvList.do";
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

		LogCheck.logger.info(LogCheck.logMsg + map.toString());

		int cnt = reviewService.updateReview(map);

		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/review/rewrite.do", method = RequestMethod.GET)
	public ModelAndView rewrite(@RequestParam Map<String, Object> map, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String viewName = "redirect:/review/mvList.do";
		MemberVo memberVo = (MemberVo) session.getAttribute("userInfo");

		map.put("userId", memberVo.getUserId());

		int cnt = reviewService.updateReview(map);

		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/review/delete.do", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		String viewName = "redirect:/review/mvList.do";

		int cnt = reviewService.deleteReview(map);

		mav.setViewName(viewName);
		return mav;
	}

}
