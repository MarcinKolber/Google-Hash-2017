
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
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
public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("videos_worth_spreading.in");
		Scanner read = new Scanner(file);

		String init = read.nextLine();
		Scanner line = new Scanner(init);

		int numVideos = line.nextInt();
		int numEndpoints = line.nextInt();
		int numRequests = line.nextInt();
		int numCaches = line.nextInt();
		int sizeOfEach = line.nextInt();

		init = read.nextLine();
		line = new Scanner(init);
		ArrayList<Video> videos = new ArrayList<>();
		for (int i = 0; i < numVideos; i++) {
			int size = line.nextInt();
			videos.add(new Video(i, size));
		}

		// Creates the caches (The name should coordinate with index)
		ArrayList<Cache> caches = new ArrayList<>();
		for (int i = 0; i < numCaches; i++) {
			caches.add(new Cache(i, sizeOfEach));
		}

		// Cache to endpoint
		ArrayList<Endpoint> endpoints = new ArrayList<>();
		for (int i = 0; i < numEndpoints; i++) {
			init = read.nextLine();
			line = new Scanner(init);
			int lat = line.nextInt();
			int c = line.nextInt();
			endpoints.add(new Endpoint(i, lat));

			for (int j = 0; j < c; j++) {
				init = read.nextLine();
				line = new Scanner(init);
				int cache = line.nextInt();
				int cLatency = line.nextInt();

				endpoints.get(i).addCache(caches.get(cache), cLatency);
			}
		}

		// Number of requests for each video
		for (int i = 0; i < numRequests; i++) {
			init = read.nextLine();
			line = new Scanner(init);
			int video = line.nextInt();
			int end = line.nextInt();
			int request = line.nextInt();
			endpoints.get(end).addRequest(videos.get(video), request);
		}

		// For each video which endpoint asks for the most requests
		Hashtable<Video, Endpoint> priority = new Hashtable<>();
		for (Video v : videos) {
			int maxreq = 0;
			Endpoint maxEnd = null;
			for (Endpoint endp : endpoints) {
				int curreq;
				try {
					curreq = endp.getRequest(v);
				} catch (Exception e) {
					curreq = 0;
				}

				if (curreq > maxreq) {
					maxreq = curreq;
					maxEnd = endp;
				}
			}

			if (maxEnd != null) {
				priority.put(v, maxEnd);
			}

		}

		Endpoint endpoint = endpoints.get(0);
		endpoint.getSmallCache();
		Set<Video> thing = priority.keySet();
		for (Video v : thing) {
			
			
			Endpoint end = priority.get(v);
			Cache cache = end.getSmallCache();

			if (cache != null) {
				if (cache.addVideo(v)) {
					//cache.addVideo(v);
				}
			}

		}

		int usedCaches = 0;
		ArrayList<Cache> usedCachesList = new ArrayList<>();
		for (Cache c : caches) {
			if (c.getNumVideos() > 0) {
				usedCaches++;
				usedCachesList.add(c);
			}
		}
		System.out.println(usedCaches);
		for (Cache c : usedCachesList) {
			System.out.print(c.getName());
			System.out.print(" ");
			for (int i = 0; i < c.getNumVideos(); i++) {
				System.out.print(c.getVideo(i).getName() + " ");
			}
			System.out.println();
		}
	}
}
