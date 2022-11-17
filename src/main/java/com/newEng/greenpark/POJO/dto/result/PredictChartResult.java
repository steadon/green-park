package com.newEng.greenpark.POJO.dto.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PredictChartResult {
    private List<Double> lastSecondPvList;
    private List<Double> realValueList;
    private List<Double> SecondPvList;
    private List<Double> SecondErrorList;
}
