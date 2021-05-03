## Java 8 Streams

- Streams are wrappers around a data source, allowing us to operate with that data source and making bulk processing convenient and fast.
- A stream does not store data and, in that sense, is not a data structure. It also never modifies the underlying data source.
- **Lazy Evaluation**
  - Computation on the source data is only performed when the terminal operation is initiated, and source elements are consumed only as needed.
  - All intermediate operations are lazy, so they’re not executed until a result of a processing is actually needed.
- Java stream operations are divided into intermediate and terminal operations.
- A stream pipeline consists of a stream source, followed by zero or more intermediate operations, and a terminal operation.

### Java Stream Creation

1. From existing array :

   ```
   Stream.of(array);
   ```

2. From existing list :
   ```
   list.stream();
   ```
   Note : Java 8 added a new stream() method to the Collection interface.

### Java Stream Operations

1. **forEach**

   ```
   list.stream().forEach(e -> System.out.println(e));
   ```

   Note : forEach() is a terminal operation, which means that, after the operation is performed, the stream pipeline is considered consumed, and can no longer be used.

2. **map** - map() produces a new stream after applying a function to each element of the original stream.

   ```
   List<Employee> employees = Stream.of(empIds)
     .map(employeeRepository::findById)
     .collect(Collectors.toList());
   ```

3. **collect** - its one of the common ways to get stuff out of the stream once we are done with all the processing

   ```
   List<Employee> employees = empList.stream().collect(Collectors.toList());
   ```

4. **filter** - this produces a new stream that contains elements of the original stream that pass a given test (specified by a Predicate).
   ```
   List<Employee> employees = Stream.of(empIds)
      .map(employeeRepository::findById)
      .filter(e -> e != null)
      .filter(e -> e.getSalary() > 200000)
      .collect(Collectors.toList());
   ```
5. **findFirst** - findFirst() returns an Optional for the first entry in the stream; the Optional can, of course, be empty
   ```
   List<Employee> employees = Stream.of(empIds)
      .map(employeeRepository::findById)
      .filter(e -> e != null)
      .filter(e -> e.getSalary() > 200000)
      .findFirst()
      .orElse(null);
   ```
6. **toArray** - If we need to get an array out of the stream, we can simply use toArray()
   ```
   Employee[] employees = empList.stream().toArray(Employee[]::new);
   ```
7. **flatMap** - flatMap() helps us to flatten the data structure to simplify further operations

   ```
   List<List<String>> namesNested = Arrays.asList(
      Arrays.asList("Jeff", "Bezos"),
      Arrays.asList("Bill", "Gates"),
      Arrays.asList("Mark", "Zuckerberg"));

    List<String> namesFlatStream = namesNested.stream()
      .flatMap(Collection::stream)
      .collect(Collectors.toList());
   ```

### Comparison Based Stream Operation

1. **sorted** - This sorts the stream elements based on the comparator passed we pass into it. Short-circuiting will not be applied for sorted().

   ```
    List<Employee> employees = empList.stream()
      .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
      .collect(Collectors.toList());
   ```

2. **min and max** - min() and max() return the minimum and maximum element in the stream respectively, based on a comparator. They return an Optional since a result may or may not exist (due to, say, filtering):
   ```
   Employee firstEmp = empList.stream()
     .min((e1, e2) -> e1.getId() - e2.getId())
     .orElseThrow(NoSuchElementException::new);
   ```
3. **distict** - distinct() does not take any argument and returns the distinct elements in the stream, eliminating duplicates. It uses the equals() method of the elements to decide whether two elements are equal or not:

   ```
    List<Integer> distinctIntList = intList.stream().distinct().collect(Collectors.toList());
   ```

4. **allMatch, anyMatch, and noneMatch** - These operations all take a predicate and return a boolean. Short-circuiting is applied and processing is stopped as soon as the answer is determined.
   ```
    boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
    boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
    boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);
   ```
