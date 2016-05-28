package VirtualWorld;

/**
 * Created by Kuba on 2016-05-09.
 */
class StackElement implements java.io.Serializable
{
    StackElement previous;
    String text;
    public StackElement() {
        this.previous = null;
        this.text = "";
    }
};
public class Messages implements java.io.Serializable
{
    StackElement top = new StackElement();

    public Messages() {
        this.top = null;
    }

    void push(String text_oryg) {
        if (text_oryg != "") {
            StackElement newEl = new StackElement();
            newEl.previous = null;
            if (top != null)
                newEl.previous = top;
            newEl.text = text_oryg;
            top = newEl;
        }
    }

    void popBack() {
        if (top != null) {
            StackElement tmp =top;
            top = top.previous;
        }
    }

    String show(int messageNumber) {
        StackElement tmp = top;
        if (top== null) {
            return "";
        }
        else {
            for (int i = 0; i < messageNumber; i++) {
                tmp = tmp.previous;
            }
            if (tmp != null) {
                return tmp.text;
            }
            else return "";
        }
    }
}
