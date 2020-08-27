package cn.edu.ahu.carmanagement.Utils;

public class NotFoundException extends ApplicationException {
    public NotFoundException() {
        super("资源不存在");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
