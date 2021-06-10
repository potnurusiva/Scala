import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class MySparkWeek17KafkaStreamingProducer {
 public static void main (String[] args) {
	 //step1 - set the properties
	 //client ID/Producer ID 
	 //BOOTSTRAP SERVERS LIST
	 //SERIALIZER CLASSES
	 Properties props = new Properties();
	 props.put(ProducerConfig.CLIENT_ID_CONFIG,ConstantConfig.appID);
	 props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,ConstantConfig.bootStrapServerList);
	 props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,IntegerSerializer.class.getName());
	 props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
	 
	 
	 //step2 - create object of Kafka producer
	 KafkaProducer<Integer,String> producer = new KafkaProducer<Integer,String>(props);
	 
	 //step3 - calling the send method on this producer object
	 //This send method takes as an argument the record and record should be enclosed in producer record
	 /*
	 for(int i=51;i<=200;i++) {
		 producer.send(new ProducerRecord<Integer,String>("newTopic",i,"This is my msg number "+ i));
	 }
	 */
	 producer.send(new ProducerRecord<Integer,String>("allOrders",1,"1,2013-07-25 00:00:00.0,11599,CLOSED"));
	 producer.send(new ProducerRecord<Integer,String>("allOrders",2,"2,2013-07-25 00:00:00.0,256,COMPLETE"));
	 
	 //step4 - close the producer object
	 producer.close();
 }
}
