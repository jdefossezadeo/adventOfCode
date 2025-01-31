package com.mycompany.jean.year2016.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 {

    public static void main(String[] args) throws IOException {

        // read file day01 into a String
        List<String> input = Files.readAllLines(Path.of("src/main/resources/input/2016/02.txt"));

        System.out.println("Part 1: " + part1(input));
        System.out.println("Part 2: " + part2(input));

    }

    public static int part1(List<String> input) {

        int start = 5; // 95549
        String code = "";
        for (String instructions : input) {
            start = decodeOnePart1(instructions, start);
            code += start;
        }
        return Integer.parseInt(code);
    }

    private static int decodeOnePart1(String instructions, int start) {
        int[][] keypad = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int xStart = start % 3;
        int yStart = start / 3;

        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'U':
                    yStart = Math.max(0, yStart - 1);
                    break;
                case 'D':
                    yStart = Math.min(2, yStart + 1);
                    break;
                case 'L':
                    xStart = Math.max(0, xStart - 1);
                    break;
                case 'R':
                    xStart = Math.min(2, xStart + 1);
                    break;
            }
        }

        return yStart * 3 + xStart + 1;
    }

    public static int part2(List<String> input) {

        int start = 5;
        return Integer.parseInt(input.stream()
                    .map(instructions -> decodeOnePart2(instructions, start))
                    .map(Object::toString).collect(Collectors.joining()));
    }

    private static int decodeOnePart2(String instructions, int start) {
        int[][] keypad = {
                {-1, -1, 1, -1, -1},
                {-1, 2, 3, 4, -1},
                {5, 6, 7, 8, 9},
                {-1, 0xA, 0xB, 0xC, -1},
                {-1, -1, 0xD, -1, -1}
        };

        int xStart = start % 5;
        int yStart = start / 5;

        return keypad[4][0];
    }
}
