// Approach:
// Classic recursive definition fib(n) = fib(n-1) + fib(n-2), but plain
// recursion recomputes the same subproblems exponentially many times. We
// add memoization via a mutable map and Kotlin's getOrPut, so each value of
// n is computed once and cached, bringing it down to O(n) while keeping the
// recursive structure of the solution intact.
 
class Solution {
    private val memo = mutableMapOf(0 to 0, 1 to 1)
 
    fun fib(n: Int): Int =
        memo.getOrPut(n) { fib(n - 1) + fib(n - 2) }
}