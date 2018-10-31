package com.ruoyi.project.openoffice.service;

import com.ruoyi.project.openoffice.model.FileAttribute;
import com.ruoyi.project.openoffice.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by kl on 2018/1/17.
 * Content :
 */
@Service
public class FilePreviewFactory {

    @Autowired
    FileUtils fileUtils;

    @Autowired
    ApplicationContext context;

    public FilePreview get(String url) {
        Map<String, FilePreview> filePreviewMap = context.getBeansOfType(FilePreview.class);
        FileAttribute fileAttribute = fileUtils.getFileAttribute(url);
        return filePreviewMap.get(fileAttribute.getType().getInstanceName());
    }
}
