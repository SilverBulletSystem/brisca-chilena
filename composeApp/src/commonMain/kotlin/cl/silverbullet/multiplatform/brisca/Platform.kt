package cl.silverbullet.multiplatform.brisca

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform