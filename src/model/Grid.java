package model;

import complexNumbers.ComplexNumber;

import java.util.Arrays;

/**
 * Models a grid of individual GridCells
 * @author Daylen Smith
 * @version 2024
 */
public class Grid {
    private final double[] topLeft;
    private double edgeLength;
    private final int edgeCellCount;
    private final GridCell[][] cells;

    /**
     * Creates a new grid
     * @param topLeftX The x position of the top left corner of the bounding square
     * @param topLeftY The y position of the top left corner of the bounding square
     * @param edgeLength The edge length of the bounding square
     * @param edgeCellCount The amount of cells per edge
     */
    public Grid(final double topLeftX, final double topLeftY, final double edgeLength, final int edgeCellCount){
        this.topLeft = new double[]{topLeftX, topLeftY};
        this.edgeLength = edgeLength;
        this.edgeCellCount = edgeCellCount;
        this.cells = new GridCell[edgeCellCount][edgeCellCount];

        for (int i = 0; i < edgeCellCount; i++){
            for (int j = 0; j < edgeCellCount; j++){
                cells[i][j] = new GridCell(new ComplexNumber(0,0));
            }
        }

        this.updateGridCellCoordinates();
    }

    public double[] getTopLeft() {
        return topLeft;
    }

    public double getEdgeLength(){
        return this.edgeLength;
    }

    /**
     * Changes the position of the top left corner of the bounding square
     * @param dX The x displacement as a fraction of the edge length
     * @param dY The y displacement as a fraction of the edge length
     */
    public void translate(final double dX, final double dY){
        this.topLeft[0] += dX * edgeLength;
        this.topLeft[1] += dY * edgeLength;
    }

    public GridCell[][] getCells(){
        return this.cells;
    }

    /**
     * Updates the cells in the grid
     */
    public void update(){
        updateGridCellCoordinates();
        iterateGrid();
    }

    /**
     * Updates the coordinates of each cell in the grid
     */
    private void updateGridCellCoordinates(){
        double delta = this.edgeLength / (edgeCellCount - 1);

        for (int row = 0; row < edgeCellCount; row++){
            double currentRealCoordinate = this.topLeft[0] + delta * row;
            for (int col = 0; col < edgeCellCount; col++){
                double currentComplexCoordinate = this.topLeft[1] - delta * col;
                this.cells[row][col].setCoordinates(currentRealCoordinate, currentComplexCoordinate);
            }
        }
    }

    /**
     * Tells each cell to iterate
     */
    public void iterateGrid(){
        for (GridCell[] row: cells){
            for (GridCell col: row){
                col.iterate();
            }
        }
    }

    /**
     * Tells each cell to update their colors
     */
    public void updateColors(){
        for (GridCell[] row: cells){
            for (GridCell cell: row){
                cell.updateColor();
            }
        }
    }

    /**
     * Resizes the bounding square
     * @param topLeftX The new x position of the top left corner
     * @param topLeftY The new y position of the top left corner
     * @param newEdgeLength The new edge length
     */
    public void resize(final double topLeftX, final double topLeftY, final double newEdgeLength){
        this.topLeft[0] = topLeftX;
        this.topLeft[1] = topLeftY;
        this.edgeLength = newEdgeLength;
        updateGridCellCoordinates();
    }

    @Override
    public String toString() {
        return "Grid{" +
                "topLeft=" + Arrays.toString(topLeft) +
                ", edgeCellCount=" + edgeCellCount +
                ", cells=" + Arrays.toString(cells) +
                '}';
    }
}
