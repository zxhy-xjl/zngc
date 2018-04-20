package net.chenlin.dp.watersaving.service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.watersaving.entity.ToiletInfoEntity;

import java.util.Map;

/**
 *
 *
 *
 */
public interface ToiletInfoService {

    Page<ToiletInfoEntity> listToiletInfo(Map<String, Object> params);

}
