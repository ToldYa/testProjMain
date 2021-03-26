package com.vesa.testprojmain.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StrUtil {
    public boolean notNullOrEmpty(String s) {
        return s != null && !s.isEmpty();
    }
}
