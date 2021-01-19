package game;

import java.awt.*;


public class Leader  extends Figure{
    public Color color;
    /**
     *
     * @author Vasil
     * @param  "това е визуализирането на една от пешките на име Guard върху бойното поле със нейните параметри и цвят"
     */
    public Leader(int row, int col ,Color color) {
        this.color = color;
        this.row = row;
        this.col = col;

    }



    public void render(Graphics g) {

        int x = this.col * GameTile.TILE_SIZE;
        int y = this.row * GameTile.TILE_SIZE;

        g.setColor(this.color);
        g.fillRect(x+25,y+26,50,50);
    }

    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }


}