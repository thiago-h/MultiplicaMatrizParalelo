

public class Main {

	public static void main(String[] args) {
		Matriz m1,m2;
		for(int i = 0; i < 3; i++) {
			m1=new Matriz(1100,1100,100);
			m2=new Matriz(1100,1100,100);
			
			m1.multiplicaMatriz(m2);
			
			m1.multiplicaMatrizThreads(m2);
		}
	}

}
