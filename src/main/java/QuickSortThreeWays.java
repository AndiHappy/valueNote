import java.util.Arrays;

public class QuickSortThreeWays {
 
    // 我们的算法类不允许产生任何实例
    private QuickSortThreeWays() {
 
    }
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
		private static void sort(Comparable[] arr, int l, int r) {
        if (l >= r)
            return;
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
 
        Comparable v = arr[l];
 
        int lt = l;     // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l + 1;    // arr[lt+1...i) == v
        while (i < gt) {
            if (arr[i].compareTo(v) < 0) {
                swap(arr, i, lt + 1);
                i++;
                lt++;
            } else if (arr[i].compareTo(v) > 0) {
                swap(arr, i, gt - 1);
                gt--;
            } else { // arr[i] == v
                i++;
            }
        }
 
        swap(arr, l, lt);
 
        sort(arr, l, lt - 1);
        sort(arr, gt, r);
    }
 
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }
 
 
    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
 
    // 测试 QuickSort3Ways
    public static void main(String[] args) {
 
        // 三路快速排序算法也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        Integer[] arr = new Integer[] {1,4,52,32,34,7,88,7,88,878,0};
        QuickSortThreeWays.sort(arr);
        System.out.println(Arrays.toString(arr));
        return;
    }
 
}