<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- isErrorPage="true" 로 설정해야지 exception 기본 객체를 사용 가능. -->
<%@ page isErrorPage="true" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>에러 페이지</title>
</head>
<body>

에러 타입   : <%= exception.getClass().getName() %> <br> 
에러 메시지 : <%= exception.getMessage() %><br>

<p>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>

<!-- 
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하겠습니다.<br>
 -->

</body>
</html>



