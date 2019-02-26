package redissource.skiplist;

/**
 * @author zhailz
 * 2019年2月25日 下午2:08:24
 */

/*
 * 保存 ziplist 节点信息的结构
 */

/*
typedef struct zskiplist {

    // 头节点，尾节点
    struct zskiplistNode *header, *tail;

    // 节点数量
    unsigned long length;

    // 目前表内节点的最大层数
    int level;

} zskiplist

typedef struct zskiplistNode {

    // member 对象
    robj *obj;

    // 分值
    double score;

    // 后退指针
    struct zskiplistNode *backward;

    // 层
    struct zskiplistLevel {

        // 前进指针
        struct zskiplistNode *forward;

        // 这个层跨越的节点数量
        unsigned int span;

    } level[];

} zskiplistNode;
*/

class zskiplist {
	zskiplistNode header, tail;
	long length;
	int level;
}

class zskiplistNode {
   Object obj;
   double score;
   zskiplistNode back;
   zskiplistLevel[] level;
}

class zskiplistLevel{
	zskiplistNode forward;
	//这个层跨越的节点数量
	int span;
}

/**
跳跃表是一种随机化数据结构，查找、添加、删除操作都可以在对数期望时间下完成。

跳跃表目前在 Redis 的唯一作用，就是作为有序集类型的底层数据结构（之一，另一个构成有序集的结构是字典）。

为了满足自身的需求，Redis 基于 William Pugh 论文中描述的跳跃表进行了修改，包括：

score 值可重复。

对比一个元素需要同时检查它的 score 和 memeber 。

每个节点带有高度为 1 层的后退指针，用于从表尾方向向表头方向迭代。

 * */
public class SkipListRedis {

}
