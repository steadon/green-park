package com.newEng.greenpark.utils;

import static com.newEng.greenpark.common.HistoryId.*;

public class SwitchUtil {
    public static String switchForName(String name) {
        switch (name) {

            //数据
            case "CO2":
                name = CO2;
                break;
            case "PM2P5":
                name = PM2P5;
                break;
            case "TVOC":
                name = TVOC;
                break;
            case "GENERATOR_VOLTAGE":
                name = GENERATOR_VOLTAGE;
                break;
            case "GENERATOR_CURRENT":
                name = GENERATOR_CURRENT;
                break;
            case "GENERATOR_POWER":
                name = GENERATOR_POWER;
                break;
            case "GENERATOR_ENERGY":
                name = GENERATOR_ENERGY;
                break;
            case "LOAD_ENERGY":
                name = LOAD_ENERGY;
                break;
            case "LOAD_VOLTAGE":
                name = LOAD_VOLTAGE;
                break;
            case "LOAD_CURRENT":
                name = LOAD_CURRENT;
                break;
            case "LOAD_POWER":
                name = LOAD_POWER;
                break;

            //开关
            case "GENERATOR_SWITCH":
                name = GENERATOR_SWITCH;
                break;
            case "LOAD_SWITCH":
                name = LOAD_SWITCH;
                break;
            case "LIGHT_SWITCH":
                name = LIGHT_SWITCH;
                break;
            case "FAN_SWITCH":
                name = FAN_SWITCH;
                break;
            case "FAN_AUTO_SWITCH":
                name = FAN_AUTO_SWITCH;
                break;

            //状态
            case "LOAD_ENABLE":
                name = LOAD_ENABLE;
                break;
            case "CO2_ENABLE":
                name = CO2_ENABLE;
                break;
            case "TVOC_ENABLE":
                name = TVOC_ENABLE;
                break;
            case "GENERATOR_ENABLE":
                name = GENERATOR_ENABLE;
                break;

            //其他
            default:
                name = null;
        }
        return name;
    }
}
