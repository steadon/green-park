package com.newEng.greenpark.POJO.dto.result;

import com.newEng.greenpark.POJO.dto.vo.PredictVo;
import lombok.Data;

import java.util.List;

@Data
public class PredictResult {
    private List<PredictVo> predictList;
    private double pv;
    private int total;
}
