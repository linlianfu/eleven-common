package cn.eleven.common.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.Collections;
import java.util.List;

/**
 * @author: eleven
 * @date: 2018/4/18 22:21
 * @description:
 */
public class ModelMapperUtils {

    public static final ModelMapper mapper=new ModelMapper();


    public <T>List<T> mapList(Collections collections, Class<T> entitClass){
        return mapper.map(collections,new TypeToken<List<T>>(){}.getType());
    }

}
