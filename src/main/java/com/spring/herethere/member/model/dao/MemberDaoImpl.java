package com.spring.herethere.member.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.herethere.vo.MemberVo;
import com.spring.herethere.vo.ReviewVo;
import com.spring.herethere.vo.ScheduleVo;

@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int checkId(String id) {
		int cnt = 0;
		cnt = sqlSession.selectOne("mapper.member.checkId", id);
		return cnt;
	}

	@Override
	public int register(MemberVo memberVo) {
		int cnt = 0;
		cnt = sqlSession.insert("mapper.member.register", memberVo);
		return cnt;
	}

	@Override
	public MemberVo login(Map<String, Object> map) {
		MemberVo memberVo = null;
		memberVo = sqlSession.selectOne("mapper.member.login", map);
		return memberVo;
	}

	@Override
	public int updateMember(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.update("mapper.member.updateMember", map);
		return cnt;
	}

	@Override
	public int deleteMember(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.delete("mapper.member.deleteRelationTable", map);
		cnt = sqlSession.delete("mapper.member.deleteMember", map);
		return cnt;
	}

	@Override
	public List<ScheduleVo> selectMyScheduleList(Map<String, Object> map) {
		List<ScheduleVo> list = new ArrayList<ScheduleVo>();
		list = sqlSession.selectList("mapper.member.selectMyScheduleList", map);
		return list;
	}

	@Override
	public int getScheduleListTotalCount(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.selectOne("mapper.member.getScheduleListTotalCount", map);
		return cnt;
	}

	@Override
	public List<ReviewVo> selectMyReviewList(Map<String, Object> map) {
		List<ReviewVo> list = new ArrayList<ReviewVo>();
		list = sqlSession.selectList("mapper.member.selectMyReviewList", map);
		return list;
	}

	@Override
	public int getReviewListTotalCount(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.selectOne("mapper.member.getReviewListTotalCount", map);
		return cnt;
	}

}
