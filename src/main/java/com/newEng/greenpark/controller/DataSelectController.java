package com.newEng.greenpark.controller;

import com.newEng.greenpark.POJO.CommonResult;
import com.newEng.greenpark.POJO.dto.param.DoubleParam;
import com.newEng.greenpark.POJO.dto.param.UpdateParam;
import com.newEng.greenpark.POJO.dto.result.TwoDoubleChartResult;
import com.newEng.greenpark.service.DataSelectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
public class DataSelectController {
    private final DataSelectService dataSelectService;

    //注入业务层Bean
    @Autowired
    public DataSelectController(DataSelectService dataSelectService) {
        this.dataSelectService = dataSelectService;
    }

    @GetMapping("/data/two")
    public CommonResult<TwoDoubleChartResult> getTwoData() {
        log.info("[Get]getTwoData");
        return dataSelectService.getTwoData();
    }

    @GetMapping("/data/one")
    public CommonResult<DoubleParam> getNewOne(@RequestParam String name) {
        return dataSelectService.getNewOne(name);
    }

    /**
     * @param name historyID
     * @description 根据historyID获取设备或开关状态
     */
    @GetMapping("/status/get")
    public CommonResult<String> getStatus(@RequestParam String name) {
        log.info("[Get]getStatus param = " + name);
        return dataSelectService.getStatus(name);
    }

    /**
     * @param param historyID + status
     * @description 根据historyID修改开关状态
     */
    @PostMapping("/status/update")
    public CommonResult<String> updateStatus(@RequestBody UpdateParam param) {
        log.info("[Post]updateStatus body = " + param.toString());
        return dataSelectService.updateStatus(param);
    }
}
