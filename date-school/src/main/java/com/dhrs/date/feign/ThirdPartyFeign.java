package com.dhrs.date.feign;

import com.dhrs.date.common.utils.R;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Classname ThirdPartyFeign
 * @Description TODO
 * @Date 2020/7/26 9:42
 * @Author 冷心影翼
 */
@FeignClient(value = "date-third",configuration = ThirdPartyFeign.MultipartSupportConfig.class)
public interface ThirdPartyFeign {

    @PostMapping(value = "/obs/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R uploadImage(@RequestPart(value = "file") MultipartFile file, @RequestParam("fileName") String fileName);


    class MultipartSupportConfig {
        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;
        // new一个form编码器，实现支持form表单提交

        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }
}
