package com.newEng.greenpark.mapper;

import com.newEng.greenpark.POJO.domain.CarbonDay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarbonDayMapper {
    Integer insert(Double value, String dayTime);

    List<CarbonDay> selectNearTen();
    List<CarbonDay> selectNearSeven();
}
