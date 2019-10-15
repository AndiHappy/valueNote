package redissource.rediscase;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * @author guizhai
 *
 */
public class RedisClusterCase {

 public Set<HostAndPort> nodes = new HashSet<HostAndPort>();
 
 public JedisCluster jedis ;
 private String value = "当初有点想放弃读书出来打工的想法，后来无意间看到类似《读者》的一个期刊（具体叫什么忘记了），专门讲大家如何考上大学，以及考上大学后大家的变化等等，这本书站在今天来看，一年刊之中，上半年有点偏鸡血和做题思路，下半年的就是让人开眼见的内容；基本也符合高考冲刺的节奏。\n"
   + "\n"
   + "这本书是我借同桌妹纸的拿回家看的，我老妈由于历史原因文化只有小学（供销社时代的人），看了我借来的连续 4 期后，就悄悄帮我订了。免除了借书的不便，我每期都看这个期刊，加上平时老师苦口婆心相劝好好读书，也渐渐改变了读书无所谓的想法。";

 public RedisClusterCase() {
  nodes.add(new HostAndPort("127.0.0.1", 7000));
  nodes.add(new HostAndPort("127.0.0.1", 7001));
  nodes.add(new HostAndPort("127.0.0.1", 7003));
  nodes.add(new HostAndPort("127.0.0.1", 7002));
  nodes.add(new HostAndPort("127.0.0.1", 7004));
  nodes.add(new HostAndPort("127.0.0.1", 7005));
  jedis= new JedisCluster(nodes);
 }

 /**
 * @param args
 */
 public static void main(String[] args) {
  RedisClusterCase test = new RedisClusterCase();
  test.storeMory();

 }

 private void storeMory() {
  for (int i = 0; i < 100000000; i++) {
   Long tmp = jedis.append("key" + i, value);
   System.out.println(tmp);
   Long tmo = jedis.hset("keyhashmap", "key" + i, value);
   System.out.println(tmo);
  }

 }

}
