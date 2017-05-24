/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pikapika.control;

import com.pikapika.utils.Utils;
import com.pikapika.view.Pikachu;
import java.util.Random;
import java.util.Scanner;

import static com.pikapika.utils.Utils.PIKACHU_NUMBER;

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

    // them phuong thuc set value tai toa do cua pikachu

    public void setXY(Pikachu pikachu, int value) {
        this.matrix[pikachu.getXPoint()][pikachu.getYPoint()] = value;
    }

    public void setXY(int x, int y, int value) {
        this.matrix[x][y] = value;
    }

    public int getXY(Pikachu pikachu) {
        return matrix[pikachu.getXPoint()][pikachu.getYPoint()];
    }

    public Matrix(int row, int col) {
        this.setCol(col);
        this.setRow(row);
        renderMatrix();
    }

    /*Tao Random Matrix*/
    public int[][] renderMatrix() {
        this.matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = CONST_VALUE;
            }
        }
        /* Tao random Matrix */
        Random random = new Random();
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {

                matrix[i][j] = random.nextInt(PIKACHU_NUMBER) + 1;
            }
        }

        Utils.debug(getClass(),(row - 2) * (col - 2) / 4 - 1+"");

        /*Dinh dang lai Matrix */
        for (int i = 1; i <= PIKACHU_NUMBER+1; i++) {
            if (demPT(i) % 2 != 0) {
                change(i);
            }
        }
        return this.matrix;
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
        int minCol = Math.min(y1, y2);
        int maxCol = Math.max(y1, y2);
        for (int y = minCol + 1; y < maxCol; y++) {
            if (matrix[x][y] != CONST_VALUE) {
                return false;
            }
        }
        return true;
    }

    private boolean checkLineY(int x1, int x2, int y) {
        int maxRow = Math.max(x1, x2);
        int minRow = Math.min(x1, x2);
        for (int x = minRow + 1; x < maxRow; x++) {
            if (this.matrix[x][y] != CONST_VALUE) {
                return false;
            }
        }
        return true;
    }
    /*TH2: Xet duyet cac duong di theo chieu ngang, doc trong pham vi chu 
     nhat */

    //Xet duyet theo chieu ngang
    private boolean checkRecX(Pikachu p1, Pikachu p2) {
        Pikachu pMinY = p1;
        Pikachu pMaxY = p2;
        if (p1.getYPoint() > p2.getYPoint()) {
            pMinY = p2;
            pMaxY = p1;
        }
        for (int y = pMinY.getYPoint(); y <= pMaxY.getYPoint(); y++) {
            if (y > pMinY.getYPoint() && matrix[pMinY.getXPoint()][y] != CONST_VALUE) {
                return false;
            }
            if ((matrix[pMaxY.getXPoint()][y] == CONST_VALUE || y == pMaxY.getYPoint())
                    && checkLineY(pMinY.getXPoint(), pMaxY.getXPoint(), y)
                    && checkLineX(y, pMaxY.getYPoint(), pMaxY.getXPoint())) {
                return true;
            }
        }
        return false;
    }

    //Xet duyet theo chieu doc hinh chu nhat
    private boolean checkRecY(Pikachu p1, Pikachu p2) {
        System.out.println("check rect y");
        Pikachu pMinX = p1;
        Pikachu pMaxX = p2;
        if (p1.getXPoint() > p2.getXPoint()) {
            pMinX = p2;
            pMaxX = p1;
        }
        for (int x = pMinX.getXPoint(); x <= pMaxX.getXPoint(); x++) {
            System.out.println(x + "   " + pMinX.getXPoint() + "  " + pMinX.getYPoint());
            if (x > pMinX.getXPoint() && matrix[x][pMinX.getYPoint()] != CONST_VALUE) {
                
             //1System.out.println(x + "   "+ pMinX.getXPoint());
             return false;
             }
            if ((matrix[x][pMaxX.getYPoint()] == CONST_VALUE || x == pMaxX.getXPoint())
                    && checkLineX(pMinX.getYPoint(), pMaxX.getYPoint(), x)
                    && checkLineY(x, pMaxX.getXPoint(), pMaxX.getYPoint())) {

                return true;
            }
        }
        return false;
    }

    /*TH3: Xet mo rong theo hang ngang, hang doc*/
    //Xet theo chieu ngang
    //type = -1 la di sang trai type=1 la di sang phai 
    private boolean checkMoreLineX(Pikachu p1, Pikachu p2, int type) {
        Pikachu pMinY = p1, pMaxY = p2;
        if (p1.getYPoint() > p2.getYPoint()) {
            pMinY = p2;
            pMaxY = p1;
        }
        int y = pMaxY.getYPoint() + type;
        int _row = pMinY.getXPoint();
        int colFinish = pMaxY.getYPoint();
        if (type == -1) {
            colFinish = pMinY.getYPoint();
            y = pMinY.getYPoint() + type;
            _row = pMaxY.getXPoint();
        }
        if ((this.matrix[_row][colFinish] == CONST_VALUE || pMinY.getYPoint() == pMaxY.getYPoint())
                && checkLineX(pMinY.getYPoint(), pMaxY.getYPoint(), _row)) {
            //System.out.println(pMinY.x + "  " + y);
            while (this.matrix[pMinY.getXPoint()][y] == CONST_VALUE
                    && this.matrix[pMaxY.getXPoint()][y] == CONST_VALUE) {
                if (checkLineY(pMinY.getXPoint(), pMaxY.getXPoint(), y)) {
                    return true;
                }
                y += type;
            }
        }
        return false;
    }
    // Xet mo rong theo chieu doc type = 1 ( di len tren) type = -1 (di xuong 
    // duoi)

    private boolean checkMoreLineY(Pikachu p1, Pikachu p2, int type) {
        Pikachu pMinX = p1, pMaxX = p2;
        if (p1.getXPoint() > p2.getXPoint()) {
            pMinX = p2;
            pMaxX = p1;
        }
        int x = pMaxX.getXPoint() + type;
        int _col = pMinX.getYPoint();
        int rowFinish = pMaxX.getXPoint();
        if (type == -1) {
            rowFinish = pMinX.getXPoint();
            x = pMinX.getXPoint() + type;
            _col = pMaxX.getYPoint();
        }
        if ((this.matrix[rowFinish][_col] == CONST_VALUE || pMinX.getXPoint() == pMaxX.getXPoint())
                && checkLineY(pMinX.getXPoint(), pMaxX.getXPoint(), _col)) {
            //System.out.println(x + "  " + pMinX.y);
            while (this.matrix[x][pMinX.getYPoint()] == CONST_VALUE
                    && this.matrix[x][pMaxX.getYPoint()] == CONST_VALUE) {
                if (checkLineX(pMinX.getYPoint(), pMaxX.getYPoint(), x)) {
                    return true;
                }
                x += type;

            }
        }
        return false;
    }
    /*Algorithm cho 2 diem*/

    public boolean algorithm(Pikachu p1, Pikachu p2) {
        if (matrix[p1.getXPoint()][p1.getYPoint()] == matrix[p2.getXPoint()][p2.getYPoint()]) {
            if (p1.getXPoint() == p2.getXPoint()) {
                if (this.checkLineX(p1.getYPoint(), p2.getYPoint(), p1.getXPoint())) {
                    return true;
                }
            }
            // Kiem tra voi cot y , hang x1,hang x2 
            if (p1.getYPoint() == p2.getYPoint()) {
                if (this.checkLineY(p1.getXPoint(), p2.getXPoint(), p1.getYPoint())) {

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
        }
        return false; // tra ve false neu khong tim thay duong di 
    }

    public boolean canPlay(){
        for (int i = 1; i < row-1; i++){
            for ( int j = 1; j < col-1;j++){
                if (matrix[i][j]!=0){
                    for (int m = 1 ; m < row -1; m++){
                        for (int n = 1; n < col-1; n++){
                            Utils.debug(getClass(),i+":"+j+" -> " + m + ":"+n);
                            if ((m!=i || n!=j) && matrix[m][n]!=0 && matrix[m][n] == matrix[i][j]){
                                if (algorithm(new Pikachu(m,n),new Pikachu(i,j))){
                                    Utils.debug(getClass(),"Go: "+i+":"+j+" -> " + m + ":"+n);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
