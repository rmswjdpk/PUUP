package com.hanb.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCode;
import com.hanb.dao.RDao;
import com.hanb.enums.Board;

@Service
public class RService {
	
	@Autowired
	private RDao dao;
	
	public String wordCloud(String path,String fileName,Board board) throws IOException
	{
		String saveTextFileName = path + "\\word.txt";
		String saveImgFileName = path + "\\"+ fileName;
		List<Map<String, Object>> wordCloud = dao.wordCloud(board);
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
		  
		code.endPlot();
		
		caller.setRCode(code);
		try{
			caller.runOnly();
		}catch(Exception e){
			return null;
		}
		
		return fileName;
	}
	
	public String averageDate(String path) throws IOException
	{
		Map<String, Object> avgDate = dao.avgDate();
		if(avgDate == null){
			avgDate = new HashMap<String, Object>();
			avgDate.put("SAMPLE", 0);
			avgDate.put("PRODUCT", 0);
		}
		int sample = Integer.parseInt(String.valueOf(avgDate.get("SAMPLE")));
		int product = Integer.parseInt(String.valueOf(avgDate.get("PRODUCT")));
		RCaller caller = RCaller.create();
		RCode code = RCode.create();
		code.clear();
		
		int [] date = {sample,product};
		
		code.addIntArray("date", date);
		File file = code.startPlot();
		
		code.addRCode("x <- matrix(date)");
		code.addRCode("barplot(x,main=\"PUP신청 평균기한\",beside=T,names=c(\"SAMPLE\",\"PRODUCT\"), col=c(\"green\"),ylim=c(0,15))");
		code.endPlot();
		
		caller.setRCode(code);
		try{
			caller.runOnly();
		}catch(Exception e){
			return null;
		}
		
		File saveFile = new File(path+"/"+file.getName());
		file.renameTo(saveFile);
	
		return saveFile.getName();
	}
	
	public String memberAge(String path) throws IOException{
		List<Map<String, Object>> agelist = dao.ageList();
		if(agelist.size() == 0){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("BIRTH", 0);
			map.put("SUM", 1);
			agelist.add(map);
		}
		String birth[]= new String[agelist.size()];
		double[] sum = new double[agelist.size()];
		for(int i=0; i<agelist.size(); i++)
		{
			int j = Integer.parseInt(String.valueOf(agelist.get(i).get("BIRTH")));
			sum[i] =  Double.parseDouble(String.valueOf(agelist.get(i).get("SUM")));
			if(j==8)
			{
			birth[i]="80년대생";
			}
			else if(j==9)
			{
			birth[i]="90년대생";
			}
			else if(j==0)
			{
			birth[i]="00년대생";
			}
			else if(j==7)
			{
			birth[i]="70년대생";
			}
			else if(j==6)
			{
			birth[i]="60년대생";
			}
			else if(j==5)
			{
			birth[i]="50년대생";
			}
			else
			{
			birth[i]="데이터없음";	
			}
		}
		
		RCaller caller = RCaller.create(); 
		RCode code = RCode.create();
		code.clear();
		if(sum.length == 0){
			sum = new double[1];
			birth = new String[1];
			sum[0] = 1.0;
			birth[0] = "데이터없음";
		}
		code.addDoubleArray("p1", sum);
		code.addStringArray("birth", birth);
		
		File file = code.startPlot();
		
		code.addRCode("library(plotrix)");
		code.addRCode("pie(p1,radius=1,init.angle=90)");
		code.addRCode("f_day <- round(p1/sum(p1)*100,1)");
		code.addRCode("f_label <- paste(f_day,\"%\")");
		code.addRCode("pie3D(p1,main=\"신청인원 연령대\",col=rainbow(length(p1)),");
		code.addRCode("cex=0.5,labels=f_label)");
		code.addRCode("legend(0.5,1,birth,cex=0.6,");
		code.addRCode("fill=rainbow(length(p1)))");
		code.endPlot();
		
		caller.setRCode(code);
		try{
			caller.runOnly();
		}catch(Exception e){
			return null;
		}
		
		File saveFile = new File(path+"/"+file.getName());
		file.renameTo(saveFile);
		return saveFile.getName();
	}
	
	public String getMemberContractReportGraphFile(String m_id , String savePath) throws IOException{
		RCaller caller = RCaller.create();
		RCode code = RCode.create();
		code.clear();
		
		File file = null;
		file = code.startPlot();
		Map<String, String> map = dao.getMemberContractCount(m_id);
		code.addInt("success", Integer.parseInt(String.valueOf(map.get("SUCCESS"))));
		code.addInt("fail", Integer.parseInt(String.valueOf(map.get("FAIL"))));
		code.addRCode("p1 <- c( success , fail );");
		code.addRCode("pct <- round( p1/sum(p1) * 100 , 1);");
		code.addRCode("lab1 <- c('성공','실패');");
		code.addRCode("lab2 <- paste( lab1, '\n' , pct , '%');");
		code.addRCode("pie( p1 , label=lab2 , radius=0.5 , init.angle=90 , col=c('green','red') );");
		code.endPlot();
		
		caller.setRCode(code);
		try{
			caller.runOnly();
		}catch(Exception e){
			return null;
		}
		
		File saveFile = new File(savePath + "/"+m_id+"ContractReportGraph.png" );
		saveFile.delete();
		file.renameTo(saveFile);
		return saveFile.getName();
	}
	
	public String getMonthReport(String savePath) throws IOException{
		RCaller caller = RCaller.create();
		RCode code = RCode.create();
		code.clear();
		
		File file = code.startPlot();
		
		RDao dao = new RDao();
		Map<String, Integer> map = dao.getMonthsReport();
		String[] months = new String[12];
		int[] cnts = new int[12];
		for(int month = 1; month <= 12; month++){
			int cnt = Integer.parseInt(String.valueOf(map.get(month+"월")));
			months[month-1] = month+"월";
			cnts[month-1] = cnt;
		}
		code.addIntArray("cnt", cnts);
		code.addStringArray("month", months);
		code.addRCode("ylimit = ceiling(max(cnt)*1.2);");
		code.addRCode("plot(cnt, type='o', ylim=c(0,ylimit) ,col='red',axes=FALSE,ann=FALSE);");
		code.addRCode("axis(1,at=1:12 ,lab=month);");
		code.addRCode("axis(2,ylim=c(0,ylimit));");
		code.addRCode("title(main='월별 매출 현황' , col.main='red',font.main=4);");
		code.addRCode("title(ylab='거래량',col.lab='blue');");
		code.endPlot();
		
		caller.setRCode(code);
		try{
			caller.runOnly();
		}catch(Exception e){
			return null;
		}
		
		File saveFile = new File(savePath + "/" + LocalDateTime.now().getYear() + "_MonthReport" );
		saveFile.delete();
		file.renameTo(saveFile);
		return saveFile.getName();
	}
}
