package leetcode;

import java.util.Arrays;

/**
 * @author zhailzh
 * 
 * @Date 201511173:22:27
 * 
 */
public class DP {

	/*ĳΪ˷йĵϮչһֵϵͳֵϵͳһȱݣ
	  Ȼĵһڵܹĸ߶ȣԺÿһڵܸǰһĸ߶ȡĳ죬
	  ״ﲶ׽йĵϮڸϵͳý׶Σֻһϵͳпܲеĵ
	  뵼ηĸ߶ȣ״ĸ߶ǲ30000ϵͳضٵ
	ҪеҪ䱸ֵϵͳ
	ʽ
	һУΪηĸ߶
	ʽ
	УֱصĵҪеҪ䱸ϵͳ
	
	389 207 155 300 299 170 158 65
	
	6
	2
	Ŀ

	һ⣺һеУԼٻֳɼ鲻СһǾ䶯̬滮ڶֱӵķС·ǣǶͼƥĸӶȽϸߣǿԽתУֵڲеС漰ѧƫ򼯵Dilworth
	*/
	/**
	 * 
	 * 飺a1,a2,a3,....,an Ĳе  (ak,....,aj)
	 * ô飺a1,a2,a3,....,an,an+1 Ĳ  an+1 ajСôԭ
	 * (ak,...aj,an+1)
	 * */
	

    public static void intercept(int[] array) {  
        int n = array.length;  
        //ĵطd[] ǷiöԺ󣬻صĵiö
        //˵d[n] =1
        int[] d = new int[n];  
        int dmax, xh;  
        for (int i = n - 1; i >= 0; i--) { // Ӻǰd[i]ֵ  
            for (int j = i; j < n; j++) {
            	// array[j] <= array[i] еĵںţͱʾ˵i
                if ((array[j] <= array[i]) && (d[i] < d[j] + 1)) {  
                    d[i] = d[j] + 1;  
                    System.out.println("d[ "+i+"] :" + d[i]);
                }  
            }  
        }  
        dmax = 0;  
        xh = 1;  
        for (int i = 0; i < n; i++) { // dmax  
            if (d[i] > dmax) {  
                dmax = d[i];  
                xh = i;  
            }  
        }  
        System.out.println(dmax); //   ĵ
        System.out.print(array[xh] + " ");
//        xh ʾĿʼλã˼
        int temp = xh;  
        for (int i = xh + 1; i < n; i++) {  
            if (d[i] == d[temp] - 1) {  
                temp = i;  
                System.out.print(array[i] + " ");  
            }  
        }  
        System.out.println();  
    }  
    
    
    
//    
//    NλͬѧվһţʦҪе(N-K)λͬѧУʹʣµKλͬѧųɺϳΡ   ϳָһֶΣ
//    KλͬѧαΪ12Kǵ߷ֱΪT1T2TK 
//    ǵT1<...<Ti>Ti+1>>TK(1<=i<=K)   ǣ֪NλͬѧߣҪλͬѧУ
//    ʹʣµͬѧųɺϳΡ 
//    ʽ Input Format      
//    ĵһһN(2<=N<=100)ʾͬѧһnÿոָiTi(130<=Ti<=230)ǵiλͬѧ() 
//    ʽ Output Format      õŶеͬѧ Լͬѧ
    
   /**
    * ؼڶ⣬һģкĽ
    * ǿԶһm[i] ʾa[0,1,....,i] l[i]  Ľеr[i]ĳ ĺ͡
    * Ȼm[i]ֵǾܹСĳ
    * */
    
    public static void makeQueue(int[] array){
    	// d ǰõ ǰԪ
    	int[] aid = new int[array.length];
    	int n = array.length;
    	
    	int[] bid = new int[array.length];
    	
    	
    	for (int i =0; i < n; i++) { // ǰd[i]ֵ
            for (int j = 0; j <= i; j++) {
                if ((array[j] < array[i]) && (aid[i] < aid[j] + 1)) {  
                    aid[i] = aid[j] + 1;  
                    System.out.println("ǰ[ "+i+"] :" + aid[i]);
                }  
            }  
        }
    	
    	
    	 for (int i = n - 1; i >= 0; i--) { // Ӻǰd[i]ֵ  
             for (int j = i; j < n; j++) {
                 if ((array[j] < array[i]) && (bid[i] < bid[j] + 1)) {  
                	 bid[i] = bid[j] + 1;  
                     System.out.println("[ "+i+"] :" + bid[i]);
                 }  
             }  
         } 
    	
    	  int max = 0;int midle = 0;
    	  for(int i = 0; i < n; i++)
    	  {
    	    if(max < aid[i] + bid[i]){
    	    	 max = aid[i] + bid[i]+1 ;  // Ѱϳ
    	    	    midle = i;
    	    }
    	     
    	  }
    	
    	  System.out.println("ϳǣ"+ max + "мλã "+ midle );
    }

    
    
    public static void main(String[] args) {  
    	makeQueue(new int[] { 186 ,186, 150, 200, 160, 130, 197, 220});  
    } 
}
