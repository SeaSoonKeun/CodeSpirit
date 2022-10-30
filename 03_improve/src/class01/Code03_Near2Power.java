package class01;

public class Code03_Near2Power {

	// 已知n是正数
	// 返回大于等于，且最接近n的，2的某次方的值
	public static final int tableSizeFor(int n) {
	// n-- 可避免int类型的数字越界
		n--;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : n + 1;
	}

	public static void main(String[] args) {
		int cap = 120;
//		整形最高位为符号位，只能最多表示到2^31
		cap = 1073741824;
		System.out.println(tableSizeFor(cap));
	}

}
