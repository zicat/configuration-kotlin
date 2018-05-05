package name.zicat.configuration.kotlin

import name.zicat.configuration.kotlin.schema.AbstractSchema
import java.io.InputStream
import java.net.URL

abstract class Configuration<T>(abstractSchema: AbstractSchema<T>) {

    val abstractSchema = abstractSchema
    abstract var instance: T

    /**
     *
     * @return t
     */
    abstract fun load(): T
}

class LocalConfiguration<T>(url: URL, abstractSchema: AbstractSchema<T>): Configuration<T>(abstractSchema) {

    val url = url

    override var instance: T
        get() = load()
        set(value) {}

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