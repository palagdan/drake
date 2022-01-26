package backend;

public class Offset2D {

    public final int x,y;
    public Offset2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equalsTo(int x, int y){
        if((this.x != x && this.y != y )||(this.x != x || this.y != y)) return false;

        return true;
    }

    public Offset2D yFlipped(){
        int negative = this.y * -1;

        return new Offset2D(this.x, negative);
    }

}
