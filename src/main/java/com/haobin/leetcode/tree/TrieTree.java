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

    /**
     * Trie Tree
     * 1. 最多有 R 个指向子节点的连接
     * 2. 含有布尔字段，表示对应节点的字段是结尾还是前缀
     */
    static class TrieTreeNode {
        // R 个 子节点
        private TrieTreeNode[] links;
        // 这里 R 是 26 个字母
        private final int R = 26;
        private boolean isEnd;

        TrieTreeNode() {
            this.links = new TrieTreeNode[R];
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieTreeNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieTreeNode node) {
            links[ch - 'a'] = node;
        }

        boolean isEnd() {
            return isEnd;
        }

        void setEnd() {
            isEnd = true;
        }
    }

    private TrieTreeNode root;

    public TrieTree() {
        this.root = new TrieTreeNode();
    }

    /**
     * 通过搜索 trie tree 来插入一个键
     * 1. 从 root 开始搜索对应第一个键字符的连接
     * 2. 如果连接存在，沿着连接移动到下一层，继续搜索下一个键的字符
     * 3. 如果连接不存在，创建一个新节点，并与父节点链接相连
     * 4. 重复 2，3 直到达到键的最后一个字符，标记当前节点为结束
     */
    public void insert(String word) {
        TrieTreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieTreeNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    /**
     * 1. 若存在连接，移动到该连接后一个节点，搜索下一个字符
     * 2. 若不存在连接，若无字符已经搜索完毕并且当前节点标记 isEnd,则返回true
     * 2.1 若还存在剩余字符，但无法跟随 Trie Tree 找不到键， 返回 false
     * 2.2 没有剩余字符，但是没有标记 isEnd， 则只是 Trie 树中的另一个键的前缀，返回 false
     */
    public boolean search(String word) {
        TrieTreeNode node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    private TrieTreeNode searchPrefix(String word) {
        TrieTreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (node.containsKey(currentChar)) {
                node = node.get(currentChar);
            } else {
                return null;
            }
        }
        return node;
    }

    /**
     * 查找键前缀
     * 该方法与在 Trie 树中搜索键时使用的方法非常相似。我们从根遍历 Trie 树，直到键前缀中没有字符，或者无法用当前的键字符继续 Trie 中的路径。
     * 与上面提到的“搜索键”算法唯一的区别是，到达键前缀的末尾时，总是返回 true。
     * 我们不需要考虑当前 Trie 节点是否用 “isend” 标记，因为我们搜索的是键的前缀，而不是整个键
     *
     */
    public boolean startWith(String prefix) {
        TrieTreeNode node = searchPrefix(prefix);
        return node != null;
    }
}
