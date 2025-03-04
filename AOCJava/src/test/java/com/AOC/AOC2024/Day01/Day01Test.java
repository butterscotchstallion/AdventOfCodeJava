package com.AOC.AOC2024.Day01;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {
    @Test
    public void testDay01() throws FileNotFoundException {
        Day01 day01 = new Day01();
        int similarityScore = day01.getSimilarityScore();
        assertEquals(22776016, similarityScore);
    }
}
