package com.mycompany.newlabyrinth;

public class Position {

    private int posX;
    private int posY;

    public Position(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public int getX() {
        return this.posX;
    }

    public int getY() {
        return this.posY;
    }

    public Position getRight() {
        return new Position(this.posX, this.posY + 1);

    }

    public Position getLeft() {
        return new Position(this.posX, this.posY - 1);

    }

    public Position getDown() {
        return new Position(this.posX + 1, this.posY);

    }

    public Position getUp() {
        return new Position(this.posX - 1, this.posY);

    }

     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }
        Position input = (Position) obj;
        if (this.posX != input.posX) {
            return false;
        }

        if(this.posY != input.posY) {
            return false;
        }

        return true;
    }
    
    
    
}
