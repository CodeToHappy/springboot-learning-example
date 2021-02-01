import java.util.Date;
import java.util.concurrent.*;

public class ThreadPool {

    public static void fixedThreadPool(){
        //创建两个数据级线程
        ExecutorService threadPool= Executors.newFixedThreadPool(2);
        //创建任务
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("任务被执行，线程"+Thread.currentThread().getName());
            }
        };
        //线程池执行任务（一次添加四个任务）
        //执行任务的方法有两种
        threadPool.submit(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
    }

    public static void cachedThreadPool(){
        //创建线程池
        ExecutorService threadPool=Executors.newCachedThreadPool();
        //执行任务
        for (int i=0;i<10;i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("任务被执行，线程"+Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void singleThreadExecutor(){
        ExecutorService threadPool=Executors.newSingleThreadExecutor();
        for(int i=0;i<10;i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("任务被执行，线程"+Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void scheduledThreadPool(){
        ScheduledExecutorService threadPool=Executors.newScheduledThreadPool(5);
        //添加定时执行任务(1s后执行)
        System.out.println("添加任务，时间："+new Date());
        threadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务被执行，时间："+new Date());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },1,TimeUnit.SECONDS);
    }

    public static void singleScheduledThreadPool(){
        ScheduledExecutorService threadPool=Executors.newSingleThreadScheduledExecutor();
        //添加定时执行任务(2s后执行)
        System.out.println("添加任务，时间："+new Date());
        for (int i=0;i<10;i++) {
            threadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("任务被执行，时间："+new Date()+"  线程："+Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },1,TimeUnit.SECONDS);
        }
    }

    public static void myThreadPoolExecutor(){
        ThreadPoolExecutor threadPool=new ThreadPoolExecutor(5,10,10,TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(10));
        for (int i=0;i<7;i++){
            final int index = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index+"被执行，线程名："+Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    public static void main(String[] args) {
//        fixedThreadPool();
//        cachedThreadPool();
//        singleThreadExecutor();
//        scheduledThreadPool();
//        singleScheduledThreadPool();
        myThreadPoolExecutor();
    }
}
