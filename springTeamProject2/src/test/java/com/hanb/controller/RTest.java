package com.hanb.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

import javax.mail.Address;

import org.junit.Test;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCallerOptions;
import com.github.rcaller.rstuff.RCode;
import com.github.rcaller.scriptengine.RCallerScriptEngine;
import com.hanb.dao.RDao;
import com.hanb.enums.Board;

public class RTest {
	@Test
	public void rTest() throws IOException{
		RCaller caller = RCaller.create();
		RCode code = RCode.create();
		code.clear();
		
		File file = code.startPlot();
		
		RDao dao = new RDao();
		Map<String, Integer> map = dao.getMonthsReport();
		String[] months = new String[12];
		int[] cnts = new int[12];
		for(int month = 1; month <= 12; month++) {
			int cnt = Integer.parseInt(String.valueOf(map.get(month+"월")));
			months[month-1] = month+"월";
			cnts[month-1] = cnt;
		}
		code.addIntArray("cnt", cnts);
		code.addStringArray("month", months);
		code.addRCode("ylimit = ceiling(max(cnt)*1.2);");
		code.addRCode("png(width = 800, height = 600)");
		code.addRCode("plot(cnt, type='o', ylim=c(0,ylimit) ,col='red',axes=FALSE,ann=FALSE);");
		code.addRCode("axis(1,at=1:12 ,lab=month);");
		code.addRCode("axis(2,ylim=c(0,ylimit));");
		code.addRCode("title(main='월별 매출 현황' , col.main='red',font.main=4);");
		code.addRCode("title(ylab='거래량',col.lab='blue');");
		code.addRCode("dev.off()");
		code.endPlot();
		
		caller.setRCode(code);
		caller.runOnly();
		code.showPlot(file);
	}
	
	@Test
	public void wordTest () {
		String saveTextFileName = "C:/stsTest02/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/springTeamProject2/resources/upload/img/word.txt";
		String saveImgFileName = "C:/stsTest02/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/springTeamProject2/resources/upload/img/word.png";
		
		RDao dao = new RDao();
		
		List<Map<String, Object>> wordCloud = dao.wordCloud(Board.QandA);
		System.out.println(wordCloud.size());
		String str="";
		for(int i=0; i<wordCloud.size(); i++)
		{
			str+=wordCloud.get(i).get("P_TITLE")+" "+wordCloud.get(i).get("P_CONTENT");
		}
		try{
			BufferedWriter ois= new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(saveTextFileName),"UTF-8"));
			ois.write(str);
			ois.flush();
			ois.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		RCaller caller = RCaller.create(); 
		RCode code = RCode.create();
		code.clear();
		saveTextFileName = saveTextFileName.replace("\\", "/");
		saveImgFileName = saveImgFileName.replace("\\", "/");
		
		code.addString("textPath", saveTextFileName);
		code.addString("imgPath", saveImgFileName);
		code.addRCode("Sys.setenv(JAVA_HOME=\"c:/Program Files/Java/jre1.8.0_101\")");
		code.addRCode("library(KoNLP)");
		code.addRCode("library(RColorBrewer)");
		code.addRCode("library(wordcloud)");
		code.addRCode("f<-file( textPath , encoding=\"utf-8\")");
		code.addRCode("textLines<-readLines(f)");
		code.addRCode("nouns<-sapply(textLines,extractNoun,USE.NAMES=F)");
		code.addRCode("close(f)");
		code.addRCode("png( imgPath )");
		code.addRCode("wordcloud<-table(unlist(nouns))");
		code.addRCode("palete=brewer.pal(9,\"Set3\")");
		code.addRCode("wordcloud(names(wordcloud),freq=wordcloud,scale=c(5,1),rot.per=0.25,min.freq=1,random.order=F,random.color=T,colors=palete)");
		code.addRCode("dev.off()");
		  
		code.endPlot();
		
		caller.setRCode(code);
		caller.runOnly();
	}
}
