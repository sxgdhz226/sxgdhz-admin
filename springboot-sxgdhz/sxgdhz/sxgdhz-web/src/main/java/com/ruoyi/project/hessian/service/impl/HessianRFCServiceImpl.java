package com.ruoyi.project.hessian.service.impl;

import com.ruoyi.project.hessian.service.HessianTestService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.*;

@Service("hessianRFCServiceImpl")
public class HessianRFCServiceImpl implements HessianTestService {

        public String sayHello(String message) {
                 System.out.println("\n message is "+message);
                 return "SUCESS";
             }

         public String upload(String filename, InputStream file ) {
             FileOutputStream fos=null;
             try {
                    fos=new FileOutputStream(new File("d:/"+filename));
                    IOUtils.copy(file, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return "Failure";
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Failure";
                }finally{
                    if(fos!=null){
                            IOUtils.closeQuietly(fos);
                        }
                 if(file!=null){
                         IOUtils.closeQuietly(file);
                     }
                 }
             return "SUCESS";
         }
}
