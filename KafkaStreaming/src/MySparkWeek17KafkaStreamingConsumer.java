import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class MySparkWeek17KafkaStreamingConsumer {
 public static void main (String[] args) {
	 //step1 - set the properties
	 //client ID/Producer ID 
	 //BOOTSTRAP SERVERS LIST
	 //SERIALIZER CLASSES
	 Properties Consumerprops = new Properties();
	 Consumerprops.put(ConsumerConfig.CLIENT_ID_CONFIG,ConstantConfig.appID);
	 Consumerprops.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,ConstantConfig.bootStrapServerList);
	 Consumerprops.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,IntegerDeserializer.class.getName());
	 Consumerprops.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
	 Consumerprops.put(ConsumerConfig.GROUP_ID_CONFIG,ConstantConfig.consumerGroup1);
	 Consumerprops.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
	 
	 
	 //step2 - create object of Kafka producer
	 KafkaConsumer<Integer,String> consumer = new KafkaConsumer<Integer,String>(Consumerprops);
	 
	 consumer.subscribe(Arrays.asList("allOrders"));
	 
	 
	 Properties Producerprops = new Properties();
	 Producerprops.put(ProducerConfig.CLIENT_ID_CONFIG,ConstantConfig.appID);
	 Producerprops.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,ConstantConfig.bootStrapServerList);
	 Producerprops.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,IntegerSerializer.class.getName());
	 Producerprops.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
	 
	//step2 - create object of Kafka producer
	 KafkaProducer<Integer,String> producer = new KafkaProducer<Integer,String>(Producerprops);
	 
	 while(true) {
		ConsumerRecords<Integer,String> records = consumer.poll(100);
		for(ConsumerRecord<Integer,String> record : records) {
			if(record.value().split(",")[3].equals("CLOSED"))
			{producer.send(new ProducerRecord<Integer,String>("CLOSED_ORDERS",record.key(),record.value()));}
			else
			{producer.send(new ProducerRecord<Integer,String>("COMPLETE_ORDERS",record.key(),record.value()));}
		}
	}
	 
	 
 }
}
