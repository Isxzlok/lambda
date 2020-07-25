package com.xiongya;

import com.xiongya.entity.User;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author xiongzhilong
 * @Email 2584496774@qq.com
 * @Date create by 2019-04-04 15:38
 */
public class lambdaTest {

    public static void main(String[] args) {
//        System.out.println("aaaaaaa");
//        new Thread(() -> {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("bbbbbb");
//        }
//        ).start();
//        System.out.println("ccccc");
//        return;
        System.out.println(args.length);
        System.out.println(args[0]);
        System.out.println(args[1]);
    }

    @Test
    public void test123() {
        new lambdaTest().B();
    }

    public void B() {
        new lambdaTest().A();
    }

    public static void A() {
        System.out.println("aaaaa");
    }

    static List<User> list = new ArrayList<User>(){
        {
            add(new User("a","b"));
            add(new User("a1","b1"));
            add(new User("a222","b2"));
            add(new User("a","bb"));
            add(new User("a","bbc"));
            add(new User("a3","b1"));
            add(new User("a44","b2"));
        }
    };

    @Test
    public void test22() throws IOException {
        String hello = "hello world";
        FileOutputStream stream = new FileOutputStream("/Users/xinshiji/Desktop/hello.txt");
        FileChannel channel = stream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(hello.getBytes());
        byteBuffer.flip();
        channel.write(byteBuffer);
        stream.close();
    }

    @Test
    public void test21() {
        //list.removeIf(user -> "a".equals(user.getUserName()));
        //System.out.println(list);
        Iterator<User> it = list.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if ("a".equals(user.getUserName())) {
                it.remove();
            }
        }
        System.out.println(list);
    }

    @Test
    public void test20() {
        List<String> list = new ArrayList<>();
        list = null;
        for (String s : list) {
            System.out.println("211111");
        }
    }

    @Test
    public void test19() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.sort((a, b) -> b - a);
        System.out.println(list);
    }

    @Test
    public void test18() {
        String str1 = new String("111").intern();
        String str2 = new String("111").intern();
        System.out.println(str1 == str2);
    }

    @Test
    public void test17() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            //return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        }

    @Test
    public void a() {
        System.out.println("aaaaaaa");
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("bbbbbb");
        }
        ).start();
        System.out.println("ccccc");
        //return 1;
    }

    @Test
    public void test16() {
        Set<Integer> set = new HashSet<>();
        boolean a = set.add(1);
        boolean b = set.add(1);
        System.out.println(a);
        System.out.println(b);
    }

    /**
     * lambda表达式foreach两种遍历方式
     */
    @Test
    public void test1(){
        String[] str = {"1","2","3","4"};
        List<String> list1 = Arrays.asList(str);
        //使用lambda表达式以及函数操作
        list1.forEach((s)->System.out.println(s+";"));
        //在java8中使用双冒号操作符
        list1.forEach(System.out::print);
        System.out.println("shhshsh");

    }

    /**
     * lambda线程实现的2种方式
     */
    @Test
    public void test2(){

        //1.使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("test2.1");
            }
        }).start();

        //2.使用lambda表达式
        new Thread(()->System.out.println("test2.2")).start();

    }

    /**
     * lambda排序方式
     */
    @Test
    public void test3(){
        String[] str = {"1","3","2","0"};
        System.out.println("排序前：");
        List<String> list = Arrays.asList(str);
        list.forEach((s)->System.out.print(s + " "));

        Arrays.sort(str, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println("排序后：");
        List<String> list1 = Arrays.asList(str);
        list1.forEach(System.out::print);
    }

    @Test
    public void test4(){
        String[] str = {"1","3","2","0"};
        Arrays.sort(str,(String o1, String o2)->{return o1.compareTo(o2);});
        Arrays.asList(str).forEach(s->System.out.print(s+" "));
    }

    /**
     * 遍历list对象
     */
    @Test
    public void test5(){
       list.forEach(user -> System.out.println(user.getUserName()+":"+user.getPassword()));
    }

    /**
     * 调用get，set方法
     */
    @Test
    public void test6(){
        list.forEach(user -> user.setUserName(user.getUserName()+"0828"));
        list.forEach(user -> System.out.println(user.getUserName()+":"+user.getPassword()));

    }

    /**
     * 定义过滤器
     */
    @Test
    public void test7(){
        list.stream()
                .filter(user -> user.getUserName().equals("a"))
                //.limit(1)
                .forEach(user -> System.out.println(user.getUserName()+":"+user.getPassword()));
    }

    /**
     * 排序在stream中处理
     */
    @Test
    public void test8(){
        list.stream().sorted((p1,p2)->p1.getUserName().compareTo(p2.getUserName()))
                .limit(2).collect(Collectors.toList())
                .forEach(user -> System.out.println(user.getUserName()+":"+user.getPassword()));
        //list.forEach(user -> System.out.println(user.getUserName()+":"+user.getPassword()));
    }

    /**
     * Stream自带方法max,min
     */
    @Test
    public void test9(){
        User user = list.stream().max((p1,p2)->p1.getUserName().length() - p2.getUserName().length()).get();
        System.out.println(user);
        User user1 = list.stream().min((p1,p2)->p1.getUserName().length() - p2.getUserName().length()).get();
        System.out.println(user1);
    }

    /**
     * Stream将list对象类型转化成String/set（无序不重复）/TreeSet（有序不重复）
     */
    @Test
    public void test10(){
        String str = list.stream().map(User::getUserName).collect(Collectors.joining(";"));
        System.out.println(str);
        Set<String> set = list.stream().map(User::getUserName).collect(Collectors.toSet());
        System.out.println(set);
        TreeSet<String> treeSet = list.stream().map(User::getUserName).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(treeSet);
        Integer i = list.parallelStream().mapToInt(p->p.getUserName().length()).sum();
        System.out.println(i);

        BigDecimal b = new BigDecimal("2.00");
        b = b.setScale(0, BigDecimal.ROUND_DOWN);
    }




}




