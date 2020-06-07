package com.spring.herethere.review.model.dao;

import java.util.List;
import java.util.Map;

import com.spring.herethere.vo.ReviewVo;

public interface ReviewDao {
	public int insertReview(Map<String, Object> map);

	public ReviewVo selectReview(String postSeq);

	public int updateReview(Map<String, Object> map);

	public int deleteReview(Map<String, Object> map);

	public List<ReviewVo> selectReviewList(Map<String, Object> map);

	public int getReviewListTotalCount(Map<String, Object> map);

	// post_seq는 두 테이블에서 동시에 사용되므로 insert시 max를 이용해서 얻어온다
	public String getMaxPostSeq();
}
