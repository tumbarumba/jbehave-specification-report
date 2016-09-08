# JBehave Specification Report

Builds a single page report of all JBehave stories.

To use, add the following dependency:

Maven:
```
<dependency>
  <groupId>com.exubero.jbehave</groupId>
  <artifactId>jbehave-specification-report</artifactId>
  <version>0.9.0</version>
</dependency>
```

Gradle:
```
dependencies {
    ...
    testCompile 'com.exubero.jbehave:jbehave-specification-report:0.9.0'
}
```

The report can be run as follows:

```java
import static com.exubero.jbehave.specification.JBehaveSpecificationBuilder.aSpecificationBuilderWithSteps;

public class CreateSpecification {
    public static final void main(String[] args) {
        StorySteps storySteps = new StorySteps();
        aSpecificationBuilderWithSteps(storySteps)
            .withSpecificationTitle("Example Specifications")
            .run();
    }
}
```

This will run all resources matching the path spec `**/*.story` in the
same location at the `StorySteps` class. 


See [`SpecificationViewGeneratorTest`](https://github.com/tumbarumba/jbehave-specification-report/blob/master/src/test/java/com/exubero/jbehave/specification/SpecificationViewGeneratorTest.java)
for an example of how this looks in working code.
