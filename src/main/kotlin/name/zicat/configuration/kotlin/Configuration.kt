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
    abstract fun load(): T

    fun createInstance(): T? {
        instance = load()
        return instance
    }
}

class LocalConfiguration<T>(url: URL, abstractSchema: AbstractSchema<T>): Configuration<T>(abstractSchema) {

    val url = url

    override fun load(): T {

        var s: InputStream? = null
        try {
            s = url.openStream()
            return abstractSchema.parse(s)
        } finally {
            s?.close()
        }
    }
}