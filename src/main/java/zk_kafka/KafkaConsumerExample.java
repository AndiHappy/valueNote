package zk_kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author guizhai
 *
 */
public class KafkaConsumerExample {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
    Properties props = new Properties();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group");
    KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
    consumer.subscribe(Arrays.asList("odd", "even"));
//    int counter = 0;
    while (true) {
        ConsumerRecords<String, String> recs = consumer.poll(10);
        if (recs.count() == 0) {
        } else {
            for (ConsumerRecord<String, String> rec : recs) {
                System.out.println("Recieved "+ rec.key() +" "+rec.value());
            }
        }
//        counter++;
    }
}

}
