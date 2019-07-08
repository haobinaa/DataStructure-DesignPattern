### 防止表单重复提交

获取表单信息的时候，客户端先申请token, 并存入redis:
```
/**
     * 先生成 token 保存到 Redis
     * token 作为 key , 并设置过期时间 时间长度 根据任务需求
     * value 为数字 自增判断 是否使用过
     *
     * @param user
     * @return
     */
public String createToken(User user) {
	String key = "placeOrder:token:" + user.getId();
	String token = UUID.randomUUID().toString();
	//保存到Redis
	redisService.set(key + token, 0, 1000L);
	return token;
}
```

提交表单的时候，客户端也传入token，后台先校验token是否有效。若无效则返回表单已过期:
```
/**
     * 校验下单的token是否有效
     * @param user
     * @param token
     * @return
     */
public Boolean checkToken(User user, String token) {
	String key = "placeOrder:token:" + user.getId();
	if (null != redisService.get(key + token)) {
		long times = redisService.increment(key + token, 1);
		if (times == 1) {
			//利用increment 原子性 判断是否 该token 是否使用,防止并发情况下出现问题
			return true;
		} else {
			// 已经使用过了
		}
		//删除
		redisService.remove(key + token);
	}
	return false;
}
```

### 安全减库存

[安全减库存](https://juejin.im/post/5d1efa446fb9a07f0870b50b)