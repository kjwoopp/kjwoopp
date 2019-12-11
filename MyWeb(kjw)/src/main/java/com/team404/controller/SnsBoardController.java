package com.team404.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.team404.command.SnsBoardVO;
import com.team404.snsboard.service.SnsBoardService;

@Controller
@RequestMapping("/snsBoard")
public class SnsBoardController {

	
	@Autowired
	SnsBoardService snsBoardService;
	
	@RequestMapping("/snsList")
	public String snsList() {
		return "snsBoard/snsList";
	}
	
	//파일 등록 메서드
	@RequestMapping("upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file,
						@RequestParam("content") String content
						
						){
		System.out.println(file);
		System.out.println(content);
		
		//1.날짜별로 20191211형식으로 upload폴더 아래에 폴더생성
		//2.uploadpath 는 날짜폴더를 포함한 경로
		//3.filerealname은 변경하기전 파일으름
		//4.filename은 변경해서 저장할 파일
		//5.fileloca 20191211형식으로 만들어진 폴더이름
		//6.transferTo()메서드를 이용해서 전송되온 파일을 해당날짜에 업로드
		//7.DB에 insert메서드를 통해서 값들을 저장 
		//8.성공시 화면에 success, 실패시 fail반환
		
		//파일명 변경 uuids.jpg
		UUID uuid = UUID.randomUUID();
		System.out.println("uuid: "+uuid);
		
		String uuids = uuid.toString().replaceAll("-", "");
		System.out.println("uuids: "+uuids);
		
		
		//2.uploadpath 는 날짜폴더를 포함한 경로
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		
		String fileLoca = date;
		String uploadPath = "D:\\KJW_spring\\upload\\"+fileLoca;
		File folder = new File(uploadPath);
		
		if (!folder.exists()) {
			try {
				folder.mkdir();
				System.out.println(date+"폴더생성");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else {
			System.out.println("이미 생성된 폴더입니다.");
		}
		
		//3.filerealname은 변경하기전 파일으름
		//6.transferTo()메서드를 이용해서 전송되온 파일을 해당날짜에 업로드
		String filerealname = file.getOriginalFilename(); //파일 이름
		String fileRealName = filerealname.substring(0,filerealname.lastIndexOf("."));
		String fileExtention = filerealname.substring(filerealname.lastIndexOf("."), filerealname.length());
		System.out.println("fileExtention: "+fileRealName);
		String fileName =uuids+fileExtention;
		File saveFile = new File("D:\\KJW_spring\\upload\\"+fileLoca+"\\"+ fileName);
		try {
			file.transferTo(saveFile);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		SnsBoardVO vo = new SnsBoardVO();
		vo.setWriter("kk111");
		vo.setUploadPath(uploadPath);
		vo.setFileLoca(fileLoca);
		vo.setFileName(fileName);
		vo.setFileRealName(fileRealName);
		vo.setContent(content);
		
		
		
		
		
		return snsBoardService.insert(vo)==true?"success":"false";
	}
	
	
	@RequestMapping("/view")
	@ResponseBody
	public byte[] view(@RequestParam("fileLoca") String fileLoca,
						@RequestParam("fileName") String fileName
						){
		
		System.out.println("연결");
		
		File file = new File("D:\\KJW_spring\\upload\\"+fileLoca+"\\"+fileName);
		byte[] result = null;
		
		try {
			//스프링의 파일데이터를 읽어서 바이트배열형으로 리턴하는 메서드 (매개변수로 File타입을 받는다)
			result = FileCopyUtils.copyToByteArray(file);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public ArrayList<SnsBoardVO> getList(){
		return snsBoardService.getList();
	}
	
	
	
	
}
