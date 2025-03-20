import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.ksp) apply false
}

subprojects {
    apply<SpotlessPlugin>()
    configure<SpotlessExtension> {
        kotlin {
            target("src/**/*.kt")
            targetExclude("build/**/*.kt")
            ktlint()
                .editorConfigOverride(
                    mapOf(
                        "max_line_length" to "140",
                        "ij_kotlin_allow_trailing_comma" to "false",
                        "ktlint_standard_filename" to "disabled",
                        "ij_kotlin_allow_trailing_comma_on_call_site" to "false",
                        "ij_kotlin_line_break_after_multiline_when_entry" to "false",
                        "ktlint_standard_no-empty-first-line-in-method-block" to "enabled",
                        "ktlint_function_signature_body_expression_wrapping" to "multiline",
                        "function-signature:force-multiline-when-parameter-count-gte" to "2",
                        "ktlint_function_naming_ignore_when_annotated_with" to "Composable, Test",
                        "ktlint_standard_package-name" to "disabled"
                    )
                )
            this.endWithNewline()
        }
        kotlinGradle {
            target("*.kts")
            ktlint()
        }
    }

    afterEvaluate {
        tasks.withType<KotlinCompile> {
            finalizedBy("spotlessApply")
        }
    }
}