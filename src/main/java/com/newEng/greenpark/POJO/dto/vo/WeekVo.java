package com.newEng.greenpark.POJO.dto.vo;

import com.newEng.greenpark.POJO.domain.CarbonDay;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WeekVo {
    List<CarbonDay> weekParamList;
    int total;
}
