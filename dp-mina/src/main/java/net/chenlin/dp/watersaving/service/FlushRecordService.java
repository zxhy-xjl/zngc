package net.chenlin.dp.watersaving.service;

import net.chenlin.dp.watersaving.entity.FlushRecordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 */
public interface FlushRecordService {

    List<Map<String, Object>> selectStatDayList();

    List<Map<String, Object>> selectStatMonthList();

    List<Map<String, Object>> selectStatTimesDayList();

    List<FlushRecordEntity> selectCurDateFlush();

    void saveOpendFlish(String account, String[] message);

    void saveClosedFlish(String account, String[] message);
}
