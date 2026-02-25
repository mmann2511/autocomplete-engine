import java.util.Arrays;


/**
 * Autocomplete.
 */
public class Autocomplete {

   private Term[] terms;

	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
   public Autocomplete(Term[] terms) {
      if (terms == null) {
         throw new NullPointerException(
               "terms array must not be null");
      }
         // Use class Term array, just copy terms + terms.length
      this.terms = Arrays.copyOf(terms, terms.length);
         
         // sort by its natural order
      Arrays.sort(this.terms);
   }

	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
	 */
   public Term[] allMatches(String prefix) {
      if (prefix == null) {
         throw new NullPointerException(
            "String prefix must not be null");
      }
      // uses binary searches to find terms with matching prefixes
      // we need a Key[] a, Key key(prefix), Comparator<Key> - Key -> Type Variable
      // Type -> Term
      
      // Term key
      Term key = new Term(prefix, 0);
      
      // BEGIN with given prefix we need length for Comparator
      int length = prefix.length();
      
      // Binary Searches for range
      int start = BinarySearch.firstIndexOf(this.terms, key, Term.byPrefixOrder(length));
      // if start is -1 = no matches we can early exit and return empty array
      if (start == -1) {
         return new Term[0]; 
      };
      
      int end = BinarySearch.lastIndexOf(this.terms, key, Term.byPrefixOrder(length));
      
      // can create a count for new array
      int count = (end - start) + 1;
      Term [] allMatches = new Term[count];
      int arrayIndex = 0; // indexing for allMatches
     
      
      // append Terms in range start to end to new array
      for (int i = start; i <= end; i++) {
         allMatches[arrayIndex++] = this.terms[i];
      }
      
      // sort array in descending order of weight
      Arrays.sort(allMatches, Term.byDescendingWeightOrder());
      // returns an array
      return allMatches;
   
   }
   
   

}
