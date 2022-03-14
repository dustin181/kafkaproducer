package com.kafkaproducer.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.kafkaproducer.model.Bars;
import com.kafkaproducer.model.MultipleBars;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MultipleBarsDeserializer extends StdDeserializer<MultipleBars> {

    public MultipleBarsDeserializer() {
        this(null);
    }

    public MultipleBarsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public MultipleBars deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {

        JsonNode barsNode = jp.getCodec().readTree(jp);
        List<Bars> bars = new ArrayList<>();

        String symbol = String.valueOf(barsNode.get("symbol"));
        symbol = symbol.replaceAll("[^a-zA-Z]+", "");

        for(JsonNode node: barsNode.get("bars")){
            Bars bar = Bars
                    .builder()
                    .symbol(symbol)
                    .timeStamp(node.get("t").textValue())
                    .open(node.get("o").doubleValue())
                    .high(node.get("h").doubleValue())
                    .low(node.get("l").doubleValue())
                    .close(node.get("c").doubleValue())
                    .volume(node.get("v").intValue())
                    .build();

            bars.add(bar);
        }

        return MultipleBars
                .builder()
                .multipleBars(bars)
                .build();
    }
}
