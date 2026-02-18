pluginManagement {
    repositories {
        google() // Simplificado: permite que busque todo lo de Google sin filtros
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MinutaNutricional"
include(":app")
