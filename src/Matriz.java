import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;
public class Matriz{
	protected int M[][];
	private int l,c,r;
	
	Matriz(int l,int c,int r)
	{
		this.M=new int[l][c];
		this.l=l;
		this.c=c;
		this.r=r;
		Random g=new Random();
		for(int i=0;i<l;i++)
		{
			for(int j=0;j<c;j++)
			{
				//M[i][j]=g.nextDouble()*this.r;
				M[i][j]=g.nextInt(this.r);
			}
		}
	}
	
	Matriz(int l,int c,int m[][])
	{
		this.M=new int[l][c];
		this.l=l;
		this.c=c;
		for(int i=0;i<l;i++)
		{
			for(int j=0;j<c;j++)
			{
				this.M[i][j]=m[i][j];
			}
		}
	}
	
	Matriz(int l,int c)
	{
		this.M=new int[l][c];
		this.l=l;
		this.c=c;
	}
	
	
	void mostraMatriz()
	{
		for(int i=0;i<this.l;i++)
		{
			for(int j=0;j<this.c;j++)
			{
				System.out.print(this.M[i][j]+"\t");
			}
			System.out.print("\n");
		}
	}
	
	Matriz multiplicaMatriz(Matriz m)
	{
		Instant t1, t2;
		Duration d;
		Matriz resultado=new Matriz(this.l,m.c);
		resultado.inicializaMatriz();
		t1 = Instant.now();
		for(int i=0;i<this.l;i++)
		{
			for(int j=0;j<m.c;j++)
			{
				for(int k=0;k<this.c;k++)
				{
					resultado.M[i][j]+=this.M[i][k]*m.M[k][j];
				}
			}
		}
		t2 = Instant.now();
		d =  Duration.between(t1, t2);
		System.out.println("Multiplicação sem threads " + d.toMillis() + " milisegundos");
		return resultado;
	}
	
	Matriz multiplicaMatrizThreads(Matriz m)
	{
		Instant t1, t2;
		Duration d;
		Matriz resultado=new Matriz(this.l,m.c);
		resultado.inicializaMatriz();
		int t=Runtime.getRuntime().availableProcessors();
		ThreadMultiplica th[] = new ThreadMultiplica[t];
		t1 = Instant.now();
		for(int i=0;i<t;i++)
		{
			th[i]=new ThreadMultiplica(this.l, this.c, m.c, i, t, this.M, m.M, resultado);
		}
		for(int i=0;i<t;i++)
		{
			try {
				th[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t2 = Instant.now();
		d =  Duration.between(t1, t2);
		System.out.println("Multiplicação com threads " + d.toMillis() + " milisegundos\n\n");
		return resultado;
	}
	
	void inicializaMatriz()
	{
		for(int i=0;i<this.l;i++)
		{
			for(int j=0;j<this.c;j++)
			{
				this.M[i][j]=0;
			}
		}
	}
	
	void mostraDiferenca(Matriz m)
	{
		for(int i=0;i<this.l;i++)
		{
			for(int j=0;j<this.c;j++)
			{
				System.out.print(this.M[i][j]-m.M[i][j]+"\t");
			}
			System.out.print("\n");
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Matriz)) {
			return false;
		}
		Matriz other = (Matriz) obj;
		if (!Arrays.deepEquals(M, other.M)) {
			return false;
		}
		if (c != other.c) {
			return false;
		}
		if (l != other.l) {
			return false;
		}
		if (r != other.r) {
			return false;
		}
		return true;
	}
	
	

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
}