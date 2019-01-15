import java.util.ArrayList;
import java.util.List;

public class BabyName implements Comparable<BabyName>{
    private String myName;
    private String myGender;
    private int myYear;
    private int myCount;
    //private ArrayList<int[]> rankings;
    private String myMeaning;

    /**Creates BabyName object with parameters
     *
     * @param name
     * @param gender
     * @param year
     * @param count
     * @param meaning
     */
    public BabyName(String name, String gender, int year, int count, String meaning){
        myName = name;
        myGender = gender;
        myYear = year;
        myCount = count;
        myMeaning = meaning;
    }

    /**
     * @return name of BabyName
     */
    public String getName(){ return myName; }

    /**
     * @return gender of BabyName
     */
    public String getGender(){ return myGender; }

    /**
     * @return year of BabyName
     */
    public int getYear(){ return myYear; }

    /**
     * @return number of babies named myName in myYear
     */
    public int getCount(){ return myCount; }

    /**
     * @return meaning of myName (if exists)
     */
    public String getMeaning(){ return myMeaning; }


    /**
     * compareTo function compares two BabyNames based on the BabyName count
     * if the counts are the same, compare alphabetically
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(BabyName other) {
        if(other != null){
            var res = Integer.compare(getCount(), other.getCount());
            if(res == 0){
                return(getName().compareTo(other.getName()));
            }
            else{
                return res*-1;
            }
        }
        else{
            return 1;
        }
    }
}
