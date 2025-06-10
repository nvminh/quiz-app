plugins {
    kotlin("jvm") version "1.9.0" apply false
    id("org.springframework.boot") version "3.2.0" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
}

allprojects {
    group = "com.example"
    version = "1.0.0"
}

subprojects {
    repositories {
        mavenCentral()
    }
}