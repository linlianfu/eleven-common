package cn.eleven.common.zookeeper;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * @author eleven
 * @date 2019/3/16
 * @description zookeeper分布式锁配置
 *
 *
 * zk节点创建参考文章：https://www.cnblogs.com/qingyunzong/p/8666288.html
 */
@Slf4j
public class CuratorFrameworkFactoryBean implements FactoryBean<CuratorFramework> ,InitializingBean,DisposableBean{


    private CuratorFramework client;

    /**
     *zk连接地址
     */
    @Setter
    List<String> zkAddressList;

    /**
     * zk节点的民命名空间
     */
    @Setter
    String namespace;


    @Override
    public void afterPropertiesSet() throws Exception {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        client = CuratorFrameworkFactory.builder()
                .connectString(StringUtils.join(zkAddressList,","))
                .retryPolicy(retryPolicy)
                .namespace(namespace)
                .build();
        client.start();
        log.info("分布式锁CuratorFramework实例化成功且启动zk节点创建");
    }


    @Override
    public void destroy() throws Exception {

    }

    @Override
    public CuratorFramework getObject() throws Exception {
        return client;
    }

    @Override
    public Class<?> getObjectType() {
        return CuratorFramework.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
