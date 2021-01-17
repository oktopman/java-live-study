package me.oktop.javastudy.week9;

public class CustomException extends RuntimeException {

    private final int code;

    public CustomException(String message, int code) { // 생성자
        super(message);
        this.code = code;
    }

    public CustomException(String msg) {
        this(msg, 100);
    }

    public int getCode() {
        return this.code;
    }

}
