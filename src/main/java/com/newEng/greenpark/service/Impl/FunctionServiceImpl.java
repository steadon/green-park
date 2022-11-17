package com.newEng.greenpark.service.Impl;

import com.newEng.greenpark.POJO.CommonResult;
import com.newEng.greenpark.POJO.domain.CarbonDay;
import com.newEng.greenpark.POJO.dto.result.PredictChartResult;
import com.newEng.greenpark.POJO.dto.vo.AllChartArgsVo;
import com.newEng.greenpark.POJO.dto.vo.PredictVo;
import com.newEng.greenpark.POJO.dto.vo.SecondSmoothingEntity;
import com.newEng.greenpark.mapper.CarbonDayMapper;
import com.newEng.greenpark.mapper.NumberDomainMapper;
import com.newEng.greenpark.service.FunctionService;
import com.newEng.greenpark.utils.CalculateUtil;
import com.newEng.greenpark.utils.CalendarUtil;
import com.newEng.greenpark.utils.SwitchUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FunctionServiceImpl implements FunctionService {
    @Resource
    NumberDomainMapper numberDomainMapper;
    @Resource
    CarbonDayMapper carbonDayMapper;

    private Double lastSinglePredictParam = 0.0;

    private Double lastSecondPredictParam = 0.0;

    @Override
    public CommonResult<PredictChartResult> predict() {
        //预测对象
        SecondSmoothingEntity vo = new SecondSmoothingEntity();

        List<CarbonDay> carbonDaysAsc = getWeekCarbonData();
        if (carbonDaysAsc == null) return CommonResult.fail("无样本数据");
        //抽取value集合
        List<Double> collect = carbonDaysAsc.stream().map(CarbonDay::getValue).collect(Collectors.toList());
        List<String> timeCollect = carbonDaysAsc.stream().map(CarbonDay::getDayTime).collect(Collectors.toList());
        //lastPV赋值
        if (lastSinglePredictParam == 0.0) lastSinglePredictParam = collect.get(0) - 0.1;
        if (lastSecondPredictParam == 0.0) lastSecondPredictParam = collect.get(0) - 0.1;
        vo.setRealDataList(collect);
        vo.setLastSinglePredictParam(lastSinglePredictParam);
        vo.setLastSecondPredictParam(lastSecondPredictParam);
        vo.setPredictTime(1);
        //抽取属性集合
        List<PredictVo> voList = CalculateUtil.secondExponentialSmoothingMethod(vo);
        List<Double> lastSecondPv = voList.stream().map(PredictVo::getLastSecondPv).collect(Collectors.toList());
        List<Double> realValue = voList.stream().map(PredictVo::getRealValue).collect(Collectors.toList());
        List<Double> secondPv = voList.stream().map(PredictVo::getSecondPv).collect(Collectors.toList());
        List<Double> secondError = voList.stream().map(PredictVo::getSecondError).collect(Collectors.toList());
        PredictChartResult result = new PredictChartResult(lastSecondPv, realValue, secondPv, secondError,timeCollect);
        return CommonResult.success(result);
    }

    @Override
    public CommonResult<Double> carbonEqual() {
        return CommonResult.success(getCarbonEqual());
    }

    @Override
    public List<CarbonDay> getWeekCarbonData() {
        List<CarbonDay> carbonDays = carbonDayMapper.selectNearSeven();
        if (carbonDays.isEmpty()) return null;
        return carbonDays.stream().sorted(Comparator.comparing(CarbonDay::getId)).collect(Collectors.toList());
    }

    public double getCarbonEqual() {
        String loadEnergy = SwitchUtil.switchForName("LOAD_ENERGY");
        String generatorEnergy = SwitchUtil.switchForName("GENERATOR_ENERGY");
        //获取当天0点时间戳
        long startTime = CalendarUtil.getStartTime();
        //保留两位小数
        DecimalFormat df = new DecimalFormat("0.00");
        //查询极差
        Double differenceA = numberDomainMapper.selectOneByHistoryId(loadEnergy, startTime);
        Double differenceB = numberDomainMapper.selectOneByHistoryId(generatorEnergy, startTime);
        if (differenceA == null || differenceB == null) return -1.0;
        double carbon = (differenceA + differenceB) * 0.785;
        return Double.parseDouble(df.format(carbon));
    }

    public CommonResult<AllChartArgsVo> getAllChartArgs() {
        String loadEnergy = SwitchUtil.switchForName("LOAD_ENERGY");
        String loadPower = SwitchUtil.switchForName("LOAD_POWER");
        String generatorEnergy = SwitchUtil.switchForName("GENERATOR_ENERGY");
        String generatorPower = SwitchUtil.switchForName("GENERATOR_POWER");
        //获取当天0点时间戳
        long startTime = CalendarUtil.getStartTime();
        //保留两位小数
        DecimalFormat df = new DecimalFormat("0.0000");
        Double generatorPowerValue = Double.valueOf(df.format(numberDomainMapper.selectOne(generatorPower).getValue()));
        Double loadPowerValue = Double.valueOf(df.format(numberDomainMapper.selectOne(loadPower).getValue()));
        Double loadEnergyValue = Double.valueOf(df.format(numberDomainMapper.selectOneByHistoryId(loadEnergy, startTime)));
        Double generatorEnergyValue = Double.valueOf(df.format(numberDomainMapper.selectOneByHistoryId(generatorEnergy, startTime)));
        List<Double> list = new ArrayList<>();
        list.add(generatorPowerValue);
        list.add(loadPowerValue);
        list.add(generatorEnergyValue);
        list.add(loadEnergyValue);
        AllChartArgsVo vo = new AllChartArgsVo(list);
        return CommonResult.success(vo);
    }
}
