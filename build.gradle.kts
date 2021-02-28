/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    java
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation("javax:javaee-web-api:7.0")
    implementation("mysql:mysql-connector-java:8.0.16")
    implementation("jstl:jstl:1.2")
    implementation("org.apache.logging.log4j:log4j-api:2.10.0")
    implementation("org.apache.logging.log4j:log4j-core:2.10.0")
    implementation("org.hibernate:hibernate-core:5.4.4.Final")
    implementation("org.hibernate:hibernate-c3p0:5.4.4.Final")
    testImplementation("org.junit.platform:junit-platform-runner:1.0.0")
    testImplementation("org.junit.vintage:junit-vintage-engine:4.12.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.0.0")
    testImplementation("junit:junit:4.12")
}

group = "org.example"
version = "1.0-SNAPSHOT"
description = "MinecraftJSONGenerator Maven Webapp"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}