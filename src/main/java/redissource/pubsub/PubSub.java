package redissource.pubsub;

import java.util.Scanner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class PubSub {

	/**
	 每个 Redis 服务器进程都维持着一个表示服务器状态的 redis.h/redisServer 结构， 结构的 pubsub_channels 属性是一个字典， 
	 这个字典就用于保存订阅频道的信息：
	 
	 类似设计模式中的订阅模式
	 
	 关键在于模式订阅的实现：
	 
	redisServer.pubsub_patterns 属性是一个链表，链表中保存着所有和模式相关的信息：
	
	struct redisServer {
	// ...
	list *pubsub_patterns;
	// ...
	};
	链表中的每个节点都包含一个 redis.h/pubsubPattern 结构：
	
	typedef struct pubsubPattern {
	redisClient *client;
	robj *pattern;
	} pubsubPattern;
	
	
	client 属性保存着订阅模式的客户端，而 pattern 属性则保存着被订阅的模式。
	每当调用 PSUBSCRIBE 命令订阅一个模式时， 程序就创建一个包含客户端信息和被订阅模式的 pubsubPattern 结构， 
	并将该结构添加到 redisServer.pubsub_patterns 链表中。
	
	通过遍历整个 pubsub_patterns 链表，程序可以检查所有正在被订阅的模式，以及订阅这些模式的客户端。
	
	如果一channel发布了了信息，可能需要遍历一整个pattern链表，确定订阅的client！
	
	
	完整描述 PUBLISH 功能的伪代码定于如下：
	
	def PUBLISH(channel, message):
	
	# 遍历所有订阅频道 channel 的客户端
	for client in server.pubsub_channels[channel]:
	    # 将信息发送给它们
	    send_message(client, message)
	
	# 取出所有模式，以及订阅模式的客户端
	for pattern, client in server.pubsub_patterns:
	    # 如果 channel 和模式匹配
	    if match(channel, pattern):
	        # 那么也将信息发给订阅这个模式的客户端
	        send_message(client, message)
	 * */

	/**
	 * 
	订阅信息由服务器进程维持的 redisServer.pubsub_channels 字典保存，字典的键为被订阅的频道，字典的值为订阅频道的所有客户端。
	当有新消息发送到频道时，程序遍历频道（键）所对应的（值）所有客户端，然后将消息发送到所有订阅频道的客户端上。
	订阅模式的信息由服务器进程维持的 redisServer.pubsub_patterns 链表保存，链表的每个节点都保存着一个 pubsubPattern 结构，结构中保存着被订阅的模式，以及订阅该模式的客户端。程序通过遍历链表来查找某个频道是否和某个模式匹配。
	当有新消息发送到频道时，除了订阅频道的客户端会收到消息之外，所有订阅了匹配频道的模式的客户端，也同样会收到消息。
	退订频道和退订模式分别是订阅频道和订阅模式的反操作。
	
	 * */

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost",6379);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				RedisSub sub = new RedisSub();
				sub.sub();
			}
		}).start();
		
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter the string to Publish:");
			String msg = scanner.nextLine();
			jedis.publish("testChannel", msg);
			if(msg.equals("exit")) {
				break;
			}
		}
		scanner.close();
		jedis.close();

	}

}

class RedisSub {

	public void sub() {
		Jedis jedis = new Jedis("localhost",6379);
		String channel = "testChannel";
		jedis.subscribe(new JedisPubSub() {
			@Override
			public void onMessage(String channel, String message) {
				super.onMessage(channel, message);
				if(message.equals("exit")) {
					jedis.close();
					System.exit(0);
				}
				System.out.println("Received message:" + message);
			}

			@Override
			public void onSubscribe(String channel, int subscribedChannels) {
			}

			@Override
			public void onUnsubscribe(String channel, int subscribedChannels) {
			}

			@Override
			public void onPMessage(String pattern, String channel, String message) {
			}

			@Override
			public void onPUnsubscribe(String pattern, int subscribedChannels) {
			}

			@Override
			public void onPSubscribe(String pattern, int subscribedChannels) {
			}

		}, channel);
	}
}
