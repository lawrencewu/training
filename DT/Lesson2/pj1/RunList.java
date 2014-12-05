/**
 * Created by z_wu on 11/30/2014.
 */
public class RunList {
    Run head;
    Run tail;
    int size = 0;

    public RunList(){

    }

    public RunList(PixImage.Color[] colors, int[] runLength){
        for (int i = 0; i < runLength.length; i++) {
            add(colors[i], runLength[i]);
        }
    }

    public  void add(PixImage.Color c, int runLength){
        check(c, runLength);
        final Run t = tail;
        Run n = new Run(c, runLength);
        if(head == null) {
            head = n;
        }
        else {
            t.setNext(n);
            n.setPrev(t);
        }
        tail = n;
        size ++;
    }
    public int size() {
        return size;
    }

    private void check(PixImage.Color c, int length){
        if(c.getBlue() <0 || c.getBlue() > 255 || c.getRed() < 0 || c.getRed() > 255 || c.getGreen() < 0 ||c.getGreen() > 255){
            throw new IllegalArgumentException("Color's value should be 0...255");
        }
        if(length < 0){
            throw new IllegalArgumentException("Run length should be greater than 0");
        }
    }
}
