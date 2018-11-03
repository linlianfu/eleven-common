package cn.eleven.common.exception;

import lombok.Data;

/**
 * @author: eleven
 * @date: 2018/4/7
 * @description:
 */
@Data
public class ErrCodeConstant {

    protected static final String NEWLINE = "\r\n";
    /**
     * 异常code
     */
    private String code;
    /**
     * 异常消息
     */
    private String message;


    protected ErrCodeConstant(String code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString()
    {
        StringBuffer sbf = new StringBuffer("{Code:");
        sbf.append(this.code);
        sbf.append(",");
        sbf.append("message:");
        sbf.append(this.message);
        sbf.append("}"+NEWLINE);
        return sbf.toString();
    }
}
