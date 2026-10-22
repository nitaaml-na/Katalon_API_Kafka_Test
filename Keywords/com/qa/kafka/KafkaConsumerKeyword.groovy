package com.qa.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import com.kms.katalon.core.annotation.Keyword

public class KafkaConsumerKeyword {

    @Keyword
    public void consumeKafkaMessage(String bootstrapServers, String topic, String groupId) {
        Properties props = new Properties()
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName())
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName())
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)
        consumer.subscribe(Collections.singletonList(topic))

        println("Mulai mendengarkan/consume dari topik: " + topic)

        // Membaca pesan (Timeout 5 detik)
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5))

        records.each { record ->
            println("Pesan diterima - Key: ${record.key()}, Value: ${record.value()}")
        }

        consumer.close()
        println("Kafka consumer ditutup.")
    }
}
