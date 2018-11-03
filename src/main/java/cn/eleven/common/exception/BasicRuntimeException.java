package cn.eleven.common.exception;

import lombok.Data;

/**
 * @author: eleven
 * @date: 2018/4/7
 * @description:
 */
@Data
public class BasicRuntimeException extends RuntimeException{

    protected static final String NEWLINE = "\r\n";
    /**
     * 发生异常时自定义的code
     */
    private ErrCodeConstant errCode;


    public BasicRuntimeException(ErrCodeConstant errorCode) {
        this.errCode = errorCode;
    }

    public BasicRuntimeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {

        if (this.errCode != null){
            return this.errCode.toString()+NEWLINE+super.getMessage();
        }

        return super.getMessage();
    }
}
