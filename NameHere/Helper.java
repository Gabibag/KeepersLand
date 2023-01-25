package NameHere;
import java.util.*;

import javax.swing.plaf.BorderUIResource.LineBorderUIResource;
public class Helper {
    public Helper() throws Exception{
        throw new Exception("Do not create objects of a helper class");
    }
    public static <T> List<T> getRandomElements(List<T> list, int amount){
        List<T> r = new ArrayList<T>();
        for(int i =0; i < amount; i++){
            r.add(list.get(Main.r.nextInt(list.size())));
        } 
        
        return r;
    }
}
