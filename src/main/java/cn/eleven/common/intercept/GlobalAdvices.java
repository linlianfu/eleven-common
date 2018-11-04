package cn.eleven.common.intercept;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eleven
 * @date 2018/11/4
 * @description
 */
@Slf4j
public class GlobalAdvices {

    public void before(){
        log.warn(">>>>>前置通知：before");
    }

    public void after(){
        log.info(">>>>>后置通知：after");
    }
}
