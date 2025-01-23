plugins {
    alias(libs.plugins.spring.boot)
}

configurations {
    configureEach {
        exclude(
            group = libs.spring.boot.starter.logging.get().group,
            module = libs.spring.boot.starter.logging.get().module.name
        )
    }
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.log4j2)
    rootProject.subprojects.filter { project != it.project }.forEach {
        runtimeOnly(project(it.path))
    }
}

tasks.bootJar {
    archiveBaseName.set(project.rootProject.name)
    destinationDirectory.set(rootProject.layout.buildDirectory.asFile.get().resolve("libs"))
}