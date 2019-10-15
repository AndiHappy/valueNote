package jdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class B{
    public int b;
    public B(){}
}

interface IA{}

class A extends B implements IA{
    public A() { }

    public A(String str) { }

    public A(String str1, String str2) { }

    private String str;
    public int age;

    public int func1(String name) {
        System.out.println("hello " + name);
        return 8;
    }

    public void func1(String name1, String name2) {
        System.out.println("hello "+name1+","+name2);
    }
}

public class ReflectDemo {
    
    public static void main(String[] args) throws Exception {
        // 根据字符串获得类
        Class<?> demoClass = Class.forName("jdk.A");

        // 获得类的完整名字
        System.out.println("类名"+demoClass.getName()); // com.hicoor.test.reflect.A
        // 获得类加载器，默认sun.misc.Launcher$AppClassLoader
        System.out.println("类加载器："+demoClass.getClassLoader().getClass().getName());

        //根据Class的共有无参构造方法创建一个实例
        A newAObj = (A)demoClass.newInstance();
        
        // 获得类中声明的属性
        Field[] publicFields = demoClass.getFields(); // 获得当前类和父类中的public类型的所有属性，返回：age
        Field[] declareFields = demoClass.getDeclaredFields(); // 获得当前类（不包含父类）声明的所有属性，包括private和public，返回：str age
        Field specifyField = demoClass.getField("age"); // 根据名称获取指定属性
        specifyField.setAccessible(true);
        //修改属性
        specifyField.set(newAObj, 88);
        
        // 获得类的方法
        Method[] publicMethods = demoClass.getMethods(); // 获得前类和父类中public类型的所有方法
        Method[] declareMethods = demoClass.getDeclaredMethods(); // 获得当前类（不包含父类）声明的所有方法，包括private和public
        Method specifyMethod = demoClass.getDeclaredMethod("func1",new Class<?>[]{java.lang.String.class});  //根据方法名和方法参数类型指定获取一个方法
        //反射调用对象的方法
        specifyMethod.invoke(newAObj, "hans");
        
        //获得构造函数
        Constructor<?>[] publicConstructors = demoClass.getConstructors();
        Constructor<?>[] declareConstructors = demoClass.getDeclaredConstructors();  //获得当前类声明的所有private和public构造方法
        Constructor<?> constructor = demoClass.getConstructor(new Class<?>[]{java.lang.String.class});  //根据指定类型获得构造方法
        A newAobj2 = (A)constructor.newInstance("hello");  //根据指定构造函数创建实例
        
        //获得实现的接口
        Class<?>[] interfaces = demoClass.getInterfaces();
        
        //获得继承的父类
        Class<?> superclass = demoClass.getSuperclass();
    }

    //反射获得一个方法的明细定义
    private static void getMethodDetail(Method method) {
        String methodModifier = Modifier.toString(method.getModifiers());  //方法修饰符
        String returnType = method.getReturnType().getName();  //方法返回值
        Class<?>[] parameterTypes = method.getParameterTypes();  //方法参数类型
        System.out.print(methodModifier+" "+returnType+" "+ method.getName()+"(");
        int i=1;
        for (Class<?> parameterType : parameterTypes) {
            System.out.print(parameterType.getName() + " arg"+(i++));
            if(i<=parameterTypes.length){
                System.out.print(",");
            }
        }
        System.out.println(") {}");
    }

}