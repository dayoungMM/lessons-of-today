package convertDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConvertDateYear {

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date inputDate = dateFormat.parse("2022-01-02");
            //1월 1일 구하기
            int year = inputDate.getYear() + 1900;
            Calendar firstDate = new GregorianCalendar();
            firstDate.set(Calendar.YEAR, year);
            firstDate.set(Calendar.MONTH, 0);
            firstDate.set(Calendar.DAY_OF_MONTH,0);
            //input 날짜 - 1월 1일로 차이 구하기
            long diff = inputDate.getTime() - firstDate.getTime().getTime();
            long calcDays = (diff /(1000*60*60*24)) +1 ;
            System.out.println(calcDays);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
