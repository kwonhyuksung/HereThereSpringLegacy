package com.spring.herethere.review.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.herethere.vo.ReviewVo;

@Repository("reviewDao")
public class ReviewDaoImpl implements ReviewDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertReview(Map<String, Object> map) {
		int cnt = 0;
		sqlSession.insert("mapper.review.insertReview1", map);
		cnt = sqlSession.insert("mapper.review.insertReview2", map);
		return cnt;
	}

	@Override
	public ReviewVo selectReview(String postSeq) {
		ReviewVo reviewVo = null;
		reviewVo = sqlSession.selectOne("mapper.review.selectReview", postSeq);
		return reviewVo;
	}

	@Override
	public int updateReview(Map<String, Object> map) {
		int cnt = 0;
		sqlSession.update("mapper.review.updateReview1", map);
		cnt = sqlSession.update("mapper.review.updateReview2", map);
		return cnt;
	}

	@Override
	public int deleteReview(Map<String, Object> map) {
		int cnt = 0;
		sqlSession.delete("mapper.review.deleteReview1", map);
		cnt = sqlSession.delete("mapper.review.deleteReview2", map);
		return cnt;
	}

	@Override
	public List<ReviewVo> selectReviewList(Map<String, Object> map) {
		List<ReviewVo> list = null;
		list = sqlSession.selectList("mapper.review.selectReviewList", map);
		return list;
	}

	@Override
	public int getReviewListTotalCount(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.selectOne("mapper.review.getReviewListTotalCount", map);
		return cnt;
	}

	@Override
	public String getMaxPostSeq() {
		String maxCode = null;
		maxCode = sqlSession.selectOne("mapper.review.getMaxPostSeq");
		return maxCode;
	}

}
