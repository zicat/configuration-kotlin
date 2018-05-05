# What is Configuration-Kotlin
    Configuration-Kotlin project is parse xml, properties, json etc file to instance with kotlin.
    The files maybe from local file system, ftp, http, zookeeper etc.
    The files can be watched after it's modified, and notify the listener.

# Demo
## Schema
    Schema is the interface of parsing file. 
    The file can be a string instance or inputstream instance.

### Parse xml file

```kotlin
@XmlRootElement(name = "configuration")
class JavaBean(@XmlAttribute val name: String?, @XmlValue val value:String?) {
    constructor(): this(null, null)
}

class XmlSchemaTest {

    @Test
    fun test() {

        val xml: String = "<configuration name=\"张\">a</configuration>"

        val schema = JAXBSchema(JavaBean::class.java, StandardCharsets.UTF_8)
        val javaBean = schema.parse(xml)
        Assert.assertEquals(javaBean.name, "张")
        Assert.assertEquals(javaBean.value, "a")
    }
}
```

### Parse properties file
```kotlin
class PropertiestSchemaTest {

    @Test
    fun test() {
        val value = "name=张\n" + "value=a\n"
        val schema = PropertiesSchema(StandardCharsets.UTF_8)
        val properties = schema.parse(value)
        Assert.assertEquals(properties.getProperty("value"), "a")
    }
}
```
    
## Configuration
    Configuration is the abstract class that maintain the instance mapping to the file

### LocalConfiguration
    LocalConfiguration is the class that maintain the instance mapping to the file that from local file system
```kotlin
class ConfigurationTest {

    @Test
    fun test() {


        val schema = JAXBSchema<JavaBean>(JavaBean::class.java, StandardCharsets.UTF_8)
        val url = Thread.currentThread().contextClassLoader.getResource("test.xml")
        val configuration = LocalConfiguration<JavaBean>(url,schema)
        Assert.assertEquals(configuration.instance.name, "张")
        Assert.assertEquals(configuration.instance.value, "a")
    }
}
```
