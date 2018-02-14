package com.company.util;

public class ClassName {
    public static String getCurrentClassName() {
        // todo
//        return Thread.currentThread().getStackTrace()[1].getClassName();
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            return e.getStackTrace()[1].getClassName();
        }
    }
}
