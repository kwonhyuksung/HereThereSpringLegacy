package com.spring.herethere.common.model.service;

import java.util.List;
import java.util.Map;

import com.spring.herethere.vo.CommentVo;

public interface CommonService {
	int updateHitCount(String postSeq);

	int selectMyDibs(Map<String, Object> map);

	int selectDibsCount(Map<String, Object> map);

	int insertMyDibs(Map<String, Object> map);

	int deleteMyDibs(Map<String, Object> map);

	int selectMyRecommend(Map<String, Object> map);

	int selectRecommendCount(Map<String, Object> map);

	int insertMyRecommend(Map<String, Object> map);

	int deleteMyRecommend(Map<String, Object> map);

	List<CommentVo> selectCommentList(Map<String, Object> map);

	int selectCommentCount(Map<String, Object> map);

	int insertComment(Map<String, Object> map);

	int updateComment(Map<String, Object> map);

	int deleteComment(Map<String, Object> map);

	// post_seq는 두 테이블에서 동시에 사용되므로 insert시 max를 이용해서 얻어온다
	String getMaxPostSeq();
}
