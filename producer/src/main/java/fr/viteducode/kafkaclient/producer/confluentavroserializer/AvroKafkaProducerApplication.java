package fr.viteducode.kafkaclient.producer.confluentavroserializer;

import fr.viteducode.avro.ChampionKey;
import fr.viteducode.avro.ChampionValue;
import fr.viteducode.avro.PlayerKey;
import fr.viteducode.avro.PlayerValue;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class AvroKafkaProducerApplication {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "0.0.0.0:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        properties.put("schema.registry.url", "http://0.0.0.0:8081");

        KafkaProducer<SpecificRecord, SpecificRecord> producer = new KafkaProducer<>(properties);

        PlayerKey playerKey = PlayerKey.newBuilder().setId(1L).build();
        PlayerValue playerValue = PlayerValue.newBuilder()
                .setName("Faker")
                .setIsPlaying(true)
                .build();

        ProducerRecord<SpecificRecord, SpecificRecord> playerRecord = new ProducerRecord<>("player_avro", playerKey, playerValue);
        producer.send(playerRecord);

        ChampionKey championKey = ChampionKey.newBuilder().setId(95L).build();
        ChampionValue championValue = ChampionValue.newBuilder().setName("Caitlyn").build();

        ProducerRecord<SpecificRecord, SpecificRecord> championRecord = new ProducerRecord<>("champion_avro", championKey, championValue);
        producer.send(championRecord);
        producer.close();
    }
}
