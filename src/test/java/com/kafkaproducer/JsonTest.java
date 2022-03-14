package com.kafkaproducer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kafkaproducer.deserializer.MultipleBarsDeserializer;
import com.kafkaproducer.model.Bars;
import com.kafkaproducer.model.MultipleBars;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {


    @Test
    public void parseBarsJsonFromFileWithJsonNodeMapping() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode barsNode = mapper.readTree(new ClassPathResource("./bars.json").getFile());
        List<Bars> bars = new ArrayList<>();

        for(JsonNode node: barsNode.get("bars")){
            Bars bar = Bars
                    .builder()
//                    .symbol(node.get("s").textValue())
                    .timeStamp(node.get("t").textValue())
                    .open(node.get("o").doubleValue())
                    .high(node.get("h").doubleValue())
                    .low(node.get("l").doubleValue())
                    .close(node.get("c").doubleValue())
                    .volume(node.get("v").intValue())
                    .build();

            bars.add(bar);
        }

        assertEquals(757, bars.size());

    }

    @Test
    public void parseBarsJsonFromFileWithCustomDeserializer() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(MultipleBars.class, new MultipleBarsDeserializer());
        mapper.registerModule(module);

        MultipleBars bars = mapper.readValue(new ClassPathResource("./bars.json").getFile(), MultipleBars.class);

        assertEquals(757, bars.getMultipleBars().size());
        assertEquals("SPY", bars.getMultipleBars().get(0).getSymbol());

    }
}
