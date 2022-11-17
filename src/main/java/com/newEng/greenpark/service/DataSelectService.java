package com.newEng.greenpark.service;

import com.newEng.greenpark.POJO.CommonResult;
import com.newEng.greenpark.POJO.dto.param.DoubleParam;
import com.newEng.greenpark.POJO.dto.param.UpdateParam;
import com.newEng.greenpark.POJO.dto.result.DoubleChartResult;
import com.newEng.greenpark.POJO.dto.result.TwoDoubleChartResult;

public interface DataSelectService {
    CommonResult<DoubleChartResult> getData(String name);

    CommonResult<TwoDoubleChartResult> getTwoData();

    CommonResult<DoubleParam> getNewOne(String name);

    CommonResult<String> getStatus(String name);

    CommonResult<String> updateStatus(UpdateParam param);
}
