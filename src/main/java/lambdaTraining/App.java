package lambdaTraining;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FunctionalInterface
interface Square {
    int square(int x);
}

public class App {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Kasia", "Ania", "Zosia", "Bartek");
//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });


        Collections.sort(names, (String s1, String s2) -> s1.compareTo(s2));

        for (String name : names) {
            System.out.println(name);
        }

        List<Artist> beatles = new ArrayList<>(SampleData.membersOfTheBeatles);
        Collections.sort(beatles, new Comparator<Artist>() {
            @Override
            public int compare(Artist o1, Artist o2) {

                return o1.getName().compareTo(o2.getName());
            }
        });
        for (Artist name : beatles) {
            System.out.println(name);
        }

        Square square = (int x) -> x * x;
        System.out.println(square.square(2));

        String[] a = {"cat", "dog", "mouse", "rat", "pig", "rabbit", "hamster", "parrot"};
        List<String> animals = Arrays.asList(a);
        //Tradycyjna pętla
        for (String animal : animals) {
            System.out.print(animal + "; ");
        }
        System.out.println();

        // Wyrażenie lambda
        animals.forEach((animal) -> System.out.print(animal + "; "));
        System.out.println();

        animals.forEach(System.out::println);

        List<String> fruits =
                Arrays.asList("apple", "banana", "cherry", "plum", "pear", "pinapple");
        List<String> result = fruits.stream()
                .filter((x) -> x.startsWith("a"))
                .map((x) -> x.toUpperCase())
                .collect(Collectors.toList());
        result.forEach(s -> System.out.println(s));

        long number = SampleData.membersOfTheBeatles.stream()
                .filter(artist -> artist.getNationality()
                        .equals("UK"))
                .count();
        System.out.println(number);

        List<String> stringList = Stream.of("a", "b", "c")
                .map(x -> x.toUpperCase())
                .collect(Collectors.toList());

        List<String> dataWithNumbers = Arrays.asList("a", "122as", "a23", "b32ss", "3a");
        for (String data : dataWithNumbers) {
            if (Character.isDigit(data.charAt(0))) {
                System.out.println(data);
            }
        }

        List<String> newData = dataWithNumbers.stream()
                .filter(x -> Character.isDigit(x.charAt(0)))
                .collect(Collectors.toList());

        for (String data : newData) {
            System.out.println(data);
        }

        System.out.println();

        List<Integer> flat = Stream.of(Arrays.asList(2, 3, 4), Arrays.asList(33, 22, 11))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
        flat.forEach(System.out::println);

        List<Track> tracks = Arrays.asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));

        Track minTrack = tracks.stream().min(Comparator.comparing(track -> track.getLength())).get();

        Collections.sort(tracks, Comparator.comparingInt(Track::getLength));

        System.out.println(minTrack.getLength());
        tracks.forEach(System.out::println);

        int count = Stream.of(1, 2, 3, 4, 5).reduce(0, (acc, element) -> acc + element);
        System.out.println(count); //adding elements on the list!

        SampleData.membersOfTheBeatles.stream()
                .forEach(member -> System.out.println(member.getName() + " " + member.getNationality()));

        List<String> beatlesNames = SampleData.membersOfTheBeatles.stream()
                .map(artist -> artist.getName()) //map collection to list of strings returned by getName
                .collect(Collectors.toList());
        beatlesNames.forEach(artist -> System.out.println(artist));

        List<Integer> integers = Arrays.asList(1, 2, 5, 7, 4, 8, 1, 7);

        List<String> integerStrings = integers.stream()
                .map(integer -> String.valueOf(integer))
                .collect(Collectors.toList());
        integerStrings.forEach(string -> System.out.print(string + " "));


        System.out.println();
        System.out.println();
        List<Album> albums = new ArrayList<>();
        albums.add(SampleData.aLoveSupreme);
        albums.add(SampleData.manyTrackAlbum);
        albums.add(SampleData.sampleShortAlbum);

        Set<String> trackNames = findLongTracks(albums);
        trackNames.forEach(System.out::println);

        Set<String> trackNamesForStream = findLongTracksStream(albums);
        trackNamesForStream.forEach(System.out::println);
    }
    public static Set<String> findLongTracks (List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        for (Album album : albums) {
            for (Track track : album.getTracks()) {
                if (track.getLength() > 60) {
                    String name = track.getName();
                    trackNames.add(name);
                }
            }
        }
        return trackNames;
    }
    public static Set<String> findLongTracksStream(List<Album> albums){
        Set<String> trackNames = new HashSet<>();
        albums.forEach(album -> album.getTracks().stream()
                .filter(track -> track.getLength()>60)
                .forEach(track -> trackNames.add(track.getName())));

        return trackNames;
    }
}
