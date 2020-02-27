allprojects {
    repositories {
        mavenCentral()
    }
}

allprojects {
    version = "0.0.1-SNAPSHOT"
    group = "io.herain"
}

plugins {
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("idea")
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
}

java.sourceCompatibility = JavaVersion.VERSION_11

val developmentOnly by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.postgresql:postgresql:42.2.10")
    implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:6.0.1")
    implementation("com.zhokhov.graphql:graphql-datetime-spring-boot-starter:1.6.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.codehaus.groovy:groovy-all:2.5.8")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.spockframework:spock-core:2.0-M2-groovy-2.5")
    testImplementation("info.solidsoft.spock:spock-global-unroll:0.5.1")
}
