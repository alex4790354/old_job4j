package ru.job4j.carsAdds;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution1 {

    public static List<String> prioritizedOrders(int size, List<String> orders) {
        List<String> sorted = new ArrayList<>();
        List<String> unorders = new ArrayList<>();
        for (String order : orders) {
            String[] line = order.substring(1, order.length() - 1).split(" ");
            if (line[1].matches("\\d+")) {
                unorders.add(order);
            } else {
                sorted.add(order);
            }
        }
        sorted.sort(new Comparator<String>() {
            @Override
            public int compare(String left, String right) {
                String[] lineLeft = left.substring(1, left.length() - 1).split(" ");
                String[] lineRight = right.substring(1, right.length() - 1).split(" ");
                int rsl = lineLeft[1].compareTo(lineRight[1]);
                if (rsl == 0) {
                    if (lineLeft[0].matches("\\d+") && lineRight[0].matches("\\d+")) {
                        rsl = Integer.valueOf(lineLeft[0]).compareTo(Integer.valueOf(lineRight[0]));
                    } else if (lineLeft[0].matches("\\d+")) {
                        rsl = -1;
                    } else if (lineRight[0].matches("\\d+")) {
                        rsl = 1;
                    } else {
                        rsl = lineLeft[0].compareTo(lineRight[0]);
                    }
                }
                return rsl;
            }
        });
        sorted.addAll(unorders);
        return sorted;
    }

}
