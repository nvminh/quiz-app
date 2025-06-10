pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        id("org.jetbrains.kotlin.jvm") version "2.1.21"
        id("org.jetbrains.kotlin.plugin.spring") version "2.1.21"
        id("org.jetbrains.kotlin.plugin.jpa") version "2.1.21"
        id("org.springframework.boot") version "3.2.5"
        id("io.spring.dependency-management") version "1.1.4"
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}


rootProject.name = "quiz-app"

include("common")
include("quiz-service")
include("score-service")
include("leaderboard-service")