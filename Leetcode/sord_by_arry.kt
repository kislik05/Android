// Approach:
// We need all even numbers before all odd numbers, and any order within
// each group is acceptable. sortedBy { it % 2 } sorts by parity as the key
// (0 for even, 1 for odd), which naturally groups evens first without
// writing a manual partition/swap loop.
 
class Solution {
    fun sortArrayByParity(nums: IntArray): IntArray =
        nums.sortedBy { it % 2 }.toIntArray()
}
