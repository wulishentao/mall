package com.beau.graduation.utils;

import com.beau.graduation.config.UploadConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @classname: FileUploadsUtil.java
 * @author: Beau
 * @create: 2020/03/23 19:32
 * @version: 1.0.0
 **/
public class FileUploadsUtil {
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = UploadConfig.getProfile();

    /**
     * 默认文件类型jpg
     */
    public static final String IMAGE_JPG_EXTENSION = ".jpg";

    private static int counter = 0;

    public static void setDefaultBaseDir(String defaultBaseDir) {
        FileUploadsUtil.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir() {
        return defaultBaseDir;
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String uploadPicture(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, FileUploadsUtil.IMAGE_JPG_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String uploadText(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, ".txt");
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir   相对应用的基目录
     * @param file      上传的文件
     * @param extension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws IOException 比如读写文件出错时
     */
    public static final String upload(String baseDir, MultipartFile file, String extension) throws Exception {

        int fileNameLength = file.getOriginalFilename().length();
        if (fileNameLength > FileUploadsUtil.DEFAULT_FILE_NAME_LENGTH) {
            throw new Exception("文件名长度超出限定长度");
        }

        assertAllowed(file);

        String fileName = extractFilename(file, extension);

        File desc = getAbsoluteFile(baseDir, baseDir + fileName);
        file.transferTo(desc);
        return fileName;
    }

    public static final String extractFilename(MultipartFile file, String extension) {
        String filename = DateUtil.datePath() + "/" + UuidUtil.getUuid() + extension;
        return filename;
    }

    private static final File getAbsoluteFile(String uploadDir, String filename) throws IOException {
        File desc = new File(File.separator + filename);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     */
    public static final void assertAllowed(MultipartFile file) {//throws FileSizeLimitExceededException {
        long size = file.getSize();
        if (size > DEFAULT_MAX_SIZE) {
            // 此处可进行异常处理throws
            System.out.println("文件大小超出最大限定大小");
        }
    }
}
