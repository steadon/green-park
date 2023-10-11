package com.newEng.greenpark.pojo.model.result;

import com.newEng.greenpark.pojo.model.vo.PredictVo;
import lombok.Data;

import java.util.List;

@Data
public class PredictResult {
    private List<PredictVo> predictList;
    private double pv;
    private int total;
}
