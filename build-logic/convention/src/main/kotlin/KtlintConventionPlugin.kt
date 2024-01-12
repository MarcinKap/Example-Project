import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

class KtlintConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jlleitschuh.gradle.ktlint")
            extensions.configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
                val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

                version.set(libs.findVersion("ktlint").get().toString())
                android.set(true)
                enableExperimentalRules.set(true)
                filter {
                    exclude { element -> element.file.path.contains("generated/") }
                }
                outputToConsole.set(true)
                outputColorName.set("RED")
                reporters {
                    reporter(ReporterType.HTML)
                }
            }
        }
    }
}
