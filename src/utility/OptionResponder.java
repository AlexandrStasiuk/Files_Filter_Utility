package utility;

import exceptions.EmptyOptionBodyException;
import exceptions.InvalidDataException;
import exceptions.WrongCommandException;

import java.text.MessageFormat;
import java.util.LinkedHashSet;
import java.util.Set;

import static utility.Constants.*;
//Класс для работы с опциями
public final class OptionResponder {
    //Указанные опции
    private final static Set<String> options = new LinkedHashSet<>();
    //Путь для выходных файлов
    private static String outPath = "";
    //Перфикс для выходных файлов
    private static String prefix = "";

    public static Set<String> getOptions(){
        return options;
    }

    public static String getOutPath() {
        return outPath;
    }

    public static String getPrefix() {
        return prefix;
    }
    //Функция для валидации опций
    public static int validateOption(String option, String optionBody){
        try {
            //Есть ли указанныя опция в списке опций для утилиты
            if(!OPTIONS_LIST.contains(option)){
                //Если нет, то вывести ошибку
                String errorMassage = MessageFormat.format("Unknown command: ''{0}''", option);
                throw new WrongCommandException(errorMassage);
            }
            //Добавление опции в список указанных опций
            options.add(option);
            return saveOptionBody(option, optionBody);
        }catch (WrongCommandException e){
            System.err.println("Error reading the command: " + e.getMessage());
        }
        return 0;
    }
    //Функция для сохранения значений префикса и выходного пути
    private static int saveOptionBody(String option, String optionBody){
        try {
            switch (option){
                case OPTION_OUTPUT_PATH:
                    //Проверка на наличия значения пути
                    if(optionBody == null || OPTIONS_LIST.contains(optionBody) || optionBody.contains("."))
                        throw new EmptyOptionBodyException("The error of the '-o' option. You must specify the path for the results");
                    //Преобразование значения пути к корректному виду
                    String[] outPathParts = optionBody.split("[\\\\/]+");
                    outPath = String.join("\\\\", outPathParts);
                    //Следующий аргумент не рассматривать, т. к. это значение пути
                    return 1;
                case OPTION_PREFIX:
                    //Проверка на наличие значения префикса
                    if(optionBody == null || OPTIONS_LIST.contains(optionBody) || optionBody.contains("."))
                        throw new EmptyOptionBodyException("The error of the '-p' option. You must specify the prefix");
                    //Проверка корректности префикса
                    if(optionBody.contains("/") || optionBody.contains("\\"))
                        throw new InvalidDataException("Invalid prefix is specified");
                    prefix = optionBody;
                    //Следующий аргумент не рассматривать, т. к. это значение префикса
                    return 1;
            }
        } catch (EmptyOptionBodyException | InvalidDataException e) {
            System.err.println("Error reading the option: " + e.getMessage());
        }
        return 0;
    }
    //Функция для получения полного пути выходного файла
    public static String getFullOutputFilePath(String fileName){
        String withPath = getOutPath().isEmpty() ? "" : getOutPath() + "\\\\";
        return withPath + getPrefix() + fileName;
    }
}
