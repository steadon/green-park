package com.newEng.greenpark.service.ScheduleService;

import com.newEng.greenpark.mapper.BooleanDomainMapper;
import com.newEng.greenpark.mapper.CarbonDayMapper;
import com.newEng.greenpark.mapper.NumberDomainMapper;
import com.newEng.greenpark.service.FunctionService;
import com.newEng.greenpark.utils.CalendarUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

import static com.newEng.greenpark.common.HistoryId.*;

@Slf4j
@Service
@CacheConfig(cacheNames = "CacheService")
public class ScheduleServiceImpl implements ScheduleService {
    @Resource
    NumberDomainMapper numberDomainMapper;
    @Resource
    BooleanDomainMapper booleanDomainMapper;
    @Resource
    CarbonDayMapper carbonDayMapper;

    private final FunctionService functionService;

    @Autowired
    public ScheduleServiceImpl(FunctionService functionService) {
        this.functionService = functionService;
    }

    @Override
    @Async
    @Scheduled(initialDelay = 10000, fixedDelay = 30 * 60 * 1000)
    public synchronized void deleteUselessHistory() {
        deleteIfNumOver(CO2);
        deleteIfNumOver(PM2P5);
        deleteIfNumOver(TVOC);
        deleteIfNumOver(GENERATOR_VOLTAGE);
        deleteIfNumOver(GENERATOR_CURRENT);
        deleteIfNumOver(GENERATOR_POWER);
        deleteIfNumOver(GENERATOR_ENERGY);
        deleteIfNumOver(LOAD_ENERGY);
        deleteIfNumOver(LOAD_VOLTAGE);
        deleteIfNumOver(LOAD_CURRENT);
        deleteIfNumOver(LOAD_POWER);
    }

    @Override
    @Async
    @Scheduled(initialDelay = 30000, fixedDelay = 30 * 60 * 1000)
    public synchronized void deleteUselessStatus() {
        deleteIfBoolOver(LOAD_ENABLE);
        deleteIfBoolOver(CO2_ENABLE);
        deleteIfBoolOver(TVOC_ENABLE);
        deleteIfBoolOver(GENERATOR_ENABLE);
    }

    @Override
    @Async
    @Scheduled(cron = "0 59 23 * * ?")
    public void insertDayCarbon() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        double carbonEqual = functionService.getCarbonEqual();
        double startTime = CalendarUtil.getStartTime();
        if (carbonDayMapper.insert(carbonEqual, df.format(startTime - 1000)) == 1) log.info("insert day-carbon succeed");
    }

    private void deleteIfNumOver(String name) {
        if (numberDomainMapper.countAllByHistoryId(name) >= 1000) {
            numberDomainMapper.deleteHalf(name);
            log.info("[deleteNum]" + "ID = " + name);
        }
    }

    private void deleteIfBoolOver(String name) {
        if (booleanDomainMapper.countAllByHistoryId(name) >= 50) {
            booleanDomainMapper.deleteHalf(name);
            log.info("[deleteBool]" + "ID = " + name);
        }
    }
}
