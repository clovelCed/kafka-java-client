package fr.viteducode.kafkaclient.producer.customserializer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;

import java.util.Properties;

public class CustomKafkaSerializerProducerApplication {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "0.0.0.0:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class);

        KafkaProducer<Long, Champion> producer = new KafkaProducer<>(properties);

        Champion champion = new Champion(1L, "Garen");

        ProducerRecord<Long, Champion> championRecord = new ProducerRecord<>("champion_topic", champion.getId(), champion);
        producer.send(championRecord);

        producer.close();
    }
}
