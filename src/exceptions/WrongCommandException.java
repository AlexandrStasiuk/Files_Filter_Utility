package exceptions;
//Класс исключения, если неверная команда
public final class WrongCommandException extends Exception {
    public WrongCommandException() {
    }

    public WrongCommandException(String message) {
        super(message);
    }

    public WrongCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongCommandException(Throwable cause) {
        super(cause);
    }

    public WrongCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
