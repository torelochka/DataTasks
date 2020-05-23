/*
���� ��������� ��� ������� ������ ������� ��� �� ���������� � ������ ������ transaction.csv.
�� ���������� ������ ��������� �����: ���� � ��� ������, �������� ����������� ������ ������ � ��������.
������ �� ���������� ����� ������ �������������� ����������� ����� ������. ����������� ���������� ��������
�� �������� � ����� ��������� � �������� ����� ���� ���������� ���� ��������� ������� ����������
����� 75% �� ������ ���������� �������.

� �������, ��� ���� �� ������ ������ � ��������� ��� �������� �����, �� �� ������ ������
������, ����� ����������� ��� ��� ��� �����, � ���� ��� �� � ���� �������� �� ������� �����.
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


