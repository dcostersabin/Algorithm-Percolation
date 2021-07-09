/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] grid;
    private final WeightedQuickUnionUF gridMap;
    private final int n;
    private int openCell;
    private final int top ;
    private final int bottom;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        gridMap = new WeightedQuickUnionUF(n * n + 2);
        grid = new boolean[n*n+2];
        this.n = n;
        openCell = 0;
        top = 0;
        bottom = n * n + 1;
    }

    private void checkRowsColumn(int row, int col) {
        if (row < 1 || row > this.n || col < 1 || col > this.n) {
            throw new IllegalArgumentException();
        }
    }

    private int indexOf(int row , int col){
        checkRowsColumn(row,col);
        return (row - 1) * n + (col -1);
    }

    public boolean isOpen(int row , int col)
    {
        checkRowsColumn(row , col);
        return grid[indexOf(row ,col)];
    }

    public void open(int row, int col) {
        checkRowsColumn(row,col);
        int current_cell_index = indexOf(row, col);
        if(!grid[current_cell_index])
        {
            grid[current_cell_index] = true;
            this.openCell ++ ;

            if(row == 1) gridMap.union(current_cell_index,top);

            if(row == this.n) gridMap.union(current_cell_index,bottom);

            if(row > 1 && isOpen(row - 1 ,col))
            {
                assert(current_cell_index > n);
                gridMap.union(current_cell_index,current_cell_index - n);
            }

            if (row < this.n && isOpen(row + 1 , col))
            {
                assert(current_cell_index + n < n * n);
                gridMap.union(current_cell_index , current_cell_index + n);
            }

            if(col > 1 && isOpen(row, col - 1))
            {
                gridMap.union(current_cell_index,current_cell_index- 1);
            }

            if(col < this.n && isOpen(row, col + 1))
            {
                gridMap.union(current_cell_index,current_cell_index + 1);
            }

        }

    }

    public boolean isFull(int row , int col)
    {
        checkRowsColumn(row, col);
        if(!isOpen(row,col)) return false;

        return gridMap.find(indexOf(row,col)) == gridMap.find(top);
    }

    public int numberOfOpenSites()
    {
        return this.openCell;
    }

    public boolean percolates()
    {
        return gridMap.find(top) == gridMap.find(bottom);
    }

    public static void main(String[] args) {

    }
}

