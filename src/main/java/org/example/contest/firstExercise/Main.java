package org.example.contest.firstExercise;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder a = new StringBuilder(bufferedReader.readLine());
        String b = bufferedReader.readLine();
        bufferedReader.close();

        StringBuilder answer = new StringBuilder(a);
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                answer.replace(i, i + 1, "P");
                a.replace(i, i + 1, "*");
            }
        }
        for (int i = 0; i < a.length(); i++) {
            if (answer.charAt(i) == 'P') continue;
            int index = a.indexOf(String.valueOf(b.charAt(i)));
            if (index != -1) {
                answer.replace(i, i + 1, "S");
                a.replace(index, index + 1, "*");
                continue;
            }
            answer.replace(i, i + 1, "I");
        }
        System.out.println(answer + " " + a);
    }
}
