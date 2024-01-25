package com.school.team.Logic;

import com.school.team.Enemy.Enemy;
import com.school.team.Utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class Logic {
    private int[][] map;


    public Logic() {}

    public void setMap(int[][] map) {
        this.map = map;
    }

    public Pair<Integer> nextStep(Enemy enemy) {
        int[][] copyMap = map.clone();
        Pair<Integer> currentPositionEnemy = enemy.getCurrentPosition();

    }

    public void waveAlgorithm(Pair<Integer> currentPosition,Pair<Integer> targetPosition,int[][] map) {
        Waves oldWave = new Waves(currentPosition);
        while (map[1][1] != map[targetPosition.first][targetPosition.second])  {
            for (Pair<Integer> waves : oldWave.Waves) {
                Waves newWaves = new Waves(new Pair<>(waves.first - 1, waves.second),new Pair<>(waves.first, waves.second + 1),
                        new Pair<>(waves.first + 1, waves.second),new Pair<>(waves.first, waves.second - 1));
                for (Pair<Integer> newWave : new Wave(waves.first - 1, waves.second)) {

                }
//                        new Wave(waves.first, waves.second + 1),new Wave(waves.first, waves.second - 1),
//                        new Wave(waves.first, waves.second + 1))
            }
        }
    }

    class Waves {
        public List<Pair<Integer>> Waves;

        public Waves(Pair<Integer> positionWave) {
            Waves = new ArrayList<>();
            Waves.add(positionWave);
        }
        public Waves(Pair<Integer>... args) {
            Waves = new ArrayList<>();
            for (Pair<Integer> wave : args) {
                Waves.add(wave);
            }
        }
    }

    class Wave {
        public Pair<Integer> position;

        public Wave(Pair<Integer> position) {
            this.position = position;
        }
        public  Wave(int x,int y) {
            position = new Pair<>(x,y);
        }
    }

}
