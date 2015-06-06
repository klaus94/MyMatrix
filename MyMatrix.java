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
			if (p.x > biggestRowCount)
			{
				biggestRowCount = p.x;
			}
		}

		if (matrixEntries.size() != 0)
		{
			biggestRowCount += 1;
		}

		return biggestRowCount;		// because counting starts with 0
	}


	public int getColumnCount()
	{
		int biggestColCount = 0;

		for (Point p: matrixEntries.keySet())
		{
			if (p.y > biggestColCount)
			{
				biggestColCount = p.y;
			}
		}

		if (matrixEntries.size() != 0)
		{
			biggestColCount += 1;
		}

		return biggestColCount;		// because counting starts with 0
	}


	public int getObjectCount()
	{
		return matrixEntries.values().size();
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


	public class DepthFirstIterator implements Iterator<T>
	{
		private int row = 0;
		private int col = 0;
		private T next;

		public DepthFirstIterator()
		{
			while (matrixEntries.get(new Point(row, col)) == null)
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
			next = matrixEntries.get(new Point(row, col));
		}

		public T next()
		{
			T result;

			result = next;

			// go one field further
			if (row < MyMatrix.this.getRowCount() - 1)
			{
				row += 1;
			}
			else
			{
				col += 1;
				row = 0;
			}

			// search next element in matrix
			while (  (col < MyMatrix.this.getColumnCount()) && (MyMatrix.this.get(row, col) == null) )		// test first, if position is in bounds, after that test null
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

			next = matrixEntries.get(new Point(row, col));	

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


	public T get(int row, int column)
	{
		Point positionRequest = new Point(row, column);
		T entry = matrixEntries.get(positionRequest);
 
		// if requested row or col is out of bounds, throw exception
		if ( (row >= this.getRowCount()) || (column >= this.getColumnCount()) )		// <= and >= because counting starts with 0
		{
			throw new IllegalArgumentException("achtung achtung");
		}
		
		return entry;
	}


	public T put (int row, int column, T value)
	{
		Point positionRequest = new Point(row, column);
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