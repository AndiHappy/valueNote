package redissource.redisdb;

/**
 * @author guizhai
 *
 */
public class RedisDB {

	/**
	
	typedef struct redisDb {
	
	// 保存着数据库以整数表示的号码
	int id;
	
	// 保存着数据库中的所有键值对数据
	// 这个属性也被称为键空间（key space）
	dict *dict;
	
	// 保存着键的过期信息
	dict *expires;
	
	// 实现列表阻塞原语，如 BLPOP
	// 在列表类型一章有详细的讨论
	dict *blocking_keys;
	dict *ready_keys;
	
	// 用于实现 WATCH 命令
	// 在事务章节有详细的讨论
	dict *watched_keys;
	
	} redisDb;
	
	 */
	
	// 通过数据库的定义，我们可以知道：
	// 因为数据库本身是一个字典， 所以对数据库的操作基本上都是对字典的操作
	
	
	public void expire(long expireTime) {
		/*
		
		在数据库中， 所有键的过期时间都被保存在 redisDb 结构的 expires 字典里：

		typedef struct redisDb {

		    // ...

		    dict *expires;

		    // ...

		} redisDb;
		
		expires 字典的键是一个指向 dict 字典（键空间）里某个键的指针， 而字典的值则是键所指向的数据库键的到期时间， 
		这个值以 long long 类型表示。
		
**********  实现的算法  ************		
def is_expired(key):
    # 取出键的过期时间
    key_expire_time = expires.get(key)
    # 如果过期时间不为空，并且当前时间戳大于过期时间，那么键已经过期
    if expire_time is not None and current_timestamp() > key_expire_time:
        return True
    # 否则，键未过期或没有设置过期时间
    return False
    
		*/
	}
	
	/**

删除的策略：

定时删除：在设置键的过期时间时，创建一个定时事件，当过期时间到达时，由事件处理器自动执行键的删除操作。
惰性删除：放任键过期不管，但是在每次从 dict 字典中取出键值时，要检查键是否过期，如果过期的话，就删除它，并返回空；如果没过期，就返回键值。
定期删除：每隔一段时间，对 expires 字典进行检查，删除里面的过期键。 
	 * */
	public void del(String key) {
		/**
1. 惰性删除


惰性删除对 CPU 时间来说是最友好的： 它只会在取出键时进行检查， 这可以保证删除操作只会在非做不可的情况下进行 
—— 并且删除的目标仅限于当前处理的键， 这个策略不会在删除其他无关的过期键上花费任何 CPU 时间。
惰性删除的缺点是， 它对内存是最不友好的： 如果一个键已经过期， 而这个键又仍然保留在数据库中， 
那么 dict 字典和 expires 字典都需要继续保存这个键的信息， 只要这个过期键不被删除， 它占用的内存就不会被释放。

在使用惰性删除策略时， 如果数据库中有非常多的过期键， 但这些过期键又正好没有被访问的话， 
那么它们就永远也不会被删除（除非用户手动执行）， 这对于性能非常依赖于内存大小的 Redis 来说， 肯定不是一个好消息。

举个例子， 对于一些按时间点来更新的数据， 比如日志（log）， 
在某个时间点之后， 对它们的访问就会大大减少， 如果大量的这些过期数据积压在数据库里面，
 用户以为它们已经过期了（已经被删除了）， 但实际上这些键却没有真正的被删除（内存也没有被释放）， 那结果肯定是非常糟糕。

2. 定期删除

从上面对定时删除和惰性删除的讨论来看， 这两种删除方式在单一使用时都有明显的缺陷：
定时删除占用太多 CPU 时间， 惰性删除浪费太多内存。

定期删除是这两种策略的一种折中：
它每隔一段时间执行一次删除操作，并通过限制删除操作执行的时长和频率，籍此来减少删除操作对 CPU 时间的影响。
另一方面，通过定期删除过期键，它有效地减少了因惰性删除而带来的内存浪费。

Redis 使用的过期键删除策略是惰性删除加上定期删除， 这两个策略相互配合，
可以很好地在合理利用 CPU 时间和节约内存空间之间取得平衡。
		 * **/
		
		/***
		 * 
1. 更新后的 RDB 文件
在创建新的 RDB 文件时，程序会对键进行检查，过期的键不会被写入到更新后的 RDB 文件中。
因此，过期键对更新后的 RDB 文件没有影响。

2. AOF 文件
在键已经过期，但是还没有被惰性删除或者定期删除之前，这个键不会产生任何影响，AOF 文件也不会因为这个键而被修改。
当过期键被惰性删除、或者定期删除之后，程序会向 AOF 文件追加一条 DEL 命令，来显式地记录该键已被删除。
举个例子， 如果客户端使用 GET message 试图访问 message 键的值， 但 message 已经过期了， 那么服务器执行以下三个动作：

从数据库中删除 message ；
追加一条 DEL message 命令到 AOF 文件；
向客户端返回 NIL 。


3. AOF 重写
和 RDB 文件类似， 当进行 AOF 重写时， 程序会对键进行检查， 过期的键不会被保存到重写后的 AOF 文件。
因此，过期键对重写后的 AOF 文件没有影响。 

		 * */
	}
	
	public static void main(String[] args) {
		/**
		 * 总结：
数据库主要由 dict 和 expires 两个字典构成，其中 dict 保存键值对，而 expires 则保存键的过期时间。

数据库的键总是一个字符串对象，而值可以是任意一种 Redis 数据类型，包括字符串、哈希、集合、列表和有序集。

expires 的某个键和 dict 的某个键共同指向同一个字符串对象，而 expires 键的值则是该键以毫秒计算的 UNIX 过期时间戳。

Redis 使用惰性删除和定期删除两种策略来删除过期的键。

更新后的 RDB 文件和重写后的 AOF 文件都不会保留已经过期的键。

当一个过期键被删除之后，程序会追加一条新的 DEL 命令到现有 AOF 文件末尾。

当主节点删除一个过期键之后，它会显式地发送一条 DEL 命令到所有附属节点。

附属节点即使发现过期键，也不会自作主张地删除它，而是等待主节点发来 DEL 命令，这样可以保证主节点和附属节点的数据总是一致的。

数据库的 dict 字典和 expires 字典的扩展策略和普通字典一样。它们的收缩策略是：当节点的填充百分比不足 10% 时，将可用节点数量减少至大于等于当前已用节点数量。

		 * 
		 * 
		 * */

	}

}
