package com.company;

import java.util.Random;
import java.util.Scanner;

enum Weapon {
    ROCK, PAPER, SCISSOR;
}
enum Result {
    WIN, TIE, LOSE;
}

enum LEVLES {
    EASY, MEDIUM, HERD
}

public class Game {
    Scanner scanner;
    Weapon player1_weapon, player2_weapon;
    Result resut;
    boolean run = true;
    int score = 0, score_2 = 0;
    int count = 0;


    Game(){
        scanner = new Scanner(System.in);

        System.out.println("Hey there, welcome to this game. ");
        System.out.println("Goal: 10 rounds, the player with highest point wins");


        loop();
    }

    void loop(){
        while (run){
            count++;
            startRoundResult();
            player1_weapon = askPrompt();
            player2_weapon = getRandomWeapon();
            resut = checkLost();
            if (resut == Result.WIN) score++;
            if (resut == Result.LOSE) score_2++;
            endRoundResult();
            run = checkGameEnd();
        }
    }


    void startRoundResult(){
        System.out.println("\n\n\n_____________ \nRound: " + count);
        System.out.println("score: [" + score + " - " + score_2+ "]");
        System.out.println("Press: (1): Rock | (2): Paper | (3): Scissor");
        System.out.print("Your input: ");
    }
    void endRoundResult(){
        System.out.printf("[your choice: %s] - [enemy choice: %s] = [result: %s]%n", player1_weapon, player2_weapon, resut);
    }

    Result checkLost(){
        switch (player1_weapon){
            case ROCK -> {
                switch (player2_weapon){
                    case ROCK: return Result.TIE;
                    case PAPER : return Result.LOSE;
                    case SCISSOR : return Result.WIN;
                }
            }

            case PAPER -> {
                switch (player2_weapon){
                    case ROCK : return Result.WIN;
                    case PAPER : return Result.TIE;
                    case SCISSOR : return Result.LOSE;
                }
            }

            case SCISSOR -> {
                switch (player2_weapon){
                    case ROCK : return Result.LOSE;
                    case PAPER : return Result.WIN;
                    case SCISSOR : return Result.TIE;
                }
            }
        }
        return null;
    }


    Weapon askPrompt(){
        switch (scanner.nextLine()) {
            case "1" : return Weapon.ROCK;
            case "2" : return Weapon.PAPER;
            case "3" : return Weapon.SCISSOR;
        }

        System.out.println("invalid input! try again:");
        return askPrompt();
    }

    Weapon getRandomWeapon(){
        return switch (random(1, 3)) {
            case 1 -> Weapon.ROCK;
            case 2 -> Weapon.PAPER;
            case 3 -> Weapon.SCISSOR;
            default -> null;
        };
    }

    boolean checkGameEnd(){
        if (count < 10) {
            return true;
        }

        System.out.println("\n\n\n");
        if (score > score_2){
            System.out.println("WOW! YOUR WIN");
        } else if (score < score_2) {
            System.out.println("LOL! YOUR LOSE");
        } else {
            System.out.println("BAAAM, it's a TIE!");
        }

        return false;
    }

    int random(int min, int max){
        return new Random().nextInt(max - min + 1) + min;
    }
}
