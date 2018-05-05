package name.zicat.configuration.kotlin.test.schema

import org.junit.Test
import name.zicat.configuration.kotlin.schema.PropertiesSchema
import org.junit.Assert
import java.nio.charset.StandardCharsets


class PropertiestSchemaTest {

    @Test
    fun test() {
        val value = "name=张\n" + "value=a\n"
        val schema = PropertiesSchema(StandardCharsets.UTF_8)
        val properties = schema.parse(value)
        Assert.assertEquals(properties.getProperty("value"), "a")
    }
}