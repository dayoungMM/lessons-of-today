package convertDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConvertDate {

    public static void main(String[] args) {

        Calendar inputDate = new GregorianCalendar();
        inputDate.set(Calendar.YEAR, 2022);
        inputDate.set(Calendar.MONTH, Integer.parseInt(args[0]) - 1);
        inputDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(args[1]) - 1);

        //1월 1일 구하기
        Calendar firstDate = new GregorianCalendar();
        firstDate.set(Calendar.YEAR, 2022);
        firstDate.set(Calendar.MONTH, 0);
        firstDate.set(Calendar.DAY_OF_MONTH,0);

        //input 날짜 - 1월 1일로 차이 구하기
        long diff = inputDate.getTime().getTime() - firstDate.getTime().getTime();
        long calcDays = (diff /(1000*60*60*24)) +1 ;
        System.out.println(calcDays);

    }
}
