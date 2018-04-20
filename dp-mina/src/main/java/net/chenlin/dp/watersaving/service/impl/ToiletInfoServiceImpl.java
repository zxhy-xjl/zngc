package net.chenlin.dp.watersaving.service.impl;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.watersaving.entity.ToiletInfoEntity;
import net.chenlin.dp.watersaving.manager.ToiletInfoManager;
import net.chenlin.dp.watersaving.service.ToiletInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 *
 *
 */
@Component("toiletInfoService")
public class ToiletInfoServiceImpl implements ToiletInfoService {

    @Autowired
    private ToiletInfoManager toiletInfoManager;

    @Override
    public Page<ToiletInfoEntity> listToiletInfo(Map<String, Object> params) {
        Query form = new Query(params);
        Page<ToiletInfoEntity> page = new Page<>(form);
        toiletInfoManager.listToiletInfo(page, form);
        return page;
    }

}
