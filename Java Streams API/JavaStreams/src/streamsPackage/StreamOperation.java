package streamsPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StreamOperation {

	public static void main(String[] args) {
		
		// array
		int[] array = new int[5];
		for (int i = 0; i < 5; i++) {
			array[i] = i;
		}
		
		// list
		String[] names = { "Rahul", "Utkarsh", "Shubham", "Neelam" };
		List<String> list = new ArrayList<String>(Arrays.asList(names));


		// forEach
		System.out.println("\nforEach:");
		Arrays.stream(array).forEach(e -> System.out.println(e));
		list.stream().forEach(e -> System.out.println(e));

		// map
		System.out.println("\nmap:");
		Arrays.stream(array).map( e -> e*2).forEach((e)-> {System.out.println(e);});
		
		// collect
		System.out.println("\ncollect:");
		List<String> collectList =list.stream()
	            .collect(Collectors.toList());
		collectList.stream().forEach(e->System.out.println(e));
		
		// filter
		System.out.println("\nfilter:");
		Arrays.stream(array).filter(e -> e%2==0).forEach(e->System.out.println(e));
		
		// findFirst
		System.out.println("\nfindFirst:");
		String ans = list.stream().filter(e->e.startsWith("U")).findFirst().orElse(null);
		System.out.println(ans);
		
		// toArray
		System.out.println("\ntoArray:");
		String[] names1 = list.stream().toArray(String[]::new);
		
		// flatMap
		System.out.println("\nflatMap:");
		List<List<String>> namesNested = Arrays.asList( 
			      Arrays.asList("Jeff", "Bezos"), 
			      Arrays.asList("Bill", "Gates"), 
			      Arrays.asList("Mark", "Zuckerberg"));
		
		List<String> flatNames = namesNested.stream().flatMap(Collection::stream).collect(Collectors.toList());
		flatNames.stream().forEach(System.out::println);
		

	}

}
