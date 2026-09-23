// Approach:
// Filter the input down to only letters and digits, normalize case, then
// compare the cleaned string to its own reverse. Kotlin's filter + lowercase
// + reversed chain expresses the "ignore non-alphanumerics, ignore case"
// rule in one readable pipeline instead of a manual two-pointer scan.
 
class Solution {
    fun isPalindrome(s: String): Boolean {
        val cleaned = s.filter { it.isLetterOrDigit() }.lowercase()
        return cleaned == cleaned.reversed()
    }
}