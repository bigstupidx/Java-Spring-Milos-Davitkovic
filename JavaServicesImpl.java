package netgloo.com.java;

import netgloo.com.java.Array.ArrayFn;
import netgloo.com.java.Integer.IntegerFn;
import netgloo.com.java.Time.TimeFn;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Miloš on 04/03/2017.
 */
@Service("JavaServices")
public class JavaServicesImpl implements JavaServices {

    @Autowired
    ArrayFn arrayFn;
    @Autowired
    TimeFn timeFn;
    @Autowired
    IntegerFn integerFn;

    @Override
    public <E> List<E> iterableToList(Iterable<E> iterable) {
        return arrayFn.IterableToList(iterable);
    }

    @Override
    public List<String> removeDuplicateInList(List<String> inputList) {
        return arrayFn.removeDuplicateInList(inputList);
    }

    @Override
    public List<Integer> arrayToList(int[] numbers) {
        return arrayFn.ArrayToList(numbers);
    }

    @Override
    public <T> T[] joinArray(T[][] arrays) {
        return arrayFn.joinArray(arrays);
    }

    @Override
    public Map<String, Integer> sortMapByKey(Map<String, Integer> unsortMap) {
        return arrayFn.SortMapByKey(unsortMap);
    }

    @Override
    public Map<String, Integer> sortMapByValue(Map<String, Integer> unsortMap) {
        return arrayFn.SortMapByValue(unsortMap);
    }

    @Override
    public Date currentNTPTime() {
        return timeFn.currentNTPTime();
    }

    @Override
    public Integer yearsFrom(int year, int month, int day) {
        return timeFn.yearsFrom(year, month, day);
    }

    @Override
    public Integer yearsBetween(Date date1, Date date2) {
        return timeFn.yearsBetween(date1, date2);
    }

    @Override
    public String seasonOfYearNow() {
        return timeFn.seasonOfYearNow();
    }

    @Override
    public boolean isYearLeep(Integer year) {
        return timeFn.isYearLeep(year);
    }

    @Override
    public boolean isThisYearLeep() {
        return timeFn.isThisYearLeep();
    }

    @Override
    public DateTime timeAfterSpecificHours(Integer hours) {
        return timeFn.timeAfterSpecificHours(hours);
    }

    @Override
    public String getNowMonth() {
        return timeFn.getNowMonth();
    }

    @Override
    public Integer getNowDate() {
        return timeFn.getNowDate();
    }

    @Override
    public Integer getNumOfNowMonth() {
        return timeFn.getNumOfNowMonth();
    }

    @Override
    public boolean isFirstGreatherThenSecond(Integer int1, Integer int2) {
        return integerFn.isFirstGreatherThenSecond(int1, int2);
    }

    @Override
    public boolean isFirstLessThenSecond(Integer int1, Integer int2) {
        return integerFn.isFirstLessThenSecond(int1, int2);
    }

    @Override
    public boolean isIntegersEqual(Integer int1, Integer int2) {
        return integerFn.isIntegersEqual(int1, int2);
    }

    @Override
    public int getRandomNumberInRange(int min, int max) {
        return integerFn.getRandomNumberInRange(min, max);
    }

    @Override
    public List<Integer> evenNumbers(List<Integer> list) {
        return integerFn.evenNumbers(list);
    }

    @Override
    public List<Integer> oddNumbers(List<Integer> list) {
        return integerFn.oddNumbers(list);
    }

    @Override
    public List<Integer> greaterThenNumbers(List<Integer> list, Integer number) {
        return integerFn.greaterThenNumbers(list, number);
    }

    @Override
    public Integer getDayOfWeekInt() {
        return timeFn.getDayOfWeekInt();
    }

    @Override
    public String getDayOfWeek() {
        return timeFn.getDayOfWeek();
    }
}
