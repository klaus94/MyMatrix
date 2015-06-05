import java.util.*;

public class MatrixTest
{
	
	public static void main(String[] args)
	{
		Object output;

		Matrix<String> m = new MyMatrix<String>();
		System.out.println("feld erstellt");

		m.put(0,1,"a2");
		m.put(1,3,"b");
		m.put(2,0,"a1");
		System.out.println("3 werte eingefügt");

		// teste 		
		System.out.println("RowCount: " + m.getRowCount());
		System.out.println("ColCount: " + m.getColumnCount());
		System.out.println("ObjectCount: " + m.getObjectCount());
		System.out.println("DistinctObjectCount: " + m.getDistinctObjectCount());

		System.out.println("get(2, 0): " + m.get(2, 0));		// a1
		System.out.println("get(1, 2): " + m.get(1, 2));		// null


		Iterator it = m.iterator();		

		System.out.println("iterate through matrix:");
		while (it.hasNext())													// TODO: wo ist das b???
		{
			//System.out.println("next()");
			output = it.next();
			System.out.println((String)output);
		}
	}

}