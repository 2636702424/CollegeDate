package com.dhrs.date.controller;

import com.dhrs.date.pojo.School;
import com.dhrs.date.pojo.resp.PageResult;
import com.dhrs.date.service.impl.SchoolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolServiceImpl schoolServiceImpl;


    @GetMapping("id")
    public School findbyId(Long id)
    {
        School school = schoolServiceImpl.findById(id);
        return  school;
    }

    @PostMapping("location")
    public ResponseEntity<PageResult> findByLocation(@RequestParam(value ="province",defaultValue ="") String province,
                                                     @RequestParam(value ="city",defaultValue ="") String city,
                                                     @RequestParam(value ="urban",defaultValue ="") String urban,
                                                     @RequestParam(value ="pageNum",defaultValue ="0") int pageNum)
    {
        PageResult pageResult = schoolServiceImpl.findByLocation(province, city, urban, pageNum);
        return ResponseEntity.ok(pageResult);
    }

}
