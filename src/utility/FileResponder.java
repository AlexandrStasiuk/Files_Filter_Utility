package utility;

import exceptions.WrongFileFormatException;

import java.io.*;
import java.text.MessageFormat;
import java.util.LinkedHashSet;
import java.util.Set;

import static utility.Constants.*;
import static utility.OptionResponder.*;
import static utility.StatisticsResponder.*;
//Класс для работы с файлами
public final class FileResponder {
    //Список файлов для чтения
    private final static Set<BufferedReader> files = new LinkedHashSet<>();
    //Функция валидации файлов
    public static void validateFile(String fileName) {
        try {
            String[] fileNameParts = fileName.split("\\.");
            //Проверка формата файла
            if (fileNameParts.length < 2 || !fileNameParts[fileNameParts.length - 1].equals("txt")) {
                String errorMassage = fileNameParts.length < 1
                        ? MessageFormat.format(
                        "Invalid file format. The file ''{0}'' must be in txt format",
                        "NaN"
                )
                        : MessageFormat.format(
                        "Invalid file format. The file ''{0}'' must be in txt format",
                        fileNameParts[0]
                );
                throw new WrongFileFormatException(errorMassage);
            }
            try {
                //Проверка существования файла и его открытие
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                files.add(reader);
            } catch (FileNotFoundException e) {
                System.err.println("Error opening the file: The file " + fileName + " was not found");
            }
        } catch (WrongFileFormatException error) {
            System.err.println("Error opening the file: " + error.getMessage());
        }
    }
    //Функция чтения данных из входных файлов и записи в выходные
    public static void readAndRewriteData() {

        //Выходные файлы
        BufferedWriter writerIntegers = null;
        BufferedWriter writerFloats = null;
        BufferedWriter writerStrings = null;

        try {
            //Проверка, что все данные из файлов записаны
            boolean isAllFilesWritten = false;
            while(!isAllFilesWritten){
                isAllFilesWritten = true;
                //Построчное чтение из каждого файла
                for (BufferedReader file: files){
                    String line = file.readLine();
                    if(line != null){
                        //Получение типа данных
                        String typeData = checkTypeData(line);
                        //Выходные файлы создаются и открываются только при первом появлении соответсвующего типа
                        if (typeData.equals(INTEGER_TYPE) && writerIntegers == null)
                            writerIntegers = new BufferedWriter(new FileWriter(
                                    getFullOutputFilePath(FILE_NAME_INTEGERS),
                                    getOptions().contains(OPTION_ADD)
                            ));
                        if (typeData.equals(FLOAT_TYPE) && writerFloats == null)
                            writerFloats = new BufferedWriter(new FileWriter(
                                    getFullOutputFilePath(FILE_NAME_FLOATS),
                                    getOptions().contains(OPTION_ADD)
                            ));
                        if (typeData.equals(STRING_TYPE) && writerStrings == null)
                            writerStrings = new BufferedWriter(new FileWriter(
                                    getFullOutputFilePath(FILE_NAME_STRINGS),
                                    getOptions().contains(OPTION_ADD)
                            ));
                        //Запись в выходные файлы
                        writeInOutputFiles(line, typeData, writerIntegers, writerFloats, writerStrings);
                        isAllFilesWritten = false;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error writing data to output files: The file data could not be written to the specified path");
        }catch (IOException e) {
            System.err.println("Error reading data from the input file: " + e.getMessage());
        }finally {
            //Закрытие выходных файлов
            try {
                if (writerIntegers != null)
                    writerIntegers.close();
                if (writerFloats != null)
                    writerFloats.close();
                if (writerStrings != null)
                    writerStrings.close();
            } catch (IOException e) {
                System.err.println("Error closing output files: " + e.getMessage());
            }
            //Закрытие входных файлов
            for (BufferedReader file : files) {
                try {
                    file.close();
                } catch (IOException e) {
                    System.err.println("Error closing input file: " + e.getMessage());
                }
            }
        }
    }
    //Функция для проверки типа данных
    private static String checkTypeData(String line) {
        try {
            Long.parseLong(line);
            return INTEGER_TYPE;
        }catch (NumberFormatException e){
            try {
                Double.parseDouble(line);
                return FLOAT_TYPE;
            }catch (NumberFormatException ex){
                return STRING_TYPE;
            }
        }
    }
    //Функция для записи в выходные файлы
    private static void writeInOutputFiles(
            String line,
            String typeData,
            BufferedWriter writerIntegers,
            BufferedWriter writerFloats,
            BufferedWriter writerStrings
    ){
        try{
            //Запись в файл в соответсвии с типом
            switch (typeData) {
                case INTEGER_TYPE:
                    if (writerIntegers != null) {
                        writerIntegers.write(line);
                        writerIntegers.newLine();
                        writerIntegers.flush();
                        addInteger(line);
                    }
                    break;
                case FLOAT_TYPE:
                    if (writerFloats != null) {
                        writerFloats.write(line);
                        writerFloats.newLine();
                        writerFloats.flush();
                        addFloat(line);
                    }
                    break;
                case STRING_TYPE:
                    if (writerStrings != null) {
                        writerStrings.write(line);
                        writerStrings.newLine();
                        writerStrings.flush();
                        addString(line);
                    }
                    break;
            }
        } catch (IOException e) {
            System.err.println("Error writing data to output files: " + e.getMessage());
        }

    }

}
