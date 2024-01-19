import java.util.*;
class Subarray{
	
	  public static int maxSubarraySum(int arr[], int n){
       
		int maxSum=Integer.MIN_VALUE;
        int  tempSum=0;
            for(int i=0;i<n;i++){
                tempSum = Math.max(arr[i],arr[i]+tempSum);
                if(tempSum>maxSum){
                    maxSum=tempSum;
                }
            
         //System.out.println(arr[i]);
			}
        return maxSum;
	  }
        
       public static void main(String args[])
	   {
		  Scanner sc=new Scanner(System.in);
		   int n=sc.nextInt();
		   int a[]=new int[n];
		   for(int i=0;i<n;i++)
		   {
			    a[i]=sc.nextInt();
		   }
        System.out.println(maxSubarraySum(a,n));		   
	}
}
        
     
        
			