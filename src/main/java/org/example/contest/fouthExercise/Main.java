package org.example.contest.fouthExercise;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        class Coord {
            int x;
            int y;
            String source;

            Coord(int x, int y) {
                this.x = x;
                this.y = y;
            }

            Coord(int x, int y, String source) {
                this.x = x;
                this.y = y;
                this.source = source;
            }

            List<Coord> getWays() {
                return Arrays.asList(
                        new Coord(x + 1, y, "L"),
                        new Coord(x - 1, y, "R"),
                        new Coord(x, y + 1, "U"),
                        new Coord(x, y - 1, "D")
                );
            }
        }


        Scanner scanner = new Scanner(System.in);
        int y = scanner.nextInt();
        int x = scanner.nextInt();
        scanner.nextLine();
        String[][] field = new String[y][x];
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < y; i++) {
            String row = scanner.nextLine();
            if (row.contains("S")) {
                startY = i;
                startX = row.indexOf("S");
            }
            field[i] = row.split("");
        }
        Queue<Coord> queue = new ArrayDeque<>();
        Coord start = new Coord(startX, startY);
        queue.add(start);
        while (!queue.isEmpty()){
            Coord pos = queue.poll();
            List<Coord> ways = pos.getWays();
            for (Coord way : ways) {
                if(Objects.equals(field[way.y][way.x], ".")){
                    field[way.y][way.x] = way.source;
                    queue.add(way);
                }
            }
        }
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }
}
