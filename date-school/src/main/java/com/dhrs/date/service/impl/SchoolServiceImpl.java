package com.dhrs.date.service.impl;

import com.dhrs.date.common.entity.school.School;
import com.dhrs.date.mapper.SchoolMapper;
import com.dhrs.date.common.entity.school.resp.PageResult;
import com.dhrs.date.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    private static final int PAGESIZE=10;

    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public School findById(Long id) {
        School school = schoolMapper.selectByPrimaryKey(id);
        return school;
    }

    @Override
    public PageResult findByLocation(String province, String city, String urban, int pageNum) {
        List<School> schoolList = schoolMapper.findByLocation(province, city, urban, pageNum, PAGESIZE);
        PageResult<School> schoolPageResult=new PageResult<>(schoolList.size(),1,schoolList);
        return schoolPageResult;
    }

    @Override
    public PageResult findAll(int pageNum) {
        List<School> schoolList = schoolMapper.finAll(pageNum,PAGESIZE);
        PageResult<School> schoolPageResult=new PageResult<>(schoolList.size(),1,schoolList);
        return schoolPageResult;
    }

    @Override
    public void insert(School school) {
        schoolMapper.insert(school);
    }


//    @Override
//    public List<MemberEntity> findUsres() {
//        return null;
//    }
}
