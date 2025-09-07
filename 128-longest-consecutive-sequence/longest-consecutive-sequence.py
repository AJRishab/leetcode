class Solution:
    def longestConsecutive(self, nums: list[int]) -> int:
        num_set = set(nums)
        longest = 0

        for num in num_set:
            # Only check for the start of a sequence
            if num - 1 not in num_set:
                current_num = num
                length = 1

                while current_num + 1 in num_set:
                    current_num += 1
                    length += 1

                longest = max(longest, length)
        return longest