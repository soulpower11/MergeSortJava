
// import java library classes
// google "java api spec" to view available classes in JDK
// Scanner class is most commonly used class to get console input and others..(check API Spec)
import java.util.Arrays;
import java.util.Scanner;

class MergeInsertionSort {

	private static final int S = 12;
	private static int keyComparison;

	public static int[] insertionSort(int[] list) {
		int len = list.length;

		for (int i = 0; i < len; i++) {
			// Swap the current element till it is at the correct position
			for (int j = i; j > 0; j--) {
				// Check if the current key is smaller than the previous element, swap if true,
				// and break if false
				if (list[j] < list[j - 1]) {
					list = swap(list, j, j - 1);
					keyComparison++;
				} else
					break;
			}
		}

		return list;
	}

	public static int[] swap(int[] list, int i, int j) {
		int temp = list[i];
		list[i] = list[j];
		list[j] = temp;
		return list;
	}

	public static int[] mergeSort(int[] list) {
		int len = list.length;

		if (len == 1)
			// Return back the array if len = 1
			return list;
		else {
			int mid = list.length / 2;

			// Split array into 2 halfs from the middle
			int[] firstHalf = Arrays.copyOfRange(list, 0, mid);
			int[] secondHalf = Arrays.copyOfRange(list, mid, len);

			// Recursive call mergeSort() function to further split the halfs
			firstHalf = mergeSort(firstHalf);
			secondHalf = mergeSort(secondHalf);

			// Finally sort and merge by calling merge() function
			return merge(firstHalf, secondHalf);
		}
	}

	public static int[] merge(int[] first, int[] second) {
		int totalLen = first.length + second.length;
		int flen = first.length;
		int slen = second.length;
		int[] sortedArray = new int[totalLen];

		int i = 0;
		int j = 0;
		int k;

		// Sort by ascending order by comparing the 2 half and adding it to the sorted
		// array
		for (k = 0; k < sortedArray.length; k++) {
			if (i >= flen || j >= slen)
				break;
			if (first[i] > second[j]) {
				sortedArray[k] = second[j++];
				keyComparison++;
			} else {
				sortedArray[k] = first[i++];
				keyComparison++;
			}
		}

		// Add the remaining element from the 2 halfs to sorted array
		for (int a = i; a < flen; a++) {
			sortedArray[k++] = first[a];
		}
		for (int a = j; a < slen; a++) {
			sortedArray[k++] = second[a];
		}

		return sortedArray;
	}

	public static int[] mergeSortWithInsertion(int[] list) {
		int len = list.length;
		if (len < S) {
			// Length is smaller than the threshold, call insertionSort() function instead
			return list = insertionSort(list);
		} else {
			int mid = list.length / 2;

			// Split array into 2 halfs from the middle
			int[] firstHalf = Arrays.copyOfRange(list, 0, mid);
			int[] secondHalf = Arrays.copyOfRange(list, mid, len);

			// Recursive call mergeSort() function to further split the halfs
			firstHalf = mergeSortWithInsertion(firstHalf);
			secondHalf = mergeSortWithInsertion(secondHalf);

			// Finally sort and merge by calling merge() function
			return merge(firstHalf, secondHalf);
		}
	}

	// entry into a Java program - in 'C' is int main()
	public static void main(String args[]) {

		 Scanner sc = new Scanner(System.in);

		 System.out.printf("Enter the size of the list you want to sort: ");
		 int size = sc.nextInt();

		 int list[] = new int[size];

		 for (int i = 0; i < size; i++) {
		 System.out.printf("Enter the element at Index " + i + ": ");
		 int input = sc.nextInt();
		 list[i] = input;
		}

		long start = System.currentTimeMillis();

		System.out.println(Arrays.toString(list));

		int sorted[];
		
		sorted = mergeSort(list);
		System.out.println(Arrays.toString(sorted));
		System.out.println("Merge Sort");
		System.out.println("Number of Key Comparison: " + keyComparison);
		System.out.println("Size of Array: " + list.length);

		keyComparison = 0;

		sorted = mergeSortWithInsertion(list);
		System.out.println(Arrays.toString(sorted));
		System.out.println("Merge with Insertion Sort");
		System.out.println("Number of Key Comparison: " + keyComparison);
		System.out.println("Size of Array: " + list.length);

		long end = System.currentTimeMillis();

		System.out.println("CPU time used: " + (end - start));
	}

}
