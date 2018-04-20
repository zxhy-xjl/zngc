package net.chenlin.dp.watersaving.service.impl;

import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.watersaving.dao.FlushRecordMapper;
import net.chenlin.dp.watersaving.dao.ToiletInfoMapper;
import net.chenlin.dp.watersaving.entity.FlushRecordEntity;
import net.chenlin.dp.watersaving.entity.ToiletInfoEntity;
import net.chenlin.dp.watersaving.service.FlushRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 *
 */
@Component("flushRecordService")
public class FlushRecordServiceImpl implements FlushRecordService {

    @Autowired
    private FlushRecordMapper flushRecordMapper;

    @Autowired
    private ToiletInfoMapper toiletInfoMapper;

    @Override
    public List<Map<String, Object>> selectStatDayList() {
        return flushRecordMapper.selectStatDayList();
    }

    @Override
    public List<Map<String, Object>> selectStatMonthList() {
        return flushRecordMapper.selectStatMonthList();
    }

    @Override
    public List<Map<String, Object>> selectStatTimesDayList() {
        return flushRecordMapper.selectStatTimesDayList();
    }

    @Override
    public List<FlushRecordEntity> selectCurDateFlush() {
        return flushRecordMapper.selectCurDateFlush();
    }

    public void saveOpendFlish(String account, String[] message){
        ToiletInfoEntity qToilet = new ToiletInfoEntity();
        qToilet.setDeviceCode(account);
        ToiletInfoEntity toiletInfoEntity = toiletInfoMapper.getObject(qToilet);
        if (toiletInfoEntity!=null)
        {
            FlushRecordEntity flushRecordEntity = new FlushRecordEntity();
            flushRecordEntity.setToiletId(toiletInfoEntity.getId());
            String[] receiveMsg = message[1].split(",");
            String dataStr = "20"+receiveMsg[1];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime ldt = LocalDateTime.parse(dataStr,dtf);
            Timestamp time = Timestamp.valueOf(LocalDateTime.now());
            flushRecordEntity.setStartTime(time);
            flushRecordMapper.save(flushRecordEntity);
        }
    }

    public void saveClosedFlish(String account, String[] message){
        ToiletInfoEntity qToilet = new ToiletInfoEntity();
        qToilet.setDeviceCode(account);
        ToiletInfoEntity toiletInfoEntity = toiletInfoMapper.getObject(qToilet);
        if (toiletInfoEntity!=null)
        {
            //查询id
            List<FlushRecordEntity> flushRecordEntities = flushRecordMapper.selectFlushRecordEntityByStart(toiletInfoEntity.getId());
            if (!flushRecordEntities.isEmpty())
            {
                FlushRecordEntity flushRecordEntity = flushRecordEntities.get(0);
                String[] receiveMsg = message[1].split(",");
                String dataStr = "20"+receiveMsg[1];
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                LocalDateTime ldt = LocalDateTime.parse(dataStr,dtf);
                Timestamp time = Timestamp.valueOf(LocalDateTime.now());
                flushRecordEntity.setEndTime(time);

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String endStr = df.format(flushRecordEntity.getEndTime());
                String startStr = df.format(flushRecordEntity.getStartTime());

                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
                Date date1 = null;
                try {
                    date1 = sdf.parse(endStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date date2 = null;
                try {
                    date2 = sdf.parse(startStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long l = date1.getTime() - date2.getTime();
                flushRecordEntity.setFlushValue(BigDecimal.valueOf(l*20));

                flushRecordMapper.update(flushRecordEntity);
            }
        }
    }

}
