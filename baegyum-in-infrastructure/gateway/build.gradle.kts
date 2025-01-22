plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    rootProject.subprojects.filter { project != it.project }.forEach {
        runtimeOnly(project(it.path))
    }
}

tasks.bootJar {
    archiveBaseName.set(project.rootProject.name)
    destinationDirectory.set(rootProject.layout.buildDirectory.asFile.get().resolve("libs"))
}