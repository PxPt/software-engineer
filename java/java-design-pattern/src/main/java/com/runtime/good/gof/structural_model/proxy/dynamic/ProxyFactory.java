package com.runtime.good.gof.structural_model.proxy.dynamic;

import java.lang.reflect.Proxy;

public class ProxyFactory {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }


    //给目标对象生成一个代理对象
    public Object getProxyInstance() {

        // target.getClass().getClassLoader(),//当前对象的类加载器,获取的方法固定
        // target.getClass().getInterfaces(),//目标对象实现的接口类型,使用泛型方法确认类型
        // new InvocationHandler() {//事情处理,执行对象目标方法时,会触发事情处理器方法,会把当前执行的目标对象方法作为参数传入
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                this.target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("开始代理");
                    Object returnVal = method.invoke(target, args);
                    System.out.println("JDK代理结束");
                    return returnVal;
                }
        );

    }

}
