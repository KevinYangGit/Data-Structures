package 斐波那契数;

public class _509_斐波那契数 {

	/*
	 * 下标：0 1 2 3 4 5 6 7 
	 * 数值：0 1 1 2 3 5 8 13 ...
	 */
	public static int fib(int N) {
        if (N<= 1) return N;
        
        int first = 0;
        int second = 1;
        for (int i=0; i < N-1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }
	
	public static int fib2(int N) {
		if (N <= 1) return N;

		int first = 0;
		int second = 1;
		while(N-- > 1) {
			second += first;
			first = second - first;
		}
		return second;
	}
	
	public static int fib3(int N) {
		double c = Math.sqrt(5);
		return (int)((Math.pow((1 + c) / 2, N) - Math.pow((1 - c) / 2, N)) / c);
	}
	
	public static void main(String[] args) {
		System.out.println(fib3(7));
	}
	
}
