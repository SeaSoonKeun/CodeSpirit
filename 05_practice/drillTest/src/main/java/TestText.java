/**
 * @author xucg
 * @version 1.0
 * Created on 2023/2/22 - 02 - 22
 * description:
 */
public class TestText {


    public static void main(String[] args) {
        Text text123 = new Text("徐纯根");
        byte[] bytes = text123.getBytes();
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
        for (byte aByte : text123.getBytes()) {
            System.out.println(aByte);
        }
        System.out.println(text123);
    }
}
