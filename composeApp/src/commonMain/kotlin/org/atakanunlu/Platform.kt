package org.atakanunlu

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform