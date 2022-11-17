package com.newEng.greenpark.POJO.dto.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TwoDoubleChartResult {
    DoubleChartResult resultA;
    DoubleChartResult resultB;
}
