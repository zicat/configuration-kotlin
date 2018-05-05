package name.zicat.configuration.kotlin.schema

import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.nio.charset.Charset
import javax.xml.bind.JAXBContext

fun <T> unmarshal(inputStream: InputStream, clazz: Class<T>, charset: Charset): T {
    val reader = InputStreamReader(inputStream, charset)
    return unmarshal(reader, clazz)
}

fun <T> unmarshal(reader: Reader,clazz: Class<T>): T {
    val context = JAXBContext.newInstance(clazz)
    val um = context.createUnmarshaller()
    return um.unmarshal(reader) as T
}

class JAXBSchema<T>(clazz: Class<T>, charset: Charset): AbstractSchema<T>(clazz, charset) {

    override fun parse(s: InputStream): T {
        return unmarshal(s, clazz, charset)
    }
}