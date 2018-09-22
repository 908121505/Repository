package com.calf.module.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.OSSClient;
import com.calf.cn.utils.MD5Utils;
import com.calf.cn.utils.OSSUtil;
import com.calf.cn.utils.SFtpUtil;
import com.calf.cn.utils.UUIDUtils;
import com.calf.module.common.entity.FileUpload;

/**
 * 图片上传控制类
 * 
 * @author guixin
 *
 */
@Controller
@RequestMapping(value = "/upload")
public class ImgUploadController {

	private static String webPath = "";
	private static String bucketName = OSSUtil.getBucketName();

	@ResponseBody
	@RequestMapping("/appversion.htm")
	public FileUpload appversionkUpload(String id,
			@RequestParam("appversionFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传文件失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				//MultipartFile转换FILE
				CommonsMultipartFile cf= (CommonsMultipartFile)file; 
		        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
		       String md5Str = MD5Utils.getMD5(fi.getStoreLocation());//对文件进行加密
		        
				String imgFolder = "voice/banner";
				String fileName = System.currentTimeMillis() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), fileName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传文件成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+fileName);
					upload.setMd5Str(md5Str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}

	@ResponseBody
	@RequestMapping("/appversionSecond.htm")
	public FileUpload appversionkUploadSecond(String id,
										@RequestParam("appversionFileSecond") MultipartFile file,
										HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传文件失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				//MultipartFile转换FILE
				CommonsMultipartFile cf= (CommonsMultipartFile)file;
				DiskFileItem fi = (DiskFileItem)cf.getFileItem();
				String md5Str = MD5Utils.getMD5(fi.getStoreLocation());//对文件进行加密

				String imgFolder = "user/app";
				String fileName = System.currentTimeMillis() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), fileName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传文件成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+fileName);
					upload.setMd5Str(md5Str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}

	@ResponseBody
	@RequestMapping("/dynamicImg.htm")
	public FileUpload dynamicImgUpload(String id,
			@RequestParam("dynamicImg") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传文件失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				//MultipartFile转换FILE
				CommonsMultipartFile cf= (CommonsMultipartFile)file; 
		        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
		       String md5Str = MD5Utils.getMD5(fi.getStoreLocation());//对文件进行加密
		        
				String imgFolder = "icon/back";
				String fileName = System.currentTimeMillis() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), fileName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传文件成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+fileName);
					upload.setMd5Str(md5Str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}
	@ResponseBody
	@RequestMapping("/backgroundimg.htm")
	public FileUpload backgroundPictureUpload(String id,
			@RequestParam("backgroundimg") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传文件失败", null);
		
		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				//MultipartFile转换FILE
				CommonsMultipartFile cf= (CommonsMultipartFile)file; 
				DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
				String md5Str = MD5Utils.getMD5(fi.getStoreLocation());//对文件进行加密
				
				String imgFolder = "icon/back";
				String fileName = System.currentTimeMillis() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), fileName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传文件成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+fileName);
					upload.setMd5Str(md5Str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}
	
	/**
	 * icon上传
	 * 
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/banner.htm")
	public FileUpload bannerUpload(String id,
			@RequestParam("bannerFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个图片上传！");
		} else {
			ServletContext context = request.getSession().getServletContext();
			webPath = context.getRealPath("/");
			String savepath = "";
			try {
				
				
				
				String uuid = UUIDUtils.getUUID().toLowerCase();
				savepath = /* SmsUtil.imgUrl +*/"image_space" + File.separator
						+ uuid.substring(0, 3) + File.separator + uuid + ".jpg";
				File dir = new File(savepath);

				if (!dir.isDirectory()) {
					dir.mkdirs();
				}
				try {
					file.transferTo(dir);

				} catch (Exception e) {
					e.printStackTrace();
				}

				/*String dirs = uuid.substring(0, 3);

				String path = "";
				path = "/images/calfloan/loan/";
				SFtpUtil sftp = SFtpUtil.getSFtpUtil();
				sftp.createDir(path, dirs);

				imgurl = "http://loan.mydkguanjia.com/" + dirs + "/" + uuid
						+ ".jpg";
				sftp.upload(path + dirs + "/", savepath, uuid + ".jpg");
				*/
				upload.setResult("success");
				upload.setMsg("上传图片成功！");
				
				upload.setImgUrl(savepath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}

	@ResponseBody
	@RequestMapping("/loancate.htm")
	public FileUpload loancateUpload(String id,
			@RequestParam("loancateFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个图片上传！");
		} else {
			ServletContext context = request.getSession().getServletContext();
			webPath = context.getRealPath("/");
			String imgurl = "";
			String savepath = "";
			try {
				String uuid = UUIDUtils.getUUID().toLowerCase();
				savepath = webPath + "image_space" + File.separator
						+ uuid.substring(0, 3) + File.separator + uuid + ".jpg";
				File dir = new File(savepath);

				if (!dir.isDirectory()) {
					dir.mkdirs();
				}
				try {
					file.transferTo(dir);

				} catch (Exception e) {
					e.printStackTrace();
				}

				String dirs = uuid.substring(0, 3);

				String path = "";
				path = "/images/calfloan/loan/";
				SFtpUtil sftp = SFtpUtil.getSFtpUtil();
				sftp.createDir(path, dirs);

				imgurl = "http://loan.mydkguanjia.com/" + dirs + "/" + uuid
						+ ".jpg";
				sftp.upload(path + dirs + "/", savepath, uuid + ".jpg");
				upload.setResult("success");
				upload.setMsg("上传图片成功！");
				upload.setImgUrl(imgurl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}

	@ResponseBody
	@RequestMapping("/loaninfo.htm")
	public FileUpload loaninfoUpload(String id,
			@RequestParam("loaninfoFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个图片上传！");
		} else {
			ServletContext context = request.getSession().getServletContext();
			webPath = context.getRealPath("/");
			String imgurl = "";
			String savepath = "";
			try {
				String uuid = UUIDUtils.getUUID().toLowerCase();
				savepath = webPath + "image_space" + File.separator
						+ uuid.substring(0, 3) + File.separator + uuid + ".jpg";
				File dir = new File(savepath);

				if (!dir.isDirectory()) {
					dir.mkdirs();
				}
				try {
					file.transferTo(dir);

				} catch (Exception e) {
					e.printStackTrace();
				}

				String dirs = uuid.substring(0, 3);

				String path = "";
				path = "/images/calfloan/loan/";
				SFtpUtil sftp = SFtpUtil.getSFtpUtil();
				sftp.createDir(path, dirs);

				imgurl = "http://loan.mydkguanjia.com/" + dirs + "/" + uuid
						+ ".jpg";
				sftp.upload(path + dirs + "/", savepath, uuid + ".jpg");
				upload.setResult("success");
				upload.setMsg("上传图片成功！");
				upload.setImgUrl(imgurl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}

	@ResponseBody
	@RequestMapping("/productLabel.htm")
	public FileUpload productLabelUpload(String id,
			@RequestParam("productLabelFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);
		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				String imgFolder = "loan/product/productLabel";
				String imageName = UUIDUtils.getUUID() + "." + "jpg";
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传图片成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+imageName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}
	
	@ResponseBody
	@RequestMapping("/product.htm")
	public FileUpload productUpload(String id,
			@RequestParam("productImgFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				String imgFolder = "loan/product";
				String imageName = UUIDUtils.getUUID() + "." + "jpg";
				OSSClient ossClient = OSSUtil.getOSSClient();
				System.out.println(SFtpUtil.bucketName);
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传图片成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+imageName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
		
	}
	
	@ResponseBody
	@RequestMapping("/activitypicOneFile.htm")
	public FileUpload activitypicOneFile(String id,
			@RequestParam("picOneFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				String imgFolder = "activity/activityImg";
				String imageName = UUIDUtils.getUUID() + "." + "jpg";
				OSSClient ossClient = OSSUtil.getOSSClient();
				System.out.println(SFtpUtil.bucketName);
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传图片成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+imageName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
		
	}
	
	@ResponseBody
	@RequestMapping("/activitypicTwoFile.htm")
	public FileUpload activitypicTwoFile(String id,
			@RequestParam("picTwoFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				String imgFolder = "activity/activityImg";
				String imageName = UUIDUtils.getUUID() + "." + "jpg";
				OSSClient ossClient = OSSUtil.getOSSClient();
				System.out.println(SFtpUtil.bucketName);
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传图片成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+imageName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
		
	}
	
	
	/**
	 *   编辑器文件上传
	 *  
	 * @param upload
	 * @throws IOException 
	 *  
	 */
	@RequestMapping(value = "uploadPicture.htm", method = RequestMethod.POST)
	public String uploadPicture( Model model,HttpServletRequest request, HttpServletResponse response,@RequestParam("upload") CommonsMultipartFile file) throws IOException   {
	        response.setCharacterEncoding("GBK");  
	        PrintWriter out = response.getWriter();
	        String callback = request.getParameter("CKEditorFuncNum"); 
	        String imgUrl="";
		if(file!=null) {
			try{
						String imgFolder = "community/articlePost";
						String imageName = UUIDUtils.getUUID() + "." + "jpg";
						OSSClient ossClient = OSSUtil.getOSSClient();
						boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, bucketName, imgFolder);
						if(flag){
							imgUrl =SFtpUtil.ossUrl + "/"+imgFolder+"/"+imageName;
						}
					
			}catch(Exception e){
				 out.println("<script type=\"text/javascript\">");    
		            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");   
		            out.println("</script>");  
		            return null;
			}
		}else{
			    out.println("<script type=\"text/javascript\">");    
	            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件不能为空');");   
	            out.println("</script>");  
	            return null;
		}
		   
		 // 返回“图像”选项卡并显示图片  
        out.println("<script type=\"text/javascript\">");    
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'"  + imgUrl + "','')");    
        out.println("</script>"); 
		
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping("/articlePost1.htm")
	public FileUpload articlePostUpload1(String id,@RequestParam("articlePostFile1") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				String imgFolder = "community/articlePost";
				String imageName = UUIDUtils.getUUID() + "." + "jpg";
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传图片成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+imageName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
		
	}
	
	@ResponseBody
	@RequestMapping("/articlePost2.htm")
	public FileUpload articlePostUpload2(String id,@RequestParam("articlePostFile2") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				String imgFolder = "community/articlePost";
				String imageName = UUIDUtils.getUUID() + "." + "jpg";
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传图片成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+imageName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
		
	}
	
	@ResponseBody
	@RequestMapping("/articlePost3.htm")
	public FileUpload articlePostUpload3(String id,@RequestParam("articlePostFile3") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				String imgFolder = "community/articlePost";
				String imageName = UUIDUtils.getUUID() + "." + "jpg";
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传图片成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+imageName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
		
	}
	
	@ResponseBody
	@RequestMapping("/prizeTask.htm")
	public FileUpload prizeTaskUpload(String id,@RequestParam("prizeTaskFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				String imgFolder = "activity/prizeTask";
				String imageName = UUIDUtils.getUUID() + "." + "jpg";
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传图片成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+imageName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
		
	}
	
	@ResponseBody
	@RequestMapping("/smalLabelImg.htm")
	public FileUpload productSmalLabelImgUpload(String id,
			@RequestParam("smallLabelImgFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				String imgFolder = "loan/product/smalLabelImg";
				String imageName = UUIDUtils.getUUID() + "." + "jpg";
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传图片成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+imageName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}
	
	@ResponseBody
	@RequestMapping("/applyprocessImg.htm")
	public FileUpload applyprocessImgUpload(String id,
			@RequestParam("applyProcessFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				String imgFolder = "loan/product/applyprocessImg";
				String imageName = UUIDUtils.getUUID() + "." + "jpg";
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传图片成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+imageName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}
	
	@ResponseBody
	@RequestMapping("/applyprocess.htm")
	public FileUpload applyprocessUpload(String id,
			@RequestParam("applyprocessFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个图片上传！");
		} else {
			ServletContext context = request.getSession().getServletContext();
			webPath = context.getRealPath("/");
			String imgurl = "";
			String savepath = "";
			try {
				String uuid = UUIDUtils.getUUID().toLowerCase();
				savepath = webPath + "image_space" + File.separator
						+ uuid.substring(0, 3) + File.separator + uuid + ".jpg";
				File dir = new File(savepath);

				if (!dir.isDirectory()) {
					dir.mkdirs();
				}
				try {
					file.transferTo(dir);

				} catch (Exception e) {
					e.printStackTrace();
				}

				String dirs = uuid.substring(0, 3);

				String path = "";
				path = "/images/calfloan/loan/";
				SFtpUtil sftp = SFtpUtil.getSFtpUtil();
				sftp.createDir(path, dirs);

				imgurl = "http://loan.mydkguanjia.com/" + dirs + "/" + uuid
						+ ".jpg";
				sftp.upload(path + dirs + "/", savepath, uuid + ".jpg");
				upload.setResult("success");
				upload.setMsg("上传图片成功！");
				upload.setImgUrl(imgurl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}

	//银行上传
	@ResponseBody
	@RequestMapping("/bank.htm")
	public FileUpload bankUpload(String id,
			@RequestParam("bankFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个图片上传！");
		} else {
			ServletContext context = request.getSession().getServletContext();
			webPath = context.getRealPath("/");
			String imgurl = "";
			String savepath = "";
			try {
				String uuid = UUIDUtils.getUUID().toLowerCase();
				savepath = webPath + "image_space" + File.separator
						+ uuid.substring(0, 3) + File.separator + uuid + ".jpg";
				File dir = new File(savepath);

				if (!dir.isDirectory()) {
					dir.mkdirs();
				}
				try {
					file.transferTo(dir);

				} catch (Exception e) {
					e.printStackTrace();
				}

				String dirs = uuid.substring(0, 3);

				String path = "";
				path = "/images/calfloan/credit/";
				SFtpUtil sftp = SFtpUtil.getSFtpUtil();
				sftp.createDir(path, dirs);

				imgurl = "http://credit.mydkguanjia.com/" + dirs + "/" + uuid
						+ ".jpg";
				sftp.upload(path + dirs + "/", savepath, uuid + ".jpg");
				upload.setResult("success");
				upload.setMsg("上传图片成功！");
				upload.setImgUrl(imgurl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}
	//信用卡上传
	@ResponseBody
	@RequestMapping("/creditCard.htm")
	public FileUpload creditCardUpload(String id,
			@RequestParam("creditCardFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个图片上传！");
		} else {
			ServletContext context = request.getSession().getServletContext();
			webPath = context.getRealPath("/");
			String imgurl = "";
			String savepath = "";
			try {
				String uuid = UUIDUtils.getUUID().toLowerCase();
				savepath = webPath + "image_space" + File.separator
						+ uuid.substring(0, 3) + File.separator + uuid + ".jpg";
				File dir = new File(savepath);

				if (!dir.isDirectory()) {
					dir.mkdirs();
				}
				try {
					file.transferTo(dir);

				} catch (Exception e) {
					e.printStackTrace();
				}

				String dirs = uuid.substring(0, 3);

				String path = "";
				path = "/images/calfloan/credit/";
				SFtpUtil sftp = SFtpUtil.getSFtpUtil();
				sftp.createDir(path, dirs);

				imgurl = "http://credit.mydkguanjia.com/" + dirs + "/" + uuid
						+ ".jpg";
				sftp.upload(path + dirs + "/", savepath, uuid + ".jpg");
				upload.setResult("success");
				upload.setMsg("上传图片成功！");
				upload.setImgUrl(imgurl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}
	
	/*********************************/
	//信用卡上传背景图
	@ResponseBody
	@RequestMapping("/creditBackCard.htm")
	public FileUpload creditCardUploadBack(String id,
			@RequestParam("creditCardFileBack") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个图片上传！");
		} else {
			ServletContext context = request.getSession().getServletContext();
			webPath = context.getRealPath("/");
			String imgurl = "";
			String savepath = "";
			try {
				String uuid = UUIDUtils.getUUID().toLowerCase();
				savepath = webPath + "image_space" + File.separator
						+ uuid.substring(0, 3) + File.separator + uuid + ".jpg";
				File dir = new File(savepath);

				if (!dir.isDirectory()) {
					dir.mkdirs();
				}
				try {
					file.transferTo(dir);

				} catch (Exception e) {
					e.printStackTrace();
				}

				String dirs = uuid.substring(0, 3);

				String path = "";
				path = "/images/calfloan/credit/";
				SFtpUtil sftp = SFtpUtil.getSFtpUtil();
				sftp.createDir(path, dirs);

				imgurl = "http://credit.mydkguanjia.com/" + dirs + "/" + uuid
						+ ".jpg";
				sftp.upload(path + dirs + "/", savepath, uuid + ".jpg");
				upload.setResult("success");
				upload.setMsg("上传图片成功！");
				upload.setImgUrl(imgurl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}
	
	//主题卡
	@ResponseBody
	@RequestMapping("/themeType.htm")
	public FileUpload themeTypeUpload(String id,
			@RequestParam("themeTypeFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个图片上传！");
		} else {
			ServletContext context = request.getSession().getServletContext();
			webPath = context.getRealPath("/");
			String imgurl = "";
			String savepath = "";
			try {
				String uuid = UUIDUtils.getUUID().toLowerCase();
				savepath = webPath + "image_space" + File.separator
						+ uuid.substring(0, 3) + File.separator + uuid + ".jpg";
				File dir = new File(savepath);

				if (!dir.isDirectory()) {
					dir.mkdirs();
				}
				try {
					file.transferTo(dir);

				} catch (Exception e) {
					e.printStackTrace();
				}

				String dirs = uuid.substring(0, 3);

				String path = "";
				path = "/images/calfloan/credit/";
				SFtpUtil sftp = SFtpUtil.getSFtpUtil();
				sftp.createDir(path, dirs);

				imgurl = "http://credit.mydkguanjia.com/" + dirs + "/" + uuid
						+ ".jpg";
				sftp.upload(path + dirs + "/", savepath, uuid + ".jpg");
				upload.setResult("success");
				upload.setMsg("上传图片成功！");
				upload.setImgUrl(imgurl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}
	
	
	@ResponseBody
	@RequestMapping("/appinfo.htm")
	public FileUpload appinfoUpload(String id,
			@RequestParam("appinfoFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个图片上传！");
		} else {
			ServletContext context = request.getSession().getServletContext();
			webPath = context.getRealPath("/");
			String imgurl = "";
			String savepath = "";
			try {
				String uuid = UUIDUtils.getUUID().toLowerCase();
				savepath = webPath + "image_space" + File.separator
						+ uuid.substring(0, 3) + File.separator + uuid + ".jpg";
				File dir = new File(savepath);

				if (!dir.isDirectory()) {
					dir.mkdirs();
				}
				try {
					file.transferTo(dir);

				} catch (Exception e) {
					e.printStackTrace();
				}

				String dirs = uuid.substring(0, 3);

				String path = "";
				path = "/images/calfloan/loan/";
				SFtpUtil sftp = SFtpUtil.getSFtpUtil();
				sftp.createDir(path, dirs);

				imgurl = "http://loan.mydkguanjia.com/" + dirs + "/" + uuid
						+ ".jpg";
				sftp.upload(path + dirs + "/", savepath, uuid + ".jpg");
				upload.setResult("success");
				upload.setMsg("上传图片成功！");
				upload.setImgUrl(imgurl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}
	
	@ResponseBody
	@RequestMapping("/raiderscate.htm")
	public FileUpload raiderscateUpload(String id,
			@RequestParam("raiderscateFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个图片上传！");
		} else {
			ServletContext context = request.getSession().getServletContext();
			webPath = context.getRealPath("/");
			String imgurl = "";
			String savepath = "";
			try {
				String uuid = UUIDUtils.getUUID().toLowerCase();
				savepath = webPath + "image_space" + File.separator
						+ uuid.substring(0, 3) + File.separator + uuid + ".jpg";
				File dir = new File(savepath);

				if (!dir.isDirectory()) {
					dir.mkdirs();
				}
				try {
					file.transferTo(dir);

				} catch (Exception e) {
					e.printStackTrace();
				}

				String dirs = uuid.substring(0, 3);

				String path = "";
				path = "/images/calfloan/strategy/";
				SFtpUtil sftp = SFtpUtil.getSFtpUtil();
				sftp.createDir(path, dirs);

				imgurl = "http://strategy.mydkguanjia.com/" + dirs + "/" + uuid
						+ ".jpg";
				sftp.upload(path + dirs + "/", savepath, uuid + ".jpg");
				upload.setResult("success");
				upload.setMsg("上传图片成功！");
				upload.setImgUrl(imgurl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}
	
	@ResponseBody
	@RequestMapping("/raidersdetail.htm")
	public FileUpload raidersdetailUpload(String id,
			@RequestParam("raidersdetailFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个图片上传！");
		} else {
			ServletContext context = request.getSession().getServletContext();
			webPath = context.getRealPath("/");
			String imgurl = "";
			String savepath = "";
			try {
				String uuid = UUIDUtils.getUUID().toLowerCase();
				savepath = webPath + "image_space" + File.separator
						+ uuid.substring(0, 3) + File.separator + uuid + ".jpg";
				File dir = new File(savepath);

				if (!dir.isDirectory()) {
					dir.mkdirs();
				}
				try {
					file.transferTo(dir);

				} catch (Exception e) {
					e.printStackTrace();
				}

				String dirs = uuid.substring(0, 3);

				String path = "";
				path = "/images/calfloan/strategy/";
				SFtpUtil sftp = SFtpUtil.getSFtpUtil();
				sftp.createDir(path, dirs);

				imgurl = "http://strategy.mydkguanjia.com/" + dirs + "/" + uuid
						+ ".jpg";
				sftp.upload(path + dirs + "/", savepath, uuid + ".jpg");
				upload.setResult("success");
				upload.setMsg("上传图片成功！");
				upload.setImgUrl(imgurl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}

	@ResponseBody
	@RequestMapping("/message.htm")
	public FileUpload messageUpload(String id,
			@RequestParam("messageFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				String imgFolder = "usercenter/message";
				String imageName = UUIDUtils.getUUID() + "." + "jpg";
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传图片成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+imageName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
		
	}

	
	
	/**
	 * 上传文章照片
	 * @param id
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/article.htm")
	public FileUpload upload(String id, @RequestParam("articleFile") MultipartFile file, HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传图片失败", null);
		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				String imgFolder = "community/article";
				String imageName = UUIDUtils.getUUID() + "." + "jpg";
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName, bucketName, imgFolder);
				if(flag){
					upload.setResult("success");
					upload.setMsg("上传图片成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/"+imgFolder+"/"+imageName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}
	
	
	@ResponseBody
	@RequestMapping("/type.htm")
	public void type(){
		List<String> list = new ArrayList<String>();
		list.add("image1");
		list.add("image2");
		list.add("image3");
	}
	
	public static void main(String[] args){
		//File file = new File("C:\\Users\\wuliujun\\Desktop\\android包\\20170929\\loanmanager_release.apk");
		File file = new File("C:\\Users\\wuliujun\\Desktop\\patch_signed_7zip.apk");
       String md5Str = MD5Utils.getMD5(file);//对文件进行加密
       System.out.println(md5Str);  //7f93de7f1dd1b4b801700ce317904c03
	}
}
