package ${project_package}

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.Window
import org.codehaus.griffon.runtime.tornadofx.artifact.AbstractTornadoFXGriffonView
import tornadofx.*
import javax.annotation.Nonnull

@ArtifactProviderFor(GriffonView::class)
class ${project_class_name}View : AbstractTornadoFXGriffonView() {
    @set:[MVCMember Nonnull]
    lateinit var model: ${project_class_name}Model
    @set:[MVCMember Nonnull]
    lateinit var controller: ${project_class_name}Controller

    override fun initUI() {
        val stage: Stage = application.createApplicationContainer(mapOf()) as Stage
        stage.title = application.configuration.getAsString("application.title")
        stage.scene = _init()
        application.getWindowManager<Window>().attach("mainWindow", stage)
    }

    private fun _init(): Scene {
        val root = gridpane {
            setPrefSize(200.0, 60.0)

            row {
                label(model.clickCountProperty())
            }

            row {
                button {
                    id        = "clickActionTarget"
                    prefWidth = 200.0
                }
            }
        }

        val scene = Scene(root).apply {
            fill = Color.WHITE
        }

        connectActions(root, controller)
        connectMessageSource(root)
        return scene
    }
}
