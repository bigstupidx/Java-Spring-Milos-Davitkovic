package netgloo.com.java;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Miloš on 04/03/2017.
 */
public interface JavaServices {

    // Array
    <E> List<E> iterableToList(Iterable<E> iterable);
    List<String> removeDuplicateInList(List<String> inputList);
    List<Integer> arrayToList(int[] numbers);
    <T> T[] joinArray(T[]... arrays);
    Map<String, Integer> sortMapByKey(Map<String, Integer> unsortMap);
    Map<String, Integer> sortMapByValue(Map<String, Integer> unsortMap);

    // Integer
    boolean isFirstGreatherThenSecond(Integer int1, Integer int2);
    boolean isFirstLessThenSecond(Integer int1, Integer int2);
    boolean isIntegersEqual(Integer int1, Integer int2);
    int getRandomNumberInRange(int min, int max);
    List<Integer> evenNumbers(List<Integer> list);
    List<Integer> oddNumbers(List<Integer> list);
    List<Integer> greaterThenNumbers(List<Integer> list, Integer number);


    // Dae & Time
    Date currentNTPTime();
    Integer yearsFrom(int year, int month, int day);
    Integer yearsBetween(Date date1,Date date2);
    String seasonOfYearNow();
    boolean isYearLeep(Integer year);
    boolean isThisYearLeep();
    DateTime timeAfterSpecificHours(Integer hours);
    String getNowMonth();
    Integer getNowDate();
    Integer getNumOfNowMonth();
    Integer getDayOfWeekInt();
    String getDayOfWeek();

}
