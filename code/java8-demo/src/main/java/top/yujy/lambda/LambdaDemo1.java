package top.yujy.lambda;

/**
 * Lambda表达式的Demo.
 */
public class LambdaDemo1 {

    public static void main(String[] args) {

        int port = 8080;
        Runnable r = () -> System.out.println(port);

        r.run();
    }
}
