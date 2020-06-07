package com.spring.herethere.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.herethere.member.model.dao.MemberDao;
import com.spring.herethere.vo.MemberVo;
import com.spring.herethere.vo.ReviewVo;
import com.spring.herethere.vo.ScheduleVo;

// @Transactional을 이용해 해당 클래스의 모든 메서드에 트랜잭션을 적용 
@Transactional(propagation = Propagation.REQUIRED)
@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	public int checkId(String id) {
		return memberDao.checkId(id);
	}

	@Override
	public int register(MemberVo memberVo) {
		return memberDao.register(memberVo);
	}

	@Override
	public MemberVo login(Map<String, Object> map) {
		return memberDao.login(map);
	}

	@Override
	public int updateMember(Map<String, Object> map) {
		return memberDao.updateMember(map);
	}

	@Override
	public int deleteMember(Map<String, Object> map) {
		return memberDao.deleteMember(map);
	}

	@Override
	public List<ScheduleVo> selectMyScheduleList(Map<String, Object> map) {
		return memberDao.selectMyScheduleList(map);
	}

	@Override
	public int getScheduleListTotalCount(Map<String, Object> map) {
		return memberDao.getScheduleListTotalCount(map);
	}

	@Override
	public List<ReviewVo> selectMyReviewList(Map<String, Object> map) {
		return memberDao.selectMyReviewList(map);
	}

	@Override
	public int getReviewListTotalCount(Map<String, Object> map) {
		return memberDao.getReviewListTotalCount(map);
	}

}
