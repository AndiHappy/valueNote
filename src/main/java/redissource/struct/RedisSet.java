package redissource.struct;

public class RedisSet {
/**
 
 第一个添加到集合的元素， 决定了创建集合时所使用的编码：

如果第一个元素可以表示为 long long 类型值（也即是，它是一个整数），
那么集合的初始编码为 REDIS_ENCODING_INTSET 。
否则，集合的初始编码为 REDIS_ENCODING_HT 。



如果一个集合使用 REDIS_ENCODING_INTSET 编码， 那么当以下任何一个条件被满足时， 
这个集合会被转换成 REDIS_ENCODING_HT 编码：

intset 保存的整数值个数超过 server.set_max_intset_entries （默认值为 512 ）。
试图往集合里添加一个新元素，并且这个元素不能被表示为 long long 类型（也即是，它不是一个整数）。

 
 * **/
	public static void main(String[] args){
		//Redis 集合类型命令的实现， 主要是对 intset 和 dict 两个数据结构的操作函数的包装， 
		//以及一些在两种编码之间进行转换的函数
	}
	
	/**
	 * set intersection operation
	 *

# coding: utf-8
def sinter(*multi_set):
    # 根据集合的基数进行排序
    sorted_multi_set = sorted(multi_set, lambda x, y: len(x) - len(y))
    # 使用基数最小的集合作为基础结果集，有助于降低常数项
    result = sorted_multi_set[0].copy()

    # 剔除所有在 sorted_multi_set[0] 中存在
    # 但在其他某个集合中不存在的元素
    for elem in sorted_multi_set[0]:
        for s in sorted_multi_set[1:]:
            if (not elem in s):
                result.remove(elem)
                break
    return result
	 * */
	public RedisHashSet sinter(RedisHashSet set1 ,RedisHashSet set2){
		return null;
	}
}
