package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

      getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        //Так, вроде, быстрее.
        Map<Integer, Integer> map = new HashMap<>();

        for(UserMeal user: mealList)
        {
            int dayOfMonth = user.getDateTime().getDayOfMonth();
            if(map.containsKey(dayOfMonth))
            {
                map.put(dayOfMonth, map.get(dayOfMonth) + user.getCalories());
            }
            else
                map.put(dayOfMonth, user.getCalories());
        }


        List<UserMealWithExceed> list = new LinkedList<>();
        for(UserMeal user: mealList)
        {
            if(user.getDateTime().toLocalTime().isBefore(endTime) && user.getDateTime().toLocalTime().isAfter(startTime))
            {
                list.add(new UserMealWithExceed(user.getDateTime(), user.getDescription(), user.getCalories(), map.get(user.getDateTime().getDayOfMonth()) > caloriesPerDay));
            }

        }

        return list;


        /*
        List<UserMealWithExceed> list = new LinkedList<>();
        for(UserMeal user: mealList)
        {
            if(user.getDateTime().toLocalTime().isBefore(endTime) && user.getDateTime().toLocalTime().isAfter(startTime))
            {
                int count = 0;
                for(int i =0; i < mealList.size(); i++)
                {
                    if(user.getDateTime().getDayOfMonth() == (mealList.get(i).getDateTime().getDayOfMonth()))
                    {
                        count = count + mealList.get(i).getCalories();
                    }
                }
                list.add(new UserMealWithExceed(user.getDateTime(), user.getDescription(), user.getCalories(), count > caloriesPerDay));
            }

        }

        return list;*/
    }

}
