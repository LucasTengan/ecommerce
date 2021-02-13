package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class FraudDetectorService {

    public static void main(String[] args) {
        var fraudService = new FraudDetectorService();
        var service = new KafkaService<>(FraudDetectorService.class.getSimpleName(), "ECOMMERCE_NEW_ORDER",
                fraudService::parse, Order.class,
                Map.of());
        service.run();
    }

    /*
        // Cada consumidor consome de um numero de partições (meio equilibradas)
        // Neste caso informei que quero 3 partições e possuo 2 consumidores (2 main's desse consumidor rodando)
        var consumer = new KafkaConsumer<String, String>(properties()); // recebe as propriedades de um consumidor
        consumer.subscribe(Collections.singletonList("ECOMMERCE_NEW_ORDER"));   // de qual tópico quero consumir/ouvir
        while(true) {
            var records = consumer.poll(Duration.ofMillis(100));    // espera por um tempo se tem registro
            if (!records.isEmpty()) {
                System.out.println("Encontrei " + records.count() + " registros");
                for (var record : records) {
                    System.out.println("Processando new order, checking for fraud");
                    System.out.println(record.key());
                    System.out.println(record.value());
                    System.out.println(record.partition());
                    System.out.println(record.offset());    // qual mensagem é
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Order processed");
                }
            }
        }
    }

     */
    public void parse(ConsumerRecord<String, Order> record) {
        System.out.println("Processando new order, checking for fraud");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());    // qual mensagem é
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Order processed");
    }
}
