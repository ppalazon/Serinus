package org.serinus.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jivesoftware.smack.packet.Message;
import org.serinus.data.Task;


public class SerinusParser {
	
	public Task parser(Message text)
	{
		Task task = new Task();
		
		task.setOriginal(text.getBody());
		task.setAuthor(text.getFrom());
		task.setLinks(parserLinks(text.getBody()));
		task.setUsers(parserUsers(text.getBody()));
		task.setTopics(parserTopics(text.getBody()));
		
		return task;
	}
	
	public List<String> parserLinks(String text)
	{
		List<String> links = new ArrayList<String>();
		
		Pattern urlPattern = Pattern.compile("((https?|ftp|gopher|telnet|file|notes|ms-help):((//)|(\\\\))+[\\w\\d:#@%/;$\\(\\)~_?\\+-=\\.&]*)");
		
		Matcher matcher = urlPattern.matcher(text);
		while(matcher.find())
		{
			links.add(matcher.group());
		}
		
		return links;
	}
	
	public List<String> parserUsers(String text)
	{
		List<String> users = new ArrayList<String>();
		
		Pattern userPattern = Pattern.compile("(@\\S*)");
		
		Matcher matcher = userPattern.matcher(text);
		while(matcher.find())
		{
			users.add(matcher.group());
		}
		
		return users;
	}
	
	public List<String> parserTopics(String text)
	{
		List<String> topics = new ArrayList<String>();
		
		Pattern topicPattern = Pattern.compile("(#\\S*)");
		
		Matcher matcher = topicPattern.matcher(text);
		while(matcher.find())
		{
			topics.add(matcher.group());
		}
		
		return topics;
	}

}
