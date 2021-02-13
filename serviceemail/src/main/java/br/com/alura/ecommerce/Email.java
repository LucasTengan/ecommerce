package br.com.alura.ecommerce;

public class Email {    // geralmente é recomendado que cada mensagem tenha uma classe

    private final String subject, body;

    public Email(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }
}
