package cn.xiangstudy.generalproject.service.impl;

import cn.xiangstudy.generalproject.mapper.SysLogMapper;
import cn.xiangstudy.generalproject.pojo.entity.SysLog;
import cn.xiangstudy.generalproject.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangxiang
 * @date 2025-08-26 15:00
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    private final SysLogMapper sysLogMapper;

    @Autowired
    public SysLogServiceImpl(SysLogMapper sysLogMapper) {
        this.sysLogMapper = sysLogMapper;
    }

    @Override
    public Long createLog(SysLog sysLog) {

        sysLogMapper.createLog(sysLog);

        return sysLog.getLogId();
    }
}
