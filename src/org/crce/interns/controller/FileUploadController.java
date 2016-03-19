package org.crce.interns.controller;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class FileUploadController {

	private String saveDirectory = "C:/Users/LENHEARD DEON/Desktop/";//the folder where the file is to be stored
	
	//uploading single file
	@RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public String handleFileUpload(HttpServletRequest request,
            @RequestParam CommonsMultipartFile fileUpload) throws Exception {
         
        if (!fileUpload.isEmpty())
        {
                 
                System.out.println("Saving file: " + fileUpload.getOriginalFilename());
                 
                if (!fileUpload.getOriginalFilename().equals("")) 
                	
						fileUpload.transferTo(new File(saveDirectory + fileUpload.getOriginalFilename()));

            }
        
        
 
        // returns to the view "success"
        return "success";
	}
	
	
	//uploading multiple files
	@RequestMapping(value = "/uploadMultiFiles",method = RequestMethod.POST)
    public String handleMultipleFileUpload(HttpServletRequest request,
            @RequestParam CommonsMultipartFile[] fileUpload) throws Exception {
         
        if (fileUpload != null && fileUpload.length > 0)
        {
                 
        	 for (CommonsMultipartFile aFile : fileUpload){
                 
                 System.out.println("Saving file: " + aFile.getOriginalFilename());
                  
                 if (!aFile.getOriginalFilename().equals("")) {
                     aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
                 }
             }
            }
       
        return "success";
	}

	
	@RequestMapping( "/uploadform")
	public String getUploadFormSingle()
	{
		return "uploadform";
	}
	@RequestMapping( "/uploadformmultiple")
	public String getUploadFormMultiple()
	{
		return "uploadform2";
	}
}
