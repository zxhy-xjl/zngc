package net.chenlin.dp.watersaving.controller;

import com.pcm.util.ContextHolder;
import net.chenlin.dp.common.constant.MsgConstant;
import net.chenlin.dp.common.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.watersaving.entity.ToiletInfoEntity;
import net.chenlin.dp.watersaving.service.ToiletInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcm.mina.service.session.DefaultSessionManager;
import com.pcm.mina.service.session.PcmSession;

import java.util.Map;

@RestController
@RequestMapping("/api/toilet")
public class ToiletInfoController extends AbstractController {

    @Autowired
    private ToiletInfoService toiletInfoService;

    @RequestMapping("/list")
    public Page<ToiletInfoEntity> list(@RequestBody Map<String, Object> params) {

        return toiletInfoService.listToiletInfo(params);
    }

    @RequestMapping("/flushOpenCmd")
    public R flushOpenCmd(@RequestBody Map<String, Object> params) {
        String account = params.get("deviceCode").toString();
        DefaultSessionManager sessionManager=(DefaultSessionManager) ContextHolder.getBean("PcmSessionManager");
        PcmSession session=sessionManager.getSession(account);
        if(session !=null){
            String[] rb = new String[2];
			rb[0] = "open";
			rb[1] = "0";
			session.write(StringUtils.join(rb,":")); //转发获取的消息
            return R.ok(MsgConstant.MSG_OPERATION_SUCCESS);
        } else {
//            return R.error(MsgConstant.MSG_OPERATION_FAILED);
            return R.ok(MsgConstant.MSG_OPERATION_SUCCESS);
        }

    }

    @RequestMapping("/flushCloseCmd")
    public R flushCloseCmd(@RequestBody Map<String, Object> params) {
        String account = params.get("deviceCode").toString();
        DefaultSessionManager sessionManager=(DefaultSessionManager) ContextHolder.getBean("PcmSessionManager");
        PcmSession session=sessionManager.getSession(account);
        if(session !=null){
            String[] rb = new String[2];
            rb[0] = "close";
            rb[1] = "0";
            session.write(StringUtils.join(rb,":")); //转发获取的消息
            return R.ok(MsgConstant.MSG_OPERATION_SUCCESS);
        } else {
            return R.error(MsgConstant.MSG_OPERATION_FAILED);
        }
    }
}
