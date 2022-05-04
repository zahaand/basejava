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
        return Arrays.stream(values).distinct().sorted().reduce(0, (left, right) -> (left * 10 + right));
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        return (integers.stream().mapToInt(i -> i).sum()) % 2 == 0 ? integers.stream().filter(i -> i % 2 == 0).collect(Collectors.toList()) : integers.stream().filter(i -> i % 2 != 0).collect(Collectors.toList());
    }
}
