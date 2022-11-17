package com.newEng.greenpark.common;

import org.springframework.stereotype.Component;

@Component
public class HistoryId {
    //发电机
    public static String GENERATOR_VOLTAGE = "/DemoC/PowerGeneratorVoltage";
    public static String GENERATOR_CURRENT = "/DemoC/PowerGeneratorCurrent";
    public static String GENERATOR_POWER = "/DemoC/PowerGeneratorPower";
    public static String GENERATOR_ENERGY = "/DemoC/PowerGeneratorEnergy";

    //发电机动作
    public static String GENERATOR_SWITCH = "/DemoC/SwitchGeneratorHandle";

    //总负载
    public static String LOAD_ENERGY = "/DemoC/PowerLoadEnergy";
    public static String LOAD_VOLTAGE = "/DemoC/PowerLoadVoltage";
    public static String LOAD_CURRENT = "/DemoC/PowerLoadCurrent";
    public static String LOAD_POWER = "/DemoC/PowerLoadPower";

    //总负载动作
    public static String LOAD_SWITCH = "/DemoC/SwitchLoadHandle";

    //空气质量指标
    public static String CO2 = "/DemoC/EnvCO2eqCO2eq";
    public static String PM2P5 = "/DemoC/PM2p5Concentration";
    public static String TVOC = "/DemoC/EnvTVOCTVOC";

    //组件动作
    public static String LIGHT_SWITCH = "/DemoC/SwitchLightHandle";
    public static String FAN_SWITCH = "/DemoC/SwitchFanHandle";
    public static String FAN_AUTO_SWITCH = "/DemoC/SwitchFanAutoControl";

    //状态
    public static String LOAD_ENABLE = "/DemoC/PowerLoadEnable";
    public static String CO2_ENABLE = "/DemoC/EnvCO2eqEnable";
    public static String TVOC_ENABLE = "/DemoC/EnvTVOCEnable";
    public static String GENERATOR_ENABLE = "/DemoC/PowerGeneratorEnable";
}
