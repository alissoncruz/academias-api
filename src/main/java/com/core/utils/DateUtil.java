package com.core.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
public abstract class DateUtil {

    public static Integer getAge(final Date birthDate) {
        Calendar cDate = Calendar.getInstance();
        Calendar cToday = Calendar.getInstance();
        cDate.setTime(birthDate);
        cDate.set(Calendar.YEAR, cToday.get(Calendar.YEAR));
        int age = cDate.after(cToday) ? -1 : 0;
        cDate.setTime(birthDate);
        age += cToday.get(Calendar.YEAR) - cDate.get(Calendar.YEAR);
        return age;
    }

    public static int getYear(LocalDate date){
        return date.getYear();
    }
    public static int getMoth(LocalDate date){
        return date.getMonthValue();
    }
}
