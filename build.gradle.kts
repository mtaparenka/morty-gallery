plugins {
    id("java")
    id("io.spring.dependency-management") version "1.1.6"
    id("org.springframework.boot") version "3.3.2"
}

group = "com.mtaparenka"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val jjwtVersion = "0.12.6"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation ("io.jsonwebtoken:jjwt-api:$jjwtVersion")

    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly ("io.jsonwebtoken:jjwt-jackson:$jjwtVersion")
    runtimeOnly ("io.jsonwebtoken:jjwt-impl:$jjwtVersion")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}