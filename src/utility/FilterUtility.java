package utility;

import static utility.Constants.OPTION_FULL_STATISTICS;
import static utility.Constants.OPTION_STATISTICS;
import static utility.FileResponder.*;
import static utility.OptionResponder.*;
import static utility.StatisticsResponder.printFullStatistics;
import static utility.StatisticsResponder.printStatistics;

//Основной класс утилиты
public final class FilterUtility {

    public static void main(String[] args) {
        //Перебор передаваемых аргументов
        for (int i = 0; i < args.length; i++) {
            //Если аргумент содержит ".", то это имя файла
            if (args[i].contains(".")){
                //Проверка формата файла и его существования
                validateFile(args[i]);
            }
            else{
                //Если аргумент не содержит ".", то это опция
                //Проверяем название опции и передаваемые значения для опции при необходимости
                if(i + 1 < args.length)
                    i += validateOption(args[i], args[i + 1]);
                else {
                    i += validateOption(args[i], null);
                }
            }
        }
        //Считываем данные из входных файлов и записываем в выходные
        readAndRewriteData();
        //Если указаны опции, то выводим статистики
        if(getOptions().contains(OPTION_STATISTICS))
            printStatistics();
        if(getOptions().contains(OPTION_FULL_STATISTICS))
            printFullStatistics();
    }

}