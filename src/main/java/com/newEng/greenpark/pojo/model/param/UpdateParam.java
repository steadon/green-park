package com.newEng.greenpark.pojo.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateParam {
    @NotBlank
    private String name;
    @NotNull
    private Integer status;
}
