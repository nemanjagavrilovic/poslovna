package poslovna.informatika.poslovna.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    public static int getYear(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static boolean sameYear(Date date1, Date date2) {
        int year1 = DateUtil.getYear(date1);
        int year2 = DateUtil.getYear(date2);
        return year1 == year2;
    }
}
