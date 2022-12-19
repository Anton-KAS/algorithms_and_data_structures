package kas.anton.tasks.internship_spring_2019;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Anton Komrachkov
 * @since (19.12.2022)
 */

/*
Стажировка весна 2019. Задача 3
Полное условие задачи в doc/T03.jpeg
 */

public class T03 {
    public static void main(String[] args) {
        int w, h, m, N;
        LocalTime localTime;
        Map<Integer, List<LocalTime>> shed = new HashMap<>();
        try (Scanner scanner = new Scanner(System.in)) {
            w = scanner.nextInt();
            h = scanner.nextInt();
            m = scanner.nextInt();
            localTime = LocalTime.of(h, m);
            N = scanner.nextInt();
            for (int i = 0; i < N; i++) {
                int lw = scanner.nextInt();
                int lh = scanner.nextInt();
                int lm = scanner.nextInt();
                if (lw != 0) {
                    List<LocalTime> shedTime = shed.computeIfAbsent(lw, k -> new ArrayList<>());
                    shedTime.add(LocalTime.of(lh, lm));
                } else {
                    for (int j = 1; j < 8; j++) {
                        List<LocalTime> shedTime = shed.computeIfAbsent(j, k -> new ArrayList<>());
                        shedTime.add(LocalTime.of(lh, lm));
                    }
                }
            }
        }
        LocalTime result = localTime;
        while (true) {
            List<LocalTime> shedTime = shed.get(w);
            if (shedTime == null || shedTime.size() == 0) {
                w++;
                if (w > 7) w = 1;
                localTime = LocalTime.of(0, 0);
                continue;
            }
            LocalTime finalLocalTime = localTime;
            List<LocalTime> filtT = shedTime.stream().filter(t -> t.equals(finalLocalTime) || t.isAfter(finalLocalTime)).collect(Collectors.toList());
            if (filtT.size() == 0) {
                w++;
                if (w > 7) w = 1;
                localTime = LocalTime.of(0, 0);
                continue;
            } else {
                result = filtT.stream().min(LocalTime::compareTo).orElse(result);
            }
            break;
        }
        System.out.printf("%s %s %s%n", w, result.getHour(), result.getMinute());
    }
}
