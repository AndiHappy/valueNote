package redissource.ziplist;

/**
 * @author zhailz
 * 2019年2月25日 下午3:24:04
 */


/**

Ziplist 是由一系列特殊编码的内存块构成的列表， 一个 ziplist 可以包含多个节点（entry）， 每个节点可以保存一个长度受限的字符数组（不以 \0 结尾的 char 数组）
或者整数， 包括：

字符数组
长度小于等于 63 （26−1）字节的字符数组
长度小于等于 16383 （214−1） 字节的字符数组
长度小于等于 4294967295 （232−1）字节的字符数组
整数
4 位长，介于 0 至 12 之间的无符号整数
1 字节长，有符号整数
3 字节长，有符号整数
int16_t 类型整数
int32_t 类型整数
int64_t 类型整数

 * */

/**
area        |<---- ziplist header ---->|<----------- entries ------------->|<-end->|

size          4 bytes  4 bytes  2 bytes    ?        ?        ?        ?     1 byte
            +---------+--------+-------+--------+--------+--------+--------+-------+
component   | zlbytes | zltail | zllen | entry1 | entry2 |  ...   | entryN | zlend |
            +---------+--------+-------+--------+--------+--------+--------+-------+
                                       ^                          ^        ^
address                                |                          |        |
                                ZIPLIST_ENTRY_HEAD                |   ZIPLIST_ENTRY_END
                                                                  |
                                                         ZIPLIST_ENTRY_TAIL
 
 * 
 * 
 * 
unsigned char *ziplistNew(void) {

    // ZIPLIST_HEADER_SIZE 是 ziplist 表头的大小
    // 1 字节是表末端 ZIP_END 的大小
    unsigned int bytes = ZIPLIST_HEADER_SIZE+1;

    // 为表头和表末端分配空间
    unsigned char *zl = zmalloc(bytes);

    // 初始化表属性
    ZIPLIST_BYTES(zl) = intrev32ifbe(bytes);
    ZIPLIST_TAIL_OFFSET(zl) = intrev32ifbe(ZIPLIST_HEADER_SIZE);
    ZIPLIST_LENGTH(zl) = 0;

    // 设置表末端
    zl[bytes-1] = ZIP_END;

    return zl;
}
 * 
 * 
 * 
area        |<---- ziplist header ---->|<-- end -->|

size          4 bytes   4 bytes 2 bytes  1 byte
            +---------+--------+-------+-----------+
component   | zlbytes | zltail | zllen | zlend     |
            |         |        |       |           |
value       |  1011   |  1010  |   0   | 1111 1111 |
            +---------+--------+-------+-----------+
                                       ^
                                       |
                               ZIPLIST_ENTRY_HEAD
                                       &
address                        ZIPLIST_ENTRY_TAIL
                                       &
                               ZIPLIST_ENTRY_END 
                               
 *
 *                             
 *                             
 *                             
 *                             
增加一个元素的样例
 
area        |<---- ziplist header ---->|<------------ entries ------------>|<-- end -->|

size          4 bytes  4 bytes  2 bytes  5 bytes          13 bytes           1 bytes
            +---------+--------+-------+----------------+------------------+-----------+
component   | zlbytes | zltail | zllen | entry 1        | entry 2          | zlend     |
            |         |        |       |                |                  |           |
value       |  11101  |  1111  |  10   | ?              | pre_entry_length | 1111 1111 |
            |         |        |       |                | 101              |           |
            |         |        |       |                |                  |           |
            |         |        |       |                | encoding         |           |
            |         |        |       |                | 00               |           |
            |         |        |       |                |                  |           |
            |         |        |       |                | length           |           |
            |         |        |       |                | 001011           |           |
            |         |        |       |                |                  |           |
            |         |        |       |                | content          |           |
            |         |        |       |                | hello world      |           |
            |         |        |       |                |                  |           |
            +---------+--------+-------+----------------+------------------+-----------+
                                       ^                ^                  ^
                                       |                |                  |
address                                |          ZIPLIST_ENTRY_TAIL   ZIPLIST_ENTRY_END
                                       |
                               ZIPLIST_ENTRY_HEAD
                           
 * */


public class ZipList {

}
