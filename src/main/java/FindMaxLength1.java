
/**
 * @author guizhai
 *
 */
public class FindMaxLength1 {

 public int findMaxLength1(int[][] a) {
  if (a == null || a.length == 0)
   return -1;
  int n = a.length;
  int m = a[0].length;
  int i = 0;
  int j = 0;
  int result = -1;

  for (int k = 0; k < m + n; k++) {
   if (i >= n || j >= m) {
    return result;
   }
   if (a[i][j] == 1) {
    j++;
    result = i;
   } else {
    i++;
   }
  }
  return result;
 }

 /**
  * @param args
  */
 public static void main(String[] args) {
//  int[][] a = new int[][] { { 1 } };
//  int[][] a = new int[][] { { 1, 1, 1, 0, 0, 0 } };
  int[][] a = new int[][] { 
   { 1, 1, 1, 0, 0, 0 }, 
   { 1, 0, 0, 0, 0, 0 },
   { 1, 1, 1, 1, 0, 0 }};
  
  FindMaxLength1 test = new FindMaxLength1();
  int value = test.findMaxLength1(a);
  System.out.println(value);
 }

}
