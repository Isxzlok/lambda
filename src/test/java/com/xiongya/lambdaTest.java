package com.xiongya;

import com.xiongya.entity.User;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author xiongzhilong
 * @Email 2584496774@qq.com
 * @Date create by 2019-04-04 15:38
 */
public class lambdaTest {

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

    /**
     * lambda表达式foreach两种遍历方式
     */
    @Test
    public void test1(){
        String[] str = {"1","2","3","4"};
        List<String> list = Arrays.asList(str);
        //使用lambda表达式以及函数操作
        list.forEach((s)->System.out.println(s+";"));
        //在java8中使用双冒号操作符
        list.forEach(System.out::print);

        System.out.println("hshshshhshs");
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
    }




}




