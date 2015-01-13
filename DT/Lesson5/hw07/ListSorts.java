/* ListSorts.java */

import list.*;
import java.lang.Comparable;
import java.util.Random;

public class ListSorts {

  private final static int SORTSIZE = 1000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    LinkedQueue queues = new LinkedQueue();
    while(!q.isEmpty()){
      try {
        Object v = q.dequeue();
        LinkedQueue queue = new LinkedQueue();
        queue.enqueue(v);
        queues.append(queue);
      }catch(Exception ex){
        System.out.println("makeQueueOfQueues calling exception: " + ex.getMessage());
      }
    }
    return queues;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
    LinkedQueue result = new LinkedQueue();
    Comparable v1 = null, v2 = null;
    try {
      while(!q1.isEmpty() && !q2.isEmpty()) {
          if (v1 == null)
            v1 = (Comparable) q1.dequeue();
          if (v2 == null)
            v2 = (Comparable) q2.dequeue();
          if (v1.compareTo(v2) == 0) {
            result.enqueue(v1);
            result.enqueue(v2);
            v1 = null;
            v2 = null;
            continue;
          }
          if (v1.compareTo(v2) < 0) {
            result.enqueue(v1);
            v1 = null;
            continue;
          } else {
            result.enqueue(v2);
            v2 = null;
            continue;
          }
        }

      while(!q1.isEmpty()){
        result.enqueue(q1.dequeue());
      }

      while(!q2.isEmpty()){
        result.enqueue(q2.dequeue());
      }
    }catch(Exception ex){
      System.out.println("mergeSortedQueues calling exception: " + ex.getMessage());
    }
    return result;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
    try {
      while (qIn != null && !qIn.isEmpty()) {
        Comparable v = (Comparable) qIn.dequeue();
        if (v.compareTo(pivot) == 0)
          qEquals.enqueue(v);
        if (v.compareTo(pivot) < 0)
          qSmall.enqueue(v);
        if (v.compareTo(pivot) > 0)
          qLarge.enqueue(v);
      }

    }catch(Exception ex){
      System.out.println("partition calling exception: " + ex.getMessage());
    }
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
    try {
      LinkedQueue l = makeQueueOfQueues(q);
      LinkedQueue result = new LinkedQueue();
      while (!l.isEmpty()) {
        LinkedQueue lq1 = (LinkedQueue) l.dequeue();
        LinkedQueue lq2 = (LinkedQueue) l.dequeue();
        if (lq1 != null && lq2 != null) {
          result.append(mergeSortedQueues(lq1, lq2));
          continue;
        }
        if (lq1 != null)
          result.append(lq1);
        if (lq2 != null)
          result.append(lq2);
      }

      while (!result.isEmpty()) {
        q.enqueue(((LinkedQueue) result.dequeue()).dequeue());
      }
    }catch(Exception ex){
      System.out.println("mergeSort calling exception: " + ex.getMessage());
    }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
    LinkedQueue qSmall = new LinkedQueue();
    LinkedQueue qEquals = new LinkedQueue();
    LinkedQueue qLarge = new LinkedQueue();
    Random r = new Random();
    int position = r.nextInt(q.size());
    Comparable pivot = (Comparable)q.nth(position);
    partition(q, pivot, qSmall, qEquals, qLarge);
    quickSort(qSmall);
    quickSort(qLarge);
    q.append(qSmall);
    q.append(qEquals);
    q.append(qLarge);
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    //Remove these comments for Part III.
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

  }

}
