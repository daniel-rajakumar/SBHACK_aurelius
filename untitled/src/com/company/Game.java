package com.company;

import java.util.Random;
import java.util.Scanner;

enum Weapon {
    ROCK, PAPER, SCISSOR;
}
enum Result {
    WIN, TIE, LOSE;
}

enum Level {
    EASY, MEDIUM, HARD
}

public class Game {
    Scanner scanner;
    Weapon player1_weapon, player2_weapon;
    Result resut;
    boolean run = true;
    int score = 0, score_2 = 0;
    int count = 0;
    Level level;
    Weapon prevWeapon;


    Game(){
        scanner = new Scanner(System.in);

        System.out.println("Hey there, welcome to this game. ");
        System.out.println("Goal: 10 rounds, the player with highest point wins");

        System.out.println("Choose level: (1): Easy | (2): Medium | (3): Hard");
        level = chooseLevel();
        System.out.println("You are playing against " + level + " level bot! Good luck! ");
        loop();
    }

    void loop(){
        while (run){
            count++;
            startRoundResult();
            player1_weapon = askPrompt();
            prevWeapon = player1_weapon;
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
        int random = 0;
        int hardness = 0;

        switch (level){
            case MEDIUM -> hardness = 14;
            case HARD -> hardness = 17;
            default -> hardness = 3;
        }
//
        random = random(1, hardness);

        switch (player1_weapon){
            case PAPER -> {
                if (random == 1) return Weapon.ROCK;
                if (random == 2) return Weapon.PAPER;
                return Weapon.SCISSOR;
            }
            case ROCK -> {
                if (random == 1) return Weapon.SCISSOR;
                if (random == 2) return Weapon.ROCK;
                return Weapon.PAPER;
            }
            case SCISSOR -> {
                if (random == 1) return Weapon.SCISSOR;
                if (random == 2) return Weapon.PAPER;
                return Weapon.ROCK;
            }
            default -> { return Weapon.ROCK;}
        }
    }

    Level chooseLevel(){
        switch (scanner.nextLine()){
            case "1": return Level.EASY;
            case "2": return Level.MEDIUM;
            case "3": return Level.HARD;
        }

        System.out.println("invalid input! try again:");
        return chooseLevel();
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
