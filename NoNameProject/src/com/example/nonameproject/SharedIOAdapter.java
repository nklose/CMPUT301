package com.example.nonameproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import com.google.gson.Gson;

import android.content.Context;
/**
 * Adapter responsible for task input and output to and from the crowdsourcer server
 */
public class SharedIOAdapter {

	private static HttpClient httpclient = new DefaultHttpClient();
	private static HttpPost httpPost = new HttpPost("http://crowdsourcer.softwareprocess.es/F12/Team1/");
    static Gson gson = new Gson();
	public static ArrayList<Task> getSharedTasks(Context context){
		//get list, turn list into some kind of array, iterate over array to get the details of them all
		//ArrayList<SharedTaskContainer> sharedTaskContainer = getSharedTaskContainer();
		return null;
	}
	
	
	public static void update(Context context, Task task) throws ClientProtocolException, IOException{
		List <BasicNameValuePair> nvps = new ArrayList <BasicNameValuePair>();
		nvps.add(new BasicNameValuePair("action", "update"));
		nvps.add(new BasicNameValuePair("id", task.getId()));
		nvps.add(new BasicNameValuePair("summary", task.getTitle()));
		nvps.add(new BasicNameValuePair("description", task.getDescription()));
		nvps.add(new BasicNameValuePair("content", gson.toJson(task)));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		HttpResponse response = httpclient.execute(httpPost);

	    String status = response.getStatusLine().toString();
	    HttpEntity entity = response.getEntity();

	    System.out.println(status);

//	    if (entity != null) {
//	        InputStream is = entity.getContent();
//	        String jsonStringVersion = convertStreamToString(is);
//	        Type taskType = Task.class;     
//	        newTask = gson.fromJson(jsonStringVersion, taskType);
//	    }
	    entity.consumeContent();
		
		
	}
	
	public static void add(Context context, Task task) throws ClientProtocolException, IOException{
		List <BasicNameValuePair> nvps = new ArrayList <BasicNameValuePair>();
		nvps.add(new BasicNameValuePair("action", "post"));
		nvps.add(new BasicNameValuePair("summary", task.getTitle()));
		nvps.add(new BasicNameValuePair("description", task.getDescription()));
		nvps.add(new BasicNameValuePair("content", gson.toJson(task)));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		HttpResponse response = httpclient.execute(httpPost);

	    String status = response.getStatusLine().toString();
	    HttpEntity entity = response.getEntity();

	    System.out.println(status);

//	    if (entity != null) {
//	        InputStream is = entity.getContent();
//	        String jsonStringVersion = convertStreamToString(is);
//	        Type taskType = Task.class;     
//	        newTask = gson.fromJson(jsonStringVersion, taskType);
//	    }
	    entity.consumeContent();
	}
}
