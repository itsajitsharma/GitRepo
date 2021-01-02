import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import com.ajit.comparator.CustomIntComparator;
import com.ajit.data.Interval;
import com.ajit.data.ListNode;


public class Test {
	
	
	/**
	 * Array Practice set
	 * 
	 * Lets say performOps was called with A : [[1, 2, 3, 4], [5, 6, 7, 8], [9,
	 * 10, 11, 12]] .
	 * 
	 * What would be the output of the following call :
	 * 
	 * 
	 * ArrayList<ArrayList<Integer>> B = performOps(A); 
	 * for (int i = 0; i < B.size(); i++){ 
	 * 		for (int j = 0; j < B.get(i).size(); j++) {
	 * 			System.out.print(B.get(i).get(j) + " "); 
	 * 		} 
	 * }
	 * 
	 * @param A
	 * @return
	 */
	static ArrayList<Integer> performOps(ArrayList<Integer> A) {
		ArrayList<Integer> B = new ArrayList<Integer>();
		for (int i = 0; i < 2 * A.size(); i++)
			B.add(0);
		for (int i = 0; i < A.size(); i++) {
			B.set(i, A.get(i));
			B.set(i + A.size(), A.get((A.size() - i) % A.size()));
		}
		return B;
	}
	

	/**
	 * 
	 * @param A
	 * @return
	 */
	public static boolean isPower(int A) {

		if (A == 1) {
			return true;
		}

		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		int i = 2;
		int originalNo = A;
		while (A > 1 && i <= originalNo / 2) {
			if ((A % i) == 0) {
				if (countMap.get(i) != null) {
					countMap.put(i, countMap.get(i) + 1);
				} else {
					countMap.put(i, 1);
				}
				A = A / i;
			} else {
				i++;
			}
		}

		if (countMap.size() == 0) {
			return false;
		}

		int value = 0;
		boolean isCountDivisibleBy2 = true;
		boolean isCountSame = true;
		for (Integer key : countMap.keySet()) {

			if (value == 0) {
				value = countMap.get(key);
			} else {
				if (countMap.get(key) % 2 != 0) {
					isCountDivisibleBy2 = false;
				}
				if (value != countMap.get(key)) {
					isCountSame = false;
				}
			}
		}

		if (isCountSame) {
			if (value == 1) {
				return false;
			}
			return true;
		}
		if (isCountDivisibleBy2) {
			if (value == 1) {
				return false;
			}
			return true;
		}

		return false;
	}

	

	/*
	 * REACH - Arrays
	 * 
	 * You are in an infinite 2D grid where you can move in any of the 8
	 * directions :
	 * 
	 * (x,y) to (x+1, y), (x - 1, y), (x, y+1), (x, y-1), (x-1, y-1), (x+1,y+1),
	 * (x-1,y+1), (x+1,y-1) You are given a sequence of points and the order in
	 * which you need to cover the points. Give the minimum number of steps in
	 * which you can achieve it. You start from the first point.
	 * 
	 * Example :
	 * 
	 * Input : [(0, 0), (1, 1), (1, 2)] Output : 2 It takes 1 step to move from
	 * (0, 0) to (1, 1). It takes one more step to move from (1, 1) to (1, 2).
	 */

	// X and Y co-ordinates of the points in order.
	// Each point is represented by (X.get(i), Y.get(i))
	public int coverPoints(ArrayList<Integer> X, ArrayList<Integer> Y) {
		int noOfMoves = 0;
		boolean isFirst = true;
		int currentX = 0;
		int currentY = 0;
		for (int i = 0; i < X.size(); i++) {
			if (isFirst) {
				currentX = X.get(i);
				currentY = Y.get(i);
				isFirst = false;
			} else {
				int xDiff = Math.abs(X.get(i) - currentX);
				int yDiff = Math.abs(Y.get(i) - currentY);
				noOfMoves = noOfMoves + (Math.max(xDiff, yDiff));
				currentX = X.get(i);
				currentY = Y.get(i);
			}
		}
		return noOfMoves;
	}

	/**
	 * MAXSUM - Arrays
	 * 
	 * Find the contiguous subarray within an array (containing at least one
	 * number) which has the largest sum.
	 * 
	 * For example:
	 * 
	 * Given the array [-2,1,-3,4,-1,2,1,-5,4],
	 * 
	 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
	 * 
	 * For this problem, return the maximum sum.
	 * 
	 * @param a
	 * @return
	 */
	public int maxSubArray(final List<Integer> a) {
		int sum = 0;
		int currentSum = 0;
		boolean allNegative = true;
		int minVal = Integer.MIN_VALUE;
		for (Integer value : a) {
			if (value < 0) {
				if (currentSum > sum) {
					sum = currentSum;
				}
				if (allNegative && (value > minVal)) {
					minVal = value;
				}
			} else {
				allNegative = false;
			}
			currentSum = currentSum + value;
			if (currentSum < 0) {
				currentSum = 0;
			}
		}
		if (allNegative) {
			return minVal;
		}
		if (currentSum > sum) {
			sum = currentSum;
		}
		return sum;
	}

	/**
	 * Wave - Arrays
	 * 
	 * Given an array of integers, sort the array into a wave like array and
	 * return it, In other words, arrange the elements into a sequence such that
	 * a1 >= a2 <= a3 >= a4 <= a5.....
	 * 
	 * Example
	 * 
	 * Given [1, 2, 3, 4]
	 * 
	 * One possible answer : [2, 1, 4, 3] Another possible answer : [4, 1, 3, 2]
	 * NOTE : If there are multiple answers possible, return
	 * 
	 * @param a
	 * @return
	 */
	public ArrayList<Integer> wave(ArrayList<Integer> a) {
		Collections.sort(a);
		/*
		 * int[] arr = new int[a.size()]; int[] arrB = new int[a.size()];
		 */
		for (int i = 0; i < a.size(); i += 2) {
			if (i + 1 < a.size()) {
				int temp = a.get(i);
				a.set(i, a.get(i + 1));
				a.set(i + 1, temp);
			}
		}

		return a;
	}

	/**
	 * INTERVAL - Arrays
	 * 
	 * Given a set of non-overlapping intervals, insert a new interval into the
	 * intervals (merge if necessary).
	 * 
	 * You may assume that the intervals were initially sorted according to
	 * their start times.
	 * 
	 * Example 1:
	 * 
	 * Given intervals [1,3],[6,9] insert and merge [2,5] would result in
	 * [1,5],[6,9].
	 * 
	 * Example 2:
	 * 
	 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] would
	 * result in [1,2],[3,10],[12,16].
	 * 
	 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
	 * 
	 * Make sure the returned intervals are also sorted.
	 * 
	 * @param a
	 * @return
	 */
	public ArrayList<Interval> insert(ArrayList<Interval> intervals,
			Interval newInterval) {
		ArrayList<Interval> returnIntervals = new ArrayList<Interval>();

		for (Interval interval : intervals) {
			// New interval is completely before current interval
			if (interval.getStart() > newInterval.getEnd()) {
				returnIntervals.add(newInterval);
				newInterval = interval;
			} else if (interval.getEnd() < newInterval.getStart()) {// new interval is
															// completely after
															// current interval
				returnIntervals.add(interval);
			} else {// newInterval is overlapping with currentInterval
				int newStart = interval.getStart() >= newInterval.getStart() ? newInterval.getStart()
						: interval.getStart();
				int newEnd = interval.getEnd() <= newInterval.getEnd() ? newInterval.getEnd()
						: interval.getEnd();
				newInterval = new Interval(newStart, newEnd);
			}
		}
		returnIntervals.add(newInterval);

		return returnIntervals;
	}

	public static ArrayList<ArrayList<Integer>> prettyPrint(int a) {
		ArrayList<ArrayList<Integer>> returnList = new ArrayList<ArrayList<Integer>>();
		int ROW_MAX = 2 * a - 1;
		int COLUMN_MAX = 2 * a - 1;

		for (int row = 0; row < ROW_MAX; row++) {
			ArrayList<Integer> rowList = new ArrayList<Integer>();
			int minVal = a;
			if (row <= ROW_MAX / 2) {
				minVal = a - row;
			} else {
				minVal = 1 + (row - ROW_MAX / 2);
			}
			int currentValue = a;
			for (int col = 0; col < COLUMN_MAX; col++) {
				if (col <= COLUMN_MAX / 2) {
					rowList.add(currentValue);
					if (currentValue > minVal) {
						--currentValue;
					}
				} else {
					if (row <= ROW_MAX / 2) {
						if (col >= COLUMN_MAX - row) {
							currentValue++;
							rowList.add(currentValue);
						} else {
							rowList.add(currentValue);
						}
					} else {
						if (col > row) {
							currentValue++;
							rowList.add(currentValue);
						} else {
							rowList.add(currentValue);
						}
					}
				}

			}
			returnList.add(rowList);
		}
		return returnList;
	}

	/*
	 * LARGESTNUM - Arrays
	 * 
	 * Given a list of non negative integers, arrange them such that they form
	 * the largest number.
	 * 
	 * For example:
	 * 
	 * Given [3, 30, 34, 5, 9], the largest formed number is 9534330.
	 * 
	 * Note: The result may be very large, so you need to return a string
	 * instead of an integer.
	 */

	public static String largestNumber(final List<Integer> a) {
		StringBuilder strBld = new StringBuilder();
		Collections.sort(a, new CustomIntComparator());

		boolean nonZeroOccurred = false;
		for (int i = a.size() - 1; i >= 0; i--) {
			if (a.get(i) > 0) {
				nonZeroOccurred = true;
			}
			if (nonZeroOccurred) {
				strBld.append(a.get(i));
			}
		}

		if (strBld.length() == 0) {
			return "0";
		}
		return strBld.toString();
	}

	/**
	 * SETZERO - Arrays
	 * 
	 * Given an m x n matrix of 0s and 1s, if an element is 0, set its entire
	 * row and column to 0.
	 * 
	 * Do it in place.
	 * 
	 * Example
	 * 
	 * Given array A as
	 * 
	 * 1 0 1 1 1 1 1 1 1 On returning, the array A should be :
	 * 
	 * 0 0 0 1 0 1 1 0 1 Note that this will be evaluated on the extra memory
	 * used. Try to minimize the space and time complexity.
	 * 
	 * @param A
	 */
	public static void setZeroes(ArrayList<ArrayList<Integer>> A) {
		int m, n;

		if (A == null)
			return;

		m = A.size();
		n = A.get(0).size();

		if (n == 0)
			return;

		for (int i = 0; i < m; i++) {
			boolean zero = false;
			for (int j = 0; j < n; j++) {
				if (A.get(i).get(j) == 0)
					zero = true;
			}

			if (zero)
				clearRow(A, i, m, n, 2);

		}

		for (int i = 0; i < n; i++) {
			boolean zero = false;
			for (int j = 0; j < m; j++) {
				if (A.get(j).get(i) == 0)
					zero = true;
			}

			if (zero)
				clearCol(A, i, m, n, 2);

		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (A.get(i).get(j) == 2)
					A.get(i).set(j, 0);
			}
		}
	}

	private static void clearRow(ArrayList<ArrayList<Integer>> A, int row,
			int m, int n, int value) {

		for (int i = 0; i < n; i++) {
			if (A.get(row).get(i) == 1)
				A.get(row).set(i, value);
		}
	}

	private static void clearCol(ArrayList<ArrayList<Integer>> A, int col,
			int m, int n, int value) {
		for (int i = 0; i < m; i++) {
			if (A.get(i).get(col) == 1)
				A.get(i).set(col, value);
		}
	}

	/**
	 * GCD - Math
	 * 
	 * Given 2 non negative integers m and n, find gcd(m, n)
	 * 
	 * GCD of 2 integers m and n is defined as the greatest integer g such that
	 * g is a divisor of both m and n. Both m and n fit in a 32 bit signed
	 * integer.
	 * 
	 * Example
	 * 
	 * m : 6 n : 9
	 * 
	 * GCD(m, n) : 3 NOTE : DO NOT USE LIBRARY FUNCTIONS
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int gcd(int a, int b) {
		if (a == 0 && b == 0) {
			return 0;
		} else if (a == 0) {
			return b;
		} else if (b == 0) {
			return a;
		} else if (a == b) {
			return a;
		} else {
			if (a > b) {
				if (a % b == 0) {
					return b;
				} else {
					for (int i = b / 2; i >= 1; i--) {
						if (a % i == 0 && b % i == 0) {
							return i;
						}
					}
				}
			} else {
				if (b % a == 0) {
					return a;
				} else {
					for (int i = a / 2; i >= 1; i--) {
						if (b % i == 0 && a % i == 0) {
							return i;
						}
					}
				}
			}
		}
		return 1;
	}

	public int gcd1(int A, int B) {
		if (A == 0)
			return B;
		return gcd(B % A, A);
	}

	/**
	 * POSITIVE - Arrays
	 * 
	 * Given an unsorted integer array, find the first missing positive integer.
	 * 
	 * Example:
	 * 
	 * Given [1,2,0] return 3,
	 * 
	 * [3,4,-1,1] return 2,
	 * 
	 * [-8, -7, -6] returns 1
	 * 
	 * Your algorithm should run in O(n) time and use constant space.
	 * 
	 * @param a
	 * @return
	 */
	public static int firstMissingPositive(ArrayList<Integer> a) {
		int j = a.size() - 1;
		// boolean allNegative = true;
		for (int i = 0; i < a.size() && j >= i; i++) {
			if (a.get(i) < 1 || a.get(i) > a.size()) {
				int temp = a.get(i);
				a.set(i, a.get(j));
				a.set(j, temp);
				j--;
				i--;
			}
			/*
			 * else{ allNegative = false; }
			 */
		}

		/*
		 * if(allNegative){ return 1; }
		 */

		for (int i = 0; i <= j; i++) {
			a.set(Math.abs(a.get(i)) - 1,
					-Math.abs((a.get(Math.abs(a.get(i)) - 1))));
		}

		for (int i = 0; i <= j; i++) {
			if (a.get(i) > 0) {
				return i + 1;
			}
		}
		return j + 2;
	}

	/*
	 * PRIMESUM - Math
	 * 
	 * Given an even number ( greater than 2 ), return two prime numbers whose
	 * sum will be equal to given number. NOTE A solution will always exist.
	 * read Goldbach’s conjecture
	 * 
	 * Example: Input : 4 Output: 2 + 2 = 4
	 * 
	 * If there are more than one solutions possible, return the
	 * lexicographically smaller solution. If [a, b] is one solution with a <=
	 * b, and [c,d] is another solution with c <= d, then
	 * 
	 * [a, b] < [c, d]
	 * 
	 * If a < c OR a==c AND b < d.
	 */
	public static ArrayList<Integer> primesum(int A) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		HashSet<Integer> primeNumSet = new HashSet<Integer>();
		primeNumSet.add(2);
		if (primeNumSet.contains(A - 2) || isPrime(A - 2)) {
			returnList.add(2);
			returnList.add(A - 2);
			return returnList;
		}
		for (int i = 3; i <= A / 2; i = i + 2) {
			if (isPrime(i)) {
				primeNumSet.add(i);
				int otherNum = A - i;
				if (primeNumSet.contains(otherNum) || isPrime(otherNum)) {
					returnList.add(i);
					returnList.add(otherNum);
					return returnList;
				}
			}
		}

		return returnList;
	}

	public static boolean isPrime(int num) {
		if (num <= 1) {
			return false;
		} else if (num == 2 || num == 3) {
			return true;
		} else {
			if (num % 2 == 0) {
				return false;
			}
			for (int i = 3; i <= (int) Math.sqrt(num); i += 2) {
				if (num % i == 0) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * EXCEL1 - Math Given a column title as appears in an Excel sheet, return
	 * its corresponding column number.
	 * 
	 * Example:
	 * 
	 * A -> 1
	 * 
	 * B -> 2
	 * 
	 * C -> 3
	 * 
	 * ...
	 * 
	 * Z -> 26
	 * 
	 * AA -> 27
	 * 
	 * AB -> 28
	 * 
	 * @param a
	 * @return
	 */
	public static int titleToNumber(String a) {
		HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();

		int i = 1;
		for (char txt = 'A'; txt <= 'Z'; txt++, i++) {
			charMap.put(txt, i);
		}

		int sum = 0;
		int multiplier = 0;
		for (i = a.length() - 1; i > -1; i--) {
			if (charMap.get(a.charAt(i)) != null) {
				sum = (int) (sum + ((int) charMap.get(a.charAt(i)))
						* Math.pow(26, multiplier));
				multiplier++;
			}
			/*
			 * else{ throw new Exception("Invalid input"); }
			 */
		}

		return sum;
	}

	/**
	 * PALINDROME - Math
	 * 
	 * Determine whether an integer is a palindrome. Do this without extra
	 * space.
	 * 
	 * A palindrome integer is an integer x for which reverse(x) = x where
	 * reverse(x) is x with its digit reversed. Negative numbers are not
	 * palindromic.
	 * 
	 * Example :
	 * 
	 * Input : 12121 Output : True
	 * 
	 * Input : 123 Output : False
	 * 
	 * 
	 * @param a
	 * @return
	 */
	public static boolean isPalindrome(int a) {
		int temp = a;

		if (a < 0) {
			return false;
		} else if (a == 0) {
			return true;
		} else {
			int reverseNum = 0;
			while (temp > 0) {
				int multiplier = temp % 10;
				temp = temp / 10;
				reverseNum = reverseNum + multiplier;
				if (temp > 0)
					reverseNum = reverseNum * 10;
			}
			if (reverseNum == a) {
				return true;
			} else {
				return false;
			}
		}

	}

	/**
	 * FACTORS - Math
	 * 
	 * Given a number N, find all factors of N.
	 * 
	 * Example:
	 * 
	 * N = 6 factors = {1, 2, 3, 6} Make sure the returned array is sorted.
	 * 
	 * @param a
	 * @return
	 */
	public static ArrayList<Integer> allFactors(int a) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		// TreeSet<Integer> factorSet= new TreeSet<Integer>();

		if (a == 0) {
			returnList.add(0);
		} else if (a == 1) {
			returnList.add(1);
		} else {
			int sqrt = (int) Math.sqrt(a);
			for (int i = 1; i <= sqrt; i++) {
				if (a % i == 0) {
					returnList.add(i);
					if (a / i != i)
						list2.add(a / i);
				}
			}

			for (int j = list2.size() - 1; j >= 0; j--) {
				returnList.add(list2.get(j));
			}
		}

		return returnList;
	}

	/**
	 * Arrange - Maths
	 * 
	 * Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1)
	 * extra space.
	 * 
	 * Example:
	 * 
	 * Input : [1, 0] Return : [0, 1] Lets say N = size of the array. Then,
	 * following holds true : All elements in the array are in the range [0,
	 * N-1] N * N does not overflow for a signed integer
	 * 
	 * @param a
	 */
	public static void arrange(ArrayList<Integer> a) {
		// First step: Increase all values by (arr[arr[i]]%n)*n
		int n = a.size();
		for (int i = 0; i < n; i++)
			a.set(i, a.get(i) + ((a.get(a.get(i))) % n) * n);

		// Second Step: Divide all values by n
		for (int i = 0; i < n; i++)
			a.set(i, a.get(i) / n);
	}

	/**
	 * Paths - Maths
	 * 
	 * A robot is located at the top-left corner of an A x B grid (marked
	 * ‘Start’ in the diagram below).
	 * 
	 * 
	 * 
	 * The robot can only move either down or right at any point in time. The
	 * robot is trying to reach the bottom-right corner of the grid (marked
	 * ‘Finish’ in the diagram below).
	 * 
	 * How many possible unique paths are there?
	 * 
	 * Note: A and B will be such that the resulting answer fits in a 32 bit
	 * signed integer.
	 * 
	 * Example :
	 * 
	 * Input : A = 2, B = 2 Output : 2
	 * 
	 * 2 possible routes : (0, 0) -> (0, 1) -> (1, 1) OR : (0, 0) -> (1, 0) ->
	 * (1, 1)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int uniquePaths(int a, int b) {
		/*
		 * if(a < 0 || b < 0){ throw new Exception("Wrong input"); }
		 */
		if (a == 0 || b == 0) {
			return 1;
		} else if (a == 1 || b == 1) {
			return 1;
		} else {// there will be total (a-1) + (b-1) moves. total no of way of
				// selecting b moves will be nCr
			if (b > a) {
				int temp = a;
				a = b;
				b = temp;
			}
			int denom = 1;
			long numerator = 1;
			long sumOfNums = a + b - 2;
			for (int i = 1; i <= b - 1; i++) {
				numerator = numerator * (sumOfNums);
				denom = denom * (b - i);
				sumOfNums--;
				if (numerator % denom == 0) {
					numerator = numerator / denom;
					denom = 1;
				}
			}
			return (int) (numerator / denom);
		}
	}

	/*
	 * search2d - BinarySearch
	 * 
	 * Write an efficient algorithm that searches for a value in an m x n
	 * matrix.
	 * 
	 * This matrix has the following properties:
	 * 
	 * Integers in each row are sorted from left to right. The first integer of
	 * each row is greater than or equal to the last integer of the previous
	 * row. Example:
	 * 
	 * Consider the following matrix:
	 * 
	 * [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ] Given target = 3,
	 * return 1 ( 1 corresponds to true )
	 * 
	 * Return 0 / 1 ( 0 if the element is not present, 1 if the element is
	 * present ) for this problem
	 */
	public static int searchMatrix(ArrayList<ArrayList<Integer>> a, int b) {
		if (a.get(0).get(0) == b) {
			return 1;
		} else if (a.get(a.size() - 1).get(0) == b) {
			return 1;
		}
		int rowNum = getRowNum(a, b);
		return binarySearchInArrayList(a.get(rowNum), b);

	}

	private static int binarySearchInArrayList(ArrayList<Integer> a, int b) {

		int start = 0;
		int end = a.size() - 1;

		while (start <= end) {
			int mid = (start + end) / 2;
			if (a.get(mid) == b) {
				return 1;
			} else if (a.get(mid) > b) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return 0;
	}

	private static int getRowNum(ArrayList<ArrayList<Integer>> a, int b) {
		int start = 0;
		int end = a.size() - 1;

		while (start < end) {
			if (start + 1 == end) {
				if (a.get(end).get(0) <= b) {
					return end;
				} else {
					return start;
				}
			}
			int mid = (start + end) / 2;
			if (a.get(mid).get(0) == b) {
				return mid;
			} else if (a.get(mid).get(0) > b) {
				end = mid;
			} else {
				start = mid;
			}
		}
		return start;
	}

	/*
	 * SQRT - BinarySearch
	 * 
	 * Implement int sqrt(int x).
	 * 
	 * Compute and return the square root of x.
	 * 
	 * If x is not a perfect square, return floor(sqrt(x)) Example :
	 * 
	 * Input : 11 Output : 3 DO NOT USE SQRT FUNCTION FROM STANDARD LIBRARY
	 */
	public static int sqrt(int A) {

		int low, high, root;
		int mid;

		low = 1;
		high = A;
		root = 0;

		while (low <= high) {

			mid = (low + high) / 2;

			if (mid == A / mid && (A % mid == 0))
				return mid;

			if (mid <= A / mid) {
				root = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}

		}

		return root;
	}

	/**
	 * POW - BinarySearch
	 * 
	 * Implement pow(x, n) % d.
	 * 
	 * In other words, given x, n and d,
	 * 
	 * find (xn % d)
	 * 
	 * Note that remainders on division cannot be negative. In other words, make
	 * sure the answer you return is non negative.
	 * 
	 * Input : x = 2, n = 3, d = 3 Output : 2
	 * 
	 * 2^3 % 3 = 8 % 3 = 2.
	 * 
	 * 
	 * @param x
	 * @param n
	 * @param d
	 * @return
	 */
	public static int pow(int x, int n, int d) {
		long a = x;
		long res = 1L;

		while (n > 0) {

			if (n % 2 == 1) {
				res *= a;
				res %= d;
			}

			a *= a;
			a %= d;
			n = n >> 1;

		}

		res = (res + d) % d;
		return (int) res;
	}

	// Below is also working code
	/*
	 * public int pow(int x, int n, int d) { long ans=1; long square=x;
	 * if(n==0){ ans = 1%d; } else{ while(n!=0) { if(n%2==1){//if power is odd.
	 * ans=ans*square; } square=(square*square)%d; n=n/2; if(ans>d){ ans=ans%d;
	 * } } }
	 * 
	 * if(ans < 0){ return (int)(ans+Math.abs(d)); } return (int)ans; }
	 */

	/*
	 * MEDIANARRAY - BinarySearch
	 * 
	 * There are two sorted arrays A and B of size m and n respectively.
	 * 
	 * Find the median of the two sorted arrays ( The median of the array formed
	 * by merging both the arrays ).
	 * 
	 * The overall run time complexity should be O(log (m+n)).
	 * 
	 * Sample Input
	 * 
	 * A : [1 4 5] B : [2 3]
	 * 
	 * Sample Output
	 * 
	 * 3 NOTE: IF the number of elements in the merged array is even, then the
	 * median is the average of n / 2 th and n/2 + 1th element. For example, if
	 * the array is [1 2 3 4], the median is (2 + 3) / 2.0 = 2.5
	 */

	public static double findMedianSortedArrays(final List<Integer> A,
			final List<Integer> B) {
		int len = A.size() + B.size();
		if (len % 2 == 1)
			return findKth(A, 0, B, 0, len / 2 + 1);
		else
			return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0,
					len / 2 + 1)) / 2.0;
	}

	public static int findKth(List<Integer> A, int A_start, List<Integer> B,
			int B_start, int k) {
		if (A_start >= A.size())
			return B.get(B_start + k - 1);
		if (B_start >= B.size())
			return A.get(A_start + k - 1);
		if (k == 1)
			return Math.min(A.get(A_start), B.get(B_start));

		int A_key = A_start + k / 2 - 1 < A.size() ? A.get(A_start + k / 2 - 1)
				: Integer.MAX_VALUE;
		int B_key = B_start + k / 2 - 1 < B.size() ? B.get(B_start + k / 2 - 1)
				: Integer.MAX_VALUE;

		if (A_key < B_key) {
			return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
		} else
			return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
	}

	public static int numSetBits(long a) {
		int count = 0;
		while (a > 0) {
			a = a & (a - 1);
			count++;
		}
		return count;
	}

	// below solution is also correct
	/*
	 * public static int numSetBits(long a) { int count = 0; while(a>0){ //long
	 * res = a & ~(a-1); if(a%2 > 0){ count++; } a= a>>1; } return count; }
	 */

	public static int singleNumber(final List<Integer> a) {
		int number = a.get(0);
		for (int i = 1; i < a.size(); i++) {
			number = number ^ a.get(i);
		}
		return number;
	}

	/**
	 * Given a string, determine if it is a palindrome, considering only
	 * alphanumeric characters and ignoring cases.
	 * 
	 * Example:
	 * 
	 * "A man, a plan, a canal: Panama" is a palindrome.
	 * 
	 * "race a car" is not a palindrome.
	 * 
	 * Return 0 / 1 ( 0 for false, 1 for true ) for this problem
	 * 
	 * @param a
	 * @return
	 */

	public static int isPalindrome(String a) {
		int end = a.length() - 1;
		int front = 0;
		while (front <= end) {
			while ((a.charAt(front) < '0' || a.charAt(front) > '9')
					&& (a.charAt(front) < 'a' || a.charAt(front) > 'z')
					&& (a.charAt(front) < 'A' || a.charAt(front) > 'Z')) {
				front++;
				if (front > a.length() - 1) {
					break;
				}
			}
			while ((a.charAt(end) < '0' || a.charAt(end) > '9')
					&& (a.charAt(end) < 'a' || a.charAt(end) > 'z')
					&& (a.charAt(end) < 'A' || a.charAt(end) > 'Z')) {
				end--;
				if (end < 0) {
					break;
				}
			}
			if ((front > a.length() - 1) && end < 0) {
				return 1;
			} else if ((front > a.length() - 1) || end < 0) {
				return 0;
			}
			if (a.charAt(front) == a.charAt(end)) {
				front++;
				end--;
				continue;
			} else if ((a.charAt(end) >= 'a' && a.charAt(end) <= 'z')
					&& ((a.charAt(front) - 'A') == (a.charAt(end) - 'a'))) {
				front++;
				end--;
				continue;
			} else if ((a.charAt(front) - 'a') == (a.charAt(end) - 'A')) {
				front++;
				end--;
				continue;
			}
			return 0;
		}
		return 1;
	}

	public static int strStr(final String haystack, final String needle) {
		if ((haystack.length() == 0) || (needle.length() == 0)) {
			return -1;
		} else if (haystack.length() < needle.length()) {
			return -1;
		} else {
			int needleCount = 0;
			int haystackCount = 0;
			int needleLen = needle.length();
			int haystackLen = haystack.length();
			for (int i = 0; i < needleLen; i++) {
				needleCount = needleCount + needle.charAt(i);
				haystackCount = haystackCount + haystack.charAt(i);
			}
			int i = 0;
			while (i < haystackLen - needleLen + 1) {
				boolean matched = true;
				if (needleCount == haystackCount) {// match the pattern
					for (int index = 0; index < needleLen; index++) {
						if (needle.charAt(index) != haystack.charAt(i + index)) {
							matched = false;
							break;
						}
					}
					if (matched) {
						return i;
					}
				}
				i++;
				if (i >= haystackLen - needleLen + 1) {
					return -1;
				}
				haystackCount = haystackCount - haystack.charAt(i - 1)
						+ haystack.charAt(i + needleLen - 1);
			}
			return -1;

		}
	}

	public static int lengthOfLastWord(final String a) {
		int lenCurrentWord = 0;
		int lenPrevWord = 0;
		int strLen = a.length();
		int i = 0;
		while (i < strLen) {
			if (a.charAt(i) == ' ') {
				i++;
				if (lenCurrentWord > 0) {
					lenPrevWord = lenCurrentWord;
				}
				lenCurrentWord = 0;
			} else {
				lenCurrentWord++;
				i++;
			}
		}
		if (lenCurrentWord > 0) {
			return lenCurrentWord;
		}
		return lenPrevWord;

	}

	/**
	 * Compare two version numbers version1 and version2.
	 * 
	 * If version1 > version2 return 1, If version1 < version2 return -1,
	 * otherwise return 0. You may assume that the version strings are non-empty
	 * and contain only digits and the . character. The . character does not
	 * represent a decimal point and is used to separate number sequences. For
	 * instance, 2.5 is not "two and a half" or "half way to version three", it
	 * is the fifth second-level revision of the second first-level revision.
	 * 
	 * Here is an example of version numbers ordering:
	 * 
	 * 0.1 < 1.1 < 1.2 < 1.13 < 1.13.4
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	
	//This is the better solution
	/*public int compareVersion(String version1, String version2) {
	    String[] arr1 = version1.split("\\.");
	    String[] arr2 = version2.split("\\.");
	 
	    int i=0;
	    while(i<arr1.length || i<arr2.length){
	        if(i<arr1.length && i<arr2.length){
	            if(Integer.parseInt(arr1[i]) < Integer.parseInt(arr2[i])){
	                return -1;
	            }else if(Integer.parseInt(arr1[i]) > Integer.parseInt(arr2[i])){
	                return 1;
	            }
	        } else if(i<arr1.length){
	            if(Integer.parseInt(arr1[i]) != 0){
	                return 1;
	            }
	        } else if(i<arr2.length){
	           if(Integer.parseInt(arr2[i]) != 0){
	                return -1;
	            }
	        }
	 
	        i++;
	    }
	 
	    return 0;
	}*/
	
	
	public static int compareVersion(String a, String b) {
		String[] aVesions = a.split("\\.");
		String[] bVesions = b.split("\\.");
		int len = getMin(aVesions.length, bVesions.length);
		for (int i = 0; i < len; i++) {
			String aStr = aVesions[i];
			String bStr = bVesions[i];

			// remove trailing zeroes;
			aStr = removeZeroes(aStr);
			bStr = removeZeroes(bStr);

			if (aStr.length() > bStr.length()) {
				return 1;
			} else if (aStr.length() < bStr.length()) {
				return -1;
			} else {
				int j = 0;
				while (j < aStr.length()) {
					int aVal = aStr.charAt(j) - '0';
					int bVal = bStr.charAt(j) - '0';
					if (aVal > bVal) {
						return 1;
					} else if (aVal < bVal) {
						return -1;
					}
					j++;
				}
			}
		}
		if (aVesions.length == bVesions.length) {
			return 0;
		}

		if (aVesions.length > bVesions.length) {
			String removeZeroes2 = removeZeroes(aVesions[len]);
			int val = Integer.parseInt(removeZeroes2);
			if (val == 0) {
				return 0;
			} else {
				return 1;
			}
		} else {
			String removeZeroes2 = removeZeroes(bVesions[len]);
			int val = Integer.parseInt(removeZeroes2);
			if (val == 0) {
				return 0;
			} else {
				return -1;
			}
		}

	}

	private static String removeZeroes(String aStr) {
		int noOfZeroes = 0;
		int i = 0;
		while (i < aStr.length()) {
			if (aStr.charAt(i) == '0') {
				noOfZeroes++;
			} else {
				break;
			}
			i++;
		}

		if (noOfZeroes == aStr.length()) {
			return "0";
		} else {
			return aStr.substring(noOfZeroes);
		}
	}

	public static int getMin(int a, int b) {
		return a < b ? a : b;
	}

	public static int getMin(int a, int b, int c) {
		if (a < b) {
			return a < c ? a : c;
		} else {
			return b < c ? b : c;
		}
	}

	/**
	 * Given an integer, convert it to a roman numeral, and return a string
	 * corresponding to its roman numeral version
	 * 
	 * Input is guaranteed to be within the range from 1 to 3999.
	 * 
	 * Example :
	 * 
	 * Input : 5 Return : "V"
	 * 
	 * Input : 14 Return : "XIV" Note : This question has a lot of scope of
	 * clarification from the interviewer. Please take a moment to think of all
	 * the needed clarifications and see the expected response using “See
	 * Expected Output” For the purpose of this question,
	 * https://projecteuler.net/about=roman_numerals has very detailed
	 * explanations.
	 * 
	 * I = 1 V = 5 X = 10 L = 50 C = 100 D = 500 M = 1000
	 * 
	 * @param a
	 * @return
	 */
	public static String intToRoman(int a) {
		Map<Integer, String> romanMap = new HashMap<Integer, String>();
		romanMap.put(1000, "M");
		romanMap.put(900, "CM");
		romanMap.put(800, "DCCC");
		romanMap.put(700, "DCC");
		romanMap.put(600, "DC");
		romanMap.put(500, "D");
		romanMap.put(400, "CD");
		romanMap.put(300, "CCC");
		romanMap.put(200, "CC");
		romanMap.put(100, "C");
		romanMap.put(90, "XC");
		romanMap.put(80, "LXXX");
		romanMap.put(70, "LXX");
		romanMap.put(60, "LX");
		romanMap.put(50, "L");
		romanMap.put(40, "XL");
		romanMap.put(30, "XXX");
		romanMap.put(20, "XX");
		romanMap.put(10, "X");
		romanMap.put(9, "IX");
		romanMap.put(8, "VIII");
		romanMap.put(7, "VII");
		romanMap.put(6, "VI");
		romanMap.put(5, "V");
		romanMap.put(4, "IV");
		romanMap.put(3, "III");
		romanMap.put(2, "II");
		romanMap.put(1, "I");

		StringBuilder strBld = new StringBuilder();
		while (a / 1000 != 0) {
			strBld.append(romanMap.get(1000));
			a = a - 1000;
		}
		int[] ints = new int[] { 900, 800, 700, 600, 500, 400, 300, 200, 100,
				90, 80, 70, 60, 50, 40, 30, 20, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int i = 0;
		while (a > 0) {
			if (a / ints[i] != 0) {
				strBld.append(romanMap.get(ints[i]));
				a = a - ints[i];
			}
			i++;
		}
		return strBld.toString();
	}

	/**
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given
	 * number of rows like this: (you may want to display this pattern in a
	 * fixed font for better legibility)
	 * 
	 * P.......A........H.......N 
	 * ..A..P....L....S....I...I....G
	 * ....Y.........I........R And then read line by line: PAHNAPLSIIGYIR Write
	 * the code that will take a string and make this conversion given a number
	 * of rows:
	 * 
	 * string convert(string text, int nRows); convert("PAYPALISHIRING", 3)
	 * should return "PAHNAPLSIIGYIR" Example 2 : ** ABCD, 2 can be written as
	 * 
	 * A....C 
	 * ...B....D and hence the answer would be ACBD.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String convert(String a, int b) {
		if (b <= 1) {
			return a;
		}
		StringBuilder[] strArray = new StringBuilder[b];
		for (int i = 0; i < b; i++) {
			StringBuilder strBuff = new StringBuilder();
			strArray[i] = strBuff;
		}
		int i = 0;
		if (i < a.length()) {
			strArray[0].append(a.charAt(i));
			i++;
		}
		while (i < a.length()) {
			for (int j = 1; j < b && i < a.length(); j++) {
				strArray[j].append(a.charAt(i));
				i++;
			}
			for (int j = b - 2; j > -1 && i < a.length(); j--) {
				strArray[j].append(a.charAt(i));
				i++;
			}
		}
		StringBuilder finalStr = new StringBuilder();
		for (i = 0; i < b; i++) {
			finalStr.append(strArray[i]);
		}
		return finalStr.toString();

	}

	/**
	 * Find longest palindrome in a given string.
	 * 
	 * @param a
	 * @return
	 */
	public static String longestPalindrome(String a) {
		if (a == null || a.length() == 0) {
			return "";
		} else if (a.length() == 1) {
			return a;
		}
		int start = 0;
		int length = 1;
		for (int i = 1; i < a.length(); i++) {
			// even length palindromes with
			int high = i;
			int low = i - 1;
			while (low >= 0 && high < a.length()
					&& a.charAt(high) == a.charAt(low)) {
				if ((high - low + 1) > length) {
					length = high - low + 1;
					start = low;
				}
				low--;
				high++;
			}

			high = i + 1;
			low = i - 1;
			while (low >= 0 && high < a.length()
					&& a.charAt(high) == a.charAt(low)) {
				if ((high - low + 1) > length) {
					length = high - low + 1;
					start = low;
				}
				low--;
				high++;
			}
		}

		return a.substring(start, start + length);
	}

	/**
	 * Find the intersection of two sorted arrays. OR in other words, Given 2
	 * sorted arrays, find all the elements which occur in both the arrays.
	 * 
	 * Example :
	 * 
	 * Input : A : [1 2 3 3 4 5 6] B : [3 3 5]
	 * 
	 * Output : [3 3 5]
	 * 
	 * Input : A : [1 2 3 3 4 5 6] B : [3 5]
	 * 
	 * Output : [3 5]
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static ArrayList<Integer> intersect(final List<Integer> a,
			final List<Integer> b) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		if (a == null || a.size() == 0 || b == null || b.size() == 0) {
			return returnList;
		} else {
			int aInd = 0;
			int bInd = 0;
			while (aInd < a.size() && bInd < b.size()) {
				if (a.get(aInd).equals(b.get(bInd))) {
					returnList.add(b.get(bInd));
					aInd++;
					bInd++;
				} else if (a.get(aInd) > b.get(bInd)) {
					bInd++;
				} else {
					aInd++;
				}
			}
		}
		return returnList;
	}

	public static int threeSumClosest(ArrayList<Integer> a, int b) {
		if (a == null || a.size() == 0) {
			return 0;
		}
		Collections.sort(a);
		int returnSum = 0;
		long sumDiff = Long.MAX_VALUE;
		for (int i = 0; i < a.size(); i++) {
			int startInd = 0;
			int endInd = a.size() - 1;
			// long targetSum = b-a.get(i);
			while (startInd < endInd) {
				if (startInd == i) {
					startInd++;
				} else if (endInd == i) {
					endInd--;
				} else {
					int sum = a.get(startInd) + a.get(endInd) + a.get(i);
					if (sum == b) {
						return b;
					} else if (Math.abs(sum - b) < sumDiff) {
						sumDiff = Math.abs(sum - b);
						returnSum = sum;
					}

					if (sum > b) {
						endInd--;
					} else {
						startInd++;
					}
				}

			}
		}

		return returnSum;

	}

	
	/**
	 * Remove Duplicates From Sorted Array - Two Pointers 
	 * 
	 * Remove duplicates from Sorted Array
		Given a sorted array, remove the duplicates in place such that each element appears only once and return the new length.
		
		Note that even though we want you to return the new length, make sure to change the original array as well in place
		
		Do not allocate extra space for another array, you must do this in place with constant memory.
		
		 Example: 
		Given input array A = [1,1,2],
		Your function should return length = 2, and A is now [1,2]. 
	 * 
	 * @param a
	 * @return
	 */
	public static int removeDuplicates1(ArrayList<Integer> a) {
		if (a == null || a.size() == 0) {
			return 0;
		}
		int currentIndex = 1;
		int i = 1;
		Integer val = a.get(0);
		while (i < a.size()) {
			if (a.get(i).equals(val)) {
				i++;
			} else {
				val = a.get(i);
				a.set(currentIndex, a.get(i));
				currentIndex++;
				i++;
			}
		}
		int j = a.size() - 1;
		for (; j >= currentIndex; j--) {
			a.remove(j);
		}
		return currentIndex;
	}

	public static String longestCommonPrefix(ArrayList<String> a) {
		if (a == null || a.size() == 0) {
			return "";
		} else if (a.size() == 1) {
			return a.get(0);
		} else {
			String currentPrefix = a.get(0);
			for (int i = 1; i < a.size(); i++) {
				currentPrefix = longCommPrefix(currentPrefix, a.get(i));
			}
			return currentPrefix;
		}
	}

	private static String longCommPrefix(String str1, String str2) {
		StringBuilder strBld = new StringBuilder();
		if (str1 == null || str1.length() == 0 || str2 == null
				|| str2.length() == 0) {
			return "";
		} else {
			for (int i = 0; i < str1.length() && i < str2.length(); i++) {
				if (str1.charAt(i) == str2.charAt(i)) {
					strBld.append(str1.charAt(i));
				} else {
					return strBld.toString();
				}
			}
			return strBld.toString();
		}

	}

	public static ArrayList<Integer> maxone(List<Integer> a, int b) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		if (a == null || a.size() == 0) {
			return returnList;
		} else {
			int wL = 0;
			int wR = 0;
			int nZero = 0;
			if (a.get(0) == 0)
				nZero = 1;
			int bestWindowWidth = -1;
			int bestWR = wR;
			int bestWL = wL;

			while (wR < a.size()) {
				// expand to the right, update '0' count:
				if (nZero <= b) {
					wR++;
					if (wR == a.size()) {
						break;
					}
					if (a.get(wR) == 0) {
						nZero++;
					}
				}

				// shrink from left, update '0' count:
				if (nZero > b) {
					if (a.get(wL) == 0) {
						nZero--;
					}
					wL++;
				}

				// update best window:
				if (wR - wL > bestWindowWidth) {
					bestWindowWidth = wR - wL;
					bestWR = wR;
					bestWL = wL;
				}
			}
			// update best window:
			if (wR - wL - 1 > bestWindowWidth) {
				bestWindowWidth = wR - wL;
				bestWR = wR - 1;
				bestWL = wL;
			}
			for (int i = bestWL; i <= bestWR; i++) {
				returnList.add(i);
			}
			return returnList;
			// }
		}
	}

	/**
	 * You are given two linked lists representing two non-negative numbers. The
	 * digits are stored in reverse order and each of their nodes contain a
	 * single digit. Add the two numbers and return it as a linked list.
	 * 
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8
	 * 
	 * 342 + 465 = 807 Make sure there are no trailing zeros in the output list
	 * So, 7 -> 0 -> 8 -> 0 is not a valid response even though the value is
	 * still 807.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	// Recursive solution
	public static ListNode addTwoNumbersRecursive(ListNode a, ListNode b) {
		if (a == null && b == null) {
			return a;
		} else if (b == null) {
			return a;
		} else {
			try {
				return addTwoNumbers(a, b, 0);

			} catch (Throwable e) {
				e.printStackTrace();

			}
			return null;
		}
	}

	private static ListNode addTwoNumbers(ListNode a, ListNode b, int carry) {

		if (a == null && b == null) {
			if (carry == 0) {
				return null;
			} else {
				return new ListNode(carry);
			}
		} else if (b == null) {
			int sum = carry + a.val;
			if (sum / 10 > 0) {
				ListNode listNode = new ListNode(sum % 10);
				listNode.next = addTwoNumbers(a.next, null, sum / 10);
				return listNode;
			} else {
				ListNode listNode = new ListNode(sum);
				listNode.next = a.next;
				return listNode;
			}
			// return a;
		} else if (a == null) {
			int sum = carry + b.val;
			if (sum / 10 > 0) {
				ListNode listNode = new ListNode(sum % 10);
				listNode.next = addTwoNumbers(b.next, null, sum / 10);
				return listNode;
			} else {
				ListNode listNode = new ListNode(sum);
				listNode.next = b.next;
				return listNode;
			}
			// return a;
		} else {
			int sum = a.val + b.val + carry;
			if (sum / 10 > 0) {
				ListNode listNode = new ListNode(sum % 10);
				listNode.next = addTwoNumbers(a.next, b.next, sum / 10);

				return listNode;
			} else {
				ListNode listNode = new ListNode(sum);
				listNode.next = addTwoNumbers(a.next, b.next, 0);
				return listNode;
			}
		}

	}

	// iterative
	public static ListNode addTwoNumbers(ListNode a, ListNode b) {
		if (a == null && b == null) {
			return a;
		} else if (b == null) {
			return a;
		} else {
			int carry = 0;
			ListNode returnNode = null;
			ListNode currentNode = null;
			boolean first = true;
			int sum = 0;
			while (a != null || b != null) {
				if (first) {
					first = false;
					returnNode = new ListNode(0);
					currentNode = returnNode;
				} else {
					currentNode.next = new ListNode(0);
					currentNode = currentNode.next;
				}

				if (a != null && b != null) {
					sum = a.val + b.val + carry;
					a = a.next;
					b = b.next;
				} else if (a != null) {
					sum = a.val + carry;
					a = a.next;
				} else {
					sum = b.val + carry;
					b = b.next;
				}

				if (sum / 10 > 0) {
					currentNode.val = sum % 10;
					carry = sum / 10;
				} else {
					currentNode.val = sum;
					carry = 0;
				}
			}
			if (carry != 0) {
				currentNode.next = new ListNode(carry);
			}
			return returnNode;
		}
	}

	/**
	 * Given a linked list and a value x, partition it such that all nodes less
	 * than x come before nodes greater than or equal to x.
	 * 
	 * You should preserve the original relative order of the nodes in each of
	 * the two partitions.
	 * 
	 * For example, Given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static ListNode partition(ListNode a, int b) {
		if (a == null) {
			return a;
		} else {
			// check for initial nodes
			ListNode returnStartNode = null;
			ListNode currentReturnNode = null;

			ListNode partitionedStartNode = null;
			ListNode currentPartNode = null;

			ListNode currentNode = a;
			boolean firstReturnNode = true;
			boolean firstPartNode = true;

			while (currentNode != null) {
				if (currentNode.val < b) {
					if (firstReturnNode) {
						returnStartNode = currentNode;
						currentReturnNode = currentNode;
						firstReturnNode = false;
					} else {
						currentReturnNode.next = currentNode;
						currentReturnNode = currentNode;
					}
					if (currentPartNode != null) {
						currentPartNode.next = currentNode.next;
					}
				} else {
					if (firstPartNode) {
						partitionedStartNode = currentNode;
						currentPartNode = currentNode;
						firstPartNode = false;
					} else {
						currentPartNode.next = currentNode;
						currentPartNode = currentNode;
					}
					if (currentReturnNode != null) {
						currentReturnNode.next = currentNode.next;
					}
				}
				currentNode = currentNode.next;
			}

			if (returnStartNode == null) {
				return partitionedStartNode;
			} else {
				currentReturnNode.next = partitionedStartNode;
				return returnStartNode;
			}
		}
	}

	/**
	 * Given a sorted linked list, delete all nodes that have duplicate numbers,
	 * leaving only distinct numbers from the original list.
	 * 
	 * For example, Given 1->2->3->3->4->4->5, return 1->2->5. Given
	 * 1->1->1->2->3, return 2->3.
	 * 
	 * @param a
	 * @return
	 */
	public static ListNode deleteDuplicates1(ListNode a) {
		if (a == null) {
			return a;
		} else {
			ListNode dummyReturnNode = new ListNode(0);
			dummyReturnNode.next = a;
			ListNode prevNode = dummyReturnNode;
			ListNode currentNode = a;
			boolean removeCurrent = false;
			while (currentNode != null) {
				int currentValue = currentNode.val;
				if (currentNode.next != null
						&& currentNode.next.val == currentValue) {
					removeCurrent = true;
				} else {
					prevNode.next = currentNode;
					prevNode = currentNode;
					currentNode = currentNode.next;
					continue;
				}
				while (removeCurrent && currentNode != null
						&& currentNode.val == currentValue) {
					currentNode = currentNode.next;
				}
				prevNode.next = currentNode;
				removeCurrent = false;
			}

			return dummyReturnNode.next;
		}
	}

	/**
	 * Given a linked list, swap every two adjacent nodes and return its head.
	 * 
	 * For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
	 * 
	 * Your algorithm should use only constant space. You may not modify the
	 * values in the list, only nodes itself can be changed.
	 * 
	 * @param a
	 * @return
	 */
	public static ListNode swapPairs(ListNode a) {
		if (a == null) {
			return a;
		} else {
			ListNode dummyStartNode = new ListNode(0);
			dummyStartNode.next = a;
			ListNode current = a;
			ListNode prev = dummyStartNode;

			while (current != null && current.next != null) {
				prev.next = current.next;
				current.next = current.next.next;
				prev.next.next = current;

				prev = current;
				current = current.next;
			}
			return dummyStartNode.next;
		}
	}

	public static ListNode reverseList(ListNode a) {
		if (a == null) {
			return a;
		} else {
			ListNode temp = null;
			ListNode prev = null;
			ListNode current = a;
			while (current != null) {
				temp = current.next;
				current.next = prev;
				prev = current;
				current = temp;
			}
			return prev;
		}
	}

	public static ListNode reverseListRecurse(ListNode a) {
		if (a == null) {
			return a;
		} else {
			if (a.next != null) {
				ListNode start = reverseListRecurse(a.next);
				a.next.next = a;
				a.next = null;
				return start;
			}
			return a;
		}
	}

	public static ListNode reverseBetween(ListNode a, int m, int n) {
		if (a == null) {
			return a;
		} else if (n <= m) {
			return a;
		} else {
			ListNode dummyReturnNode = new ListNode(0);
			dummyReturnNode.next = a;
			int count = 0;
			ListNode prev = null;
			ListNode current = dummyReturnNode;
			ListNode temp = null;
			while ((count < m) && current != null) {
				prev = current;
				current = current.next;
				count++;
			}
			if (current == null) {
				return a;
			}

			ListNode tempPrev = prev;
			ListNode reverseEnd = current;

			while ((count <= n) && current != null) {
				temp = current.next;
				current.next = prev;
				prev = current;
				current = temp;
				count++;
			}

			tempPrev.next = prev;
			reverseEnd.next = current;

			return dummyReturnNode.next;

		}
	}

	public static ListNode detectCycle(ListNode a) {
		if (a == null) {
			return null;
		} else {
			// get inside loop
			ListNode slow = a;
			ListNode fast = a.next;
			while (slow != fast && fast != null && fast.next != null) {
				slow = slow.next;
				fast = fast.next.next;
			}
			if (fast == null || fast.next == null) {
				return null;
			}

			// we are inside loop. count the nodes inside loop
			int count = 1;
			slow = slow.next;
			while (slow != fast) {
				count++;
				slow = slow.next;
			}
			// System.out.println("count" + count);
			fast = a;
			while (count != 0) {
				fast = fast.next;
				count--;
			}

			slow = a;
			while (fast != slow) {
				fast = fast.next;
				slow = slow.next;
			}
			return slow;

		}
	}

	/**
	 * Given an absolute path for a file (Unix-style), simplify it.
	 * 
	 * Examples:
	 * 
	 * path = "/home/", => "/home" path = "/a/./b/../../c/", => "/c" Note that
	 * absolute path always begin with ‘/’ ( root directory ) Path will not have
	 * whitespace characters.
	 * 
	 * @param a
	 * @return
	 */
	public static String simplifyPath(String a) {
		String[] split = a.split("/");
		Stack<String> stk = new Stack<String>();
		for (String string : split) {
			// System.out.println(string);
			if (string.equals("") || string.equals(".")) {
				continue;
			} else if (string.equals("..")) {
				if (!stk.empty()) {
					stk.pop();
				}
			} else {
				stk.push(string);
			}
		}

		Stack<String> stk1 = new Stack<String>();
		while (!stk.empty()) {
			stk1.push(stk.pop());
		}

		StringBuilder strBld = new StringBuilder();
		if (stk1.empty()) {
			return "/";
		}
		while (!stk1.empty()) {
			strBld.append("/");
			strBld.append(stk1.pop());
		}

		return strBld.toString();
	}

	public static int evalRPN(ArrayList<String> a) {
		if (a == null) {
			return 0;
		}

		Stack<Integer> stk = new Stack<Integer>();
		for (String string : a) {
			if (string.equals("+")) {
				int val2 = stk.pop();
				int val1 = stk.pop();
				stk.push(val1 + val2);
			} else if (string.equals("-")) {
				int val2 = stk.pop();
				int val1 = stk.pop();
				stk.push(val1 - val2);
			} else if (string.equals("*")) {
				int val2 = stk.pop();
				int val1 = stk.pop();
				stk.push(val1 * val2);
			} else if (string.equals("/")) {
				int val2 = stk.pop();
				int val1 = stk.pop();
				stk.push(val1 / val2);
			} else {
				stk.push(Integer.parseInt(string));
			}
		}
		return stk.pop();
	}

	/**
	 * Combinations - Backtracking 
	 * 
	 * Given two integers n and k, return all possible combinations of k numbers
	 * out of 1 2 3 ... n.
	 * 
	 * Make sure the combinations are sorted.
	 * 
	 * To elaborate, 
	 * 1. Within every entry, elements should be sorted. [1, 4] is a valid entry while [4, 1] is not. 
	 * 2. Entries should be sorted within themselves.
	 * 
	 * Example : If n = 4 and k = 2, a solution is:
	 * 
	 * [ [1,2], [1,3], [1,4], [2,3], [2,4], [3,4], ]
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> combine(int n, int k) {
		if (n < 0 || k < 0) {
			return null;
		} else if (n == 0) {
			return null;
		} else if (k == 0) {
			return new ArrayList<ArrayList<Integer>>();
		} else if (n < k) {
			return new ArrayList<ArrayList<Integer>>();
		} else {
			ArrayList<ArrayList<Integer>> returnList = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> currentList = new ArrayList<Integer>();
			combine(1, n, k, returnList, currentList);
			return returnList;
		}

	}

	private static void combine(int start, int end, int k,
			ArrayList<ArrayList<Integer>> returnList,
			ArrayList<Integer> currentList) {
		if (k == 0) {
			if (currentList.size() > 0) {
				ArrayList<Integer> newList = new ArrayList<Integer>();
				newList.addAll(currentList);
				returnList.add(newList);
				return;
			}
		}

		for (int i = start; i <= end - k + 1; i++) {
			currentList.add(i);
			combine(i + 1, end, k - 1, returnList, currentList);
			currentList.remove(currentList.size() - 1);
		}
	}

	
	/**
	 * Letter Phone - Backtracking 
	 * 
	 * Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



The digit 0 maps to 0 itself.
The digit 1 maps to 1 itself.

Input: Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
	 * @param a
	 * @return
	 */
	public static ArrayList<String> letterCombinations(String a) {
		Map<Character, Character[]> letterMap = new HashMap<Character, Character[]>();
		letterMap.put('0', new Character[] { '0' });
		letterMap.put('1', new Character[] { '1' });
		letterMap.put('2', new Character[] { 'a', 'b', 'c' });
		letterMap.put('3', new Character[] { 'd', 'e', 'f' });
		letterMap.put('4', new Character[] { 'g', 'h', 'i' });
		letterMap.put('5', new Character[] { 'j', 'k', 'l' });
		letterMap.put('6', new Character[] { 'm', 'n', 'o' });
		letterMap.put('7', new Character[] { 'p', 'q', 'r', 's' });
		letterMap.put('8', new Character[] { 't', 'u', 'v' });
		letterMap.put('9', new Character[] { 'w', 'x', 'y', 'z' });

		ArrayList<String> returnList = new ArrayList<String>();
		StringBuilder strBuilder = new StringBuilder();

		combineLetter(0, a, strBuilder, returnList, letterMap);

		return returnList;

	}

	private static void combineLetter(int currentInd, String a,
			StringBuilder strBuilder, ArrayList<String> returnList,
			Map<Character, Character[]> letterMap) {

		if (currentInd == a.length()) {
			returnList.add(strBuilder.toString());
		} else {
			for (Character currentChar : letterMap.get(a.charAt(currentInd))) {
				strBuilder.append(currentChar);
				combineLetter(currentInd + 1, a, strBuilder, returnList,
						letterMap);
				strBuilder.setLength(strBuilder.length() - 1);
			}
		}

	}

	/**
	 * Generate all Parentheses II - Backtracking 
	 * 
	 * Given n pairs of parentheses, write a function to generate all
	 * combinations of well-formed parentheses of length 2*n.
	 * 
	 * For example, given n = 3, a solution set is:
	 * 
	 * "((()))", "(()())", "(())()", "()(())", "()()()" Make sure the returned
	 * list of strings are sorted.
	 * 
	 * @param a
	 * @return
	 */
	public static ArrayList<String> generateParenthesis(int a) {

		Set<String> set = new HashSet<String>();
		ArrayList<String> returnList = new ArrayList<String>();
		StringBuilder strBuilder = new StringBuilder();
		int currentIndex = 0;
		generateParenthesis(0, 0, currentIndex, 2 * a, strBuilder, set);

		returnList.addAll(set);
		Collections.sort(returnList);
		return returnList;

	}

	private static void generateParenthesis(int leftBrackets,
			int rightBrackets, int currentIndex, int totalBrackets,
			StringBuilder strBuilder, Set<String> returnList) {
		if (leftBrackets == totalBrackets / 2) {
			for (int i = currentIndex; i < totalBrackets; i++) {
				strBuilder.append(")");
			}
			returnList.add(strBuilder.toString());
			strBuilder.setLength(currentIndex);
			return;
		} else if ((rightBrackets > leftBrackets)
				|| (leftBrackets > totalBrackets / 2)) {
			return;
		} else if (currentIndex == totalBrackets) {
			returnList.add(strBuilder.toString());
		} else {
			strBuilder.append("(");
			generateParenthesis(leftBrackets + 1, rightBrackets,
					currentIndex + 1, totalBrackets, strBuilder, returnList);
			strBuilder.setLength(strBuilder.length() - 1);
			strBuilder.append(")");
			generateParenthesis(leftBrackets, rightBrackets + 1,
					currentIndex + 1, totalBrackets, strBuilder, returnList);
			strBuilder.setLength(strBuilder.length() - 1);
		}

	}

	
	/**
	 * Permutations - Backtracking 
	 * 
	 * Given a collection of numbers, return all possible permutations.

Example:

[1,2,3] will have the following permutations:

[1,2,3]
[1,3,2]
[2,1,3] 
[2,3,1] 
[3,1,2] 
[3,2,1]
 NOTE
No two entries in the permutation sequence should be the same.
For the purpose of this problem, assume that all the numbers in the collection are unique.
	 * 
	 * @param a
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> a) {
		ArrayList<ArrayList<Integer>> returnList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> currentList = new ArrayList<Integer>();
		boolean[] used = new boolean[a.size()];
		int startIndex = 0;

		permute1(startIndex, a, currentList, returnList, used);
		return returnList;
	}

	private static void permute1(int currentIndex, ArrayList<Integer> a,
			ArrayList<Integer> currentList,
			ArrayList<ArrayList<Integer>> returnList, boolean[] used) {
		if (currentIndex == a.size()) {
			ArrayList<Integer> listToAdd = new ArrayList<Integer>();
			listToAdd.addAll(currentList);
			returnList.add(listToAdd);
			return;
		} else {
			for (int i = 0; i < a.size(); i++) {
				if (!used[i]) {
					used[i] = true;
					currentList.add(a.get(i));
					permute1(currentIndex + 1, a, currentList, returnList, used);
					used[i] = false;
					currentList.remove(currentList.size() - 1);
				}
			}
		}

	}

	/**
	 * The set [1,2,3,…,n] contains a total of n! unique permutations.
	 * 
	 * By listing and labeling all of the permutations in order, We get the
	 * following sequence (ie, for n = 3 ) :
	 * 
	 * 1. "123" 2. "132" 3. "213" 4. "231" 5. "312" 6. "321" Given n and k,
	 * return the kth permutation sequence.
	 * 
	 * For example, given n = 3, k = 4, ans = "231"
	 * 
	 * Good questions to ask the interviewer : What if n is greater than 10. How
	 * should multiple digit numbers be represented in string? > In this case,
	 * just concatenate the number to the answer. > so if n = 11, k = 1, ans =
	 * "1234567891011" Whats the maximum value of n and k? > In this case, k
	 * will be a positive integer thats less than INT_MAX. > n is reasonable
	 * enough to make sure the answer does not bloat up a lot.
	 * 
	 * @param n
	 * @param k
	 * @return
	 */

	// First solution
	/**
	 * This involves a little bit of maths and recursion for simplicity.
	 * 
	 * Number of permutation possible using n distinct numbers = n!
	 * 
	 * Lets first make k 0 based. Let us first look at what our first number
	 * should be. Number of sequences possible with 1 in first position : (n-1)!
	 * Number of sequences possible with 2 in first position : (n-1)! Number of
	 * sequences possible with 3 in first position : (n-1)!
	 * 
	 * Hence, the number at our first position should be k / (n-1)! + 1 th
	 * integer.
	 * 
	 * Can we reduce the k and modify the set we pick our numbers from (
	 * initially 1 2 3 … n ) to call the function for second position onwards ?
	 * 
	 * @param n
	 * @param k
	 * @return
	 */

	/*
	 * public String getPermutation(int A, int B) { ArrayList<Integer>
	 * candidateSet = new ArrayList<>(); for (int i = 1; i <= A; i++){
	 * candidateSet.add(i); } return getPermutation(B - 1, candidateSet); }
	 * 
	 * private static String getPermutation(int k, ArrayList<Integer>
	 * candidateSet) { int n = candidateSet.size(); if (n == 0) { return ""; }
	 * if (k > factorial(n)) return ""; // invalid. Should never reach here.
	 * 
	 * int f = factorial(n - 1); int pos = k / f; k %= f; String ch =
	 * String.valueOf(candidateSet.get(pos)); // now remove the character ch
	 * from candidateSet. candidateSet.remove(pos); return ch +
	 * getPermutation(k, candidateSet); }
	 * 
	 * static int factorial(int n) { if (n > 12) { // this overflows in int. So,
	 * its definitely greater than k // which is all we care about. So, we
	 * return INT_MAX which // is also greater than k. return Integer.MAX_VALUE;
	 * } // Can also store these values. But this is just < 12 iteration, so
	 * meh! int fact = 1; for (int i = 2; i <= n; i++) fact *= i; return fact; }
	 */

	
	/**
	 * Kth Permutation Sequence - Backtracking 
	 * 
	 * The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3 ) :

1. "123"
2. "132"
3. "213"
4. "231"
5. "312"
6. "321"
Given n and k, return the kth permutation sequence.

For example, given n = 3, k = 4, ans = "231"

 Good questions to ask the interviewer :
What if n is greater than 10. How should multiple digit numbers be represented in string?
> In this case, just concatenate the number to the answer.
> so if n = 11, k = 1, ans = "1234567891011"
Whats the maximum value of n and k?
> In this case, k will be a positive integer thats less than INT_MAX.
> n is reasonable enough to make sure the answer does not bloat up a lot.


	 * @param n
	 * @param k
	 * @return
	 */
	public static String getPermutation(int n, int k) {
		HashMap<Integer, String> permMap = new HashMap<Integer, String>();
		StringBuilder strBuilder = new StringBuilder();
		boolean[] used = new boolean[n + 1];
		int startIndex = 1;

		permute(startIndex, n, strBuilder, permMap, used);
		// System.out.println(permMap);
		return permMap.get(k);
	}

	private static void permute(int currentIndex, int n,
			StringBuilder strBuilder, HashMap<Integer, String> permMap,
			boolean[] used) {
		if (currentIndex == n + 1) {
			permMap.put(permMap.size() + 1, strBuilder.toString());
			return;
		} else {
			for (int i = 1; i <= n; i++) {
				if (!used[i]) {
					used[i] = true;
					strBuilder.append(i);
					permute(currentIndex + 1, n, strBuilder, permMap, used);
					used[i] = false;
					strBuilder.setLength(strBuilder.length()
							- (String.valueOf(i)).length());
				}
			}
		}

	}

	/**
	 * NQueens - Backtracking
	 * 
	 * The n-queens puzzle is the problem of placing n queens on an n×n
	 * chessboard such that no two queens attack each other.
	 * 
	 * 
	 * 
	 * Given an integer n, return all distinct solutions to the n-queens puzzle.
	 * 
	 * Each solution contains a distinct board configuration of the n-queens’
	 * placement, where 'Q' and '.' both indicate a queen and an empty space
	 * respectively.
	 * 
	 * For example, There exist two distinct solutions to the 4-queens puzzle: [
	 * [".Q..", // Solution 1 "...Q", "Q...", "..Q."],
	 * 
	 * ["..Q.", // Solution 2 "Q...", "...Q", ".Q.."] ]
	 * 
	 * @param a
	 * @return
	 */
	public static ArrayList<ArrayList<String>> solveNQueens(int a) {

		ArrayList<ArrayList<String>> returnList = new ArrayList<ArrayList<String>>();

		int[] xCoordinates = new int[a];
		int[] yCoordinates = new int[a];

		int currentQueenNo = 1;

		placeOnBoard(a, xCoordinates, yCoordinates, currentQueenNo, returnList);
		/*
		 * for (int i = 0; i < xCoordinates.length; i++) {
		 * System.out.println(xCoordinates[i] + ", " + yCoordinates[i]); }
		 */

		return returnList;
	}

	private static boolean placeOnBoard(int n, int[] xCoordinates,
			int[] yCoordinates, int currentQueenNo,
			ArrayList<ArrayList<String>> returnList) {
		if (currentQueenNo == n + 1) {
			// all queens are placed now.
			ArrayList<String> list = new ArrayList<String>();
			for (int index = 0; index < n; index++) {
				StringBuilder strBldr = new StringBuilder();
				for (int col = 0; col < n; col++) {
					if (col == yCoordinates[index]) {
						strBldr.append("Q");
					} else {
						strBldr.append(".");
					}
				}
				list.add(strBldr.toString());
			}
			returnList.add(list);
			return true;
		} else {
			int i = currentQueenNo - 1;
			for (int j = 0; j < n; j++) {
				if (isValidMove(xCoordinates, yCoordinates, i, j,
						currentQueenNo - 1)) {
					xCoordinates[i] = i;
					yCoordinates[i] = j;
					placeOnBoard(n, xCoordinates, yCoordinates,
							currentQueenNo + 1, returnList);
					// backtrack;
					xCoordinates[i] = -1;
					yCoordinates[i] = -1;
				}
			}
			return false;
		}
	}

	private static boolean isValidMove(int[] xCoordinates, int[] yCoordinates,
			int i, int j, int n) {
		for (int x = 0; x < n; x++) {
			if (i == xCoordinates[x] || j == yCoordinates[x]) {
				return false;
			} else {
				if (Math.abs(xCoordinates[x] - i) == Math.abs(yCoordinates[x]
						- j)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Pascal1 - Arrays
	 * 
	 * Given numRows, generate the first numRows of Pascal’s triangle.
	 * 
	 * Pascal’s triangle : To generate A[C] in row R, sum up A’[C] and A’[C-1]
	 * from previous row R - 1.
	 * 
	 * Example:
	 * 
	 * Given numRows = 5,
	 * 
	 * Return
	 * 
	 * [ [1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1] ] Generates the pascal
	 * triangle
	 * 
	 * @param a
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> generate(int a) {
		ArrayList<ArrayList<Integer>> returnList = new ArrayList<ArrayList<Integer>>();
		if (a == 0) {
			return returnList;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		returnList.add(list);
		if (a == 1) {
			return returnList;
		} else {
			for (int i = 1; i < a; i++) {
				list = new ArrayList<Integer>();
				for (int j = 0; j <= i; j++) {
					if (i == j) {
						list.add(returnList.get(i - 1).get(j - 1));
					} else if (j == 0) {
						list.add(returnList.get(i - 1).get(0));
					} else {
						list.add(returnList.get(i - 1).get(j - 1)
								+ returnList.get(i - 1).get(j));
					}
				}
				returnList.add(list);
			}
			return returnList;
		}
	}

	public static int colorful(int a) {
		if (a == 0 || a == 1) {
			return 1;
		} else if (a == -1) {
			return 0;
		} else {
			a = Math.abs(a);
			if (a < 10) {
				return 1;
			} else {
				char[] charArray = String.valueOf(a).toCharArray();
				HashSet<Integer> set = new HashSet<Integer>();
				for (int i = 1; i <= charArray.length; i++) {
					StringBuilder strBld = new StringBuilder();
					boolean isColorFul = checkColorFul(i, charArray, strBld,
							set);
					if (!isColorFul) {
						return 0;
					}
				}
				return 1;
			}
		}

	}

	private static boolean checkColorFul(int length, char[] charArray,
			StringBuilder strBld, HashSet<Integer> set) {
		for (int i = 0; i < charArray.length - length + 1; i++) {
			int mult = 1;
			int index = i;
			for (int j = 0; j < length; j++) {
				mult = mult * (charArray[index++] - '0');
			}
			if (set.contains(mult)) {
				return false;
			} else {
				set.add(mult);
			}
		}
		return true;
	}

	/*
	 * public static String minWindow(String S, String T) { int resStringStart =
	 * -1; int resultStartInd = Integer.MIN_VALUE; int resultEndInd =
	 * Integer.MAX_VALUE; long sizeOfResultStr = Long.MAX_VALUE; boolean
	 * hasAllCharAppeared = false; Map<Character, Integer> charCountMap = new
	 * HashMap<Character, Integer>(); Map<Character, ArrayList<Integer>>
	 * charIndexMap = new HashMap<Character, ArrayList<Integer>>();
	 * 
	 * if(T == null){ return ""; } else{ for (int i =0; i<T.length(); i++) {
	 * if(charCountMap.get(T.charAt(i)) == null){ charCountMap.put(T.charAt(i),
	 * 1); } else{ charCountMap.put(T.charAt(i), charCountMap.get(T.charAt(i)) +
	 * 1); }
	 * 
	 * } }
	 * 
	 * for (int i =0; i<S.length(); i++) { //check if this character is from the
	 * T string. if( charCountMap.containsKey(S.charAt(i)) ){ //Update the
	 * indexMap ArrayList<Integer> indexList = charIndexMap.get(S.charAt(i));
	 * if(indexList == null){ ArrayList<Integer> list = new
	 * ArrayList<Integer>(); if(list.size() == charCountMap.get(S.charAt(i))){
	 * if(hasAllCharAppeared){
	 * 
	 * } list.remove(0); } list.add(i);
	 * 
	 * }
	 * 
	 * //If a character appears multiple time in T, then in the output String
	 * also it should appear same no of times. if(!hasAllCharAppeared){
	 * //charCountMap.put(T.charAt(i), charCountMap.get(T.charAt(i)) - 1);
	 * //Check if all characters appeared hasAllCharAppeared =
	 * checkAllAppeard(S.charAt(i), charCountMap, charIndexMap);
	 * 
	 * if(hasAllCharAppeared){ //Need to update the resultLenght, startInd and
	 * EndInd int minInd = -1; int maxInd = -1; for (Character currChar :
	 * charIndexMap.keySet()) { ArrayList<Integer> arrayList =
	 * charIndexMap.get(currChar); if(minInd > arrayList.get(0)){ minInd =
	 * arrayList.get(0); } if(maxInd <
	 * arrayList.get(charCountMap.get(currChar)-1)){ maxInd =
	 * arrayList.get(charCountMap.get(currChar)-1); } } int size =
	 * maxInd-minInd+1; if(sizeOfResultStr > size){ sizeOfResultStr = size;
	 * resultStartInd = minInd; resultEndInd = maxInd; } }
	 * 
	 * } else{//we already have all chars... need to update the index list and
	 * then calculate the size of string. indexList =
	 * charIndexMap.get(S.charAt(i));
	 * 
	 * } }
	 * 
	 * } }
	 */

	/**
	 * Think 2 pointers and hashing.
	 * 
	 * Essentially you have a start and end pointer starting from the beginning
	 * of the string. You keep moving the end pointer and including more
	 * characters till you have all the characters of T included. At this point,
	 * you start moving start pointer and popping out characters till the point
	 * that you still have all the characters of T included. Update your answer
	 * and keep repeating the process.
	 * 
	 * @param charAt
	 * @param charCountMap
	 * @param charIndexMap
	 * @return
	 */

	public String minWindow(String S, String T) {

		int start = 0;
		int end = 0;
		int n;
		char c;

		if (S == null || T == null)
			return null;

		HashMap<Character, Integer> count = new HashMap<>();
		HashMap<Character, Integer> hashMap = new HashMap<>();

		n = T.length();

		for (int i = 0; i < n; i++) {
			c = T.charAt(i);
			add(count, c, 1);
			add(hashMap, c, 0);
		}

		ArrayList<Integer> res = new ArrayList<>();
		n = S.length();

		while (end < n) {
			c = S.charAt(end);
			add(hashMap, c, 1);
			while (start < end) {
				decrement(hashMap, S.charAt(start));
				if (!validString(count, hashMap)) {
					add(hashMap, S.charAt(start), 1);
					break;
				}
				start++;
			}
			updateResult(res, start, end, count, hashMap);
			end++;
		}

		if (res.size() == 0)
			return "";

		start = res.get(0);
		end = res.get(1);

		return S.substring(start, end + 1);

	}

	public void add(HashMap<Character, Integer> map, char key, int v) {
		int val;
		val = v;
		if (map.containsKey(key)) {
			val += map.get(key);
		}
		map.put(key, val);
	}

	public void decrement(HashMap<Character, Integer> map, char key) {
		int val;
		if (!map.containsKey(key))
			return;
		val = map.get(key);
		val--;
		map.put(key, val);
	}

	public void updateResult(ArrayList<Integer> res, int start, int end,
			HashMap<Character, Integer> count,
			HashMap<Character, Integer> hashMap) {
		if (!validString(count, hashMap))
			return;

		int i, j;

		if (res.size() == 0) {
			res.add(start);
			res.add(end);
			return;
		}

		i = res.get(0);
		j = res.get(1);

		if (j - i <= end - start)
			return;

		res.clear();
		res.add(start);
		res.add(end);
	}

	public boolean validString(HashMap<Character, Integer> count,
			HashMap<Character, Integer> hashMap) {
		for (Map.Entry<Character, Integer> entry : count.entrySet()) {
			char key = entry.getKey();
			int val = entry.getValue();
			if (val > hashMap.get(key))
				return false;
		}
		return true;
	}

	/**
	 * Given two integers representing the numerator and denominator of a
	 * fraction, return the fraction in string format.
	 * 
	 * If the fractional part is repeating, enclose the repeating part in
	 * parentheses.
	 * 
	 * Example :
	 * 
	 * Given numerator = 1, denominator = 2, return "0.5" Given numerator = 2,
	 * denominator = 1, return "2" Given numerator = 2, denominator = 3, return
	 * "0.(6)"
	 * 
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public static String fractionToDecimal(int numerator, int denominator) {
		long a = numerator, b = denominator;
		if (a % b == 0)
			return String.valueOf(a / b);
		Map<Long, Integer> map = new HashMap<>();
		StringBuilder res = new StringBuilder();
		if ((a > 0 && b < 0) || (a < 0 && b > 0))
			res.append("-");
		a = Math.abs(a);
		b = Math.abs(b);
		res.append(a / b + ".");
		a = (a % b) * 10;
		while (!map.containsKey(a)) {
			map.put(a, res.length());
			res.append(String.valueOf(a / b));
			a = (a % b) * 10;
			if (a == 0)
				return res.toString();
		}
		return res.insert(map.get(a), "(").append(")").toString();
	}

	/**
	 * You are given a string, S, and a list of words, L, that are all of the
	 * same length.
	 * 
	 * Find all starting indices of substring(s) in S that is a concatenation of
	 * each word in L exactly once and without any intervening characters.
	 * 
	 * Example :
	 * 
	 * S: "barfoothefoobarman" L: ["foo", "bar"] You should return the indices:
	 * [0,9]. (order does not matter).
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static ArrayList<Integer> findSubstring(String a,
			final List<String> b) {
		if (a == null || a.length() == 0 || b == null || b.size() == 0) {
			return new ArrayList<Integer>();
		} else {
			ArrayList<Integer> returnList = new ArrayList<Integer>();
			int sizeOfStrs = b.get(0).length();
			if (sizeOfStrs == 0) {
				return returnList;
			}

			Map<String, List<Integer>> strIndexMap = new HashMap<>();
			for (int i = 0; i < b.size(); i++) {
				if (strIndexMap.containsKey(b.get(i))) {
					strIndexMap.get(b.get(i)).add(i);
				} else {
					List<Integer> list = new ArrayList<Integer>();
					strIndexMap.put(b.get(i), list);
					list.add(i);
				}
			}

			int currentIndex = 0;
			while (currentIndex + b.size() * sizeOfStrs <= a.length()) {
				// match all strings of size sizeOfStrs from this index.
				Set<Integer> indexSet = new HashSet<Integer>();
				boolean allFind = true;
				for (int j = 0; j < b.size(); j++) {
					int subStrStartInd = currentIndex + j * sizeOfStrs;
					String subStr = a.substring(subStrStartInd, subStrStartInd
							+ sizeOfStrs);
					if (!strIndexMap.containsKey(subStr)) {
						allFind = false;
						break;
					} else {
						int addCount = 0;
						for (Integer index : strIndexMap.get(subStr)) {
							if (!indexSet.contains(index)) {
								indexSet.add(index);
								addCount++;
								break;
							}
						}
						if (addCount == 0) {
							allFind = false;
							break;
						}
					}
				}
				if (allFind) {
					returnList.add(currentIndex);
				}
				currentIndex++;

			}
			return returnList;
		}
	}

	public static void dateTest() {
		long time = new Date().getTime();
		Date date = new Date(time);

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		// Date time2 = cal.getTime();
		System.out.println("Date 1 = " + date.getTime());
		System.out.println("Date 1 = " + cal.getTimeInMillis());

	}

	/**
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		if (B > A.size()) {
			return returnList;
		}

		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < B; i++) {
			Integer count = countMap.get(A.get(i));
			if (count != null) {
				countMap.put(A.get(i), count + 1);
			} else {
				countMap.put(A.get(i), 1);
			}
		}

		int windowStartIndex = 0;
		while (true) {
			// check for unique digits
			returnList.add(countMap.keySet().size());
			// System.out.println(returnList);
			windowStartIndex++;
			if (windowStartIndex + B > A.size()) {
				break;
			}
			// update the countMap
			Integer count = countMap.get(A.get(windowStartIndex - 1));
			if (count == 1) {
				countMap.remove(A.get(windowStartIndex - 1));
			} else {
				countMap.put(A.get(windowStartIndex - 1), count - 1);
			}

			count = countMap.get(A.get(windowStartIndex + B - 1));
			if (count != null) {
				countMap.put(A.get(windowStartIndex + B - 1), count + 1);
			} else {
				countMap.put(A.get(windowStartIndex + B - 1), 1);
			}
		}
		return returnList;
	}

	public static ListNode mergeKLists(ArrayList<ListNode> a) {
		if (a == null || a.size() == 0) {
			return null;
		}
		ListNode returnNode = null;
		ListNode returnListLastNode = null;
		NavigableMap<Integer, List<ListNode>> listMap = new TreeMap<>();
		// populate the treeMap
		for (ListNode listNode : a) {
			if (listNode != null) {
				List<ListNode> list = listMap.get(listNode.val);
				if (list == null) {
					list = new ArrayList<ListNode>();
					listMap.put(listNode.val, list);
				}
				list.add(listNode);
			}
		}

		// Now we need to merge the lists
		while (listMap.keySet().size() > 0) {
			// get the smallest key from the listMap
			Integer currentVal = listMap.firstKey();
			List<ListNode> listsWithLowestKey = listMap.get(currentVal);
			listMap.remove(currentVal);
			for (ListNode listNode : listsWithLowestKey) {
				if (listNode.next != null) {
					List<ListNode> list = listMap.get(listNode.next.val);
					if (list == null) {
						list = new ArrayList<ListNode>();
						listMap.put(listNode.next.val, list);
					}
					list.add(listNode.next);
				}
				if (returnNode == null) {
					returnNode = listNode;
					listNode.next = null;
					returnListLastNode = listNode;
				} else {
					returnListLastNode.next = listNode;
					listNode.next = null;
					returnListLastNode = listNode;
				}
			}
		}
		return returnNode;
	}

	/**
	 * Given a binary tree, determine if it is height-balanced.
	 * 
	 * Height-balanced binary tree : is defined as a binary tree in which the
	 * depth of the two subtrees of every node never differ by more than 1.
	 * Return 0 / 1 ( 0 for false, 1 for true ) for this problem
	 * 
	 * Example :
	 * 
	 * Input : 1 / \ 2 3
	 * 
	 * Return : True or 1
	 * 
	 * Input 2 : 3 / 2 / 1
	 * 
	 * Return : False or 0 Because for the root node, left subtree has depth 2
	 * and right subtree has depth 0. Difference = 2 > 1.
	 * 
	 * @param a
	 * @return
	 */
	public static int isBalanced(TreeNode a) {
		if (a == null) {
			return 1;
		}
		int leftHeight = isBalancedReturnHeight(a.left);
		int rightHeight = isBalancedReturnHeight(a.right);
		if (leftHeight >= 0 && rightHeight >= 0) {
			if (Math.abs(leftHeight - rightHeight) <= 1) {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * if the tree is balanced than return the height of tree, otherwise return
	 * -1;
	 * 
	 * @param treeNode
	 * @return
	 */
	private static int isBalancedReturnHeight(TreeNode treeNode) {
		if (treeNode == null) {
			return 0;
		}
		int leftHeight = isBalancedReturnHeight(treeNode.left);
		int rightHeight = isBalancedReturnHeight(treeNode.right);
		if (leftHeight >= 0 && rightHeight >= 0) {
			if (Math.abs(leftHeight - rightHeight) <= 1) {
				if (leftHeight > rightHeight) {
					return leftHeight + 1;
				} else {
					return rightHeight + 1;
				}
			}
		}
		return -1;
	}

	/**
	 * Given a binary tree, invert the binary tree and return it. Look at the
	 * example for more details.
	 * 
	 * Example : Given binary tree
	 * 
	 * 1 / \ 2 3 / \ / \ 4 5 6 7 invert and return
	 * 
	 * 1 / \ 3 2 / \ / \ 7 6 5 4
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		} else {
			TreeNode temp;
			invertTree(root.left);
			invertTree(root.right);
			temp = root.left;
			root.left = root.right;
			root.right = temp;
			return root;
		}
	}

	/**
	 * Given a binary search tree, write a function to find the kth smallest
	 * element in the tree.
	 * 
	 * Example :
	 * 
	 * Input : 2 / \ 1 3
	 * 
	 * and k = 2
	 * 
	 * Return : 2
	 * 
	 * As 2 is the second smallest element in the tree. NOTE : You may assume 1
	 * <= k <= Total number of nodes in BST
	 * 
	 * @param root
	 * @param k
	 * @return
	 */
	public static int kthsmallest(TreeNode root, int k) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		CountObject countObj = new CountObject();
		countObj.count = k;
		int kthSmallestInBST = getKthSmallestInBST(root, countObj);
		if (countObj.count == 0) {
			return kthSmallestInBST;
		}
		return Integer.MIN_VALUE;
	}

	private static int getKthSmallestInBST(TreeNode root, CountObject countObj) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		int kthSmallestInBST = getKthSmallestInBST(root.left, countObj);
		if (countObj.count == 0) {
			return kthSmallestInBST;
		}
		countObj.count = countObj.count - 1;
		if (countObj.count == 0) {
			return root.val;
		}
		return getKthSmallestInBST(root.right, countObj);
	}

	/**
	 * CARTESIAN- Trees
	 * 
	 * Given an inorder traversal of a cartesian tree, construct the tree.
	 * 
	 * Cartesian tree : is a heap ordered binary tree, where the root is greater
	 * than all the elements in the subtree. Note: You may assume that
	 * duplicates do not exist in the tree. Example :
	 * 
	 * Input : [1 2 3]
	 * 
	 * Return : 3 / 2 / 1
	 * 
	 * @param a
	 * @return
	 */
	public static TreeNode buildTree(ArrayList<Integer> a) {
		if (a == null || a.size() == 0) {
			return null;
		}
		return buildCartesianTree(a, 0, a.size() - 1);
	}

	private static TreeNode buildCartesianTree(ArrayList<Integer> a, int i,
			int j) {
		if (i == j) {
			return new TreeNode(a.get(i));
		}
		if (i > j) {
			return null;
		}
		int index = getMaxIndex(a, i, j);
		TreeNode returnNode = new TreeNode(a.get(index));
		returnNode.left = buildCartesianTree(a, i, index - 1);
		returnNode.right = buildCartesianTree(a, index + 1, j);
		return returnNode;
	}

	private static int getMaxIndex(ArrayList<Integer> a, int i, int j) {
		int max = a.get(i);
		int maxIndex = i;
		for (int index = i; index <= j; index++) {
			if (a.get(index) > max) {
				max = a.get(index);
				maxIndex = index;
			}
		}
		return maxIndex;
	}

	/**
	 * SYMMETRY - tree
	 * 
	 * Given a binary tree, check whether it is a mirror of itself (ie,
	 * symmetric around its center).
	 * 
	 * Example :
	 * 
	 * 1 / \ 2 2 / \ / \ 3 4 4 3 The above binary tree is symmetric. But the
	 * following is not:
	 * 
	 * 1 / \ 2 2 \ \ 3 3 Return 0 / 1 ( 0 for false, 1 for true ) for this
	 * problem
	 * 
	 * @param a
	 * @return
	 */
	public static int isSymmetric(TreeNode a) {
		if (a == null) {
			return 1;
		}
		if (isSymmetric(a.left, a.right)) {
			return 1;
		}
		return 0;
	}

	private static boolean isSymmetric(TreeNode left, TreeNode right) {
		if (left == null) {
			if (right != null) {
				return false;
			}
			return true;
		}
		if (right == null) {
			return false;
		}
		if (left.val == right.val && isSymmetric(left.left, right.right)
				&& isSymmetric(left.right, right.left)) {
			return true;
		}
		return false;
	}

	/**
	 * Prefix - tree
	 * 
	 * Find shortest unique prefix to represent each word in the list.
	 * 
	 * Example:
	 * 
	 * Input: [zebra, dog, duck, dove] Output: {z, dog, du, dov} where we can
	 * see that zebra = z dog = dog duck = du dove = dov NOTE : Assume that no
	 * word is prefix of another. In other words, the representation is always
	 * possible.
	 * 
	 * @param a
	 * @return
	 */
	public static ArrayList<String> prefix(ArrayList<String> a) {
		TrieNode trieTree = new TrieNode();
		for (String string : a) {
			char[] charArray = string.toCharArray();
			TrieNode currentNode = trieTree;
			for (int i = 0; i < charArray.length; i++) {
				int index = charArray[i] - 'a';
				TrieNode trieNode = currentNode.nodes[index];
				if (trieNode == null) {
					trieNode = new TrieNode();
					trieNode.ch = charArray[i];
					currentNode.nodes[index] = trieNode;
				}
				trieNode.strList.add(string);
				currentNode = trieNode;
			}
		}

		ArrayList<String> returnList = new ArrayList<String>();
		for (String string : a) {
			char[] charArray = string.toCharArray();
			TrieNode currentNode = trieTree;
			StringBuilder strBld = new StringBuilder();
			for (int i = 0; i < charArray.length; i++) {
				int index = charArray[i] - 'a';
				TrieNode trieNode = currentNode.nodes[index];
				strBld.append(trieNode.ch);
				if (trieNode.strList.size() == 1) {
					break;
				}
				currentNode = trieNode;
			}
			returnList.add(strBld.toString());
		}
		return returnList;
	}

	/**
	 * Inorder - Tree
	 * 
	 * Given a binary tree, return the inorder traversal of its nodes’ values.
	 * 
	 * Example : Given binary tree
	 * 
	 * 1 \ 2 / 3 return [1,3,2].
	 * 
	 * Using recursion is not allowed.
	 * 
	 * @param a
	 * @return
	 */
	public static ArrayList<Integer> inorderTraversal(TreeNode a) {
		if (a == null) {
			return null;
		}
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		TreeNode currentNode = a;
		Stack<TreeNode> stk = new Stack<TreeNode>();
		while (currentNode != null) {
			stk.push(currentNode);
			currentNode = currentNode.left;
		}
		while (!stk.isEmpty()) {
			currentNode = stk.pop();
			returnList.add(currentNode.val);
			currentNode = currentNode.right;
			while (currentNode != null) {
				stk.push(currentNode);
				currentNode = currentNode.left;
			}
		}
		return returnList;

	}

	/**
	 * MAXDEPTH - TREE
	 * 
	 * Given a binary tree, find its maximum depth.
	 * 
	 * The maximum depth is the number of nodes along the longest path from the
	 * root node down to the nearest leaf node.
	 * 
	 * NOTE : The path has to end on a leaf node. Example :
	 * 
	 * 1 / 2 max depth = 2.
	 * 
	 * @param a
	 * @return
	 */
	public static int maxDepth(TreeNode a) {
		if (a == null) {
			return 0;
		} else {
			int maxDepthLeft = maxDepth(a.left);
			int maxDepthRight = maxDepth(a.right);
			if (maxDepthLeft > maxDepthRight) {
				return 1 + maxDepthLeft;
			}
			return 1 + maxDepthRight;
		}
	}

	/**
	 * 
	 * ZIGZAGTREE - TREE
	 * 
	 * Given a binary tree, return the zigzag level order traversal of its
	 * nodes’ values. (ie, from left to right, then right to left for the next
	 * level and alternate between).
	 * 
	 * Example : Given binary tree
	 * 
	 * 3 / \ 9 20 / \ 15 7 return
	 * 
	 * [ [3], [20, 9], [15, 7] ]
	 * 
	 * @param a
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode a) {
		if (a == null) {
			return null;
		}
		ArrayList<ArrayList<Integer>> returnList = new ArrayList<ArrayList<Integer>>();
		Stack<TreeNode> leftStack = new Stack<TreeNode>();
		Stack<TreeNode> rightStack = new Stack<TreeNode>();
		leftStack.push(a);
		while (!leftStack.empty() || !rightStack.empty()) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			while (!leftStack.empty()) {
				TreeNode node = leftStack.pop();
				list.add(node.val);
				if (node.left != null) {
					rightStack.push(node.left);
				}
				if (node.right != null) {
					rightStack.push(node.right);
				}
			}
			if (list.size() > 0) {
				returnList.add(list);
			}

			list = new ArrayList<Integer>();
			while (!rightStack.empty()) {
				TreeNode node = rightStack.pop();
				list.add(node.val);
				if (node.right != null) {
					leftStack.push(node.right);
				}
				if (node.left != null) {
					leftStack.push(node.left);
				}
			}
			if (list.size() > 0) {
				returnList.add(list);
			}
		}
		return returnList;
	}

	/**
	 * LCA - TREE
	 * 
	 * Find the lowest common ancestor in an unordered binary tree given two
	 * values in the tree.
	 * 
	 * Lowest common ancestor : the lowest common ancestor (LCA) of two nodes v
	 * and w in a tree or directed acyclic graph (DAG) is the lowest (i.e.
	 * deepest) node that has both v and w as descendants. Example :
	 * 
	 * 
	 * _______3______ / \ ___5__ ___1__ / \ / \ 6 _2_ 0 8 / \ 7 4 For the above
	 * tree, the LCA of nodes 5 and 1 is 3.
	 * 
	 * LCA = Lowest common ancestor Please note that LCA for nodes 5 and 4 is 5.
	 * 
	 * You are given 2 values. Find the lowest common ancestor of the two nodes
	 * represented by val1 and val2 No guarantee that val1 and val2 exist in the
	 * tree. If one value doesn’t exist in the tree then return -1. There are no
	 * duplicate values. You can use extra memory, helper functions, and can
	 * modify the node struct but, you can’t add a parent pointer.
	 * 
	 * @param a
	 * @param val1
	 * @param val2
	 * @return
	 */
	public static int lca(TreeNode a, int val1, int val2) {
		if (a == null) {
			return -1;
		}

		LCAStruct lcaStruct = new LCAStruct();

		searchNodesAndUpdateLCAStruct(a, val1, val2, lcaStruct);

		if (!lcaStruct.val1Found || !lcaStruct.val2Found) {// any or both val1
															// and val2 are not
															// found return -1
			return -1;
		} else {
			// iterate over both the paths and return the last matching int
			// value;
			int i = 0;
			int returnVal = a.val;
			while (i < lcaStruct.pathTOVal1.size()
					&& i < lcaStruct.pathTOVal2.size()) {
				if (lcaStruct.pathTOVal1.get(i) == lcaStruct.pathTOVal2.get(i)) {
					returnVal = lcaStruct.pathTOVal1.get(i);
					i++;
				} else {
					break;
				}
			}
			return returnVal;
		}

	}

	private static void searchNodesAndUpdateLCAStruct(TreeNode a, int val1,
			int val2, LCAStruct lcaStruct) {
		if (a == null) {
			return;
		} else if (lcaStruct.val1Found && lcaStruct.val2Found) {
			return;
		} else if (lcaStruct.val1Found) {
			if (a.val == val2) {
				lcaStruct.val2Found = true;
				lcaStruct.pathTOVal2.add(a.val);
				return;
			} else {
				lcaStruct.pathTOVal2.add(a.val);
				searchNodesAndUpdateLCAStruct(a.left, val1, val2, lcaStruct);
				if (lcaStruct.val2Found) {
					return;
				} else {
					searchNodesAndUpdateLCAStruct(a.right, val1, val2,
							lcaStruct);
					if (lcaStruct.val2Found) {
						return;
					}
					lcaStruct.pathTOVal2
							.remove(lcaStruct.pathTOVal2.size() - 1);
					return;
				}
			}
		} else if (lcaStruct.val2Found) {
			if (a.val == val1) {
				lcaStruct.val1Found = true;
				lcaStruct.pathTOVal1.add(a.val);
				return;
			} else {
				lcaStruct.pathTOVal1.add(a.val);
				searchNodesAndUpdateLCAStruct(a.left, val1, val2, lcaStruct);
				if (lcaStruct.val1Found) {
					return;
				} else {
					searchNodesAndUpdateLCAStruct(a.right, val1, val2,
							lcaStruct);
					if (lcaStruct.val1Found) {
						return;
					}
					lcaStruct.pathTOVal1
							.remove(lcaStruct.pathTOVal1.size() - 1);
					return;
				}
			}
		} else {// both val1 and val2 not found yet
				// special case when val1 and val2 both are same
			if (val1 == val2 && a.val == val1) {
				lcaStruct.val1Found = true;
				lcaStruct.val2Found = true;
				lcaStruct.pathTOVal1.add(a.val);
				lcaStruct.pathTOVal2.add(a.val);
				return;
			} else if (a.val == val1) {
				lcaStruct.val1Found = true;
				lcaStruct.pathTOVal1.add(a.val);
				// search val2
				lcaStruct.pathTOVal2.add(a.val);
				searchNodesAndUpdateLCAStruct(a.left, val1, val2, lcaStruct);
				if (lcaStruct.val2Found) {
					return;
				} else {
					searchNodesAndUpdateLCAStruct(a.right, val1, val2,
							lcaStruct);
					if (lcaStruct.val2Found) {
						return;
					}
					lcaStruct.pathTOVal2
							.remove(lcaStruct.pathTOVal2.size() - 1);
					return;
				}

			} else if (a.val == val2) {
				lcaStruct.val2Found = true;
				lcaStruct.pathTOVal2.add(a.val);

				// search val1
				lcaStruct.pathTOVal1.add(a.val);
				searchNodesAndUpdateLCAStruct(a.left, val1, val2, lcaStruct);
				if (lcaStruct.val1Found) {
					return;
				} else {
					searchNodesAndUpdateLCAStruct(a.right, val1, val2,
							lcaStruct);
					if (lcaStruct.val1Found) {
						return;
					}
					lcaStruct.pathTOVal1
							.remove(lcaStruct.pathTOVal1.size() - 1);
					return;
				}
			} else {
				lcaStruct.pathTOVal1.add(a.val);
				lcaStruct.pathTOVal2.add(a.val);
				searchNodesAndUpdateLCAStruct(a.left, val1, val2, lcaStruct);
				if (lcaStruct.val1Found && lcaStruct.val2Found) {
					return;
				} else if (lcaStruct.val1Found) {
					// search for val2
					searchNodesAndUpdateLCAStruct(a.right, val1, val2,
							lcaStruct);
					if (lcaStruct.val2Found) {
						return;
					}
					lcaStruct.pathTOVal2
							.remove(lcaStruct.pathTOVal2.size() - 1);
					return;
				} else if (lcaStruct.val2Found) {
					// search for val1
					searchNodesAndUpdateLCAStruct(a.right, val1, val2,
							lcaStruct);
					if (lcaStruct.val1Found) {
						return;
					}
					lcaStruct.pathTOVal1
							.remove(lcaStruct.pathTOVal1.size() - 1);
					return;
				} else {// none of the values found on left subtree. Search both
						// in right subtree
					searchNodesAndUpdateLCAStruct(a.right, val1, val2,
							lcaStruct);
					if (lcaStruct.val1Found && lcaStruct.val2Found) {
						return;
					} else if (lcaStruct.val1Found) {
						lcaStruct.pathTOVal2
								.remove(lcaStruct.pathTOVal2.size() - 1);
						return;
					} else if (lcaStruct.val2Found) {
						lcaStruct.pathTOVal1
								.remove(lcaStruct.pathTOVal1.size() - 1);
						return;
					} else {
						lcaStruct.pathTOVal1
								.remove(lcaStruct.pathTOVal1.size() - 1);
						lcaStruct.pathTOVal2
								.remove(lcaStruct.pathTOVal2.size() - 1);
						return;
					}
				}
			}
		}

	}

	/**
	 * FLATTEN - TREE
	 * 
	 * Given a binary tree, flatten it to a linked list in-place.
	 * 
	 * Example : Given
	 * 
	 * 
	 * 1 / \ 2 5 / \ \ 3 4 6 The flattened tree should look like:
	 * 
	 * 1 \ 2 \ 3 \ 4 \ 5 \ 6 Note that the left child of all nodes should be
	 * NULL.
	 * 
	 * @param a
	 * @return
	 */
	public static TreeNode flatten(TreeNode a) {
		if (a == null) {
			return null;
		}
		if (a.left != null) {
			TreeNode leftFlattenTree = flatten(a.left);
			TreeNode rightFlattenTree = flatten(a.right);
			TreeNode currentNode = leftFlattenTree;
			while (currentNode.right != null) {
				currentNode = currentNode.right;
			}
			currentNode.right = rightFlattenTree;
			a.left = null;
			a.right = leftFlattenTree;
			return a;
		}
		a.right = flatten(a.right);
		return a;
	}

	/**
	 * 
	 * 
	 * A message containing letters from A-Z is being encoded to numbers using
	 * the following mapping:
	 * 
	 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing
	 * digits, determine the total number of ways to decode it.
	 * 
	 * Example :
	 * 
	 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L"
	 * (12).
	 * 
	 * The number of ways decoding "12" is 2.
	 * 
	 * @param a
	 * @return
	 */
	public static int numDecodings(String a) {
		Map<String, String> encodeMap = new HashMap<String, String>();

		for (int i = 1, j = 65; i <= 26; i++, j++) {
			encodeMap.put(String.valueOf(i), String.valueOf((char) j));

		}

		int[] resultArray = new int[a.length()];
		if (a.charAt(0) < '1' || a.charAt(0) > '9') {
			resultArray[0] = 0;
		} else {
			resultArray[0] = 1;
		}

		for (int i = 1; i < a.length(); i++) {
			String str = a.substring(i - 1, i + 1);
			int val = encodeMap.get(str) != null ? 1 : 0;
			if (i - 2 >= 0) {
				val = resultArray[i - 2] * val;
			}

			int val2 = encodeMap.get(String.valueOf(a.charAt(i))) != null ? 1
					: 0;

			resultArray[i] = resultArray[i - 1] * val2 + val;
		}

		return resultArray[a.length() - 1];
	}

	/**
	 * ADJACENT - DP
	 * 
	 * Given a 2 * N Grid of numbers, choose numbers such that the sum of the
	 * numbers is maximum and no two chosen numbers are adjacent horizontally,
	 * vertically or diagonally, and return it.
	 * 
	 * Example:
	 * 
	 * Grid: 1 2 3 4 2 3 4 5 so we will choose 3 and 5 so sum will be 3 + 5 = 8
	 * 
	 * 
	 * Note that you can choose more than 2 numbers
	 * 
	 * @param a
	 * @return
	 */
	public static int adjacent(ArrayList<ArrayList<Integer>> a) {
		if (a == null || a.size() != 2) {
			return 0;
		}
		if (a.get(0).size() != a.get(1).size()) {
			return 0;
		}
		if (a.get(0).size() == 0) {
			return 0;
		}

		int[] sum = new int[a.get(0).size()];
		int[] maxSum = new int[a.get(0).size()];
		// prefill the max sum for first two columns
		sum[0] = getMax(a.get(0).get(0), a.get(1).get(0));
		maxSum[0] = sum[0];
		if (a.get(0).size() == 1) {
			return sum[0];
		}

		sum[1] = getMax(sum[0], a.get(0).get(1), a.get(1).get(1));
		maxSum[1] = getMax(sum[1], sum[0]);
		/*
		 * if(a.get(0).size() == 2){ return maxSum[1]; }
		 */

		// fill the Sum and maxSum from third column to last column
		for (int i = 2; i < a.get(0).size(); i++) {
			int addComponent = getMax(a.get(0).get(i), a.get(1).get(i));
			//sum[i] = getMax(sum[i - 2], maxSum[i - 2]) + addComponent;
			sum[i] = maxSum[i - 2] + addComponent;
			maxSum[i] = getMax(sum[i - 1], sum[i]);
		}

		return maxSum[a.get(0).size() - 1];
	}

	private static int getMax(int a, int b) {
		return a > b ? a : b;
	}

	private static int getMax(int a, int b, int c) {
		if (a > b) {
			return getMax(a, c);
		} else {
			return getMax(b, c);
		}
	}

	public static int canJump(ArrayList<Integer> a) {
		if (a == null || a.size() == 0) {
			return 1;
		}
		int[] jumpPower = new int[a.size()];
		jumpPower[0] = a.get(0);
		for (int i = 1; i < a.size(); i++) {
			if (jumpPower[i - 1] == 0) {
				return 0;
			}
			jumpPower[i] = getMax(jumpPower[i - 1] - 1, a.get(i));
		}
		return 1;
	}

	/**
	 * coinchange2 - DP
	 * 
	 * You are given a set of coins S. In how many ways can you make sum N
	 * assuming you have infinite amount of each coin in the set.
	 * 
	 * Note : Coins in set S will be unique. Expected space complexity of this
	 * problem is O(N).
	 * 
	 * Example :
	 * 
	 * Input : S = [1, 2, 3] N = 4
	 * 
	 * Return : 4
	 * 
	 * Explanation : The 4 possible ways are {1, 1, 1, 1} {1, 1, 2} {2, 2} {1,
	 * 3} Note that the answer can overflow. So, give us the answer % 1000007
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int coinchange2(ArrayList<Integer> a, int b) {
		if (a == null || a.size() == 0) {
			return 0;
		}
		if (b == 0) {
			return 1;
		}
		return getChangeCount(a, a.size(), b);
	}

	private static int getChangeCount(ArrayList<Integer> a, int m, int n) {
		int i, j, x, y;

		// We need n+1 rows as the table is consturcted in bottom up manner
		// using
		// the base case 0 value case (n = 0)
		int[][] table = new int[n + 1][m];

		// Fill the enteries for 0 value case (n = 0)
		for (i = 0; i < m; i++)
			table[0][i] = 1;

		// Fill rest of the table enteries in bottom up manner
		for (i = 1; i < n + 1; i++) {
			for (j = 0; j < m; j++) {
				// Count of solutions including S[j]
				x = (i - a.get(j) >= 0) ? table[i - a.get(j)][j] : 0;

				// Count of solutions excluding S[j]
				y = (j >= 1) ? table[i][j - 1] : 0;

				// total count
				table[i][j] = (x + y) % 1000007;
			}
		}
		return table[n][m - 1];
	}

	/**
	 * LIS - DP
	 * 
	 * Find the longest increasing subsequence of a given sequence / array.
	 * 
	 * In other words, find a subsequence of array in which the subsequence’s
	 * elements are in strictly increasing order, and in which the subsequence
	 * is as long as possible. This subsequence is not necessarily contiguous,
	 * or unique. In this case, we only care about the length of the longest
	 * increasing subsequence.
	 * 
	 * Example :
	 * 
	 * Input : [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15] Output : 6
	 * The sequence : [0, 2, 6, 9, 13, 15] or [0, 4, 6, 9, 11, 15] or [0, 4, 6,
	 * 9, 13, 15]
	 * 
	 * @param a
	 * @return
	 */
	public static int lis(final List<Integer> a) {
		if (a == null || a.size() == 0) {
			return 0;
		}
		if (a.size() == 1) {
			return 1;
		}
		int max = 1;
		int[] lisVal = new int[a.size()];
		lisVal[0] = 1;
		for (int i = 1; i < a.size(); i++) {
			lisVal[i] = 1;
			for (int j = 0; j < i; j++) {
				if (a.get(j) < a.get(i) && lisVal[j] + 1 > lisVal[i]) {
					lisVal[i] = lisVal[j] + 1;
				}
			}
			if (lisVal[i] > max) {
				max = lisVal[i];
			}
		}
		return max;
	}

	/**
	 * PATHS2 - DP
	 * 
	 * Given a grid of size m * n, lets assume you are starting at (1,1) and
	 * your goal is to reach (m,n). At any instance, if you are on (x,y), you
	 * can either go to (x, y + 1) or (x + 1, y).
	 * 
	 * Now consider if some obstacles are added to the grids. How many unique
	 * paths would there be? An obstacle and empty space is marked as 1 and 0
	 * respectively in the grid.
	 * 
	 * Example : There is one obstacle in the middle of a 3x3 grid as
	 * illustrated below.
	 * 
	 * [ [0,0,0], [0,1,0], [0,0,0] ] The total number of unique paths is 2.
	 * 
	 * Note: m and n will be at most 100.
	 * 
	 * @param a
	 * @return
	 */
	public static int uniquePathsWithObstacles(ArrayList<ArrayList<Integer>> a) {
		if (a == null || a.size() == 0 || a.get(0).size() == 0) {
			return 0;
		}
		int[][] pathNums = new int[a.size()][a.get(0).size()];
		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < a.get(0).size(); j++) {
				if (i == 0 && j == 0) {
					if (isMovable(a, i, j)) {
						pathNums[i][j] = 1;
					} else {
						return 0;
					}
				} else {
					if (isMovable(a, i, j)) {
						pathNums[i][j] = getValue(pathNums, i - 1, j)
								+ getValue(pathNums, i, j - 1);
					}
				}
			}
		}
		return pathNums[a.size() - 1][a.get(0).size() - 1];
	}

	private static int getValue(int[][] pathNums, int i, int j) {
		if (i >= 0 && j >= 0) {
			return pathNums[i][j];
		}
		return 0;
	}

	private static boolean isMovable(ArrayList<ArrayList<Integer>> a, int i2,
			int j2) {
		int numberOfRows = a.size();
		int numberOfColumns = a.get(0).size();
		if (i2 >= 0 && i2 < numberOfRows && j2 >= 0 && j2 < numberOfColumns
				&& a.get(i2).get(j2) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * MAXCOIN-DP
	 * 
	 * There are N coins (Assume N is even) in a line. Two players take turns to
	 * take a coin from one of the ends of the line until there are no more
	 * coins left. The player with the larger amount of money wins. Assume that
	 * you go first.
	 * 
	 * Write a program which computes the maximum amount of money you can win.
	 * 
	 * Example:
	 * 
	 * suppose that there are 4 coins which have value 1 2 3 4 now you are first
	 * so you pick 4 then in next term next person picks 3 then you pick 2 and
	 * then next person picks 1 so total of your money is 4 + 2 = 6
	 * next/opposite person will get 1 + 3 = 4 so maximum amount of value you
	 * can get is 6
	 * 
	 * http://articles.leetcode.com/2011/02/coins-in-line.html
	 * 
	 * @param a
	 * @return
	 */
	public static int maxcoin(ArrayList<Integer> a) {
		if (a == null || a.size() == 0) {
			return 0;
		}
		int MAX_N = a.size();
		int[][] P = new int[MAX_N][MAX_N];
		int p = 0, q = 0, r = 0;
		for (int i = 0; i < MAX_N; i++) {
			for (int m = 0, n = i; n < MAX_N; m++, n++) {
				// assert(m < N); assert(n < N);
				p = ((m + 2 <= MAX_N - 1) ? P[m + 2][n] : 0);
				q = ((m + 1 <= MAX_N - 1 && n - 1 >= 0) ? P[m + 1][n - 1] : 0);
				r = ((n - 2 >= 0) ? P[m][n - 2] : 0);
				P[m][n] = getMax(a.get(m) + getMin(p, q),
						a.get(n) + getMin(q, r));
			}
		}
		// printMoves(P, A, N);
		return P[0][MAX_N - 1];
	}

	/**
	 * Return the size of max subArray square matrics with values 1.
	 * 
	 * @param a
	 * @return
	 */
	public static int maximalSquare(ArrayList<ArrayList<Integer>> a) {
		if (a == null || a.size() == 0 || a.get(0).size() == 0) {
			return 0;
		}
		int i, j;
		int NUM_ROWS = a.size();
		int NUM_COLS = a.get(0).size();
		int[][] S = new int[NUM_ROWS][NUM_COLS];

		/* Set first column of S[][] */
		for (i = 0; i < NUM_ROWS; i++)
			S[i][0] = a.get(i).get(0);

		/* Set first row of S[][] */
		for (j = 0; j < NUM_COLS; j++)
			S[0][j] = a.get(0).get(j);

		/* Construct other entries of S[][] */
		int max_val = 0;
		for (i = 1; i < NUM_ROWS; i++) {
			for (j = 1; j < NUM_COLS; j++) {
				if (a.get(i).get(j) == 1) {
					S[i][j] = getMin(S[i][j - 1], S[i - 1][j], S[i - 1][j - 1]) + 1;
					if (S[i][j] > max_val) {
						max_val = S[i][j];
					}
					System.out.print(" " + S[i][j]);
				} else {
					S[i][j] = 0;
					System.out.print(" " + S[i][j]);
				}
			}
			System.out.println();
		}

		return max_val;
	}
	
	
	
	
	//DP - Max Rectangle in Binary Matrix
//	Given a 2D binary matrix filled with 0’s and 1’s, find the largest rectangle containing all ones and return its area.
//
//			Bonus if you can solve it in O(n^2) or less.
//
//			Example :
//
//			A : [  1 1 1
//			       0 1 1
//			       1 0 0 
//			    ]
//
//			Output : 4 
//
//			As the max area rectangle is created by the 2x2 rectangle created by (0,1), (0,2), (1,1) and (1,2)
	
	//For solution refer : http://www.geeksforgeeks.org/maximum-size-rectangle-binary-sub-matrix-1s
	
	public static int maximalRectangle(ArrayList<ArrayList<Integer>> grid) {
		// Calculate area for first row and initialize it as
	    // result
	    int result = largestRectangleArea(grid.get(0).toArray());
	 
	    // iterate over row to find maximum rectangular area
	    // considering each row as histogram
	    for (int i = 1; i < grid.size(); i++)
	    {
	 
	        for (int j = 0; j < grid.get(0).size(); j++)
	 
	            // if A[i][j] is 1 then add A[i -1][j]
	            if (grid.get(i).get(j) == 1){
	            	int newVal = grid.get(i).get(j) + grid.get(i-1).get(j);
	            	grid.get(i).set(j,  newVal ) ;
	            } 
	 
	 
	        // Update result if area with current row (as last row)
	        // of rectangle) is more
	        result = getMax(result, largestRectangleArea(grid.get(i).toArray()));
	    }
	 
	    return result;
	}
	
	private static int largestRectangleArea(Object[] array) {
		int[] intArr = new int[array.length];
		int index=0;
		for (Object i : array) {
			if(i instanceof Integer){
				intArr[index++] = (Integer)i;
			}
		}
		return largestRectangleArea(intArr);
	}


	// Finds the maximum area under the histogram represented
	// by histogram.  See below article for details.
	// http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
	public static int largestRectangleArea(int row[])
	{
		int C = row.length;
	    // Create an empty stack. The stack holds indexes of
	    // hist[] array/ The bars stored in stack are always
	    // in increasing order of their heights.
	    Stack<Integer> result = new Stack<Integer>();
	 
	    int top_val;     // Top of stack
	 
	    int max_area = 0; // Initialize max area in current
	                      // row (or histogram)
	 
	    int area = 0;    // Initialize area with current top
	 
	    // Run through all bars of given histogram (or row)
	    int i = 0;
	    while (i < C)
	    {
	        // If this bar is higher than the bar on top stack,
	        // push it to stack
	        if (result.empty() || row[result.peek()] <= row[i])
	            result.push(i++);
	 
	        else
	        {
	            // If this bar is lower than top of stack, then
	            // calculate area of rectangle with stack top as
	            // the smallest (or minimum height) bar. 'i' is
	            // 'right index' for the top and element before
	            // top in stack is 'left index'
	            
	        	int popedIndex = result.pop();
	        	top_val = row[popedIndex];
	            area = top_val * i;
	 
	            if (!result.empty()){
	            	area = top_val * (i - result.peek() - 1 );
	            }
	            max_area = getMax(area, max_area);
	        }
	    }
	 
	    // Now pop the remaining bars from stack and calculate area
	    // with every popped bar as the smallest bar
	    while (!result.empty())
	    {
	    	int poppedIndex = result.pop();
	        top_val = row[poppedIndex];
	        area = top_val * i;
	        if (!result.empty())
	            area = top_val * (i - result.peek() - 1 );
	 
	        max_area = getMax(area, max_area);
	    }
	    return max_area;
	}


	
	
	

	/**
	 * SameTree - tree
	 * 
	 * Given two binary trees, write a function to check if they are equal or
	 * not.
	 * 
	 * Two binary trees are considered equal if they are structurally identical
	 * and the nodes have the same value.
	 * 
	 * Return 0 / 1 ( 0 for false, 1 for true ) for this problem
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int isSameTree(TreeNode a, TreeNode b) {
		if (a == null && b == null) {
			return 1;
		}
		if (a == null) {
			return 0;
		}
		if (b == null) {
			return 0;
		}
		if (a.val == b.val && isSameTree(a.left, b.left) == 1
				&& isSameTree(a.right, b.right) == 1) {
			return 1;
		}
		return 0;
	}

	/**
	 * PREORDER - Tree
	 * 
	 * Given a binary tree, return the preorder traversal of its nodes’ values.
	 * Using recursion is not allowed.
	 * 
	 * @param a
	 * @return
	 */
	public static ArrayList<Integer> preorderTraversal(TreeNode a) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		if (a == null) {
			return returnList;
		}
		Stack<TreeNode> stk = new Stack<>();
		TreeNode current = a;
		while (true) {
			while (current != null) {
				returnList.add(current.val);
				stk.add(current);
				current = current.left;
			}
			if (stk.isEmpty()) {
				break;
			}
			TreeNode node = stk.pop();
			current = node.right;
		}

		return returnList;
	}

	/**
	 * POSTORDER - Tree
	 * 
	 * Given a binary tree, return the postorder traversal of its nodes’ values.
	 * Recursion not allowed
	 * 
	 * @param a
	 * @return
	 */
	public ArrayList<Integer> postorderTraversal(TreeNode a) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		if (a == null) {
			return returnList;
		}
		Stack<PostOrderNode> stk = new Stack<>();
		TreeNode current = a;
		while (true) {
			while (current != null) {
				// returnList.add(current.val);
				stk.add(new PostOrderNode(current));
				current = current.left;
			}
			if (stk.isEmpty()) {
				break;
			}
			PostOrderNode postOrderNode = stk.pop();
			if (postOrderNode.isRightTreeTraversed) {
				returnList.add(postOrderNode.treeNode.val);
				current = null;
			} else {
				postOrderNode.isRightTreeTraversed = true;
				stk.push(postOrderNode);
				current = postOrderNode.treeNode.right;
			}
		}

		return returnList;
	}

	/**
	 * ARRAYBST - Tree
	 * 
	 * Given an array where elements are sorted in ascending order, convert it
	 * to a height balanced BST.
	 * 
	 * Balanced tree : a height-balanced binary tree is def
	 * 
	 * @param a
	 * @return
	 */
	public static TreeNode sortedArrayToBST(final List<Integer> a) {
		if (a == null || a.size() == 0) {
			return null;
		}
		if (a.size() == 1) {
			return new TreeNode(a.get(0));
		}
		return sortedArrayToBST(a, 0, a.size() - 1);
	}

	private static TreeNode sortedArrayToBST(List<Integer> a, int i, int j) {
		if (i > j) {
			return null;
		}
		if (i == j) {
			return new TreeNode(a.get(i));
		}
		if (i + 1 == j) {
			TreeNode root = new TreeNode(a.get(j));
			root.left = new TreeNode(a.get(i));
			return root;
		}
		int rootIndex = (i + j) / 2;
		TreeNode leftSubTree = sortedArrayToBST(a, i, rootIndex - 1);
		TreeNode rightSubTree = sortedArrayToBST(a, rootIndex + 1, j);
		TreeNode root = new TreeNode(a.get(rootIndex));
		root.left = leftSubTree;
		root.right = rightSubTree;
		return root;
	}

	/**
	 * PATH - TREE
	 * 
	 * Given a binary tree and a sum, determine if the tree has a root-to-leaf
	 * path such that adding up all the values along the path equals the given
	 * sum.
	 * 
	 * Example :
	 * 
	 * Given the below binary tree and sum = 22,
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int hasPathSum(TreeNode a, int b) {
		if (a == null) {
			return 0;
		}
		if (a.left == null && a.right == null && b - a.val == 0) {
			return 1;
		}
		b = b - a.val;
		int hasLeftTreePathSum = hasPathSum(a.left, b);
		int hasRightTreePathSum = hasPathSum(a.right, b);
		if (hasLeftTreePathSum == 1 || hasRightTreePathSum == 1) {
			return 1;
		}
		return 0;
	}

	public static int minDepth(TreeNode a) {
		if (a == null) {
			return 0;
		}
		if (a.left == null && a.right == null) {
			return 1;
		}
		if (a.left == null) {
			return minDepth(a.right) + 1;
		}
		if (a.right == null) {
			return minDepth(a.left) + 1;
		}
		int leftMinDepth = minDepth(a.left);
		int rightMinDepth = minDepth(a.right);
		if (leftMinDepth < rightMinDepth) {
			return leftMinDepth + 1;
		}
		return rightMinDepth + 1;
	}

	/**
	 * PATH2 Tree
	 * 
	 * Given a binary tree and a sum, find all root-to-leaf paths where each
	 * path’s sum equals the given sum.
	 * 
	 * For example: Given the below binary tree and sum = 22,
	 * 
	 * 5 / \ 4 8 / / \ 11 13 4 / \ / \ 7 2 5 1 return
	 * 
	 * [ [5,4,11,2], [5,8,4,5] ]
	 * 
	 * @param root
	 * @param sum
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
		if (root == null) {
			return null;
		}
		ArrayList<ArrayList<Integer>> returnList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> currentList = new ArrayList<Integer>();
		return pathSum(root, sum, returnList, currentList);
	}

	private static ArrayList<ArrayList<Integer>> pathSum(TreeNode root,
			int sum, ArrayList<ArrayList<Integer>> returnList,
			ArrayList<Integer> currentList) {
		if (root == null) {
			return returnList;
		}
		currentList.add(root.val);
		if (root.left == null && root.right == null && root.val == sum) {
			ArrayList<Integer> cpList = new ArrayList<Integer>(currentList);
			returnList.add(cpList);
			currentList.remove(currentList.size() - 1);
		} else {
			pathSum(root.left, sum - root.val, returnList, currentList);
			pathSum(root.right, sum - root.val, returnList, currentList);
			currentList.remove(currentList.size() - 1);
		}
		return returnList;
	}

	/**
	 * BINTREE2 - TREE
	 * 
	 * Given preorder and inorder traversal of a tree, construct the binary
	 * tree.
	 * 
	 * Note: You may assume that duplicates do not exist in the tree. Example :
	 * 
	 * Input : Preorder : [1, 2, 3] Inorder : [2, 1, 3]
	 * 
	 * Return : 1 / \ 2 3
	 * 
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	
	public static TreeNode buildTree1(ArrayList<Integer> preorder,
			ArrayList<Integer> inorder) {
		if (preorder == null) {
			return null;
		}
		return buildPreInOrderTree(preorder, 0, preorder.size() - 1, inorder,
				0, inorder.size() - 1);
	}

	private static TreeNode buildPreInOrderTree(ArrayList<Integer> preorder,
			int preStartIndex, int preEndIndex, ArrayList<Integer> inorder,
			int inStartIndex, int inEndIndex) {
		if (preEndIndex < preStartIndex) {
			return null;
		}
		TreeNode root = new TreeNode(preorder.get(preStartIndex));
		int inOrderRootIndex = inStartIndex;
		for (int i = inStartIndex; i <= inEndIndex; i++) {
			if (inorder.get(i) == root.val) {
				inOrderRootIndex = i;
			}
		}
		root.left = buildPreInOrderTree(preorder, preStartIndex + 1,
				preStartIndex + inOrderRootIndex - inStartIndex, inorder,
				inStartIndex, inOrderRootIndex - 1);
		root.right = buildPreInOrderTree(preorder, preStartIndex
				+ inOrderRootIndex - inStartIndex + 1, preEndIndex, inorder,
				inOrderRootIndex + 1, inEndIndex);
		return root;
	}
	 

	public static TreeNode buildTree(ArrayList<Integer> inorder,
			ArrayList<Integer> postorder) {
		if (inorder == null) {
			return null;
		}
		return buildPostInOrderTree(postorder, 0, postorder.size() - 1,
				inorder, 0, inorder.size() - 1);
	}

	private static TreeNode buildPostInOrderTree(ArrayList<Integer> postorder,
			int postStartIndex, int postEndIndex, ArrayList<Integer> inorder,
			int inStartIndex, int inEndIndex) {
		if (postEndIndex < postStartIndex) {
			return null;
		}
		TreeNode root = new TreeNode(postorder.get(postEndIndex));
		int inOrderRootIndex = inStartIndex;
		for (int i = inStartIndex; i <= inEndIndex; i++) {
			if (inorder.get(i) == root.val) {
				inOrderRootIndex = i;
			}
		}
		root.left = buildPostInOrderTree(postorder, postStartIndex,
				postStartIndex + inOrderRootIndex - inStartIndex - 1, inorder,
				inStartIndex, inOrderRootIndex - 1);
		root.right = buildPostInOrderTree(postorder, postStartIndex
				+ inOrderRootIndex - inStartIndex, postEndIndex - 1, inorder,
				inOrderRootIndex + 1, inEndIndex);
		return root;
	}

	/**
	 * STAIRS - DP
	 * 
	 * You are climbing a stair case. It takes n steps to reach to the top.
	 * 
	 * Each time you can either climb 1 or 2 steps. In how many distinct ways
	 * can you climb to the top?
	 * 
	 * Example :
	 * 
	 * Input : 3 Return : 3
	 * 
	 * Steps : [1 1 1], [1 2], [2 1]
	 * 
	 * @param a
	 * @return
	 */
	public static int climbStairs(int a) {
		int[] solArray = new int[a + 1];
		if (a < 0) {
			return 0;
		}
		if (a == 1 || a == 0) {
			return 1;
		}
		if (a == 2) {
			return 2;
		}
		solArray[0] = 1;
		solArray[1] = 1;
		solArray[2] = 2;
		for (int i = 3; i <= a; i++) {
			solArray[i] = solArray[i - 1] + solArray[i - 2];
		}
		return solArray[a];
	}

	/**
	 * STOCKS1 - DP
	 * 
	 * Say you have an array for which the ith element is the price of a given
	 * stock on day i.
	 * 
	 * If you were only permitted to complete at most one transaction (ie, buy
	 * one and sell one share of the stock), design an algorithm to find the
	 * maximum profit.
	 * 
	 * Example :
	 * 
	 * Input : [1 2] Return : 1
	 * 
	 * @param a
	 * @return
	 */
	public static int maxProfit(final List<Integer> a) {
		if (a == null || a.size() <= 1) {
			return 0;
		}
		if (a.size() == 1 && a.get(1) < a.get(0)) {
			return 0;
		}
		int minVal = a.get(0);
		int maxProfit = 0;
		for (Integer price : a) {
			int currentProfit = price - minVal;
			if (currentProfit > maxProfit) {
				maxProfit = currentProfit;
			}
			if (price < minVal) {
				minVal = price;
			}
		}
		return maxProfit;
	}

	/**
	 * PASCAL2 - Arrays
	 * 
	 * 
	 * Given an index k, return the kth row of the Pascal’s triangle.
	 * 
	 * Pascal’s triangle : To generate A[C] in row R, sum up A’[C] and A’[C-1]
	 * from previous row R - 1.
	 * 
	 * Example:
	 * 
	 * Input : k = 3
	 * 
	 * Return : [1,3,3,1] NOTE : k is 0 based. k = 0, corresponds to the row
	 * [1]. Note:Could you optimize your algorithm to use only O(k) extra space?
	 * 
	 * @param a
	 * @return
	 */
	public static ArrayList<Integer> getRow(int a) {
		if (a < 0) {
			return null;
		}
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		tempList.add(1);
		returnList.add(1);
		for (int i = 0; i <= a; i++) {
			for (int j = 1; j <= i; j++) {
				if (j < i) {
					returnList.set(j, tempList.get(j - 1) + tempList.get(j));
				} else {
					returnList.add(1);
				}
			}
			tempList = new ArrayList<Integer>(returnList);
		}
		return returnList;
	}

	/**
	 * REMDUPLNK - linked list
	 * 
	 * Given a sorted linked list, delete all duplicates such that each element
	 * appear only once.
	 * 
	 * For example, Given 1->1->2, return 1->2. Given 1->1->2->3->3, return
	 * 1->2->3.
	 * 
	 * @param a
	 * @return
	 */
	public static ListNode deleteDuplicates(ListNode a) {
		if (a == null) {
			return null;
		}
		ListNode currentNode = a;
		while (currentNode != null && currentNode.next != null) {
			if (currentNode.val == currentNode.next.val) {
				currentNode.next = currentNode.next.next;
			} else {
				currentNode = currentNode.next;
			}
		}
		return a;
	}

	/**
	 * delete n th node from end
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static ListNode removeNthFromEnd(ListNode a, int b) {
		if (a == null || b <= 0) {
			return a;
		}
		int i = 0;
		ListNode fastNode = a;
		while (fastNode.next != null && i < b) {
			i++;
			fastNode = fastNode.next;
		}
		if (fastNode.next == null) {
			return a.next;
		}

		ListNode currentNode = a;
		while (fastNode.next != null) {
			fastNode = fastNode.next;
			currentNode = currentNode.next;
		}
		currentNode.next = currentNode.next.next;
		return a;
	}

	/**
	 * ROTATELIST - Linked list
	 * 
	 * Given a list, rotate the list to the right by k places, where k is
	 * non-negative.
	 * 
	 * For example:
	 * 
	 * Given 1->2->3->4->5->NULL and k = 2, return 4->5->1->2->3->NULL.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static ListNode rotateRight(ListNode a, int b) {
		if (a == null || b < 0) {
			return null;
		}
		if (a.next == null) {
			return a;
		}
		ListNode currentNode = a;
		while (b != 0) {
			b--;
			if (currentNode.next != null) {
				currentNode = currentNode.next;
			} else {
				currentNode = a;
			}
		}
		if (currentNode == a) {
			return a;
		}
		ListNode slowNode = a;
		while (currentNode.next != null) {
			slowNode = slowNode.next;
			currentNode = currentNode.next;
		}
		ListNode newStart = slowNode.next;
		slowNode.next = null;
		currentNode.next = a;
		return newStart;
	}

	/**
	 * chk if list is palindrom, in order of n.
	 * 
	 * @param A
	 * @return
	 */
	// Recursive
	/*
	 * public static int lPalin(ListNode A) { if(A==null || A.next == null ||
	 * (A.next.next == null && A.val == A.next.val)){ return 1; } ListNode
	 * lastNode = chkPalin(A.next, A.next.next); if(lastNode == null){ return 0;
	 * } if(A.val == lastNode.val){ return 1; } return 0; }
	 * 
	 * 
	 * 
	 * private static ListNode chkPalin(ListNode slow, ListNode fast) { if(fast
	 * == null){ return slow; } if(fast.next == null){ return slow.next; }
	 * ListNode retNode = chkPalin(slow.next, fast.next.next); if(retNode ==
	 * null){ return null; } if(slow.val == retNode.val){ return retNode.next; }
	 * return null; }
	 */

	public static int lPalin(ListNode A) {
		if (A == null || A.next == null
				|| (A.next.next == null && A.val == A.next.val)) {
			return 1;
		}

		ListNode fast = A.next;
		ListNode slow = A;
		ListNode prev = null;
		ListNode temp;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			temp = slow.next;
			slow.next = prev;
			prev = slow;
			slow = temp;
		}
		if (fast == null) {

		} else if (fast.next == null) {
			if (slow.val != slow.next.val) {
				return 0;
			}
			slow = slow.next;
		}
		ListNode current = slow.next;
		while (prev != null) {
			if (prev.val != current.val) {
				return 0;
			}
			prev = prev.next;
			current = current.next;
		}
		return 1;

	}

	public static String countAndSay(int a) {
		if (a <= 0) {
			return "";
		}
		if (a == 1) {
			return "1";
		}
		String currentStr = "1";
		StringBuilder strBld = new StringBuilder();
		for (int i = 2; i <= a; i++) {
			strBld.setLength(0);

			int currCharCount = 1;
			char currChar = currentStr.charAt(0);
			for (int j = 1; j < currentStr.length(); j++) {
				if (currChar == currentStr.charAt(j)) {
					currCharCount++;
				} else {
					strBld.append(currCharCount).append(currChar);
					currChar = currentStr.charAt(j);
					currCharCount = 1;
				}
			}
			strBld.append(currCharCount).append(currChar);
			currentStr = strBld.toString();
		}
		return currentStr;
	}

	/**
	 * BULBS - GREEDY
	 * 
	 * N light bulbs are connected by a wire. Each bulb has a switch associated
	 * with it, however due to faulty wiring, a switch also changes the state of
	 * all the bulbs to the right of current bulb. Given an initial state of all
	 * bulbs, find the minimum ber of switches you have to press to turn on
	 * all the bulbs. You can press the same switch multiple times.
	 * 
	 * Note : 0 represents the bulb is off and 1 represents the bulb is on.
	 * 
	 * Example:
	 * 
	 * Input : [0 1 0 1] Return : 4
	 * 
	 * Explanation : press switch 0 : [1 0 1 0] press switch 1 : [1 1 0 1] press
	 * switch 2 : [1 1 1 0] press switch 3 : [1 1 1 1]
	 * 
	 * @param a
	 * @return
	 */
	public static int bulbs(List<Integer> a) {
		if (a == null || a.size() == 0) {
			return 0;
		}

		int buttonPressCount = 0;
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) == 0) {
				if (buttonPressCount % 2 == 0) {
					buttonPressCount++;
				}
			} else if (buttonPressCount % 2 != 0) {
				buttonPressCount++;
			}
		}
		return buttonPressCount;
	}

	public static int mice(ArrayList<Integer> a, ArrayList<Integer> b) {
		if (a == null || b == null || a.size() == 0 || b.size() == 0
				|| a.size() != b.size()) {
			return 0;
		}

		Collections.sort(a);
		Collections.sort(b);

		int maxDiff = Integer.MIN_VALUE;
		for (int i = 0; i < a.size(); i++) {
			int diff = Math.abs(a.get(i) - b.get(i));
			if (diff > maxDiff) {
				maxDiff = diff;
			}
		}
		return maxDiff;
	}

	/**
	 * 
	 * 
	 * Given an array of integers, return the highest product possible by
	 * multiplying 3 numbers from the array
	 * 
	 * Input:
	 * 
	 * array of integers e.g {1, 2, 3} NOTE: Solution will fit in a 32-bit
	 * signed integer Example:
	 * 
	 * [0, -1, 3, 100, 70, 50]
	 * 
	 * => 70*50*100 = 350000
	 * 
	 * @param a
	 * @return
	 */
	public static int maxp3(List<Integer> a) {
		if (a == null || a.size() < 3) {
			return 0;
		}
		Collections.sort(a);

		int maxIndex = a.size() - 1;
		if (a.get(maxIndex) < 0) {
			return a.get(maxIndex) * a.get(maxIndex - 1) * a.get(maxIndex - 2);
		}
		if (a.get(1) < 0) {
			if (a.get(maxIndex) * a.get(maxIndex - 1) * a.get(maxIndex - 2) < a
					.get(0) * a.get(1) * a.get(maxIndex)) {
				return a.get(0) * a.get(1) * a.get(maxIndex);
			}

		}
		return a.get(maxIndex) * a.get(maxIndex - 1) * a.get(maxIndex - 2);
	}

	/**
	 * 
	 * Majority - Greedy
	 * 
	 * Given an array of size n, find the majority element. The majority element
	 * is the element that appears more than floor(n/2) times.
	 * 
	 * You may assume that the array is non-empty and the majority element
	 * always exist in the array.
	 * 
	 * Example :
	 * 
	 * Input : [2, 1, 2] Return : 2 which occurs 2 times which is greater than
	 * 3/2.
	 * 
	 * @param a
	 * @return
	 */
	public static int majorityElement(final List<Integer> a) {
		if (a == null || a.size() == 0) {
			return 0;
		}
		int count = 0;
		int majItem = a.get(0);
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).equals(majItem)) {
				count++;
			} else {
				count--;
			}
			if (count == 0) {
				majItem = a.get(i);
				count = 1;
			}
		}

		count = 0;
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).equals(majItem)) {
				count++;
			}
		}
		if (count > a.size() / 2) {
			return majItem;
		}
		return 0;
	}

	/*
	 * public static int candy(List<Integer> ratings) { if(ratings == null ||
	 * ratings.size() == 0){ return 0; } List<Integer> candyCount = new
	 * ArrayList<Integer>(); candyCount.add(1); for (int i = 1; i <
	 * ratings.size(); i++) { if(ratings.get(i)>ratings.get(i-1)){
	 * candyCount.add(candyCount.get(i-1) + 1); } else
	 * if(ratings.get(i)==ratings.get(i-1)){
	 * candyCount.add(candyCount.get(i-1)); } else{ candyCount.add(1); } } for
	 * (int i = ratings.size()-2; i >=0; i--) {
	 * if(ratings.get(i)>ratings.get(i+1)){
	 * if(candyCount.get(i)<=candyCount.get(i+1)){ candyCount.set(i,
	 * candyCount.get(i+1)+1); } } } int count = 0; for (int i = 0; i <
	 * candyCount.size(); i++) { count = count + candyCount.get(i);
	 * System.out.println("rating: " + ratings.get(i) + ", candy: " +
	 * candyCount.get(i)); } return count; }
	 */

	public static int candy(List<Integer> ratings) {
		int[] candies = new int[ratings.size()];
		candies[0] = 1;
		// from left to right
		for (int i = 1; i < ratings.size(); i++) {
			if (ratings.get(i) > ratings.get(i - 1)){
				candies[i] = candies[i - 1] + 1;
			}
			else{
				candies[i] = 1;
			}
		}

		int result = candies[ratings.size() - 1];
		// from right to left
		for (int i = ratings.size() - 2; i >= 0; i--) {
			int curr = 1;
			if (ratings.get(i) > ratings.get(i + 1)){
				curr = candies[i + 1] + 1;
			}
			// consider same rating neighbors with different candies
			candies[i] = Math.max(curr, candies[i]);
			result += candies[i];
		}
		return result;
	}
	
	
	
	
	public static int minimumTotal(ArrayList<ArrayList<Integer>> a) {
		if(a == null || a.size() == 0 || ( a.size()==1 && a.get(0) == null) ){
			return 0;
		}
		if(a.size() == 1){
			return a.get(0).get(0);
		}
		
		int[][] minSumArr = new int[a.size()][a.size()];
		
		minSumArr[0][0] = a.get(0).get(0);
		int min = Integer.MAX_VALUE;
		
		for(int i=1; i<a.size(); i++){
			for(int j=0;j<=i; j++){
				if(j==0){
					minSumArr[i][j] = minSumArr[i-1][j] + a.get(i).get(j);
				}
				else if(i==j){
					minSumArr[i][j] = minSumArr[i-1][j-1] + a.get(i).get(j);
				}
				else{
					minSumArr[i][j] = getMin(minSumArr[i-1][j-1], minSumArr[i-1][j]) + a.get(i).get(j);
				}
				if( i==a.size()-1 && min > minSumArr[i][j]){
					min = minSumArr[i][j];
				}
			}
		}
		return min;
	}
	
	
	/**
	 * Insertion Sort List - LINKED LIST
			Sort a linked list using insertion sort.
			
			We have explained Insertion Sort at Slide 7 of Arrays
			
			Insertion Sort Wiki has some details on Insertion Sort as well.
			
			Example :
			
			Input : 1 -> 3 -> 2
			
			Return 1 -> 2 -> 3
	 * @param a
	 * @return
	 */
	
	public static ListNode insertionSortList(ListNode a) {
		if(a == null || a.next == null){
			return a;
		}
		ListNode currentNodeToInsert = a.next;
		ListNode prev = a;
		boolean delInserted = false;
		while(currentNodeToInsert != null){
			
			/*if(prev.val < currentNodeToInsert){
				currentNodeToInsert = currentNodeToInsert.next;
			}*/
			
			if(a.val > currentNodeToInsert.val){
				//remove currentNodeToInsert from its original position
				prev.next = prev.next.next;
				
				//insert as first node.
				currentNodeToInsert.next = a;
				a = currentNodeToInsert;
				currentNodeToInsert = prev.next;
				continue;
			}
			ListNode current = a.next;
			ListNode prevCurrent = a;
			while(current != currentNodeToInsert){
				if(current.val > currentNodeToInsert.val){
					//remove currentNodeToInsert from its original position
					prev.next = prev.next.next;
					
					//insert at this position.
					currentNodeToInsert.next = current;
					prevCurrent.next = currentNodeToInsert;
					delInserted = true;
					break;
				}
				else{
					prevCurrent = current;
					current = current.next;
				}
				
			}
			
			if(delInserted){
				delInserted = false;
			}
			else{
				prev = currentNodeToInsert;
				currentNodeToInsert = currentNodeToInsert.next;
			}
		}
		return a;
	}
	
	
	/**
	 * Merge Two Sorted Lists - Two Pointers 
		Given two sorted integer arrays A and B, merge B into A as one sorted array.
		
		 Note: You have to modify the array A to contain the merge of A and B. Do not output anything in your code.
		TIP: C users, please malloc the result into a new array and return the result. 
		If the number of elements initialized in A and B are m and n respectively, the resulting size of array A after your code is executed should be m + n
		
		Example :
		
		Input : 
		         A : [1 5 8]
		         B : [6 9]
		
		Modified A : [1 5 6 8 9]

	 * @param a
	 * @param b
	 */
	public static void merge(ArrayList<Integer> a, ArrayList<Integer> b) {
		
		if(b==null || b.size() == 0){
			return;
		}
		
		int currentInd = 0;
		int currentBArrInd = 0;
		//int i =0;
		for( ; currentBArrInd<b.size() && currentInd <a.size(); ){
			if(a.get(currentInd) > b.get(currentBArrInd)){
				a.add(currentInd++, b.get(currentBArrInd++));
			}
			else{
				currentInd++;
			}
		}
		if(currentBArrInd<b.size()){
			while(currentBArrInd < b.size()){
				a.add(b.get(currentBArrInd++));
			}
		}
	}
	
	
	
	/**
	 * Reverse the String - Strings	 
		Given an input string, reverse the string word by word.
		
		Example:
		
		Given s = "the sky is blue",
		
		return "blue is sky the".
		
		A sequence of non-space characters constitutes a word.
		Your reversed string should not contain leading or trailing spaces, even if it is present in the input string.
		If there are multiple spaces between words, reduce them to a single space in the reversed string.
	 * @param a
	 * @return
	 */
	public static String reverseWords(String a) {
		if(a==null || a.length() == 1){
			return a;
		}
		char[] charArray = a.toCharArray();
		charArray = reverseStrArry(charArray, 0, charArray.length-1);
		int startInd = 0;
		int endInd = 0;
		for(int i=0; i<charArray.length; i++){
			if(charArray[i] == ' '){
				endInd = i-1;
				char[] reverseStrArry = reverseStrArry(charArray, startInd, endInd);
				
				startInd = i+1;
			}
		}
		char[] reverseStrArry = reverseStrArry(charArray, startInd, charArray.length-1);
		charArray = removeMultipleSpace(reverseStrArry);
		return String.valueOf(charArray).trim();
	}
	

	private static char[] removeMultipleSpace(char[] reverseStrArry) {
		int currInd = 0;
		boolean isSpace = false;
		for (int i = 0; i < reverseStrArry.length; i++) {
			if(reverseStrArry[i] == ' '){
				if(isSpace){
					//Do nothing
				}
				else{
					reverseStrArry[currInd] = reverseStrArry[i];
					currInd++;
					isSpace = true;
				}
			}
			else{
				reverseStrArry[currInd] = reverseStrArry[i];
				currInd++;
				isSpace = false;
			}
		}
		for(;currInd < reverseStrArry.length; currInd++){
			reverseStrArry[currInd] = ' ';
		}
		return reverseStrArry;
	}


	

	private static char[] reverseStrArry(char[] charArray, int startInd, int endInd) {
		if(startInd < endInd){
			for (int i = startInd, j=0; i <= (startInd + endInd )/2; i++,j++) {
				char temp = charArray[i];
				charArray[i] = charArray[endInd-j];
				charArray[endInd-j] = temp;
			}
		}
		return charArray;
	}

	
	private static String reverseStr(String a) {
		if(a==null || a.length() == 1){
			return a;
		}
		char[] charArray = a.toCharArray();
		for (int i = 0; i <= charArray.length/2; i++) {
			char temp = charArray[i];
			charArray[i] = charArray[charArray.length-1-i];
			charArray[charArray.length-1-i] = temp;
		}
		String returnStr = String.valueOf(charArray, 0, charArray.length);
		return returnStr;
	}
	
	
	/**
	 * Min Sum Path in Matrix - DP
		Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
		
		 Note: You can only move either down or right at any point in time. 
		Example :
		
		Input : 
		
		    [  1 3 2
		       4 3 1
		       5 6 1
		    ]
		
		Output : 8
		     1 -> 3 -> 2 -> 1 -> 1
	 * 
	 * @param a
	 * @return
	 */
	public static int minPathSum(ArrayList<ArrayList<Integer>> a) {
		if(a == null || a.size() == 0 || a.get(0) == null || a.get(0).size() == 0){
			return 0;
		}
		int MAX_ROWS = a.size();
		int MAX_COLUMN = a.get(0).size();
		int[][] minPathMat = new int[MAX_ROWS][MAX_COLUMN];
		
		for(int i=0; i< MAX_ROWS; i++){
			for(int j=0; j< MAX_COLUMN; j++){
				if(i==0 && j==0){
					minPathMat[i][j] = a.get(i).get(j);
				}
				else if(i==0){
					minPathMat[i][j] = a.get(i).get(j) + minPathMat[i][j-1];
				}
				else if(j==0){
					minPathMat[i][j] = a.get(i).get(j) + minPathMat[i-1][j];
				}
				else{
					minPathMat[i][j] = a.get(i).get(j) + getMin(minPathMat[i-1][j], minPathMat[i][j-1]);
				}
			}
		}
		return minPathMat[MAX_ROWS-1][MAX_COLUMN-1];
	}
	
	
	/**
	 * Remove Element from Array - tow pointers
		Remove Element
		
		Given an array and a value, remove all the instances of that value in the array. 
		Also return the number of elements left in the array after the operation.
		It does not matter what is left beyond the expected length.
		
		 Example:
		If array A is [4, 1, 1, 2, 1, 3]
		and value elem is 1, 
		then new length is 3, and A is now [4, 2, 3] 
		Try to do it in less than linear additional space complexity.
	 * @param a
	 * @param b
	 * @return
	 */
	public static int removeElement(ArrayList<Integer> a, int b) {
		if(a == null || a.size() ==0){
			return 0;
		}
		
		int curInd = 0;
		int newSize = 0;
		for(int i = 0; i<a.size(); i++){
			if(a.get(i) == b){
				
			}
			else{
				a.set(curInd, a.get(i));
				curInd++;
				newSize++;
			}
		}
		return newSize;
	}
	
	
	/**
	 *  Combination Sum II - Backtracking
		Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
		
		Each number in C may only be used once in the combination.
		
		 Note:
		All numbers (including target) will be positive integers.
		Elements in a combination (a1, a2, ... , ak)should be sorted.
		The solution set must not contain duplicate combinations.
		Example :
		
		Given candidate set 10,1,2,7,6,1,5 and target 8,
		
		A solution set is:
		
		[1, 7]
		[1, 2, 5]
		[2, 6]
		[1, 1, 6]
		 

	 * @param a
	 * @param b
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> combinationSum1(List<Integer> a, int b) {
		ArrayList<ArrayList<Integer>>  returnList = new ArrayList<ArrayList<Integer>>();
		if(b < 0 || a == null || a.size() == 0){
			return returnList;
		}
		if(b==0){
			return returnList;
		}
		
		Collections.sort(a);
		
		Set<ArrayList<Integer>> setList = new HashSet<ArrayList<Integer>>();
		
		ArrayList<Integer> currList = new ArrayList<Integer>();
		combinationSum1(a, 0, a.size(), b, currList, setList);
		for (ArrayList<Integer> list : setList) {
			returnList.add(list);
		}
		return returnList;
	}
	

	private static void combinationSum1(List<Integer> a, int currentInd, int maxIndex, int b,
			ArrayList<Integer> currList, Set<ArrayList<Integer>> setList) {
		if(b == 0){
			ArrayList<Integer> listToAdd = new ArrayList<Integer>();
			listToAdd.addAll(currList);
			setList.add(listToAdd);
			return;
		}
		if(b<0){
			return;
		}
		for(int i = currentInd; i<maxIndex; i++){
			b = b-a.get(i);
			currList.add(a.get(i));
			combinationSum1(a, i+1, maxIndex, b, currList, setList);
			b=b+a.get(i);
			currList.remove(currList.size()-1);
		}
	}
	
	
	
	/**
	 * Combination Sum - Backtracking  
	 * 
	 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
		
		The same repeated number may be chosen from C unlimited number of times.
		
		 Note:
		All numbers (including target) will be positive integers.
		Elements in a combination should be sorted ascending.
		The combinations themselves must be sorted in ascending order.
		
		The solution set must not contain duplicate combinations.
		Example, 
		Given candidate set 2,3,6,7 and target 7,
		A solution set is:
		
		[2, 2, 3]
		[7]

	 * @param a
	 * @param b
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> combinationSum(List<Integer> a, int b) {
		ArrayList<ArrayList<Integer>>  returnList = new ArrayList<ArrayList<Integer>>();
		if(b < 0 || a == null || a.size() == 0){
			return returnList;
		}
		if(b==0){
			return returnList;
		}
		
		Collections.sort(a);
		//Set<ArrayList<Integer>> setList = new HashSet<ArrayList<Integer>>();
		
		ArrayList<Integer> currList = new ArrayList<Integer>();
		combinationSum(a, 0, a.size(), b, currList, returnList);
		/*for (ArrayList<Integer> list : setList) {
			returnList.add(list);
		}*/
		return returnList;
	}
	
	private static void combinationSum(List<Integer> a, int currentInd, int maxIndex, int b,
			ArrayList<Integer> currList, ArrayList<ArrayList<Integer>> setList) {
		if(b == 0){
			ArrayList<Integer> listToAdd = new ArrayList<Integer>();
			listToAdd.addAll(currList);
			setList.add(listToAdd);
			return;
		}
		if(b<0){
			return;
		}
		for(int i = currentInd; i<maxIndex; ){
			b = b-a.get(i);
			currList.add(a.get(i));
			combinationSum(a, i, maxIndex, b, currList, setList);
			b=b+a.get(i);
			currList.remove(currList.size()-1);
			int curVal = a.get(i);
			i++;
			while(i<maxIndex && a.get(i) == curVal){
				i++;
			}
		}
	}
	
	
	/**
	 * Nearest Smaller Element - Stacks And Queues 
	 * 
	 * 
	 * Given an array, find the nearest smaller element G[i] for every element A[i] in the array such that the element has an index smaller than i.

		More formally,
		
		G[i] for an element A[i] = an element A[j] such that 
		    j is maximum possible AND 
		    j < i AND
		    A[j] < A[i]
		Elements for which no smaller element exist, consider next smaller element as -1.
		
		Example:
		
		Input : A : [4, 5, 2, 10]
		Return : [-1, 4, -1, 2]
		
		Example 2:
		
		Input : A : [3, 2, 1]
		Return : [-1, -1, -1]
		

	 * @param arr
	 * @return
	 */
	public static ArrayList<Integer> prevSmaller(ArrayList<Integer> arr) {
		if(arr == null || arr.size() == 0){
			return null;
		}
		Stack<Integer> stk = new Stack<Integer>();
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		stk.push(-1);
		for (int i = 0; i < arr.size(); i++) {
			if(stk.peek() < arr.get(i)){
				returnList.add(stk.peek());
			}
			else{
				while(stk.peek() >= arr.get(i)){
					stk.pop();
				}
				returnList.add(stk.peek());
			}
			stk.push(arr.get(i));
		}
		return returnList;
		
    }
	
	
	public int diffPossible(final List<Integer> a, int b) {
		if( a==null || a.size() < 2){
			return 0;
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Integer integer : a) {
			if(map.get(integer) != null){
				map.put(integer, map.get(integer)+1);
			}
			else{
				map.put(integer, 1);
			}
		}
		
		if(b==0){
			for (Integer integer : a) {
				if(map.get(integer) != null && map.get(integer)>1){
					return 1;
				}
			}			
		}
		else{
			for (Integer integer : a) {
				if(map.get(integer+b)!=null){
					return 1;
				}
			}
		}
		return 0;
	}
	
	
	/**
	 * Sory By Color - Two Pointers 
	 * 
	 * Given an array with n objects colored red, white or blue, 
		sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
		
		Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
		
		Note: Using library sort function is not allowed.
		
		Example :
		
		Input : [0 1 2 0 1 2]
		Modify array so that it becomes : [0 0 1 1 2 2]
	 * 
	 * @param a
	 */
	public void sortColors(ArrayList<Integer> a) {
		if(a==null || a.size()==0){
			return;
		}
		int[] count = new int[3];
		count[0] = 0;
		count[1] = 0;
		count[2] = 0;
		for (Integer i : a) {
			count[i] = count[i] +1; 
		}
		
		int j=0;
		int k=0;
		
		while(j<=2){
			if(count[j] > 0){
				a.set(k++, j);
				count[j] = count[j] -1;
			}
			else{
				j++;
			}
		}
	}
	
	
	/**
	 * Remove Duplicates From Sorted Array II - Two Pointers 
	 * 
	 * Remove Duplicates from Sorted Array

		Given a sorted array, remove the duplicates in place such that each element appear atmost twice and return the new length.
		
		Do not allocate extra space for another array, you must do this in place with constant memory.
		
		Note that even though we want you to return the new length, make sure to change the original array as well in place
		
		For example,
		Given input array A = [1,1,1,2],
		
		Your function should return length = 3, and A is now [1,1,2].
	 * @param a
	 * @return
	 */
	public static int removeDuplicates(ArrayList<Integer> a) {
		if(a==null || a.size()==0){
			return 0;
		}
		int currentIndex = 1;
		int i=1;
		Integer val = a.get(0);
		int count = 1;
		while(i<a.size()){
			if(a.get(i).equals(val) ){
				i++;
				count++;
				if(count<=2){
					a.set(currentIndex, val);
					currentIndex++;
				}
			}
			else{
				val = a.get(i);
				a.set(currentIndex, a.get(i));
				currentIndex++;
				i++;
				count = 1;
			}
		}
		int j=a.size()-1;
		for(;j>=currentIndex;j--){
			a.remove(j);
		}
		return a.size();
	}
	
	
	/**
	 * Add One To Number = Arrays
	 * 
	 * Given a non-negative number represented as an array of digits,

		add 1 to the number ( increment the number represented by the digits ).
		
		The digits are stored such that the most significant digit is at the head of the list.
		
		Example:
		
		If the vector has [1, 2, 3]
		
		the returned vector should be [1, 2, 4]
		
		as 123 + 1 = 124.
		
		 NOTE: Certain things are intentionally left unclear in this question which you should practice asking the interviewer.
		For example, for this problem, following are some good questions to ask :
		Q : Can the input have 0’s before the most significant digit. Or in other words, is 0 1 2 3 a valid input?
		A : For the purpose of this question, YES
		Q : Can the output have 0’s before the most significant digit? Or in other words, is 0 1 2 4 a valid output?
		A : For the purpose of this question, NO. Even if the input has zeroes before the most significant digit.
		See Expected Output

	 * @param a
	 * @return
	 */
	public static ArrayList<Integer> plusOne(ArrayList<Integer> a){
		if(a == null || a.size() == 0){
			ArrayList<Integer> returnList = new ArrayList<Integer>();
			a.add(1);
			return returnList;
		}
		int carry = 1;
		for(int i=a.size()-1; i>=0; i--){
			int sum = a.get(i) + carry;
			if(sum >= 10){
				a.set(i, sum%10);
				carry = sum/10;
			}
			else{
				a.set(i, sum);
				carry = 0;
			}
		}
		if(carry > 0){
			a.add(0, carry);
		}
		
		while(a.size() > 0){
			if(a.get(0) == 0){
				a.remove(0);
			}
			else{
				break;
			}
		}
		
		return a;
	}
	
	
	
	public static ArrayList<Integer> flip(String A){
		if(A==null || A.length() ==0){
			return new ArrayList<Integer>();
		}
		char[] charArray = A.toCharArray();
		int maxSum = 0;
		int currSum = 0;
		int maxStartInd = -1;
		int maxEndInd = -1;
		int currStartInd = 0;
		int currEndInd = -1;
		int onesCount = 0;
		for (int i = 0; i < charArray.length; i++) {
			if(charArray[i] == '0'){
				currSum++;
				currEndInd = i;
				if(currSum > maxSum){
					maxSum = currSum;
					maxStartInd = currStartInd;
					maxEndInd = currEndInd;
				}
			}
			else{
				onesCount++;
				currSum--;
				if(currSum < 0){
					currStartInd = i+1;
					currSum = 0;
				}
			}
		}
		
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		if(maxSum != 0){
			returnList.add(maxStartInd+1);
			returnList.add(maxEndInd+1);
		}
		return returnList;
	}
	
	
	/**
	 * Sorted Insert Position - Binary Search 
	 * 
	 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

		You may assume no duplicates in the array.
		
		Here are few examples.
		
		[1,3,5,6], 5 --> 2
		[1,3,5,6], 2 --> 1
		[1,3,5,6], 7 --> 4
		[1,3,5,6], 0 --> 0

	 * @param a
	 * @param b
	 * @return
	 */
	public static int searchInsert(ArrayList<Integer> a, int b) {
		if(a == null || a.size() == 0){
			return 0;
		}
		else if(a.size() == 1){
			if(a.get(0) < b){
				return 1;
			}
			return 0;
		}
		
		int start = 0;
		int end = a.size()-1;
		int mid = 0; 
		
		while(start <= end){
			mid = (start + end)/2;
			if(a.get(mid) == b){
				return mid;
			}
			else if(a.get(mid) > b){
				end = mid -1;
			}
			else{
				start = mid +1;
			}
		}
		if(a.get(mid) < b){
			return mid+1;
		}
		return mid;
	}
	
	/**
	 * Two Pointers - Diffk
	 * 
	 * Given an array ‘A’ of sorted integers and another non negative integer k, find if there exists 2 indices i and j such that A[i] - A[j] = k, i != j.

	 Example: Input : 
	    A : [1 3 5] 
	    k : 4
	 Output : YES as 5 - 1 = 4 
	Return 0 / 1 ( 0 for false, 1 for true ) for this problem
	
	Try doing this in less than linear space complexity.
	
	 * @param a
	 * @param b
	 * @return
	 */
	public static int diffPossible(ArrayList<Integer> a, int b) {
		if(a == null || a.size() < 2){
			return 0;
		}
		int i=0;
		int j=1;
		while(true){
			if(i>a.size()-2){
				return 0;
			}
			if(j>=a.size() ){
				i++;
				j = i+1;
				continue;
			}
			int diff = a.get(j) - a.get(i);
			if(diff == b){
				return 1;
			}
			if(diff > b ){
				i++;
				j=i+1;
			}
			else{
				j++;
			}
		}
	}
	
	
	
	public static int sumNumbers(TreeNode a) {
		if(a==null){
			return 0;
		}
		
		//int level = 0;
		long numberTillNow = a.val;
		
		if(a.left == null && a.right == null){
			return (int)numberTillNow;
		}
		
		long leftVal =  getSumNumbers(a.left, numberTillNow*10)%1003;
		long rightVal =  getSumNumbers(a.right, numberTillNow*10)%1003;
		return (int)(leftVal+rightVal)%1003;
	}
	
	
	private static long getSumNumbers(TreeNode a, long numberTillNow) {
		if(a == null){
			return 0;
		}
		numberTillNow = numberTillNow + a.val;
		//System.out.println(numberTillNow);
		
		if(a.left == null && a.right == null){
			return numberTillNow;
		}
		
		long leftVal =  getSumNumbers(a.left, numberTillNow*10)%1003;
		long rightVal =  getSumNumbers(a.right, numberTillNow*10)%1003;
		return (leftVal+rightVal)%1003;
	}

	/*private static long getSumNumbers(TreeNode a, long numberTillNow) {
		numberTillNow = numberTillNow + a.val;
		if(a.left == null && a.right == null){
			return numberTillNow%1003;
		}
		
		if(a.left == null){
			long leftVal =  0;
			long rightVal =  getSumNumbers(a.right, numberTillNow*10)%1003;
			return (leftVal+rightVal)%1003;
		}
		if(a.right == null){
			long leftVal =  getSumNumbers(a.left, numberTillNow*10)%1003;
			long rightVal =  0;
			return (leftVal+rightVal)%1003;
		}
		
		long leftVal =  getSumNumbers(a.left, numberTillNow*10)%1003;
		long rightVal =  getSumNumbers(a.right, numberTillNow*10)%1003;
		return (leftVal+rightVal)%1003;
	}*/
	
	
	
	public static int seats(String a) {
		/*if(a == null || a.length() == 0){
			return 0;
		}*/
		//identify the biggest group
		return 0;
	}
	
	
	public static LinkedList<Integer> removeBytes0(LinkedList<Integer> l1){
		int len = l1.size();
	      for(int i=len-1;i>=0;i--){
	          if(l1.get(i) == 0) {
	              l1.remove(i);
	          }
	      }
	      return l1;
	}
	
	
	public void intListToStringList(List<Integer> intList){
		//intList.stream().map( e -> e.toString() ).collect( toList() ) ;
	}
	
	
	
	/*public static ArrayList<String> wordBreak(String a, ArrayList<String> b) {
		ArrayList<String> returnList = new ArrayList<String>();
		if(a == null || a.length() == 0){
			return returnList;
		}
		if(b == null || b.size() == 0){
			return returnList;
		}
		Set<String> wordSet = new HashSet<>();
		for (String word : b) {
			wordSet.add(word);
		}
		
		//
		String[][] subStrArray = new String[b.size()];
		subStrArray[0] = "";
		for(int i = 1; i <b.size(); i++ ){
			String subStr = a.substring(0, i+1);
			if()
		}
 		
		return returnList;
	}*/
	
	

	public static void main(String[] args) throws Exception {
		
		Set<String> wordSet = new TreeSet<String>();
		wordSet.add("ABCD");
		wordSet.add("ABC");
		int count = 1;
		while(count < 1000000){
			System.out.println("running count = " + count);
			Thread.sleep(2000);
		}
		
		System.out.println(wordSet);
		
		//System.out.println(largestRectangleArea(new int[]{6,2,5,5,5,1,6}));
		
		/*LinkedList<Integer> d = new LinkedList<Integer>(); 
		  d.add(1);
		  d.add(0);
		  d.add(5);
		  d.add(0);
		  d.add(6);
		System.out.println(Test.removeBytes0(d));*/
		
		
		/*Map<String, String> testMap = new LinkedHashMap<String, String>();
		testMap.put("test", "test");
		testMap.get("test");*/
 		
		/*TimeZone timeZone1 = TimeZone.getTimeZone("America/Los_Angeles");
		System.out.println(timeZone1.getID());*/
		//timeZone1.get
		
		/*TreeNode listNode1 = new TreeNode(1); 
		listNode1.left = new TreeNode(2);
		listNode1.right = new TreeNode(3);
		listNode1.right.left = new TreeNode(4);
		listNode1.right.left.right = new TreeNode(5);
		System.out.println(sumNumbers(listNode1));*/
		
		/*TreeNode listNode1 = new TreeNode(6); 
		listNode1.left = new TreeNode(3);
		listNode1.left.left = new TreeNode(2);
		listNode1.left.right = new TreeNode(5);
		listNode1.left.right.left = new TreeNode(7);
		listNode1.left.right.right = new TreeNode(4);
		listNode1.right = new TreeNode(5);
		listNode1.right.right = new TreeNode(4);
		System.out.println(sumNumbers(listNode1));*/
		
		/*ArrayList<Integer> d = new ArrayList<Integer>(); 
		  d.add(1);
		  d.add(3);
		  d.add(5);
		 // d.add(6);
		  
		  System.out.println(diffPossible(d, 4));*/
		  /*System.out.println(searchInsert(d, 2));
		  System.out.println(searchInsert(d, 7));
		  System.out.println(searchInsert(d, 0));*/

		  
		  /*ArrayList<Integer> d = new ArrayList<Integer>(); 
		  d.add(1);
		  d.add(3);
		  d.add(5);
		  d.add(6);
		  
		  System.out.println(searchInsert(d, 5));
		  System.out.println(searchInsert(d, 2));
		  System.out.println(searchInsert(d, 7));
		  System.out.println(searchInsert(d, 0));*/
		
		/*System.out.println(flip("010"));
		System.out.println(flip("1101010001"));*/
		
		/*ArrayList<Integer> d = new ArrayList<Integer>(); 
		  d.add(0);
		  d.add(1);
		  d.add(2);
		  d.add(3);
		  
		  System.out.println(plusOne(d));*/
		
		/*Integer[] a = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
		//Integer[] a = new Integer[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, };
		List<Integer> list = Arrays.asList(a);
		ArrayList<Integer> b = new ArrayList<Integer>();
		for (Integer integer : a) {
			b.add(integer);
		}
		 
		 System.out.println(removeDuplicates(b)); 
		 System.out.println(b);*/
		
		/*AtomicInteger threadCounter = new AtomicInteger();
		System.out.println(threadCounter.get());*/
		
		/*Integer[] a = new Integer[]{8, 10, 6, 11, 1, 16, 8 }; 
		List<Integer> list = Arrays.asList(a);
		System.out.println( combinationSum(list, 28) );*/
		
		/*Integer[] a = new Integer[]{2,3,6,7 }; 
		List<Integer> list = Arrays.asList(a);
		System.out.println( combinationSum(list, 7) );*/
		
		/*Integer[] a = new Integer[]{10,1,2,7,6,1,5 }; 
		List<Integer> list = Arrays.asList(a);
		System.out.println( combinationSum1(list, 2) );*/
		
		//System.out.println(reverseWords("the     sky    is blue"));
		
		/*ListNode listNode = new ListNode(9); 
			listNode.next = new ListNode(7);
			listNode.next.next = new ListNode(3); 
			listNode.next.next.next = new ListNode(2); 
			listNode.next.next.next.next = new ListNode(6);
		System.out.println(insertionSortList(listNode));*/
		
		
		/*for(int i= 0; i < 11; i++){
			System.out.println(climbStairs(i));
		}*/
		
		/*ArrayList<ArrayList<Integer>> a = new ArrayList<>();
		 
		  ArrayList<Integer> b = new ArrayList<Integer>(); 
		  b.add(0); */
		  
		 /* ArrayList<Integer> c = new ArrayList<Integer>(); 
		  c.add(6);
		  c.add(6);
		  
		  ArrayList<Integer> d = new ArrayList<Integer>(); 
		  d.add(7);
		  d.add(8);
		  d.add(4);*/
		  
		  /*a.add(b); //a.add(c); a.add(d);
		
		System.out.println(minimumTotal(a));*/
		
		/*ThreadLocal th;
		
		double d = 80.123;
		DecimalFormat df = new DecimalFormat("#.#");
		double p = Double.parseDouble(df.format(d));
		System.out.println(p);*/
		
		
		
		
		// List<Integer> asList = Arrays.asList(-255, 369, 319, 77, 128, -202);
		/*List<Integer> asList = Arrays.asList(16, -277, -479, 62, 369, -185,
				377, 486, -495, 358, 338, -166, -158, 338, -470, 473, 68, -152,
				-301, 425, -246, 428, 369, 232, 198, -440, -366, -284, 89,
				-279, 195, 58, -411, -75, -487, -345, 447, -253, -466, -326,
				260, -494, -443, -335, 301, 191, 184, 161, -403, 89, 89, 197,
				316, -91, 285, -457, 232, -313, -355, -333, 80, 86, 182, -484,
				116, 16, -428, -234, -347, -133, -161, -260, -285, -464, 214,
				446, -324, -70, -44, 199, 105, 465, -487, 271, 13, 167, 278,
				-420, 166, -445, -255, 367, 343, -51, -67, -238, -243, -174,
				92, -51, -216, -245, -209, -347, -24, -492, -164, 359, 451, 3,
				32, -468, 96, -48, 147, -339, -153, 76, 281, 432, -196, -310,
				-354, -132, 109, -400, -378, -49, -407, 395, -339, -68, 40,
				276, 90, -398, 160, 353, 86, -322, 468, 438, 36, 274, -276,
				383, 68, 71, 311, 240, -97, 403, 238, -75, 103, 71, -367, -198,
				476, 178, -38, 459, 71, 233, 277, 442, 473, -400, 473, -258,
				-200, 289, -92, -286, 42, 458, 436, -341, -65, 252, 62, 182,
				-88, -210, 303, 244, 300, 35, 119, -242, -11, 480, -195, 331,
				191, 105, -149, 412, -388, -399, 262, -36, -113, 27, 236, -274,
				111, 69, 491, 144, 364, -322, -459, 12, 378, 26, -298, 250,
				-92, 424, -388, 273, -487, 233, 409, -289, 98, 242, 84, 36,
				-86, 288, 18, -271, -243, 342, 118, -348, -394, -372, -16, 363,
				-337, -125, -120, -340, 91, -294, -72, -335, 50, 384, -159,
				308, 258, -246, -124, 69, -431, -297, 276, 371, 331, 69, 333,
				-476, 132, -65, -369, 314, 357, 99, 281, 271, -99, 312, -61,
				411, 346, 266, 143, -230, -260, 197, 27, -159, -101, 296, 448,
				12, -179, 472, 351, -360, -230, -402, 455, -225, 240, 245,
				-373, -203, 167, -383, -408, 98, 302, -56, 67, 426, -374, 388,
				-474, 405, -346, -361, -377, 190, -300, -10, -208, 171, -304,
				-339, 68, -497, 41, 446, 115, -88, -55, -156, 479, -486, -165,
				-158, -257, 184, -13, -476, -447, 343, -255, 125, 192, -54,
				212, -416, -368, -452, -47, -475, 207, 327, 277, -40, 18, -223,
				305, -370, -245, -449, -37, -500, -195, 338, 276, -358, -58,
				-143, 243, 68, 21, -41, -489, 30, -178, 436, 470, -70, 321,
				378, 455, 157, 474, 146, 157, 243, -43, 109, 238, 243, 100,
				326, -82, -80, -104, 389, -452, -155, -156, -187, -45, 92,
				-191, 69, 11, 219, -484, 318, -424, 491, -84, -261, -237, 136,
				25, 277, 404, 7, 491, 143, 91, -208, -190, -66, 252, 61, 147,
				37, 40, 209, 374, 375, -461, 131, -216, -432, 294, 390, -229,
				451, -484, 211, -75, -273, -137, 473, 422, -156, -184, -150,
				458, -28, 62, 50, -400, -40, 266, 21, 346, 391, 46, 472, 6,
				-305, -305, 170, -293, -127, 377, -415, 491, -437, 16, 400,
				-168, 309, -447, -417, 444, -253, 479, 82, 340, 134, -326, 107,
				100, -201, -494, 350, 431, 16, -13, -290, 329, -238, -184, 278,
				-409, -375, -208, -364, 343, -414, 89, -177, -249, -477, 124,
				-89, 293, 401, -435, -210, 349, 98, 77, 92, 250, 30, -251, 112,
				-12, -85, -156, 82, -31, 267, 22, 105, -449, 131, 496, 395,
				288, 435, -361, 167, 181, -57, -404, 496, 62, -339, 56, -103,
				-459, -459, 226, 253, -328, -248, 318, 200, 164, 78, -496, 453,
				-84, -304, -254, 460, -153, -329, -285, -432, -308, 63, -107,
				402, -470, -184, -351, -7, 54, -123, 256, -395, -280, -391,
				-300, 81, -269, -170, -19, 462, 110, -264, 297, -250, 44, 465,
				-160, 485, -313, -497, -167, -372, 464, 0, 138, 110, 269, -491,
				203, 250, 400, -301, -445, -480, -133, -349, -499, 499, -133,
				-171, -57, -147);

		System.out.println(candy(asList));*/

		/*
		 * List<Integer> asList = Arrays.asList(345550, 795673, 817297, 463389,
		 * 310822, 805076, 920925, 817297, 817297, 182709, 436820, 817297,
		 * 817297, 817297, 817297, 817297, 817297, 263236, 772190, 585741,
		 * 354367, 514928, 903332, 817297, 817297, 222927, 608, 817297, 672384,
		 * 817297, 681041, 256380, 805870, 266065, 676851, 817297, 438526,
		 * 817297, 880946, 817297, 817297, 817297, 247558, 817297, 817297,
		 * 357701, 839408, 817297, 925734, 817297, 817297, 798594, 817297,
		 * 817297, 735998, 817297, 817297, 817297, 817297, 322939, 817297,
		 * 817297, 817297, 812814, 734999, 817297, 134242, 817297, 118807,
		 * 817297, 557107, 156963, 817297, 293811, 817297, 817297, 405032,
		 * 89956, 817297, 4995, 48808, 53643, 817297, 345494, 817297, 817297,
		 * 817297, 817297, 996030, 73655, 917692, 515770, 917138, 817297,
		 * 817297, 817297, 817297, 817297, 238606, 817297, 817297, 876877,
		 * 817297, 817297, 817297, 817297, 817297, 817297, 817297, 817297,
		 * 759087, 817297, 376711, 817297, 761397, 817297, 288919, 53522,
		 * 817297, 412310, 817297, 323156, 530068, 968505, 703653, 238695,
		 * 817297, 817297, 145534, 142969, 817297, 568236, 817297, 817297,
		 * 547877, 817297, 817297, 817297, 159595, 817297, 547748, 587178,
		 * 951289, 829077, 183946, 5238, 525340, 817297, 817297, 817297, 941193,
		 * 296599, 817297, 817297, 18742, 259107, 752688, 817297, 817297,
		 * 136640, 249438, 817297, 817297, 817297, 817297, 729092, 817297,
		 * 526140, 709339, 817297, 346760, 868516, 817297, 488457, 817297,
		 * 422269, 690955, 817297, 494990, 817297, 817297, 878670, 152130,
		 * 817297, 817297, 817297, 432349, 817297, 817297, 451043, 817297,
		 * 606514, 817297, 817297, 425553, 817297, 273500, 104433, 297289,
		 * 801636, 817297, 674138, 895444, 817297, 237525, 817297, 274485,
		 * 980252, 569478, 537620, 817297, 637787, 994649, 980607, 586599,
		 * 817297, 552136, 817297, 987194, 15735, 817297, 817297, 817297,
		 * 101278, 817297, 817297, 673307, 231170, 817297, 817297, 817297,
		 * 403281, 817297, 576661, 817297, 583692, 817297, 318073, 817297,
		 * 817297, 817297, 967815, 401713, 817297, 911162, 817297, 796759,
		 * 828982, 817297, 763281, 731769, 100773, 817297, 817297, 371010,
		 * 926255, 158962, 817297, 904208, 817297, 235542, 841642, 473435,
		 * 817297, 460901, 817297, 782334, 817297, 569957, 728893, 817297,
		 * 459182, 817297, 515670, 817297, 381180, 111842, 817297, 641692,
		 * 817297, 349796, 817297, 926385, 950051, 932495, 817297, 817297,
		 * 760357, 4779, 817297, 817297, 730053, 957368, 736526, 429749, 817297,
		 * 817297, 817297, 720715, 817297, 587860, 672037, 470805, 817297,
		 * 817297, 817297, 817297, 534528, 107022, 817297, 267143, 817297,
		 * 334588, 783229, 817297, 817297, 817297, 711634, 817297, 163881,
		 * 15195, 817297, 817297, 929583, 817297, 817297, 817297, 817297,
		 * 817297, 817297, 397051, 177103, 817297, 673949, 812868, 107051,
		 * 817297, 308762, 817297, 360133, 817297, 457671, 817297, 383996,
		 * 817297, 817297, 25696, 105289, 628188, 817297, 817297, 642160,
		 * 565455, 817297, 817297, 817297, 767666, 895213, 817297, 817297,
		 * 199610, 817297, 817297, 817297, 817297, 149061, 817297, 817297,
		 * 870323, 817297, 503489, 155123, 817297, 392196, 539414, 41286,
		 * 667807, 232952, 817297, 817297, 30067, 817297, 817297, 50598, 817297,
		 * 423805, 817297, 10547, 817297, 202599, 817297, 512840, 930869,
		 * 307292, 972247, 817297, 775489, 817297, 817297, 982806, 817297,
		 * 590869, 817297, 965214, 817297, 817297, 48707, 911713, 795879,
		 * 817297, 817297, 677, 362034, 817297, 785684, 817297, 817297, 109138,
		 * 952068, 476727, 817297, 893621, 489639, 817297, 817297, 289458,
		 * 538836, 817297, 817297, 653690, 817297, 817297, 817297, 89647,
		 * 817297, 241895, 881263, 432297, 817297, 3041, 18206, 817297, 672452,
		 * 817297, 817297, 65674, 817297, 817297, 817297, 817297, 233272,
		 * 817297, 276552, 619920, 300237, 355936, 817297, 811202, 817297,
		 * 817297, 817297, 817297, 192104, 328611, 405959, 518208, 817297,
		 * 835763, 610375, 650213, 817297, 627803, 640230, 817297, 12278,
		 * 817297, 817297, 121393, 650577, 912274, 225163, 309592, 817297,
		 * 817297, 817297, 817297, 817297, 817297, 839132, 482902, 817297,
		 * 817297, 817297, 817297, 817297, 817297, 817297, 592996, 817297,
		 * 946395, 817297, 817297, 817297, 169558, 382129, 817297, 817297,
		 * 968842, 745267, 972617, 446065, 817297, 817297, 817297, 817297,
		 * 375463, 817297, 817297, 30169, 844394, 817297, 817297, 827693,
		 * 449706, 817297, 817297, 650409, 817297, 817297, 667151, 904686,
		 * 817297, 11423, 817297, 342440, 986566, 436543, 817297, 488935,
		 * 817297, 61678, 817297, 817297, 159602, 520848, 855240, 817297,
		 * 817297, 817297, 438962, 697510, 817297, 375901, 744587, 817297,
		 * 817297, 817297, 54845, 817297, 564326, 817297, 9167, 817297, 817297,
		 * 550286, 232088, 228587, 817297, 817297, 921560, 446717, 817297,
		 * 729103, 376199, 754927, 668547, 804719, 817297, 401162, 817297,
		 * 817297, 817297, 930674, 92362, 817297, 817297, 817297, 280659,
		 * 817297, 817297, 349286, 966203, 817297, 817297, 238205, 998091,
		 * 201978, 817297, 817297, 817297, 817297, 517970, 514931, 817297,
		 * 817297, 876733, 59568, 440215, 817297, 817297, 817297, 817297,
		 * 201030, 823160, 274353, 817297, 817297, 817297, 817297, 120553,
		 * 817297, 817297, 511328, 194736, 752258, 713055, 817297, 817297,
		 * 817297, 655162, 268091, 817297, 817297, 12944, 798699, 817297,
		 * 508366, 817297, 89165, 817297, 835033, 817297, 817297, 817297,
		 * 506974, 407170, 338801, 668560, 372551, 607670, 817297, 795222,
		 * 867292, 698203, 576590, 817297, 121896, 817297, 928111, 909304,
		 * 500640, 817297, 817297, 817297, 817297, 817297, 817297, 637466,
		 * 817297, 817297, 817297, 325458, 817297, 817297, 55494, 216918, 74222,
		 * 367297, 871278, 878792, 817297, 396708, 817297, 817297, 742484,
		 * 957163, 817297, 298271, 682831, 817297, 817297, 817297, 409713,
		 * 817297, 817297, 817297, 817297, 817297, 553618, 817297, 817297,
		 * 817297, 224463, 852006, 971420, 797015, 776982, 790867, 427681,
		 * 311461, 752934, 817297, 817297, 817297, 817380, 817297, 817297,
		 * 817297, 957370, 817297, 737503, 817297, 817297, 817297, 817297,
		 * 817297, 195089, 817297, 349020, 363491, 877768, 949619, 872723,
		 * 895070, 817297, 819230, 817297, 508772, 817297, 817297, 988919,
		 * 827564, 692408, 817297, 817297, 680604, 159844, 805408, 219151,
		 * 817297, 817297, 817297, 817297, 635061, 281133, 817297, 817297,
		 * 817297, 817297, 510461, 817297, 631070, 817297, 817297, 817297,
		 * 174599, 817297, 817297, 746114, 817297, 803001, 817297, 280654,
		 * 817297, 894881, 529085, 328922, 738280, 904065, 817297, 501174,
		 * 817297, 817297, 817297, 817297, 817297, 607501, 817297, 941139,
		 * 817297, 317590, 817297, 817297, 817297, 817297, 817297, 531995,
		 * 817297, 375321, 817297, 817297, 709186, 817297, 123471, 63145,
		 * 817297, 611178, 689348, 278361, 817297, 19587, 817297, 817297,
		 * 817297, 675166, 817297, 817297, 341930, 817297, 520628, 588666,
		 * 468153, 818369, 172489, 817297, 817297, 817297, 912510, 817297,
		 * 817297, 75540, 537448, 664192, 817297, 817297, 485682, 817297,
		 * 817297, 817297, 817297, 817297, 473448, 817297, 872999, 817297,
		 * 985949, 817297, 817297, 124204, 817297, 817297, 817297, 678994,
		 * 817297, 817297, 635987, 817297, 277891, 817297, 564490, 695721,
		 * 107852, 88145, 817297, 46639, 716194, 817297, 817297, 134441, 780574,
		 * 817297, 658870, 541265, 817297, 817297, 862303, 817297, 817297,
		 * 400393, 817297, 803632, 750347, 710469, 817297, 949874, 817297,
		 * 276123, 46874, 719099, 817297, 817297, 457112, 480730, 817297,
		 * 552795, 258486, 817297, 862125, 301792, 414098, 817297, 817297,
		 * 817297, 817297, 817297, 817297, 129119, 570988, 817297, 180510,
		 * 817297, 41281, 817297, 189412, 845855, 814219, 817297, 817297,
		 * 732951, 260013, 563923, 817297, 859399, 441392, 817297, 817297,
		 * 817297, 817297, 510446, 817297, 925035, 817297, 219460, 817297,
		 * 817297, 883074, 817297, 817297, 817297, 840903, 198162, 296172,
		 * 817297, 161981, 817297, 635734, 834334, 817297, 817297, 817297,
		 * 153672, 817297, 992243, 218689, 270024, 723313, 817297, 517357,
		 * 817297, 876842, 817297, 817297, 817297, 227251, 984944, 64521,
		 * 817297, 817297, 817297, 817297, 740508, 817297, 884083, 202328,
		 * 341565, 787434, 817297, 955001, 817297, 817297, 13632, 592638,
		 * 817297, 35188, 668959, 554733, 817297, 817297, 341798, 817297,
		 * 817297, 817297, 174566, 416959, 817297); //List<Integer> asList =
		 * Arrays.asList(-1, -2, -3, -4, -5);
		 * 
		 * System.out.println(majorityElement(asList));
		 */

		/*
		 * List<Integer> asList = Arrays.asList(-40, -15, -49, -17, -8, -39, 86,
		 * -43, 47, 25, 58, -35, -38, -87, -11, 1, -13, -73, -24, 72, 31, 40, 5,
		 * -16, -32, 96, 69, 54, -23); //List<Integer> asList =
		 * Arrays.asList(-1, -2, -3, -4, -5);
		 * 
		 * System.out.println(maxp3(asList));
		 */

		/*
		 * List<Integer> asList = Arrays.asList(0, 1, 0, 1);
		 * 
		 * System.out.println(bulbs(asList));
		 */

		// System.out.println(countAndSay(5));

		/*
		 * ListNode listNode = new ListNode(9); listNode.next = new ListNode(7);
		 * listNode.next.next = new ListNode(7); listNode.next.next.next = new
		 * ListNode(2); //listNode.next.next.next.next = new ListNode(6);
		 * System.out.println(lPalin(listNode));
		 */

		/*
		 * ListNode listNode = new ListNode(6); listNode.next = new ListNode(3);
		 * listNode.next.next = new ListNode(7); listNode.next.next.next = new
		 * ListNode(3); listNode.next.next.next.next = new ListNode(6);
		 * System.out.println(lPalin(listNode));
		 */

		// System.out.println(getRow(4));

		/*
		 * TreeNode listNode1 = new TreeNode(1); listNode1.left = new
		 * TreeNode(2); listNode1.left.right = new TreeNode(2);
		 * 
		 * System.out.println(minDepth(listNode1));
		 */

		/*
		 * TreeNode listNode1 = new TreeNode(1000); listNode1.left = new
		 * TreeNode(200); //listNode1.right = new TreeNode(200);
		 * 
		 * System.out.println(hasPathSum(listNode1, 1000));
		 */

		// int[] arr = new int[]{14, 24, 18, 46, 55, 53, 82, 18, 101, 20, 78,
		// 35, 68, 9, 16, 93, 101, 85, 81, 28, 78};
		/*
		 * List<Integer> asList = Arrays.asList(14, 24, 18, 46, 55, 53, 82, 18,
		 * 101, 20, 78, 35, 68, 9, 16, 93, 101, 85, 81, 28, 78);
		 * 
		 * System.out.println(lis(asList));
		 */

		/*
		 * ArrayList<Integer> a = new ArrayList<Integer>(); a.add(3); a.add(2);
		 * a.add(1); a.add(0); a.add(4); ArrayList<Integer> b = new
		 * ArrayList<Integer>(); b.add(2); b.add(3); b.add(1); b.add(1);
		 * b.add(4); System.out.println(canJump(a));
		 */

		/*
		 * [2, 6, 6, 1, 16, 6, 15] [9, 16, 5, 4, 20, 3, 3]
		 */

		
			/*ArrayList<ArrayList<Integer>> a = new ArrayList<>();
		 
			  ArrayList<Integer> b = new ArrayList<Integer>(); b.add(2); b.add(6);
			  b.add(6); b.add(1); b.add(16); b.add(6); b.add(15);
			  
			  ArrayList<Integer> c = new ArrayList<Integer>(); c.add(9); c.add(16);
			  c.add(5); c.add(4); c.add(20); c.add(3); c.add(3);
			  
			  a.add(b); a.add(c);
			  
			  System.out.println(adjacent(a));*/
		 

		/*
		 * ArrayList<ArrayList<Integer>> a = new ArrayList<>();
		 * 
		 * ArrayList<Integer> b = new ArrayList<Integer>(); b.add(2); b.add(68);
		 * b.add(7); b.add(81); b.add(81);
		 * 
		 * ArrayList<Integer> c = new ArrayList<Integer>(); c.add(13); c.add(4);
		 * c.add(7); c.add(81); c.add(81);
		 * 
		 * a.add(b); a.add(c);
		 * 
		 * System.out.println(adjacent(a));
		 */

		// System.out.println(numDecodings("113"));

		/*
		 * TreeNode listNode1 = new TreeNode(3); listNode1.left = new
		 * TreeNode(1); listNode1.right = new TreeNode(20); listNode1.left.left
		 * = new TreeNode(6); listNode1.right.right = new TreeNode(7);
		 * listNode1.right.left = new TreeNode(15); listNode1.right.left.left =
		 * new TreeNode(12); listNode1.right.left.right = new TreeNode(5);
		 * 
		 * System.out.println(lca(listNode1, 1, 1));
		 */

		/*
		 * TreeNode listNode1 = new TreeNode(3); listNode1.left = new
		 * TreeNode(9); listNode1.right = new TreeNode(20); listNode1.left.left
		 * = new TreeNode(6); listNode1.right.right = new TreeNode(7);
		 * listNode1.right.left = new TreeNode(15); listNode1.right.left.left =
		 * new TreeNode(12); listNode1.right.left.right = new TreeNode(5);
		 * 
		 * System.out.println(zigzagLevelOrder(listNode1));
		 */

		/*
		 * TreeNode listNode1 = new TreeNode(3); listNode1.left = new
		 * TreeNode(9); listNode1.right = new TreeNode(20); listNode1.left.left
		 * = new TreeNode(6); listNode1.right.right = new TreeNode(7);
		 * listNode1.right.left = new TreeNode(15); listNode1.right.left.left =
		 * new TreeNode(12); listNode1.right.left.right = new TreeNode(5);
		 * 
		 * System.out.println(zigzagLevelOrder(listNode1));
		 */

		/*
		 * ListNode listNode = new ListNode(1); listNode.next = new
		 * ListNode(10); listNode.next.next = new ListNode(20);
		 * 
		 * ListNode listNode1 = new ListNode(4); listNode1.next = new
		 * ListNode(10); listNode1.next.next = new ListNode(11);
		 * listNode1.next.next.next = new ListNode(13);
		 * 
		 * ListNode listNode2 = new ListNode(3); listNode2.next = new
		 * ListNode(8); listNode2.next.next = new ListNode(9);
		 * 
		 * ArrayList<ListNode> a = new ArrayList<ListNode>(); a.add(listNode);
		 * a.add(listNode1); a.add(listNode2);
		 * 
		 * System.out.println(mergeKLists(a));
		 */

		/*
		 * ListNode listNode = new ListNode(1); listNode.next = new
		 * ListNode(10); listNode.next.next = new ListNode(20);
		 * 
		 * ListNode listNode1 = new ListNode(4); listNode1.next = new
		 * ListNode(11); listNode1.next.next = new ListNode(13);
		 * 
		 * ListNode listNode2 = new ListNode(3); listNode2.next = new
		 * ListNode(8); listNode2.next.next = new ListNode(9);
		 * 
		 * ArrayList<ListNode> a = new ArrayList<ListNode>(); a.add(listNode);
		 * a.add(listNode1); a.add(listNode2);
		 * 
		 * System.out.println(mergeKLists(a));
		 */

		/*
		 * ArrayList<Integer> a = new ArrayList<Integer>(); a.add(2); a.add(7);
		 * a.add(7); a.add(81); a.add(81); System.out.println(dNums(a, 1));
		 */

		/*
		 * ArrayList<Integer> a = new ArrayList<Integer>(); a.add(1); a.add(2);
		 * a.add(1); a.add(3); a.add(4); a.add(3); System.out.println(dNums(a,
		 * 3));
		 */

		// dateTest();

		/*
		 * ArrayList<String> a = new ArrayList<String>(); a.add("aaa");
		 * a.add("aaa"); a.add("aaa"); a.add("aaa"); a.add("aaa");
		 * System.out.println(findSubstring(
		 * "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
		 * , a));
		 */

		/*
		 * ArrayList<String> a = new ArrayList<String>(); a.add("foo");
		 * a.add("bar"); System.out.println(findSubstring("barfoothefoobarman",
		 * a));
		 */

		/*
		 * System.out.println(fractionToDecimal(87, 22));
		 * System.out.println(fractionToDecimal(2, 3));
		 * System.out.println(fractionToDecimal(-1, -2147483648));
		 * System.out.println(fractionToDecimal(945, 103));
		 */
		// System.out.println(fractionToDecimal(-2, -1));
		// System.out.println(fractionToDecimal(-2147483648, -1));

		// System.out.println((new Test()).minWindow("ADOBECODEBANC","ABC"));

		// System.out.println(colorful(263));

		// System.out.println(generate(5));

		// System.out.println(solveNQueens(8));

		/*
		 * ArrayList<Integer> a = new ArrayList<Integer>(); a.add(1); a.add(2);
		 * a.add(3); System.out.println(permute(a));
		 */

		// System.out.println(getPermutation(9, 46));

		// System.out.println(generateParenthesis(3));

		// System.out.println(letterCombinations("11"));

		// System.out.println(combine(4, 2));

		/*
		 * ArrayList<String> a = new ArrayList<String>(); a.add("1");
		 * a.add("2"); a.add("+"); System.out.println(evalRPN(a));
		 */

		/*
		 * System.out.println(simplifyPath("/a/./b//../../c"));
		 * System.out.println(simplifyPath("/home/"));
		 * System.out.println(simplifyPath("/../../b"));
		 * System.out.println(simplifyPath("/../"));
		 */

		/*
		 * ListNode listNode = new ListNode(1); listNode.next = new ListNode(2);
		 * listNode.next.next = new ListNode(3); listNode.next.next.next = new
		 * ListNode(4); listNode.next.next.next.next = new ListNode(5);
		 * listNode.next.next.next.next.next = listNode.next.next.next;
		 * 
		 * System.out.println(detectCycle(listNode));
		 */

		/*
		 * ListNode listNode = new ListNode(1); listNode.next = new ListNode(2);
		 * listNode.next.next = new ListNode(3); listNode.next.next.next = new
		 * ListNode(4); listNode.next.next.next.next = new ListNode(5);
		 * listNode.next.next.next.next.next = new ListNode(6);
		 * 
		 * System.out.println(reverseBetween(listNode, 2, 2));
		 */

		/*
		 * ListNode listNode = new ListNode(1); listNode.next = new ListNode(2);
		 * listNode.next.next = new ListNode(3); listNode.next.next.next = new
		 * ListNode(4); listNode.next.next.next.next = new ListNode(5);
		 * listNode.next.next.next.next.next = new ListNode(6);
		 * 
		 * System.out.println(reverseListRecurse(listNode));
		 */

		/*
		 * ListNode listNode = new ListNode(1); listNode.next = new ListNode(2);
		 * listNode.next.next = new ListNode(3); listNode.next.next.next = new
		 * ListNode(4); listNode.next.next.next.next = new ListNode(5);
		 * //listNode.next.next.next.next.next = new ListNode(6);
		 * 
		 * System.out.println(swapPairs(listNode));
		 */

		/*
		 * ListNode listNode = new ListNode(1); listNode.next = new ListNode(1);
		 * listNode.next.next = new ListNode(3); listNode.next.next.next = new
		 * ListNode(5); listNode.next.next.next.next = new ListNode(6);
		 * listNode.next.next.next.next.next = new ListNode(6);
		 * 
		 * System.out.println(deleteDuplicates(listNode));
		 */

		/*
		 * ListNode listNode = new ListNode(1); listNode.next = new ListNode(4);
		 * listNode.next.next = new ListNode(3); listNode.next.next.next = new
		 * ListNode(2); listNode.next.next.next.next = new ListNode(5);
		 * listNode.next.next.next.next.next = new ListNode(2);
		 */

		/*
		 * ListNode listNode = new ListNode(4); listNode.next = new ListNode(3);
		 * listNode.next.next = new ListNode(3); listNode.next.next.next = new
		 * ListNode(2); listNode.next.next.next.next = new ListNode(1);
		 * listNode.next.next.next.next.next = new ListNode(5);
		 * 
		 * System.out.println(partition(listNode, 3));
		 */

		/*
		 * ListNode listNode = new ListNode(9); listNode.next = new ListNode(9);
		 * listNode.next.next = new ListNode(9);
		 * 
		 * ListNode listNode2 = new ListNode(1); //listNode2.next = new
		 * ListNode(9); listNode.toString(); ListNode addTwoNumbers =
		 * addTwoNumbers(listNode, listNode2);
		 * System.out.println(addTwoNumbers);
		 */

		// Integer[] a = new Integer[]{1, 1, 0, 1, 1, 0, 0, 1, 1, 1 };
		/*
		 * Integer[] a = new Integer[]{0, 1, 1, 1 }; List<Integer> list =
		 * Arrays.asList(a); System.out.println(maxone(list, 0));
		 */

		/*
		 * ArrayList<String> a = new ArrayList<String>(); a.add("");
		 * a.add("ab"); a.add("abx"); //a.add(7);
		 * System.out.println(longestCommonPrefix(a));
		 */

		/*
		 * ArrayList<Integer> a = new ArrayList<Integer>(); a.add(1); a.add(1);
		 * a.add(3); a.add(3); a.add(4); a.add(1000); a.add(1000); a.add(1000);
		 * a.add(-9); a.add(-1); a.add(1); a.add(-2);
		 * System.out.println(removeDuplicates(a)); System.out.println(a);
		 */

		/*
		 * //-10, -10, -10 ArrayList<Integer> a = new ArrayList<Integer>();
		 * //-4, -8, -10, -9, -1, 1, -2, 0, -8, -2 a.add(-4); a.add(-8);
		 * a.add(-10); a.add(-9); a.add(-1); a.add(1); a.add(-2); a.add(0);
		 * a.add(-8); a.add(-2); a.add(-10); a.add(-10); a.add(-10); a.add(-1);
		 * a.add(2); a.add(1); a.add(-4); System.out.println(threeSumClosest(a,
		 * -5));
		 */

		/*
		 * ArrayList<Integer> a = new ArrayList<Integer>(); a.add(10000000);
		 * //a.add(3); //a.add(3);
		 * 
		 * ArrayList<Integer> b = new ArrayList<Integer>(); b.add(10000000);
		 * //a.add(3); //a.add(3);
		 * 
		 * System.out.println(intersect(a, b));
		 */

		// System.out.println(longestPalindrome("ac"));

		// System.out.println(convert("PAYPALISHIRING", 3));
		// System.out.println(convert("kHAlbLzY8Dr4zR0eeLwvoRFg9r23Y3hEujEqdio0ctLh4jZ1izwLh70R7SAkFsXlZ8UlghCL95yezo5hBxQJ1Td6qFb3jpFrMj8pdvP6M6k7IaXkq21XhpmGNwl7tBe86eZasMW2BGhnqF6gPb1YjCTexgCurS",
		// 1));

		// System.out.println(intToRoman(0));

		// System.out.println(compareVersion11("4444371174137455", "5.168"));
		// System.out.println(compareVersion("4444371174137455", "5.168"));

		// System.out.println(lengthOfLastWord(""));

		// System.out.println(strStr("bbaabbbbbaabbaabbbbbbabbbabaabbbabbabbbbababbbabbabaaababbbaabaaaba",
		// "babaaa"));

		// System.out.println(isPalindrome("a man, a plan, a canal: PanamA"));
		// System.out.println(isPalindrome("\"\""));

		/*
		 * ArrayList<Integer> a = new ArrayList<Integer>(); a.add(1); a.add(3);
		 * a.add(3); System.out.println(singleNumber(a));
		 */

		// System.out.println(numSetBits(2));

		// System.out.println(pow(79161127, 99046373, 57263970));

		// System.out.println(sqrt(2147483647));

		/*
		 * ArrayList<Integer> a = new ArrayList<Integer>(); a.add(1); a.add(3);
		 * a.add(5); a.add(7);
		 * 
		 * ArrayList<Integer> b = new ArrayList<Integer>(); b.add(10);
		 * b.add(11); b.add(16); b.add(20);
		 * 
		 * ArrayList<Integer> c = new ArrayList<Integer>(); c.add(23);
		 * c.add(30); c.add(34); c.add(50);
		 * 
		 * ArrayList<ArrayList<Integer>> arrList = new
		 * ArrayList<ArrayList<Integer>>(); arrList.add(a); arrList.add(b);
		 * arrList.add(c);
		 * 
		 * //setZeroes(arrList); System.out.println(searchMatrix(arrList, 50));
		 */

		// System.out.println(uniquePaths(14,15));
		// System.out.println(allFactors(38808));
		// System.out.println(isPalindrome(0));

		// System.out.println(titleToNumber("BA"));

		// System.out.println(primesum(4));
		// System.out.println(primesum(16777214));

		/*
		 * ArrayList<Integer> a = new ArrayList<Integer>(); a.add(1);
		 * //a.add(4); //a.add(-1); //a.add(1);
		 * System.out.println(firstMissingPositive(a));
		 */

		// System.out.println(gcd(5, 15));

		/*
		 * ArrayList<Integer> a = new ArrayList<Integer>(); a.add(1); a.add(0);
		 * a.add(1);
		 * 
		 * ArrayList<Integer> b = new ArrayList<Integer>(); b.add(1); b.add(1);
		 * b.add(1);
		 * 
		 * ArrayList<Integer> c = new ArrayList<Integer>(); c.add(1); c.add(1);
		 * c.add(1);
		 * 
		 * ArrayList<ArrayList<Integer>> arrList = new
		 * ArrayList<ArrayList<Integer>>(); arrList.add(a); arrList.add(b);
		 * arrList.add(c);
		 * 
		 * setZeroes(arrList); System.out.println(arrList);
		 */

		/*
		 * ArrayList<Integer> a = new ArrayList<Integer>(); //3, 30, 34, 5, 9
		 * int[] arr = new int[]{ 751, 718, 708, 722, 77};//, 9, 99, 999, 9999,
		 * 9998}; for (int i : arr) { a.add(i); } a.add(3); a.add(30);
		 * a.add(34); a.add(5); //a.add(5); //a.add(9); //a.add(7);
		 * System.out.println(largestNumber(a));
		 */

		// CustomIntComparator comp= new CustomIntComparator();
		// int val = comp.compare(12, 121);
		// int val2 = comp.compare(507, 109);
		// System.out.println(val);
		// System.out.println(val2);
		/* System.out.println(prettyPrint(4)); */

		/*
		 * ArrayList<Integer> a = new ArrayList<Integer>();
		 * //-2,1,-3,4,-1,2,1,-5,4 a.add(1); a.add(2); a.add(3); a.add(4);
		 * a.add(5); a.add(6); a.add(7); //a.add(8);
		 * 
		 * Test test = new Test(); System.out.println(test.wave(a));
		 */

		/*
		 * ArrayList<Integer> a = new ArrayList<Integer>();
		 * //-2,1,-3,4,-1,2,1,-5,4 a.add(-2); a.add(1); a.add(-3); a.add(4);
		 * a.add(-1); a.add(2); a.add(1); a.add(-5); a.add(4); Test test = new
		 * Test(); System.out.println(test.maxSubArray(a));
		 * 
		 * ArrayList<Integer> b = new ArrayList<Integer>();
		 * //-2,1,-3,4,-1,2,1,-5,4 b.add(-6); b.add(-4);
		 * System.out.println(test.maxSubArray(b));
		 */

		/*
		 * ArrayList<Integer> a = new ArrayList<Integer>(); ArrayList<Integer> b
		 * = new ArrayList<Integer>(); a.add(-7); a.add(-13); b.add(1);
		 * b.add(-5); Test test = new Test();
		 * System.out.println(test.coverPoints(a, b));
		 */

		/*
		 * NavigableMap<Integer, String> map = new TreeMap<Integer, String>();
		 * map.put(1, "a"); map.put(2, "b"); map.put(3, "c"); map.put(4, "d");
		 * map.put(5, "e");
		 * 
		 * System.out.println(map.subMap(2, true, 4, true));
		 * System.out.println(map.subMap(0, true, 9, true));
		 */

		
		
	}

}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

class CountObject {
	int count;
}

class TrieNode {
	char ch;
	TrieNode[] nodes;
	List<String> strList;

	TrieNode() {
		nodes = new TrieNode[26];
		strList = new ArrayList<String>();
	}

}

class LCAStruct {
	boolean val1Found = false;
	boolean val2Found = false;
	ArrayList<Integer> pathTOVal1 = new ArrayList<Integer>();
	ArrayList<Integer> pathTOVal2 = new ArrayList<Integer>();
}

class PostOrderNode {
	TreeNode treeNode;
	boolean isRightTreeTraversed;

	PostOrderNode(TreeNode treeNode) {
		this.treeNode = treeNode;
		isRightTreeTraversed = false;
	}
}

