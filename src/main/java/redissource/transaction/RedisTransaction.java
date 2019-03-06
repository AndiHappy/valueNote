/**
 * 
 */
package redissource.transaction;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author guizhai
 *
 */
public class RedisTransaction {

	/**
	事务执行的过程：
	
	def execute_transaction():
	# 创建空白的回复队列
	reply_queue = []
	# 取出事务队列里的所有命令、参数和参数数量
	for cmd, argv, argc in client.transaction_queue:
	    # 执行命令，并取得命令的返回值
	    reply = execute_redis_command(cmd, argv, argc)
	    # 将返回值追加到回复队列末尾
	    reply_queue.append(reply)
	# 清除客户端的事务状态
	clear_transaction_state(client)
	# 清空事务队列
	clear_transaction_queue(client)
	# 将事务的执行结果返回给客户端
	send_reply_to_client(client, reply_queue)
	 */

	/**
	不过事务中的命令和普通命令在执行上还是有一点区别的，其中最重要的两点是：
	
	1. 非事务状态下的命令以单个命令为单位执行，前一个命令和后一个命令的客户端不一定是同一个；
	
	而事务状态则是以一个事务为单位，执行事务队列中的所有命令：除非当前事务执行完毕，否则服务器不会中断事务，也不会执行其他客户端的其他命令。
	
	2. 在非事务状态下，执行命令所得的结果会立即被返回给客户端；
	
	而事务则是将所有命令的结果集合到回复队列，再作为 EXEC 命令的结果返回给客户端。
	
	 * */

	public static void main(String[] args) {

		Jedis jedis = new Jedis("127.0.0.1", 6379);
		Transaction multi = jedis.multi();
		multi.watch("key");
		multi.set("key", "value");
		multi.get("key");
		
		List<Object> result = multi.exec();
		System.out.println(result);
		jedis.close();
	}

	/**
	 * 
事务提供了一种将多个命令打包，然后一次性、有序地执行的机制。
事务在执行过程中不会被中断，所有事务命令执行完之后，事务才能结束。
多个命令会被入队到事务队列中，然后按先进先出（FIFO）的顺序执行。
带 WATCH 命令的事务会将客户端和被监视的键在数据库的 watched_keys 字典中进行关联，当键被修改时，程序会将所有监视被修改键的客户端的 REDIS_DIRTY_CAS 选项打开。
只有在客户端的 REDIS_DIRTY_CAS 选项未被打开时，才能执行事务，否则事务直接返回失败。
Redis 的事务保证了 ACID 中的一致性（C）和隔离性（I），原子性（A）但并不保证持久性（D）。

	 * */
}
