package jdk;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.crypto.Data;

class CachedData {
 Object data;
 volatile boolean cacheValid;
 final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

 void processCachedData() {
  rwl.readLock().lock();
  if (!cacheValid) {
   // Must release read lock before acquiring write lock
   rwl.readLock().unlock();
   rwl.writeLock().lock();
   try {
    // Recheck state because another thread might have
    // acquired write lock and changed state before we did.
    if (!cacheValid) {
     data = "use data ";
     cacheValid = true;
    }
    // Downgrade by acquiring read lock before releasing write lock
    rwl.readLock().lock();
   } finally {
    rwl.writeLock().unlock(); // Unlock write, still hold read
   }
  }

  try {
   //       use(data);
  } finally {
   rwl.readLock().unlock();
  }
 }
}


class RWDictionary {
 private final Map<String, Data> m = new TreeMap<String, Data>();
 private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
 private final Lock r = rwl.readLock();
 private final Lock w = rwl.writeLock();

 public Data get(String key) {
   r.lock();
   try { return m.get(key); }
   finally { r.unlock(); }
 }
 public String[] allKeys() {
   r.lock();
   try { return (String[]) m.keySet().toArray(); }
   finally { r.unlock(); }
 }
 public Data put(String key, Data value) {
   w.lock();
   try { return m.put(key, value); }
   finally { w.unlock(); }
 }
 public void clear() {
   w.lock();
   try { m.clear(); }
   finally { w.unlock(); }
 }
}