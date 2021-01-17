package com.springboot.elementuser.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Result {
    private Boolean status = true;
    private String msg;
}
