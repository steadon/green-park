package com.newEng.greenpark.pojo.model.result;

import com.newEng.greenpark.pojo.model.param.DoubleParam;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DoubleResult {
    private List<DoubleParam> doubleList;
}
