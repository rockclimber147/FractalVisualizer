package model;

import complexNumbers.ComplexNumber;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Grid {
    private final double[] topLeft;
    private double edgeLength;
    private final int edgeCellCount;

    private final GridCell[][] cells;
    public Grid(double topLeftX, double topLeftY, double edgeLength, int edgeCellCount){
        this.topLeft = new double[]{topLeftX, topLeftY};
        this.edgeLength = edgeLength;
        this.edgeCellCount = edgeCellCount;
        this.cells = new GridCell[edgeCellCount][edgeCellCount];

        for (int i = 0; i < edgeCellCount; i++){
            for (int j = 0; j < edgeCellCount; j++){
                cells[i][j] = new GridCell(new ComplexNumber(0,0));
            }
        }

        this.updateGridCoordinates();
    }

    public double[] getTopLeft() {
        return topLeft;
    }

    public double getEdgeLength(){
        return this.edgeLength;
    }


    public void translate(double dX, double dY){
        this.topLeft[0] += dX;
        this.topLeft[1] += dY;
    }

    public GridCell[][] getCells(){
        return this.cells;
    }

    public void update(){
        updateGridCoordinates();
        iterateGrid();
    }

    private void updateGridCoordinates(){
        double realDelta = this.edgeLength / (edgeCellCount - 1);
        double complexDelta = this.edgeLength / (edgeCellCount - 1);

        for (int row = 0; row < edgeCellCount; row++){
            double currentRealCoordinate = this.topLeft[0] + realDelta * row;
            for (int col = 0; col < edgeCellCount; col++){
                double currentComplexCoordinate = this.topLeft[1] - complexDelta * col;
                this.cells[row][col].setCoordinates(currentRealCoordinate, currentComplexCoordinate);
            }
        }
    }

    private void iterateGrid(){
        for (GridCell[] row: cells){
            for (GridCell col: row){
                col.iterate();
            }
        }
    }

    public void resize(double topLeftX, double topLeftY, double newEdgeLength){
        this.topLeft[0] = topLeftX;
        this.topLeft[1] = topLeftY;
        this.edgeLength = newEdgeLength;
        updateGridCoordinates();
    }

    public String getStringRepresentation(){
        StringBuilder output = new StringBuilder();
        DecimalFormat format = new DecimalFormat("0.00");
        for (int col = 0; col < edgeCellCount; col++){
            for (int row = 0; row < edgeCellCount; row++){
                double[] currentCoords = cells[row][col].getDoubleCoords();
                output.append("[")
                        .append(format.format(currentCoords[0]))
                        .append(",")
                        .append(format.format(currentCoords[1]))
                        .append("]");
            }
            output.append("\n");
        }
        return output.toString();
    }

    @Override
    public String toString() {
        return "Grid{" +
                "topLeft=" + Arrays.toString(topLeft) +
                ", edgeCellCount=" + edgeCellCount +
                ", cells=" + Arrays.toString(cells) +
                '}';
    }

    public static void main(String[] args) {
        Grid grid = new Grid(0,2, 2, 8);
        grid.update();
        System.out.println(grid.getStringRepresentation());
    }
}
