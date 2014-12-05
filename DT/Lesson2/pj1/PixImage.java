/* PixImage.java */

import java.util.ArrayList;
import java.util.List;

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

  /**
   *  Define any variables associated with a PixImage object here.  These
   *  variables MUST be private.
   */

    private int width;
    private int height;
    private Pixel[][] pixels;
    private final short[][] xVector = new short[][] {{-1,0,1},{-2,0,2},{-1,0,1}};
    private final short[][] yVector = new short[][] {{1,2,1},{0,0,0},{-1,-2,-1}};
    /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   */
  public PixImage(int width, int height) {
    // Your solution here.
      this.width = width;
      this.height = height;
      pixels = new Pixel[width][height];
      for (int i = 0; i < width; i++) {
          for (int j = 0; j < height; j++) {
              Color c = new Color((short)0, (short)0, (short)0);
              pixels[i][j] = new Pixel(i, j, c);
          }
      }
  }

   public Pixel[][] getPixels(){
       return this.pixels;
   }
  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    // Replace the following line with your solution.
    return this.width;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return this.height;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   */
  public short getRed(int x, int y) {
    // Replace the following line with your solution.
      return pixels[x][y].getColor().getRed();
  }

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   */
  public short getGreen(int x, int y) {
    // Replace the following line with your solution.
    return pixels[x][y].getColor().getGreen();
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   */
  public short getBlue(int x, int y) {
    // Replace the following line with your solution.
    return pixels[x][y].getColor().getBlue();
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
      // Your solution here.
      if( red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue >255 )
          return;
      Color c = new Color(green, red, blue);
      pixels[x][y] = new Pixel(x, y, c);
  }

  /**
   * toString() returns a String representation of this PixImage.
   *
   * This method isn't required, but it should be very useful to you when
   * you're debugging your code.  It's up to you how you represent a PixImage
   * as a String.
   *
   * @return a String representation of this PixImage.
   */
  public String toString() {
    // Replace the following line with your solution.
    StringBuilder sb = new StringBuilder();
    for (int x =0; x< width; x++){
        for (int y = 0; y < height; y++) {
            sb.append(String.format("{%s,%s}", x, y));
            sb.append("( " + getRed(x,y));
            sb.append(" " + getGreen(x,y));
            sb.append(" " + getBlue(x,y) + " )");
            sb.append("\n");
        }
    }
    return sb.toString();
  }

  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   */
  public PixImage boxBlur(int numIterations) {
      if(numIterations <= 0)
          return this;
      PixImage first = new PixImage(width, height);
      for (int i = 0; i < width; i++) {
          for (int j = 0; j < height; j++) {
              Color c = this.getAvg(i, j);
              first.setPixel(i, j, (short)c.getRed(),(short)c.getGreen(), (short)c.getBlue());
          }
      }
      for (int k = 1; k < numIterations; k++) {
          PixImage second = new PixImage(width, height);
          for (int i = 0; i < width; i++) {
              for (int j = 0; j < height; j++) {
                  Color c = first.getAvg(i, j);
                  second.setPixel(i, j, (short)c.getRed(), (short)c.getGreen(), (short)c.getBlue());
              }
          }
          first = second;
      }
    return first;
  }

  private Color getAvg(int x, int y){
      int green =0, red =0, blue =0,  count = 0;
      for (int i = x -1; i < x + 2; i++) {
          if(i >= 0 && i < this.width) {
              for (int j = y - 1; j < y + 2; j++) {
                  if (j >= 0 && j < this.height) {
                      green += getGreen(i, j);
                      blue += getBlue(i ,j);
                      red += getRed(i ,j);
                      count ++;
                  }
              }
          }
      }
      Color c = new Color((short)(green/count), (short)(red/count), (short)(blue/count));
      return  c;
  }

  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
   * correct images and pass the autograder.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */
  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }

  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * +
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */
  public PixImage sobelEdges() {
    // Replace the following line with your solution.
    //return this;
    // Don't forget to use the method mag2gray() above to convert energies to
    // pixel intensities.
      PixImage sobledImage = new PixImage(width, height);
      for (int i=0; i< width; i++){
          for (int j = 0; j < height; j++) {
              short intensities = mag2gray(energy(i, j));
              sobledImage.setPixel(i, j, intensities, intensities, intensities);
          }
      }
      return sobledImage;
  }

   private long energy(int x, int y){
       List<Gradient> gradients = getGradient(x, y);
      if(gradients == null || gradients.size() == 0)
          throw new RuntimeException("Cannot get the gradient");
      long energy = 0L;
      for (Gradient e : gradients){
         energy += e.getGx() * e.getGx() + e.getGy() * e.getGy();
      }
      return energy;
   }

   private  List<Gradient> getGradient(int x, int y){
       Color[][] colors = getNeighbors(x, y);
       if(colors == null)
           throw new RuntimeException("Cannot get the colors");
       List<Gradient> gradients = new ArrayList<Gradient>();
       long gxRed =0L, gxGreen=0L, gxBlue =0L, gyRed =0L, gyGreen=0L, gyBlue =0L;
       for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
               gxRed += (long)(colors[i][j].getRed() * xVector[i][j]);
               gxGreen += (long)colors[i][j].getGreen() * xVector[i][j];
               gxBlue += (long)colors[i][j].getRed() * xVector[i][j];

               gyRed += (long)colors[i][j].getRed() * yVector[i][j];
               gyGreen += (long)colors[i][j].getGreen() * yVector[i][j];
               gyBlue += (long)colors[i][j].getRed() * yVector[i][j];
           }
       }
       gradients.add(new Gradient(gxRed, gyRed));
       gradients.add(new Gradient(gxBlue, gyBlue));
       gradients.add(new Gradient(gxGreen, gyGreen));
       return gradients;
   }

   private Color[][] getNeighbors(int x, int y){
       Color[][] colors = new Color[3][3];
       for (int i = 0; i < 3; i++) {
           int xCoordinate = x + i -1;
           if(xCoordinate < 0) {
               xCoordinate = 0;
           }
           else if(xCoordinate >= width) {
               xCoordinate = width - 1;
           }
           for (int j = 0; j < 3; j++) {
               int yCoordinate = y +j -1;
               if(yCoordinate < 0) {
                   yCoordinate = 0;
               }
               else if(yCoordinate >= height) {
                   yCoordinate = height - 1;
               }
                   colors[i][j] = pixels[xCoordinate][yCoordinate].getColor();
           }
       }
       return colors;
   }

  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * main() runs a series of tests to ensure that the convolutions (box blur
   * and Sobel) are correct.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
    System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                       "Input image:");
    System.out.print(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");
    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());


   PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image2);
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 2x3 image.");
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());
  }
    public static class Color{
        private short green;
        private short red;
        private short blue;
        public  Color(short green, short red, short blue){
            this.green = green;
            this.red = red;
            this.blue = blue;
        }

        public short getGreen(){
            return this.green;
        }

        public short getRed(){
            return  this.red;
        }

        public short getBlue(){
            return this.blue;
        }

        public static Color createBlackColor(){
            return new Color((short)0,(short)0,(short)0);
        }

        public boolean equals(Color c){
            return this.red==c.getRed() && this.green == c.green && this.blue == c.blue;
        }

        public String toString(){
            return "{red, green, blue}" + red + "," + green + "," + blue;
        }
    }

    public static class Pixel{
        private int x;
        private int y;
        private Color c;
        public  Pixel(int x, int y, Color c){
            this.x = x;
            this.y = y;
            this.c = c;
        }
        public int getX(){
            return this.x;
        }
        public int getY(){
            return this.y;
        }

        public Color getColor(){
            return this.c;
        }
    }

    public static class Gradient{
       private Long gx;
       private Long gy;
       public Gradient(Long gx, Long gy){
           this.gx = gx;
           this.gy = gy;
       }
       public Long getGx(){return gx;}
       public Long getGy(){return gy;}
    }
}
