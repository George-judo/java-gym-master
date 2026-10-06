package Gym;

import java.util.*;

public class Timetable {

    private Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        if (timetable.containsKey(trainingSession.getDayOfWeek())){
            Map dayMap = timetable.get(trainingSession.getDayOfWeek());
            if (dayMap.containsKey(trainingSession.getTimeOfDay())){
                //List listOfTrainingSessions = dayMap.get(trainingSession.getTimeOfDay());
                //listOfTrainingSessions.add(trainingSession);
            }
        } else {
            //timetable.put(trainingSession.getDayOfWeek(), );

        }
        //сохраняем занятие в расписании
    }

    public /* непонятно, что возвращать */ getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
    }

    public /* непонятно, что возвращать */ getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
    }

}
