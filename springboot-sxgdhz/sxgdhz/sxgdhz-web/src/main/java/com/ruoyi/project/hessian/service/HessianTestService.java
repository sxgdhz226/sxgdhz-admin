package com.ruoyi.project.hessian.service;

import java.io.InputStream;

public interface HessianTestService {
    public String sayHello(String message);

    public String upload(String filename, InputStream is);
}
