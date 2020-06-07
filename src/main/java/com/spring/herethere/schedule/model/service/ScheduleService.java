package com.spring.herethere.schedule.model.service;

import java.util.List;
import java.util.Map;

import com.spring.herethere.vo.ScheduleVo;

public interface ScheduleService {
	public int insertSchedule(Map<String, Object> map);

	public ScheduleVo selectSchedule(String postSeq);

	public int updateSchedule(Map<String, Object> map);

	public int deleteSchedule(Map<String, Object> map);

	public List<ScheduleVo> selectScheduleList(Map<String, Object> map);

	public int getScheduleListTotalCount(Map<String, Object> map);

	// post_seq는 두 테이블에서 동시에 사용되므로 insert시 max를 이용해서 얻어온다
	public String getMaxPostSeq();

}
