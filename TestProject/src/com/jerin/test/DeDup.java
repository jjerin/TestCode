package com.jerin.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DeDup {

    public int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3,
								    20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,
								    13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11};

	public int[] getRandomIntegers() {
		return randomIntegers;
	}

	public void setRandomIntegers(int[] randomIntegers) {
		this.randomIntegers = randomIntegers;
	}

	public static void main(String[] args) {
		DeDup deDup = new DeDup();
		if (deDup.getRandomIntegers() != null && deDup.getRandomIntegers().length > 0) {

			int[] uniqueArr = deDup.deDupWithoutDataStructures();
			deDup.printArray(uniqueArr);

			uniqueArr = deDup.deDupUsingSet();
			deDup.printArray(uniqueArr);

			uniqueArr = deDup.deDupMaintainOrder();
			deDup.printArray(uniqueArr);


		} else {
			System.out.println("The array is empty");
		}

		// uniqueArr = deDup.deDupUsingSet();
		// deDup.printArray(uniqueArr);
		// deDup.printArray(deDup.randomIntegers);

	}
	
	/*
	 * Method 1:
	 * ---------
	 * 
	 * This method uses a plain algorithm which iterates through the array once 
	 * to identify the duplicates.
	 * Any time a duplicate is identified, it is replaced with the last number
	 * in the list (ignoring the previously replaced numbers in the end)
	 * Cons:
	 *   Will not be ideal if the number of items is huge, as the number of operations
	 *   may be (n-1)! in the scenario of all unique numbers.
	 */
	public int[] deDupWithoutDataStructures() {
		int arrayLength = randomIntegers.length;
		for (int i = 0; i < arrayLength; i++) {
			for (int j = i + 1; j < arrayLength; j++) {
				if (randomIntegers[i] == randomIntegers[j]) {
					// Duplicate found.

					// Replace the duplicate with the last element in the array.
					arrayLength = replaceWithLastElement(j, arrayLength);
				}
			}
		}
		int[] newIntArr = new int[arrayLength];
		newIntArr = Arrays.copyOf(randomIntegers, arrayLength);
		return newIntArr;
	}

	/*
	 * Utility method for deDupWithoutDataStructures()
	 */
	private int replaceWithLastElement(int elementIdx, int arrayLength) {
		if (arrayLength == elementIdx){
			return arrayLength;
		}

		if (randomIntegers[elementIdx] == randomIntegers[arrayLength - 1]) {
			arrayLength = replaceWithLastElement(elementIdx, (arrayLength - 1));
		} else {
			randomIntegers[elementIdx] = randomIntegers[arrayLength - 1];
		}
		return arrayLength;
	}

	/*
	 * Method 2:
	 * ---------
	 * 
	 * Utilizing the non-duplicate behavior of the Set data structure.
	 * HashSet is used for the performance of the Hashing algorithm of the 
	 * set in identifying duplicates.
	 * 
	 * The Elements are just added to the set during which the duplicate
	 * elements are removed.  Then the values are returned.
	 * 
	 * This would be the preferred method to remove duplicates.
	 * 
	 */
	public int[] deDupUsingSet() {
		Set<Integer> intSet = new HashSet<Integer>();
		for (int i : randomIntegers) {
			intSet.add(i);
		}

		int[] uniqueArr = new int[intSet.size()];

		int idx = 0;
		for (Integer num : intSet) {
			uniqueArr[idx] = num;
			idx++;
		}

		return uniqueArr;
	}

	/*
	 * Method 3:
	 * ----------
	 * Method to remove duplicates but still maintain the original order.
	 * 
	 * Here again the unique list of numbers is maintained in a HashSet, while 
	 * the arrays is iterated only once and compared with the Set to identify 
	 * whether a particular number is duplicated.
	 */
	public int[] deDupMaintainOrder() {
		Set<Integer> uniqueList = new HashSet<Integer>();
		int[] uniqueArr = new int[randomIntegers.length];
		int index = 0;
		for (int num : randomIntegers) {
			if (!uniqueList.contains(num)) {
				uniqueArr[index] = num;
				uniqueList.add(num);
				index++;
			}
		}
		uniqueArr = Arrays.copyOf(uniqueArr, index);
		return uniqueArr;
	}


	/*
	 * Method to print the array.
	 */
	private void printArray(int[] intArr) {
		for (int num : intArr) {
			System.out.print(num + " ");
		}
		System.out.println("");
	}

}
