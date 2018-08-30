package com.elinmalikzade;

public class Grid {
    private GridCell[][] cells;

    public Grid(int m, int n) {
        // Convert grid sizes into two dimentional array sizes
        int temp = m + 1;
        m = n + 1;
        n = temp;
        cells = new GridCell[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new GridCell();
            }
        }
    }

    public GridCell[][] getCells() {
        return cells;
    }

    // Convert coordinaties in the given grid into two dimentional array indexes
    public int[] convertMartianCoordsToInternal(int x, int y) {
        int[] coords = new int[2];
        coords[0] = cells.length - (y + 1);
        coords[1] = x;

        return coords;
    }

    // Convert two dimentional array indexes into grid coorinates
    public int[] convertInternalCoordsToMartian(int x, int y) {
        int[] coords = new int[2];
        coords[0] = y;
        coords[1] = cells.length - (x + 1);

        return coords;
    }
}
