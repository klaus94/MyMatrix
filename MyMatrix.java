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

		return biggestRowCount;
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

		return biggestColCount;
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
		private T next = matrixEntries.get(new Point(0,0));

		public T next()
		{
			T result;

			result = matrixEntries.get(new Point(row, col));
			

			// vorerst nur einfache Schritte. null wird noch nicht herausgefiltert.
			if (row < MyMatrix.this.getRowCount() - 1)
			{
				row += 1;		// eine Zeile tiefer gehen
			}
			else
			{
				col += 1;		// neue Spalte anfangen
				row = 0;
			}
			
			return result;
		}

		public boolean hasNext()
		{
			return (row != MyMatrix.this.getRowCount() - 1) && (col != MyMatrix.this.getColumnCount() - 1);
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

		if (entry == null)
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