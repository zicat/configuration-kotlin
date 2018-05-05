package name.zicat.configuration.kotlin.schema

import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets


interface Schema<S, T> {

    fun parse(s: S): T
}

interface StringSchema<T>: Schema<String, T>

interface InputStreamSchema<T>: StringSchema<T> {

    fun parse(s: InputStream): T
}

abstract class AbstractSchema<T>(clazz: Class<T>, charset: Charset = StandardCharsets.UTF_8): InputStreamSchema<T> {

    val clazz = clazz
    val charset = charset

    override fun parse(str: String): T {

        var inputStream: InputStream? = null
        try {
            inputStream = ByteArrayInputStream(str.toByteArray(charset))
            return parse(inputStream)
        } finally {
            inputStream?.close()
        }
    }

}