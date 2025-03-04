package com.AOC.AOC2024.Day01;

import com.AOC.AOC2024.common.ListFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Day01 {
    Logger logger = Logger.getLogger(Day01.class.getName());

    private HashMap<String, ArrayList<Integer>> getSortedInputColumns() throws FileNotFoundException {
        ListFileReader listFileReader = new ListFileReader();
        ArrayList<String> inputLines = listFileReader.getInputLines("src/main/java/com/AOC/AOC2024/Day01/input.txt");
        ArrayList<Integer> leftCol = new ArrayList<>();
        ArrayList<Integer> rightCol = new ArrayList<>();
        inputLines.forEach(line -> {
            String[] splitLine = line.split("\\s+");

            assert splitLine.length == 2;

            leftCol.add(Integer.parseInt(splitLine[0]));
            rightCol.add(Integer.parseInt(splitLine[1]));
        });

        leftCol.sort((a, b) -> b.compareTo(a));
        rightCol.sort((a, b) -> b.compareTo(a));

        return new HashMap<>() {{
            put("leftCol", leftCol);
            put("rightCol", rightCol);
        }};
    }

    public int getDistancesSum() throws FileNotFoundException {
        HashMap<String, ArrayList<Integer>> inputColumns = getSortedInputColumns();
        ArrayList<Integer> leftCol = inputColumns.get("leftCol");
        ArrayList<Integer> rightCol = inputColumns.get("rightCol");

        ArrayList<Integer> distances = new ArrayList<>();
        for (int j = 0; j < leftCol.size(); j++) {
            Integer rightValue = rightCol.get(j);
            Integer leftValue = leftCol.get(j);
            distances.add(Math.abs(leftValue - rightValue));
        }

        return distances.stream().mapToInt(Integer::intValue).sum();
    }
    
    public int getSimilarityScore() throws FileNotFoundException {
        HashMap<String, ArrayList<Integer>> inputColumns = getSortedInputColumns();
        ArrayList<Integer> leftCol = inputColumns.get("leftCol");
        ArrayList<Integer> rightCol = inputColumns.get("rightCol");

        int score = 0;
        Map<String, Long> counter = rightCol.stream()
                .collect(Collectors.groupingBy(Object::toString, Collectors.counting()));
        for (Integer right : rightCol) {
            counter.put(right.toString(), counter.getOrDefault(right.toString(), 0L) + 1);
        }

        for (long num : leftCol) {
            score += (int) (num * counter.get(String.valueOf(num)));
        }
        return score;
    }
}
