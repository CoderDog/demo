/**
 * @program: demo
 * @description:
 * @author: KaiTao.wu
 * @create: 2018-10-23 14:12
 **/
package com.example.runner;

import com.example.demo.DemoApplication;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryForever;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//@Component
//@SpringBootApplication
public class ZookeeperRunner implements CommandLineRunner {
    private final static Logger logger = LoggerFactory.getLogger(ZookeeperRunner.class);




    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(ZookeeperRunner.class, args);
        //disabled banner, don't want to see the spring logo
//		SpringApplication app = new SpringApplication(DemoApplication.class);
//		app.setBannerMode(Banner.Mode.OFF);
//		app.run(args);

        logger.info(configurableApplicationContext.toString());
//		Assert.hasText("","hehe");
    }
    @Override
    public void run(String... args) throws Exception {
        for(String a:args){
            logger.debug(a);
        }
        CuratorFramework curator = CuratorFrameworkFactory.newClient("192.168.245.128:2181,192.168.9.134:2181",
                5000, 3000, new RetryForever(1000));
        String regContent = "kkkk";
        String zkRegPathPrefix = "/test/service-provider-";

        curator.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                logger.info("state change：{}",connectionState.name());
                if(ConnectionState.CONNECTED == connectionState || ConnectionState.RECONNECTED == connectionState){
                    try {
                        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                                .forPath(zkRegPathPrefix, regContent.getBytes("UTF-8"));
                    } catch (Exception e) {
                        logger.error("",e);
                    }
                }
            }


        });
        curator.blockUntilConnected();

//        curator.getCuratorListenable().addListener(new CuratorListener() {
//            @Override
//            public void eventReceived(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
//                logger.info(curatorEvent.getWatchedEvent().toString());
//            }
//        });
        curator.close();
        logger.info("finish");
//        System.exit(0);
    }
}
