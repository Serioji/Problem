package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.cert.CertificateParsingException;
import java.util.ArrayList;


public class gameboard extends JFrame implements MouseListener {
    /**
     *
     * @author Vasil
     * @param "това е игралната дъска със координатите на съответните пешки и визуализирането на дъската "
     */
    public static final int TILE_SIDE_COUNT = 5;

    private Object[][] cirleCollection;
    private Object[][] cirleOutLine;
    private Object[][] leaderCollection;
    private Object selectedPiece;
    private Color orange = new Color(255, 165, 0);
    public static Color grey = new Color(128, 128, 128);

    public gameboard() {
        this.cirleCollection = new Object[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
        //Green
        this.cirleCollection[0][1]=(new Guard(0, 0,Color.GREEN));
        this.cirleCollection[0][2]=(new Guard(0, 1,Color.GREEN));
        this.cirleCollection[0][3]=(new Guard(0, 2,Color.GREEN));
        this.cirleCollection[0][4]=(new Guard(0, 3,Color.GREEN));
        //center
        this.cirleCollection[2][2]=(new Guard(2, 2,Color.gray));
        //Yellow
        this.cirleCollection[4][1]=(new Guard(4, 1,Color.YELLOW));
        this.cirleCollection[4][2]=(new Guard(4, 2,Color.YELLOW));
        this.cirleCollection[4][3]=(new Guard(4, 3,Color.YELLOW));
        this.cirleCollection[4][4]=(new Guard(4, 4,Color.YELLOW));


        this.leaderCollection = new Object[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
        this.leaderCollection[0][4]=(new Leader(0,4,Color.green));
        this.leaderCollection[4][0]=(new Leader(4,0,Color.yellow));

        this.cirleOutLine = new Object[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
        this.cirleOutLine[0][1]=(new CircleOutline(0, 0,Color.YELLOW));
        this.cirleOutLine[0][2]=(new CircleOutline(0, 1,Color.YELLOW));
        this.cirleOutLine[0][3]=(new CircleOutline(0, 2,Color.YELLOW));
        this.cirleOutLine[0][4]=(new CircleOutline(0, 3,Color.YELLOW));
        this.cirleOutLine[4][1]=(new CircleOutline(4, 1,Color.GREEN));
        this.cirleOutLine[4][2]=(new CircleOutline(4, 2,Color.GREEN));
        this.cirleOutLine[4][3]=(new CircleOutline(4, 3,Color.GREEN));
        this.cirleOutLine[4][4]=(new CircleOutline(4, 4,Color.GREEN));
        this.setSize(525, 525);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        /**
         *
         * @author Vasil
         * @param "визуализиране на игралните пешки върху бойното поле чрез два for цикъла и повикване на точната им позиция чрез row,col"
         */

        super.paint(g);
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {

                this.renderGameTile(g,row,col);

            }
        }

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {

                this.renderGamePiece(g,row,col);

            }
        }


    }
    private Color getTileColor(int row, int col) {
            if((row == 0 && (col == 4 || col==0)) || (row == 4 && (col==1 || col==3))) return orange;
            if((row==1 || row==3  )&& col!=2)return grey;
            if(col == 2 || row == 2) return Color.WHITE;

            {

        }

     return Color.BLACK;
    }

    private Object getBoardOutline(int row, int col) {
        return this.cirleOutLine[row][col];

    }
    private void renderGameTile(Graphics g, int row, int col) {
        Color tileColor = this.getTileColor(row, col);
        GameTile tile = new GameTile(row, col, tileColor);
        tile.render(g);
    }
    private Object getBoardCirle(int row, int col) {
        return this.cirleCollection[row][col];

    }
    private boolean hasBoardCirle(int row, int col) {
        return this.getBoardCirle(row, col) != null;
    }
    private Object getBoardLeader(int row, int col) {
        return this.leaderCollection[row][col];

    }
    private boolean hasBoardLeader(int row, int col) {
        return this.getBoardLeader(row, col) != null;
    }
    private boolean hasCircleOutline(int row, int col) {
        return this.getBoardOutline(row, col) != null;
    }

    private int getBoardDimensionBasedOnCoordinates(int coordinates) {
        return coordinates / GameTile.TILE_SIZE;
    }
   private void renderGamePiece(Graphics g, int row, int col) {

       if(this.hasCircleOutline(row,col)){
           CircleOutline p=(CircleOutline) this.getBoardOutline(row,col);
           p.render(g);

       }
        if(this.hasBoardCirle(row, col)) {

            Guard p0= (Guard) this.getBoardCirle(row, col);
            p0.render(g);

        }
        if(this.hasBoardLeader(row,col)){
            Leader p1=(Leader) this.getBoardLeader(row,col);
            p1.render(g);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardDimensionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimensionBasedOnCoordinates(e.getX());


        if(this.cirleCollection != null) {
            Guard p = (Guard) this.selectedPiece;
            p.move(row, col);
            Leader p1 = (Leader) this.selectedPiece;
            p1.move(row,col);
            this.cirleCollection = null;
            this.repaint();
        }
        if(this.hasBoardCirle(row, col)) {
            this.selectedPiece = this.getBoardCirle(row, col);
        }
        if(this.hasBoardLeader(row,col)){
            this.selectedPiece = this.getBoardLeader(row, col);
        }

    }


    @Override
    public void mousePressed(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}