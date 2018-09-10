package cn.eleven.common.dao;


import cn.eleven.common.bean.superbean.Dao;
import com.mongodb.BasicDBObject;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.validation.annotation.Validated;
/**
 * @author: eleven
 * @date: 2017/10/21
 * @Description: 默认的集合名为类名首字母小写，所以当不传集合名字时，则类名必须和数据库集合名一致
 */
@Validated
public interface Mongo extends Dao {
    /**
     * 新增一个实体
     * @param entity
     */
    void save(Object entity);

    /**
     * 根据id查询实体
     * @param id
     * @return
     */
    Object findById(Object id);
    /**
     * 根据id查询实体
     * @param query
     * @return
     */
    Object findByQuery(Query query);

    /**
     * 分页获取数据
     * @param page
     * @param query
     * @return
     */
    Page page( Page page, Query query);

    /**
     * 更新一个实体
     * 若ID无效（id空或者不存在的id）：新增一个实体
     * 若ID有效：更新一个实体
     * @param entity
     */
    void update(Object entity);

    /**
     * 统计
     * @param query
     * @return
     */
    long countByQuery(Query query);

    /**
     * 根据id删除数据
     * @param id
     */
    void deleteById( String id);

    /**
     * 为符合条件的数据的内嵌数组追加一个元素
     * @param query
     * @param fieldName
     * @param object
     * @return
     */
    int addElement(Query query, String fieldName, Object object);
    /**
     * 为符合条件的数据的内嵌数组删除一个元素
     * @param query
     * @param fieldName
     * @param dbObject
     * @return
     */
    int deleteElement(Query query, String fieldName, BasicDBObject dbObject);
}
