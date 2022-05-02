package com.zahaand.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] values = {8, 3, 5, 7, 5, 8, 3, 8, 4};
        System.out.println(minValue(values));

        List<Integer> list = new ArrayList<>() {{
            add(8);
            add(3);
            add(5);
            add(7);
            add(5);
            add(8);
            add(3);
            add(8);
            add(4);
        }};
        System.out.println(oddOrEven(list));
    }

    static int minValue(int[] values) {
        int[] value = Arrays.stream(values).distinct().sorted().toArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : value) {
            stringBuilder.append(i);
        }
        return Integer.parseInt(stringBuilder.toString());
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        return (integers.stream().mapToInt(i -> i).sum()) % 2 == 0 ? integers.stream().filter(i -> i % 2 == 0).collect(Collectors.toList()) : integers.stream().filter(i -> i % 2 != 0).collect(Collectors.toList());
    }
}
