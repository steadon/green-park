package com.newEng.greenpark.controller;

import com.newEng.greenpark.POJO.CommonResult;
import com.newEng.greenpark.POJO.dto.result.PredictChartResult;
import com.newEng.greenpark.POJO.dto.vo.AllChartArgsVo;
import com.newEng.greenpark.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FunctionController {
    private final FunctionService functionService;

    @Autowired
    public FunctionController(FunctionService functionService) {
        this.functionService = functionService;
    }

    @GetMapping("/function/predict")
    public CommonResult<PredictChartResult> predict(@RequestParam String name) {
        return functionService.predict(name);
    }

    @GetMapping("/function/equal")
    public CommonResult<Double> carbonEqual() {
        return functionService.carbonEqual();
    }

    @GetMapping("/function/all")
    public CommonResult<AllChartArgsVo> getAllChartArgs() {
        return functionService.getAllChartArgs();
    }
}
