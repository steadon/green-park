package com.newEng.greenpark.pojo.model.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TwoDoubleChartResult {
    DoubleChartResult resultA;
    DoubleChartResult resultB;
}
