package com.school.team.Utils;

public class Pair <T extends Number> implements Cloneable {
    public T first;
    public   T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public Pair(Pair pair) {
        this.first = (T) pair.first;
        this.second = (T) pair.second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
