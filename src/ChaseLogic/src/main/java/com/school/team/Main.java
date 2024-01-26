//package com.school.team;
//
//import com.school.team.Player.Enemy;
//import com.school.team.Logic.Logic;
//import com.school.team.Utils.Pair;
//
//public class Main {
//    public static void main(String[] args) {
//
//        int[][] map = {{3,0,3,2},
//                {0,0,0,0},
//                {3,0,3,0},
//                {1,0,0,0}};
//        Logic logic = new Logic();
//        logic.setMap(map.clone());
//       Pair step = logic.nextStep(new Enemy(new Pair<>(3,0)),new Pair<>(0,3),map);
//        System.out.println("=========================");
//        System.out.println(step);
//        for (int i = 0; i != map.length; i++) {
//            for (int j = 0; j != map[i].length; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
//
//
//}