package kr.or.nextit.web;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogTest {

	private static Logger logger = Logger.getLogger(LogTest.class); // 로그를 찍는 주체

	public static void main(String[] args) {

		// Trace

		// Debug

		// Info

		// Warn

		// Error

		// Fatal
		
		BasicConfigurator.configure();

		logger.setLevel(Level.WARN);
		
		logger.debug("debug 메시지 입니다.");
		logger.info("Info 메시지 입니다");
		logger.warn("Warn 메시지 입니다");
		logger.error("error 메시지 입니다");
		logger.fatal("fatal 메시지 입니다");
		

	}
}
