package com.birtu.oj.allsearch.nai.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @program: birtu-oj-knowledge-graph-allsearch
 * @description: 将节点的数据写入到自定义词典中
 * @author: Gauss
 * @date: 2020-07-26 13:43
 **/

public class ToDictionaryIO<V> {
    //1.题目名称    2.算法名字

    private List<V> list = new ArrayList<>();


    /**
     * 算法名文件目录
     */
    private final String algorithm_Path = "algorithm.txt";

    /**
     * 题目名文件目录
     */
    private final String problemName_Path = "problem.txt";


    /**
     * 文件流
     *
     * <作用> 写入到文件中 </作用>
     *
     * 如果该流在打开文件进行输出前，目标文件不存在，那么该流会创建该文件。
     */
    public void outFile(Collection<String> f, String path) throws IOException {
        Iterator<String> iterator = f.iterator();
        File file = new File(path);

        if(!file.exists()){
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferWritter = new BufferedWriter(fileWriter);

        while (iterator.hasNext()){
            bufferWritter.write(iterator.next());
            bufferWritter.newLine();
        }
        bufferWritter.close();
    }



}

