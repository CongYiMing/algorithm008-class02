package com.yim;

import java.util.*;

public class HomeWork2 {
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static void main(String[] args) {
        isAnagram_main();
        twoSum_main();
        preorder_main();
    }

    public static void preorder_main() {
        Node node6 = new Node(6, null);
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, null);
        Node node2 = new Node(2, null);
        List<Node> children3 = new ArrayList<>();
        children3.add(node5);
        children3.add(node6);
        Node node3 = new Node(3, children3);
        List<Node> children1 = new ArrayList<>();
        children1.add(node3);
        children1.add(node2);
        children1.add(node4);
        Node root = new Node(1, children1);
        List<Integer> rtn = new ArrayList<>();
        preorder(root, rtn);
        p(rtn.toString());
    }

    public static List<Integer> preorder(Node root, List<Integer> rtn) {
        if (root != null) {
            rtn.add(root.val);
            List<Node> children = root.children;
            if (children != null && children.size() > 0) {
                for (Node n : children) {
                    preorder(n, rtn);
                }
            }
        }
        return rtn;
    }

    public static void twoSum_main() {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] ints = twoSum1(nums, target);
        p(Arrays.toString(ints));
    }

    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return null;
    }


    private static void isAnagram_main() {
        String s = "anagram";
        String t = "nagaram";
//        String s = "rat"; String t = "car";
        boolean anagram = isAnagram1(s, t);
        p(anagram);
    }

    private static boolean isAnagram1(String s, String t) {
        if (null == s && null == t) return true;
        if (null == s || null == t) return false;
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isAnagram(String s, String t) {
        if (null == s && null == t) return true;
        if (null == s || null == t) return false;
        if (s.length() != t.length()) {
            return false;
        } else {
            char[] chars = s.toCharArray();
            char[] chart = t.toCharArray();
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < chars.length; i++) {
                Character sChar = chars[i];
                Integer count = map.get(sChar);
                if (null == count) {
                    map.put(sChar, 1);
                } else {
                    int newCount = count + 1;
                    if (newCount == 0) {
                        map.remove(sChar);
                    } else {
                        map.put(sChar, newCount);
                    }
                }

                Character tChar = chart[i];
                Integer count2 = map.get(tChar);
                if (null == count2) {
                    map.put(tChar, -1);
                } else {
                    int newCount = count2 - 1;
                    if (newCount == 0) {
                        map.remove(tChar);
                    } else {
                        map.put(tChar, newCount);
                    }
                }
            }
            return map.size() == 0;
        }
    }

    private static void p(boolean param) {
        p(String.valueOf(param));
    }

    private static void p(String param) {
        System.out.println("rtn:" + param);
    }
}
