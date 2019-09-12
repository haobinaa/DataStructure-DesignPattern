## mysql 优化

### limit 优化

分页查询中如下：
```
SELECT * 
FROM   operation 
WHERE  type = 'SQLStats' 
       AND name = 'SlowLog' 
ORDER  BY create_time 
LIMIT  1000, 10;
```

当`limit 10000, 10` 的时候， 虽然还是只查10条但是mysql并不知道第10000条记录从何开始，在加了索引的情况下，比如对`type, name, create_time` 加上组合索引，然后使用上一页的最大值当作查询条件，就可以命中索引，快速筛选出大量数据的偏移量位置.

优化后sql语句如下:
```
SELECT * 
FROM   operation 
WHERE  type = 'SQLStats' 
       AND name = 'SlowLog' 
ORDER  BY create_time 
LIMIT  1000, 10;
```

这种查询并不会随着数据量的增长而发生变化，执行时间比较固定

#### 隐式转换

mysql中查询变量与字段类型定义不匹配是一个隐形的坑， 如:
```
mysql> explain extended SELECT * 
     > FROM   my_balance b 
     > WHERE  b.bpn = 14000000123 
     >       AND b.isverified IS NULL ;
mysql> show warnings;
| Warning | 1739 | Cannot use ref access on index 'bpn' due to type or collation conversion on field 'bpn'
```

其中字段 bpn 的定义为 varchar(20)，MySQL 的策略是将字符串转换为数字之后再比较。函数作用于表字段，索引失效。

所以这里要注意， 有时候框架会做一些转换，最后执行的sql语句中就有可能不会命中索引

