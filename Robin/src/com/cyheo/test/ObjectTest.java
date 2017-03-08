package com.cyheo.test;

import java.util.HashMap;
import java.util.Map;

public class ObjectTest {

	private Map<String, String> map = null;
	
	public ObjectTest() {
		map = new HashMap<String, String>();
	}
	
	public Map<String, String> getMap() {
		return map;
	}

	public static void main(String[] args) {

		ObjectTest test = new ObjectTest();
		setMap(test.getMap());
		setMap(test.getMap());
		setMap(test.getMap());
		setMap(test.getMap());

		Map<String, String> map = test.getMap();
		for(int i=1; i<map.size()+1; i++) {
			System.out.println(map.get(""+i));
		}
	}

	public static void setMap(Map<String, String> map) {
		map.put(""+(map.size()+1),  ""+(map.size()+1)+"번째");
	}

}