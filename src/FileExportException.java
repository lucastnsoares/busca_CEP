public class FileExportException extends RuntimeException {
    String message;
    public FileExportException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
