package name.zicat.configuration.kotlin.schema

import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.util.*

fun propertiesParse(s: InputStream, charset: Charset): Properties {
    val prop = Properties()
    prop.load(InputStreamReader(s, charset))
    return prop
}

class PropertiesSchema(charset: Charset) : AbstractSchema<Properties>(Properties::class.java, charset) {

    override fun parse(s: InputStream): Properties {
        return propertiesParse(s, charset)
    }

}