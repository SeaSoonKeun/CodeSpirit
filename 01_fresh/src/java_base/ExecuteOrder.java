package java_base;

/**
 * @author : xucg
 * date : 2023/2/11 - 02 - 11 - 9:08 下午
 */
public class ExecuteOrder {
    //属性
    int a;
    static int sa;

    //静态块
    static {
        System.out.println("-----这是静态块");
        //在静态块中只能方法：静态属性，静态方法
        System.out.println(sa);
        b();
    }
    //静态方法
    public static void b() {
        System.out.println("-----这是静态方法");
    }

    //构造块
    {
        System.out.println("------这是构造块");
    }

    //构造器
    public ExecuteOrder() {
        System.out.println("这是空构造器");
    }

    public ExecuteOrder(int a) {
        this.a = a;
    }

    //方法
    public void a() {
        System.out.println("-----a");
        {
            //普通块限制了局部变量的作用范围
            System.out.println("这是普通块");
        }
    }




    //这是一个main方法，是程序的入口：
    public static void main(String[] args) {
        ExecuteOrder t = new ExecuteOrder();
        t.a();
        ExecuteOrder t2 = new ExecuteOrder();
        t2.a();
    }
}
