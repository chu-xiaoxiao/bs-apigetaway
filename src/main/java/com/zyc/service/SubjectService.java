package com.zyc.service;

import com.zyc.model.Example.SubjectExample;
import com.zyc.model.Subject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by YuChen Zhang on 18/01/11.
 */
@FeignClient("QUESTION-SERVICE")
@RequestMapping("/subject")
public interface SubjectService {

    @RequestMapping("query/{id}")
    String query(@PathVariable("id") Integer id);

    @RequestMapping(value = "query",method = RequestMethod.POST)
    String query(@RequestBody SubjectExample subjectExample);

    @RequestMapping(value = "insert" ,method = RequestMethod.POST)
    String insert(Subject subject);

    @RequestMapping("delete/{id}")
    String delete(@PathVariable("id") Integer id);

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    String delete(@RequestBody SubjectExample subjectExample);

    @RequestMapping(value = "update",method = RequestMethod.POST)
    String update(@RequestBody Map<String, Object> modifyResult) throws Exception ;
}
