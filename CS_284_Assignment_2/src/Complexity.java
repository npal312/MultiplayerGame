public class Complexity {
	
	
	public static void method1(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}
	
	
	public static void method2(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					System.out.println("Operation " + counter);
					counter++;
				}
			}
		}
	}

	
	public static void method3(int n) {
		int counter = 0;
		for (int i = 1; i < n; i = i * 2) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}
	
	
	public static boolean bSearch(int[] a, int x) {
		int end = a.length - 1;
		int start = 0;
		while (start <= end) {
			System.out.println("start = " + start + ", end = " + end);
			int mid = (end - start) / 2 + start;
			if (a[mid] == x) return true;
			else if (a[mid] > x) end = mid - 1;
			else start = mid + 1;
		}
		return false;
	}
	
	
	/*
	 * Question 4:    a.length = 32
	 * Iteration	|	Start	|	End
	 * 		1		|	 0		|	31
	 * 		2		|	 16		|	31
	 * 		3		|	 24		|	31
	 * 		4		|	 28		|	31
	 * 		5		|	 30		|	31
	 * 		6		|	 31		|	31
	 * 
	 * 
	 * Iteration	|	Start	|	End
	 * 		1		|	 0		|	63
	 * 		2		|	 32		|	63
	 * 		3		|	 48		|	63
	 * 		4		|	 56		|	63
	 * 		5		|	 60		|	63
	 * 		6		|	 62		|	63
	 * 		7		|	 63		|	63
	 */
	
	/*
	 * Question 5:
	 * As the size n of a increases, the number of iterations increases.
	 */
	
	/*
	 * Question 6:
	 * for bSearch, T(n) = 4*(log(n)+1) + 3     //(LOG IS BASE 2)
	 * bSearch has time complexity O(log(n))    //(base 2).
	 * (n = a.length, as that determines how many times it iterates)
	 * 3 comes from the two initializations and the "return false" at the end
	 */
	
	
	public static void method4(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n; j = j * 2) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}
	
	
	public static void method5(int n) {
		int counter = 0;
		for (int i = 2; i < n; i = i * i) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}
	
	
	static int counting = 0; // I could initialize at top of function but it only lists counter at around 1/2 of true value
	
	public static int method6(int n) {
		int correct = 0; //so it runs the correct amount of times
		if (n == 0) {
			for (int i = 0; i < 1; i++) {
				System.out.println("Operation " + counting);
				counting++;
			}
			return 1;
		}
		else {
			correct = 2 * method6(n - 1);
		}
		for (int i = 0; i < correct / 2; i++) {
			System.out.println("Operation " + counting);
			counting++;
		}
		return correct;
	}
	
	public static void main(String[] args) {
		/*method1(3);
		System.out.println();
		System.out.println();
		
		method2(4);
		System.out.println();
		System.out.println();
		
		method3(8);
		System.out.println();
		method4(8);
		
		System.out.println();
		System.out.println();
		
		int n = 65536;
		
		method3(n);
		System.out.println();
		method5(n);
		System.out.println();
		
		System.out.println();
		System.out.println();*/
		
		System.out.println(method6(5));
		//method6(5);
		
		}
		
		
}
