import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.example.configureBuildTypes
import com.example.configureFlavors
import com.example.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<BaseAppModuleExtension> {
                configureKotlinAndroid(this)
                configureFlavors(this)
                configureBuildTypes(this)
                defaultConfig.targetSdk = 33
            }
        }
    }
}
