package com.b3a4a.groups.generator.global;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class Result {
    private boolean isOk;
    private String errorMsg;

    private Result() {
    }

    private Result(boolean isOk) {
        this.isOk = isOk;
    }

    private Result(boolean isOk, String errorMsg) {
        this.isOk = isOk;
        this.errorMsg = errorMsg;
    }

    public static Result ok() {
        return new Result(true);
    }

    public static Result error(String errorMsg) {
        return new Result(false, errorMsg);
    }
}
