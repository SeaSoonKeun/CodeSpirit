package java_base;

/**
 * @author xucg
 * @version 1.0
 * Created on 2023/2/10 - 02 - 10
 * description:
 */
public class TestSwap {
    public static void main(String[] args) {
        // 任意给出两个数，交换两个数并输出：
        // 1.给出两个数：
        int num1 = 10;
        int num2 = 20;

        // 2.输出交换前的两个数：
        System.out.println("交换前：" + num1 + "\t" + num2);

        // 3.交换
                /*
                错误代码：
                num1 = num2;
                num2 = num1;
                */
        // 解决办法：
        // 引入一个中间变量:
        int t;
        t = num1;
        num1 = num2;
        num2 = t;
                /*
                int t;
                t = num2;
                num2 = num1;
                num1 = t;

                */

        // 4.输出交换后的两个数：
        System.out.println("交换后：" + num1 + "\t" + num2);
    }
}

