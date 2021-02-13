package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class EmailService {
    public static void main(String[] args) {
        var emailService = new EmailService();

        var service = new KafkaService(EmailService.class.getSimpleName(), "ECOMMERCE_SEND_EMAIL",
                emailService::parse,
                String.class,
                Map.of());
        service.run();  // solicita que consuma
    }

    private void parse(ConsumerRecord<String, String> record) { // Para cada record faça isso -> código ligado ao envio de email
        System.out.println("Sending email, checking for fraud");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
    }

}

