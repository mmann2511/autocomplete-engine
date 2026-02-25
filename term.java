import java.util.Comparator;

/**
 * Autocomplete term representing a (query, weight) pair.
 * 
 */
public class Term implements Comparable<Term> {
   
   private final String query;
   private final long weight;

   /**
    * Initialize a term with the given query and weight.
    * This method throws a NullPointerException if query is null,
    * and an IllegalArgumentException if weight is negative.
    */
   public Term(String query, long weight) {
      if (query == null) {
         throw new NullPointerException(
            "query must not be null");
      }
      if (weight < 0) {
         throw new IllegalArgumentException(
            "weight must be greater than 0(posiitve number)");
      }
      this.query = query;
      this.weight = weight;
   }

   /**
    * Compares the two terms in descending order of weight.
    */
   public static Comparator<Term> byDescendingWeightOrder() {
      return 
         new Comparator<Term>() {
            @Override
            public int compare(Term a, Term b) {
               return Long.compare(b.weight, a.weight);
            }  
         };
           
   }

   /**
    * Compares the two terms in ascending lexicographic order of query,
    * but using only the first length characters of query. This method
    * throws an IllegalArgumentException if length is less than or equal
    * to zero.
    */
   public static Comparator<Term> byPrefixOrder(int length) {
      if (length <= 0) {
         throw new IllegalArgumentException(
               "length must be greater than 0");
      }
         
      return 
         new Comparator<Term>() {
            @Override
            public int compare(Term a, Term b) {
               String prefixA = "";
               String prefixB = "";
               if (a.query.length() < length) {
                  prefixA = a.query;
               } else {
                  prefixA = a.query.substring(0, length);
               }
               
               if (b.query.length() < length) {
                  prefixB = b.query;
               } else {
                  prefixB = b.query.substring(0, length);
               }
               
               return prefixA.compareToIgnoreCase(prefixB);
            }
         };
      
   }

   /**
    * Compares this term with the other term in ascending lexicographic order
    * of query.
    */
   @Override
   public int compareTo(Term other) {
      return this.query.compareToIgnoreCase(other.query);
   }

   /**
    * Returns a string representation of this term in the following format:
    * query followed by a tab followed by weight
    */
   @Override
   public String toString(){
      return this.query + "\t" + this.weight;
   
   }

}
