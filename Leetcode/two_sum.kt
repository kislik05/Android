// Approach:
// Iterate through the array once while keeping a hash map of value -> index
// for numbers already seen. For each element, check if its complement
// (target - current) is already in the map; if so, we found the pair in O(n)
// time instead of the O(n^2) brute force.
 
class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val seen = HashMap<Int, Int>()
        nums.forEachIndexed { index, value ->
            val complement = target - value
            seen[complement]?.let { complementIndex ->
                return intArrayOf(complementIndex, index)
            }
            seen[value] = index
        }
        return intArrayOf()
    }
}