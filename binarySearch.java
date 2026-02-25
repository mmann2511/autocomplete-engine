import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

    /**
     * Returns the index of the first key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
   public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
      if (a == null || key == null || comparator == null) {
         throw new NullPointerException(
               "parameters must not be null");
      }
      // boundaries are indexes
      int left = 0;
      int right = a.length - 1;
      int result = -1; // pre make result because we can't instantly return
         
      while (left <= right) {
         int mid = (left + right) / 2;
         Key currentItem = a[mid];
            
         if (comparator.compare(currentItem, key) == 0) {
            // found key
            result = mid;
            right = mid -1; // keep searching for duplicates to the left
         } else if (comparator.compare(currentItem, key) > 0) {
            right = mid - 1; // normal binary
         } else {
            left = mid + 1; // normal binary
         }
      }
      return result; 
   }

    /**
     * Returns the index of the last key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
   public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
      if (a == null || key == null || comparator == null) {
         throw new NullPointerException(
            "parameters must not be null");
      }
      
      // boundaries are indexes
      int left = 0;
      int right = a.length - 1;
      int result = -1;
      
      while (left <= right) {
         int mid = (left + right) / 2;
         Key currentItem = a[mid];
         
         if (comparator.compare(currentItem, key) == 0) {
            result = mid; // normal binary, but don't immediatley return
            // move left boundary to keep searching for LAST matching key index
            left = mid + 1;
         } else if (comparator.compare(currentItem, key) < 0) {
            left = mid + 1; //normal binary
         } else {
            right = mid - 1; // normal boundary
         }
      }
      return result;
   
   }

}
