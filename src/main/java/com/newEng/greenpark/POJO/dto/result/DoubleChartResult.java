package com.newEng.greenpark.POJO.dto.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DoubleChartResult {
    private List<Integer> idList;
    private List<String> historyIdList;
    private List<String> timeStampList;
    private List<Double> valueList;
}
