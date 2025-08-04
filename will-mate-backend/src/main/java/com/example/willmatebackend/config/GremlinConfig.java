package com.example.willmatebackend.config;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.util.ser.Serializers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GremlinConfig {

    @Value("${gremlin.endpoint}")
    private String neptuneEndpoint;

    @Value("${gremlin.port}")
    private int neptunePort;

    @Value("${gremlin.protocol}")
    private String neptuneProtocol;

    @Bean
    public Client neptuneClient() {
        Cluster.Builder builder = Cluster.build()
                .addContactPoint(neptuneEndpoint)
                .port(neptunePort)
                .serializer(Serializers.GRAPHBINARY_V1);

        if ("wss".equalsIgnoreCase(neptuneProtocol)) {
            builder.enableSsl(true);
        }

        Cluster cluster = builder.create();
        return cluster.connect();
    }
}
