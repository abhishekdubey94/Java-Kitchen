## Java 8 Streams

- Streams are wrappers around a data source, allowing us to operate with that data source and making bulk processing convenient and fast.
- A stream does not store data and, in that sense, is not a data structure. It also never modifies the underlying data source.
- **Lazy Evaluation**
  - One of the most important characteristics of Java streams is that they allow for significant optimizations through lazy evaluations.
  - Computation on the source data is only performed when the terminal operation is initiated, and source elements are consumed only as needed.
  - All intermediate operations are lazy, so they’re not executed until a result of a processing is actually needed.

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

1. forEach

   ```
   list.stream().forEach(e -> System.out.println(e));
   ```

   Note : forEach() is a terminal operation, which means that, after the operation is performed, the stream pipeline is considered consumed, and can no longer be used.

2. map - map() produces a new stream after applying a function to each element of the original stream.

   ```
   List<Employee> employees = Stream.of(empIds)
     .map(employeeRepository::findById)
     .collect(Collectors.toList());
   ```

3. collect - its one of the common ways to get stuff out of the stream once we are done with all the processing

   ```
   List<Employee> employees = empList.stream().collect(Collectors.toList());
   ```

4. filter - this produces a new stream that contains elements of the original stream that pass a given test (specified by a Predicate).
