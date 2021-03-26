package org.luoyuhan.learn.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyPattern implements InvocationHandler, MethodInterceptor {
    public static void main(String[] args) {
        ProxyPattern proxyPattern = new ProxyPattern();
        // jdk proxy
        SayInterface sayService = new SayHelloService();
        SayInterface proxiedTarget = (SayInterface) proxyPattern.createReflectProxy(sayService);
        proxiedTarget.say();

        // cglib proxy
        SayHelloClass sayHelloClass = new SayHelloClass();
        SayHelloClass cGlibProxiedClass = (SayHelloClass) proxyPattern.createCglibProxy(sayHelloClass);
        cGlibProxiedClass.say();
    }

    // 使用jdk反射进行代理开始*****, 实现InvocationHandler接口，需要使用被代理对象，method.invoke(targetClass, args);
    private SayInterface targetClass;
    public Object createReflectProxy(SayInterface targetClass) {
        //传入真实实现类, 本身要做的事情会由他自己做, 代理类会额外进行其他增强操作
        this.targetClass = targetClass;
        //获取本类类加载器
        ClassLoader classLoader = ProxyPattern.class.getClassLoader();
        ///获取被代理对象的所有接口
        Class[] clazz = targetClass.getClass().getInterfaces();
        //生成代理类并返回，参数为代理类的类加载器，被代理类的方法，和代理类
        return Proxy.newProxyInstance(classLoader, clazz, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDKProxy前置增强");
        Object obj = method.invoke(targetClass, args);
        System.out.println("JDKProxy后置增强");
        return obj;
    }
    //使用jdk反射进行代理结束*****

    //使用cglib进行代理开始*****, 实现MethodInterceptor
    public Object createCglibProxy(SayHelloClass targetClass) {
        //创建一个动态类对象
        Enhancer enhancer = new Enhancer();
        //确定要增强的类,设置期父类
        enhancer.setSuperclass(targetClass.getClass());
        //添加回调函数
        enhancer.setCallback(this);
        //返回创建的代理类
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib前置增强");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("Cglib后置增强");
        return object;
    }

}
