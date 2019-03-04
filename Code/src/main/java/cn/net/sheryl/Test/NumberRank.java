package cn.net.sheryl.Test;

//URL:http://www.myeclipsecn.com/learningcenter/java-resource/8-java-sort/
public class NumberRank {
//	public static void main(String[] args) {//直接插入排序 继续循环的时候 数组长度应该+了1 循环结束的时候长度变回原来的长度，但是中间进行的临近的两个数的排序 
//		int a[] = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51 };
//		int temp = 0;
//		for (int i = 1; i < a.length; i++) {
//			int j = i - 1;
//			temp = a[i];
//			for (; j >= 0 && temp < a[j]; j--) {
//				a[j + 1] = a[j]; // 将大于temp的值整体后移一个单位
//			}
//			a[j + 1] = temp;
//		}
//		for (int i = 0; i < a.length; i++)
//			System.out.println(a[i]);
//	}
	
	public static void main(String[] args) {//哈希排序
		int a[] = { 1, 54, 6, 3, 78, 34, 12, 45, 56, 100 };
		double d1 = a.length;
		int temp = 0;
		while (true) {
			d1 = Math.ceil(d1 / 2);
			int d = (int) d1;
			for (int x = 0; x < d; x++) {
				for (int i = x + d; i < a.length; i += d) {
					int j = i - d;
					temp = a[i];
					for (; j >= 0 && temp < a[j]; j -= d) {
						a[j + d] = a[j];
					}
					a[j + d] = temp;
				}
			}
			if (d == 1)
				break;
		}
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}
}
