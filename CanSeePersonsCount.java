package top100Liked;

import java.util.Stack;

public class CanSeePersonsCount
{
	// An O(n) algorithm.
	public int[] canSeePersonsCount(int[] heights) 
	{
		int[] result = new int[heights.length];
	    Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < heights.length; ++i) 
	    {
			// If the height of the element at the top of the stack is shorter
			// than the current heights[i], then heights[i] will be the blocker.
			// Nothing on the right will be visible, and hence the final result.
			// So we simply pop it up from the stack without any further processing.
			// This is the case with "you can see the right next greater element".
			while (!stack.isEmpty() && heights[stack.peek()] <= heights[i])
				result[stack.pop()]++;

			// Otherwise if it is strictly taller, then the current heights[i]
			// is visible to the element at the top of the stack.
			// This is the case with "your left next greater element can see you".
			if (!stack.isEmpty())
				result[stack.peek()]++;

			stack.add(i);
	    }
	    return result;
	}

	// An O(n^2) algorithm.
    public int[] canSeePersonsCountSlow(int[] heights)
    {
        int[] result = new int[heights.length];

        for (int i = 0; i < result.length; i++)
        {
            int temp = 0;
            Integer lsh = null;    // The last seen tower.

            for (int j = i+1; j < result.length; j++)
            {
                if (heights[i] == heights[j])
                    break;

                if (heights[i] < heights[j])
                {
                    temp++;
                    result[i] = temp;
                    break;
                }

				// For the case where heights[i] > heights[j].
				if (lsh == null || heights[lsh] <= heights[j])
				{
					temp++;
					lsh = j;
				}
            }
            result[i] = temp;
        }
        return result;
    }	
}