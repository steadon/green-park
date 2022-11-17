package com.newEng.greenpark.POJO.dto.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SecondSmoothingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Double> realDataList;

    private Double lastSinglePredictParam;

    private Double lastSecondPredictParam;
    //预测期数
    private int predictTime;
}
