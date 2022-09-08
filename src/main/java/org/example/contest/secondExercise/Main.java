package org.example.contest.secondExercise;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        class Player{
            final String name;
            final int tricks;
            final int miss;
            Player(String name, int tricks, int miss){
                this.miss = miss;
                this.name = name;
                this.tricks = tricks;
            }

            @Override
            public String toString() {
                return "Player{" +
                        "name='" + name + '\'' +
                        ", tricks=" + tricks +
                        ", miss=" + miss +
                        '}';
            }
        }

        Scanner scanner = new Scanner(System.in);
        int countChallenge = scanner.nextInt();
        scanner.nextLine();
        HashMap<String, Integer> challengeAndFinalists = new HashMap<>();
        HashMap<String, List<Player>> playersSort = new HashMap<>();
        for(int i = 0; i < countChallenge; i++){
            String[] arguments = scanner.nextLine().split(",");
            challengeAndFinalists.put(arguments[0], Integer.valueOf(arguments[1]));
            playersSort.put(arguments[0], new ArrayList<>());
        }
        int players = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < players; i++) {
            String[] arguments = scanner.nextLine().split(",");
            List<Player> players1 = playersSort.get(arguments[1]);
            players1.add(new Player(
                    arguments[0],
                    Integer.parseInt(arguments[2]),
                    Integer.parseInt(arguments[3])));
        }
        List<String> winners = new ArrayList<>();
        for (String s : playersSort.keySet()) {
            winners.addAll(playersSort.get(s).stream().sorted((p1, p2) -> {
                if(p1.tricks > p2.tricks) return -1;
                if(p1.tricks < p2.tricks) return 1;
                if(p1.miss > p2.miss) return 1;
                return -1;
            }).map(e -> e.name).collect(Collectors.toList()).subList(0, challengeAndFinalists.get(s)));
        }
        winners = winners.stream().sorted().collect(Collectors.toList());
        for (String winner : winners) {
            System.out.println(winner);
        }
    }
}
