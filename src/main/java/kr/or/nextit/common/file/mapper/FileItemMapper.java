package kr.or.nextit.common.file.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.nextit.common.file.model.FileItem;

@Mapper
public interface FileItemMapper {

	// 파일 목록 가져오기

	public List<FileItem> selectFileItemList(Map<String, Object> paramMap) throws Exception;

	public FileItem selectFileItem(Map<String, Object> paramMap) throws Exception;

	// 파일 정보 등록
	public int insertFileItem(FileItem fileItem) throws Exception;

	// 파일정보 삭제
	public int deleteFileItem(Map<String, Object> paramMap) throws Exception;

	public int updateDownloadCnt(Map<String, Object> paramMap) throws Exception;

}
