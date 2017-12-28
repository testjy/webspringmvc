package kr.or.nextit.common.file.service.impl;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.nextit.common.file.mapper.FileItemMapper;
import kr.or.nextit.common.file.model.FileItem;
import kr.or.nextit.common.file.service.FileItemService;
import kr.or.nextit.common.util.ConstantUtil;

@Service("fileItemService")
public class FileItemServiceImpl implements FileItemService{

	@Autowired
	FileItemMapper fileItemMapper;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	private DecimalFormat decimalformate = new DecimalFormat();
	
	@Override
	public FileItem getFileItem(Map<String, Object> paramMap) throws Exception {
		return fileItemMapper.selectFileItem(paramMap);
	}

	@Override
	public int updateDownloadCnt(Map<String, Object> paramMap) throws Exception {
		return fileItemMapper.updateDownloadCnt(paramMap);
		}

	@Override
	public List<FileItem> uploadFiles(HttpServletRequest request, String biz_type) throws Exception {

		List<FileItem> fileItemList = new ArrayList<FileItem>();
		
		Collection<Part> parts = request.getParts();
		
		for(Part part : parts) {
			
			if(part.getHeader("Content-Disposition").contains("filename=")) {
				if(part.getSize() > 0) {
					// FileItem 객체 생성
					FileItem fileItem = new FileItem();
					fileItem.setFile_content_type(part.getContentType());
					fileItem.setFile_size(part.getSize());
					fileItem.setFile_fancy_size(getFancySize(part.getSize())); // ex)1200kb;
					fileItem.setFile_name(part.getSubmittedFileName());
					
					fileItem.setBiz_type(biz_type);
					fileItem.setFile_path(biz_type + "/" + dateFormat.format(new Date())); // bbs/20171208
					fileItem.setFile_save_name(	// 실제 디렉토리의 이름
							UUID.randomUUID().toString() + "_" + // 난수 발생(확장자 없음) 
							part.getSubmittedFileName());	// 진짜 이름
					
					// 파일 저장.
//					part.write(); // 해당 경로가 없으면 에러
					FileUtils.copyInputStreamToFile(part.getInputStream(),
							new File(ConstantUtil.UPLOAD_PATH + "/" + fileItem.getFile_path() + "/" + fileItem.getFile_save_name())
							);
					
					fileItemList.add(fileItem);
				}
			}
			
		}
		
		
		
		return fileItemList;
	}

	private String getFancySize(long size) {
		
		String fancy="";
		if(size < 1024) {
			fancy = decimalformate.format(size) + "bytes";
			
		}else if(size < 1024 * 1024){
			fancy = decimalformate.format(size / 10240.) + "KB";
		}else {
			fancy = decimalformate.format(size / (1024 * 1024.0)) + "MB";
		}
		
		return fancy;
	}
	
	
}
