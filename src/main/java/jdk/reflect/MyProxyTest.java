package jdk.reflect;
 
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
 
/**
 * 使用JDK动态代理的五大步骤:
 * 1.通过实现InvocationHandler接口来自定义自己的InvocationHandler;
 * 2.通过Proxy.getProxyClass获得动态代理类
 * 3.通过反射机制获得代理类的构造方法，方法签名为getConstructor(InvocationHandler.class)
 * 4.通过构造函数获得代理对象并将自定义的InvocationHandler实例对象传为参数传入
 * 5.通过代理对象调用目标方法
 */
public class MyProxyTest {
    public static void main(String[] args)
            throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
//        // =========================第一种==========================
//        // 1、生成$Proxy0的class文件
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        // 2、获取动态代理类
//        Class proxyClazz = Proxy.getProxyClass(IHello.class.getClassLoader(),IHello.class);
//        // 3、获得代理类的构造函数，并传入参数类型InvocationHandler.class
//        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
//        // 4、通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
//        IHello iHello1 = (IHello) constructor.newInstance(new MyInvocationHandler(new HelloImpl()));
//        // 5、通过代理对象调用目标方法
//        iHello1.sayHello();
// 
//        // ==========================第二种=============================
//        /**
//         * Proxy类中还有个将2~4步骤封装好的简便方法来创建动态代理对象，
//         *其方法签名为：newProxyInstance(ClassLoader loader,Class<?>[] instance, InvocationHandler h)
//         */
//        IHello  iHello2 = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(), // 加载接口的类加载器
//                new Class[]{IHello.class}, // 一组接口
//                new MyInvocationHandler(new HelloImpl())); // 自定义的InvocationHandler
//        iHello2.sayHello();
//        
        InvocationHandler h =  new MyInvocationHandler(new NoInterfaceImpl());
        /**
newProxyInstance()方法帮我们执行了生成代理类----获取构造器----生成代理对象这三步；

生成代理类: Class<?> cl = getProxyClass0(loader, intfs);

获取构造器: final Constructor<?> cons = cl.getConstructor(constructorParams);

生成代理对象: cons.newInstance(new Object[]{h});
public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h){
        final Class<?>[] intfs = interfaces.clone();
        Class<?> cl = getProxyClass0(loader, intfs);
        final Constructor<?> cons = cl.getConstructor(constructorParams);
        return cons.newInstance(new Object[]{h});
}
* */
        NoInterfaceImpl hell0 = (NoInterfaceImpl) Proxy.newProxyInstance(NoInterfaceImpl.class.getClassLoader(), new Class[] {NoInterfaceImpl.class}, h );
        hell0.sayHello();
        
        /**
Exception in thread "main" java.lang.IllegalArgumentException: jdk.reflect.NoInterfaceImpl is not an interface
at java.lang.reflect.Proxy$ProxyClassFactory.apply(Proxy.java:590)
at java.lang.reflect.Proxy$ProxyClassFactory.apply(Proxy.java:557)
at java.lang.reflect.WeakCache$Factory.get(WeakCache.java:230)
at java.lang.reflect.WeakCache.get(WeakCache.java:127)
at java.lang.reflect.Proxy.getProxyClass0(Proxy.java:419)
at java.lang.reflect.Proxy.newProxyInstance(Proxy.java:719)
at jdk.reflect.MyProxyTest.main(MyProxyTest.java:41)
 
         * */
        
    }
}