rootProject.name = ext["project.name"].toString()

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
        ?.filter { it.isDirectory } ?: emptyList()
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
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}