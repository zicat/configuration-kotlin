package name.zicat.configuration.kotlin.test.schema

import org.junit.Test
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlValue
import name.zicat.configuration.kotlin.schema.JAXBSchema
import org.junit.Assert
import java.nio.charset.StandardCharsets


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