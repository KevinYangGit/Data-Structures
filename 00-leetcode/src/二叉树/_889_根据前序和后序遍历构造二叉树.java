package 二叉树;

import java.util.Arrays;

public class _889_根据前序和后序遍历构造二叉树 {
	/*
	 * 方法一：递归
	 */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
    	int N = pre.length;
    	if (N == 0) {
			return null;
		}
    	TreeNode root = new TreeNode(pre[0]);
    	if (N == 1) {
			return root;
		}
    	
    	//令左分支有 L 个节点
    	int L = 0;
    	for (int i = 0; i < N; i++) {
			if (post[i] == pre[1]) {
				L = i + 1; //L = post.indexOf(pre[1]) + 1
			}
		}
    	
    	//copyOfRange(int[] original, int from, int to)：包括下标from，不包括上标to
    	//左分支由 pre[1 : L+1] 和 post[0 : L] 重新分支
    	root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1,	L + 1), Arrays.copyOfRange(post, 0, L));
    	//右分支将由 pre[L+1 : N] 和 post[L : N-1] 重新分支
    	root.right = constructFromPrePost(Arrays.copyOfRange(pre, L + 1, N), Arrays.copyOfRange(post, L, N - 1));
    	
    	return root;
    }
}
