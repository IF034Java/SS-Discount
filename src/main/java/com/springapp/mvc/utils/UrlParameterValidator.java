package com.springapp.mvc.utils;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 10/6/13
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class UrlParameterValidator {
    public UrlParameterValidator() {
    }

    public boolean getBoolean(String value) {
        return Boolean.parseBoolean(value);
    }

    public int getInt(String value){
        try {
            return Math.abs(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
