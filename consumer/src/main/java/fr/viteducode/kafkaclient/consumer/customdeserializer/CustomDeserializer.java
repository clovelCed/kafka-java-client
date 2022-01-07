package fr.viteducode.kafkaclient.consumer.customdeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class CustomDeserializer implements Deserializer<Champion> {

    private final ObjectMapper objectMapper;

    public CustomDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void configure(Map<String, ?> config, boolean isKey) {

    }

    @Override
    public Champion deserialize(String s, byte[] data) {

        Champion champion = null;

        if (data != null) {
            try {
                champion = objectMapper.readValue(data, Champion.class);
            } catch (IOException e) {
                throw new SerializationException(e);
            }
        }

        return champion;
    }


    @Override
    public void close() {
        // nothing to perform
    }
}
