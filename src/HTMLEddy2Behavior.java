import com.sun.javafx.scene.control.behavior.BehaviorBase;
import com.sun.javafx.scene.control.behavior.KeyBinding;
import com.sun.javafx.scene.web.skin.HTMLEditorSkin;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.*;


/**
 * HTML editor behavior.
 */
public class HTMLEddy2Behavior extends BehaviorBase<HTMLEddy2> {
    protected static final List<KeyBinding> HTML_EDITOR_BINDINGS = new ArrayList<KeyBinding>();

    static {
        HTML_EDITOR_BINDINGS.add(new KeyBinding(B, "bold").shortcut());
        HTML_EDITOR_BINDINGS.add(new KeyBinding(I, "italic").shortcut());
        HTML_EDITOR_BINDINGS.add(new KeyBinding(U, "underline").shortcut());

        HTML_EDITOR_BINDINGS.add(new KeyBinding(F12, "F12"));
        HTML_EDITOR_BINDINGS.add(new KeyBinding(TAB, "TraverseNext").ctrl());
        HTML_EDITOR_BINDINGS.add(new KeyBinding(TAB, "TraversePrevious").ctrl().shift());
    }

    public HTMLEddy2Behavior(HTMLEddy2 htmlEditor) {
        super(htmlEditor, HTML_EDITOR_BINDINGS);
    }

    @Override
    protected void callAction(String name) {
        if ("bold".equals(name) || "italic".equals(name) || "underline".equals(name)) {
            HTMLEddy2 editor = getControl();
            HTMLEditorSkin editorSkin = (HTMLEditorSkin)editor.getSkin();
            editorSkin.keyboardShortcuts(name);
        } else if ("F12".equals(name)) {
            getControl().getImpl_traversalEngine().selectFirst().requestFocus();
        } else {
            super.callAction(name);
        }
    }
}
