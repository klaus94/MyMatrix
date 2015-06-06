import java.util.*;

public class MatrixTest
{
	
	public static void main(String[] args)
	{
		Object output;

		Matrix<String> m = new MyMatrix<String>();
		System.out.println("feld erstellt");

		m.put(0,1,"a");
		m.put(1,3,"b");
		m.put(2,0,"a");
		System.out.println("3 werte eingefügt");

		// Leerzeile - muss ja auch schön sein
		System.out.println();

		// teste 		
		System.out.println("RowCount: " + m.getRowCount());							// 3
		System.out.println("ColCount: " + m.getColumnCount());						// 4
		System.out.println("ObjectCount: " + m.getObjectCount());					// 3
		System.out.println("DistinctObjectCount: " + m.getDistinctObjectCount());	// 2

		System.out.println("get(2, 0): " + m.get(2, 0));							// a1
		System.out.println("get(1, 2): " + m.get(1, 2));							// null
		//System.out.println("get(0, 4): " + m.get(0, 4));							// IllegalArgumentException


		System.out.println();

		Iterator it = m.iterator();													// instance of DepthFirstIterator
		if (it instanceof MyMatrix.DepthFirstIterator)
			System.out.println("it is instance of MyMatrix.DepthFirstIterator: true");
		else
			System.out.println("it is instance of MyMatrix.DepthFirstIterator: false");	

		System.out.println("iterate through matrix:");
		while (it.hasNext())
		{
			output = it.next();
			System.out.println((String)output);
		}

		System.out.println();

		System.out.println("put(2, 0, 'b'): " + m.put(2, 0, "b"));					// a
		System.out.println("put(0, 0, 'b'): " + m.put(0, 0, "b"));					// null

		System.out.println();
		System.out.println("contains('a'): " + m.contains("a"));
	}

}