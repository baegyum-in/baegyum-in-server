subprojects {
    val core = rootProject.projects.core
    val defaultGateway = rootProject.projects.infrastructure.gatewayInfrastructure

    if (project != core.dependencyProject) {
        dependencies {
            implementation(core)
        }
    }
    if (project != defaultGateway.dependencyProject) {
        dependencies {
            implementation(defaultGateway)
        }
    }
}