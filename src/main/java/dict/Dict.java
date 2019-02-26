package dict;

import java.util.List;

/**
 * @author zhailz
 * 2019年2月25日 上午11:03:17
 */

///*
// * 哈希表节点
// */
//typedef struct dictEntry {
//    
//    // 键
//    void *key;
//
//    // 值
//    union {
//        void *val;
//        uint64_t u64;
//        int64_t s64;
//    } v;
//
//    // 指向下个哈希表节点，形成链表
//    struct dictEntry *next;
//
//} dictEntry;

// hash表的节点
class DictEntry {
	Object key;
	Object value;
	DictEntry next;
}

/*
 * 哈希表
 *
 * 每个字典都使用两个哈希表，从而实现渐进式 rehash 。
 */
//typedef struct dictht {
//    
//    // 哈希表数组
//    dictEntry **table;
//
//    // 哈希表大小
//    unsigned long size;
//    
//    // 哈希表大小掩码，用于计算索引值
//    // 总是等于 size - 1
//    unsigned long sizemask;
//
//    // 该哈希表已有节点的数量
//    unsigned long used;
//
//} dictht;
// dictht 使用链地址法来处理键碰撞： 当多个不同的键拥有相同的哈希值时，哈希表用一个链表将这些键连接起来。
class Dictht {
	List<DictEntry> table;
	long size;
	long sizemask;;
	long used;
}

/*
 * 字典
 */
//typedef struct dict {
//
//    // 类型特定函数
//    dictType *type;
//
//    // 私有数据
//    void *privdata;
//
//    // 哈希表
//    dictht ht[2];
//
//    // rehash 索引
//    // 当 rehash 不在进行时，值为 -1
//    int rehashidx; /* rehashing not in progress if rehashidx == -1 */
//
//    // 目前正在运行的安全迭代器的数量
//    int iterators; /* number of iterators currently running */
//
//} dict;

public class Dict {
    //0 号哈希表（ht[0]）是字典主要使用的哈希表， 而 1 号哈希表（ht[1]）则只有在程序对 0 号哈希表进行 rehash 时才使用
    //	ht[0]->table 的空间分配将在第一次往字典添加键值对时进行；
    //	ht[1]->table 的空间分配将在 rehash 开始时进行；

	Dictht[] ht = new Dictht[2];
	// rehash 索引
	// 当 rehash 不在进行时，值为 -1
	int rehashidx; /* rehashing not in progress if rehashidx == -1 */

	// 目前正在运行的安全迭代器的数量
	int iterators; /* number of iterators currently running */

}

/**
 def iter_dict(dict):

    # 迭代 0 号哈希表
    iter_table(ht[0]->table)

    # 如果正在执行 rehash ，那么也迭代 1 号哈希表
    if dict.is_rehashing(): iter_table(ht[1]->table)


def iter_table(table):

    # 遍历哈希表上的所有索引
    for index in table:

        # 跳过空索引
        if table[index].empty():
            continue

        # 遍历索引上的所有节点
        for node in table[index]:

            # 处理节点
            do_something_with(node) 
 * */

///*
// * 字典迭代器
// */
//typedef struct dictIterator {
//
//    dict *d;                // 正在迭代的字典
//
//    int table,              // 正在迭代的哈希表的号码（0 或者 1）
//        index,              // 正在迭代的哈希表数组的索引
//        safe;               // 是否安全？
//
//    dictEntry *entry,       // 当前哈希节点
//              *nextEntry;   // 当前哈希节点的后继节点
//
//} dictIterator;



