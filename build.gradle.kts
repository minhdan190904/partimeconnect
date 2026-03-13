plugins {
    java
    id("org.springframework.boot") version "4.0.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.epu"
version = "0.0.1-SNAPSHOT"
description = "partimeconnect"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.projectlombok:lombok")

        runtimeOnly("com.mysql:mysql-connector-j")

        annotationProcessor("org.projectlombok:lombok")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
