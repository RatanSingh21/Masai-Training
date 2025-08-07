public enum StatusCode {

    SUCCESS(400),
    ERROR(500),
    NOT_FOUND(404);

    private final int code;

    // Constructor
    StatusCode(int code) {
        this.code = code;
    }

    //Getter
    public int getCode(){
        return code;
    }
}
