import org.apache.commons.lang3.time.FastDateFormat;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class Test {
    public static void main(String[] args) {
//        FastDateFormat formatter =
//                FastDateFormat.getDateInstance(Integer.parseInt("20210120"),
//                        TimeZone.getDefault(),
//                        Locale.getDefault());
//
//        String output = formatter.format(new Date());
//
//        System.out.println(output);
//
//        FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date());
//
//        LinkedList<Integer> list = new LinkedList<Integer>();
//        list.add(0, 1);
//        list.add(1, 2);
//        list.add(2, 3);
//        System.out.println("list = " + list);
//        list.pop();
//        list.push(4);
//        System.out.println("list = " + list);

//        while(true){
//            try {
//                for (int i=0;i<100;i++){
//                    Thread.sleep(1000);
//                    System.out.println(i);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        final User user=new UserImpl();
        User proxy=(User)Proxy.newProxyInstance(Test.class.getClassLoader(), user.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("begin "+method.getName()+"----------");
                Object result=method.invoke(user,args);
                System.out.println("end "+method.getName()+"------------");
                return result;
            }
        });
        proxy.getName();
        proxy.getAge();
    }
}
