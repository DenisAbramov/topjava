package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

        List<UserMealWithExceed> list =  getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

        System.out.println(list);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> list = new LinkedList<>();
        for(UserMeal user: mealList)
        {
            if(user.getDateTime().toLocalTime().isBefore(endTime) && user.getDateTime().toLocalTime().isAfter(startTime))
            {
                int count = 0;
                for(int i =0; i < mealList.size(); i++)
                {
                    if(user.getDateTime().getDayOfMonth() == (mealList.get(i).getDateTime().getDayOfMonth())) //если число месяца равно - то суммируем все калории за это число
                    {
                        count = count + mealList.get(i).getCalories();
                    }
                }
                list.add(new UserMealWithExceed(user.getDateTime(), user.getDescription(), user.getCalories(), count > caloriesPerDay));
            }

        }

        return list;
    }

}
