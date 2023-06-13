package com.example.baking.controller;

import com.example.baking.response.ResultVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className UploadController
 * @date 2023/06/01 15:37
 */
@RestController
@RequestMapping("/v1/upload")
public class UploadController {
    @Value("${filePath}")
    private String filePath;
    
    @RequestMapping("")
    public ResultVO upload(MultipartFile file) throws IOException {
        // 获取文件的完整名称
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        // 获取文件的后缀名
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 得到唯一文件名: UUID.randomUUID()方法可以得到一个唯一标识的16进制字符串
        fileName = UUID.randomUUID() + suffix;

        // 以当前时间的年月日作为文件路径的一部分，并作为目录创建出来
        SimpleDateFormat format = new SimpleDateFormat("/yyyy/MM/dd/");
        String datePath = format.format(new Date());
        File dirFile = new File(filePath + datePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        // 把图片保存进文件夹C:/E/files/2023/06/01/xxx.jpg，异常抛出
        file.transferTo(new File(filePath + datePath + fileName));
        // 把图片路径/2023/06/01/xxx.jpg返回给客户端
        return ResultVO.ok(datePath + fileName);
    }

    @GetMapping("remove")
    public void remove(String url) {
        new File(filePath + url).delete();
    }
}
