package com.hanb.dao;

import java.util.ArrayList;
import java.util.Date;


import org.bson.types.ObjectId;

import com.hanb.vo.MessageVo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class MongoTest {
	
	public ArrayList<MessageVo> getList()
	{
		ArrayList<MessageVo> list = null;
		DBCursor c = null;
		int currentPage = 1;
		
		try{
			Mongo mongo = new Mongo();
			DB db = mongo.getDB("mymessagedb");
			DBCollection collection=null;
			collection = db.getCollection("receivemessage");
			BasicDBObject ob = new BasicDBObject();
			
			c = collection.find(ob);
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
}
