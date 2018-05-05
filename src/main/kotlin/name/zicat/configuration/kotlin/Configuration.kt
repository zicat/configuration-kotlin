package name.zicat.configuration.kotlin

import name.zicat.configuration.kotlin.schema.AbstractSchema
import java.io.InputStream
import java.net.URL

abstract class Configuration<T>(abstractSchema: AbstractSchema<T>) {

    val abstractSchema = abstractSchema
    var instance: T? = null

    /**
     *
     * @return t
     */
    internal abstract fun parse(): T
}

class LocalConfiguration<T>(url: URL, abstractSchema: AbstractSchema<T>): Configuration<T>(abstractSchema) {

    val url = url
    init {
        instance = parse()
    }

    override fun parse(): T {

        var s: InputStream? = null
        try {
            s = url.openStream()
            return abstractSchema.parse(s)
        } finally {
            s?.close()
        }
    }
}