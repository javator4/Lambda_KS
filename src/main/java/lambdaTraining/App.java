package lambdaTraining;

import java.util.*;

public class App
{
    public static void main( String[] args )
    {
        List<String> names = Arrays.asList("Kasia","Ania","Zosia","Bartek");
//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });


        Collections.sort(names, (String s1, String s2) -> s1.compareTo(s2));

        for(String name: names){
            System.out.println(name);
        }

        List<Artist> beatles = new ArrayList<>(SampleData.membersOfTheBeatles);
        Collections.sort(beatles, new Comparator<Artist>() {
            @Override
            public int compare(Artist o1, Artist o2) {

                return o1.getName().compareTo(o2.getName());
            }
        });
        for(Artist name: beatles){
            System.out.println(name);
        }

    }
}
