package com.newEng.greenpark.POJO.dto.result;

import com.newEng.greenpark.POJO.dto.param.DoubleParam;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DoubleResult {
    private List<DoubleParam> doubleList;
}
