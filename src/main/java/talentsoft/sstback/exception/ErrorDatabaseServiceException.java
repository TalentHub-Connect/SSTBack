package talentsoft.sstback.exception;

import lombok.Getter;

@Getter
public class ErrorDatabaseServiceException extends Exception{
    private final int statusCode;
    public ErrorDatabaseServiceException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
