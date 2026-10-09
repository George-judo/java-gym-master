package junit;

import gym.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach, DayOfWeek.MONDAY,
                new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);
        int amountOfTrainingsInMonday = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size();
        int amountOfTrainingsInTUESDAY = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).size();


        assertEquals(1, amountOfTrainingsInMonday);
        assertEquals(0, amountOfTrainingsInTUESDAY);
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        int amountOfTrainingsInMonday = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size();
        assertEquals(1, amountOfTrainingsInMonday);
        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        TreeMap<TimeOfDay, List<TrainingSession>> map = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        assertEquals(2, map.size());

        // Проверка порядка ключей в TreeMap: сначала 13:00, потом 20:00
        var keys = new ArrayList<>(map.keySet()); // с этой частью помогла ии
        assertEquals(2, keys.size());
        assertEquals(new TimeOfDay(13, 0), keys.get(0));
        assertEquals(new TimeOfDay(20, 0), keys.get(1));

        // Проверить, что за вторник не вернулось занятий
        int amountOfForTuesday = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).size();
        assertEquals(0, amountOfForTuesday);
    }
}
