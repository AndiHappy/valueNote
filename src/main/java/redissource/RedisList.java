package redissource;

/**
 * @author zhailz
 * 2019年2月25日 下午4:56:19
 */
public class RedisList {

	/***
	 * 
	 REDIS_LIST （列表）是 LPUSH 、 LRANGE 等命令的操作对象， 它使用 REDIS_ENCODING_ZIPLIST 和 REDIS_ENCODING_LINKEDLIST 这两种方式编码
	 
	 同样是压缩表和双端链表来进行实现
	 
	 创建新列表时 Redis 默认使用 REDIS_ENCODING_ZIPLIST 编码， 当以下任意一个条件被满足时， 列表会被转换成 REDIS_ENCODING_LINKEDLIST 编码：
     试图往列表新添加一个字符串值，且这个字符串的长度超过 server.list_max_ziplist_value （默认值为 64 ）。
     ziplist 包含的节点超过 server.list_max_ziplist_entries （默认值为 512 ）。

	 
	 
	 * */
}
