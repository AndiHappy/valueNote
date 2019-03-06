package redissource.struct;

/**
 * @author zhailz
 * 2019年2月25日 下午3:58:43
 */
public class RedisHashSet {
	/**
	 * hlen，hget 操作对应的数据结构：
	 * 
	 * 它使用 REDIS_ENCODING_ZIPLIST 和 REDIS_ENCODING_HT 两种编码方式
	 * 
	 * 分别对应的实现是压缩表和字典
	 * 
压缩列表编码的哈希表
当使用 REDIS_ENCODING_ZIPLIST 编码哈希表时， 程序通过将键和值一同推入压缩列表， 从而形成保存哈希表所需的键-值对结构：

+---------+------+------+------+------+------+------+------+------+---------+
| ZIPLIST |      |      |      |      |      |      |      |      | ZIPLIST |
| ENTRY   | key1 | val1 | key2 | val2 | ...  | ...  | keyN | valN | ENTRY   |
| HEAD    |      |      |      |      |      |      |      |      | END     |
+---------+------+------+------+------+------+------+------+------+---------+
新添加的 key-value 对会被添加到压缩列表的表尾。

当进行查找/删除或更新操作时，程序先定位到键的位置，然后再通过对键的位置来定位值的位置。

如果是是压缩表，使用遍历的方法确定key值的位置！！

创建空白哈希表时， 程序默认使用 REDIS_ENCODING_ZIPLIST 编码， 当以下任何一个条件被满足时， 
程序将编码从 REDIS_ENCODING_ZIPLIST 切换为 REDIS_ENCODING_HT ：

哈希表中某个键或某个值的长度大于 server.hash_max_ziplist_value （默认值为 64 ）。
压缩列表中的节点数量大于 server.hash_max_ziplist_entries （默认值为 512 ）。

* */

}
