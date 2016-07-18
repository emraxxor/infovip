/*
 * Copyright (C) 2016 attila
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.infovip.useful;

import org.apache.lucene.util.ArrayUtil;

/**
 * Basically it contains some useful functions that could be very useful in the
 * future. The class is given the Number name because it contains functions that
 * are typically used for numeric instructions.
 *
 * @author attila
 */
public class Number {

    /**
     * It simple reverses the given number
     *
     * @param number
     * @return
     */
    public static int reverseNumber(int number) {
        int reverse = 0;
        while (number != 0) {
            reverse = (reverse * 10) + (number % 10);
            number = number / 10;
        }
        return reverse;
    }

    /**
     * Converts decimal number to binary then it returns with it as a string
     *
     * @param number
     */
    public static String decToBinary(int number) {
        StringBuilder b = new StringBuilder();

        while (number > 0) {
            b.append(number % 2);
            number = number / 2;
        }

        return b.reverse().toString();
    }

    /**
     * A basic bubble sort implementation
     *
     * @param array
     */
    public static void bubbleSort(int array[]) {
        int n = array.length;
        int next;
        for (int i = n; i >= 0; i--) {
            for (int j = 0; j < n - 1; j++) {
                next = j + 1;
                if (array[j] > array[next]) {
                    swap(j, next, array);
                }
            }
        }
    }

    /**
     * A basic insertion sort implementation
     *
     * @param array
     */
    public static void insertionSort(int array[]) {
        int n = array.length;
        for (int j = 1; j < n; j++) {
            int current = array[j];
            int prev = j - 1;
            while ((prev > -1) && (array[prev] > current)) {
                array[prev + 1] = array[prev];
                prev--;
            }
            array[prev + 1] = current;
        }
    }

    /**
     * A simple palindrome implementation This function tests the equality if a
     * number and its reverse.
     *
     * @param num
     * @return
     */
    public static boolean isPalindrome(int num) {
        return reverseNumber(num) == num;
    }

    /**
     * Swaps the given elements in the given array
     *
     * @param i
     * @param j
     * @param array
     */
    private static void swap(int i, int j, int[] array) {
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Converts the given char array to number
     *
     * 0x30 -> '0'.encode('hex') it can  be (int)'c' too
     *
     * @param array
     * @return
     */
    public static int stringToNumber(char[] array) {
        int sum = 0;
        for (char c : array) {
            sum = (sum * 10) + (((int) c) - 0x30);
        }
        return sum;
    }

    /*
     * This function finds  the Greatest Common Divisor of two numbers using Euclid’s method. 
     * @return 
     */
    private static int findGCD(int num1, int num2) {
        //base case
        if (num2 == 0) {
            return num1;
        }
        return findGCD(num2, num1 % num2);
    }

    /**
     * Checks if the given number is binary or not
     *
     * @param number
     * @return
     */
    public static boolean isBinary(int number) {
        while (number != 0) {
            if (number % 10 > 1) {
                return false;
            }
            number = number / 10;
        }
        return true;
    }

}
