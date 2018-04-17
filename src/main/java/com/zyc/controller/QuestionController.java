package com.zyc.controller;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zyc.exception.StatusException;
import com.zyc.model.*;
import com.zyc.model.Example.McqExample;
import com.zyc.model.Example.PqExample;
import com.zyc.model.Example.ScqExample;
import com.zyc.model.Example.TfqExample;
import com.zyc.service.QuestionService;
import com.zyc.util.JSONResult;
import com.zyc.util.Page;
import com.zyc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuChen Zhang on 18/01/11.
 */
@RestController
public class QuestionController extends BaseController{
    @Autowired
    public QuestionService questionService;


    @RequestMapping(value = "insert/MCQ",method = RequestMethod.POST)
    @ResponseBody
    public Mcq inserMCQ(Mcq mcq) throws StatusException {
        JSONResult jsonResult = new JSONResult(questionService.insert(mcq));
        return jsonResult.getResult(Mcq.class);
    }

    @RequestMapping(value = "insert/SCQ",method = RequestMethod.POST)
    @ResponseBody
    public Scq insertSCQ(Scq scq) throws StatusException {
        JSONResult jsonResult = new JSONResult(questionService.insert(scq));
        return jsonResult.getResult(Scq.class);
    }

    @RequestMapping(value = "insert/TFQ",method = RequestMethod.POST)
    @ResponseBody
    public Tfq insertSCQ(Tfq tfq) throws StatusException {
        JSONResult jsonResult = new JSONResult(questionService.insert(tfq));
        return jsonResult.getResult(Tfq.class);
    }

    @RequestMapping(value = "insert/PQ",method = RequestMethod.POST)
    @ResponseBody
    public Pq insertSCQ(Pq pq) throws StatusException {
        pq.setPqpath("123");
        JSONResult jsonResult = new JSONResult(questionService.insert(pq));
        return jsonResult.getResult(Pq.class);
    }

    
    @RequestMapping(value = "query/MCQPage",method = RequestMethod.POST)
    public String queryQuestion(Mcq mcq,Integer currentpage,Integer size) throws StatusException {
        McqExample mcqExample = new McqExample();
        McqExample.Criteria criteria = mcqExample.createCriteria();
        if(mcq.getMcqid()!=null){
            criteria.andMcqidEqualTo(mcq.getMcqid());
        }
        if(!StringUtil.isNull(mcq.getMcqtext())) {
           criteria.andMcqtextLike(StringUtil.appendLike(mcq.getMcqtext()));
        }
        Page page = new Page<Mcq, McqExample>(mcqExample,currentpage,size);
        JSONResult jsonResult = new JSONResult(questionService.queryQuestionMCQ(page));
        return jsonResult.getResult();
    }
    @RequestMapping(value = "query/PQPage",method = RequestMethod.POST)
    public String queryQuestion(Pq pq,Integer currentpage,Integer size) throws StatusException {
        PqExample pqExample = new PqExample();
        PqExample.Criteria criteria = pqExample.createCriteria();
        if(pq.getPqid()!=null){
            criteria.andPqidEqualTo(pq.getPqid());
        }
        if(!StringUtil.isNull(pq.getPqtext())){
            criteria.andPqtextLike(StringUtil.appendLike(pq.getPqtext()));
        }
        Page page = new Page<Pq, PqExample>(pqExample,currentpage,size);
        JSONResult jsonResult = new JSONResult(questionService.queryQuestionPQ(page));
        return jsonResult.getResult();
    }
    @RequestMapping(value = "query/SCQPage",method = RequestMethod.POST)
    public String queryQuestion(Scq scq,Integer currentpage,Integer size) throws StatusException {
        ScqExample scqExample = new ScqExample();
        ScqExample.Criteria criteria = scqExample.createCriteria();
        if(scq.getScqid()!=null){
            criteria.andScqidEqualTo(scq.getScqid());
        }
        if(!StringUtil.isNull(scq.getScqtext())){
            criteria.andScqtextLike(StringUtil.appendLike(scq.getScqtext()));
        }
        Page page = new Page<Scq, ScqExample>(scqExample,currentpage,size);
        return new JSONResult(questionService.queryQuestionSCQ(page)).getResult();
    }
    @RequestMapping(value = "query/TFQPage",method = RequestMethod.POST)
    public String queryQuestion(Tfq tfq,Integer currentpage,Integer size) throws StatusException {
        TfqExample tfqExample = new TfqExample();
        TfqExample.Criteria criteria = tfqExample.createCriteria();
        if(tfq.getTfqid()!=null){
            criteria.andTfqidEqualTo(tfq.getTfqid());
        }
        if(!StringUtil.isNull(tfq.getTfqtext())){
            criteria.andTfqtextLike(StringUtil.appendLike(tfq.getTfqtext()));
        }
        Page page = new Page<Tfq, TfqExample>(tfqExample,currentpage,size);
        return new JSONResult(questionService.queryQuestionTFQ(page)).getResult();
    }

    @RequestMapping (value = "/query/{id}",method = RequestMethod.GET)
    public String queryQuestion(@PathVariable("id") Integer id) throws StatusException {
        Question question = new JSONResult(questionService.queryQuestion(id)).getResult(Question.class);
        if(question.getQuestiontype()==1){
            question = (Scq)question;
        }
        if(question.getQuestiontype()==2){
            question = (Mcq)question;
        }
        if(question.getQuestiontype()==3){
            question = (Tfq) question;
        }
        if(question.getQuestiontype()==4){
            question = (Pq) question;
        }
        return JSON.toJSONString(question);
    }
    @RequestMapping(value = "/modify",method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "modifyFallback")
    public String modify() throws StatusException {
        Mcq mcq = new Mcq();
        mcq.setMcqtext("测试多选题目修改测试");
        McqExample mcqExample = new McqExample();
        mcqExample.createCriteria().andMcqtextLike(StringUtil.appendLike("测试多选题目"));
        Map<String,Object> result = new HashMap<>();
        result.put("mcq",mcq);
        result.put("mcqExample",mcqExample);
        questionService.modifyMCQ(result);
        return new JSONResult(questionService.queryQuestion(mcqExample)).getResult();
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String get(@PathVariable("id") Integer id) throws StatusException {
        Question question = new JSONResult(questionService.queryQuestion(id)).getResult(Question.class);
        questionService.delteQuestion(id);
        return JSON.toJSONString(question);
    }
}
