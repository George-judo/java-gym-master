package ru.yandex.practicum.gym;

public class CounterOfTrainings implements Comparable<CounterOfTrainings> {
    private Coach coach;
    private int amountOfTraining;

    public CounterOfTrainings(Coach coach, int amountOfTraining) {
        this.coach = coach;
        this.amountOfTraining = amountOfTraining;
    }

    public Coach getCoach() {
        return coach;
    }

    public int getAmountOfTraining() {
        return amountOfTraining;
    }

    @Override
    public int compareTo(CounterOfTrainings o) {
        return Integer.compare(o.amountOfTraining, this.amountOfTraining);
    }
}