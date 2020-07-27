package com.dhrs.date.obs.controller;

import com.dhrs.date.common.entity.thirdparty.response.ObsResult;
import com.dhrs.date.common.exception.ErrCodeEnume;
import com.dhrs.date.common.utils.R;
import com.dhrs.date.obs.service.ObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/obs")
public class ObsController {

    @Autowired
    private ObsService obsService;

    @PostMapping(value = "image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R uploadImage(@RequestPart(value = "file") MultipartFile file, @RequestParam("fileName") String fileName) {
        ObsResult result = obsService.upload(file,fileName);
        if(StringUtils.isEmpty(result)) {
            return R.error(ErrCodeEnume.FILE_UPLOAD_FILE);
        }
        System.out.println(result);
        return R.ok().put("data",result);
    }
}
