import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.plugin.spring) apply false
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management)
    java
}

subprojects {
    group = property("project.group").toString()
    version = property("project.version").toString()
    with(pluginManager) {
        apply(rootProject.libs.plugins.kotlin.jvm.get().pluginId)
        apply(rootProject.libs.plugins.kotlin.plugin.spring.get().pluginId)
        apply(rootProject.libs.plugins.spring.dependency.management.get().pluginId)
    }
    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    tasks.withType<KotlinCompile> {
        compilerOptions {
            freeCompilerArgs.add("-Xjsr305=strict")
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
    dependencyManagement {
        imports {
            mavenBom(rootProject.libs.spring.boot.dependencies.get().toString())
        }
    }
    dependencies {
        implementation(rootProject.libs.kotlin.reflect)
    }
}