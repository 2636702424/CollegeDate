package com.dhrs.date.obs.controller;

import com.dhrs.date.obs.response.ObsResult;
import com.dhrs.date.obs.service.ObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/obs")
public class ObsController {

    @Autowired
    private ObsService obsService;

    @PostMapping("image")
    public ResponseEntity<ObsResult> UploadImage(@RequestParam("file") MultipartFile file)
        {
            ObsResult result = obsService.upload(file);
            if(StringUtils.isEmpty(result))
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }
}
