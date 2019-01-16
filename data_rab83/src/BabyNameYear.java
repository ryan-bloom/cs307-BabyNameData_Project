import java.util.ArrayList;
import java.util.List;

/**
 * Class initially created and used but after refactoring, class no longer used in Bins.java
 * Use of this class was replaced by use of namesMap HashMap in Bins.java
 */
public class BabyNameYear{
    private int myYear;
    private ArrayList<BabyName> myNamesInfo;

    /**
     * Create a new BabyNameYear object -- array of all baby names and their information for a given year
     * @param year
     * @param name
     */
    public BabyNameYear(int year, BabyName name){
        myYear = year;
        myNamesInfo = new ArrayList();
        myNamesInfo.add(name);
    }

    /**
     * @return year
     */
    public int getYear(){ return myYear; }

    /**
     * @return return array of BabyName objects
     */
    public ArrayList<BabyName> getNames (){ return myNamesInfo; }

    public void add(BabyName name){
        myNamesInfo.add(name);
    }

}
