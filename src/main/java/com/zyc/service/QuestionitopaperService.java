package com.zyc.service;

import com.zyc.model.Example.QuestionitopaperExample;
import com.zyc.model.Question;
import com.zyc.model.Questionitopaper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by YuChen Zhang on 18/03/12.
 */
@FeignClient("QUESTION-SERVICE")
@RequestMapping("questionitopaper")
public interface QuestionitopaperService {
    @RequestMapping(value = "query",method = RequestMethod.POST)
    List query(QuestionitopaperExample questionitopaperExample);

}
