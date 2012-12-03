package com.example.nonameproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
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

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Adapter responsible for task input and output to and from the crowdsourcer server
 */
public class SharedTaskIOAdapter {

	private class OnlineContentFromList {
		public String id;
	}

	private class OnlineContentFromGet {
		public Task content;
		public String id;
	}

	private static HttpClient httpclient = new DefaultHttpClient();
	private static HttpPost httpPost = new HttpPost("http://crowdsourcer.softwareprocess.es/F12/CMPUT301F12T10/");
	
	static Gson gson = null;
	
	public static ArrayList<Task> getSharedTasks(Context context) throws Exception{

		if( gson == null ) {
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(TaskItem.class, new TaskItemAdapter());
			gson = builder.create();
		}
		
		String jsonString = new String();
		List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
		nvps.add( new BasicNameValuePair("action", "list"));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		HttpResponse response = httpclient.execute(httpPost);

		String status = response.getStatusLine().toString();
		HttpEntity entity = response.getEntity();

		System.out.println(status);

		if( entity != null){
			InputStream is = entity.getContent();
			jsonString = convertStreamToString(is);
		}

		entity.consumeContent();
		Type arrayType = new TypeToken<ArrayList<OnlineContentFromList>>(){}.getType();
		ArrayList<OnlineContentFromList> content = new ArrayList<OnlineContentFromList>();
		ArrayList<Task> taskList = new ArrayList<Task>();
		content = gson.fromJson(jsonString, arrayType);
		for( OnlineContentFromList o : content){
			List<BasicNameValuePair> nvpsGet = new ArrayList<BasicNameValuePair>();
			nvpsGet.add( new BasicNameValuePair("action", "get"));
			nvpsGet.add( new BasicNameValuePair("id", o.id));

			httpPost.setEntity(new UrlEncodedFormEntity(nvpsGet));
			response = httpclient.execute(httpPost);

			status = response.getStatusLine().toString();
			entity = response.getEntity();

			System.out.println(status);

			CompletedTaskController completedController = NoNameApp.getCompletedTaskController(context);
			
			if( entity != null) {
				InputStream is = entity.getContent();
				jsonString = convertStreamToString(is);
				OnlineContentFromGet get = gson.fromJson(jsonString, OnlineContentFromGet.class);
				Task task = get.content;
				task.setId(get.id);
				if( task.getCompleted()==false){
					taskList.add(task);
				} else {
					completedController.addTask(task);
				}
			}
		}
		return taskList;
	}


	public static void update(Task task) throws ClientProtocolException, IOException{
		
		if( gson == null ) {
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(TaskItem.class, new TaskItemAdapter());
			gson = builder.create();
		}
		
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
		entity.consumeContent();


	}

	public static void add(Task task) throws ClientProtocolException, IOException{
		
		if( gson == null ) {
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(TaskItem.class, new TaskItemAdapter());
			gson = builder.create();
		}
		
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
		entity.consumeContent();
	}

	public static void delete(Task task) throws ClientProtocolException, IOException {
		List <BasicNameValuePair> nvps = new ArrayList <BasicNameValuePair>();
		nvps.add(new BasicNameValuePair("action", "remove"));
		nvps.add(new BasicNameValuePair("id", task.getId()));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		HttpResponse response = httpclient.execute(httpPost);

		String status = response.getStatusLine().toString();
		HttpEntity entity = response.getEntity();

		System.out.println(status);
		entity.consumeContent();
	}

	private static  String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				is.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/*
	 * Resets CrowdSourcer by removing all shared task entries
	 */
	public static void nuke(){

	}
}
