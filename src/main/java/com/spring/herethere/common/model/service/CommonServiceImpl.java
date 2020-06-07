package com.spring.herethere.common.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.herethere.common.model.dao.CommonDao;
import com.spring.herethere.vo.CommentVo;

//@Transactional을 이용해 해당 클래스의 모든 메서드에 트랜잭션을 적용 
@Transactional(propagation = Propagation.REQUIRED)
@Service("commonService")
public class CommonServiceImpl implements CommonService {
	@Autowired
	private CommonDao CommonDao;

	@Override
	public int updateHitCount(String postSeq) {
		return CommonDao.updateHitCount(postSeq);
	}

	@Override
	public int selectMyDibs(Map<String, Object> map) {
		return CommonDao.selectMyDibs(map);
	}

	@Override
	public int selectDibsCount(Map<String, Object> map) {
		return CommonDao.selectDibsCount(map);
	}

	@Override
	public int insertMyDibs(Map<String, Object> map) {
		return CommonDao.insertMyDibs(map);
	}

	@Override
	public int deleteMyDibs(Map<String, Object> map) {
		return CommonDao.deleteMyDibs(map);
	}

	@Override
	public int selectMyRecommend(Map<String, Object> map) {
		return CommonDao.selectMyRecommend(map);
	}

	@Override
	public int selectRecommendCount(Map<String, Object> map) {
		return CommonDao.selectRecommendCount(map);
	}

	@Override
	public int insertMyRecommend(Map<String, Object> map) {
		return CommonDao.insertMyRecommend(map);
	}

	@Override
	public int deleteMyRecommend(Map<String, Object> map) {
		return CommonDao.deleteMyRecommend(map);
	}

	@Override
	public List<CommentVo> selectCommentList(Map<String, Object> map) {
		return CommonDao.selectCommentList(map);
	}

	@Override
	public int selectCommentCount(Map<String, Object> map) {
		return CommonDao.selectCommentCount(map);
	}

	@Override
	public int insertComment(Map<String, Object> map) {
		return CommonDao.insertComment(map);
	}

	@Override
	public int updateComment(Map<String, Object> map) {
		return CommonDao.updateComment(map);
	}

	@Override
	public int deleteComment(Map<String, Object> map) {
		return CommonDao.deleteComment(map);
	}

	@Override
	public String getMaxPostSeq() {
		return CommonDao.getMaxPostSeq();
	}

}
