import java.util.*;

class palindrome{
        public static String longest(String str)
		{
		      String max=" ";
			  for(int i=0;i<str.length();i++)
			  {
			     for(int j=str.length()-1;j>=i;j--)
				 {
				    if(str.charAt(i)==str.charAt(j))
					{
					     if(palindrome1(str.substring(i,j+1)))
						 {
						    max=max.length()<str.substring(i,j+1).length()? str.substring(i, j+1):max;
						    break;
						 }
                     }
                }
            }
               return max;
           }
         public static boolean palindrome1(String str)
         {
                  int i=0,j=str.length()-1,c=0;
                   while(i<j)
                   {
                      if(str.charAt(i)==str.charAt(j))
                       {
                             c++;
                             i++;
                             j--;
                       }
                        else
                        {
                              return false;
                        }
                      if(c==str.length()/2)
                              return true;
				   }
                     return false;
                   
            }
			public static void main(String args[])
			{
				 Scanner sc=new Scanner(System.in);
				 String str=sc.nextLine();
				 System.out.println(longest(str));
			}
}
			
			