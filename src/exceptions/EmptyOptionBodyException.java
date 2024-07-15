package exceptions;
//Класс исключения, если не указано значения для опции
public final class EmptyOptionBodyException extends Exception{
    public EmptyOptionBodyException() {
    }

    public EmptyOptionBodyException(String message) {
        super(message);
    }

    public EmptyOptionBodyException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyOptionBodyException(Throwable cause) {
        super(cause);
    }

    public EmptyOptionBodyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
