package net.chenlin.dp.watersaving.dao;

import net.chenlin.dp.common.dao.BaseMapper;
import net.chenlin.dp.watersaving.entity.FlushRecordEntity;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 *
 */
@MapperScan
public interface FlushRecordMapper extends BaseMapper<FlushRecordEntity> {

    List<Map<String, Object>> selectStatDayList();
    List<Map<String, Object>> selectStatMonthList();
    List<Map<String, Object>> selectStatTimesDayList();
    List<FlushRecordEntity> selectCurDateFlush();

    List<FlushRecordEntity> selectFlushRecordEntityByStart(@Param("toiletId") Long toiletId);
}
