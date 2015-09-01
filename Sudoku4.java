import java.util.HashMap;


public class Sudoku4 {

	
	int grid [][] = {
			
			{2,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			
		/*  {3, 0, 0, 0, 0, 8, 4, 0, 0},
			{5, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 8, 7, 0, 0, 0, 0, 3, 1},
			{0, 0, 3, 0, 1, 0, 0, 8, 0},
			{9, 0, 0, 0, 6, 3, 0, 0, 5},
			{0, 5, 0, 0, 9, 0, 6, 0, 0},
			{1, 3, 0, 0, 0, 0, 2, 5, 0},
			{0, 0, 0, 0, 0, 0, 0, 7, 4},
			{0, 0, 0, 2, 0, 6, 3, 0, 0}
			
			*/
	};
	
	HashMap<Integer,Entry> posis=new HashMap<Integer,Entry>();
	
	public boolean isAllowed(int val,int index)
	{
		Entry e = posis.get(index);
		int row=e.getRow();
		int col=e.getCol();
		
		if(isPresentInBlock(val, row, col)|| isPresentInColumn(val, row)|| isPresentInRow(val, col))
		{
		//System.out.println("Not allowed at :"+row+" "+col+" "+val);
			return true;
		}
	//	System.out.println("Allowed");
		return false;
	}
	
	
	public void show()
	{
		for(int i=0;i<9;i++)
		{
		for(int j=0;j<9;j++)
		{
		 
		System.out.print(" " + grid[i][j] + " ");
		
		}
		System.out.println();
		}
		}
	
	public boolean solve(int index)
	{
		
//System.out.print("index : "+index+"  ");

	if(isFull())
	{
		show();
		System.exit(0);	
	}
		for(int val=1;val<10;val++)
		{
			if(!isAllowed(val,index))
			{
				assign(val,index);
				if(solve(index+1))
				{
					return true;	
				}
			}
			
		}
	unassign(index);
	return false;
	}
	
	
	public void assign(int val,int index)
	{
		Entry e = posis.get(index);
		int row=e.getRow();
		int col=e.getCol();
		grid[row][col] = val;
		//System.out.println("Assign val "+ val + " at  : " + row + " " + col);
	}
	
	public void unassign(int index)
	{
		Entry e = posis.get(index);
		int row=e.getRow();
		int col=e.getCol();
		grid[row][col]=0;
	//System.out.println("Backtrack at : "+row+" "+col);
	}
	
	public boolean isFull()
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
		{
				if(grid[i][j]==0)
				{
					return false;
				}
		}
		}
	return true;
	}
	
	
	public boolean isPresentInRow(int val, int column)
	{
	for(int i=0;i<9;i++)
	{
	if(val == grid[i][column])
	{
		//System.out.println("row return : " +i+" "+column);
	return true;
	}
	}
	 
	return false;
	}
	 
	public boolean isPresentInColumn(int val, int row)
	{
	for(int i=0;i<9;i++)
	{
	if(val == grid[row][i])
	{
		//System.out.println("col return : " +row+" "+i);
	return true;
	}
	}
	 
	return false;
	}
	public boolean isPresentInBlock(int val, int row,int column)
	{
	int i,j;
	 
	if(row<=2)
	{
	j= 0;
	if(column <= 2)
	{
	i=0;
	}
	else if(column <= 5)
	{
	i=3;
	}
	else
	{
	i=6;
	}
	 
	}
	else if(row<=5)
	{
	j = 3;
	if(column <= 2)
	{
	i=0;
	}
	else if(column <= 5)
	{
		i=3;
	}
		else
		{
		i=6;
		}
	 
	}
	else
	{
		j = 6;
	if(column <= 2)
	{
		i=0;
	}
	else if(column <= 5)
	{
		i=3;
	}
	else
	{
		i=6;
	}
	 
	}
	  
	for(int k=i;k<i+3;k++)
	{
		for(int l=j;l<j+3;l++)
		{
				if(val == grid[l][k])
					{
	//	System.out.println("block  return : " +k+" "+l);
					return true;
					}
	 
		}
	}
	return false;
	}
	
	public static void main(String args[])
	{
		try{
		Sudoku4 s=new Sudoku4();
		System.out.println("-----Start sudoku solver----");
		s.possibility();
		s.solve(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally	{
			System.out.println("--Solved--");
		}
	}
	
	/*
	 public boolean next(int key) 
	{
		Entry e = posis.get(key);
		return solve(e.getRow(),e.getCol());
	}
	 */
	 
	 public void possibility() 
	 {
		 Entry e;
		 int k=0;
		 
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(grid[i][j]== 0)
				{
					e=new Entry();
					e.setRow(i);
					e.setCol(j);
					posis.put(k, e);
					k++;
					//System.out.println(e.getRow()+" "+e.getCol());
				}	
			}
		}
		 
	 }
	
}
