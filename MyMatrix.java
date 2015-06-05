import java.util.*;
import java.awt.Point;
import java.util.Iterator;

public class MyMatrix<T> implements Matrix<T>
{
	private Map<Point, T> matrixEntries;

	public MyMatrix()
	{
		matrixEntries = new HashMap<Point, T>();
	}

	public int getRowCount()
	{
		int biggestRowCount = 0;

		for (Point p: matrixEntries.keySet())
		{
			if (p.y > biggestRowCount)
			{
				biggestRowCount = p.y;
			}
		}

		return biggestRowCount + 1;		// because counting starts with 0
	}


	public int getColumnCount()
	{
		int biggestColCount = 0;

		for (Point p: matrixEntries.keySet())
		{
			if (p.x > biggestColCount)
			{
				biggestColCount = p.x;
			}
		}

		return biggestColCount + 1;		// because counting starts with 0
	}


	public int getObjectCount()
	{
		return matrixEntries.values().size();		// evt auf null prüfen ???
	}


	public int getDistinctObjectCount()
	{
		Collection<T> entries = matrixEntries.values();
		Set<T> entrieSet = new HashSet<T>();
		for (T entry: entries)
		{
			entrieSet.add(entry);
		}

		return entrieSet.size();
	}


	private class DepthFirstIterator implements Iterator<T>
	{
		private int row = 0;
		private int col = 0;
		private T next;// = matrixEntries.get(new Point(0,0));

		public DepthFirstIterator()
		{
			while (matrixEntries.get(new Point(col, row)) == null)
			{
				if (row < MyMatrix.this.getRowCount() - 1)
				{
					row += 1;
				}
				else
				{
					col += 1;
					row = 0;
				}
			}
			next = matrixEntries.get(new Point(col, row));
			//System.out.println("first element: " + next + " at ("+ col + "/" + row + ")");
		}

		public T next()
		{
			T result;

			result = next;

			// ein feld weiter rücken
			if (row < MyMatrix.this.getRowCount() - 1)
			{
				row += 1;
			}
			else
			{
				col += 1;
				row = 0;
			}

			// suche nach nächstem element in der matrix
			while ( (MyMatrix.this.get(row, col) == null) && (col != MyMatrix.this.getColumnCount() - 1) )
			{
				//System.out.println("MyMatrix.this.get("+col+", "+row+"): " + MyMatrix.this.get(row, col));
				if (row < MyMatrix.this.getRowCount() - 1)
				{
					row += 1;
				}
				else
				{
					col += 1;
					row = 0;
				}
				//System.out.println("new = (col " + col + " row " + row + ")");
			}

			next = matrixEntries.get(new Point(col, row));

			return result;

		}

		public boolean hasNext()
		{
			return next != null;
		}

		public void remove() 
		{
			throw new UnsupportedOperationException(); 
		}
	}

	public Iterator<T> iterator()
	{
		return new DepthFirstIterator();
	}


	// ACHTUNG: get(row, column)  <-> matrixEntries.get(new Point(col, row)) !!!
	public T get(int row, int column)
	{
		Point positionRequest = new Point(column, row);
		T entry = matrixEntries.get(positionRequest);
 
		if ( (row > this.getRowCount()) || (column > this.getColumnCount()) )
		{
			throw new IllegalArgumentException("achtung achtung");
		}
		
		return entry;
	}


	public T put (int row, int column, T value)
	{
		Point positionRequest = new Point(column, row);
		T lastValue;
		
		lastValue = matrixEntries.get(positionRequest);		// returns null, if not existing
		
		matrixEntries.put(positionRequest, value);

		return lastValue;
	}


	public boolean contains(T value)
	{
		return matrixEntries.containsValue(value);
	}

}