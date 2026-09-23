// Approach:
// Two strings are anagrams if and only if they contain the same characters
// with the same frequencies. We use groupingBy/eachCount to build a
// character-frequency map for each string and compare the maps directly,
// which reads more clearly than manually incrementing counters in a loop.
 
class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        return s.groupingBy { it }.eachCount() == t.groupingBy { it }.eachCount()
    }
}