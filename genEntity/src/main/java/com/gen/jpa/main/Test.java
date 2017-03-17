package com.gen.jpa.main;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by guofeng.chen on 2016/10/21.
 */
public class Test {

    public static boolean isMonthEnd(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.get(Calendar.DATE) == 1;
    }

    public static void main(String[] args) throws IOException {
        Date date = new Date(2016,2,30);
        System.out.println(Test.isMonthEnd(date));

    }
}
