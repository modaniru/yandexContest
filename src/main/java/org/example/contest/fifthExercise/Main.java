package org.example.contest.fifthExercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        class Close{
            int index;
            char letter;

            @Override
            public String toString() {
                return "Close[" +
                        "index=" + index +
                        ", letter=" + letter +
                        ']';
            }

            public Close(int index, char letter) {
                this.index = index;
                this.letter = letter;

            }
        }
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        String code = scanner.nextLine();
        Stack<Close> par = new Stack<>();
        for(int i = 0; i < code.length(); i++){
            if(code.charAt(i)=='{'){
                par.add(new Close(i, '{'));
            }
            else if (code.charAt(i)=='}' && !par.isEmpty()){
                if(par.peek().letter=='{') par.pop();
                else{
                    par.add(new Close(i, '}'));
                }
            }
        }
        if(par.size()!=1) {
            System.out.println(-1);
        }
        else{
            System.out.println(par.pop().index+1);
        }
    }
}
