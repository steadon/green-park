package com.newEng.greenpark.mapper;

import com.newEng.greenpark.pojo.domain.HistoryMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryMapMapper {
    HistoryMap selectByHistoryId(Integer historyId);
}
