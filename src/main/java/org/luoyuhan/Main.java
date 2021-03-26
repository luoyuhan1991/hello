package org.luoyuhan;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> a = IntStream.generate(() -> 0).limit(3).boxed().collect(Collectors.toList());
        a.add(0, 1);
        System.out.println(a);

        List<BigDecimal> collect = Arrays.stream(",,,1".split(",")).map(
                i -> StringUtils.isBlank(i) ? new BigDecimal("0") : new BigDecimal(i)
        ).collect(Collectors.toList());
        System.out.println(collect);
    }
}