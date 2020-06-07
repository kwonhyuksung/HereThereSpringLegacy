package com.spring.herethere.schedule.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.herethere.schedule.model.dao.ScheduleDao;
import com.spring.herethere.vo.ScheduleVo;

//@Transactional을 이용해 해당 클래스의 모든 메서드에 트랜잭션을 적용 
@Transactional(propagation = Propagation.REQUIRED)
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDao scheduleDao;

	@Override
	public int insertSchedule(Map<String, Object> map) {
		return scheduleDao.insertSchedule(map);
	}

	@Override
	public ScheduleVo selectSchedule(String postSeq) {
		return scheduleDao.selectSchedule(postSeq);
	}

	@Override
	public int updateSchedule(Map<String, Object> map) {
		return scheduleDao.updateSchedule(map);
	}

	@Override
	public int deleteSchedule(Map<String, Object> map) {
		return scheduleDao.deleteSchedule(map);
	}

	@Override
	public List<ScheduleVo> selectScheduleList(Map<String, Object> map) {
		return scheduleDao.selectScheduleList(map);
	}

	@Override
	public int getScheduleListTotalCount(Map<String, Object> map) {
		return scheduleDao.getScheduleListTotalCount(map);
	}

	@Override
	public String getMaxPostSeq() {
		return scheduleDao.getMaxPostSeq();
	}

}
