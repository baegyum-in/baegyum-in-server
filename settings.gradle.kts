rootProject.name = ext["project.name"].toString()

includeModule("baegyum-in-core", "core")
includeModules("baegyum-in-infrastructure", "infrastructure")
includeModules("baegyum-in-service", "service")

fun includeModule(parentDirectory: String, name: String) {
    val modulePath = ":$name"
    include(modulePath)

    val projectDirectory = rootProject.projectDir
    project(modulePath).projectDir = projectDirectory.resolve(parentDirectory)
}

fun includeModules(parentDirectory: String, parentModuleName: String) {
    includeModule(parentDirectory, parentModuleName)

    val projectDirectory = rootProject.projectDir
    val childModuleDirectories = projectDirectory
        .resolve(parentDirectory)
        .listFiles()
        ?.filter { it.isDirectory  || it.name == "build" } ?: emptyList()
    childModuleDirectories.forEach { childModuleDirectory ->
        val classifier = parentModuleName
            .replace(":", "-")

        val modulePath = ":$parentModuleName:${childModuleDirectory.name}-$classifier"
        include(modulePath)
        project(modulePath).projectDir = childModuleDirectory
    }
}

pluginManagement {
    repositories {
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")