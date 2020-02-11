package cn.mycz.community.controller;

import cn.mycz.community.dto.UploadResultDto;
import cn.mycz.community.provider.UCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 木已成舟
 * @date 2020/2/10
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private UCloudProvider uCloudProvider;

    @RequestMapping("/upload")
    @ResponseBody
    public UploadResultDto upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        try {
            String filename = uCloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());

            UploadResultDto resultDto = new UploadResultDto();
            resultDto.setSuccess(1);
            resultDto.setUrl(filename);
            return resultDto;
        } catch (Exception e) {
            e.printStackTrace();
        }


        UploadResultDto resultDto = new UploadResultDto();
        resultDto.setSuccess(1);
        resultDto.setUrl("/images/wechat.jpg");
        return resultDto;
    }
}
