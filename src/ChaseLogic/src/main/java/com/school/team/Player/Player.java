package com.school.team.Player;

import com.school.team.Utils.Pair;

public class Player {

    private Pair<Integer> currentPosition;

    public Player() {
    }

    public Player(Pair<Integer> currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Pair<Integer> getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Pair<Integer> currentPosition) {
        this.currentPosition = currentPosition;
    }
}
