package com.formacion.backweb.kafka;

import com.formacion.backweb.controller.dto.ReservaOutputDto;
import com.virtualtravel.common.ReservaOutput;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaProducerConfig {
    @Value("reservas")
    public String topic;

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaTemplate<String, ReservaOutput> reservaKafkaTemplate() {
        return new KafkaTemplate<>(reservaProducerFactory());
    }

    @Bean
    public ProducerFactory<String, ReservaOutput> reservaProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    public ReservaOutputDto sendReserva(ReservaOutputDto reserva) {
        ReservaOutput reservaOutput = new ReservaOutput();
        reservaOutput.setId(reserva.getId());
        reservaOutput.setCiudadDestino(reserva.getCiudadDestino());
        reservaOutput.setNombre(reserva.getNombre());
        reservaOutput.setApellido(reserva.getApellido());
        reservaOutput.setTelefono(reserva.getTelefono());
        reservaOutput.setEmail(reserva.getEmail());
        reservaOutput.setFechaReserva(reserva.getFechaReserva());
        reservaOutput.setHoraReserva(reserva.getHoraReserva());

        reservaKafkaTemplate().send(topic, reservaOutput);
        return reserva;
    }
}
