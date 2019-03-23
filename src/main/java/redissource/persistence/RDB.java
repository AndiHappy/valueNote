package redissource.persistence;

/**
 
################################ SNAPSHOTTING  ################################
#
# Save the DB on disk:
#
#   save <seconds> <changes>
#
#   Will save the DB if both the given number of seconds and the given
#   number of write operations against the DB occurred.
#
#   In the example below the behaviour will be to save:
#   after 900 sec (15 min) if at least 1 key changed
#   after 300 sec (5 min) if at least 10 keys changed
#   after 60 sec if at least 10000 keys changed
#
#   Note: you can disable saving completely by commenting out all "save" lines.
#
#   It is also possible to remove all the previously configured save
#   points by adding a save directive with a single empty string argument
#   like in the following example:
#
#   save ""

save 900 1
save 300 10
save 60 10000


 * */

// RDB 功能最核心的是 rdbSave 和 rdbLoad 两个函数， 
// rdbSave用于生成 RDB 文件到磁盘， 
// rdbLoad用于将 RDB 文件中的数据重新载入到内存中


/***

rdbSave 会将数据库数据保存到 RDB 文件，并在保存完成之前阻塞调用者。

SAVE 命令直接调用 rdbSave ，阻塞 Redis 主进程； BGSAVE 用子进程调用 rdbSave ，主进程仍可继续处理命令请求。

SAVE 执行期间， AOF 写入可以在后台线程进行， BGREWRITEAOF 可以在子进程进行，所以这三种操作可以同时进行。

为了避免产生竞争条件， BGSAVE 执行时， SAVE 命令不能执行。

为了避免性能问题， BGSAVE 和 BGREWRITEAOF 不能同时执行。

调用 rdbLoad 函数载入 RDB 文件时，不能进行任何和数据库相关的操作，不过订阅与发布方面的命令可以正常执行，因为它们和数据库不相关联。

RDB 文件的组织方式如下：

+-------+-------------+-----------+-----------------+-----+-----------+
| REDIS | RDB-VERSION | SELECT-DB | KEY-VALUE-PAIRS | EOF | CHECK-SUM |
+-------+-------------+-----------+-----------------+-----+-----------+

                      |<-------- DB-DATA ---------->|
键值对在 RDB 文件中的组织方式如下：

+----------------------+---------------+-----+-------+
| OPTIONAL-EXPIRE-TIME | TYPE-OF-VALUE | KEY | VALUE |
+----------------------+---------------+-----+-------+
RDB 文件使用不同的格式来保存不同类型的值。

 * */
public class RDB {

}
