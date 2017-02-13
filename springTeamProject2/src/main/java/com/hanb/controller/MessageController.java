package com.hanb.controller;


import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Basic;
import com.hanb.vo.LoginMemberVo;
import com.hanb.vo.MessageVo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

@Controller
public class MessageController {
	
	static int PAGESIZE =5;
	
	public String getPaging(int check,HttpSession session, String page)
	{
		String str = "";
		ArrayList<MessageVo> list = null;
		DBCursor c = null;
		int currentPage = 1;
		
		if(!page.equals("1"))
		{
			currentPage=Integer.parseInt(page);
		}
		
		int skip = (currentPage-1)*PAGESIZE;
		int totalCount = 0;
		int totalPage=0;
		
		try{
			Mongo mongo = new Mongo();
			DB db = mongo.getDB("mymessagedb");
			DBCollection collection=null;
			if(check==0)
			{
				collection = db.getCollection("sendmessage");
			}
			else
			{
				collection = db.getCollection("receivemessage");
			}
			BasicDBObject ob = new BasicDBObject();
			String myID = (String)session.getAttribute("messageID");
			if(check==0)
			{
				ob.append("fromID", myID);
			}
			else
			{
				ob.append("toID", myID);
			}
			c = collection.find(ob);
			totalCount = c.size();
			totalPage=(int)Math.ceil((double)totalCount/PAGESIZE);
			
			
			String s="";
			if(check==0)
			{
				s ="sendMessageList.do";
			}
			else
			{
				s ="receiveMessageList.do";
			}
			
			if(currentPage!=1)
			{
				str += "<li class=\"active\"><a href=\""+s+"?pNum="+(currentPage-1)+"\">Prev</a></li>";
			}
			str+= "<li><a href=\"#\">"+currentPage+"</a></li>";
			if(currentPage!=totalPage)
			{
				str += " <li class=\"active\"><a href=\""+s+"?pNum="+(currentPage+1)+"\">Next</a></li>";
			}
			
			if(totalPage==0)
			{
				str="";
			}
			
			mongo.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return str;
	}
	
	//check값이 1이면 받은메세지 0이면 보낸메세지..
	public ArrayList<MessageVo> getList(int check,HttpSession session, String page)
	{
		ArrayList<MessageVo> list = null;
		DBCursor c = null;
		int currentPage = 1;
		
		if(page!=null)
		{
			currentPage=Integer.parseInt(page);
		}
		
		int skip = (currentPage-1)*PAGESIZE;
		int totalCount = 0;
		int totalPage=0;
		
		try{
			Mongo mongo = new Mongo();
			DB db = mongo.getDB("mymessagedb");
			DBCollection collection=null;
			if(check==0)
			{
				collection = db.getCollection("sendmessage");
			}
			else
			{
				collection = db.getCollection("receivemessage");
			}
			BasicDBObject ob = new BasicDBObject();
			String myID = (String)session.getAttribute("messageID");
			if(check==0)
			{
				ob.append("fromID", myID);
			}
			else
			{
				ob.append("toID", myID);
			}
			c = collection.find(ob);
			totalCount = c.size();
			totalPage=(int)Math.ceil((double)totalCount/PAGESIZE);
			c.sort(new BasicDBObject().append("saved_at", -1)).skip(skip).limit(PAGESIZE);
			list = new ArrayList<MessageVo>();
			while(c.hasNext())
			{
				DBObject obj = c.next();
				MessageVo m = new MessageVo();
				m.set_id(((ObjectId)obj.get("_id")).toString());
				m.setToID((String)obj.get("toID"));
				m.setFromID((String)obj.get("fromID"));
				m.setTitle((String)obj.get("title"));
				m.setSaved_at((Date)obj.get("saved_at"));
				m.setContent((String)obj.get("content"));
				list.add(m);
			}
			mongo.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return list;
	}
	
	@RequestMapping("frontMessage.do")
	public ModelAndView frontMain(String m_id,HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		if(session.getAttribute("messageID") == null)
			session.setAttribute("messageID", m_id);
			
		mav.addObject("viewPage","front.jsp");
		mav.setViewName("member/message/main");
		return mav;
	}
	
	@RequestMapping("deleteMessage.do")
	public ModelAndView deleteMessage(String id,int check)
	{
		ModelAndView mav = new ModelAndView();
		try{
			Mongo mongo = new Mongo();
			DB db = mongo.getDB("mymessagedb");
			DBCollection collection = null;
			if(check==0)
			{
				collection=db.getCollection("sendmessage");
			}
			else
			{
				collection=db.getCollection("receivemessage");
			}
			BasicDBObject obj = new BasicDBObject();
			obj.append("_id", new ObjectId(id));
			collection.remove(obj);
			mongo.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		mav.addObject("viewPage","front.jsp");
		mav.setViewName("member/message/main");
		return mav;
	}
	
	@RequestMapping("detailMessage.do")
	public ModelAndView detailMessage(String id,int check)
	{
		ModelAndView mav = new ModelAndView();
		MessageVo m = new MessageVo();
		try{
			Mongo mongo = new Mongo();
			DB db = mongo.getDB("mymessagedb");
			DBCollection collection = null;
			if(check==0)
			{
				collection=db.getCollection("sendmessage");
			}
			else
			{
				collection=db.getCollection("receivemessage");
			}
			BasicDBObject ob = new BasicDBObject();
			ob.append("_id", new ObjectId(id));
			DBObject obj = collection.findOne(ob);
			
			m.set_id(((ObjectId)obj.get("_id")).toString());
			m.setToID((String)obj.get("toID"));
			m.setFromID((String)obj.get("fromID"));
			m.setTitle((String)obj.get("title"));
			m.setSaved_at((Date)obj.get("saved_at"));
			m.setContent((String)obj.get("content"));
			
			mongo.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		mav.addObject("m",m);
		mav.addObject("viewPage","detailMessage.jsp");
		mav.setViewName("member/message/main");
		return mav;
	}
	
	@RequestMapping("sendMessageList.do")
	public ModelAndView sendList(HttpSession session,String pNum)
	{
		ModelAndView mav = new ModelAndView();
		String page = "1";
		if(pNum!=null)
		{
			page=pNum;
		}
		ArrayList<MessageVo> list = getList(0,session,page);
		String str = getPaging(0, session, page);
		mav.addObject("sendList", list);
		mav.addObject("paging", str);
		mav.addObject("check",0);
		mav.addObject("viewPage","MessageList.jsp");
		mav.setViewName("member/message/main");
		return mav;
	}
	@RequestMapping("receiveMessageList.do")
	public ModelAndView receiveList(HttpSession session,String pNum)
	{
		ModelAndView mav = new ModelAndView();
		String page = "1";
		if(pNum!=null)
		{
			page=pNum;
		}
		ArrayList<MessageVo> list = getList(1,session,page);
		String str = getPaging(1, session, page);
		mav.addObject("sendList", list);
		mav.addObject("paging", str);
		mav.addObject("check",1);
		mav.addObject("viewPage","MessageList.jsp");
		mav.setViewName("member/message/main");
		return mav;
	}
	
	@RequestMapping(value="sendMessage.do",method=RequestMethod.GET)
	public ModelAndView send_form()
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("viewPage","sendMessage.jsp");
		mav.setViewName("member/message/main");
		return mav;
	}
	
	@RequestMapping(value="sendMessage.do",method=RequestMethod.POST)
	public ModelAndView send(MessageVo m)
	{
		ModelAndView mav = new ModelAndView();
		String toID=m.getToID();
		String fromID=m.getFromID();
		String title=m.getTitle();
		String content=m.getContent();
		Date saved_at=new Date();
		ObjectId _id = null;
		try{
			Mongo mongo = new Mongo();
			DB db = mongo.getDB("mymessagedb");
			DBCollection sendcollection = db.getCollection("sendmessage");
			DBCollection receivecollection = db.getCollection("receivemessage");
			BasicDBObject obj = new BasicDBObject();
			obj.append("toID", toID);
			obj.append("fromID", fromID);
			obj.append("title", title);
			obj.append("content", content);
			obj.append("saved_at", saved_at);
			sendcollection.insert(obj);
			receivecollection.insert(obj);
			_id = (ObjectId)obj.get("_id");
			mongo.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		mav.addObject("id", _id);
		mav.addObject("toID", toID);
		mav.addObject("saved_at", saved_at);
		mav.addObject("viewPage","sendMessageOk.jsp");
		mav.setViewName("member/message/main");
		return mav;
	}
}