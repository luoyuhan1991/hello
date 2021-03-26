package org.luoyuhan.learn.proxy;

public class SayHelloService implements SayInterface {
    @Override
    public void say() {
        System.out.println("said hello from implemented class");
    }
}
class SayHelloClass {
    public void say() {
        System.out.println("said hello from common class");
    }

}