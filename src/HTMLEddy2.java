import javafx.css.StyleableProperty;
import javafx.print.PrinterJob;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * Created by Skynet on 27.03.2016.
 */
public class HTMLEddy2 extends Control {

    /**
     * Creates a new instance of the HTMLEditor control.
     */
    public HTMLEddy2() {
        ((StyleableProperty)super.skinClassNameProperty()).applyStyle(
                null,
                "HTMLEddy2Skin"
        );
        getStyleClass().add("html-editor");
    }

    @Override protected Skin<?> createDefaultSkin() {
        return new HTMLEddy2Skin(this);
    }

    /**
     * Returns the HTML content of the editor.
     */
    public String getHtmlText() {
        return ((HTMLEddy2Skin)getSkin()).getHTMLText();
    }


    /**
     * Returns the Selected content of the editor.
     */
    public String getSelectedText() {
        return ((HTMLEddy2Skin)getSkin()).getSelTEXT();
    }



    /**
     * Sets the HTML content of the editor. Note that if the contentEditable
     * property on the <body> tag of the provided HTML is not set to true, the
     * HTMLEditor will become read-only. You can ensure that the text remains
     * editable by ensuring the body appears as such:
     * <code>
     * &lt;body contentEditable="true"&gt;
     * </code>
     *
     * @param htmlText The full HTML markup to put into the editor. This should
     *      include all normal HTML elements, starting with
     *      <code>&lt;html&gt;</code>, and including a <code>&lt;body&gt;</code>.
     */
    public void setHtmlText(String htmlText) {
        ((HTMLEddy2Skin)getSkin()).setHTMLText(htmlText);
    }

    /**
     * Prints the content of the editor using the given printer job.
     * <p>This method does not modify the state of the job, nor does it call
     * {@link PrinterJob#endJob}, so the job may be safely reused afterwards.
     *
     * @param job printer job used for printing
     * @since JavaFX 8.0
     */
    public void print(PrinterJob job) {
        ((HTMLEddy2Skin)getSkin()).print(job);
    }
}
