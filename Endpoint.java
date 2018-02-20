
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathlah
 */
public class Endpoint {
	private final int name;
	private final int latency;
	private Hashtable<Cache, Integer> caches; // Cache, latency
	private Hashtable<Video, Integer> requests; // video, requests
	private ArrayList<Cache> sortedCaches;

	public Endpoint(int name, int latency) {
		this.name = name;
		this.latency = latency;
		this.caches = new Hashtable<>();
		this.requests = new Hashtable<>();
		this.sortedCaches = new ArrayList<>();
	}

	public Cache getSmallCache() {

		ArrayList<Integer> caches1 = new ArrayList<>(caches.values());

		Set<Cache> set = caches.keySet();
		int min = Integer.MAX_VALUE;
		Cache minCache = null;
		for (Cache key : set) {
			int cur = caches.get(key);
			Cache tempCache = key;
			if (cur < min) {
				min = cur;
				minCache = key;
			}
		}
		return minCache;

	}

	public void addCache(Cache cache, int latency) {
		caches.put(cache, latency);
	}

	public void addRequest(Video v, int num) {
		requests.put(v, num);
	}

	public int getName() {
		return name;
	}

	public int getLatency() {
		return latency;
	}

	public int getLat(Cache c) {
		return caches.get(c);
	}

	public int getRequest(Video v) {
		return requests.get(v);
	}
}
