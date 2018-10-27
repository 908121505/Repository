package com.calf.module.common.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.calf.cn.utils.SFtpUtil;
import com.calf.cn.utils.UUIDUtils;
import com.calf.module.common.entity.FileUpload;
import com.honglu.quickcall.common.api.code.AliYunFilePaths;
import com.honglu.quickcall.common.third.OSS.OSSUtil;

/**
 * 图片上传控制类
 *
 * @author guixin
 */
@Controller
@RequestMapping(value = "/upload")
public class ImgUploadController {
	private static final Logger log = LoggerFactory.getLogger(ImgUploadController.class);
	private static String bucketName = OSSUtil.getBucketName();

	@ResponseBody
	@RequestMapping("/appversion.htm")
	public FileUpload appversionkUpload(String id, @RequestParam("appversionFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传文件失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				// MultipartFile转换FILE
				CommonsMultipartFile cf = (CommonsMultipartFile) file;
				DiskFileItem fi = (DiskFileItem) cf.getFileItem();
				String md5Str = MD5Utils.getMD5(fi.getStoreLocation());// 对文件进行加密

				String imgFolder = "user/app";
				String fileName = System.currentTimeMillis() + "."
						+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), fileName,
						bucketName, imgFolder);
				if (flag) {
					upload.setResult("success");
					upload.setMsg("上传文件成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/" + imgFolder + "/" + fileName);
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
	public FileUpload appversionkUploadSecond(String id, @RequestParam("appversionFileSecond") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传文件失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
		} else {
			try {
				// MultipartFile转换FILE
				CommonsMultipartFile cf = (CommonsMultipartFile) file;
				DiskFileItem fi = (DiskFileItem) cf.getFileItem();
				String md5Str = MD5Utils.getMD5(fi.getStoreLocation());// 对文件进行加密

				String imgFolder = "user/app";
				String fileName = System.currentTimeMillis() + "."
						+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
				OSSClient ossClient = OSSUtil.getOSSClient();
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), fileName,
						bucketName, imgFolder);
				if (flag) {
					upload.setResult("success");
					upload.setMsg("上传文件成功！");
					upload.setImgUrl(SFtpUtil.ossUrl + "/" + imgFolder + "/" + fileName);
					upload.setMd5Str(md5Str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return upload;
	}

	/**
	 * 商户app端展示Banner的接口
	 *
	 * @param id
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/appBanner.htm")
	@ResponseBody
	public FileUpload appBannerUpload(String id, @RequestParam("appversionFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传文件失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
			return upload;
		}
		try {
			// MultipartFile转换FILE
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			String md5Str = MD5Utils.getMD5(fi.getStoreLocation());// 对文件进行加密

			String imgFolder = AliYunFilePaths.APP_BANNER;
			String fileName = System.currentTimeMillis() + "."
					+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

			// 阿里云客户端
			OSSClient ossClient = OSSUtil.getOSSClient();
			// 上传
			boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), fileName, imgFolder);
			if (flag) {
				upload.setResult("success");
				upload.setMsg("上传文件成功！");
				upload.setImgUrl(SFtpUtil.ossUrl + "/" + imgFolder + "/" + fileName);
				upload.setMd5Str(md5Str);
			}
		} catch (Exception e) {
			log.error("上传APP Banner图片失败：", e);
		}
		return upload;
	}

	/**
	 * 商户app端展示Banner的接口
	 *
	 * @param id
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/skillImageupload.htm")
	@ResponseBody
	public FileUpload skillImageupload(String id, @RequestParam("imageFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传文件失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
			return upload;
		}
		try {
			// MultipartFile转换FILE
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			String md5Str = MD5Utils.getMD5(fi.getStoreLocation());// 对文件进行加密

			String imgFolder = AliYunFilePaths.APP_SKILL;
			String fileName = System.currentTimeMillis() + "."
					+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

			// 阿里云客户端
			OSSClient ossClient = OSSUtil.getOSSClient();
			// 上传
			boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), fileName, imgFolder);
			if (flag) {
				upload.setResult("success");
				upload.setMsg("上传文件成功！");
				upload.setImgUrl(SFtpUtil.ossUrl + "/" + imgFolder + "/" + fileName);
				upload.setMd5Str(md5Str);
			}
		} catch (Exception e) {
			log.error("上传技能背景图片失败：", e);
		}
		return upload;
	}

	/**
	 * 商户app端展示Banner的接口
	 *
	 * @param id
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/skillImageupload1.htm")
	@ResponseBody
	public FileUpload skillImageupload1(String id, @RequestParam("imageFile1") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "上传文件失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
			return upload;
		}
		try {
			// MultipartFile转换FILE
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			String md5Str = MD5Utils.getMD5(fi.getStoreLocation());// 对文件进行加密

			String imgFolder = AliYunFilePaths.APP_SKILL;
			String fileName = System.currentTimeMillis() + "."
					+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

			// 阿里云客户端
			OSSClient ossClient = OSSUtil.getOSSClient();
			// 上传
			boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), fileName, imgFolder);
			if (flag) {
				upload.setResult("success");
				upload.setMsg("上传文件成功！");
				upload.setImgUrl(SFtpUtil.ossUrl + "/" + imgFolder + "/" + fileName);
				upload.setMd5Str(md5Str);
			}
		} catch (Exception e) {
			log.error("上传技能背景图片失败：", e);
		}
		return upload;
	}

	/**
	 * 编辑器文件上传
	 *
	 * @param file
	 * @throws IOException
	 */
	@RequestMapping(value = "uploadPicture.htm", method = RequestMethod.POST)
	public String uploadPicture(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("upload") CommonsMultipartFile file) throws IOException {
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		String callback = request.getParameter("CKEditorFuncNum");
		String imgUrl = "";
		if (file != null) {
			try {
				String imgFolder = AliYunFilePaths.EDITOR_FILE;
				String imageName = UUIDUtils.getUUID() + "." + "jpg";
				// 阿里云客户端
				OSSClient ossClient = OSSUtil.getOSSClient();
				// 上传
				boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), imageName,
						imgFolder);
				if (flag) {
					imgUrl = SFtpUtil.ossUrl + "/" + imgFolder + "/" + imageName;
				}

			} catch (Exception e) {
				out.println("<script type=\"text/javascript\">");
				out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'',"
						+ "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
				out.println("</script>");
				return null;
			}
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件不能为空');");
			out.println("</script>");
			return null;
		}

		// 返回“图像”选项卡并显示图片
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imgUrl + "','')");
		out.println("</script>");

		return null;
	}

	/**
	 * 随机用户头像上传文件
	 *
	 * @param id
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/fadeCustomerHeadImg.htm")
	@ResponseBody
	public FileUpload fadeCustomerHeadImg(String id, @RequestParam("appversionFile") MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileUpload upload = new FileUpload("error", "随机用户头像上传文件失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
			return upload;
		}
		try {
			// MultipartFile转换FILE
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			String md5Str = MD5Utils.getMD5(fi.getStoreLocation());// 对文件进行加密

			String imgFolder = AliYunFilePaths.FADE_USER_UPLOAD_HEAD_IMG;
			String fileName = System.currentTimeMillis() + "."
					+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

			// 阿里云客户端
			OSSClient ossClient = OSSUtil.getOSSClient();
			// 上传
			boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), fileName, imgFolder);
			if (flag) {
				upload.setResult("success");
				upload.setMsg("上传文件成功！");
				upload.setImgUrl(SFtpUtil.ossUrl + "/" + imgFolder + "/" + fileName);
				upload.setMd5Str(md5Str);
			}
		} catch (Exception e) {
			log.error("上传APP Banner图片失败：", e);
		}
		return upload;
	}

	/**
	 * 弹窗广告图片上传接口
	 *
	 * @param id
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/advertisement.htm")
	@ResponseBody
	public FileUpload advertisementImgUpload(@RequestParam("advertisementImgFile") MultipartFile file)
			throws IOException {
		FileUpload upload = new FileUpload("error", "上传文件失败", null);

		if (file == null) {
			upload.setMsg("请选择一个文件上传！");
			return upload;
		}
		try {
			// MultipartFile转换FILE
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			String md5Str = MD5Utils.getMD5(fi.getStoreLocation());

			String imgFolder = AliYunFilePaths.APP_ADVERTISEMENT;
			String fileName = System.currentTimeMillis() + "."
					+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

			// 阿里云客户端
			OSSClient ossClient = OSSUtil.getOSSClient();
			// 上传
			boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), fileName, imgFolder);
			if (flag) {
				upload.setResult("success");
				upload.setMsg("上传文件成功！");
				upload.setImgUrl(SFtpUtil.ossUrl + "/" + imgFolder + "/" + fileName);
				upload.setMd5Str(md5Str);

				log.info("上传弹窗广告图片成功：" + upload.getImgUrl());
			}
		} catch (Exception e) {
			log.error("上传弹窗广告图片失败：", e);
		}
		return upload;
	}
}
