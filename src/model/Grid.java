package model;

import complexNumbers.ComplexNumber;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Grid {
    private final double[] topLeft;
    private final double[] bottomRight;

    private final int edgeCellCount;

    private final GridCell[][] cells;
    public Grid(double topLeftX, double topLeftY, double edgeLength, int edgeCellCount){
        this.topLeft = new double[]{topLeftX, topLeftY};
        this.bottomRight = new double[]{topLeftX + edgeLength, topLeftY - edgeLength};
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

    public double[] getBottomRight() {
        return bottomRight;
    }

    public void translate(double dX, double dY){
        this.topLeft[0] += dX;
        this.topLeft[1] += dY;
        this.bottomRight[0] += dX;
        this.bottomRight[1] += dY;
    }

    public GridCell[][] getCells(){
        return this.cells;
    }

    public void update(int iterationCount){
        updateGridCoordinates();
        iterateGrid(iterationCount);
    }

    private void updateGridCoordinates(){
        double realDelta = (this.bottomRight[0] - this.topLeft[0]) / (edgeCellCount - 1);
        double complexDelta = (this.bottomRight[1] - this.topLeft[1]) / (edgeCellCount - 1);

        for (int row = 0; row < edgeCellCount; row++){
            double currentRealCoordinate = this.topLeft[0] + realDelta * row;
            for (int col = 0; col < edgeCellCount; col++){
                double currentComplexCoordinate = this.topLeft[1] + complexDelta * col;
                this.cells[row][col].setCoordinates(currentRealCoordinate, currentComplexCoordinate);
            }
        }
    }

    private void iterateGrid(int iterationCount){
        for (GridCell[] row: cells){
            for (GridCell col: row){
                col.iterate(iterationCount);
            }
        }
    }

    public void resize(double topLeftX, double topLeftY, double bottomRightX, double bottomRightY){
        this.topLeft[0] = topLeftX;
        this.topLeft[1] = topLeftY;
        this.bottomRight[0] = bottomRightX;
        this.bottomRight[1] = bottomRightY;

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
                ", bottomRight=" + Arrays.toString(bottomRight) +
                ", edgeCellCount=" + edgeCellCount +
                ", cells=" + Arrays.toString(cells) +
                '}';
    }

    public static void main(String[] args) {
        Grid grid = new Grid(0,2, 2, 8);
        grid.update(5);
        System.out.println(grid.getStringRepresentation());
    }
}
