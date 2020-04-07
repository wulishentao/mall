package com.beau.graduation;

import com.beau.graduation.config.UploadConfig;
import com.beau.graduation.utils.FileUploadsUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@SpringBootTest
class MallWebApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(FileUploadsUtil.getDefaultBaseDir());
        System.out.println(FileUploadsUtil.extractFilename(".jpg"));
        System.out.println(File.separator + UploadConfig.getUploadPath() + FileUploadsUtil.extractFilename(".jpg"));
        File file = new File(File.separator + UploadConfig.getUploadPath() + FileUploadsUtil.extractFilename(".jpg"));
        File file1 = new File("D:" + File.separator + "pro" + File.separator + "a.txt");
        System.out.println("file1 = " + file1);

        System.out.println("file = " + file);
    }

}
