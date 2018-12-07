package baseAlg.dp;

/**
 * @author zhailz
 * 2018年11月30日 上午10:31:17
 */
public class FindSumBiggerPath {
	
	/**
    7

    3   8

    8   1   0

    2   7   4   4

    4   5   2   6   5
	 * */
	
	public static int[][] D = new int[5][5];
	
	public static void main(String[] args){
		
		D = new int[][]{
			 {7,0,0,0,0},
			 {3,8,0,0,0},
			 {8,1,0,0,0},
			 {2,7,4,4,0},
			 {4,5,2,6,5}
		};
		
		
		
		for (int i = 0; i < D.length; i++) {
			for (int j = 0; j < D[i].length; j++) {
				System.out.print(D[i][j] +" ");
			}
			System.out.println();
		}
		
	}
	
	
	
	
//	#include <iostream>  
//	#include <algorithm> 
//	#define MAX 101  
//	using namespace std; 
//	int D[MAX][MAX];  
//	int n;  
//	int MaxSum(int i, int j){    
//		if(i==n)  
//			return D[i][j];    
//		int x = MaxSum(i+1,j);    
//		int y = MaxSum(i+1,j+1);    
//		return max(x,y)+D[i][j];  
//	}
//	int main(){    
//		int i,j;    
//		cin >> n;    
//		for(i=1;i<=n;i++)   
//			for(j=1;j<=i;j++)        
//				cin >> D[i][j];    
//		cout << MaxSum(1,1) << endl;  
//	}      

}
