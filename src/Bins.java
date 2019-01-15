import java.util.*;
import java.io.File;

/**
 * Runs a number of algorithms that analyze data from text files of baby name information.
 */
public class Bins{
    //NOT USED IN BASIC VERSION YET
    public static final String DATA_FILE = "baby_name_meanings.txt";

    //public static ArrayList<BabyNameYear> namesByYear = new ArrayList<>();
    public static HashMap<Integer, ArrayList<BabyName>> namesMap = new HashMap<>();
    public static HashMap<Integer, ArrayList<BabyName>> femaleNamesMap = new HashMap<>();
    public static HashMap<Integer, ArrayList<BabyName>> maleNamesMap = new HashMap<>();

    //process
    public List<String> readData (Scanner input) {
        var results = new ArrayList<String>();
        while (input.hasNext()) {
            results.add(input.nextLine());
        }
        return results;
    }

    public void fillMap(String gender, int year, BabyName name){
        var map = femaleNamesMap;
        if(gender.toUpperCase().equals("M")){
            map = maleNamesMap;
        }
        if(map.keySet().contains(year)){
            var tempList = map.get(year);
            tempList.add(name);
            map.put(year, tempList);
        }
        else{
            ArrayList<BabyName> newList = new ArrayList<>();
            newList.add(name);
            map.put(year, newList);
        }
    }

    //Output

    /**
     * paseNamesMap is called by 2 other functions and gives rankings for the name/gender combo in the desired year
     * @param name
     * @param gender
     * @param year
     */
    public void parseNamesMap(String name, String gender, int year){
        //var namesList = namesMap.get(year);
        var namesList = femaleNamesMap.get(year);
        if(gender.toUpperCase().equals("M")){
            namesList = maleNamesMap.get(year);
        }
        Collections.sort(namesList);
        int size = namesList.size();
        for(int j=0; j<size; j++){
            if(namesList.get(j).getName().equals(name.toUpperCase())
                    && namesList.get(j).getGender().toUpperCase().equals(gender.toUpperCase())){
                int res = j+1;
                System.out.println("YEAR: " + year + " RANKING: " + res + " of " + size);
                break;
            }
            if(j==namesList.size()-1){
                System.out.println("YEAR: " + year + " RANKING: NA of " + size);
            }
        }
    }

    /**
     * getAllRankings calls parseNamesMap and returns rankings for name/gender combo for every year in the dataset
     * @param name
     * @param gender
     */
    public void getAllRankings(String name, String gender){
        var map = femaleNamesMap;
        if(gender.toUpperCase().equals("M")){
            map = maleNamesMap;
        }
        for(HashMap.Entry<Integer, ArrayList<BabyName>> entry : map.entrySet()){
            int year = entry.getKey();
            parseNamesMap(name, gender, year);
        }
    }

    /**
     * getRankingsByYear calls parseNamesMap and returns the rankings for name/gender combos in the desired year range
     * @param name
     * @param gender
     * @param start
     * @param end
     */
    public void getRankingsByYear(String name, String gender, int start, int end){
        for(int i=start; i<end+1; i++){
            int year = i;
            parseNamesMap(name, gender, year);
        }
    }

    /**
     * The main program.
     */
    public static void main (String[] args) {
        //Input section -- read inputs (INPUTS Ipo)
        var b = new Bins();
        var inputMeanings = new Scanner(Bins.class.getClassLoader().getResourceAsStream(DATA_FILE));
        //Need to add this to BabyName objects
        var dataMeanings = b.readData(inputMeanings);
        //System.out.println(dataMeanings);
        File folder = new File("data");
        File[] listOfFiles = folder.listFiles();

        //iterate through all files to process inputs -- PROCESS (iPo)
        for(int i=0; i<listOfFiles.length; i++){
            if(listOfFiles[i].isFile() && listOfFiles[i].getName().startsWith("yob")){
                String DATA_FILE2 = listOfFiles[i].getName();
                int year = Integer.parseInt(listOfFiles[i].getName().substring(3,7));
                var input = new Scanner(Bins.class.getClassLoader().getResourceAsStream(DATA_FILE2));
                var data = b.readData(input);

                //Loop through all lines in data
                for(int j=0; j<data.size(); j++){
                    String[] tempData = data.get(j).split(",");
                    String name = tempData[0].toUpperCase();
                    String gen = tempData[1].toUpperCase();
                    int count = Integer.parseInt(tempData[2]);

                    BabyName tempName = new BabyName(name, gen, year, count, "NA");
                    //If this year has already been accounted for -- add to that arrayList of babynames
                    b.fillMap(gen, year, tempName);
                    /*if(gen.equals("F")){
                        if(femaleNamesMap.keySet().contains(year)){
                            var tempList = femaleNamesMap.get(year);
                            tempList.add(tempName);
                            femaleNamesMap.put(year, tempList);
                        }
                        else{
                            ArrayList<BabyName> newList = new ArrayList<>();
                            newList.add(tempName);
                            femaleNamesMap.put(year, newList);
                        }
                    }
                    else{
                        if(maleNamesMap.keySet().contains(year)){
                            var tempList = maleNamesMap.get(year);
                            tempList.add(tempName);
                            maleNamesMap.put(year, tempList);
                        }
                        else{
                            ArrayList<BabyName> newList = new ArrayList<>();
                            newList.add(tempName);
                            maleNamesMap.put(year, newList);
                        }
                    }*/
                    /*if(namesMap.keySet().contains(year)){
                        var tempList = namesMap.get(year);
                        tempList.add(tempName);
                        namesMap.put(year, tempList);
                    }
                    //If year not in namesMap (either because it is empty or new year, create new input
                    else{
                        ArrayList<BabyName> newList = new ArrayList<>();
                        newList.add(tempName);
                        namesMap.put(year, newList);
                    }*/
                }
            }
        }

        //TESTS- OUTPUTS (ipO)
        /**
         * Test 1: find all rankings for "Ryan" "M"
         * 2018test.txt = ranking 4
         * 2019test.txt = unranked (NA) (name/gender combo not in the file)
         * 2020test.txt = ranking 1
         */
        System.out.println("TEST 1: ALL RANKINGS RYAN/M");
        b.getAllRankings("ryan", "m");
        System.out.println("\n");

        /**
         * TEST 2: Find all rankings for "Ariana" "F"
         * 2018test.txt = ranking 1
         * 2019test.txt = ranking 4
         * 2020test.txt = ranking 3
         */
        System.out.println("TEST 2: ALL RANKINGS ARIANA/F");
        b.getAllRankings("Ariana","F");
        System.out.println("\n");

        /**
         * Test 3: Find rankings for "Josh" "M" between the years 2012-2019
         * 2018test.txt = ranking 5
         * 2019test.txt = ranking 1
         */
        System.out.println("TEST 3: 2012-2019 RANKINGS JOSH/M");
        b.getRankingsByYear("Josh", "m", 2012, 2019);
        System.out.println("\n");

        /**
         * Test 4: Find rankings for "Suzanne" "F" between years 2010-2020
         * 2018test.txt = ranking 3
         * 2019test.txt = ranking 3
         * 2020test.txt = ranking 4
         */
        System.out.println("TEST 4: 2010-2020 RANKINGS SUZANNE/F");
        b.getRankingsByYear("Suzanne", "f", 2010, 2020);
        System.out.println("\n");
    }
}

