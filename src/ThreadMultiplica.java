
public class ThreadMultiplica extends Thread {
	private int d1,d2,d3,threads,id;
	private Matriz m;
	int m1[][],m2[][];
	
	ThreadMultiplica(int d1,int d2,int d3,int i,int t,int m1[][],int m2[][],Matriz m)
	{
		this.d1=d1;
		this.d2=d2;
		this.d3=d3;
		this.id=i;
		this.threads=t;
		this.m1=m1;
		this.m2=m2;
		this.m=m;
		
		this.start();
	}
	
	public void run()
	{
		for(int i=this.id;i<this.d1;i+=this.threads)
		{
			for(int j=0;j<this.d3;j++)
			{
				for(int k=0;k<this.d2;k++)
				{
					this.m.M[i][j]+=this.m1[i][k]*this.m2[k][j];
				}
			}
		}
    }
	
}
