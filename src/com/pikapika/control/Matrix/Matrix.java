/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Ronaldo Hanh
 */
public class Matrix {

    private int matrix[][];
    private static final int CONST_VALUE = 0; //Mac dinh nhung o khong co anh co value  = 0
    private int row;
    private int col;
    private int value;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(int row, int col) {
        this.setCol(col);
        this.setRow(row);
        createMatrix();
    }

    /*Tao Random Matrix*/
    public void createMatrix() {
        this.matrix = new int[row][col];
        /* Tao random Matrix */
        Random random = new Random();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = random.nextInt(row * col / 4 - 1) + 1;
            }
        }

        /*Dinh dang lai Matrix */
        for (int i = 1; i <= row * col / 4; i++) {
            if (demPT(i) % 2 != 0) {
                change(i);
            }
        }
    }

    /*Dem so phan tu giong nhau */
    private int demPT(int value) {
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == value) {
                    count++;
                }
            }
        }
        return count;
    }

    /*Thay doi gia tri cua phan tu de thanh cac cap*/
    private void change(int value) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (this.matrix[i][j] == value) {
                    this.matrix[i][j]++;
                }
            }
        }
    }

    /*Giai thuat kiem tra 2 diem da click vao co duong noi voi nhau hay khong */
    /*TH1: Cung nam tren 1 hang hoac 1 cot*/
    private boolean checkLineX(int y1, int y2, int x) {
         //System.out.println("checkLineX(p1.y, p2.y, p1.x)");
        if (this.matrix[x][y1] != this.matrix[x][y2]) {
            return false;
        }
        int maxCol = Math.max(y1, y2);
        int minCol = Math.min(y1, y2);
        if ((minCol + 1) == maxCol) {
            return true;
        }
        for (int i = minCol + 1; i < maxCol; i++) {
            if (this.matrix[x][i] != CONST_VALUE) {
                return false;
            }
        }
        return true;
    }

    private boolean checkLineY(int x1, int x2, int y) {
         //System.out.println("checkLineY(p1.y, p2.y, p1.x)");
        if (this.matrix[x1][y] != this.matrix[x2][y]) {
            return false;
        }
        int maxRow = Math.max(x1, x2);
        int minRow = Math.min(x1, x2);
        if ((minRow + 1) == maxRow) {
            return true;
        }
        for (int i = minRow + 1; i < maxRow; i++) {
            if (this.matrix[i][y] != CONST_VALUE) {
                return false;
            }
        }
        return true;
    }
    /*TH2: Xet duyet cac duong di theo chieu ngang, doc trong pham vi chu 
     nhat */

    //Xet duyet theo chieu ngang
    private boolean checkRecX(Point p1, Point p2) {
         //System.out.println("checkRecX(p1, p2)");
        if (this.matrix[p1.x][p1.y] != this.matrix[p2.x][p2.y]) {
            return false;
        }
        Point pMinY = p1;
        Point pMaxY = p2;
        if (p1.y > p2.y) {
            pMinY = p2;
            pMaxY = p1;
        }
        for (int i = pMinY.y; i < pMaxY.y; i++) {
            if (i > pMinY.y && this.matrix[pMinY.x][i] != CONST_VALUE) {
                return false;
            }
            if ((this.matrix[pMaxY.x][i] == CONST_VALUE || i == pMaxY.y)
                    && checkLineX(i, pMaxY.y, pMaxY.x)
                    && checkLineY(pMinY.x, pMaxY.x, i)) {
                return true;
            }
        }
        return false;
    }

    //Xet duyet theo chieu doc hinh chu nhat
    private boolean checkRecY(Point p1, Point p2) {
        //System.out.println("checkRecY(p1, p2)");
        if (this.matrix[p1.x][p1.y] != this.matrix[p2.x][p2.y]) {
            return false;
        }
        Point pMinX = p1;
        Point pMaxX = p2;
        if (p1.x > p2.x) {
            pMinX = p2;
            pMaxX = p1;
        }
        for (int i = pMinX.x; i < pMaxX.x; i++) {
            if (i > pMinX.x && this.matrix[i][pMinX.y] != CONST_VALUE) {
                return false;
            }
            if ((this.matrix[i][pMaxX.y] == CONST_VALUE || i == pMaxX.x)
                    && checkLineX(pMinX.y, pMaxX.y, i)
                    && checkLineY(i, pMaxX.x, pMaxX.y)) {
                return true;
            }
        }
        return false;
    }

    /*TH3: Xet mo rong theo hang ngang, hang doc*/
    //Xet theo chieu ngang
    //type = -1 la di sang trai type=1 la di sang phai 
    private boolean checkMoreLineX(Point p1, Point p2, int type) {
         //System.out.println("checkMoreLineX(p1, p2, "+ type + " )");
        if (this.matrix[p1.x][p1.y] != this.matrix[p2.x][p2.y]) {
            return false;
        }
        Point pMinY = p1, pMaxY = p2;
        if (p1.y > p2.y) {
            pMinY = p2;
            pMaxY = p1;
        }
        int y = pMaxY.y + type;
        int _row = pMinY.x;
        int colFinish = pMaxY.y;
        if (type == -1) {
            colFinish = pMinY.y;
            y = pMinY.y + type;
            _row = pMaxY.x;
        }
        if ((this.matrix[_row][colFinish] == CONST_VALUE || pMinY.y == pMaxY.y)
                && checkLineX(pMinY.y, pMaxY.y, _row)) {
            //System.out.println(pMinY.x + "  " + y);
            if (y > this.col - 1) {
                return false;
            }
            if (y < 0) {
                return false;
            }
            while (this.matrix[pMinY.x][y] == CONST_VALUE
                    && this.matrix[pMaxY.x][y] == CONST_VALUE) {
                if (checkLineY(pMinY.x, pMaxY.x, y)) {
                    return true;
                }
                y += type;
                if (y > this.col - 1) {
                    return false;
                }
                if (y < 0) {
                    return false;
                }
            }
        }
        return false;
    }
    // Xet mo rong theo chieu doc type = 1 ( di len tren) type = -1 (di xuong 
    // duoi)

    private boolean checkMoreLineY(Point p1, Point p2, int type) {
        //System.out.println("checkMoreLineY(p1, p2, "+ type + " )");
        if (this.matrix[p1.x][p1.y] != this.matrix[p2.x][p2.y]) {
            return false;
        }
        Point pMinX = p1, pMaxX = p2;
        if (p1.x > p2.x) {
            pMinX = p2;
            pMaxX = p1;
        }
        int x = pMaxX.x + type;
        int _col = pMinX.y;
        int rowFinish = pMaxX.x;
        if (type == -1) {
            rowFinish = pMinX.x;
            x = pMinX.x + type;
            _col = pMaxX.y;
        }
        if ((this.matrix[rowFinish][_col] == CONST_VALUE || pMinX.x == pMaxX.x)
                && checkLineY(pMinX.x, pMaxX.x, _col)) {
            //System.out.println(x + "  " + pMinX.y);
            if (x > this.row - 1) {
                return false;
            }
            if (x < 0) {
                return false;
            }
            while (this.matrix[x][pMinX.y] == CONST_VALUE
                    && this.matrix[x][pMaxX.y] == CONST_VALUE) {
                if (checkLineX(pMinX.y, pMaxX.y, x)) {
                    return true;
                }
                x += type;
                if (x > this.row - 1) {
                    return false;
                }
                if (x < 0) {
                    return false;
                }
                //System.out.println("1111111    " + x);
            }
        }
        return false;
    }
    
    
    /*Kiem tra bao ngoai*/
    private boolean checkSide(Point p1, Point p2){
        if((p1.x == p2.x) && (p1.x == 0) && (this.matrix[p1.x][p1.y] == this.matrix[p2.x][p2.y])){
            return true;
        }
        if((p1.x == p2.x) && (p1.x == row - 1) && (this.matrix[p1.x][p1.y] == this.matrix[p2.x][p2.y])){
            return true;
        }
        if((p1.y == p2.y) && (p1.y == col - 1) && (this.matrix[p1.x][p1.y] == this.matrix[p2.x][p2.y])){
            return true;
        }
        if((p1.y == p2.y) && (p1.y == 0) && (this.matrix[p1.x][p1.y] == this.matrix[p2.x][p2.y])){
            return true;
        }
        return false;
    }
    
    /*Algorithm cho 2 diem*/
    public boolean Algorithm(Point p1, Point p2) {
        //Check side
         if(this.checkSide(p1, p2)){
            return true;
        }
        // Kiem tra voi hang x , cot y1,y2 
        if (p1.x == p2.x) {
            if (this.checkLineX(p1.y, p2.y, p1.x)) {
               
                return true;
            }
        }
        // Kiem tra voi cot y , hang x1,hang x2 
        if (p1.y == p2.y) {
            if (this.checkLineY(p1.x, p2.x, p1.y)) {
               
                return true;
            }
        }
        // Xet duong di theo chieu ngang 
        if (this.checkRecX(p1, p2)) {
           
            return true;
        }
        // Xet duong fi theo chieu doc 
        if (this.checkRecY(p1, p2)) {
            
            return true;
        }
        // xet su mo rong theo chieu ngang ben phai 
        if (this.checkMoreLineX(p1, p2, 1)) {
           
            return true;
        }
        // xet su mo rong theo chieu ngang ben trai 
        if (this.checkMoreLineX(p1, p2, -1)) {
            //System.out.println("checkMoreLineX(p1, p2, -1)");
            return true;
        }
        // Xet su mo rong theo chieu doc di len tren 
        if (this.checkMoreLineY(p1, p2, 1)) {
           
            return true;
        }
        // Xet su mo rong theo chieu doc di xuong duoi 
        if (this.checkMoreLineY(p1, p2, -1)) {
            //System.out.println("checkMoreLineY(p1, p2, -1)");
            return true;
        }
       
        return false; // tra ve false neu khong tim thay duong di 
    }

   /* public static void main(String[] args) {
        Matrix m = new Matrix(6, 10);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                int x = j + 1;
                if (x == 10) {
                    System.out.print(m.matrix[i][j] + "\n");
                } else {
                    System.out.print(m.matrix[i][j] + "  ");
                }
            }
        }
        Point p1 = new Point();
        Point p2 = new Point();
        p1.x = 0;
        p1.y = 0;
        p2.x = 3;
        p2.y = 0;
        //System.out.println(m.Algorithm(p1, p2));
    }*/
}
