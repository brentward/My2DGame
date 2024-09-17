plugins {
    application
}

application {
    mainClass = "org.brentwardindustries.main.Main"
}

group = "org.brentwardindustries"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.java.jinput:jinput:2.0.9")
    implementation("net.java.jinput:jinput:2.0.9:natives-all")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}