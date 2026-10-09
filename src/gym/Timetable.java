package gym;

import java.util.*;

public class Timetable {

    private Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        DayOfWeek dayOfWeek = trainingSession.getDayOfWeek();
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();

        if (!timetable.containsKey(dayOfWeek)) {
            timetable.put(dayOfWeek, new TreeMap<>());
        }

        Map<TimeOfDay, List<TrainingSession>> mapDayOfWeek = timetable.get(dayOfWeek);

        if (!mapDayOfWeek.containsKey(timeOfDay)) {
            mapDayOfWeek.put(timeOfDay, new ArrayList<>());
        }

        List<TrainingSession> list = mapDayOfWeek.get(timeOfDay);
        list.add(trainingSession);
    }

    public TreeMap<TimeOfDay, List<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        if (timetable.containsKey(dayOfWeek)) {
            return timetable.get(dayOfWeek);
        }
        return new TreeMap<>();
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, List<TrainingSession>> day = this.getTrainingSessionsForDay(dayOfWeek);
        if (day.isEmpty()) {
            return new ArrayList<>();
        }
        if (day.containsKey(timeOfDay)) {
            return day.get(timeOfDay);
        }
        return new ArrayList<>();
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        HashMap<Coach, Integer> counts = new HashMap<>();

        for (TreeMap<TimeOfDay, List<TrainingSession>> map : timetable.values()) { //C
            // перебором помогла ии
            for (List<TrainingSession> list : map.values()) {
                for (TrainingSession trainingSession : list) {
                    if (!counts.containsKey(trainingSession.getCoach())) {
                        counts.put(trainingSession.getCoach(), 0);
                    }
                    counts.put(trainingSession.getCoach(),
                            counts.get(trainingSession.getCoach()) + 1);
                }
            }
        }

        List<CounterOfTrainings> counterOfTrainings = new ArrayList<>();
        for (Map.Entry<Coach, Integer> map : counts.entrySet()) {
            counterOfTrainings.add(new CounterOfTrainings(map.getKey(), map.getValue()));
        }

        Collections.sort(counterOfTrainings);

        return counterOfTrainings;
    }

}
