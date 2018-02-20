
import java.util.ArrayList;
import java.util.Hashtable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathlah
 */
public class Cache {
    private final int name;
    private final int capacity;
    private int size;
    private ArrayList<Video> videos;
    
    
    public Cache(int name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.size = capacity;
        videos = new ArrayList<>();
    }
    
    public int getName() {
        return name;
    }
    
    public int getSize() {
        return size;
    }
    
    public boolean addVideo(Video v) {
        if (size - v.getSize() >= 0) {
            videos.add(v);
            this.size -= v.getSize();
            return true;
        } else {
            return false;
        }
    }
    
    public int getNumVideos() {
    	return videos.size();
    }
    
    public Video getVideo(int index) {
    	
    	if(videos.isEmpty()){
    		return null;
    	} else  {
    	
    	return videos.get(index);
    	}
    }
}
