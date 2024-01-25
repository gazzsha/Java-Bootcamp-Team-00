package com.school.team.Logic;

import com.school.team.Enemy.Enemy;
import com.school.team.Exception.ExceptionNotCorrectMap;
import com.school.team.Utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Logic {
    private int[][] map;


    public Logic() {}

    public void setMap(int[][] map) {
        this.map = map;
    }

    public Pair<Integer> nextStep(Enemy enemy,Pair<Integer> targetPosition,int[][] originalMap) throws ExceptionNotCorrectMap {
        Stack<Pair<Integer>> path = waveAlgorithm(enemy.getCurrentPosition(),targetPosition,originalMap);
        System.out.println(path);
        path.pop();
        return path.peek();
    }

    public int[][] deepCopy(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }

    public  Stack<Pair<Integer>> waveAlgorithm(Pair<Integer> currentPosition,Pair<Integer> targetPosition,int[][] originalMap) throws ExceptionNotCorrectMap {
        int[][] map = deepCopy(originalMap);
        Waves oldWave = new Waves(currentPosition);
        int step = 4;
        while (map[targetPosition.first][targetPosition.second] == 2)  {
            if (oldWave.Waves.isEmpty()) throw new ExceptionNotCorrectMap();
            Waves nextNewWaves = new Waves();
            ++step;
            for (Pair<Integer> waves : oldWave.Waves) {
                Waves newWaves = new Waves(new Pair<>(waves.first - 1, waves.second),new Pair<>(waves.first, waves.second + 1),
                        new Pair<>(waves.first + 1, waves.second),new Pair<>(waves.first, waves.second - 1));
                for (Pair<Integer> newWave : newWaves.Waves) {
                    if ((newWave.first < map.length && newWave.second < map.length && newWave.first >= 0 && newWave.second >= 0) && (map[newWave.first][newWave.second] == 0 || map[newWave.first][newWave.second] == 2)) {
                        nextNewWaves.Waves.add(newWave);
                        map[newWave.first][newWave.second] = step;
                    }
                }
            }
            oldWave = nextNewWaves;
        }

        for (int i = 0; i != map.length; i++) {
            for (int j = 0; j != map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        return solutionWave(map,currentPosition,targetPosition);
    }
    private  Stack<Pair<Integer>> solutionWave(int[][] map,Pair<Integer> from,Pair<Integer> to) {
        map[from.first][from.second] = 4;
        System.out.println("Map in silutionWave");
        for (int i = 0; i != map.length; i++) {
            for (int j = 0; j != map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        Stack<Pair<Integer>> path = new Stack<>();
        Pair<Integer> copyTo = new Pair<>(to);
        int lastStep = map[copyTo.first][copyTo.second];
        path.add(to);
        while (copyTo.second != from.second || copyTo.first != from.first) {
            if (copyTo.first - 1 >= 0 && map[copyTo.first - 1][copyTo.second] + 1 == lastStep) {
                copyTo.setFirst(copyTo.first - 1);
                path.add(new Pair<>(copyTo));
            }
            else if (copyTo.second + 1 < map.length && map[copyTo.first][copyTo.second + 1] + 1 == lastStep) {
                copyTo.setSecond(copyTo.second + 1);
                path.add(new Pair<>(copyTo));
            }
            else if (copyTo.first + 1 < map.length && map[copyTo.first + 1][copyTo.second] + 1 == lastStep) {
                copyTo.setFirst(copyTo.first + 1);
                path.add(new Pair<>(copyTo));
            }
            else if (copyTo.second - 1 >= 0 && map[copyTo.first][copyTo.second - 1] + 1 == lastStep) {
                copyTo.setSecond(copyTo.second - 1);
                path.add(new Pair<>(copyTo));
            }
            --lastStep;
        }
        return path;
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

        public Waves() {
            Waves = new ArrayList<>();
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
