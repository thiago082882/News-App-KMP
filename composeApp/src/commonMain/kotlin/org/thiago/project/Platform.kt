package org.thiago.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform