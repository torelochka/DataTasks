/*
Идея сосчитать для каждого товара сколько раз он встречался в момент чтения transaction.csv.
По полученным данным составить карту: ключ – код товара, значение –количество встреч товара в корзинах.
Исходя из количества можно понять покупательскую способность этого товара. Отсортируем полученные значения
по убывания и будем добавлять в итоговую карту пока количество всех остальных товаров составляет
более 75% от общего количества товаров.

Я понимаю, что идея не совсем верная и правильно это делается иначе, но на данный момент
знаний, чтобы реализовать это так как нужно, у меня нет… их я хочу получить от данного курса.
 */

import java.io.*;
import java.util.*;

public class Task2 {
    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, Integer> res = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("res.csv"))) {
            reader.readLine();

            writer.write("PROD_CODE;COUNT\n");

            final int[] sum = {0};
            while (reader.ready()) {
                String[] line = reader.readLine().split(";");
                if (map.containsKey(line[0])) map.put(line[0], map.get(line[0]) + 1);
                else map.put(line[0], 1);
                sum[0] += 1;
            }

            final double[] procent = {100};
            map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach((x) -> {
                if (procent[0] >= 75) {
                    procent[0] -= (x.getValue() * 100.) / sum[0];
                    res.put(x.getKey(), x.getValue());
                }
            });

            res.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.naturalOrder())).forEach((x) -> {
                try {
                    writer.write(x.getKey() + ";" + x.getValue() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


