package cn.eleven.common.zookeeper;

import cn.eleven.common.utils.CheckUtls;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * @author eleven
 * @date 2019/3/16
 * @description 获取指定节点的锁的工厂类
 *
 * 验证方式
 *   1.方法内部指定代码块获取锁
 *   2.锁的范围内打上断点，包括平台和能力服务
 *   3.此时发起N个请求指向改同步代码快
 *   4.可以看到日志按先获得锁的请求先输出日志，且必须等待整个代码快执行完毕才有下一个请求的相关日志输出，
 *   严格遵守请求的先后顺序
 *
 * 反验证方式
 *  1.不加锁
 *  2.基础数据服务加上断点
 *  3.发起N个请求
 *  4.日志输出顺序是无序的，与请求的先后顺序无关
 */


@Slf4j
public class LockFactory {


    @Setter
    private CuratorFramework client;



    /**
     * 获取指定节点的锁,
     * 通过{@link InterProcessMutex#acquire()}
     * 创建出来的节点类型为{@link org.apache.zookeeper.CreateMode#EPHEMERAL_SEQUENTIAL}
     * 即：客户端一旦断开连接，节点将自动删除
     * @return
     */
    public InterProcessMutex getInterProcessMutex(String node){

        CheckUtls.notNull(client,"zk分布式锁客户端CuratorFramework未实例化?");
        CheckUtls.notBlank(node,"锁节点不能为空");

        return new InterProcessMutex(client, "/curator/lock"+"/"+node);
    }
}
