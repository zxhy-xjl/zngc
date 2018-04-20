package net.chenlin.dp.watersaving.manager;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.watersaving.entity.ToiletInfoEntity;

import java.util.List;

/**
 *
 *
 *
 */
public interface ToiletInfoManager {

    List<ToiletInfoEntity> listToiletInfo(Page<ToiletInfoEntity> page, Query search);
}
