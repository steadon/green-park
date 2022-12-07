package com.newEng.greenpark.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SwitchMapper {
    Integer updateById(String historyId, Integer newStatus, Integer count);

    Integer selectForCount(String historyId);

    Integer selectForStatus(String historyId);
}
