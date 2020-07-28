package com.birtu.oj.allsearch.nai.entry.rel;


import com.birtu.oj.allsearch.nai.entry.node.Difficulty;
import com.birtu.oj.allsearch.nai.entry.node.Problem;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "难度")
@Data
public class DifficultyRelation {
    /**
     * 每一个节点都会有一个隐藏的自增ID作为主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 难度节点
     */
    @StartNode
    private Difficulty difficulty;

    /**
     * 问题节点
     */
    @EndNode
    private Problem problem;
}
