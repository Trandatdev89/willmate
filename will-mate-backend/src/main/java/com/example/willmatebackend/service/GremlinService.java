package com.example.willmatebackend.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GremlinService {

    private final Client neptuneClient;
    private GraphTraversalSource g;

    @PostConstruct
    public void init() {
        this.g = AnonymousTraversalSource.traversal()
                .withRemote(DriverRemoteConnection.using(neptuneClient));
    }

    @PreDestroy
    public void destroy() throws Exception {
        if (g != null) {
            g.close();
        }
    }

    public GraphTraversalSource getTraversalSource() {
        return g;
    }
}
