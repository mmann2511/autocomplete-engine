# Autocomplete Engine

A Java implementation of an autocomplete system similar to what you'd find in search engines like Google. Given a prefix string, the engine predicts the most likely completions ranked by weight (e.g. frequency of use).

---

## How It Works

The engine is built around three classes that work together:

- **Term** — Represents a (query, weight) pair and supports multiple comparison orderings
- **BinarySearch** — Provides first and last index binary search on sorted arrays
- **Autocomplete** — Uses Term and BinarySearch to find and rank all completions for a given prefix

When a prefix is typed, the engine binary searches a sorted array of terms to find all matches, then returns them sorted by descending weight — so the most likely completions come first.

---

## Example

Using a dataset of English words weighted by frequency:

```java
// Load terms from a data source
Autocomplete ac = new Autocomplete(terms);

// Get top completions for "th"
Term[] results = ac.allMatches("th");
// Returns: "the", "that", "they", "this", "there", ... (sorted by weight)
```

---

## Classes

### `Term`
Represents a single (query, weight) pair.

```java
Term t = new Term("the", 23135851162L);
```

Supports three orderings:
- Natural (lexicographic) order via `compareTo`
- Descending weight order via `Term.byDescendingWeightOrder()`
- Prefix order via `Term.byPrefixOrder(int length)`

### `BinarySearch`
Provides two binary search variants on sorted arrays:

```java
int first = BinarySearch.firstIndexOf(terms, key, comparator);
int last  = BinarySearch.lastIndexOf(terms, key, comparator);
```

Both run in O(log N) time.

### `Autocomplete`
Main engine class. Sorts terms lexicographically on construction, then uses binary search to find all matches for a prefix.

```java
Autocomplete ac = new Autocomplete(terms);
Term[] matches = ac.allMatches("pre"); // Returns matches in descending weight order
```

---

## Performance
- `allMatches` identifies the matching range in **O(log N)** time using binary search
- Results are sorted by weight in **O(K log K)** where K is the number of matches

---

## Tech Stack
- Java
- `java.util.Arrays`
- `java.util.Comparator`

---

