package com.dhrs.date.obs.service;

import com.alibaba.fastjson.JSONObject;
import com.dhrs.date.common.entity.thirdparty.response.ObsResult;
import com.dhrs.date.obs.config.ObsProperties;
import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class ObsService {

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif","image/png");
    private static final Logger LOGGER = LoggerFactory.getLogger(ObsService.class);


    @Autowired
    private ObsProperties obsProperties;


    /**
     * 文件上传
     * @param file
     * @return
     */
    public ObsResult upload(MultipartFile file,String fileName)
    {

        String contype=file.getContentType();
        String originalFilename = file.getOriginalFilename();
        String sub=originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
        if(!CONTENT_TYPES.contains(contype))
        {
            LOGGER.error("文件类型出错"+originalFilename);
            return null;
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                LOGGER.error("文件内容不合法" + originalFilename);
                return null;
            }
        }catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("获取文件流失败");
        }
        //上传图片
        InputStream in=null;
        ObsClient obsClient = null;
        try{
            //获取图片名称，作为上传文件名参数
//            String objectKey = file.getOriginalFilename();
            String objectKey = fileName;
            //获取流对象
            in= file.getInputStream();
            // 创建ObsClient实例
            obsClient = new ObsClient(obsProperties.getAk(), obsProperties.getSk(), obsProperties.getEndPoint());
            // 使用访问OBS
            PutObjectResult putObjectResult = obsClient.putObject("college-date", objectKey, in);
            //将图片信息封装起来，方便前端回显调用
            String url=putObjectResult.getObjectUrl();
            return new ObsResult(objectKey,url);
        }catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("上传图片失败!");
        }
        finally {
            try
            {
                if(in!=null)
                {
                    in.close();
                }
            }catch (IOException e)
            {
                e.printStackTrace();
                System.err.println("输出流关闭失败！");
            }
            try
            {
                if(obsClient!=null)
                {
                    // 关闭obsClient
                    obsClient.close();
                }
            }catch (IOException e)
            {
                e.printStackTrace();
                System.err.println("obs客户端流对象关闭失败！");
            }

        }
        return null;
    }

    //文件下载
//    public

}
