import java.util.*;

public class MatrixTest
{
	
	public static void main(String[] args)
	{
		Object output;

		Matrix<String> m = new MyMatrix<String>();
		System.out.println("feld erstellt");
		String a = "a";
		String b = "b";

		m.put(0,1,a);
		m.put(1,3,b);
		m.put(2,0,a);
		System.out.println("3 werte eingefügt");

		// teste 		
		System.out.println("RowCount: " + m.getRowCount());
		System.out.println("ColCount: " + m.getColumnCount());
		System.out.println("ObjectCount: " + m.getObjectCount());
		System.out.println("DistinctObjectCount: " + m.getDistinctObjectCount());


		System.out.println("aufzählung startet jetzt:");
		Iterator it = m.iterator();		while (it.hasNext())
		{
			output = it.next();
			if (output == null)
			{
				System.out.println("null");
			}
			else
			{
				System.out.println((String)output);	
			}
		}
	}

}