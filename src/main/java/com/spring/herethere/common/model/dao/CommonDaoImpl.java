package com.spring.herethere.common.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.herethere.vo.CommentVo;

@Repository("commonDao")
public class CommonDaoImpl implements CommonDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int updateHitCount(String postSeq) {
		int cnt = 0;
		cnt = sqlSession.update("mapper.common.updateHitCount", postSeq);
		return cnt;
	}

	@Override
	public int selectMyDibs(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.selectOne("mapper.common.selectMyDibs", map);
		return cnt;
	}

	@Override
	public int selectDibsCount(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.selectOne("mapper.common.selectDibsCount", map);
		return cnt;
	}

	@Override
	public int insertMyDibs(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.insert("mapper.common.insertMyDibs", map);
		return cnt;
	}

	@Override
	public int deleteMyDibs(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.delete("mapper.common.deleteMyDibs", map);
		return cnt;
	}

	@Override
	public int selectMyRecommend(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.selectOne("mapper.common.selectMyRecommend", map);
		return cnt;
	}

	@Override
	public int selectRecommendCount(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.selectOne("mapper.common.selectRecommendCount", map);
		return cnt;
	}

	@Override
	public int insertMyRecommend(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.insert("mapper.common.insertMyRecommend", map);
		return cnt;
	}

	@Override
	public int deleteMyRecommend(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.delete("mapper.common.deleteMyRecommend", map);
		return cnt;
	}

	@Override
	public List<CommentVo> selectCommentList(Map<String, Object> map) {
		List<CommentVo> list = new ArrayList<CommentVo>();
		list = sqlSession.selectList("mapper.common.selectCommentList", map);
		return list;
	}

	@Override
	public int selectCommentCount(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.selectOne("mapper.common.selectCommentCount", map);
		return cnt;
	}

	@Override
	public int insertComment(Map<String, Object> map) {
		int cnt = 0;
		sqlSession.update("mapper.common.updateGroupOrderInGroupNo", map);
		sqlSession.insert("mapper.common.insertCommentInPost", map);
		cnt = sqlSession.insert("mapper.common.insertComment", map);
		return cnt;
	}

	@Override
	public int updateComment(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.update("mapper.common.updateComment", map);
		return cnt;
	}

	@Override
	public int deleteComment(Map<String, Object> map) {
		int cnt = 0;
		sqlSession.delete("mapper.common.deleteComment", map);
		cnt = sqlSession.delete("mapper.common.deleteCommentInPost", map);
		return cnt;
	}

	@Override
	public String getMaxPostSeq() {
		String maxCode = null;
		maxCode = sqlSession.selectOne("mapper.common.getMaxPostSeq");
		return maxCode;
	}

}
