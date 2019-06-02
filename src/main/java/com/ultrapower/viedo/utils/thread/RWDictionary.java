package com.ultrapower.viedo.utils.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWDictionary {
  
	 private final Map<String, Object> map = new TreeMap<String, Object>();
	private ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
	private Lock rLock=lock.readLock();
	private Lock wLock=lock.writeLock();
	
	public Object getKey(String key) {
		try {
			rLock.lock();
			return map.get(key);
		} finally {
			// TODO: handle finally clause
			rLock.unlock();
		}
	}
	public Object[] allKey(String key) {
		rLock.lock();
		 try { return map.keySet().toArray(); } finally {
			// TODO: handle finally clause
			rLock.unlock();
		}
	}
	
	public void put(String key,Object val) {
		wLock.lock();
		try {
			map.put(key, val);
		} finally {
			// TODO: handle finally clause
			wLock.unlock();
		}
		
	}
	public void clear() {
		wLock.lock();
		try {
			map.clear();
		} finally {
			wLock.unlock();
		}
	}
}
