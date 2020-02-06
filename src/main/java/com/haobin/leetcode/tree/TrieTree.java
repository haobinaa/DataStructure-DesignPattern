package com.haobin.leetcode.tree;

/**
 * @Author HaoBin
 * @Create 2020/2/5 17:13
 * @Description: 字典树
 *
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 **/
public class TrieTree {

    static class TireTreeNode {
        private TireTreeNode[] links;
        private final int R = 26;
    }
}
