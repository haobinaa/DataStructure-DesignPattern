## 字符集

### 字符的存储

计算机存储的都是二进制，所以如果要存储字符，就需要将字符映射成二进制，映射的规则就是字符集

#### 常用的字符集

- ASCII字符集：共收录128个字符，包括空格、标点符号、数字、大小写字母和一些不可见字符。由于总共才128个字符，所以可以使用1个字节来进行编码

- GBK字符集：收录了汉字以及拉丁字母、希腊字母、日文平假名及片假名字母、俄语西里尔字母。同时也兼容了 ASCII 字符集。它的编码长度是：
    - 如果该字符在ASCII字符集中，则采用1字节编码
    - 否则采用2字节编码

- utf8字符集：收录地球上能想到的所有字符，而且还在不断扩充。这种字符集兼容ASCII字符集，采用变长编码方式，编码一个字符需要使用1～4个字节

#### MySQL中的utf8和utf8m64

utf8字符集表示一个字符需要使用1～4个字节，但是我们常用的一些字符使用1～3个字节就可以表示了。

由于字符集中一个字符的最大长度会影响mysql的存储性能，mysql就定义了两个概念:

- utf8mb3(也就是utf8)：阉割过的utf8字符集，只使用1～3个字节表示字符
- utf8mb4：正宗的utf8字符集，使用1～4个字节表示字符

一般又四个字节编码一个字符的时候，就需要采用utf8mb64,如 emoji 表情