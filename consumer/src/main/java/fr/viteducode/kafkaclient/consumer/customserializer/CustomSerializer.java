package fr.viteducode.kafkaclient.consumer.customserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustomSerializer implements Serializer<Champion> {

    private final ObjectMapper objectMapper;

    public CustomSerializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void configure(Map<String, ?> config, boolean isKey) {

    }

    @Override
    public byte[] serialize(String s, Champion message) {

        byte[] data = null;

        if (message != null) {
            try {
                data = objectMapper.writeValueAsBytes(message);
            } catch (JsonProcessingException e) {
                throw new SerializationException(e);
            }
        }

        return data;
    }

    @Override
    public void close() {
        // nothing to perform
    }
}
