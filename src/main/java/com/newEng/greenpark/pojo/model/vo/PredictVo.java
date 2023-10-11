package com.newEng.greenpark.pojo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PredictVo {
    private double si;
    private double lastSecondPv;
    private double realValue;
    private double SecondPv;
    private double SecondError;
}
