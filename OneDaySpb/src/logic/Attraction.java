package logic;

/**
 * Created by Alexander on 19.10.2014.
 */
public class Attraction {

    public Attraction(String[] attr){
        if (attr.length == 0)
            return;
        else
            data = attr;
    }

    public String[]  getData(){
        return data;
    }

    private String[] data;
}
