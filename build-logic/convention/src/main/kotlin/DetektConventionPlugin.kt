
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import io.gitlab.arturbosch.detekt.report.ReportMergeTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.registering
import org.gradle.kotlin.dsl.withType

class DetektConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            extensions.configure<DetektExtension> {
                parallel = true
            }

            val detektReportMerge by tasks.registering(ReportMergeTask::class) {
                output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.sarif"))
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                "detektPlugins"(libs.findLibrary("compose.rules").get())
            }

            plugins.withType<DetektPlugin> {
                tasks.withType<Detekt> {
                    finalizedBy(detektReportMerge)

                    detektReportMerge.configure {
                        reports.sarif.required.set(true)
                        input.from(sarifReportFile)
                    }
                }
            }
        }
    }
}
