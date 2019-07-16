package zk_kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author guizhai
 *
 */
public class KafkaProduceClient {

	private KafkaProducer<String, String> producer = null;

	private static class KafkaClientHolder {
		private static KafkaProduceClient instance = new KafkaProduceClient();
	}

	public static KafkaProduceClient getIns() {
		return KafkaClientHolder.instance;
	}


	private KafkaProduceClient() {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "zlz.kafka.client");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
		this.setProducer(producer);
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			ProducerRecord<String, String> data;
			if (i % 2 == 0) {
				data = new ProducerRecord<String, String>("even", 0, Integer.toString(i), String.format("%d is even", i));
			} else {
				data = new ProducerRecord<String, String>("odd", 0, Integer.toString(i), String.format("%d is odd", i));
			}
			KafkaProduceClient.getIns().getProducer().send(data);
			Thread.sleep(1L);
		}
	}

	public KafkaProducer<String, String> getProducer() {
		return producer;
	}

	public void setProducer(KafkaProducer<String, String> producer) {
		this.producer = producer;
	}

}
