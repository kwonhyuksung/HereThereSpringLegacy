package com.spring.herethere.member.model.service;

import java.util.List;
import java.util.Map;

import com.spring.herethere.vo.MemberVo;
import com.spring.herethere.vo.ReviewVo;
import com.spring.herethere.vo.ScheduleVo;

public interface MemberService {
	int checkId(String id);

	int register(MemberVo memberVo);

	MemberVo login(Map<String, Object> map);

	int updateMember(Map<String, Object> map);

	int deleteMember(Map<String, Object> map);

	List<ScheduleVo> selectMyScheduleList(Map<String, Object> map);

	int getScheduleListTotalCount(Map<String, Object> map);

	List<ReviewVo> selectMyReviewList(Map<String, Object> map);

	int getReviewListTotalCount(Map<String, Object> map);
}
