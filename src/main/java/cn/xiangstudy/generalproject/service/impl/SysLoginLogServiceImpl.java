package cn.xiangstudy.generalproject.service.impl;

import cn.xiangstudy.generalproject.mapper.SysLoginLogMapper;
import cn.xiangstudy.generalproject.pojo.entity.SysLoginLog;
import cn.xiangstudy.generalproject.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangxiang
 * @date 2025-08-27 11:49
 */
@Service
public class SysLoginLogServiceImpl implements SysLoginLogService {

    private final SysLoginLogMapper mapper;

    @Autowired
    public SysLoginLogServiceImpl(SysLoginLogMapper sysLoginLogMapper) {
        this.mapper = sysLoginLogMapper;
    }

    @Override
    public Long createUserLoginLog(SysLoginLog sysLoginLog) {
        mapper.createUserLoginLog(sysLoginLog);

        return sysLoginLog.getLoginLogId();
    }
}
