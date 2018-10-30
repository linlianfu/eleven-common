package cn.eleven.common.dao;

import com.mongodb.BasicDBObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.BasicMongoPersistentEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author: eleven
 * @date: 2017/10/21
 * @Description: 实体对象和mongo数据库集合映射关系
 * 1.如果实体对象类头有{@link org.springframework.data.mongodb.core.mapping.Document}注解，则使用注解配置的集合映射；
 * 2.如果实体对象类头不存在注解，则默认为实体对象类的首字母小写进行映射；
 * 可查看源码：{@link BasicMongoPersistentEntity#BasicMongoPersistentEntity(org.springframework.data.util.TypeInformation)}
 */
@Slf4j
public class MDSpringDaoTemplate<T extends Serializable>  implements Mongo,InitializingBean{
    /**
     * mongo数据的操作对象
     * 依赖注入,每个继承MDSpringDaoTemplate的类实例化后，调用set从spring IOC容器取该对象的实例注入
     */
    @Autowired
    @Getter
    MongoTemplate mt;

    private Class<T> entityClass;

    @Override
    public void afterPropertiesSet() throws Exception {

        this.initEntityClassBean();//初始化实体bean

    }

    private void initEntityClassBean(){
        entityClass =  (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    @Override
    public void save(Object entity) {
        this.mt.insert(entity);
    }

    @Override
    public Object findById(Object id) {
        return this.mt.findById(id,entityClass);
    }

    @Override
    public Page page(Page page, Query query) {
        if (page.getPageNo() <1){
            throw new RuntimeException("页码必须大于1");
        }
        if (page.getPageSize() < 1){
            throw new RuntimeException("每页获取的条数必须大于1");
        }
        long totalSize = this.countByQuery(query);
        double tempTotalPageSize = (double) totalSize/page.getPageSize();
        int totalPageSize = (int) tempTotalPageSize;
        if (totalPageSize < tempTotalPageSize){
            ++totalPageSize;
        }
        page.setTotalSize(totalSize);
        page.setTotalPageSize(totalPageSize);
        if (page.getPageNo() > totalPageSize){
            return  page;
        }
        query.skip((page.getPageNo()-1)*page.getPageSize());
        query.limit(page.getPageSize());
        List<T> resultList = this.getMt().find(query,entityClass);
        page.setCurrentPageStartIndex(query.getSkip()+1);
        page.setCurrentPageData(resultList);
        return page;
    }

    @Override
    public void update(Object entity) {
        this.mt.save(entity);
    }

    @Override
    public Object findByQuery(Query  query) {

        return this.mt.find(query,entityClass);
    }
    @Override
    public long countByQuery(Query query) {
        return this.getMt().count(query,entityClass);
    }

    @Override
    public void deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        this.getMt().remove(query,entityClass);
    }

    @Override
    public int addElement(Query query, String fieldName, Object object) {

        Update update = new Update();
        update.addToSet(fieldName,object);
        int result = this.getMt().upsert(query,update,entityClass).getN();
        return result;
    }

    @Override
    public int deleteElement(Query query, String fieldName, BasicDBObject dbObject) {
        Update update = new Update();
        update.pull(fieldName,dbObject);
        int result = this.getMt().updateFirst(query,update,entityClass).getN();
        return result;
    }
}
