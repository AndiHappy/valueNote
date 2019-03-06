package redissource.struct;

/**
 * @author zhailz
 * 2019年2月25日 下午3:52:44
 */
public class RedisObject {
/**
typedef struct redisObject {

    // 类型
    unsigned type:4;

    // 对齐位
    unsigned notused:2;

    // 编码方式
    unsigned encoding:4;

    // LRU 时间（相对于 server.lruclock）
    unsigned lru:22;

    // 引用计数
    int refcount;

    // 指向对象的值
    void *ptr;

} robj; 

type 记录了对象所保存的值的类型，它的值可能是以下常量的其中一个（定义位于 redis.h）：
#define REDIS_STRING 0  // 字符串
#define REDIS_LIST 1    // 列表
#define REDIS_SET 2     // 集合
#define REDIS_ZSET 3    // 有序集
#define REDIS_HASH 4    // 哈希表


encoding 记录了对象所保存的值的编码，它的值可能是以下常量的其中一个（定义位于 redis.h）：
#define REDIS_ENCODING_RAW 0            // 编码为字符串
#define REDIS_ENCODING_INT 1            // 编码为整数
#define REDIS_ENCODING_HT 2             // 编码为哈希表
#define REDIS_ENCODING_ZIPMAP 3         // 编码为 zipmap
#define REDIS_ENCODING_LINKEDLIST 4     // 编码为双端链表
#define REDIS_ENCODING_ZIPLIST 5        // 编码为压缩列表
#define REDIS_ENCODING_INTSET 6         // 编码为整数集合
#define REDIS_ENCODING_SKIPLIST 7       // 编码为跳跃表
ptr 是一个指针，指向实际保存值的数据结构，这个数据结构由 type 属性和 encoding 属性决定。



字符串类型分别使用 REDIS_ENCODING_INT 和 REDIS_ENCODING_RAW 两种编码：
REDIS_ENCODING_INT 使用 long 类型来保存 long 类型值。
REDIS_ENCODING_RAW 则使用 sdshdr 结构来保存 sds （也即是 char* )、 long long 、 double 和 long double 类型值。
换句话来说， 在 Redis 中， 只有能表示为 long 类型的值， 才会以整数的形式保存， 其他类型的整数、小数和字符串， 都是用 sdshdr 结构来保存。
 * */
}
