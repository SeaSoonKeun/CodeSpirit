package java_base;

/**
 * @author : xucg
 * date : 2023/2/11 - 02 - 11 - 9:00 下午
 */
public class StaticTest {
    //属性：
    int id;
    static int sid;
    //这是一个main方法，是程序的入口：
    public static void main(String[] args) {
        //创建一个Test类的具体的对象
        StaticTest t1 = new StaticTest();
//        int sid = StaticTest.sid;
        t1.id = 10;
        t1.sid = 10;
        StaticTest t2 = new StaticTest();
        t2.id = 20;
        t2.sid = 20;
        StaticTest t3 = new StaticTest();
        t3.id = 30;
        t3.sid = 30;
        //读取属性的值：
        System.out.println(t1.id);
        System.out.println(t2.id);
        System.out.println(t3.id);
        System.out.println(t1.sid);
        System.out.println(t2.sid);
        System.out.println(t3.sid);
    }
}
