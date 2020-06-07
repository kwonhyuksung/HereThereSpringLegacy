package com.spring.herethere.common.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.herethere.common.model.service.CommonService;
import com.spring.herethere.vo.CommentVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller("commonController")
public class CommonController {
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/common/selectDibs.do", method = RequestMethod.POST)
	public @ResponseBody String selectDibs(@RequestBody Map<String, Object> map) {
		int myDibs = commonService.selectMyDibs(map);
		int dibsCount = commonService.selectDibsCount(map);

		JSONObject jlist = new JSONObject();

		jlist.put("myDibs", myDibs);
		jlist.put("dibsCount", dibsCount);

		return jlist.toString();
	}

	@RequestMapping(value = "/common/insertDibs.do", method = RequestMethod.POST)
	public @ResponseBody String insertDibs(@RequestBody Map<String, Object> map) {
		int myDibs = commonService.insertMyDibs(map);
		int dibsCount = commonService.selectDibsCount(map);

		JSONObject jlist = new JSONObject();

		jlist.put("myDibs", myDibs);
		jlist.put("dibsCount", dibsCount);

		return jlist.toString();
	}

	@RequestMapping(value = "/common/deleteDibs.do", method = RequestMethod.POST)
	public @ResponseBody String deleteDibs(@RequestBody Map<String, Object> map) {
		int myDibs = commonService.deleteMyDibs(map);
		myDibs = 0;
		int dibsCount = commonService.selectDibsCount(map);

		JSONObject jlist = new JSONObject();

		jlist.put("myDibs", myDibs);
		jlist.put("dibsCount", dibsCount);

		return jlist.toString();
	}

	@RequestMapping(value = "/common/selectRecommend.do", method = RequestMethod.POST)
	public @ResponseBody String selectRecommend(@RequestBody Map<String, Object> map) {
		int myRecommend = commonService.selectMyRecommend(map);
		int recommendCount = commonService.selectRecommendCount(map);

		JSONObject jlist = new JSONObject();

		jlist.put("myRecommend", myRecommend);
		jlist.put("recommendCount", recommendCount);

		return jlist.toString();
	}

	@RequestMapping(value = "/common/insertRecommend.do", method = RequestMethod.POST)
	public @ResponseBody String insertRecommend(@RequestBody Map<String, Object> map) {
		int myRecommend = commonService.insertMyRecommend(map);
		int recommendCount = commonService.selectRecommendCount(map);

		JSONObject jlist = new JSONObject();

		jlist.put("myRecommend", myRecommend);
		jlist.put("recommendCount", recommendCount);

		return jlist.toString();
	}

	@RequestMapping(value = "/common/deleteRecommend.do", method = RequestMethod.POST)
	public @ResponseBody String deleteRecommend(@RequestBody Map<String, Object> map) {
		int myRecommend = commonService.deleteMyRecommend(map);
		myRecommend = 0;
		int recommendCount = commonService.selectRecommendCount(map);

		JSONObject jlist = new JSONObject();

		jlist.put("myRecommend", myRecommend);
		jlist.put("recommendCount", recommendCount);

		return jlist.toString();
	}

	@RequestMapping(value = "/common/selectCommentList.do", method = RequestMethod.POST)
	public @ResponseBody String selectCommentList(@RequestBody Map<String, Object> map) {
		List<CommentVo> list = commonService.selectCommentList(map);
		int totalCount = commonService.selectCommentCount(map);

		JSONObject jlist = new JSONObject();
		JSONArray jarray = new JSONArray();
		for (CommentVo vo : list) {
			JSONObject jobj = new JSONObject();
			jobj.put("postSeq", vo.getPostSeq());
			jobj.put("postTypeCode", vo.getPostTypeCode());
			jobj.put("boardCode", vo.getBoardCode());
			jobj.put("userId", vo.getUserId());
			jobj.put("postTitle", vo.getPostTitle());
			jobj.put("postContent", vo.getPostContent());
			jobj.put("hitCount", vo.getHitCount());
			jobj.put("logDate", new SimpleDateFormat("yyyy-MM-dd").format(vo.getLogDate()));
			jobj.put("renewalDate", new SimpleDateFormat("yyyy-MM-dd").format(vo.getRenewalDate()));
			jobj.put("commentSeq", vo.getCommentSeq());
			jobj.put("groupNo", vo.getGroupNo());
			jobj.put("groupOrder", vo.getGroupOrder());
			jobj.put("depth", vo.getDepth());
			jobj.put("parentSeq", vo.getParentSeq());
			jobj.put("replyCount", vo.getReplyCount());

			jarray.add(jobj);
		}

		jlist.put("list", jarray);
		jlist.put("totalCount", totalCount);

		return jlist.toString();
	}

	@RequestMapping(value = "/common/insertComment.do", method = RequestMethod.POST)
	public @ResponseBody String insertComment(@RequestBody Map<String, Object> map) {
		map.put("postSeq", commonService.getMaxPostSeq());

		int cnt = commonService.insertComment(map);

		JSONObject jlist = new JSONObject();

		jlist.put("resultCount", cnt);

		return jlist.toString();
	}

	@RequestMapping(value = "/common/updateComment.do", method = RequestMethod.POST)
	public @ResponseBody String updateComment(@RequestBody Map<String, Object> map) {
		int cnt = commonService.updateComment(map);

		JSONObject jlist = new JSONObject();

		jlist.put("resultCount", cnt);

		return jlist.toString();
	}

	@RequestMapping(value = "/common/deleteComment.do", method = RequestMethod.POST)
	public @ResponseBody String deleteComment(@RequestBody Map<String, Object> map) {
		int cnt = commonService.deleteComment(map);

		JSONObject jlist = new JSONObject();

		jlist.put("resultCount", cnt);

		return jlist.toString();
	}

}
