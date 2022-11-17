package com.newEng.greenpark.service;

import com.newEng.greenpark.POJO.CommonResult;
import com.newEng.greenpark.POJO.domain.CarbonDay;
import com.newEng.greenpark.POJO.dto.result.PredictChartResult;
import com.newEng.greenpark.POJO.dto.vo.AllChartArgsVo;

import java.util.List;

public interface FunctionService {
    CommonResult<PredictChartResult> predict(String name);

    CommonResult<Double> carbonEqual();

    List<CarbonDay> getWeekCarbonData();

    CommonResult<AllChartArgsVo> getAllChartArgs();

    double getCarbonEqual();
}
