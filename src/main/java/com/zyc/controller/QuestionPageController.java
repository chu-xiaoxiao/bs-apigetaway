package com.zyc.controller;

import com.zyc.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by YuChen Zhang on 18/03/09.
 */
@Controller
public class QuestionPageController {

    @Autowired
    SubjectService subjectService;
    @RequestMapping(value = "pages/index",method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("question/pages/index1");
        return modelAndView;
    }

    @RequestMapping(value = "pages/MCQManager",method = RequestMethod.GET)
    public ModelAndView insertMCQ(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("question/pages/MCQManager");
        return modelAndView;
    }

    @RequestMapping(value = "pages/SCQManager",method = RequestMethod.GET)
    public ModelAndView insertSCQ(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("question/pages/SCQManager");
        return modelAndView;
    }

    @RequestMapping(value = "pages/TFQManager",method = RequestMethod.GET)
    public ModelAndView insertTFq(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("question/pages/TFQManager");
        return modelAndView;
    }
    @RequestMapping(value = "pages/PQManager",method = RequestMethod.GET)
    public ModelAndView insertPq(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("question/pages/PQManager");
        return modelAndView;
    }
}
