package com.newEng.greenpark.service;

import com.newEng.greenpark.pojo.CommonResult;
import com.newEng.greenpark.pojo.model.param.DoubleParam;
import com.newEng.greenpark.pojo.model.param.UpdateParam;
import com.newEng.greenpark.pojo.model.result.DoubleChartResult;
import com.newEng.greenpark.pojo.model.result.TwoDoubleChartResult;

public interface DataSelectService {
    CommonResult<DoubleChartResult> getData(String name);

    CommonResult<TwoDoubleChartResult> getTwoData();

    CommonResult<DoubleParam> getNewOne(String name);

    CommonResult<String> getStatus(String name);

    CommonResult<String> updateStatus(UpdateParam param);
}
