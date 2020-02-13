package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName UploadService
 * @Description: 文件上传Service
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/11 22:22
 * @Version V1.0
 **/
@Service
public class UploadService {

    @Autowired
    private FastFileStorageClient storageClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    //文件格式数组
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpg",
            "image/jpeg", "image/png", "image/gif");

    public String uploadFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        //校验文件类型
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)) {
            //文件不是指定格式
            LOGGER.info("文件类型不符合要求:{}", originalFilename);
            return null;
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                LOGGER.info("文件类型不符合要求:{}", originalFilename);
                return null;
            }
            //没有文件夹 先创建文件夹
            File path = new  File("D\\Data\\leyou\\images");
            if (!path.exists()) {
                boolean result = path.mkdirs();
                LOGGER.info("创建文件夹" + (result ? "成功" : "失败"));
            }
//            //保存到服务器
//            file.transferTo(new File(originalFilename));
            String ext = StringUtils.substringAfterLast(originalFilename, ".");
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);
            //生成url地址返回
            return "http://image.leyou.com/" + storePath.getFullPath();
        } catch (IOException e) {
            LOGGER.error("服务器内部错误：{}", originalFilename);
            e.printStackTrace();
            return null;
        }
    }
}
