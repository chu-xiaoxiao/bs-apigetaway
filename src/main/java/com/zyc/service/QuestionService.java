package com.zyc.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zyc.model.*;
import com.zyc.model.Example.*;
import com.zyc.util.Page;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by YuChen Zhang on 18/01/11.
 */
@FeignClient("QUESTION-SERVICE")
@RequestMapping("/question")
public interface QuestionService {

    @RequestMapping(value = "insertMCQ",method = RequestMethod.POST)
    String insert(@RequestBody Mcq mcq);
    @RequestMapping(value = "insertPQ",method = RequestMethod.POST)
    String insert(@RequestBody Pq pq);
    @RequestMapping(value = "insertSCQ",method = RequestMethod.POST)
    String insert(@RequestBody Scq scq);
    @RequestMapping(value = "insertTFQ",method = RequestMethod.POST)
    String insert(@RequestBody Tfq tfq);

    @RequestMapping(value = "queryQuestion/{id}",method = RequestMethod.POST)
    String queryQuestion (@PathVariable("id") Integer id);

    @RequestMapping(value = "queryQuestion",method = RequestMethod.POST)
    String queryQuestion (@RequestBody QuestionExample questionExample);

    @RequestMapping(value = "queryMCQ",method = RequestMethod.POST)
    String queryQuestion (@RequestBody McqExample mcqExample);
    @RequestMapping(value = "querySCQ",method = RequestMethod.POST)
    String queryQuestion (@RequestBody ScqExample scqExample);
    @RequestMapping(value = "queryTFQ",method = RequestMethod.POST)
    String queryQuestion (@RequestBody TfqExample tfqExample);
    @RequestMapping(value = "queryPq",method = RequestMethod.POST)
    String queryQuestion (@RequestBody PqExample pqExample);

    @RequestMapping(value = "queryMCQPage",method = RequestMethod.POST)
    String queryQuestionMCQ(@RequestBody Page<Mcq,McqExample> p);
    @RequestMapping(value = "queryPqPage",method = RequestMethod.POST)
    String queryQuestionPQ(@RequestBody Page<Pq,PqExample> p);
    @RequestMapping(value = "querySCQPage",method = RequestMethod.POST)
    String queryQuestionSCQ(@RequestBody Page<Scq,ScqExample> p);
    @RequestMapping(value = "queryTFQPage",method = RequestMethod.POST)
    String queryQuestionTFQ(@RequestBody Page<Tfq,TfqExample> p);

    @RequestMapping(value = "deleteQuestion",method = RequestMethod.POST)
    String delteQuestion(@RequestBody Integer id);
    @RequestMapping(value = "deleteMCQ",method = RequestMethod.POST)
    String delteQuestion(@RequestBody McqExample mcqExample);
    @RequestMapping(value = "deletePq",method = RequestMethod.POST)
    String delteQuestion(@RequestBody PqExample pqExample);
    @RequestMapping(value = "deleteSCQ",method = RequestMethod.POST)
    String delteQuestion(@RequestBody ScqExample scqExample);
    @RequestMapping(value = "deleteTFQ",method = RequestMethod.POST)
    String delteQuestion(@RequestBody TfqExample tfqExample);

    @RequestMapping(value = "modifyMCQ",method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod = "modifyFallback")
    String modifyMCQ(@RequestBody Map<String,Object> modifyResult);
    @RequestMapping(value = "modifyPq",method = RequestMethod.POST)
    String modifyPQ(@RequestBody Map<String,Object> modifyResult);
    @RequestMapping(value = "modifySCQ",method = RequestMethod.POST)
    String modifySCQ(@RequestBody Map<String,Object> modifyResult);
    @RequestMapping(value = "modifyTFQ",method = RequestMethod.POST)
    String modifyTFQ(@RequestBody Map<String,Object> modifyResult);

}
