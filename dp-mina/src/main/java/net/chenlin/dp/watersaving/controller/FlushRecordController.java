package net.chenlin.dp.watersaving.controller;

import net.chenlin.dp.common.controller.AbstractController;
import net.chenlin.dp.watersaving.entity.FlushRecordEntity;
import net.chenlin.dp.watersaving.service.FlushRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flush")
public class FlushRecordController extends AbstractController {

    @Autowired
    private FlushRecordService flushRecordService;

    @RequestMapping("/statDay")
    public List<Map<String, Object>> selectStatDayList() {

        return flushRecordService.selectStatDayList();
    }

    @RequestMapping("/statMonth")
    public List<Map<String, Object>> selectStatMonthList() {

        return flushRecordService.selectStatMonthList();
    }

    @RequestMapping("/statTimesDay")
    public List<Map<String, Object>> selectStatTimesDayList() {

        return flushRecordService.selectStatTimesDayList();
    }

    @RequestMapping("/curDateFlush")
    public List<FlushRecordEntity> selectCurDateFlush() {

        return flushRecordService.selectCurDateFlush();
    }
}
