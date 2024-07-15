package utility;

import java.text.MessageFormat;
import java.util.*;
//Класс для работы со статистикой
public final class StatisticsResponder {
    //Список целых чисел
    private static final List<Long> integersList = new ArrayList<>();
    //Список вещественных чисел
    private static final List<Double> floatsList = new ArrayList<>();
    //Список строк
    private static final List<String> stringsList = new ArrayList<>();

    public static void addInteger(String integer){
        integersList.add(Long.parseLong(integer));
    }
    public static void addFloat(String fl){
        floatsList.add(Double.parseDouble(fl));
    }
    public static void addString(String str){
        stringsList.add(str);
    }
    //Вывод краткой статистики
    public static void printStatistics(){
        String statistics = MessageFormat.format(
                "\n\t\tIntegers written: {0}\n\t\tFloats written: {1}\n\t\tStrings written: {2}\n",
                integersList.size(),
                floatsList.size(),
                stringsList.size()
        );
        System.out.println(statistics);
    }
    //Вывод полной статистики
    public static void printFullStatistics(){
        //Расчет максимального и минимального значений целых чисел
        String minInt = integersList.isEmpty() ? "0" : Collections.min(integersList).toString();
        String maxInt = integersList.isEmpty() ? "0" : Collections.max(integersList).toString();
        //Расчет среднего значения целых чисел
        OptionalDouble averageInt = integersList.stream().mapToLong(Long::longValue).average();
        String averageIntToStr = averageInt.isPresent() ? averageInt.getAsDouble() + "" : "0";
        //Расчет максимального и минимального значений вещественных чисел
        String minFloat = floatsList.isEmpty() ? "0" : Collections.min(floatsList).toString();
        String maxFloat = floatsList.isEmpty() ? "0" : Collections.max(floatsList).toString();
        //Расчет среднего значения вещественных чисел
        OptionalDouble averageFloat = floatsList.stream().mapToDouble(Double::doubleValue).average();
        String averageFloatToStr = averageFloat.isPresent() ? averageFloat.getAsDouble() + "" : "0";
        //Расчет минимальной длины строки
        Optional<String> minStr = stringsList.stream().min(Comparator.comparing(String::length));
        Integer minStrLength = minStr.map(String::length).orElse(0);
        //Расчет максимальной длины строки
        Optional<String> maxStr = stringsList.stream().max(Comparator.comparing(String::length));
        Integer maxStrLength = maxStr.map(String::length).orElse(0);

        String statistics = MessageFormat.format(
                "\n\t\tIntegers written: {0} | Min: {1} | Max: {2} | Sum: {3} | Average: {4}" +
                        "\n\t\tFloats written: {5} | Min: {6} | Max: {7} | Sum: {8} | Average: {9}" +
                        "\n\t\tStrings written: {10} | Min length: {11} | Max length: {12}\n",
                integersList.size(),
                minInt,
                maxInt,
                integersList.stream().mapToDouble(Long::doubleValue).sum() + "",
                averageIntToStr,
                floatsList.size(),
                minFloat,
                maxFloat,
                floatsList.stream().mapToDouble(Double::doubleValue).sum() + "",
                averageFloatToStr,
                stringsList.size(),
                minStrLength,
                maxStrLength
        );
        System.out.println(statistics);
    }

}
