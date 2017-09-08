//Raghav Bhatia 2016071
import java.util.Scanner;
import java.io.*;
import java.util.*;

class code6
{

		public static void main(String[] args) throws IOException 
		{
			PrintWriter w = new PrintWriter("C:\\Users\\Raghav\\Desktop\\lab6ans" + ".txt", "UTF-8");
			Scanner sc=new Scanner(System.in);
			int n=sc.nextInt();
			int iter=sc.nextInt();
			int qx=sc.nextInt();
			int qy=sc.nextInt();
			knight[] arr=new knight[n];
			for(int jj=1;jj<=n;jj++)
			{
				String nn="C:\\Users\\Raghav\\Desktop\\Lab6\\Lab 6\\Test Case\\Input\\";
				nn=nn.concat(Integer.toString(jj));
				nn=nn.concat(".txt");
				Scanner dc=new Scanner(new FileReader(nn));
				knight k=new knight(dc.next(),dc.nextInt(),dc.nextInt(),dc.nextInt());
				for(int i=0;i<k.size;i++)
				{
					String type=dc.next();
					if (type.equals("String"))
					{
						String m=dc.next();
						k.st.push(m);
					}
					else if(type.equals("Integer"))
					{
						int m=dc.nextInt();
						k.st.push(m);
					}
					else if(type.equals("Float"))
					{
						float m=dc.nextFloat();
						k.st.push(m);

					}
					else
					{
						coordinates c=new coordinates(dc.nextInt(),dc.nextInt());
						k.st.push(c);
					}
				}
				
				arr[jj-1]=k;
			}

			knightsort comp=new knightsort();
			Arrays.sort(arr,comp);



			int flag=0;
			for(int i=0;i<iter;i++)
			{
					for(int j=0;j<n;j++)
					{
							w.println((i+1)+" "+arr[j].name+" "+ arr[j].xy.x+ " "+ arr[j].xy.y);
							try
							{
									checkempty(arr[j].st);
							}
							catch(Exception m)
							{
								w.println(m+" ");
								for(int ind=j+1;ind<n;ind++)
								{
									arr[ind-1]=arr[ind];
								}
								n--;
								j--;
								continue;

							}
							String str=String.valueOf(arr[j].st.peek().getClass());
							try
							{
								check(str);
							}
							catch(Exception m)
							{
								w.print(m+" ");
								w.println(arr[j].st.pop());
								continue;
							}
							coordinates c=(coordinates)arr[j].st.pop();
							arr[j].xy.x=c.x;
							arr[j].xy.y=c.y;
							int g=0;
							try
							{
								for(int i1=0;i1<n;i1++)
								{
									if(i1!=j)
									{
											if(c.x==arr[i1].xy.x && c.y==arr[i1].xy.y)
											{
												g=i1;
												throw new OverlapException("Knights Overlap Exception"); 

											}
									}
								}
							}
							catch(Exception m)
							{
								w.print(m+" ");
								w.println(arr[g].name);
								for(int ind=g+1;ind<n;ind++)
								{
									arr[ind-1]=arr[ind];
								}
								n--;
								if(g<j)
								{
									j--;
								}
								continue;

							}
							try
							{

								if(arr[j].xy.x==qx && arr[j].xy.y==qy)
								{
										throw new QueenFoundException("Queen Has Been Found. Abort!");
								}	
							}
							catch(Exception m)
							{
									w.println(m);
									flag=1;
									break;

							}		
							w.println("No Exception "+c.x+" "+c.y);

					}

					if(flag==1)
					{
						break;
					}
			}











			w.close();

		}



	public static void check(String s) throws NonCoordinateException
	{  
	     if(!s.equals("class coordinates"))  
	      throw new NonCoordinateException("Not A Coordinate Exception");  
   	}
   	public static void checkempty(Stack s) throws StackEmptyException
	{  
	     if(s.empty()==true)  
	      throw new StackEmptyException("Stack Empty Exception");  
   	}   


}

class knight
{
	public String name;
	public coordinates xy;
	public int size;
	public Stack st;	

	public knight(String str,int x1, int y1, int size1)
	{
			name=str;
			xy=new coordinates(x1,y1);
			size=size1;
			st=new Stack();

	}




}

class coordinates
{
	public int x;
	public int y;
	public coordinates(int x1, int y1)
	{
		x=x1;
		y=y1;
	}

}

class knightsort implements Comparator<knight>
{
		public int compare(knight a, knight b)
		{
				return a.name.compareTo(b.name);
		}


}



//EXCEPTIONS

class NonCoordinateException extends Exception
{
	NonCoordinateException(String s)
	{
		super(s);
	}
}

class StackEmptyException extends Exception
{
	StackEmptyException(String s)
	{
		super(s);
	}
}

class OverlapException extends Exception
{
	OverlapException(String s)
	{
		super(s);
	}
}

class QueenFoundException extends Exception
{
	QueenFoundException(String s)
	{
		super(s);
	}
}