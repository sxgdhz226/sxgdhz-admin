package com.ruoyi.project.openoffice.service.impl;

import com.google.common.collect.Lists;
import com.ruoyi.project.openoffice.service.FilePreview;
import com.ruoyi.project.openoffice.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

/**
 * Created by kl on 2018/1/17.
 * Content :图片文件处理
 */
@Service
public class PictureFilePreviewImpl implements FilePreview {

    @Autowired
    FileUtils fileUtils;

    @Override
    public String filePreviewHandle(String url, Model model) {
        String fileKey=(String) RequestContextHolder.currentRequestAttributes().getAttribute("fileKey",0);
        List imgUrls = Lists.newArrayList(url);
        try{
            imgUrls.clear();
            imgUrls.addAll(fileUtils.getRedisImgUrls(fileKey));
        }catch (Exception e){
            imgUrls = Lists.newArrayList(url);
        }
        model.addAttribute("imgurls", imgUrls);
        model.addAttribute("currentUrl",url);
        return "picture";
    }
}
