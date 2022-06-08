package basic.backtrack;

import java.util.LinkedList;
import java.util.List;

public class backtrack {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.permute(new int[]{10, 13, 14, 15}));
    }
}

class Solution{
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums){
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums,track);
        return res;
    }

    void backtrack(int[] nums, LinkedList<Integer> track){
        if(track.size()==nums.length){
            res.add(new LinkedList<>(track));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(track.contains(nums[i])){
                continue;
            }
            track.add(nums[i]);
            backtrack(nums,track);
            track.removeLast();
        }
    }
}