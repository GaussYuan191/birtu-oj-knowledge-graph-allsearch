package com.birtu.oj.allsearch.nai.service.neo;



import com.birtu.oj.allsearch.nai.Exception.ErrorCode;
import com.birtu.oj.allsearch.nai.Exception.Resp;
import com.birtu.oj.allsearch.nai.Exception.RestException;
import com.birtu.oj.allsearch.nai.entry.node.Problem;
import com.birtu.oj.allsearch.nai.entry.node.Tags;
import com.birtu.oj.allsearch.nai.repository.OriginCql;
import com.birtu.oj.allsearch.nai.util.ToMsgFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-04-29 20:23
 */
@Service
public class OriginService {
    @Autowired
    private OriginCql originCql;

    public Resp getOriginByName(String name){
        if(name == null || "".equals(name)){
            throw new RestException(ErrorCode.PARAM_INVALID);
        }
        List<Problem> problemList = originCql.getOrigin(name);
        if(problemList == null || problemList.size() < 1){
            return new Resp(name+"暂时没有题目",null);
        }
        StringBuilder sb = new StringBuilder();
        ToMsgFormat.titleList(problemList,sb);
        return new Resp(sb.toString(),problemList);
    }

    /**
     * 获取所有来源的标签数据
     * @return
     */
    public Resp getOriTag(){
        List<Tags> oriTag = originCql.getOriTag();
        StringBuilder sb = new StringBuilder();
        sb.append("来源 : \n");
        ToMsgFormat.tagListToMsg(oriTag,sb);
        return new Resp(sb.toString(),oriTag);
    }


}
