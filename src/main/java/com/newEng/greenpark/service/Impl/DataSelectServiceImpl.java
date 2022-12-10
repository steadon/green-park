package com.newEng.greenpark.service.Impl;

import com.newEng.greenpark.POJO.CommonResult;
import com.newEng.greenpark.POJO.domain.NumberDomain;
import com.newEng.greenpark.POJO.dto.param.DoubleParam;
import com.newEng.greenpark.POJO.dto.param.UpdateParam;
import com.newEng.greenpark.POJO.dto.result.DoubleChartResult;
import com.newEng.greenpark.POJO.dto.result.TwoDoubleChartResult;
import com.newEng.greenpark.mapper.BooleanDomainMapper;
import com.newEng.greenpark.mapper.NumberDomainMapper;
import com.newEng.greenpark.mapper.SwitchMapper;
import com.newEng.greenpark.service.DataSelectService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.newEng.greenpark.utils.SwitchUtil.switchForName;

@Service
@Slf4j
public class DataSelectServiceImpl implements DataSelectService {

    @Resource
    private NumberDomainMapper numberDomainMapper;
    @Resource
    private BooleanDomainMapper booleanDomainMapper;
    @Resource
    private SwitchMapper switchMapper;

    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
    DecimalFormat dfd = new DecimalFormat("0.00");

    private static final int timeX = 12 * 1000;

    @Override
    public synchronized CommonResult<TwoDoubleChartResult> getTwoData() {
        long time = System.currentTimeMillis();
        DoubleChartResult resultA = getDataOne("GENERATOR_POWER", time - timeX);
        DoubleChartResult resultB = getDataOne("LOAD_POWER", time - timeX);
        TwoDoubleChartResult result = new TwoDoubleChartResult(resultA, resultB);
        log.info("执行完成");
        return CommonResult.success(result);
    }

    @Override
    public CommonResult<DoubleParam> getNewOne(String name) {
        //匹配常量名
        String simpleName = switchForName(name);
        if (simpleName == null) return CommonResult.fail("无相关数据");
        NumberDomain numberDomain = numberDomainMapper.selectOne(simpleName);
        //格式化数据
        DoubleParam param = new DoubleParam(numberDomain);
        //格式化数据
        if (numberDomain.getValue() != null) param.setValue(Double.valueOf(dfd.format(numberDomain.getValue())));
        if (numberDomain.getValue() == null) param.setValue(0.00);
        return CommonResult.success(param);
    }

    @Override
    public synchronized CommonResult<String> getStatus(String name) {
        //匹配常量名
        String simpleName = switchForName(name);
        if (simpleName == null) return CommonResult.fail("无相关数据");

        Integer status = switchMapper.selectForStatus(simpleName);
        if (status == 1) return CommonResult.success("ON");
        if (status == 0) return CommonResult.success("OFF");

        return CommonResult.fail("返回数据异常");
    }

    @Override
    public synchronized CommonResult<String> updateStatus(UpdateParam param) {
        //匹配常量名
        String simpleName = switchForName(param.getName());
        if (simpleName == null) return CommonResult.fail("无相关数据");

        Integer count = switchMapper.selectForCount(simpleName);
        if (count == null) return CommonResult.fail("无相关记录");

        if (switchMapper.updateById(simpleName, param.getStatus(), ++count) == 0) return CommonResult.fail("更新失败");
        return CommonResult.success("更新成功");
    }

    @SneakyThrows
    public DoubleChartResult getDataOne(String name, long time) {
        //匹配常量名
        String simpleName = switchForName(name);
        if (simpleName == null) return null;

        //查找数据
        List<NumberDomain> numberDomains = numberDomainMapper.selectByHistoryId(simpleName, time);
        List<NumberDomain> collect = numberDomains.stream().sorted(Comparator.comparing(NumberDomain::getId)).collect(Collectors.toList());
        List<DoubleParam> paramList = new ArrayList<>();
        for (NumberDomain numberDomain : collect) {
            DoubleParam param = new DoubleParam(numberDomain);

            //格式化时间戳
            param.setTimeStamp(dateFormat.format(Long.parseLong(param.getTimeStamp())));
            if (param.getValue() == null) param.setValue(0.0000);
            param.setValue(Double.valueOf(String.format("%.4f", param.getValue())));

            //简化historyId
            param.setHistoryId(simpleName);
            paramList.add(param);
        }
        //单独抽取每个属性集合
        List<Integer> idList = paramList.stream().map(DoubleParam::getId).collect(Collectors.toList());
        List<String> historyIdList = paramList.stream().map(DoubleParam::getHistoryId).collect(Collectors.toList());
        List<String> timeStampList = paramList.stream().map(DoubleParam::getTimeStamp).collect(Collectors.toList());
        List<Double> valueList = paramList.stream().map(DoubleParam::getValue).collect(Collectors.toList());
        return new DoubleChartResult(idList, historyIdList, timeStampList, valueList);
    }

}
