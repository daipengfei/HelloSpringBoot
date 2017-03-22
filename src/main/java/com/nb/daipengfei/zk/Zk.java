package com.nb.daipengfei.zk;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/****************************************
 * Created by daipengfei on 17/2/17.****
 ***************************************/

public class Zk {
    private CountDownLatch latch = new CountDownLatch(1);

    private ZooKeeper zk = null;

    @Test
    public void testCreate() throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, watchedEvent -> {
            System.out.println("event1 : " + watchedEvent);
            if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                latch.countDown();
            }
        });
        latch.await();
        final long sessionId = zooKeeper.getSessionId();
        final byte[] sessionPasswd = zooKeeper.getSessionPasswd();
        System.out.println(sessionId + " : " + new String(sessionPasswd));
        zooKeeper = new ZooKeeper("localhost:2181", 5000, watchedEvent -> {
            System.out.println("event2 : " + watchedEvent);
            if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                latch.countDown();
            }
        }, 1L, "test".getBytes());
        zooKeeper = new ZooKeeper("localhost:2181", 5000, watchedEvent -> {
            System.out.println("event3 : " + watchedEvent);
            if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                latch.countDown();
            }
        }, sessionId, sessionPasswd);
        Thread.sleep(Integer.MAX_VALUE);
    }


    @Test
    public void testCreateNode() throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, watchedEvent -> {
            System.out.println("event1 : " + watchedEvent);
            if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                latch.countDown();
            }
        });
        latch.await();
        final String path = zooKeeper.create("/zk-test-ephemeral-", "hi".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(path);
        final String path2 = zooKeeper.create("/zk-test-ephemeral-", "hi".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(path2);

    }

    @Test
    public void testCreateNodeAsync() throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, watchedEvent -> {
            System.out.println("event1 : " + watchedEvent);
            if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                latch.countDown();
            }
        });
        latch.await();
        zooKeeper.create("/zk-test-ephemeral-", "hi".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new AsyncCallback.StringCallback() {
                    @Override
                    public void processResult(int rc, String path, Object ctx, String name) {
                        System.out.println("rc =" + rc + path + ctx + name);
                    }
                }, "Context!");
        zooKeeper.create("/zk-test-ephemeral-", "hi".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new AsyncCallback.StringCallback() {
                    @Override
                    public void processResult(int rc, String path, Object ctx, String name) {
                        System.out.println("rc =" + rc + path + ctx + name);
                    }
                }, "Context!");
        zooKeeper.create("/zk-test-ephemeral-", "hi".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, new AsyncCallback.StringCallback() {
                    @Override
                    public void processResult(int rc, String path, Object ctx, String name) {
                        System.out.println("rc =" + rc + path + ctx + name);
                    }
                }, "Context!");
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testGetChildren() throws Exception {
        zk = new ZooKeeper("localhost:2181", 5000, watchedEvent -> {
            if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                if (Watcher.Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                    latch.countDown();
                } else if (watchedEvent.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                    try {
                        System.out.println(zk.getChildren(watchedEvent.getPath(), true));
                    } catch (Exception e) {

                    }
                }
            }
        });
        latch.await();
//        zk.create("/zk-test", "hi".getBytes(),
//                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create("/zk-test/c1", "c1".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(zk.getChildren("/zk-test",true));
        zk.create("/zk-test/c2", "hi".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zk.create("/zk-test/c3", "hi".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testAuth() throws Exception {
        ZooKeeper zk = new ZooKeeper("localhost:2181", 5000, event -> {

        });
        zk.addAuthInfo("digest","foo:bar".getBytes());
        zk.create("/zk-test", "hi".getBytes(),
                ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        zk.create("/zk-test/c1", "c1".getBytes(),
                ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
        zk.create("/zk-test/c2", "c2".getBytes(),
                ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000,event -> {

        });
        System.out.println(zooKeeper.getChildren("/zk-test",false));
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void zkClientCreate() throws InterruptedException {
        ZkClient zkClient = new ZkClient("localhost:2181",5000);
        zkClient.subscribeChildChanges("/zk-client", (parentPath, currentChilds) -> {
            System.out.println(parentPath + "  :  " + currentChilds);
        });
        zkClient.getChildren("/zk-client");
        zkClient.createPersistent("/zk-client/c2",true);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void zkClientGet() throws InterruptedException {
        ZkClient zkClient = new ZkClient("localhost:2181",5000);
        zkClient.getChildren("/zk-client");
        zkClient.createPersistent("/zk-client/c3","hello");
        final String data = (String)zkClient.readData("/zk-client/c3");
        System.out.println(data);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void curatorCreate() throws Exception {
        final RetryPolicy retryPolicy= new ExponentialBackoffRetry(1000, 3);
        final CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", 5000, 3000, retryPolicy);
        client.start();
        //非叶子必须持久
        client.create().creatingParentsIfNeeded().
                forPath("/zk-curator/c1","hello".getBytes());
    }
}
