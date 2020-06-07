package com.spring.herethere.schedule.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.herethere.vo.ScheduleVo;

@Repository("scheduleDao")
public class ScheduleDaoImpl implements ScheduleDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertSchedule(Map<String, Object> map) {
		int cnt = 0;
		sqlSession.insert("mapper.schedule.insertSchedule1", map);
		cnt = sqlSession.insert("mapper.schedule.insertSchedule2", map);
		return cnt;
	}

	@Override
	public ScheduleVo selectSchedule(String postSeq) {
		ScheduleVo scheduleVo = null;
		scheduleVo = sqlSession.selectOne("mapper.schedule.selectSchedule", postSeq);
		return scheduleVo;
	}

	@Override
	public int updateSchedule(Map<String, Object> map) {
		int cnt = 0;
		sqlSession.update("mapper.schedule.updateSchedule1", map);
		cnt = sqlSession.update("mapper.schedule.updateSchedule2", map);
		return cnt;
	}

	@Override
	public int deleteSchedule(Map<String, Object> map) {
		int cnt = 0;
		sqlSession.delete("mapper.schedule.deleteSchedule1", map);
		cnt = sqlSession.delete("mapper.schedule.deleteSchedule2", map);
		return cnt;
	}

	@Override
	public List<ScheduleVo> selectScheduleList(Map<String, Object> map) {
		List<ScheduleVo> list = null;
		list = sqlSession.selectList("mapper.schedule.selectScheduleList", map);
		return list;
	}

	@Override
	public int getScheduleListTotalCount(Map<String, Object> map) {
		int cnt = 0;
		cnt = sqlSession.selectOne("mapper.schedule.getScheduleListTotalCount", map);
		return cnt;
	}

	@Override
	public String getMaxPostSeq() {
		String maxCode = null;
		maxCode = sqlSession.selectOne("mapper.schedule.getMaxPostSeq");
		return maxCode;
	}

}
