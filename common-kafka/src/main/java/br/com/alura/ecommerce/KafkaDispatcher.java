package br.com.alura.ecommerce;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Closeable;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaDispatcher<T> implements Closeable {

    private final KafkaProducer<String, T> producer;   // cria um Produtor de mensagem: quem envia

    KafkaDispatcher () {
        this.producer = new KafkaProducer<>(properties());  // assim que instancia ja cria um produtor de mensagem
    }

    private static Properties properties() {    // Propriedades do produtor do tópico que criei
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());   //Serializar: string para bytes
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializar.class.getName());

        return properties;
    }

    void send(String topic, String key, T value) throws ExecutionException, InterruptedException {
        var record = new ProducerRecord<>(topic, key, value); // cria a mensagem e em qual tópico será postado
        Callback callback = (data, ex) -> { // o que fazer o que fazer quando esperamos pelo sucesso da postagem
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.println(data.topic() + "::: partition" + data.partition() + "/ offset" + data.offset() + "/" + data.timestamp());
        };
        producer.send(record, callback).get();  // get informa que devemos esperar o envio da mensagem
    }

    @Override
    public void close() {
        producer.close();
    }
}
