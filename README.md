# conf-loader
Java configuration loader
<br>

## Examples

`MyAppConf.java`
```java
public class MyAppConf {
    public String name = "default value";
    private int poolThreadsSize;
    public Float distance;
}
```
<br>

`file.properties`
```properties
name=java app example
poolThreadsSize=10
distance=2.35
```
<br>

### Loading properties file to java class
```java
MyAppConf myConf = ConfLoader.load().fromFile("/path/to/file.properties").toClass(MyAppConf.class);
```
<br>

### Loading environment variable to java class
```java
MyAppConf myConf = ConfLoader.load().fromEnv().toClass(MyAppConf.class);
```

<br>

### Loading from both file and env with file priority
```java
MyAppConf myConf = ConfLoader.load().fromEnv().fromFile("/path/to/file.properties").toClass(MyAppConf.class);
```

<br>

### The opposite(env priority)
```java
MyAppConf myConf = ConfLoader.load().fromFile("/path/to/file.properties").fromEnv().toClass(MyAppConf.class);
```

<br>

### Loading from multiple files (`f2.properties` priority)
```java
MyAppConf myConf = ConfLoader.load().fromFile("f1.properties").fromFile("f2.properties").toClass(MyAppConf.class);
```

### Using Maven
Set up this repository in your `pom.xml`
```java
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```
And add this dependency
```java
<dependencies>
  <dependency>
    <groupId>com.github.urlv</groupId>
    <artifactId>conf-loader</artifactId>
    <version>1.0.0</version>
  </dependency>
</dependencies>
```
