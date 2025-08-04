package com.example.willmatebackend.repository.User;

import com.example.willmatebackend.GraphIdentifiers;
import com.example.willmatebackend.dto.PaginationDTO;
import com.example.willmatebackend.dto.ResponseCase;
import com.example.willmatebackend.dto.ServerResponseDto;
import com.example.willmatebackend.dto.request.UserDTO;
import com.example.willmatebackend.graph_property.user.User;
import com.example.willmatebackend.service.GremlinService;
import lombok.RequiredArgsConstructor;
import org.apache.tinkerpop.gremlin.process.traversal.Order;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;
import static org.apache.tinkerpop.gremlin.structure.VertexProperty.Cardinality.single;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final GremlinService gremlinService;

    public ServerResponseDto createOrUpdateUser(UserDTO userRequest){

        GraphTraversalSource g = gremlinService.getTraversalSource();
        GraphTraversal<?,?> graphTraversal;

        String userId = userRequest.getId();

        if(StringUtils.hasText(userId)){

            if(!existsUserByUserId(userId)){
                return ServerResponseDto.with(ResponseCase.NOTFOUND);
            }

            graphTraversal = g.V().has(GraphIdentifiers.VERTEX_USER, User.ID,userId);
        }else{
            graphTraversal = g.addV(GraphIdentifiers.VERTEX_USER)
                    .property(User.ID, UUID.randomUUID().toString());
        }

        Map<?,?> mapData =  graphTraversal
                .property(single,User.EMAIL,userRequest.getEmail())
                .property(single,User.USERNAME,userRequest.getUsername())
                .property(single,User.PASSWORD,userRequest.getPassword()).valueMap().next();

        return ServerResponseDto.with(ResponseCase.SUCCESS,mapData);
    }

    private boolean existsUserByUserId(String userId){
        GraphTraversalSource g = gremlinService.getTraversalSource();
        return g.V().has(GraphIdentifiers.VERTEX_USER,User.ID,userId).count().next() > 0;
    }

    public PaginationDTO<?> getUserPagi(int page, int size, String sortField, String sortDir, String keywordSearch){
        Order order = sortDir.equals("desc") ? Order.desc : Order.asc;

        int start = (page - 1)*size; //offset so phan tu muon bo qua, size nhu la limit
        int end = start+size; //limit+offset,lay den phan tu bao nhieu


        return null;
    }

    private GraphTraversal<?, ?> buildQueryGetCompanyByTraversal(String keywordSearch){
         GraphTraversalSource g = gremlinService.getTraversalSource();
         GraphTraversal<?,?> graphTraversal = g.V().hasLabel(GraphIdentifiers.VERTEX_USER);

         if(StringUtils.hasText(keywordSearch)){
             graphTraversal.or()
         }
    }
}
