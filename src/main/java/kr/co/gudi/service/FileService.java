package kr.co.gudi.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	private String root = "C:/";
	
	public void upload(MultipartFile uploadFile) {
		
		// 1. 파일명 추출
		String fileName = uploadFile.getOriginalFilename(); // image.jpg
		String ext = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		System.out.println("확장자 : "+ext);
		
		// 2. 파일명 변경 -> 중복되지 않는 이름으로 변경(현재시간을 밀리초로 환산한 이름)
		String newFileName = System.currentTimeMillis()+ext; // 1234567981234.jpg
				
		// 3. 파일 저장(java.nio)
		try {
			// 3-1. 객체로 부터 바이트 추출
			byte[] arr= uploadFile.getBytes();
			// 3-2. 저장할 경로와 파일명 지정
			Path path = Paths.get(root+"upload/"+newFileName);
			// 3-3. 파일 저장
			Files.write(path, arr);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}

	public List<String> list() {
		// c:/upload/ 의 파일 리스트 불러오기
		File file = new File(root+"upload/");
		String[] list= file.list();
		System.out.println(Arrays.toString(list));
		return Arrays.asList(list);
	}

	public void multiUpload(MultipartFile[] files) {
		// 1. 배열 안의 파일 객체를 하나씩 꺼내서		
		for (MultipartFile file : files) {			
			try {
				// 2. upload() 에서 했던 내용을 실행 해 준다.
				upload(file);// 파일용량이 적으면 1밀리세컨드 안에 여러 파일을 업로드 하여 이름이 같아질 수 있다.
				Thread.sleep(1);  // 강제로 1밀리 세컨드씩 지연을 시킨다.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

	public void delete(String file) {
		File f = new File(root+"upload/"+file);
		if(f.exists()) {
			f.delete();
		}
	}

}
