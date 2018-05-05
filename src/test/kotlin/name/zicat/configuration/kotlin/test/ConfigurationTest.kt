package name.zicat.configuration.kotlin.test

import org.junit.Test
import name.zicat.configuration.kotlin.test.schema.JavaBean
import name.zicat.configuration.kotlin.LocalConfiguration
import name.zicat.configuration.kotlin.schema.JAXBSchema
import org.junit.Assert
import java.nio.charset.StandardCharsets


class ConfigurationTest {

    @Test
    fun test() {


        val schema = JAXBSchema<JavaBean>(JavaBean::class.java, StandardCharsets.UTF_8)
        val url = Thread.currentThread().contextClassLoader.getResource("test.xml")
        val configuration = LocalConfiguration<JavaBean>(url,schema)
        Assert.assertEquals(configuration.instance?.name, "张")
        Assert.assertEquals(configuration.instance?.value, "a")
    }
}