package cn.eleven.common.utils;

import cn.eleven.common.except.BasicRuntimeException;
import cn.eleven.common.except.ErrCodeConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;

/**
 * @author: eleven
 * @date: 2018/9/8 15:11
 * @description: 参数常规检查工具
 */
public class CheckUtls {

    /**
     * 字符串非空检查
     * @param str
     * @param errMsg
     */
    public void notBlank(String str,String errMsg){
        if (StringUtils.isBlank(str))
            throw new BasicRuntimeException(errMsg);
    }
    /**
     * 字符串非空检查
     * @param str
     * @param errCode
     */
    public void notBlank(String str, ErrCodeConstant errCode){
        if (StringUtils.isBlank(str))
            throw new BasicRuntimeException(errCode);
    }

    /**
     * 检查是否存在空字符串
     * @param arr
     * @return
     */
    public boolean existBlankStr(String... arr){
        if (ArrayUtils.isEmpty(arr))
            return false;
        for (String s : arr) {
            if (StringUtils.isBlank(s))
                return true;
        }
        return false;
    }

    /**
     * 检查是否是空的字符串数组或者数组元素是否全部为空
     * @param arr
     * @return
     */
    public boolean isBlankStrArr(String... arr){
        if (ArrayUtils.isEmpty(arr))
            return true;
        for (String s : arr) {
            if (StringUtils.isNotBlank(s))
                return false;
        }
        return true;
    }

    /**
     * 检查是否是空的字符串数组或者数组元素是否全部为空并决定是否抛出异常
     * @param msg
     * @param arr
     */
    public void isBlankStrArr(String msg,String... arr){
        if (isBlankStrArr(arr))
            throw new BasicRuntimeException(msg);
    }
    /**
     * 检查是否是空的字符串数组或者数组元素是否全部为空并决定是否抛出异常
     * @param errCode
     * @param arr
     */
    public void isBlankStrArr(ErrCodeConstant errCode,String... arr){
        if (isBlankStrArr(arr))
            throw new BasicRuntimeException(errCode);
    }

    /**
     * 集合不为空检查
     * @param coll
     * @param errMsg
     */
    public void noeEmpty(Collection coll,String errMsg){
        if (CollectionUtils.isEmpty(coll))
            throw new BasicRuntimeException(errMsg);
    }
    /**
     * 集合不为空检查
     * @param coll
     * @param errCode
     */
    public void noeEmpty(Collection coll,ErrCodeConstant errCode){
        if (CollectionUtils.isEmpty(coll))
            throw new BasicRuntimeException(errCode);
    }
}
