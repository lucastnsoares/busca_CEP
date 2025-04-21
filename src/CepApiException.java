public class CepApiException extends RuntimeException {
    String message;
    public CepApiException(String message) {
       this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
