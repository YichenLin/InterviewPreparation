//You are climbing a stair case. It takes n steps to reach to the top.
//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?


public class Solution {
    public int climbStairs(int n) {
        if(n == 0 || n == 1) return 1;
        // take one step to the current one
        int oneStep = 1;
        // take two steps
        int twoSteps = 1;
        // current steps & !!! DON'T forget to initialize temp (as 0 or 1) !!!
        int temp = 0;
        for (int i = 2; i <= n; i++){
            // to current stair, we can go one step or two steps
            temp = oneStep + twoSteps;
            // now the onestep will be the two steps in the upper stair
            twoSteps = oneStep;
            // current step will be the onestep in the upper stair
            oneStep = temp;
        }
        // or return oneStep (oneStep = temp) and we don't need to initialize temp
        return temp;
    }
}