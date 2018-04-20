package net.chenlin.dp.watersaving.manager.impl;

import net.chenlin.dp.watersaving.dao.ToiletInfoMapper;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.watersaving.entity.ToiletInfoEntity;
import net.chenlin.dp.watersaving.manager.ToiletInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 *
 *
 */
@Component("toiletInfoManager")
public class ToiletInfoManagerImpl implements ToiletInfoManager {

    @Autowired
    private ToiletInfoMapper toiletInfoMapper;

    @Override
    public List<ToiletInfoEntity> listToiletInfo(Page<ToiletInfoEntity> page, Query search) {
        return toiletInfoMapper.listForPage(page, search);
    }
}
