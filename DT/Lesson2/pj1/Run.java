/**
 * Created by z_wu on 12/1/2014.
 */
public class Run {
    private PixImage.Color c;
    private int length;
    private Run next;
    private Run prev;

    public Run(PixImage.Color c, int length) {
        this.c = c;
        this.length = length;
    }

    public PixImage.Color getColor() {
        return c;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length){
        this.length = length;
    }

    public void setPrev(Run prev) {
        this.prev = prev;
    }

    public Run getPrev() {
        return this.prev;
    }

    public void setNext(Run next){
        this.next = next;
    }

    public Run getNext() {
        return this.next;
    }
}
