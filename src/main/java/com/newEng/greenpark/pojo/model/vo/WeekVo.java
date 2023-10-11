package com.newEng.greenpark.pojo.model.vo;

import com.newEng.greenpark.pojo.domain.CarbonDay;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WeekVo {
    List<CarbonDay> weekParamList;
    int total;
}
