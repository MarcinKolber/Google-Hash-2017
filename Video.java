
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathlah
 */
public class Video {
    private final int name;
    private final int size;
    
    public Video(int name, int size) {
        this.name = name;
        this.size = size;
    }
    
    public int getSize() {
        return size;
    }
    
    public int getName() {
        return name;
    }
}
