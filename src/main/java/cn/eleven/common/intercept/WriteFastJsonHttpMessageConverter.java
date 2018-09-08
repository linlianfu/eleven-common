package cn.eleven.common.intercept;

import cn.eleven.common.dao.Page;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: eleven
 * @date: 2018/4/7 17:12
 * @description:
 */
public class WriteFastJsonHttpMessageConverter extends FastJsonHttpMessageConverter {


    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream =  outputMessage.getBody();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",true);
        jsonObject.put("code","200");

        if (obj instanceof Page){
            Page page = (Page)obj;
            jsonObject.put("info",page.getCurrentPageData());
            jsonObject.put("totalPageSize",page.getTotalPageSize());
            jsonObject.put("totalSize",page.getTotalSize());
        }else {
            jsonObject.put("info",obj);
        }


        String test = JSON.toJSONString(jsonObject,getFeatures());
        byte[] bytes =test.getBytes(getCharset());
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }
}
