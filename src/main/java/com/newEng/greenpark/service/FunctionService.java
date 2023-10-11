package com.newEng.greenpark.service;

import com.newEng.greenpark.pojo.CommonResult;
import com.newEng.greenpark.pojo.domain.CarbonDay;
import com.newEng.greenpark.pojo.model.result.PredictChartResult;
import com.newEng.greenpark.pojo.model.vo.AllChartArgsVo;

import java.util.List;

public interface FunctionService {
    CommonResult<PredictChartResult> predict();

    CommonResult<Double> carbonEqual();

    List<CarbonDay> getWeekCarbonData();

    CommonResult<AllChartArgsVo> getAllChartArgs();

    double getCarbonEqual();
}
