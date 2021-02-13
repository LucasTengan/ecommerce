package br.com.alura.ecommerce;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Serializer;

public class GsonSerializar<T> implements Serializer<T> {

    private final Gson gson = new GsonBuilder().create();

    public byte[] serialize(String s, T object) {
        return gson.toJson(object).getBytes();
    }
}
