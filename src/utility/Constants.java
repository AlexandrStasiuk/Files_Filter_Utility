package utility;

import java.util.Set;
//Класс с постоянными переменными
public final class Constants {
    //Типы данных в фиде строк
    public static final String STRING_TYPE = "String";
    public static final String FLOAT_TYPE = "Float";
    public static final String INTEGER_TYPE = "Integer";
    //Названия выходных файлов
    public static final String FILE_NAME_INTEGERS = "integers.txt";
    public static final String FILE_NAME_FLOATS = "floats.txt";
    public static final String FILE_NAME_STRINGS = "strings.txt";
    //Опции
    public static final String OPTION_OUTPUT_PATH = "-o";
    public static final String OPTION_PREFIX = "-p";
    public static final String OPTION_ADD= "-a";
    public static final String OPTION_STATISTICS= "-s";
    public static final String OPTION_FULL_STATISTICS= "-f";
    //Список опций
    public static final Set<String> OPTIONS_LIST = Set.of(
            OPTION_ADD,
            OPTION_STATISTICS,
            OPTION_PREFIX,
            OPTION_OUTPUT_PATH,
            OPTION_FULL_STATISTICS
    );
}
