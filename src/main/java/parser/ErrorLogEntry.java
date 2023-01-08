package parser;
import java.util.List;


public class ErrorLogEntry {
    private User user;
    private String methodName;
    private String errorType;
    private List<Object> inputValues;

    public ErrorLogEntry(User user, String methodName, String errorType, List<Object> inputValues) {
        this.user = user;
        this.methodName = methodName;
        this.errorType = errorType;
        this.inputValues = inputValues;
    }

    public User getUser() {
        return user;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getErrorType() {
        return errorType;
    }

    public List<Object> getInputValues() {
        return inputValues;
    }
}
