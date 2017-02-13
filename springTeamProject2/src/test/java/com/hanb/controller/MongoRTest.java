package com.hanb.controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCode;
import com.hanb.dao.MongoTest;
import com.hanb.dao.RDao;
import com.hanb.enums.Board;
import com.hanb.service.RService;
import com.hanb.vo.MessageVo;

public class MongoRTest {
	@Test
	public void wordCloud()
	{
		MongoTest mdao = new MongoTest();
		ArrayList<MessageVo> wordCloud  = mdao.getList();
		
	}
	
	@Test
	public void wordTest () {
		String saveTextFileName = "C:/stsTest02/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/springTeamProject2/resources/upload/img/word.txt";
		String saveImgFileName = "C:/stsTest02/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/springTeamProject2/resources/upload/img/word.png";
		
		MongoTest mdao = new MongoTest();
		
		ArrayList<MessageVo> wordCloud  = mdao.getList();
		System.out.println(wordCloud.size());
		String str="";
		for(int i=0; i<wordCloud.size(); i++)
		{
			str+=wordCloud.get(i).getTitle()+" "+wordCloud.get(i).getContent();
			System.out.println(str);
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
		
		code.addString("textPath", saveTextFileName);
		code.addString("imgPath", saveImgFileName);
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
