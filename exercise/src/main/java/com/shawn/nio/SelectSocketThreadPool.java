package com.shawn.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;
/**
 * 一个简单的服务器; 一个selector 委托线程池的线程来处理
 */
public class SelectSocketThreadPool extends SelectSockets {

    private ThreadPool pool = new ThreadPool(5);

    @Override
    protected void readFromSocket(SelectionKey key) throws IOException, InterruptedException {
        WorkerThread worker = pool.getWorker();
        if (null == worker) {
            return;
        }
        worker.serviceChannel(key);
    }

    private class ThreadPool {
        List<WorkerThread> list = new LinkedList<>();

        ThreadPool(int size) {
            for (int i = 0; i < size; i++) {
                WorkerThread workerThread = new WorkerThread(this);
                workerThread.setName("Worker " + (i + 1));
                workerThread.start();
                list.add(workerThread);
            }
        }

        void returnWorker(WorkerThread workerThread) {
            synchronized (list) {
                list.add(workerThread);
            }
        }
        WorkerThread getWorker() {
            synchronized (list) {
                if (!list.isEmpty()) {
                    return list.remove(0);
                }
            }
            return null;
        }
    }

    private class WorkerThread extends Thread {
        private ByteBuffer buffer = ByteBuffer.allocate(1024);
        private ThreadPool threadPool;
        private SelectionKey key;
        WorkerThread(ThreadPool pool) {
            threadPool = pool;
        }


        @Override
        public synchronized void run() {
            System.out.println(getName() + " is ready");
            while (true) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.interrupt();
                }
                if (key == null) {
                    continue;
                }
                System.out.println(getName() + " has been awakened");
                try {
                    drainChannel(key);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        key.channel().close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    key.selector().wakeup();
                }
                key = null;
                this.threadPool.returnWorker(this);
            }
        }

        void drainChannel(SelectionKey key) throws Exception {
            SocketChannel channel = (SocketChannel) key.channel();
            int count = 0;
            buffer.clear();
            while ((count = channel.read(buffer)) > 0) {
                // 这个循环仅仅是为了拿出缓存区的数据,
                while (buffer.hasRemaining()) {
                    channel.write(buffer);
                }
                buffer.clear();
            }
            if (count < 0) {
                channel.close();
                return;
            }
            key.interestOps(key.interestOps() | SelectionKey.OP_READ);
            key.selector().wakeup();
        }

        synchronized void serviceChannel(SelectionKey key) {
            this.key = key;
            key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
            notify();
        }
    }
}
