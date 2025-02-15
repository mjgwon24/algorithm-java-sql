class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 1. create a hashmap and store numbers and indexes
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        // 2. searching the array from start to finish
        for (int i = 0; i < nums.length; i++) {
            // 3. number j required to create a target using the current number
            int j  = target - nums[i];

            // 4. if J is present in the hashmap, the answer is found, so return the index
            if (hashMap.containsKey(j)) {
                return new int[]{hashMap.get(j), i};
            }

            // 5. save the current numbers and indexes in the hashmap
            hashMap.put(nums[i], i); // key: array value, value: index
        }

        // 6. if there is no correct answer, it returns NULL (not executed)
        return null;
    }
}
