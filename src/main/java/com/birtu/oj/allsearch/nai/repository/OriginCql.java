package com.birtu.oj.allsearch.nai.repository;


import com.birtu.oj.allsearch.nai.entry.node.Problem;
import com.birtu.oj.allsearch.nai.entry.node.Tags;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-04-29 20:20
 */
public interface OriginCql extends Neo4jRepository<Tags,Long> {

    @Query("MATCH (p:Problem)-[]-(t:Tags{type:\"Origin\",name:$name}) RETURN p LIMIT 10")
    List<Problem> getOrigin(String name);

    @Query("MATCH (n:Tags{type:\"Origin\"}) RETURN n")
    List<Tags> getOriTag();



}
