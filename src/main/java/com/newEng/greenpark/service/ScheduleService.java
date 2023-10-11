package com.newEng.greenpark.service;

public interface ScheduleService {
    void deleteUselessHistory();

    void deleteUselessStatus();

    void insertDayCarbon();
}
