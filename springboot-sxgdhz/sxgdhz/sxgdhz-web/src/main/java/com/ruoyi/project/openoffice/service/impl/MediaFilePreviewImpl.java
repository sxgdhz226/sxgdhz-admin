package com.ruoyi.project.openoffice.service.impl;

import com.ruoyi.project.openoffice.service.FilePreview;
import com.ruoyi.project.openoffice.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * @author : kl
 * @authorboke : kailing.pub
 * @create : 2018-03-25 上午11:58
 * @description:
 **/
@Service
public class MediaFilePreviewImpl implements FilePreview {

    @Autowired
    FileUtils fileUtils;

    @Override
    public String filePreviewHandle(String url, Model model) {
        model.addAttribute("mediaUrl", url);
        return "media";
    }


}
