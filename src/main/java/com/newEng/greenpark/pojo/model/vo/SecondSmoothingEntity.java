package com.newEng.greenpark.pojo.model.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SecondSmoothingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Double> realDataList; // 数据点集合

    private Double lastSinglePredictParam; // 上一次单次预测值

    private Double lastSecondPredictParam; // 上一次二次预测值

    private int predictTime; //预测期数
}