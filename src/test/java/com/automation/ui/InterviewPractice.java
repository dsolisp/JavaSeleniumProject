package com.automation.ui;

import org.openqa.selenium.By;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * INTERVIEW PRACTICE — pure Java, no Selenium, no Maven needed.
 *
 * How to run (any of these):
 *   1) Right-click in IDE  -> Run 'InterviewPractice.main()'
 *   2) Terminal (Java 11+): java InterviewPractice.java   (from this folder)
 *
 * Read each method top-to-bottom. Try to solve it yourself FIRST by emptying
 * the body, then compare with the solution below.
 */
public class InterviewPractice {

    public static void main(String[] args) {
        System.out.println("=== 1. FizzBuzz (1..15) ===");
        fizzBuzz(15);

        System.out.println("\n=== 2. Reverse string ===");
        System.out.println(reverse("interview"));          // weivretni

        System.out.println("\n=== 3. Palindrome ===");
        System.out.println(isPalindrome("racecar"));        // true
        System.out.println(isPalindrome("hello"));          // false

        System.out.println("\n=== 4. Char frequency ===");
        System.out.println(charCount("banana"));            // {b=1, a=3, n=2}

        System.out.println("\n=== 5. Find duplicates ===");
        System.out.println(findDuplicates(new int[]{1, 2, 3, 2, 5, 1})); // [1, 2]

        System.out.println("\n=== 6. Two sum (target 9) ===");
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9))); // [0, 1]

        System.out.println("\n=== 7. Fibonacci (first 10) ===");
        for (int i = 0; i < 10; i++) System.out.print(fib(i) + " "); // 0 1 1 2 3 5 8 13 21 34
        System.out.println();

        System.out.println("\n=== 8. Factorial of 5 ===");
        System.out.println(factorial(5));                   // 120

        System.out.println("\n=== 9. Max in array ===");
        System.out.println(max(new int[]{3, 9, 1, 7, 4}));  // 9

        System.out.println("\n=== 10. Anagram ===");
        System.out.println(isAnagram("listen", "silent"));  // true

        System.out.println("\n=== 11. Count vowels ===");
        System.out.println(countVowels("Automation"));      // 5

        System.out.println("\n=== 12. Sum of digits ===");
        System.out.println(sumOfDigits(1234));              // 10
    }

    // 1. FizzBuzz — classic modulo + if/else chain.
    static void fizzBuzz(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) System.out.println("FizzBuzz");
            else if (i % 3 == 0) System.out.println("Fizz");
            else if (i % 5 == 0) System.out.println("Buzz");
            else System.out.println(i);
        }
    }

    // 2. Reverse a string — StringBuilder is the easy way; loop shows the logic.
    static String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    // 3. Palindrome — two-pointer from both ends.
    static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // 4. Character frequency — the bread-and-butter HashMap pattern.
    static Map<Character, Integer> charCount(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        return counts;
    }

    // 5. Find duplicates — HashSet remembers what we've already seen.
    static Set<Integer> findDuplicates(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();
        for (int n : nums) {
            if (!seen.add(n)) {   // add() returns false if already present
                duplicates.add(n);
            }
        }
        return duplicates;
    }

    // 6. Two sum — map value -> index, look for the complement (O(n)).
    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) {
                return new int[]{seen.get(need), i};
            }
            seen.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    // 7. Fibonacci — iterative, no recursion (interview-safe, no stack overflow).
    static long fib(int n) {
        if (n < 2) return n;
        long a = 0;
        long b = 1;
        for (int i = 2; i <= n; i++) {
            long next = a + b;
            a = b;
            b = next;
        }
        return b;
    }

    // 8. Factorial — simple accumulating loop (use long, ints overflow fast).
    static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // 9. Max in array — track the running maximum.
    static int max(int[] nums) {
        int best = nums[0];
        for (int n : nums) {
            if (n > best) best = n;
        }
        return best;
    }

    // 10. Anagram — same letters? Sort both and compare (clean & simple).
    static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        Arrays.sort(ca);
        Arrays.sort(cb);
        return Arrays.equals(ca, cb);
    }

    // 11. Count vowels — iterate chars, check membership in a vowel string.
    static int countVowels(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) count++;
        }
        return count;
    }

    // 12. Sum of digits — repeatedly take last digit with % 10, drop it with / 10.
    static int sumOfDigits(int n) {
        int sum = 0;
        n = Math.abs(n);
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    static void clickTable{
        Array list = driver.findElements(By.id("password")).sendKeys(PASSWORD);
        for (int i = 0; i <= list.lenght; i++) {
            for (int i = 0; i <= 10; i++) {

            }
        }
    }
}


