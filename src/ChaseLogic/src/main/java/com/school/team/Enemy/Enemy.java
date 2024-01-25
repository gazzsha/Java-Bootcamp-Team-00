package com.school.team.Enemy;

import com.school.team.Utils.Pair;

public class Enemy {
    public Enemy() {
    }
    private Pair<Integer> currentPosition;

    public Enemy(Pair<Integer> currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Pair<Integer> getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Pair<Integer> currentPosition) {
        this.currentPosition = currentPosition;
    }
}
