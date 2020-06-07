package com.spring.herethere.review.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.herethere.review.model.dao.ReviewDao;
import com.spring.herethere.vo.ReviewVo;

//@Transactional을 이용해 해당 클래스의 모든 메서드에 트랜잭션을 적용 
@Transactional(propagation = Propagation.REQUIRED)
@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewDao reviewDao;

	@Override
	public int insertReview(Map<String, Object> map) {
		return reviewDao.insertReview(map);
	}

	@Override
	public ReviewVo selectReview(String postSeq) {
		return reviewDao.selectReview(postSeq);
	}

	@Override
	public int updateReview(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return reviewDao.updateReview(map);
	}

	@Override
	public int deleteReview(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return reviewDao.deleteReview(map);
	}

	@Override
	public List<ReviewVo> selectReviewList(Map<String, Object> map) {
		return reviewDao.selectReviewList(map);
	}

	@Override
	public int getReviewListTotalCount(Map<String, Object> map) {
		return reviewDao.getReviewListTotalCount(map);
	}

	@Override
	public String getMaxPostSeq() {
		return reviewDao.getMaxPostSeq();
	}

}
